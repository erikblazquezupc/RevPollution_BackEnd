package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxSignUp;

@Path("/signup")
public class SignUp {

    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/signup")

	public Response signup(@QueryParam("username") String username, @QueryParam("password") String password, 
    @QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("telf") String telf, 
    @QueryParam("image") String image) {
		System.out.println("signup");
		TxSignUp tx = new TxSignUp(username, name, password, email, telf, image);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}
