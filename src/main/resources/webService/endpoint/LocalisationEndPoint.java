package resources.webService.endpoint;


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
    private LocalisationService localisation;

    public LocalisationEndPoint(LocalisationService localisationService) {
        localisation = localisationService;

    }

    /*              ------ A capter
    @PayloadRoot(namespace = Constants.NAMESPACE_URI, localPart = "LocalisationRequest")
    @Namespace(prefix = "cp", uri = Constants.NAMESPACE_URI)
    @ResponsePayload     */
    public Element handleLocalisationRequest(
            @XPathParam("/cp:LocalisationRequest/cp:UserID") Integer userID,
            @XPathParam("/cp:LocalisationRequest/cp:RadiusKM") Integer rayonKm)
            throws Exception {
        Element response = localisation.chercheVoisin(userID, rayonKm);
        return response;
    }

}
