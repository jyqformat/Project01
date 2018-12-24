package XML;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XPathDemo1 {
    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File("mybook.xml"));
            Node root = document.selectSingleNode("books");
            List<Node> list = root.selectNodes("book/title[@lang='zh']");
            for (Node e : list) {
                System.out.println(e.getStringValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
