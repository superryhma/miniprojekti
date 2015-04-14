package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.TypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.TypeDAOdbImpl;
import com.github.superryhma.miniprojekti.models.ReferenceType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Set;

@Path("types")
@Produces(MediaType.APPLICATION_JSON)
public class TypesResource {

	private static TypeDAO typeDAO = new TypeDAOdbImpl();
	
	@GET
	public String getTypes() {
		Set<ReferenceType> types = typeDAO.getTypes();
		return ResponseBuilder.getAPITypes(types).toString();
	}
}