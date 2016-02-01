package com.example.adam.agendron_fueltrack;

/**
 * Created by Adam on 31/01/2016.
 */
public class unitcostEntry {
    private Float unitcost;

    public void setUnitcost(Float unitcost){
        this.unitcost = unitcost/100;
    }

    public float getUnitcost(){
        return this.unitcost;
    }
}
