package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT = "Exit and return change";
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
        BigDecimal money = new BigDecimal(0.00);
        String vendingFile = "vendingmachine.csv";

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
                    System.out.println("Current Money Provided: " + money); // placeholder for money variable);
                    String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_SCREEN_OPTIONS);

                    if (purchaseChoice.equals(MONEY_FEED_REQUEST)) {

                        Scanner userInput = new Scanner(System.in);
                        System.out.println("How much money do you want to add? (Please enter in format x.xx): ");
                        String addedMoney = userInput.nextLine();




						/*
						Saving BigDecimal conversion for now

						double doubleMoney = Double.parseDouble(addedMoney);
						BigDecimal moneyAdded = BigDecimal.valueOf(addedMoney);
						money.add(moneyAdded);
						*/

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
                                            System.out.println(itemLine);
                                            // split the line with "|"
                                            // convert index 2 (price) into a double / BIgdecimal
                                            // minus the price from money
                                            // log transaction
                                            // return to purchase selection
                                        }


                                    }


                                } catch (Exception e) {

                                }

                            }


                            // Figure out how to log what is purchased


                        }


                    } else if (purchaseChoice.equals(COMPLETE_TRANSACTION)) {
                        // Empty money and give back change


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