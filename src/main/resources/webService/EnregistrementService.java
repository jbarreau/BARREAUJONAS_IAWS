package resources.webService;

import org.w3c.dom.Element;
import resources.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 01/04/13
 * Time: 18:09
 */
public interface EnregistrementService {

    public Element enregistreUser(User user);
}
