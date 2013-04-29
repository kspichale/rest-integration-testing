package com.kspichale.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "vehicleList")
@XmlSeeAlso(Vehicle.class)
public class VehicleList {

    List vehicles;
   
    @XmlElement(name = "vehicle")
    public List getVehicle() {
        if (vehicles == null) {
        	vehicles = new ArrayList();
        }
        return this.vehicles;
    }
}
