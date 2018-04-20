import java.io.*;


public class PrintsStream {

    public static void main(String[] args) throws IOException {
        new PrintsStream().show();
    }

    public void show() throws IOException{
        // create and connect pipes firstly.
        PipedOutputStream out =  new PipedOutputStream();
        PipedInputStream in = new PipedInputStream(out);
        new Thread(new Output(out)).start();
        new Thread(new Input(in)).start();

    }
    private class Output implements Runnable{
        private PipedOutputStream out;
        public Output(PipedOutputStream out){
            this.out = out;
        }
        @Override
        public void run(){
            BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            try{
                while((message = read.readLine())!=null){
                    out.write(message.getBytes());
                    if("over".equalsIgnoreCase(message))
                        break;
                }

            }catch(IOException e){

            }
            finally {
                try{out.close();}catch(IOException e){}
            }


        }
    }

    private class Input implements Runnable{
        private PipedInputStream in;
        public Input(PipedInputStream in){
            this.in = in;
        }
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int len = 0;
            try{
                while((len = in.read(buffer))!= -1){
                    System.out.println("Input Thread: "+Thread.currentThread().getName()
                    +":"+new String(buffer,0, len));
                    if(new String(buffer,0, len).trim().equalsIgnoreCase("over"))
                        break;
                }
            }catch (IOException e){
                throw new RuntimeException("IOException in Input thread.");
            }
            finally {
                try{in.close();}catch(IOException e){}
            }

        }
    }
}
