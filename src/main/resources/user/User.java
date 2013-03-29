package user;

import osm.OSMClient;

import java.io.IOException;

public class User {
    private String nom;
    private String prenom;
    private String adrMail;
    private String adrPostale;
    private float latitude;
    private float longitude;


    public static void main(String[] args) {
        User u = new User("barreau", "julien", "onSenFou@univ-tlse3.fr", "11 chemin du canal 31400");
        u.globalCheck();

        System.out.println(u);
    }

    public User(String pnom, String pPrenom, String pAdrMail, String pAdrPostale) {
        nom = pnom;
        prenom = pPrenom;
        adrMail = pAdrMail;
        adrPostale = pAdrPostale;
    }

    public void globalCheck() {
        mailCheck();

        OSMFound();
    }

    public void mailCheck() {

        if (!adrMail.endsWith("@univ-tlse3.fr")) {
            ;//error 110
        }
    }

    public void OSMFound() {
        OSMClient osm = new OSMClient(adrPostale);
        try {
            if (osm.search()) {
                latitude = osm.getLat();
                longitude = osm.getLon();
                //adrPostale = osm.getDisplay_name();
            } /*else {
                return error 200
            }*/
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    @Override
    public String toString() {
        return nom + " " + prenom + "\n" + adrPostale + "\n" + adrMail + "\nlatitude\tlongitude\n" + latitude + "\t" + longitude;
    }

}
