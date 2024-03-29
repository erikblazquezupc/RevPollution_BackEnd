package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxLogIn;

@Path("/login")
public class LogIn {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/login")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		TxLogIn tx = new TxLogIn(username, password);
		tx.execute();
		String result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}
}
