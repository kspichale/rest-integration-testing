package com.kspichale.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.kspichale.rest.domain.Vehicle;
import com.kspichale.rest.domain.VehicleList;

@Path("/vehicles/")
public interface VehicleFleetService {

	@PUT
	@Produces("application/json")
	@Path("/")
	void add(Vehicle vehicle);

	@GET
	@Produces("application/json")
	@Path("/available/")
	VehicleList getAvailableVehicles();

	@PUT
	@Produces("application/json")
	@Path("/{id}/available/{available}")
	void setAvailability(@PathParam("id") int vehicleId, @PathParam("available") boolean available);

	@GET
	@Produces("application/json")
	@Path("/{id}")
	boolean isAvailable(@PathParam("id") int vehicleId);

}
