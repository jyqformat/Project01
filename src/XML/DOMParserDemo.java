package XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;

public class DOMParserDemo {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            InputStream in = new FileInputStream("./doc/demo.xml");
            Document document = builder.parse(in);
            Element element = document.getDocumentElement();
            NodeList bookNodes = element.getChildNodes();
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node node = bookNodes.item(i);
                NodeList nodeList = node.getChildNodes();
                for (int j = 0; j < nodeList.getLength(); i++) {
                    Node n = nodeList.item(j);
                    System.out.println(n.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
