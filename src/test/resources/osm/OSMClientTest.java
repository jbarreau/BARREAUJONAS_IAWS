package resources.osm;

import junit.framework.TestCase;
import resources.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 30/03/13
 * Time: 14:56
 */
public class OSMClientTest extends TestCase {
    private OSMClient osm;

    private User u1 = new User("barreau", "julien", "julien.barreau@univ-tlse3.fr", "62 rue leon bonnat 31400");
    private User u2 = new User("Durand", "remi", "remi.dur456@univ-tlse3.fr@univ-tlse3.fr", "11 chemin du canal 31400");
    private User uErrorAdr = new User("Durand", "remi", "remi.dur456sq@univ-tlse3.fr@univ-tlse3.fr", "qsdcanal 31400");


    public void testU1() throws Exception {
        osm = new OSMClient(u1.getAdrPostale());
        assertTrue(osm.search());
        assertEquals(43.5785198, osm.getLat());
        //assertEquals(1.4619501,osm.getLon());

    }

    public void testU2() throws Exception {
        osm = new OSMClient(u2.getAdrPostale());
        assertTrue(osm.search());
        assertEquals(43.5726569, osm.getLat());
        assertEquals(1.4626106, osm.getLon());

    }

    public void testUError() throws Exception {
        osm = new OSMClient(uErrorAdr.getAdrPostale());
        assertFalse(osm.search());
    }
}
