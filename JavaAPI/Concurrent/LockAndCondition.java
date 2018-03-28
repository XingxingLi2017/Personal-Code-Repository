import java.util.concurrent.locks.*;


public class HelloIntellij {

    public static void main(String[] args) {
        Resource r = new Resource();
        Pro pro = new Pro(r);
        Con con = new Con(r);
        new Thread(pro).start();
        new Thread(pro).start();
        new Thread(con).start();
        new Thread(con).start();
    }

}
// shared resource
class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;
    //synchronized lock
    Lock lock = new ReentrantLock();
    // multiple wait-sets
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();


    public void set(String name){
        lock.lock();
        try{
            while(flag)
                // use while loop to avoid that thread x waiting here doesn't check flag and go to the next code line
                // use wait in while(flag) loop for thread safe. 
                try{producer_con.await();}catch(InterruptedException e){e.printStackTrace();}
            this.name = name+Integer.toString(count);
            count++;
            System.out.println(Thread.currentThread().getName()+"...producer..."+this.name);
            flag = true;
            consumer_con.signal();

        }
        finally {
            lock.unlock();
        }
    }
    public void out(){
        lock.lock();
        try{
            while(!flag)
                try{consumer_con.await();}catch(InterruptedException e){e.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+"-----consumer-----"+this.name);
            flag = false;
            producer_con.signal();
        }
        finally {
            lock.unlock();
        }
    }
}

class Pro implements Runnable{
    private Resource r;
    public Pro(Resource r){
        this.r = r;
    }

    @Override
    public void run(){
        while(true){
            r.set("Roast Duck");
        }
    }
}

class Con implements Runnable{
    private Resource r;
    public Con(Resource r){
        this.r = r;
    }
    @Override
    public void run(){
        while(true){
            r.out();
        }
    }
}

