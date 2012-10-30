package com.kspichale.rest.service;

import java.util.ArrayList;
import java.util.List;

import com.kspichale.rest.domain.Order;
import com.kspichale.rest.domain.OrderList;

public class SimpleServiceImpl implements SimpleService {

	List<Order> list = new ArrayList<Order>();

	public SimpleServiceImpl() {
		Order order = new Order();
		order.setItemName("Veggie Pizza");
		order.setQuantity(9);
		order.setCustomerName("OptumInsight");
		order.setId(0);
		list.add(order);

		order = new Order();
		order.setItemName("Green Salad");
		order.setQuantity(2);
		order.setCustomerName("OptumInsight");
		order.setId(1);
		list.add(order);
	}

	@Override
	public Order getOrderXml(int id) {
		for (Order o : list) {
			if (o.getId() == id) {
				return o;
			}
		}
		return null;
	}

	@Override
	public Order getOrderJson(int id) {
		return getOrderXml(id);
	}

	@Override
	public OrderList getAllOrders() {
		OrderList fullList = new OrderList();
		for (Order order : list) {
			fullList.getOrder().add(order);
		}
		return fullList;
	}

	// // Common method returning an Order POJO
	// public Order getOrder(int id) {
	// if ((id > 0) && (id <= list.size())) {
	// return list.get(id - 1);
	// } else
	// return null;
	// }

	@Override
	public String hello(String arg1, String arg2) {
		return arg1 + " " + arg2;
	}

	@Override
	public Order updateOrder(Order order) {
		Order oldOrder = getOrderXml(order.getId());
		list.remove(oldOrder);
		list.add(order);
		return order;
	}
}
