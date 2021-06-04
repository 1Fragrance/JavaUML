package eCommerce.storage;

import eCommerce.Constants;
import eCommerce.storage.repositories.xml.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class XmlDb extends DbBase {

    public XmlDb() {
        admins = new AdminRepository();
        addresses = new AddressRepository();
        bills = new BillRepository();
        customers = new CustomerRepository();
        items = new ItemRepository();
        orders = new OrderRepository();
        products = new ProductRepository();
        shoppingCards = new ShoppingCartRepository();
    }

    public void initialize() throws IOException, SAXException {
        try {
            validateXMLbyXSD();
        } catch (IOException e) {
            System.out.println("Ошибка: файл XML не найден");
            // e.printStackTrace();
            throw(e);
        } catch (SAXException e) {
            System.out.println("Ошибка: структура XML файла нарушена");
            // e.printStackTrace();
            throw(e);
        }
    }

    private boolean validateXMLbyXSD() throws IOException, SAXException {
        System.out.println("Проверка структуры XML...");

        File xmlFile = new File(Constants.XML.XML_DB_PATH);
        File xsdFile = new File(Constants.XML.XSD_DB_PATH);

        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(xsdFile).newValidator().validate(new StreamSource(xmlFile));

        System.out.println("Файл базы данных успешно прошел проверку");
        return true;
    }
}
