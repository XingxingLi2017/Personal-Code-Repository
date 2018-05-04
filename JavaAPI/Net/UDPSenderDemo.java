import java.io.IOException;
import java.net.*;
import java.io.*;

public class UDPSenderDemo {

	public static void main(String[] args){
		DatagramSocket ds = null;
		try{
			// create a new socket to send data packets
			 ds = new DatagramSocket();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// prepare the message
			String message = null;
			while((message = br.readLine())!= null){
				byte[] data = message.getBytes();
				DatagramPacket dp = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 10000);
				// send the packet
				ds.send(dp);
			}
		}catch(IOException e){}
		finally{
			if(ds != null){
				ds.close();
			}
		}
		
	}
}
