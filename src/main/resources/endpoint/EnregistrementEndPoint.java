package endpoint;
                  /*
import org.jdom.Element;
import org.jdom.xpath.XPath;
import org.lightcouch.CouchDbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.text.SimpleDateFormat;
import java.util.Date;


@Endpoint
public class EnregistrementEndPoint extends AbstractJDomPayloadEndpoint {
	private static final String NAMESPACE_URI = "http://barreaujonas.com/iaws/schemas";

	private CouchDbClient dbClient = new CouchDbClient("couchdb.properties");

	
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
	
	 @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")                  
	  public void handleHolidayRequest(@RequestPayload Element holidayRequest)               
	      throws Exception {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date startDate = dateFormat.parse(startDateExpression.valueOf(holidayRequest));
	    Date endDate = dateFormat.parse(endDateExpression.valueOf(holidayRequest));
	    String name = nameExpression.valueOf(holidayRequest);

	    humanResourceService.bookHoliday(startDate, endDate, name);
	  }
}
                                       */