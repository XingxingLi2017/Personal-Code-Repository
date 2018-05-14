package Reflection;

public class Mainboard {

    public void run(){
        System.out.println("main board is running.");
        try{Thread.sleep(1000);}catch (InterruptedException e){ e.printStackTrace();}
    }

    public void usePCI(PCI p){
        if(p != null){
            p.open();
            p.close();
        }
    }
}
