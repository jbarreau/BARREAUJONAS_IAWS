package resources.couchdb;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;
import org.lightcouch.View;
import resources.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 29/03/13
 * Time: 19:23
 */
public class CouchDB {
    private static CouchDbProperties DB_PROPERTIES = new CouchDbProperties("barreaujonas", true, "http", "127.0.0.1", 5984, "dbreader", "dbreader");
    private CouchDbClient dbClient;

    public CouchDB() {
        dbClient = new CouchDbClient(DB_PROPERTIES);
    }

    public Response addUser(User u) {
        u.setIdRev(null, null);
        Response res = null;
        if (u.globalCheck()) {
            res = dbClient.save(u);
            u.setIdRev(res.getId(), res.getRev());
        } else {
            if (!u.mailCheck()) {
                System.err.println("\t\t110 mail non @univ-tlse3");
                return null;
            }
            if (!u.alreadyExist()) {
                System.err.println("\t\t100 mail deja utilisé");
                return null;
            }
            if (!u.OSMFound()) {
                System.err.println("\t\t200 adresse postale non connu");
                return null;
            }
        }
        return res;
    }

    public List<User> getAllUsers() {
        View users = dbClient.view("allUsers/allUsers").includeDocs(true);//barreaujonas/_design/allUsers/_view/allUsers

        return users.query(User.class);
    }

    public Response remove(User u) {
        return dbClient.remove(u);
    }

    public Response remove(String id, String rev) {
        return dbClient.remove(id, rev);
    }

    public static void main(String[] args) {
        CouchDB db = new CouchDB();

        User u1 = new User("barreau", "julien", "julien.barreau@univ-tlse3.fr", "62 rue leon bonnat 31400");
        User u2 = new User("Durand", "remi", "remi.durand@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");
        User uError = new User("Durand", "remi", "remi.dur456@univ-tlse3.fr@univ-tlse3.fr", "qsdcanal 31400");

        db.addUser(u1);
        db.addUser(u2);
        db.addUser(uError);

        for (User user : db.getAllUsers()) {
            System.out.println(user + "\n");
        }
    }
}
