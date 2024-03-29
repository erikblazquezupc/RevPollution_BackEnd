package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.StationStub;
import domain.controllers.TxGetStationAdmin;
import domain.controllers.TxGetStationsAdmin;
import domain.controllers.TxSwitchActivationStation;

@Path("/stationsAdmin")
public class StationsAdmin {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getStationsAdmin() {
		TxGetStationsAdmin tx = new TxGetStationsAdmin();
		tx.execute();
		ArrayList<StationStub> result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/info")
	public Response getStationAdmin(@QueryParam("idStation") int id) {
		TxGetStationAdmin tx = new TxGetStationAdmin(id);
		tx.execute();
		StationStub result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/switchActivation")
	public Response switchActivation(@QueryParam("idStation") int id) {
		TxSwitchActivationStation tx = new TxSwitchActivationStation(id);
		tx.execute();
		return Response.ok(true).header("Access-Control-Allow-Origin", "*").build();
	}

	@OPTIONS
	@Path("/switchActivation")
	public Response options(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}
}

