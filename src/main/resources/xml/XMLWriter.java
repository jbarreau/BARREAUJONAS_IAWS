package resources.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import resources.user.User;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 03/04/13
 * Time: 01:00
 */
public class XMLWriter {
    private static String XMLUSERRESPONSEPATH = "resources/user/response/";

    public Element createEnregistrementResponse(String ko, Integer errorCode, String descrError) {
        try {
            File xml;
            if (errorCode == null && descrError == null && ko.toLowerCase().equals("ok")) {
                xml = new File(XMLUSERRESPONSEPATH + "enregistrementResponseOK.xml");
            } else {
                xml = new File(XMLUSERRESPONSEPATH + "enregistrementResponse" + errorCode + ".xml");
            }
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();

            return docBuilder.parse(xml).getDocumentElement();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }

    public Element createLocalisationResponse(List<User> users) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Document document = builder.newDocument();

        // Set DOM properties.
        document.setXmlStandalone(true);

        // Create the DOM tree diagram.
        Element root = document.createElement("LocalisationResponse");
        document.appendChild(root);
        root.setAttribute("xmlns", "http://barreaujonas.com/iaws/");
        root.setAttribute("xmlns:xs", "http://barreaujonas.com/iaws/schemas/");
        root.setAttribute("xs:schemaLocation", " http://barreaujonas.com/iaws/schemas/localisation.xsd");

        // Create the main tag.
        for (User u : users) {
            Element userElement = createUser(u, document);
            document.appendChild(userElement);
        }

        return document.getDocumentElement();
    }

    private Element createUser(User user, Document doc) {
        Element root = doc.createElement("utilisateur");

        Element id = doc.createElement("id_user");
        id.setTextContent(user.get_id().toString());
        root.appendChild(id);

        Element nom = doc.createElement("nom");
        nom.setTextContent(user.getNom());
        root.appendChild(nom);

        Element prenom = doc.createElement("prenom");
        prenom.setTextContent(user.getPrenom());
        root.appendChild(prenom);

        Element adresse = doc.createElement("adresse");
        adresse.setTextContent(user.getAdrPostale());
        root.appendChild(prenom);

        Element lat = doc.createElement("lat");
        lat.setTextContent("" + user.getLatitude());
        root.appendChild(lat);

        Element lon = doc.createElement("lon");
        lon.setTextContent("" + user.getLongitude());
        root.appendChild(lon);

        return root;
    }


}
