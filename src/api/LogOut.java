package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxLogOut;

@Path("/logout")
public class LogOut {
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/logout")
	public Response logout(@QueryParam("username") String username) {
		TxLogOut tx = new TxLogOut(username);
		tx.execute();
		return Response.ok().build();
	}
}
