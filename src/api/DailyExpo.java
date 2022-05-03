package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxGetDailyExpo;
import domain.controllers.TxAddDailyExpo;
import domain.Expo;

@Path("/dailyExpo")
public class DailyExpo {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getDailyExpo(@QueryParam("token") String token) {
		TxGetDailyExpo tx = new TxGetDailyExpo(token);
		tx.execute();
		ArrayList<Expo> result = tx.getResult();
		return Response.ok(result).build();
	}
 
    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/addExpo")
	public Response addDailyExpo(@QueryParam("token") String token, @QueryParam("lat") Double lat, 
    @QueryParam("lon") Double lon) {
		TxAddDailyExpo tx = new TxAddDailyExpo(token, lat, lon);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}
