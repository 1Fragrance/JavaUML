package eCommerce.storage.repositories.xml;

import eCommerce.enums.OrderStatus;
import eCommerce.models.*;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IOrderRepository;
import org.w3c.dom.Element;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderRepository extends XmlRepositoryBase<Order> implements IOrderRepository {
    @Override
    public List<Order> getListByAuthorId(UUID authorId) {
        return getListByParameter("authorId", authorId.toString());
    }

    @Override
    public List<Order> getListByResponsibleAdminIdAndStatus(UUID adminId, OrderStatus status) {
        return getListByTwoParameters("adminId", adminId.toString(), "status", Integer.toString(status.ordinal()));
    }

    @Override
    protected Order insertNode(Order entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("orderDate", entity.getOrderDate().toString(), element);
        AppendStringChild("sum", String.valueOf(entity.getSum()), element);

        if(entity.getBillId() != null) {
            AppendStringChild("billId", entity.getBillId().toString(), element);
        } else {
            AppendStringChild("billId", null, element);
        }
        AppendStringChild("responsibleAdminId", entity.getResponsibleAdminId().toString(), element);
        AppendStringChild("addressId", entity.getAddressId().toString(), element);
        AppendStringChild("authorId", entity.getAuthorId().toString(), element);
        AppendStringChild("orderStatus", String.valueOf(entity.getOrderStatus().ordinal()), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected Order updateNode(Order entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("orderDate").item(0).setTextContent(entity.getOrderDate().toString());
            updateElement.getElementsByTagName("sum").item(0).setTextContent(String.valueOf(entity.getSum()));
            if(entity.getBillId() != null) {
                updateElement.getElementsByTagName("billId").item(0).setTextContent(entity.getBillId().toString());
            } else {
                updateElement.getElementsByTagName("billId").item(0).setTextContent("");
            }
            updateElement.getElementsByTagName("responsibleAdminId").item(0).setTextContent(entity.getResponsibleAdminId().toString());
            updateElement.getElementsByTagName("addressId").item(0).setTextContent(entity.getAddressId().toString());
            updateElement.getElementsByTagName("authorId").item(0).setTextContent(entity.getAuthorId().toString());
            updateElement.getElementsByTagName("orderStatus").item(0).setTextContent(String.valueOf(entity.getOrderStatus().ordinal()));

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "orders";
    }

    @Override
    protected String getTagName() {
        return "order";
    }

    @Override
    protected Order MapXMLElementToEntity(Element element) {

        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String orderDate = element.getElementsByTagName("orderDate").item(0).getTextContent();
        String sum = element.getElementsByTagName("sum").item(0).getTextContent();
        UUID billId = null;
        if(element.getElementsByTagName("billId").item(0).getChildNodes().getLength() == 0)
        {
            billId = UUID.fromString(element.getElementsByTagName("billId").item(0).getTextContent());
        }
        String responsibleAdminId = element.getElementsByTagName("responsibleAdminId").item(0).getTextContent();
        String addressId = element.getElementsByTagName("addressId").item(0).getTextContent();
        String authorId = element.getElementsByTagName("authorId").item(0).getTextContent();
        String orderStatus = element.getElementsByTagName("orderStatus").item(0).getTextContent();

        int status = Integer.parseInt(orderStatus);

        return new Order(UUID.fromString(id),
                new Date(orderDate),
                Integer.parseInt(sum),
                billId,
                UUID.fromString(responsibleAdminId),
                UUID.fromString(addressId),
                OrderStatus.values()[status],
                UUID.fromString(authorId));
    }
}
