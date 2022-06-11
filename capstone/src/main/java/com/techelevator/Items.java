package com.techelevator;

import java.io.File;
import java.math.BigDecimal;

public class Items {

    private String item;
    private BigDecimal itemPrice;
    private int amtItemLeft = 5;
    private String locationInMachine;

    public String getItem() {
        return this.item;
    }

    public BigDecimal getItemPrice() {
        return this.itemPrice;
    }

    public int getAmtItemLeft() {
        return this.amtItemLeft;
    }

    public String getLocationInMachine() {
        return this.locationInMachine;
    }

    File inventory = new File("vendingmachine.csv");


}
