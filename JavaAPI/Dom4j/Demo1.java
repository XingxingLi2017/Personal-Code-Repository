package dom4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


public class Demo1 {

	@Test
	public void read() throws DocumentException {
		// get reader
		SAXReader reader = new SAXReader();
		// read document
		Document document = reader.read(new File("src/dom4j/bookshelf.xml"));

		// get element
		Element root = document.getRootElement();
		Element book = root.elements("book").get(0);

		// access element
		System.out.println(book.element("name").getText());
		// access attribute
		System.out.println(book.element("name").attribute("I").getValue());
	}

	@Test
	public void addElement() throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("src/dom4j/bookshelf.xml"));
		Element root = doc.getRootElement();

		// add element in memeory
		Element book = root.element("book");
		book.addElement("price").setText("23.00");


		// write back into file
		// set encoding method and output format
		OutputFormat format = new OutputFormat().createPrettyPrint();
		format.setEncoding("gb2312");

//		XMLWriter writer = new XMLWriter
//				(new OutputStreamWriter		// we can specify charset
//						(new FileOutputStream("src/dom4j/bookshelf.xml"), "UTF-8"));
		XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream("src/dom4j/book.xml")), format);
		writer.write(doc);
		writer.close();
	}

	@Test
	public void add2() throws Exception {
		// add element at specified position

		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/dom4j/bookshelf.xml"));

		Element book = document.getRootElement().element("book");
		Element price = DocumentHelper.createElement("price");
		price.setText("39.00");

		// attach element to specified parent
		List<Element> list = book.elements();
		list.add(2, price);

		// write back to file
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileWriter("src/dom4j/bookshelf.xml"),format);
		writer.write(document);
		writer.close();
	}

	@Test
	public void delete() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("src/dom4j/bookshelf.xml"));

		// delete prices to only one price left
		List<Element> prices= document.getRootElement().element("book").elements("price");
		for (int i = 0 ; i < prices.size() ; i++) {
			if (i < prices.size() - 1) {
				Element curr = prices.get(i);
				// delete current node
				curr.getParent().remove(curr);
			}
		}
		// write back to file
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileWriter("src/dom4j/bookshelf.xml"), format);
		writer.write(document);
		writer.close();
	}

	@Test
	public void update() throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("src/dom4j/bookshelf.xml"));
		// update the 2nd book's name
		Element book = doc.getRootElement().elements("book").get(1);
		book.element("name").setText("new author2");
		// write back to file
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter writer = new XMLWriter(new FileWriter("src/dom4j/bookshelf.xml"), format);
		writer.write(doc);
		writer.close();
	}

	@Test
	public void select() throws Exception {
		// using xPath to extract target element

		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("src/dom4j/bookshelf.xml"));

		String value = doc.selectSingleNode	("//name").getText();
		System.out.println(value);
		System.out.println(doc.selectNodes("//name[@I='li' and @id='1']").get(0).getText());
	}

	@Test
	public void selectUser() throws Exception {
		// find target user
		String user = "aaa";
		String password = "123";
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("src/dom4j/user.xml"));
		Node node = doc.selectSingleNode("//user[@username='"+user+"' and @password = '"+password+"']");
		if (node != null) {
			System.out.println("find the user "+user+".");
		}
	}

}
