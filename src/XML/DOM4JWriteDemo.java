package XML;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


import java.io.FileWriter;
import java.io.IOException;

public class DOM4JWriteDemo {
    public static void main(String[] args) {
        String[][] books = {
                {"1001", "武侠", "天龙八部", "金庸", "60", "1965", "zh"},
                {"1002", "武侠", "西游记", "吴承恩", "60", "明朝中期", "zh"},
        };
        Document document = DocumentHelper.createDocument();
        Element rootElmt = document.addElement("book");
        for (String[] book : books) {
            Element bookElmt = rootElmt.addElement("book");
            Element titleElmt = bookElmt.addElement("title");
            Element authorElmt = bookElmt.addElement("author");
            Element priceElmt = bookElmt.addElement("price");
            Element yearElmt = bookElmt.addElement("year");
            titleElmt.setText(book[2]);
            authorElmt.setText(book[3]);
            priceElmt.setText(book[4]);
            yearElmt.setText(book[5]);
            bookElmt.addAttribute("ISDN", book[0]);
            bookElmt.addAttribute("catalog", book[1]);
            titleElmt.addAttribute("lang", book[6]);
        }
        try {
            FileWriter fw = new FileWriter("mybooks.xml");
            OutputFormat format = OutputFormat.createCompactFormat();
            format.setEncoding("gb2312");
            XMLWriter xmlWriter = new XMLWriter(fw, format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
