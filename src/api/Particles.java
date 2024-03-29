package api;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Particle;
import domain.controllers.TxActivateParticle;
import domain.controllers.TxDeactivateParticle;
import domain.controllers.TxGetParticles;


@Path("/particles")
public class Particles {
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/")
	public Response getParticles() {
        TxGetParticles tx = new TxGetParticles();
        tx.execute();
        ArrayList<Particle> result = tx.getResult();
        return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{particleName}/activate")
	public Response activate(@PathParam("particleName") String particleName) {
        TxActivateParticle tx = new TxActivateParticle(particleName);
        tx.execute();
        boolean result = tx.getResult();
        return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{particleName}/deactivate")
	public Response deactivate(@PathParam("particleName") String particleName) {
        TxDeactivateParticle tx = new TxDeactivateParticle(particleName);
        tx.execute();
        boolean result = tx.getResult();
        return Response.ok(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@OPTIONS
	@Path("/")
	public Response options(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}

	@OPTIONS
	@Path("/{particleName}/activate")
	public Response options2(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}

	@OPTIONS
	@Path("/{particleName}/deactivate")
	public Response options3(){
		return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Headers", "*").header("Access-Control-Allow-Methods", "OPTIONS,GET,PUT,DELETE,POST").build();
	}
}
