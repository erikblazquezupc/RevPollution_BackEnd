package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.controllers.TxUserDelete;

@Path("/userdelete")
public class UserDelete {
    
    @DELETE
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response userdelete(@QueryParam("idUser") String idUser) {
		TxUserDelete tx = new TxUserDelete(idUser);
		tx.execute();
		boolean result = tx.getResult();
		return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@OPTIONS
	@Path("/")
	public Response options(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}
}
