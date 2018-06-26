import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

	public static void main(String[] args) {

		try {
			//listen to a port
			ServerSocket server = new ServerSocket(55555);
			System.out.println(server.getInetAddress());
			// get request socket
			Socket client =  server.accept();
			System.out.println("client address = "+ client.getInetAddress());
			// print request
			InputStream in = client.getInputStream();
			byte[] buffer = new byte[1024];
			int len = in.read(buffer);
			System.out.println(new String(buffer, 0, len));

			// response
			PrintWriter out = new PrintWriter(client.getOutputStream());
			out.println("http/1.1 200 ok");
			out.println("connection: closed");
			out.println("content-type: text/html");
			out.println("");
			out.println("<!DOCTYPE html>"
					+ "<html>"
					+"<head><title>Response</title></head>"
					+"<body><h1>Submit Successfully!</h1></body>"
					+ "</html>");
//			out.println("<font color='lightgreen' size='7'>Successfully!</font>");
			out.flush();
			client.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
