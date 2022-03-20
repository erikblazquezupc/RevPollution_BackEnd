package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	public Response getStations(@QueryParam("stations") ArrayList<StationStub> stations) {
		TxGetMapStations tx = new TxGetMapStations(stations);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}