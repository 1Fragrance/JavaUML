package eCommerce.storage.repositories.xml;

import eCommerce.models.Bill;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IBillRepository;
import org.w3c.dom.Element;

import java.util.Date;
import java.util.UUID;

public class BillRepository extends XmlRepositoryBase<Bill> implements IBillRepository {

    @Override
    protected Bill MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String issueDate = element.getElementsByTagName("issueDate").item(0).getTextContent();
        String sum = element.getElementsByTagName("sum").item(0).getTextContent();
        String orderId = element.getElementsByTagName("orderId").item(0).getTextContent();

        return new Bill(UUID.fromString(id), new Date(issueDate), Double.parseDouble(sum), UUID.fromString(orderId));
    }

    @Override
    protected Bill insertNode(Bill entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("issueDate", entity.getIssueDate().toString(), element);
        AppendStringChild("sum", String.valueOf(entity.getSum()), element);
        AppendStringChild("orderId", entity.getOrderId().toString(), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected Bill updateNode(Bill entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("issueDate").item(0).setTextContent(entity.getIssueDate().toString());
            updateElement.getElementsByTagName("sum").item(0).setTextContent(String.valueOf(entity.getSum()));
            updateElement.getElementsByTagName("orderId").item(0).setTextContent(entity.getOrderId().toString());

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "bills";
    }

    @Override
    protected String getTagName() {
        return "bill";
    }
}
