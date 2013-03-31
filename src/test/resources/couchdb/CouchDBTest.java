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
    private User uErrorMailAlready = new User("Durand", "remi", "remi.dur456@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");
    private User uErrorAdr = new User("Durand", "remi", "remi.dur456sq@univ-tlse3.fr@univ-tlse3.fr", "qsdcanal 31400");
    private User uErrorMailInvalide = new User("Durand", "remi", "remi.durdsqdq456@laposte.net", "11 chemin du canal 31400");


    public void testAddUser() throws Exception {
        try {
            Response res = db.addUser(u1);
            assertNotNull(res);
            db.remove(res.getId(), res.getRev());

            res = db.addUser(uErrorMailAlready);
            assertNotNull(res);
            //db.remove(uE);


            res = db.addUser(uErrorMailAlready);
            assertNull(res);
            res = db.addUser(uErrorMailInvalide);
            assertNull(res);
            res = db.addUser(uErrorAdr);
            assertNull(res);

            db.remove(uErrorMailAlready);
        } catch (NullPointerException e) {
            e.printStackTrace();
            fail("unwanted exception");
        }
    }

    public void testGetAllUsers() throws Exception {
        try {
            db.addUser(u1);
            db.addUser(uErrorMailAlready);
        } catch (Exception e) {

        }

        List<User> users = db.getAllUsers();
        assertTrue(users.size() == 2);
        assertTrue(users.get(0).equals(u1) || users.get(0).equals(uErrorMailAlready));
        assertTrue(users.get(1).equals(u1) || users.get(1).equals(uErrorMailAlready));

        db.remove(u1);
        db.remove(uErrorMailAlready);
        users = db.getAllUsers();
        assertTrue(users.size() == 0);

    }

    public void testRemove() throws Exception {
        // test remove(String id, String rev);
        Response res1 = db.addUser(u1);
        Response res2 = db.addUser(uErrorMailAlready);
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
        db.addUser(uErrorMailAlready);
        users = db.getAllUsers();
        assertTrue(users.size() == 2);

        db.remove(u1);
        db.remove(uErrorMailAlready);
        users = db.getAllUsers();
        assertTrue(users.size() == 0);

    }

}
