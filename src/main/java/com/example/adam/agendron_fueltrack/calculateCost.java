package com.example.adam.agendron_fueltrack;

/**
 * Created by Adam on 01/02/2016.
 */
public class calculateCost{
    protected float amountCalc;
    protected float unitcostCalc;
    protected float finalcostCalc;

    public calculateCost(amountEntry amount, unitcostEntry unitcost){
        this.amountCalc = amount.getAmount();
        this.unitcostCalc = unitcost.getUnitcost();
        this.finalcostCalc = amountCalc*unitcostCalc;
    }

    public float getCost(){
        return this.finalcostCalc;
    }

}
