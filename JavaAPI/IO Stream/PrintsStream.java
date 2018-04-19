package Day23;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.out;



public class PrintsStream {

    public static void main(String[] args) throws IOException {
        PrintStream ps = new PrintStream(new FileOutputStream("test.txt"));

        ps.write(97);
        ps.println();
        ps.println(97);


    }
}
