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
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.TEXT_HTML})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response signup(@QueryParam("username") String username, @QueryParam("password") String password, 
    @QueryParam("name") String name, @QueryParam("email") String email, @QueryParam("telf") String telf,
	String image) {
		TxSignUp tx = new TxSignUp(username, name, password, email, telf, image);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}
}
