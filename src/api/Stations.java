package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.StationStub;
import domain.Quality;
import domain.controllers.TxGetQuality;
import domain.controllers.TxGetStation;
import domain.controllers.TxGetStations;
import domain.controllers.TxGetPastQualities;
import domain.controllers.TxGetPastQualitiesByHours;
import domain.controllers.TxGetFutureQualities;
import domain.controllers.TxGetFutureQualitiesByHours;

@Path("/stations")
public class Stations {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getStations() {
		TxGetStations tx = new TxGetStations();
		tx.execute();
		ArrayList<StationStub> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/info")
	public Response getStation(@QueryParam("idStation") int id) {
		TxGetStation tx = new TxGetStation(id);
		tx.execute();
		StationStub result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/quality")
	public Response getQuality(@QueryParam("lat") double lat, @QueryParam("lon") double lon) {
		TxGetQuality tx = new TxGetQuality(lat, lon);
		tx.execute();
		String result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/pastQualitiesByHours")
	public Response getPastQualitiesByHours(@QueryParam("idStation") int idStation) {
		TxGetPastQualitiesByHours tx = new TxGetPastQualitiesByHours(idStation);
		tx.execute();
		ArrayList<Quality> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/pastQualities")
	public Response getPastQualities(@QueryParam("idStation") int idStation) {
		TxGetPastQualities tx = new TxGetPastQualities(idStation);
		tx.execute();
		ArrayList<Quality> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/futureQualitiesByHours")
	public Response getFutureQualitiesByHours(@QueryParam("idStation") int idStation) {
		TxGetFutureQualitiesByHours tx = new TxGetFutureQualitiesByHours(idStation);
		tx.execute();
		ArrayList<Quality> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/futureQualities")
	public Response getFutureQualities(@QueryParam("idStation") int idStation) {
		TxGetFutureQualities tx = new TxGetFutureQualities(idStation);
		tx.execute();
		ArrayList<Quality> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}
}
