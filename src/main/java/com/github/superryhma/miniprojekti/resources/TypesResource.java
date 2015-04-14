package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.ReferenceTypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.ReferenceTypeDAOInMemoryImpl;
import com.github.superryhma.miniprojekti.models.AttributeType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("types")
@Produces(MediaType.APPLICATION_JSON)
public class TypesResource {

	private static ReferenceTypeDAO referenceTypeDAO = new ReferenceTypeDAOInMemoryImpl();
	
	@GET
	public Response getTypes() {
		Set<AttributeType> types = referenceTypeDAO.getTypes();
		return ResponseBuilder.getAPITypes(types);
	}
}
