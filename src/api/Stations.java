package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.StationStub;
import domain.controllers.TxGetStations;

@Path("/stations")
public class Stations {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/stations")
	public Response getStations() {
		TxGetStations tx = new TxGetStations();
		tx.execute();
		ArrayList<StationStub> result = tx.getResult();
		return Response.ok(result).build();
	}
}
