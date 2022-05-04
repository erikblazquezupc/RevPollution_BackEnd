package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.UserLogro;
import domain.controllers.TxGetUserLogros;

@Path("/users")
public class Users {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{username}/logros")
	public Response getUserLogros(@PathParam("username") String username) {
		TxGetUserLogros tx = new TxGetUserLogros(username);
		tx.execute();
		ArrayList<UserLogro> user = tx.getResult();
		return Response.ok(user).header("Access-Control-Allow-Origin", "*").build();
	}
}