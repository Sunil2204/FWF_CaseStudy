package com.yash.caseStudy.teaCoffeeMachineImpl;

import java.io.IOException;
import java.util.Scanner;

import com.yash.caseStudy.bean.TeaCoffeeMachineMaterial;
import com.yash.caseStudy.bean.WasteMaterial;

public class ApplicationExecuter {

	public static void main(String[] args) throws IOException {
		Integer choice = null;
		Integer refillCounter=0;
		Integer numberOfDrink=0;

		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		TeaCoffeeMachineServiceImpl coffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		coffeeMachineServiceImpl.setDrinkMaterialChecker(drinkMaterialChecker);
		
		System.out.println("<-----*****Welcome To Tea-Coffee Vending Machine...!*****----->\n");
		System.out.println("*****Tea Coffee Machine Initial Capacity: *****\n"+teaCoffeeMachineMaterial.toString()+"\n");

		do{
			System.out.println("\nPlease choose option: \n1. Coffee  2. Tea  3. Black Coffee  4. Black Tea 5. Refill Container  "
					+ "\n6. Toatal Sale  7. Container Status  8. Reset Container 9. Exit");
			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();
			System.out.println();

			switch(choice){

			case 1 : 
				System.out.println("Please Enter Number of Drinks:");
				numberOfDrink = scanner.nextInt();
				System.out.println();
				coffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForCoffee, Constants.sugarConsumptionForCoffee, Constants.waterConsumptionForCoffee, Constants.milkConsumptionForCoffee, "Coffee",numberOfDrink);
				break;

			case 2 : 
				System.out.println("Please Enter Number of Drinks:");
				numberOfDrink = scanner.nextInt();
				System.out.println();
				coffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, Constants.teaPowderConsumptionForTea, 0, Constants.sugarConsumptionForTea, Constants.waterConsumptionForTea, Constants.milkConsumptionForTea, "Tea",numberOfDrink);
				break;

			case 3 :
				System.out.println("Please Enter Number of Drinks:");
				numberOfDrink = scanner.nextInt();
				System.out.println();
				coffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForBlackCoffee, Constants.sugarConsumptionForBlackCoffee, Constants.waterConsumptionForBlackCoffee, 0, "Black Coffee",numberOfDrink);
				break;

			case 4 :
				System.out.println("Please Enter Number of Drinks:");
				numberOfDrink = scanner.nextInt();
				System.out.println();
				coffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, Constants.teaPowderConsumptionForBlackTea, 0, Constants.sugarConsumptionForBlackTea, Constants.waterConsumptionForBlackTea, 0, "Black Tea",numberOfDrink);
				break;

			case 5 : 
				System.out.println("Please give inputs to refill containers...!\n");
				System.out.println("Enter how much grams of tea powder do you want to refill in container.");
				Integer tea = scanner.nextInt();
				System.out.println("Enter how much grams of coffee do you want to refill in container.");
				Integer coffee = scanner.nextInt();
				System.out.println("Enter how much grams of sugar do you want to refill in container.");
				Integer sugar = scanner.nextInt();
				System.out.println("Enter how much mili-liter of water do you want to refill in container.");
				Integer water = scanner.nextInt();
				System.out.println("Enter how much mili-liter of milk do you want to refill in container.");
				Integer milk = scanner.nextInt();
				coffeeMachineServiceImpl.refillContainer(teaCoffeeMachineMaterial,refillCounter,tea,coffee,sugar,water,milk);
				break;

			case 6 : 
				coffeeMachineServiceImpl.checkTotalSale(teaCoffeeMachineMaterial, wasteMaterial );
				break;

			case 7 : 
				coffeeMachineServiceImpl.containerStatus(teaCoffeeMachineMaterial);
				break;

			case 8 :  
				coffeeMachineServiceImpl.resetContainer(teaCoffeeMachineMaterial);
				break;

			case 9 :
				System.out.println("Bye Bye...!");
				break;
				
			default:
				System.out.println("Invalid Input...!");
				break;

			}

		}while(choice != 9);	    

	}
}


