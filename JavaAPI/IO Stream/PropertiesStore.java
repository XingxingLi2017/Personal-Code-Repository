package Day23;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class PropertiesStore {

    public static void main(String[] args) throws IOException{
        Properties prop = new Properties();
        prop.setProperty("name","xing");
        prop.setProperty("age","24");
        prop.setProperty("D.O.B","April 18, 1994");

        Set<String> entries = prop.stringPropertyNames();
        for(String s1:entries){
            System.out.println(s1+"="+prop.getProperty(s1));
        }

        prop.list(System.out);

        File myProp = new File("./myProperties.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(myProp));
        String comments = "Hello everyone, i'm xing, this is a test comment";
        prop.store(bw, comments);

        Properties newProp = new Properties();
        BufferedReader br = new BufferedReader(new FileReader(myProp));

        newProp.load(br);
        System.out.println(newProp.toString());


    }
}
