package eCommerce.storage.repositories.xml;

import eCommerce.models.Admin;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IAdminRepository;
import org.w3c.dom.Element;

import java.util.Optional;
import java.util.UUID;

public class AdminRepository extends XmlRepositoryBase<Admin> implements IAdminRepository {
    @Override
    public Optional<Admin> getByLoginAndPassword(String login, String password) {
        return getByTwoParameters("login", login, "password", password);
    }

    @Override
    protected Admin MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
        String email = element.getElementsByTagName("email").item(0).getTextContent();
        String login = element.getElementsByTagName("login").item(0).getTextContent();
        String password = element.getElementsByTagName("password").item(0).getTextContent();
        String phone = element.getElementsByTagName("phone").item(0).getTextContent();

        return new Admin(UUID.fromString(id), firstName, lastName, email, login, password, phone);
    }

    @Override
    protected Admin insertNode(Admin entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("firstName", entity.getFirstName(), element);
        AppendStringChild("lastName", entity.getLastName(), element);
        AppendStringChild("email", entity.getEmail(), element);
        AppendStringChild("login", entity.getLogin(), element);
        AppendStringChild("password", entity.getPassword(), element);
        AppendStringChild("phone", entity.getPhone(), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected Admin updateNode(Admin entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("firstName").item(0).setTextContent(entity.getFirstName());
            updateElement.getElementsByTagName("lastName").item(0).setTextContent(entity.getLastName());
            updateElement.getElementsByTagName("email").item(0).setTextContent(entity.getEmail());
            updateElement.getElementsByTagName("login").item(0).setTextContent(entity.getLogin());
            updateElement.getElementsByTagName("password").item(0).setTextContent(entity.getPassword());
            updateElement.getElementsByTagName("phone").item(0).setTextContent(entity.getPhone());

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }

    @Override
    protected String getParentTagName() {
        return "admins";
    }

    @Override
    protected String getTagName() {
        return "admin";
    }
}
