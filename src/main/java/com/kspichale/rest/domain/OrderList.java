package com.kspichale.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "orderList")
@XmlSeeAlso(Order.class)
public class OrderList {

    List orders;
   
    @XmlElement(name = "order")
    public List getOrder() {
        if (orders == null) {
            orders = new ArrayList();
        }
        return this.orders;
    }
}
