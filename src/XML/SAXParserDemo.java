package XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;

public class SAXParserDemo extends DefaultHandler {
    String targName;

    public void startDocment() throws SAXException {
        System.out.println("开始文档解析");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        targName = qName;
        System.out.println(targName + "");
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.println(qName);
    }

    public void endDocument() throws SAXException {
        System.out.println();
        System.out.println("文档解析结束");
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch, start, length) + "");
    }

    public static void main(String[] args) {
        SAXParser parser = null;
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            SAXParserDemo parserXml = new SAXParserDemo();
            InputStream stream = new FileInputStream("./doc/demo.xml");
            parser.parse(stream, parserXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

