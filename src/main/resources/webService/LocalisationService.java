package resources.webService;

import org.w3c.dom.Element;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 01/04/13
 * Time: 18:10
 */
public interface LocalisationService {

    public Element chercheVoisin(int userId, float rayonKm);
}
