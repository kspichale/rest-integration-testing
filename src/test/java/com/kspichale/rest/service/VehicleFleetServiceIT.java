package com.kspichale.rest.service;

import static com.jayway.restassured.RestAssured.given;
import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.response.Response;
import com.kspichale.rest.domain.Vehicle;
import com.kspichale.rest.domain.VehicleList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rest-test-client.xml" })
public class VehicleFleetServiceIT {

	@Autowired
	@Qualifier("vehicleFleetClient")
	protected VehicleFleetService proxy;

	@Test
	public void addNewAvailableVehicle() throws Exception {
		// setup
		final Vehicle vehicle = new Vehicle();
		vehicle.setId(42);
		vehicle.setAvailable(true);

		// exercise
		proxy.add(vehicle);

		// verify with rest-assured DSL
		Response response = given().port(8080).expect().statusCode(200).when()
				.body("vehicleList.vehicle.available", equalTo(true), "vehicleList.vehicle.id", equalTo(42))
				.get("rest-integration-testing/api/vehicles/available");

		response.print();
	}

	@Test
	public void addNewAvailableAndNotAvailableVehicle() throws Exception {
		// setup
		final Vehicle availableVehicle = new Vehicle();
		availableVehicle.setId(42);
		availableVehicle.setAvailable(true);
		final Vehicle notAvailableVehicle = new Vehicle();
		notAvailableVehicle.setId(43);
		notAvailableVehicle.setAvailable(false);

		// exercise
		proxy.add(availableVehicle);
		proxy.add(notAvailableVehicle);

		// verify
		final VehicleList availableVehicles = proxy.getAvailableVehicles();
		assertThat(availableVehicles.getVehicle()).containsOnly(availableVehicle);
	}

	@Test
	public void changeAvailabilityToFalse() throws Exception {
		// setup
		final Vehicle availableVehicle = new Vehicle();
		availableVehicle.setId(42);
		availableVehicle.setAvailable(true);

		// exercise
		proxy.add(availableVehicle);
		proxy.setAvailability(availableVehicle.getId(), false);

		// verify
		final VehicleList availableVehicles = proxy.getAvailableVehicles();
		assertThat(availableVehicles.getVehicle()).isEmpty();
		assertThat(proxy.isAvailable(availableVehicle.getId())).isFalse();
	}
}