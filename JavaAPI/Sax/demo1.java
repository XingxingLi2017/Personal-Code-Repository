package sax;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class demo1 {

	public static void main(String[] args) throws Exception{
		// 1. create factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2. get parser
		SAXParser parser = factory.newSAXParser();
		// 3. get reader
		XMLReader reader = parser.getXMLReader();
		// 4. setup reader
//		reader.setContentHandler(new ListHandler());
//		reader.setContentHandler(new TagValueHandler());
		BeanListHandler beans = new BeanListHandler();
		reader.setContentHandler(beans);
		// 5. get xml content
		reader.parse(new InputSource(new FileInputStream("src/sax/bookshelft.xml")));
		List list = beans.getList();
		System.out.println(list);
	}
}
// print all the tags
class ListHandler implements ContentHandler {

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		String attrs = " ";
		for (int i = 0 ; atts != null && i < atts.getLength() ; i++) {
			attrs += atts.getQName(i) + "=" + atts.getValue(i) + " "; 
		}
		System.out.println("<" + qName+attrs+">");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</"+qName+">");
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println(new String(ch, start, length));
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}
}
// print the specified elements
class TagValueHandler extends DefaultHandler {
	private String currElement = "";
	private int neededNumber = 2; // we want to get the 1st author
	private int currNumber = 0; // current number
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currElement = qName;
		if (currElement.equals("author")) currNumber++;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// prevent from printing #text blank text nodes
		currElement = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if ("author".equals(currElement) && currNumber == neededNumber) {
			System.out.println(new String(ch, start, length));
		}
	}
}

// encapsulate xml elements into object
class BeanListHandler extends DefaultHandler {
	private List list = new ArrayList<>();
	private String currTag;
	private Book book;
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currTag = qName;
		if (currTag.equals("book")) {
			book = new Book();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("book")) {
			list.add(book);
			book = null;
		}
		currTag = null; // to deal with "/n/t/t"
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currTag == null) return;    //to deal with "/n/t/t"
		if (currTag.equals("name") && book != null) {
			book.setName(new String(ch, start, length));
		} else if (currTag.equals("author") && book != null) {
			book.setAuthor(new String(ch, start, length));
		} else if (currTag.equals("price") && book != null) {
			book.setPrice(new String(ch, start, length));
		}
	}

	public List getList() {
		return list;
	}
	
	
}
