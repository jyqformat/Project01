package XML;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class DMOM4JParserDemo {
    public static void main(String[] args) {
        readText();
    }

    public static void readText() {
        SAXReader sax = new SAXReader();
        File file = new File("./doc/demo.xml");
        Document doc = null;
        try {
            doc = sax.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element root = doc.getRootElement();
        List javas = root.elements("Java");
        Iterator it = javas.iterator();
        while (((Iterator) it).hasNext()) {
            Element e = (Element) ((Iterator) it).next();
            System.out.println("书名" + e.elementText("书名"));
            System.out.println("价格" + e.elementText("价格"));
            System.out.println("描述" + e.elementText("描述"));
            List attrList = e.attributes();
            Iterator attrIt = attrList.iterator();
        }
    }
}
