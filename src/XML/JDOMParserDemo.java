package XML;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.util.List;

public class JDOMParserDemo {
    public static void main(String[] args) {
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc = saxBuilder.build("./doc/demo.xml");
            Element root = doc.getRootElement();
            List<Element> elements = root.getChildren();
            for (int i = 0; i < elements.size(); i++) {
                Element childrenRoot = elements.get(i);
                List<Element> javas = childrenRoot.getChildren();
                for (int j = 0; j < javas.size(); j++) {
                    Element element = javas.get(j);
                    System.out.println(element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
