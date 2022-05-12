package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Logro;
import domain.Logro.Tier;
import domain.controllers.TxCreateLogro;
import domain.controllers.TxDeleteLogro;
import domain.controllers.TxEditLogro;
//import domain.controllers.TxGetLogro;
import domain.controllers.TxGetLogroAdmin;
//import domain.controllers.TxGetLogros;
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
		return Response.ok(result).build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/info")
	public Response getLogroAdmin(@QueryParam("name") String name, @QueryParam("tier") Tier tier) {
		TxGetLogroAdmin tx = new TxGetLogroAdmin(name, tier);
		tx.execute();
		Logro result = tx.getResult();
		return Response.ok(result).build();
	}

    @PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/switchActivation")
	public Response switchActivation(@QueryParam("name") String name, @QueryParam("tier") Tier tier) {
		TxSwitchActivationLogro tx = new TxSwitchActivationLogro(name, tier);
		tx.execute();
		return Response.ok(true).build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/create")
	public Response createLogro(@QueryParam("name") String name, @QueryParam("tier") Tier tier, @QueryParam("cond") String cond, @QueryParam("activated") boolean activated) {
		TxCreateLogro tx = new TxCreateLogro(name, tier, cond, activated);
		tx.execute();
		return Response.ok(true).build();
	}

	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/delete")
	public Response deleteLogro(@QueryParam("name") String name, @QueryParam("tier") Tier tier) {
		TxDeleteLogro tx = new TxDeleteLogro(name, tier);
		tx.execute();
		return Response.ok(true).build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/edit")
	public Response editLogro(@QueryParam("name") String name, @QueryParam("tier") Tier tier, @QueryParam("cond") String cond, @QueryParam("activated") boolean activated) {
		TxEditLogro tx = new TxEditLogro(name, tier, cond, activated);
		tx.execute();
		return Response.ok(true).build();
	}
}
