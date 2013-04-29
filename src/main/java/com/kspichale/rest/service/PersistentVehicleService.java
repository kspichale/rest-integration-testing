package com.kspichale.rest.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.kspichale.rest.domain.Vehicle;
import com.kspichale.rest.domain.VehicleList;

public class PersistentVehicleService implements VehicleFleetService {

	private final Map<Integer, Vehicle> availableVehicles = new HashMap<Integer, Vehicle>();
	private final Map<Integer, Vehicle> notAvailableVehicles = new HashMap<Integer, Vehicle>();

	public PersistentVehicleService() {
	}

	@Override
	@PUT
	@Produces("application/json")
	@Path("/")
	public void add(Vehicle vehicle) {
		if (vehicle.isAvailable()) {
			availableVehicles.put(vehicle.getId(), vehicle);
		} else {
			notAvailableVehicles.put(vehicle.getId(), vehicle);
		}
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/available/")
	public VehicleList getAvailableVehicles() {
		final VehicleList list = new VehicleList();
		for (Vehicle vehicle : availableVehicles.values()) {
			list.getVehicle().add(vehicle);
		}
		return list;
	}

	@Override
	@PUT
	@Produces("application/json")
	@Path("/{id}/available/{available}")
	public void setAvailability(@PathParam("id") int vehicleId, @PathParam("available") boolean available) {
		System.out.println("start setAvailability");
		final Vehicle vehicle;
		if (availableVehicles.containsKey(vehicleId)) {
			vehicle = availableVehicles.remove(vehicleId);
		} else {
			vehicle = notAvailableVehicles.remove(vehicleId);
		}
		if (available) {
			availableVehicles.put(vehicleId, vehicle);
			notAvailableVehicles.remove(vehicleId);
		} else {
			notAvailableVehicles.put(vehicleId, vehicle);
			availableVehicles.remove(vehicleId);
		}
		System.out.println("finish setAvailability");
	}

	@Override
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public boolean isAvailable(@PathParam("id") int vehicleId) {
		return availableVehicles.containsKey(vehicleId);
	}
}
