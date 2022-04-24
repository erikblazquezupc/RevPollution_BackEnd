package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxSignUp;
import domain.controllers.TxSignUpPic;

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

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/signupPic")

	public Response signupPic(@QueryParam("username") String username, @QueryParam("image") String image) {
		TxSignUpPic tx = new TxSignUpPic(username, image);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}

/*package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.*;

import domain.controllers.TxSignUp;

@Path("/signup")
public class SignUp {

    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/signup")

	public Response signup(@RequestParam String body) {
		System.out.println("signup");
		System.out.prinln(body.toString());
		/*JSONObject obj = new JSONObject(body);
		String user = obj.getString("username");
		String password = obj.getString("password");
		String name = obj.getString("name");
		String email = obj.getString("email");
		String telf = obj.getString("telf");
		String image = obj.getString("image");
		TxSignUp tx = new TxSignUp(username, name, password, email, telf, image);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}*/
