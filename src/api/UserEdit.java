package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxUserEdit;

@Path("/useredit")
public class UserEdit {

    @POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response useredit(String username, String name, String email, 
    String password, String telf, String img) {
		TxUserEdit tx = new TxUserEdit(username, name, password, email, telf, img);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}