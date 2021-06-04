package eCommerce.storage.repositories.xml;

import eCommerce.models.Item;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IItemRepository;
import org.w3c.dom.Element;

import java.util.List;
import java.util.UUID;

public class ItemRepository extends XmlRepositoryBase<Item> implements IItemRepository {
    @Override
    public List<Item> getByCartId(UUID cartId) {
        return getListByParameter("cartId", cartId.toString());
    }

    @Override
    public List<Item> getByOrderId(UUID orderId) {
        return getListByParameter("orderId", orderId.toString());
    }

    @Override
    protected Item updateNode(Item entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("quantity").item(0).setTextContent(String.valueOf(entity.getQuantity()));
            if(entity.getOrderId() != null) {
                updateElement.getElementsByTagName("orderId").item(0).setTextContent(entity.getOrderId().toString());
            } else {
                updateElement.getElementsByTagName("orderId").item(0).setTextContent("");
            }
            if(entity.getCartId() != null) {
                updateElement.getElementsByTagName("cartId").item(0).setTextContent(entity.getCartId().toString());
            } else {
                updateElement.getElementsByTagName("cartId").item(0).setTextContent("");
            }

            updateElement.getElementsByTagName("productId").item(0).setTextContent(entity.getProductId().toString());

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "items";
    }

    @Override
    protected String getTagName() {
        return "item";
    }

    @Override
    protected Item MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String quantity = element.getElementsByTagName("quantity").item(0).getTextContent();

        UUID orderId = null;
        if(element.getElementsByTagName("orderId").item(0).getChildNodes().getLength() == 0) {
            orderId = UUID.fromString(element.getElementsByTagName("orderId").item(0).getTextContent());
        }

        UUID cartId = null;
        if(element.getElementsByTagName("cartId").item(0).getChildNodes().getLength() == 0) {
            cartId = UUID.fromString(element.getElementsByTagName("cartId").item(0).getTextContent());
        }

        String productId = element.getElementsByTagName("productId").item(0).getTextContent();

        return new Item(UUID.fromString(id), Integer.parseInt(quantity), UUID.fromString(productId), cartId, orderId);
    }

    @Override
    protected Item insertNode(Item entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("quantity", String.valueOf(entity.getQuantity()), element);
        AppendStringChild("productId", entity.getProductId().toString(), element);

        if(entity.getCartId() != null) {
            AppendStringChild("cartId", entity.getCartId().toString(), element);
        } else {
            AppendStringChild("cartId", null, element);
        }

        if(entity.getOrderId() != null) {
            AppendStringChild("orderId", entity.getOrderId().toString(), element);
        } else {
            AppendStringChild("orderId", null, element);
        }

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }
}
