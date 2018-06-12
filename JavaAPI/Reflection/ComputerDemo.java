package Reflection;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ComputerDemo {
    public static void main(String[] args) throws Exception{
        Mainboard mainboard = new Mainboard();
        mainboard.run();
        // combine profile with reflection to avoid modifying source code
        // load class name from profile
        File profile = new File("pci.properties");
        Properties properties = new Properties();
        //
        FileInputStream fis = new FileInputStream(profile);
        properties.load(fis);
        fis.close();
        // traverse profile to find the classes'name and get the instance
        Set<String> keys = properties.stringPropertyNames();
        for(Iterator<String> itr = keys.iterator() ; itr.hasNext() ;){
            String key = itr.next();
            String className = properties.getProperty(key).trim();
            try{
                // load the class file
                Class clazz = Class.forName(className);
                PCI p = (PCI)clazz.newInstance();
                // use the pci
                mainboard.usePCI(p);
            }catch(ClassNotFoundException e){
                System.out.println("can't find the specified class in profile. Please check the class name.");
            }

        }

    }
}
