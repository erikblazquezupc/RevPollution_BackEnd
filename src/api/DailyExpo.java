package api;

import java.sql.Date;
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
import domain.DailyExposition;

@Path("/dailyExpo")
public class DailyExpo {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getDailyExpo(@QueryParam("token") String token) {
		TxGetDailyExpo tx = new TxGetDailyExpo(token);
		tx.execute();
		ArrayList<DailyExposition> result = tx.getResult();
		return Response.ok(result).build();
	}
 
    @POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/addExpo")
	public Response addDailyExpo(@QueryParam("token") String token, @QueryParam("lat") Double lat, 
    @QueryParam("lon") Double lon) {
		Date dat = new Date(System.currentTimeMillis());
		TxAddDailyExpo tx = new TxAddDailyExpo(token, lat, lon, dat);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).build();
	}
}
