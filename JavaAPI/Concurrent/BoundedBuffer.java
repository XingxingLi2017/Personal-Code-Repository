import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            if(count == items.length)
                System.out.println("buffer is full now.");
            while (count == items.length){
                notFull.await();
            }

            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            if(count == 0)
                System.out.println("buffer is empty now.");
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args){
        BoundedBuffer buffer = new BoundedBuffer();
        Producer pro = new Producer(buffer);
        Consumer con = new Consumer(buffer);
        new Thread(pro).start();
        new Thread(pro).start();
        new Thread(pro).start();
        new Thread(con).start();

    }


}

class Producer implements Runnable{
    private BoundedBuffer buffer;
    public Producer(BoundedBuffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        Random r = new Random();
        while(true){
            try {
                Thread.sleep(100);
                Integer in = new Integer(r.nextInt(100));
                System.out.println(Thread.currentThread().getName()+"...Producer..."+in.toString());
                buffer.put(in);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private BoundedBuffer buffer;
    public Consumer(BoundedBuffer buffer){
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"---consumer---"+((Integer)buffer.take()).toString());
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}