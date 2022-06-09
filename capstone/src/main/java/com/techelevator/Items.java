package com.techelevator;

import java.math.BigDecimal;

public class Items {

    private String itemName;
    private BigDecimal itemPrice;
    private int amtItemLeft;
    private String locationInMachine;

    public String getItemName() {
        return this.itemName;
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
}
