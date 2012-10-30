package com.kspichale.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.kspichale.rest.domain.Order;
import com.kspichale.rest.domain.OrderList;

@Path("/order/")
public interface SimpleService {

	@GET
	@Produces("application/xml")
	@Path("/xml/{orderId}")
	public Order getOrderXml(@PathParam("orderId") int id);

	@GET
	@Produces("application/json")
	@Path("/json/{orderId}")
	public Order getOrderJson(@PathParam("orderId") int id);

	@GET
	@Produces("text/xml")
	@Path("/all/")
	public OrderList getAllOrders();

	@GET
	@Produces("text/plain")
	@Path("/hello/")
	public String hello(@QueryParam("arg1") String arg1,
			@QueryParam("arg2") String arg2);

	@PUT
	@Produces("application/xml")
	@Path("/update/")
	public Order updateOrder(Order order);

}
