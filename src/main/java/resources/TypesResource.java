package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("types")
@Produces(MediaType.APPLICATION_JSON)
public class TypesResource {
	
	@GET
	public String getReferences() {
		return "";
	}
}
