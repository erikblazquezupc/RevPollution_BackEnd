package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.StationStub;
import domain.controllers.TxGetMapStations;

@Path("/station")
public class Station {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/station")
	public Response getStations() {
		TxGetMapStations tx = new TxGetMapStations();
		tx.execute();
		ArrayList<StationStub> result = tx.getResult();
		return Response.ok(result).build();
	}
}