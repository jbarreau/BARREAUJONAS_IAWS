package resources.webService;

import org.w3c.dom.Element;
import resources.couchdb.CouchDB;
import resources.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 01/04/13
 * Time: 18:14
 */
public class LocalisationServiceImpl implements LocalisationService {
    public Element chercheVoisin(int userId, float rayonKm) {
        CouchDB db = new CouchDB();
        User centralUser = db.getUser(userId);
        if (centralUser == null) {
            //error
        }

        List<User> voisins = new ArrayList<User>();
        for (User u : db.getAllUsers()) {
            /*if (distance(centralUser,u) <= rayonKM))
                voisins.add(u);
            */
        }

        Element ret = null;
        /*
        XMLWriter writer = new XMLClientWriter();
        ret = writer.createLocalisationResponse(centralUser,rayonKM,voisins);
         */


        return ret;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
