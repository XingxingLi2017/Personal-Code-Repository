import java.net.*;
import java.nio.charset.Charset;
import java.io.*;

public class MimicBrowser{

	public static void main(String[] args) throws UnknownHostException, IOException {
		// use socket with url
		String url_str = "http://www.google.com";
		
		URL url = new URL(url_str);
		// extract information from url:
		/* protocol
		 * host
		 * port: 443 for https & 80 for http
		 * path of target file
		 * query
		 * */
		String host = url.getHost();
		String path = url.getPath();
		if(path == null || path.length() == 0){
			path = "/";
		}
		String query = url.getQuery();
		if(query != null && query.length() > 0){
			path += "?"+query;
		}
		
		String protocol = url.getProtocol();
		int port = url.getPort();
		if(port == -1){
			if(protocol.equals("http"))
				port = 80;
			else if(protocol.equals("https"))
				port = 443;
			else
				return;
		}
		
		Socket s = new Socket(host, port);
		PrintWriter out = new PrintWriter(s.getOutputStream());
		/* prepare the https request
		 * must have:
		 * Request line: GET(POST) /path HTTP/1.1
		 * separate lines: a blank line to separate headers & body
		 * 
		 * optional:
		 * http headers
		 * 
		 * */
		out.println("GET "+path+" HTTP/1.1");
		out.println("Accept: */*");
		out.println("Host:"+host);
		out.println("Content-Type: text/html");
		out.println("Connection: close");
		out.println("");
		out.println("");
		out.flush();
		
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024*1024];
		int len = in.read(buf);
		System.out.println("len = "+ len);
		System.out.println(new String(buf, 0 , len, Charset.forName("ISO-8859-1")));
		s.close();
	}

}
