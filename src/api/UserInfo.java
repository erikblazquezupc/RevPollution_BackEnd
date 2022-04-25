package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Date;

import domain.Search;

import domain.controllers.TxRecentSearches;
import domain.controllers.TxAddSearch;
import domain.controllers.TxDeleteSearch;
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
		String username = tx.getResult();
		return Response.ok(username).build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/recentSearches")
	public Response recentSearches (@QueryParam("token") String token) {
		TxRecentSearches tx = new TxRecentSearches(token);
		tx.execute();
		ArrayList<Search> result = tx.getResult();
		return Response.ok(result).build();
	}

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/addSearch")
	public Response addSearch (@QueryParam("token") String token, 
		@QueryParam("idStation") int idStation) {
		Date date = new Date();
		TxAddSearch tx = new TxAddSearch(token, idStation, date);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}

	@DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/deleteSearch")
	public Response deleteSearch (@QueryParam("token") String token, 
		@QueryParam("idStation") int idStation) {
		TxDeleteSearch tx = new TxDeleteSearch(token, idStation);
		tx.execute();
		return Response.ok().build();
	}
}