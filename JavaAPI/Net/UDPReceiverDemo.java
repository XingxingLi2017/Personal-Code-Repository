import java.io.IOException;
import java.net.*;

public class UDPReceiverDemo {

	public static void main(String[] args){
		DatagramSocket ds = null;
		try{
			// create the socket
			ds = new DatagramSocket(10000);
			while(true)
			{
				//
				byte[] buffer = new byte[1024];
				//
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				//
				ds.receive(dp);
				//
				byte[] data = dp.getData();
				System.out.println("data: "+ new String(data).trim());
				System.out.println("packet length: "+dp.getLength());
				System.out.println("sender port number: "+dp.getPort());
				System.out.println("sender address: "+dp.getAddress().toString());
				if("over".equals( new String(data).trim())){
					break;
				}
			}
			
		}catch(IOException E){}
		finally{
			if(ds != null)
			ds.close();
		}
		
	}
}