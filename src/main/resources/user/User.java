package resources.user;


import resources.couchdb.CouchDB;
import resources.osm.OSMClient;

import java.io.IOException;

public class User {
    private String _id = null;
    private String _rev = null;
    private String nom;
    private String prenom;
    private String adrMail;
    private String adrPostale;
    private float latitude = 0;
    private float longitude = 0;


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
        CouchDB db = new CouchDB();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        //if (Float.compare(user.latitude, latitude) != 0) return false;
        //if (Float.compare(user.longitude, longitude) != 0) return false;
        //if (_id != null ? !_id.equals(user._id) : user._id != null) return false;
        if (_id != null && user._id != null) if (!_id.equals(user._id)) return false;
        //if (_rev != null ? !_rev.equals(user._rev) : user._rev != null) return false;
        if (_rev != null && user._rev != null) if (!_rev.equals(user._rev)) return false;
        if (!adrMail.equals(user.adrMail)) return false;
        if (!adrPostale.equals(user.adrPostale)) return false;
        if (!nom.equals(user.nom)) return false;
        if (!prenom.equals(user.prenom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (_rev != null ? _rev.hashCode() : 0);
        result = 31 * result + nom.hashCode();
        result = 31 * result + prenom.hashCode();
        result = 31 * result + adrMail.hashCode();
        result = 31 * result + adrPostale.hashCode();
        //result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        //result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        return result;
    }

    public Integer get_id() {
        if (_id == null) return null;
        else return Integer.decode(_id);
    }

    public Integer get_rev() {
        if (_rev == null) return null;
        else return Integer.decode(_rev);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdrMail() {
        return adrMail;
    }

    public String getAdrPostale() {
        return adrPostale;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdrMail(String adrMail) {
        this.adrMail = adrMail;
    }

    public void setAdrPostale(String adrPostale) {
        this.adrPostale = adrPostale;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setIdRev(String id, String rev) {
        _id = id;
        _rev = rev;
    }
}
