package com.github.superryhma.miniprojekti.resources;

import com.github.superryhma.miniprojekti.dao.TypeDAO;
import com.github.superryhma.miniprojekti.dao.impl.TypeDAOInMemoryImpl;
import com.github.superryhma.miniprojekti.models.AttributeType;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("types")
@Produces(MediaType.APPLICATION_JSON)
public class TypesResource {

	private TypeDAO typeDAO = new TypeDAOInMemoryImpl();
	
	@GET
	public String getTypes() {
		Set<AttributeType> types = typeDAO.getTypes();
		return ResponseBuilder.getAPITypes(types).toString();
	}
}
