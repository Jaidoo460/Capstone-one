package com.techelevator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;


public class Inventory {

        private Map<String, Integer> inventoryList = new TreeMap<>();
        private int totalItems = 5;
        private String vendingFile = "vendingmachine.csv";
        private String soldOut = "SOLD OUT";


        public Map<String, Integer> getInventoryList () {
            return inventoryList;
        }

        public int getTotalItems () {
            return totalItems;
        }

        public String getSoldOut () {
            return soldOut;
        }


      //  public static void getInventory (Map<String, Integer> inventoryList, int totalItems, String vendingFile) throws IOException {
/*
       try {

            Scanner input = new Scanner(vendingFile);

            while (input.hasNextLine()) {
                inventoryList.put(input.nextLine(), totalItems);
            }

        }
        */


}