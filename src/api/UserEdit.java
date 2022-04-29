package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxUserEdit;

@Path("/useredit")
public class UserEdit {

    @PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response useredit(@QueryParam("username") String username,@QueryParam("password") String password, @QueryParam("email") String email, @QueryParam("name") String name, 
    @QueryParam("telf") String telf,@QueryParam("img") String img,@QueryParam("token") String token) {
		TxUserEdit tx = new TxUserEdit(username, name, password, email, telf, img, token);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}