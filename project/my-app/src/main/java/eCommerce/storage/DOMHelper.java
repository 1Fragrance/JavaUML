package eCommerce.storage;

import javax.xml.parsers.*;

import eCommerce.Constants;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class DOMHelper {

    public static Document getDocument() {
        Document d = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            d = db.parse(Constants.XML.XML_DB_PATH);
        } catch (Exception ex) {
            d = null;
        }
        return d;
    }

    public static void saveXMLContent(Document d) {
        try {
            File file=  new File(Constants.XML.XML_DB_PATH);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(d);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
