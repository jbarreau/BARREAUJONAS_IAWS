package main.resources.user;

import junit.framework.TestCase;
import resources.couchdb.CouchDB;
import resources.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 30/03/13
 * Time: 14:56
 */
public class UserTest extends TestCase {
    private User u1 = new User("barreau", "julien", "julien.barreau@univ-tlse3.fr", "62 rue leon bonnat 31400");
    private User uErrorMailAlready = new User("Durand", "remi", "remi.dur456@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");
    private User uErrorAdr = new User("Durand", "remi", "remi.dur456sq@univ-tlse3.fr@univ-tlse3.fr", "qsdcanal 31400");
    private User uErrorMailInvalide = new User("Durand", "remi", "remi.durdsqdq456@laposte.net", "11 chemin du canal 31400");


    @Override
    public void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
        CouchDB db = new CouchDB();
        db.addUser(uErrorMailAlready);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
        CouchDB db = new CouchDB();
        db.remove(uErrorMailAlready);
    }

    public void testGlobalCheck() throws Exception {
        assertTrue(u1.globalCheck());
        assertFalse(uErrorAdr.globalCheck());
        assertFalse(uErrorMailAlready.globalCheck());
        assertFalse(uErrorMailInvalide.globalCheck());

    }

    public void testAlreadyExist() throws Exception {
        assertTrue(u1.alreadyExist());
        assertTrue(uErrorAdr.alreadyExist());
        assertFalse(uErrorMailAlready.alreadyExist());
        assertTrue(uErrorMailInvalide.alreadyExist());

    }

    public void testMailCheck() throws Exception {
        assertTrue(u1.mailCheck());
        assertTrue(uErrorAdr.mailCheck());
        assertTrue(uErrorMailAlready.mailCheck());
        assertFalse(uErrorMailInvalide.mailCheck());

    }

    public void testOSMFound() throws Exception {
        assertTrue(u1.OSMFound());
        assertFalse(uErrorAdr.OSMFound());
        assertTrue(uErrorMailAlready.OSMFound());
        assertTrue(uErrorMailInvalide.OSMFound());

    }

    public void testToString() throws Exception {
    }
}
