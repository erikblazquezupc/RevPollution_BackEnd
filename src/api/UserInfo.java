package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.User;
import domain.controllers.TxUserInfo;

@Path("/userinfo")
public class UserInfo {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response userinfo(@QueryParam("token") String token) { //token solo o token + username?
		TxUserInfo tx = new TxUserInfo(token);
		tx.execute();
		User user = tx.getResult();
		return Response.ok(user).build();
	}
}