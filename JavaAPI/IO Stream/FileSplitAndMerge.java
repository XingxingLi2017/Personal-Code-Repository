import java.io.*;
import java.util.*;

public class FileSplit {
	private static final int BUFFER_SIZE = 1024*1024;

	public static void main(String[] args) throws IOException{
		FileSplit fs = new FileSplit();
		File source = fs.prepareFile();
		File dir = fs.splitFile(source);
		fs.mergeFiles(dir);
	}  
	public File prepareFile() throws FileNotFoundException{
		// prepare the file to be splited.
//		File file = new File("SourceFile.txt");
//		PrintWriter fw = new PrintWriter(new FileOutputStream(file));
//		String text = "<start>fasdfwefiasnfonweofi"
//						+ "nwoilefhj;owihfe;qoiwhjf;"
//						+ "klsandlkfhjqwl;kehf;oqiwejf"
//						+ "o;iwqjefo;iqwhe;foihqw;jklfhsl"
//						+ ";djkhf;ldfasfweihp;ownvo;inw;o"
//						+ "wekj<end>";
//				
//		for(int i = 0 ; i < 1000 ; i++){
//			fw.println(i+"--"+text);
//		}
//		fw.close();
//		return file;
		return new File("test.avi");
	}
	public File splitFile(File source) throws IOException{
		// relate in stream with file to read into memory
		FileInputStream in = new FileInputStream(source);
		// create buffer
		byte[] buffer = new byte[BUFFER_SIZE];
		// create destination directory
		File dir = new File("partfiles");
		if(!dir.exists()){
			dir.mkdir();
		}
		// write
		FileOutputStream out = null;
		int len = 0;
		int count = 0;
			// generate output stream in loop
		while((len = in.read(buffer))!=-1){
			out = new FileOutputStream(new File(dir,(count++)+".part"));
			out.write(buffer,0, len);
			out.close();
		}
		
		// create properties to describe how to merge the fragments
		Properties prop = new Properties();
		// set the properties
		prop.setProperty("partcount",count+"");
		prop.setProperty("filename",source.getName());
		
		out = new FileOutputStream(new File(dir, count+".properties"));
		prop.store(out,"source file information");
		// close
		in.close();
		out.close();
		return dir;
	}
	
	public File mergeFiles(File dir) throws IOException{
		// judge if the file is a directory
		if(!dir.exists()){
			throw new RuntimeException("error, don't have the dir to merge files");
		}
		if(!dir.isDirectory()){
			throw new RuntimeException("error, the dir is not a directory.");
		}
		// get properties file
		File[] files = dir.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".properties");
			}
		});
			// ensure there is only one properties file
		if(files.length != 1){
			throw new RuntimeException("properties files are more than one.");
		}
		Properties prop = new Properties();
		prop.load(new FileInputStream(files[0]));
		// get the properties 
		int count = Integer.parseInt(prop.getProperty("partcount"));
		String fileName = prop.getProperty("filename");
		
		File outFile = new File(dir, fileName);
			// ensure the count number is equal to the total number of .part files
		files = dir.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name){
				return name.endsWith(".part");
			}
		});
		if(files.length != count){
			throw new RuntimeException("fragments are not complete.");
		}
		// add files into list
		ArrayList<InputStream> inputs = new ArrayList<>();
			// generate input stream in loop
		for(int i = 0 ; i < count ; i++){
			// if we use files[i], the order of the fragments will be messed up
			inputs.add(new FileInputStream(files[i]));
		}
		// merge into one sequence input stream
		SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(inputs));
		// write into one file 
		FileOutputStream fos = new FileOutputStream(outFile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = sis.read(buffer))!=-1){
			fos.write(buffer,0,len);
		}
		// close streams
		fos.close();
		sis.close();
		return outFile;
	}
}
