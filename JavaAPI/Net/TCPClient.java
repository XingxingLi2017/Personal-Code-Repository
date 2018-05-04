import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args)throws IOException{
        try{
            TCPClient client = new TCPClient();
            client.runClient();
        }catch(IOException e){

        }
        

    }
    private Socket client;

    public TCPClient() throws IOException{
        InetAddress local = InetAddress.getLocalHost();
        client = new Socket(local,10000);
    }

    public void runClient()throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String message = null;
        String serverMessage = null;
        while((message = input.readLine())!=null){
            System.out.println(message);
            out.write(message, 0, message.length());
            out.newLine();
            out.flush();
            if(message.trim().equals("over")){
                break;
            }
        }
        client.close();

    }
}