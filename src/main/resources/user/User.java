package user;

import couchdb.couchDB;
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
        User u1 = new User("barreau", "julien", "julien.barreau@univ-tlse3.fr", "62 rue leon bonnat 31400");
        User u2 = new User("Durand", "remi", "remi.durand@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");
        u1.globalCheck();

        System.out.println(u2);
    }

    public User(String pnom, String pPrenom, String pAdrMail, String pAdrPostale) {
        nom = pnom;
        prenom = pPrenom;
        adrMail = pAdrMail;
        adrPostale = pAdrPostale;
    }

    public boolean globalCheck() {
        return mailCheck() && OSMFound() && alreadyExist();
    }

    public boolean alreadyExist() {
        couchDB db = new couchDB();
        for (User user : db.getAllUsers()) {
            if (this.adrMail.equals(user.adrMail))
                return false;
        }
        return true;
    }

    public boolean mailCheck() {
        return adrMail.endsWith("@univ-tlse3.fr");
    }

    public boolean OSMFound() {
        OSMClient osm = new OSMClient(adrPostale);
        try {
            if (osm.search()) {
                latitude = osm.getLat();
                longitude = osm.getLon();
                //adrPostale = osm.getDisplay_name();
            } else {
                return false; //Error 200
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return true;

    }


    @Override
    public String toString() {
        return nom + " " + prenom + "\n" + adrPostale + "\n" + adrMail + "\nlatitude\tlongitude\n" + latitude + "\t" + longitude;
    }

}
