package resources.osm;

import resources.user.User;
import resources.xml.XMLReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class OSMClient {
    private static final String OSMURL = "http://nominatim.openstreetmap.org/search?format=xml&q=";
    private String display_name;
    private String adresse;
    private float lat;
    private float lon;

    public float getLat() {
        return lat;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public float getLon() {
        return lon;
    }

    public OSMClient(String Padresse) {
        adresse = Padresse;
        lat = 0;
        lon = 0;
    }

    /**
     * on suppose qu'une adresse ne retournera toujour qu'un resultat, non valide sinon
     *
     * @return false if Adresse not found on OSM
     */
    public boolean search() throws IOException {
        //adresse = Padresse;
        String requeteOSM = OSMURL + adresse.replaceAll(" ", "+");

        try {
            URL osm = new URL(requeteOSM);
            HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
            XMLReader xml = new XMLReader(connection.getInputStream());
            connection.disconnect();

            this.lat = xml.getLat();
            this.lon = xml.getLon();
            this.display_name = xml.getAdr();

        } catch (Exception e) {
            //e.printStackTrace();
            return false;//To change body of catch statement use File | Settings | File Templates.
        }
        return true;
    }

    public static float Distance(User u1, User u2) {
        double lat0 = u1.getLatitude();
        double lon0 = u1.getLongitude();
        double lat1 = u2.getLatitude();
        double lon1 = u2.getLongitude();
        int R = 6371; // km
        double dLat = Math.toRadians(lat1 - lat0);
        double dLon = Math.toRadians(lon1 - lon0);
        lat0 = Math.toRadians(lat0);
        lat1 = Math.toRadians(lat1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat0) * Math.cos(lat1);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return new Double(d).floatValue();
    }
}
