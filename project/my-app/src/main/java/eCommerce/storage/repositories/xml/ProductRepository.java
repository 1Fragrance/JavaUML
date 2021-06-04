package eCommerce.storage.repositories.xml;

import eCommerce.models.Product;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IProductRepository;
import org.w3c.dom.Element;

import java.util.UUID;

public class ProductRepository extends XmlRepositoryBase<Product> implements IProductRepository {
    @Override
    protected Product insertNode(Product entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("name", entity.getName(), element);
        AppendStringChild("description", entity.getDescription(), element);
        AppendStringChild("manufacturer", entity.getManufacturer(), element);
        AppendStringChild("price", String.valueOf(entity.getPrice()), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected Product updateNode(Product entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("name").item(0).setTextContent(entity.getName());
            updateElement.getElementsByTagName("description").item(0).setTextContent(entity.getDescription());
            updateElement.getElementsByTagName("manufacturer").item(0).setTextContent(entity.getManufacturer());
            updateElement.getElementsByTagName("price").item(0).setTextContent(String.valueOf(entity.getPrice()));

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "products";
    }

    @Override
    protected String getTagName() {
        return "product";
    }

    @Override
    protected Product MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        String manufacturer = element.getElementsByTagName("manufacturer").item(0).getTextContent();
        String price = element.getElementsByTagName("price").item(0).getTextContent();


        return new Product(UUID.fromString(id), name, description, manufacturer, Integer.parseInt(price));
    }
}
