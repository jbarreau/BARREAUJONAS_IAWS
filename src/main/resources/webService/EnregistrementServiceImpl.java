package resources.webService;

import org.lightcouch.Response;
import org.w3c.dom.Element;
import resources.couchdb.CouchDB;
import resources.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 01/04/13
 * Time: 18:14
 */
public class EnregistrementServiceImpl implements EnregistrementService {

    public Element enregistreUser(User user) {
        Element ret = null;
        //XMLWriter writer = new XMLClientWriter();
        if (!user.mailCheck()) {
            System.err.println("\t\t110 mail non @univ-tlse3");
            return null/*writer.createEnregistrementResponse("KO",110,"mail non @univ-tlse3")*/;
        }
        if (!user.alreadyExist()) {
            System.err.println("\t\t100 mail deja utilisé");
            return null/*writer.createEnregistrementResponse("KO",100,"mail deja utilise")*/;
        }
        if (!user.OSMFound()) {
            System.err.println("\t\t200 adresse postale non reconnu");
            return null/*writer.createEnregistrementResponse("KO",200,"t200 adresse postale non reconnu")*/;
        }
        CouchDB db = new CouchDB();
        Response res = db.addUser(user);
        if (res == null) {
            //error adding;
        }

        /*
        ret = writer.createEnregistrementResponse("OK",null,null);
         */

        return ret;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
