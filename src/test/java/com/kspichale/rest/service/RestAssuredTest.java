package com.kspichale.rest.service;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import com.jayway.restassured.response.Response;

public class RestAssuredTest {

	@Test
	public void testGetPersons() {

		final String customerName = "OptumInsight";
		final int id = 1;
		final String itemName = "newItemName";
		final int quantity = 2;

		Response response = expect()
				.statusCode(200)
				.when()
				.body("order.customerName", equalTo(customerName), "order.id",
						equalTo(id), "order.itemName", equalTo(itemName),
						"order.quantity", equalTo(quantity))
				.get("rest-integration-testing/api/order/json/1");

		response.print();
	}

}
