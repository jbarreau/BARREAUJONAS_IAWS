package osm;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OSMClient {
    private static final String OSMURL = "http://nominatim.openstreetmap.org/search?format=xml&q=";


    public static void main(String[] args) {
        OSMClient ret = search("68 rue leon bonnat");
    }

    /**
     * @param Padresse
     * @return false if pAdresse not found on OSM
     */
    public static OSMClient search(String Padresse) {
        //adresse = Padresse;
        String requeteOSM = OSMURL + Padresse;

        URL osm = null;
        try {
            osm = new URL(requeteOSM);
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();

            HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
            Document document = docBuilder.parse(connection.getInputStream());
            connection.disconnect();


            NodeList ele = document.getDocumentElement().getElementsByTagName("place");
            NamedNodeMap att = ele.item(0).getAttributes();
            float lat = Float.valueOf(att.getNamedItem("lat").getNodeValue());
            float lon = Float.valueOf(att.getNamedItem("lon").getNodeValue());
            System.out.println("lat : " + lat + ", lon :" + lon);

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        /*if (true)//OSM found adresse
              return new OSMClient(lat, lont, Padresse);
          else */
        return null;
    }
}
