package eCommerce.storage.repositories.xml;

import eCommerce.models.ShoppingCart;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IShoppingCartRepository;
import org.w3c.dom.Element;

import java.util.Optional;
import java.util.UUID;

public class ShoppingCartRepository extends XmlRepositoryBase<ShoppingCart> implements IShoppingCartRepository {
    @Override
    public Optional<ShoppingCart> getByCustomerId(UUID customerId) {
        return getByParameter("customerId", customerId.toString());
    }

    @Override
    protected ShoppingCart insertNode(ShoppingCart entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("customerId", entity.getCustomerId().toString(), element);
        AppendStringChild("hasItems", String.valueOf(entity.getHasItems()), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected ShoppingCart updateNode(ShoppingCart entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("customerId").item(0).setTextContent(entity.getCustomerId().toString());
            updateElement.getElementsByTagName("hasItems").item(0).setTextContent(String.valueOf(entity.getHasItems()));

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "shoppingCarts";
    }

    @Override
    protected String getTagName() {
        return "shoppingCart";
    }

    @Override
    protected ShoppingCart MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String customerId = element.getElementsByTagName("customerId").item(0).getTextContent();
        String hasItems = element.getElementsByTagName("hasItems").item(0).getTextContent();

        return new ShoppingCart(UUID.fromString(id), UUID.fromString(customerId), Boolean.parseBoolean(hasItems));
    }
}
