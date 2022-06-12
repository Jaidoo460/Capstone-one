package com.techelevator.view;


import com.techelevator.VendingMachineCLI;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static java.awt.SystemColor.menu;


public class VendingMachineCLITest {


    private Menu menu;

    @Test
public void check_main_menu(){
    VendingMachineCLI vendingTest = new VendingMachineCLI(this.menu);


    VendingMachineCLI run = vendingTest;
    Assert.assertEquals(run, vendingTest);


}



}
