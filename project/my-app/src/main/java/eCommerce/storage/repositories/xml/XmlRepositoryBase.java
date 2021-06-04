package eCommerce.storage.repositories.xml;

import eCommerce.Constants;
import eCommerce.storage.DOMHelper;
import eCommerce.storage.repositories.interfaces.IRepository;
import eCommerce.models.base.EntityBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class XmlRepositoryBase<T extends EntityBase> implements IRepository<T> {

    protected abstract T insertNode(T entity);
    protected abstract T updateNode(T entity);
    protected abstract String getParentTagName();
    protected abstract String getTagName();
    protected abstract T MapXMLElementToEntity(Element element);

    protected Document document;
    protected Element rootNode;
    protected Element dbSetNode;

    protected XmlRepositoryBase()
    {
        reload();
    }

    private void reload() {
        document =  DOMHelper.getDocument();
        rootNode = (Element) document.getElementsByTagName(Constants.XML.ROOT_NODE).item(0);
        dbSetNode = (Element) rootNode.getElementsByTagName(getParentTagName()).item(0);
    }


    @Override
    public T insert(T entity) {
        reload();
        return insertNode(entity);
    }

    @Override
    public T update(T entity) {
        reload();
        return updateNode(entity);
    }

    @Override
    public void delete(T entity) {
        reload();
        try {
            NodeList nl = dbSetNode.getChildNodes();
            for(int i = 0; i < nl.getLength(); i++) {
                if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element item = (Element) nl.item(i);
                    if (item.getElementsByTagName("id").item(0).getTextContent().equals(entity.getId().toString())) {
                        item.getParentNode().removeChild(item);
                    }
                }
            }

            DOMHelper.saveXMLContent(document);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void AppendStringChild(String tag, String value, Element parentNode) {
        Element element = document.createElement(tag);
        element.appendChild(document.createTextNode(value));
        parentNode.appendChild(element);
    }

    @Override
    public ArrayList<T> getList() {
        reload();
        NodeList nl = dbSetNode.getChildNodes();

        ArrayList<T> list = new ArrayList<T>();
        for(int i = 0; i < nl.getLength(); i++) {
            if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element item = (Element) nl.item(i);
                list.add(MapXMLElementToEntity(item));
            }
        }

        return list;
    }

    @Override
    public Optional<T> getById(UUID id) {
        return getByParameter("id", id.toString());
    }

    protected Optional<T> getByParameter(String key, String value) {
        reload();
        Optional<T> targetEntity = Optional.empty();
        Element item = getElementByParameter(key, value);
        if(item != null) {
            targetEntity = Optional.of(MapXMLElementToEntity(item));
        }

        return targetEntity;
    }

    protected Element getElementByParameter(String key, String value) {
        reload();
        NodeList nl = dbSetNode.getChildNodes();;
        for(int i = 0; i < nl.getLength(); i++) {
            if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element item = (Element) nl.item(i);
                if (item.getElementsByTagName(key).item(0).getTextContent().equals(value)) {
                    return item;
                }
            }
        }

        return null;
    }

    protected List<T> getListByParameter(String key, String value) {
        reload();
        List<T> resultList = new ArrayList<T>();

        NodeList nl = dbSetNode.getChildNodes();
        for(int i = 0; i < nl.getLength(); i++) {
            if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element item = (Element) nl.item(i);
                if (item.getElementsByTagName(key).item(0).getTextContent().equals(value)) {
                    resultList.add(MapXMLElementToEntity(item));
                }
            }
        }

        return resultList;
    }

    // TODO: Code reuse
    protected List<T> getListByTwoParameters(String key, String value, String secondKey, String secondValue) {
        reload();
        List<T> resultList = new ArrayList<T>();

        NodeList nl = dbSetNode.getChildNodes();;
        for(int i = 0; i < nl.getLength(); i++) {
            if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element item = (Element) nl.item(i);
                if (item.getElementsByTagName(key).item(0).getTextContent().equals(value) &&
                        item.getElementsByTagName(secondKey).item(0).getTextContent().equals(secondValue)) {
                    resultList.add(MapXMLElementToEntity(item));
                }
            }
        }

        return resultList;
    }

    // TODO: Code reuse
    protected Optional<T> getByTwoParameters(String key, String value, String secondKey, String secondValue) {
        reload();
        Optional<T> targetEntity = Optional.empty();

        NodeList nl = dbSetNode.getChildNodes();
        for(int i = 0; i < nl.getLength(); i++) {
            if(nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element item = (Element) nl.item(i);
                if (item.getElementsByTagName(key).item(0).getTextContent().equals(value) &&
                        item.getElementsByTagName(secondKey).item(0).getTextContent().equals(secondValue)) {
                    targetEntity = Optional.of(MapXMLElementToEntity(item));
                    break;
                }
            }
        }

        return targetEntity;
    }
}
