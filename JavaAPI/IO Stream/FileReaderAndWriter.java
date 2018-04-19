import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HelloIntellij{

    public static void main(String[] args) throws IOException {
        FileReader reader = null;
        FileWriter writer = null;
        try{
            // to deal with string, use reader and writer
            /*
            * 1. open stream with path
            * 2. create buffer
            * 3. use loop to read and write
            * */
            reader = new FileReader("/Users/lixingxing/Desktop/test.txt");
            writer = new FileWriter("/Users/lixingxing/Documents/test_copy.txt");
            char[] buffer = new char[1024];
            int len = 0;
            while((len = reader.read(buffer))!= -1){
                writer.write(buffer,0,len);
            }
            System.out.println("finish copying.");
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
               writer.close();
            }
        }


    }
}