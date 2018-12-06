package Class;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.Iterator;
public class DOM4JUtil {
    public static String getTableNameByEntity(Class obj) throws Exception {
        SAXReader reader = new SAXReader();
        String entityName = obj.getName();
        String Path = getPath(entityName);
        Document node = reader.read(Path);
        Element e = node.getRootElement();
        if (e.getName().equals("class")) {
            for (Iterator<Attribute> it = e.attributeIterator(); it.hasNext();) {
                Attribute att = it.next();
                if (att.getName().equals("name")) {
                    if (entityName.equals(att.getValue())) {
                        Iterator<Element> itElement = e.elementIterator();
                        while (itElement.hasNext()) {
                            Node node2 = itElement.next();
                            if (node2.getName().equals("table")) {
                                return node2.getText();
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String getColumnNameByFiled(Class obj, Field filedObj)
            throws Exception {
        String entityName = obj.getName();
        String filed = filedObj.getName();
        SAXReader reader = new SAXReader();
        String Path = getPath(entityName);
        Document node = reader.read(Path);
        Element e = ((Document) node).getRootElement();
        if (e.getName().equals("class")) {
            for (Iterator<Attribute> it = e.attributeIterator(); it.hasNext();) {
                Attribute att = it.next();
                if (att.getName().equals("name")) {
                    if (entityName.equals(att.getValue())) {
                        Iterator<Element> itElement = e.elementIterator();
                        while (itElement.hasNext()) {
                            Node node2 = itElement.next();
                            if (node2.getName().equals("filed")) {
                                e = (Element) node2;
                                for (Iterator<Attribute> itFiled = e
                                        .attributeIterator(); itFiled.hasNext();) {
                                    Attribute attFiled = itFiled.next();
                                    if (attFiled.getName().equals("name")) {
                                        if (attFiled.getValue().equals(filed)) {
                                            e = (Element) node2;
                                            Iterator<Element> it3 = e
                                                    .elementIterator();
                                            while (it3.hasNext()) {
                                                Node n3 = it3.next();
                                                if (n3.getName().equals(
                                                        "column")) {
                                                    return n3.getText();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }


    public static String getColumnTypeByFiled(Class classObj, Field fieldObj)
            throws Exception {
        return null;
    }

    public static String getPath(String entityName) throws URISyntaxException {
        String[] filenames = entityName.split("\\.");
        String filePath = "";
        for (int i = 0; i < filenames.length; i++) {
            filePath += filenames[i] + "/";
        }
        filePath = filePath.substring(0, filePath.length() - 1) + ".xml";
        ClassLoader cl = DOM4JUtil.class.getClassLoader();
        String path = cl.getResource(filePath).toURI().getPath();
        return path;
    }
}
