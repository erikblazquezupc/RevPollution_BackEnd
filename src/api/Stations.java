package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import domain.controllers.TxGetStations;
import domain.StationStub;

@Path("/stations")
public class Stations {
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/stations")
	public Response stations() {
		System.out.println("stations");
		TxGetStations tx = new TxGetStations();
		tx.execute();
		List<StationStub> result = tx.getResult();
		return Response.ok(result).build();
	}
}
