package main.resources.couchdb;

import junit.framework.TestCase;
import org.lightcouch.Response;
import resources.couchdb.CouchDB;
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
    private User u2 = new User("Durand", "remi", "remi.dur456@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");


    public void testAddUser() throws Exception {
        try {
            if (u1.globalCheck()) {
                Response res = db.addUser(u1);
                assertNull(res.getError());
            }
            List<User> users = db.getAllUsers();
            assertTrue(users.size() == 1);
            assertTrue(users.get(0).equals(u1));


            if (u2.globalCheck()) {
                Response res = db.addUser(u2);
                assertNull(res.getError());
            }
            users = db.getAllUsers();
            assertTrue(users.size() == 2);
            assertTrue(users.get(0).equals(u1) || users.get(1).equals(u1));
            assertTrue(users.get(0).equals(u2) || users.get(1).equals(u2));

        } catch (NullPointerException e) {
            e.printStackTrace();
            fail("unwanted exception");
        } finally {
            db.remove(u1);
            db.remove(u2);
        }
    }

    public void testGetAllUsers() throws Exception {
        try {
            db.addUser(u1);
            db.addUser(u2);

            List<User> users = db.getAllUsers();
            assertTrue(users.contains(u1));
            assertTrue(users.contains(u2));
        } catch (Exception e) {

        } finally {
            db.remove(u1);
            db.remove(u2);
        }
    }

    public void testGetUser() throws Exception {
        try {
            db.addUser(u1);
            db.addUser(u2);

            List<User> users = db.getAllUsers();
            assertTrue(users.size() == 2);

            User tmp = db.getUser(u1.get_id());
            assertTrue(tmp.equals(u1));
            tmp = db.getUser(u2.get_id());
            assertTrue(tmp.equals(u2));
        } catch (Exception e) {

        } finally {
            db.remove(u1);
            db.remove(u2);
        }
    }

    public void testRemove() throws Exception {

        //test remove(object)
        try {
            db.addUser(u1);
            db.addUser(u2);
            List<User> users = db.getAllUsers();
            assertTrue(users.size() == 2);

            db.remove(u1);
            db.remove(u2);
            users = db.getAllUsers();
            assertTrue(users.size() == 0);
        } catch (Exception e) {

        } finally {
            if (db.getAllUsers().size() > 0) {
                db.remove(u1);
                db.remove(u2);
            }
        }


        // test remove(String id, String rev);
        try {
            Response res1 = db.addUser(u1);
            Response res2 = db.addUser(u2);
            List<User> users = db.getAllUsers();
            assertTrue(users.size() == 2);
            db.remove(Integer.decode(res1.getId()), Integer.decode(res1.getRev()));
            db.remove(Integer.decode(res2.getId()), Integer.decode(res2.getRev()));

            users = db.getAllUsers();
            assertTrue(users.size() == 0);

            db.addUser(u1);
            db.addUser(u2);
            db.remove(u1.get_id(), u2.get_rev());
            db.remove(u2.get_id(), u2.get_rev());
        } catch (Exception e) {

        } finally {
            if (db.getAllUsers().size() > 0) {
                db.remove(u1);
                db.remove(u2);
            }
        }
        assertTrue(db.getAllUsers().size() == 0);
    }

}
