package resources.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

public class XMLReader {
    private float lat;
    private float lon;
    private String display_name;

    public XMLReader(InputStream in) throws Exception {
        try {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document document = docBuilder.parse(in);
            NodeList ele = document.getDocumentElement().getElementsByTagName("place");
            NamedNodeMap att = ele.item(0).getAttributes();
            this.lat = Float.parseFloat(att.getNamedItem("lat").getNodeValue());
            this.lon = Float.parseFloat(att.getNamedItem("lon").getNodeValue());
            this.display_name = att.getNamedItem("display_name").getNodeValue();
        } catch (ParserConfigurationException e) {
            //e.printStackTrace();
            throw (new Exception());
        } catch (SAXException e) {
            //e.printStackTrace();
            throw (new Exception());
        } catch (java.lang.NullPointerException e) {
            //e.printStackTrace();
            throw (new Exception());
        }
    }

    public float getLat() {
        return (this.lat);
    }

    public float getLon() {
        return (this.lon);
    }

    public String getAdr() {
        return (this.display_name);
    }
}