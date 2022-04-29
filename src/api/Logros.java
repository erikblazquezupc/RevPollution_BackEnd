package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Logro;
import domain.Logro.Tier;
import domain.controllers.TxGetLogro;
import domain.controllers.TxGetLogros;

@Path("/logros")
public class Logros {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getLogros() {
		TxGetLogros tx = new TxGetLogros();
		tx.execute();
		ArrayList<Logro> result = tx.getResult();
		return Response.ok(result).build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/info")
	public Response getLogro(@QueryParam("name") String name, @QueryParam("tier") Tier tier) {
		TxGetLogro tx = new TxGetLogro(name, tier);
		tx.execute();
		Logro result = tx.getResult();
		return Response.ok(result).build();
	}
}
