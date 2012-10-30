package com.kspichale.rest.service;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kspichale.rest.domain.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rest-test-client.xml" })
public class SimpleServiceIT {

	@Autowired
	@Qualifier("simpleClient")
	protected SimpleService proxy;

	@Test
	public void testGetXml() throws Exception {
		final Order order = proxy.getOrderXml(1);
		assertThat(order).isNotNull();
	}

	@Test
	public void testGetJson() throws Exception {
		final Order order = proxy.getOrderJson(1);
		assertThat(order).isNotNull();
	}

	@Test
	public void testUpdateOrder() throws Exception {
		Order o1 = proxy.getOrderXml(1);
		o1.setItemName("newItemName");
		proxy.updateOrder(o1);
		Order o2 = proxy.getOrderXml(1);
		assertThat(o1).isEqualTo(o2);
	}
}