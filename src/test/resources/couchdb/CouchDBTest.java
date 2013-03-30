package resources.couchdb;

import junit.framework.TestCase;
import org.lightcouch.Response;
import resources.user.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 30/03/13
 * Time: 15:10
 */

public class CouchDBTest extends TestCase {
    private CouchDB db = new CouchDB();

    private User u1 = new User("barreau", "julien", "julien.barreau@univ-tlse3.fr", "62 rue leon bonnat 31400");
    private User u2 = new User("Durand", "remi", "remi.durand@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");
    private User uError = new User("Durand", "remi", "remi.dur456@univ-tlse3.fr@univ-tlse3.fr", "qsdcanal 31400");

    public void testAddUser() throws Exception {
        try {
            Response res = db.addUser(u1);
            assertTrue(res != null);
            db.remove(res.getId(), res.getRev());

            res = db.addUser(u2);
            assertTrue(res != null);
            db.remove(res.getId(), res.getRev());


            res = db.addUser(uError);
            assertTrue(res == null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            fail("unwanted exception");
        }
    }

    public void testGetAllUsers() throws Exception {
        db.addUser(u1);
        db.addUser(u2);

        List<User> users = db.getAllUsers();
        assertTrue(users.size() == 2);
        assertTrue(users.contains(u1));
        assertTrue(users.contains(u2));

        db.remove(u1);
        db.remove(u2);
        assertTrue(users.size() == 0);

    }

    public void testRemove() throws Exception {
        // test remove(String id, String rev);
        Response res1 = db.addUser(u1);
        Response res2 = db.addUser(u2);
        List<User> users = db.getAllUsers();
        assertTrue(users.size() == 2);
        db.remove(res1.getId(), res1.getRev());
        db.remove(res2.getId(), res2.getRev());

        try {
            users = db.getAllUsers();

        } catch (NullPointerException e) {
            System.out.println("in catch");
        }
        assertTrue(users.size() == 0);

        //test remove(object)
        db.addUser(u1);
        db.addUser(u2);
        users = db.getAllUsers();
        assertTrue(users.size() == 2);

        db.remove(u1);
        db.remove(u2);
        users = db.getAllUsers();
        assertTrue(users.size() == 0);

    }

}
