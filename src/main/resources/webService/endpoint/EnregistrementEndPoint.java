package resources.webService.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
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
    private EnregistrementService enregistrement;

    public EnregistrementEndPoint(EnregistrementService enregistrementService) {
        enregistrement = enregistrementService;
    }

    /*              ------ A capter
    @PayloadRoot(namespace = Constants.NAMESPACE_URI, localPart = "EnregistrementRequest")
    @Namespace(prefix = "cp", uri = Constants.NAMESPACE_URI)
    @ResponsePayload     */
    public Element handleLocalisationRequest(
            @XPathParam("/cp:LocalisationRequest/cp:UserID") User user)
            throws Exception {
        Element response = enregistrement.enregistreUser(user);
        return response;
    }


}
                 /*
@Endpoint
public class EnregistrementEndPoint extends AbstractJDomPayloadEndpoint {
	private static final String NAMESPACE_URI = "http://barreaujonas.com/iaws/schemas";


	
	@Autowired
	public EnregistrementEndPoint(){
		User u ;//= new user();


		this.humanResourceService = humanResourceService;

	    Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);

	    startDateExpression = XPath.newInstance("//hr:StartDate");
	    startDateExpression.addNamespace(namespace);

	    endDateExpression = XPath.newInstance("//hr:EndDate");
	    endDateExpression.addNamespace(namespace);

	    nameExpression = XPath.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
	    nameExpression.addNamespace(namespace);
	}

    @Override
    protected Element invokeInternal(org.jdom2.Element element) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
	  public void handleHolidayRequest(@RequestPayload Element holidayRequest)               
	      throws Exception {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date startDate = dateFormat.parse(startDateExpression.valueOf(holidayRequest));
	    Date endDate = dateFormat.parse(endDateExpression.valueOf(holidayRequest));
	    String name = nameExpression.valueOf(holidayRequest);

	    humanResourceService.bookHoliday(startDate, endDate, name);
	  }
}  */
