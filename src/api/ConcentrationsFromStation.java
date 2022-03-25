package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import domain.Concentration;
import domain.controllers.TxGetConcentrationsFromStation;

@Path("/concentrations")
public class ConcentrationsFromStation {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response concentrationsFromStation(@QueryParam("idStation") int idStation) {
		TxGetConcentrationsFromStation tx = new TxGetConcentrationsFromStation(idStation);
		tx.execute();
		ArrayList<Concentration> result = tx.getResult();
		return Response.ok(result).build();
	}
}
