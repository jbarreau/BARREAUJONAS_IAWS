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
        assert (u.globalCheck());
        //u.setIdRev(null, null);
        Response res = dbClient.save(u);
        u.setIdRev(res.getId(), res.getRev());
        return res;
    }

    public List<User> getAllUsers() {
        View users = dbClient.view("allUsers/allUsers").includeDocs(true);//barreaujonas/_design/allUsers/_view/allUsers

        return users.query(User.class);
    }

    public User getUser(int id) {
        List<User> lu = getAllUsers();
        for (User u : lu) {
            if (u.get_id() == id)
                return u;
        }
        return null;
    }

    public Response remove(User u) {
        return dbClient.remove(u);
    }

    public Response remove(Integer id, Integer rev) {
        return dbClient.remove(id.toString(), rev.toString());
    }

}
