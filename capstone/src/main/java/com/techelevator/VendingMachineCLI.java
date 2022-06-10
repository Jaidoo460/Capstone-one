package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit machine";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT};

    private static final String MONEY_FEED_REQUEST = "Feed Money";
    private static final String SELECT_PRODUCT = "Select Product";
    private static final String COMPLETE_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_SCREEN_OPTIONS = {MONEY_FEED_REQUEST, SELECT_PRODUCT, COMPLETE_TRANSACTION};

    private Menu menu;

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {

        boolean vending = true;
        String[] activeMenu = MAIN_MENU_OPTIONS;
        BigDecimal money = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN);
        String vendingFile = "vendingmachine.csv";
        Date currentDate = new Date();

        while (vending) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                System.out.println("Current stock:");

                try {
                    Scanner input = new Scanner(new File(vendingFile));

                    while (input.hasNextLine()) {
                        System.out.println(input.nextLine());
                    }


                } catch (Exception e) {
                    System.out.println("Error found");
                    System.exit(1);
                }


            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                boolean purchasing = true;

                while (purchasing) {
                    System.out.println("Current Money Provided: $" + money); // placeholder for money variable);
                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_SCREEN_OPTIONS);

                    if (purchaseChoice.equals(MONEY_FEED_REQUEST)) {

                        Scanner userInput = new Scanner(System.in);
                        System.out.println("How much money do you want to add? (Please enter in format x.xx): ");
                        String addedMoney = userInput.nextLine();

//                        BigDecimal moneyByFive = new BigDecimal(addedMoney);

//                        if (moneyByFive.remainder(new BigDecimal(0.05), BigDecimal.ZERO) {
//                                //equals(new BigDecimal(0))) {
                        //!!! bug fix

                            money = money.add(new BigDecimal(addedMoney));

//                        } else {
//                            System.out.println("Please enter a value divisible by $0.05");
//                        }


                    } else if (purchaseChoice.equals(SELECT_PRODUCT)) {
                        boolean currentlyChoosing = true;

                        while (currentlyChoosing) {
                            Scanner userInput = new Scanner(System.in);
                            System.out.print("Select the row (A-D), or enter \"No\" to cancel. ");
                            String itemRow = userInput.nextLine();
                            System.out.print("Select the # item you want to buy (1-4), or enter \"No\" to cancel. ");
                            String itemNumber = userInput.nextLine();
                            String selection = itemRow + itemNumber;

                            if (selection.length() != 2) {

                                System.out.println("Please enter a value in the correct format");

                            } else if (selection.equalsIgnoreCase("No")) {

                                System.out.println("Returning to last menu");
                                currentlyChoosing = false;

                            } else {

                                File vendingMachine = new File("vendingmachine.csv");


                                try {
                                    Scanner readFile = new Scanner(vendingMachine.getAbsoluteFile());


                                    while (readFile.hasNextLine()) {
                                        String itemLine = readFile.nextLine();

                                        if (itemLine.contains(selection)) {

                                            String[] lineSplitter = itemLine.split("[|]");
                                            Arrays.toString(lineSplitter);

                                            System.out.println();

                                            System.out.println("The price of " + lineSplitter[1] + " is $" + (new BigDecimal(lineSplitter[2])));

                                            if (money.compareTo(new BigDecimal(lineSplitter[2])) >= 0){

                                                System.out.println("Enjoy your " + lineSplitter[1] + "!");

                                                money = money.subtract(new BigDecimal(lineSplitter[2]));
                                                System.out.println("Your new balance is $" + money);

                                                if (lineSplitter[3].contains("Chip")) {
                                                    System.out.println("Crunch Crunch, Yum!");
                                                } else if (lineSplitter[3].contains("Candy")) {
                                                    System.out.println("Munch Munch, Yum!");
                                                } else if (lineSplitter[3].contains("Drink")) {
                                                    System.out.println("Glug Glug, Yum!");
                                                } else {
                                                    System.out.println("Chew Chew, Yum!");
                                                }
                                            } else {
                                                System.out.println("Not enough money entered");
                                            }

                                            System.out.println(currentDate.toString());
                                            //!!!log transaction
                                            //!!!implement date + time

                                            //!!!update inventory after purchase

                                            currentlyChoosing = false;

                                        }
                                    }
                                } catch (Exception e) {
                                }
                            }
                        }


                    } else if (purchaseChoice.equals(COMPLETE_TRANSACTION)) {
                        int quartersCounter = 0;
                        int dimesCounter = 0;
                        int nickelsCounter = 0;

                        BigDecimal quarter = new BigDecimal(0.25).setScale(2, RoundingMode.HALF_EVEN);
                        BigDecimal dime = new BigDecimal(0.10).setScale(2, RoundingMode.HALF_EVEN);
                        BigDecimal nickel = new BigDecimal(0.05).setScale(2, RoundingMode.HALF_EVEN);


                        while(money.compareTo(BigDecimal.ZERO) == 1) {

                            if (money.compareTo(quarter) == 1) {
                                money = money.subtract(quarter);

                                quartersCounter++;
                            } else if (money.compareTo(dime) == 1) {
                                money = money.subtract(dime);

                                dimesCounter++;
                            } else {
                                money = money.subtract(nickel);

                                nickelsCounter++;
                            }
                        }
                        System.out.println("Change returned = " + quartersCounter + " quarters, " + dimesCounter + " dimes, and " + nickelsCounter + " nickels");


                        System.out.println("Back to the main menu");
                        purchasing = false;
                    }

                }


            } else if (choice.equals(MAIN_MENU_EXIT)) {
                System.out.println("Thank you for shopping with us");
                vending = false;
            }

        }

    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}