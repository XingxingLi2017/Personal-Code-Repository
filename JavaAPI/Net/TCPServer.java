import java.io.*;
import java.net.*;

public class TCPServer {
    private ServerSocket ss = null;

    public static void main(String[] args){
        try{
            TCPServer server = new TCPServer();
            server.runServer();
        }catch(IOException e){}

    }

    public TCPServer() throws IOException {
        try {
            ss = new ServerSocket(10000);
            System.out.println(ss.getInetAddress().toString());
            System.out.println(ss.getLocalPort());
        }
        catch(IOException e){
            throw new IOException("fail to create server socket.");
        }
    }

    public void runServer() throws IOException {
        while(true){
            Socket client = ss.accept();
            System.out.println("this is runServer.");
            new Thread(new Task(client)).start();
        }
    }

    class Task implements Runnable{
        private Socket client;
        public Task(Socket client){
            this.client = client;
        }
        @Override
        public void run(){
            System.out.println("this is Task run.");
            System.out.println("client address: " + client.getInetAddress());
            System.out.println("client port: " + client.getPort());

            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                InetAddress clientAddr = client.getInetAddress();
                String message = "";
                while((message = in.readLine())!=null){
                    if("over".equals(message.trim())) {
                        System.out.println("client " + clientAddr.getHostName() + " disconnected.");
                        break;
                    }
                    System.out.println("client " + clientAddr.getHostName() + ": "+message);
                }
                client.close();
            }catch (IOException e){

            }
        }

    }

}
