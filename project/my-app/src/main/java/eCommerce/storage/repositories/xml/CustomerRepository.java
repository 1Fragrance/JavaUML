package eCommerce.storage.repositories.xml;

import eCommerce.enums.CustomerStatus;
import eCommerce.models.Customer;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.ICustomerRepository;
import org.w3c.dom.Element;

import java.util.Optional;
import java.util.UUID;

public class CustomerRepository extends XmlRepositoryBase<Customer> implements ICustomerRepository {
    @Override
    public Optional<Customer> getByLoginAndPassword(String login, String password) {
        return getByTwoParameters("login", login, "password", password);
    }

    @Override
    public boolean isLoginExist(String login) {
        return getByParameter("login", login).isPresent();
    }

    @Override
    protected Customer MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String login = element.getElementsByTagName("login").item(0).getTextContent();
        String password = element.getElementsByTagName("password").item(0).getTextContent();
        String phone = element.getElementsByTagName("phone").item(0).getTextContent();
        String customerStatus = element.getElementsByTagName("customerStatus").item(0).getTextContent();

        int intStatus = Integer.parseInt(customerStatus);
        return new Customer(UUID.fromString(id), firstName, lastName, email, login, password, phone, CustomerStatus.values()[intStatus]);
    }

    @Override
    protected Customer insertNode(Customer entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("firstName", entity.getFirstName(), element);
        AppendStringChild("lastName", entity.getLastName(), element);
        AppendStringChild("email", entity.getEmail(), element);
        AppendStringChild("login", entity.getLogin(), element);
        AppendStringChild("password", entity.getPassword(), element);
        AppendStringChild("phone", entity.getPhone(), element);
        AppendStringChild("customerStatus", String.valueOf(entity.getCustomerStatus().ordinal()), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected Customer updateNode(Customer entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("firstName").item(0).setTextContent(entity.getFirstName());
            updateElement.getElementsByTagName("lastName").item(0).setTextContent(entity.getLastName());
            updateElement.getElementsByTagName("email").item(0).setTextContent(entity.getEmail());
            updateElement.getElementsByTagName("login").item(0).setTextContent(entity.getLogin());
            updateElement.getElementsByTagName("password").item(0).setTextContent(entity.getPassword());
            updateElement.getElementsByTagName("phone").item(0).setTextContent(entity.getPhone());
            updateElement.getElementsByTagName("customerStatus").item(0).setTextContent(String.valueOf(entity.getCustomerStatus().ordinal()));

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "customers";
    }

    @Override
    protected String getTagName() {
        return "customer";
    }
}
