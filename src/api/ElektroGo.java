package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxElektroGo;

@Path("/elektrogo")
public class ElektroGo {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getElektroGo(@QueryParam("lat") double lat, @QueryParam("lon") double lon) {
		TxElektroGo tx = new TxElektroGo(lat, lon);
		tx.execute();
		String result = tx.getResult();
		return Response.ok(result).build();
	}
}
