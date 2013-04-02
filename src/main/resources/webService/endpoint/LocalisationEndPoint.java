package resources.webService.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.Element;
import resources.webService.LocalisationService;

/**
 * Created with IntelliJ IDEA.
 * User: Julien
 * Date: 01/04/13
 * Time: 17:59
 */
public class LocalisationEndPoint {
    private static final String NAMESPACE_URI = "http://barreaujonas.com/iaws/schemas/";

    private LocalisationService localisation;

    @Autowired
    public LocalisationEndPoint(LocalisationService localisationService) {
        localisation = localisationService;

    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LocalisationRequest")
    @Namespace(prefix = "pref", uri = NAMESPACE_URI)
    @ResponsePayload
    public Element handleLocalisationRequest(
            @XPathParam("/pref:LocalisationRequest/pref:id_user") Integer userID,
            @XPathParam("/pref:LocalisationRequest/pref:rayon") Integer rayonKm)
            throws Exception {
        Element response = localisation.chercheVoisin(userID, rayonKm);
        return response;
    }

}
