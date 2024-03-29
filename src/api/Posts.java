package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import domain.controllers.TxGetAllPosts;
import domain.controllers.TxGetPosts;
import domain.controllers.TxNewPost;

@Path("/posts")
public class Posts {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getPosts(@QueryParam("firstDate") Long firstDate, @QueryParam("lastDate") Long lastDate) {
		JSONArray result;
		if (firstDate == null && lastDate == null){
			TxGetAllPosts tx = new TxGetAllPosts();
			tx.execute();
			result = tx.getResult();
		}
		else {
			TxGetPosts tx = new TxGetPosts(firstDate, lastDate);
			tx.execute();
			result = tx.getResult();
		}
		return Response.ok(result.toString()).header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/new")
	public Response newPost(@QueryParam("text") String text, @QueryParam("userToken") String userToken) {
		TxNewPost tx = new TxNewPost(text, userToken);
		tx.execute();
		Long result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}
}
