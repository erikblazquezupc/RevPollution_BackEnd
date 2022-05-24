package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Logro;
import domain.Logro.Tier;
import domain.controllers.TxCreateLogro;
import domain.controllers.TxGetLogroAdmin;
import domain.controllers.TxGetLogrosAdmin;
import domain.controllers.TxSwitchActivationLogro;

@Path("/logrosAdmin")
public class LogrosAdmin {
    @GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getLogrosAdmin() {
		TxGetLogrosAdmin tx = new TxGetLogrosAdmin();
		tx.execute();
		ArrayList<Logro> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/info")
	public Response getLogroAdmin(@QueryParam("name") String name, @QueryParam("tier") Tier tier) {
		TxGetLogroAdmin tx = new TxGetLogroAdmin(name, tier);
		tx.execute();
		Logro result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

    @PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/switchActivation")
	public Response switchActivation(@QueryParam("name") String name, @QueryParam("tier") Tier tier) {
		TxSwitchActivationLogro tx = new TxSwitchActivationLogro(name, tier);
		tx.execute();
		return Response.ok(true).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/create")
	public Response createLogro(@QueryParam("name") String name, @QueryParam("tier") Tier tier, @QueryParam("cond") String cond, @QueryParam("activated") boolean activated, @QueryParam("limit") int limit, @QueryParam("statistic") String statistic) {
		TxCreateLogro tx = new TxCreateLogro(name, tier, cond, activated, limit, statistic);
		tx.execute();
		return Response.ok(true).header("Access-Control-Allow-Origin", "*").build();
	}

	@OPTIONS
	@Path("/create")
	public Response options(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}

	@OPTIONS
	@Path("/switchActivation")
	public Response options2(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}
}
