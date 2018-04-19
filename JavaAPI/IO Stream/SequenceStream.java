package Day23;

import java.io.File;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class SequenceStream {

    public static void main(String[] args) throws IOException {
        File f1 = new File("test.txt");
        File f2 = new File("test2.txt");
        File f3 = new File("test3.txt");

        PrintWriter pw1 = new PrintWriter(f1);
        PrintWriter pw2 = new PrintWriter(f2);
        PrintWriter pw3 = new PrintWriter(f3);
        // generate some test data
        pw1.println("file 1: donaigoisnniwfapwfn <the end >");
        pw2.println("file 2: sdignaidnfaosnfdfna <the end >");
        pw3.println("file 2: asdifnoweifnaifiengrng <the end >");

        pw1.close();
        pw2.close();
        pw3.close();

        // to improve the performance of Enumeration, we will use the iterator
//        Vector<InputStream> vec = new Vector<>();
//        vec.add(new FileInputStream(f1));
//        vec.add(new FileInputStream(f2));
//        vec.add(new FileInputStream(f3));
//        // combine the sources
//        SequenceInputStream sequencein = new SequenceInputStream(vec.elements());
        // use iterator
        ArrayList<InputStream> inArray = new ArrayList<>();
        inArray.add(new FileInputStream(f1));
        inArray.add(new FileInputStream(f2));
        inArray.add(new FileInputStream(f3));
        /*
        Iterator<InputStream> itr = inArray.iterator();
        Enumeration<InputStream> en = new Enumeration<InputStream>(){
            @Override
            public boolean hasMoreElements(){
                return itr.hasNext();
            }
            @Override
            public InputStream nextElement(){
                return itr.next();
            }

        };
        */
        Enumeration<InputStream> en = Collections.enumeration(inArray);
        // combine multiple input source
        SequenceInputStream sequencein = new SequenceInputStream(en);

        FileOutputStream out = new FileOutputStream("test4.txt");
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=sequencein.read(buffer))!=-1){
            out.write(buffer,0,len);
        }

        out.close();
        sequencein.close();


    }
}
