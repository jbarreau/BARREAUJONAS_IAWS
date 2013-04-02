package resources.webService.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;
import org.w3c.dom.Element;
import resources.user.User;
import resources.webService.EnregistrementService;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 01/04/13
 * Time: 17:59
 */
@Endpoint
public class EnregistrementEndPoint {
    private static final String NAMESPACE_URI = "http://barreaujonas.com/iaws/schemas/";
    private EnregistrementService enregistrement;

    @Autowired
    public EnregistrementEndPoint(EnregistrementService enregistrementService) {
        enregistrement = enregistrementService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EnregistrementRequest")
    @Namespace(prefix = "pref", uri = NAMESPACE_URI)
    @ResponsePayload
    public Element handleEnregistrementRequest(
            @XPathParam("/pref:LocalisationRequest/pref:User") String nom,
            @XPathParam("/pref:LocalisationRequest/pref:User") String prenom,
            @XPathParam("/pref:LocalisationRequest/pref:User") String adrMail,
            @XPathParam("/pref:LocalisationRequest/pref:User") String adrPostale)
            throws Exception {
        Element response = enregistrement.enregistreUser(new User(nom, prenom, adrMail, adrPostale));
        return response;
    }


}