package eCommerce.storage.repositories.xml;

import eCommerce.models.Address;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IAddressRepository;
import org.w3c.dom.Element;

import java.util.List;
import java.util.UUID;

public class AddressRepository extends XmlRepositoryBase<Address> implements IAddressRepository {
    @Override
    public List<Address> getListByUserId(UUID userId) {
        return getListByParameter("userId", userId.toString());
    }

    @Override
    protected String getParentTagName() {
        return "addresses";
    }

    @Override
    protected String getTagName() {
        return "addresse";
    }

    @Override
    protected Address MapXMLElementToEntity(Element element) {
        String id = element.getElementsByTagName("id").item(0).getTextContent();
        String userId = element.getElementsByTagName("userId").item(0).getTextContent();
        String apartmentNumber = element.getElementsByTagName("apartmentNumber").item(0).getTextContent();
        String city = element.getElementsByTagName("city").item(0).getTextContent();
        String country = element.getElementsByTagName("country").item(0).getTextContent();
        String houseNumber = element.getElementsByTagName("houseNumber").item(0).getTextContent();
        String street = element.getElementsByTagName("street").item(0).getTextContent();

        return new Address(UUID.fromString(id), UUID.fromString(userId), apartmentNumber, city, country, houseNumber, street);
    }

    @Override
    protected Address insertNode(Address entity) {
        Element element = document.createElement(getTagName());

        AppendStringChild("id", entity.getId().toString(), element);
        AppendStringChild("userId", entity.getUserId().toString(), element);
        AppendStringChild("apartmentNumber", entity.getApartmentNumber(), element);
        AppendStringChild("city", entity.getCity(), element);
        AppendStringChild("country", entity.getCountry(), element);
        AppendStringChild("houseNumber", entity.getHouseNumber(), element);
        AppendStringChild("street", entity.getStreet(), element);

        dbSetNode.appendChild(element);
        DOMHelper.saveXMLContent(document);

        return entity;
    }

    @Override
    protected Address updateNode(Address entity) {
        Element updateElement = getElementByParameter("id", entity.getId().toString());
        if(updateElement != null) {
            updateElement.getElementsByTagName("userId").item(0).setTextContent(entity.getUserId().toString());
            updateElement.getElementsByTagName("apartmentNumber").item(0).setTextContent(entity.getApartmentNumber());
            updateElement.getElementsByTagName("city").item(0).setTextContent(entity.getCity());
            updateElement.getElementsByTagName("country").item(0).setTextContent(entity.getCountry());
            updateElement.getElementsByTagName("houseNumber").item(0).setTextContent(entity.getHouseNumber());
            updateElement.getElementsByTagName("street").item(0).setTextContent(entity.getStreet());

            DOMHelper.saveXMLContent(document);
        }

        return entity;
    }
}
