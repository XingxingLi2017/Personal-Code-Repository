package Reflection;

public class SoundCard implements PCI {
    @Override
    public void open() {
        System.out.println("this is sound card.");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close(){
        System.out.println("sound card close.");
    }
}
