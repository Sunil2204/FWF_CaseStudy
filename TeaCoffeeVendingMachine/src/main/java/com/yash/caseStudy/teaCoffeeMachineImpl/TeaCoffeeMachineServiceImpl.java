package com.yash.caseStudy.teaCoffeeMachineImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.yash.caseStudy.bean.TeaCoffeeMachineMaterial;
import com.yash.caseStudy.bean.WasteMaterial;
import com.yash.caseStudy.teaCoffeeMachine.TeaCoffeeMachineService;

public class TeaCoffeeMachineServiceImpl implements TeaCoffeeMachineService{
	
	private DrinkMaterialChecker drinkMaterialChecker;
	Integer coffeeCounter=0;
	Integer blackCoffeeCounter=0;
	Integer teaCounter=0;
	Integer blackTeaCounter=0;
	final static Logger logger = Logger.getLogger(TeaCoffeeMachineServiceImpl.class);
	
	public void setDrinkMaterialChecker(DrinkMaterialChecker drinkMaterialChecker){
		this.drinkMaterialChecker = drinkMaterialChecker;
	}
	
	Scanner scanner = new Scanner(System.in);
	public void makeDrink(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial,WasteMaterial wasteMaterial, Integer tea, Integer coffee, Integer sugar, Integer water, Integer milk, String typeOfDrink,Integer numberOfDrink) {
		
			if(typeOfDrink.equals("Coffee")){
				coffeeCounter = coffeeCounter + numberOfDrink;
				Map<String,Integer> requiredMaterialForCoffeeMap = new HashMap<String, Integer>();
				requiredMaterialForCoffeeMap.put("Coffee", (Constants.coffeeConsumptionForCoffee + Constants.wasteCoffeeConsumptionForCoffee) * numberOfDrink);
				requiredMaterialForCoffeeMap.put("Milk", (Constants.milkConsumptionForCoffee + Constants.wasteMilkConsumptionForCoffee) * numberOfDrink);
				requiredMaterialForCoffeeMap.put("Sugar", (Constants.sugarConsumptionForCoffee + Constants.wasteSugarConsumptionForCoffee) * numberOfDrink);
				requiredMaterialForCoffeeMap.put("Water", (Constants.waterConsumptionForCoffee + Constants.wasteWaterConsumptionForCoffee) * numberOfDrink);
				requiredMaterialForCoffeeMap.put("Tea", 0);
				
				if(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForCoffeeMap)){
					
					teaCoffeeMachineMaterial.setCoffeeContainer(teaCoffeeMachineMaterial.getCoffeeContainer() - requiredMaterialForCoffeeMap.get("Coffee"));
					teaCoffeeMachineMaterial.setMilkContainer(teaCoffeeMachineMaterial.getMilkContainer() - requiredMaterialForCoffeeMap.get("Milk"));
					teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer() - requiredMaterialForCoffeeMap.get("Sugar"));
					teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() - requiredMaterialForCoffeeMap.get("Water"));
					
					wasteMaterial.setWasteCoffee(wasteMaterial.getWasteCoffee() + (Constants.wasteCoffeeConsumptionForCoffee * numberOfDrink));
					wasteMaterial.setWasteMilk((wasteMaterial.getWasteMilk() + (Constants.wasteMilkConsumptionForCoffee) * numberOfDrink));
					wasteMaterial.setWasteSugar((wasteMaterial.getWasteSugar() + (Constants.wasteSugarConsumptionForCoffee) * numberOfDrink));
					wasteMaterial.setWasteWater(wasteMaterial.getWasteWater() + (Constants.wasteWaterConsumptionForCoffee * numberOfDrink));
					logger.info("We pleased to serve you coffee, Please pay "+(15*numberOfDrink)+" rupees...!\n");
					
				}	
				else
					logger.error("no enough material available to prepare coffee.");
			}
			
			else if(typeOfDrink.equals("Black Coffee")){
				blackCoffeeCounter = blackCoffeeCounter + numberOfDrink;
				Map<String,Integer> requiredMaterialForBlackCoffeeMap = new HashMap<String, Integer>();
				requiredMaterialForBlackCoffeeMap.put("Coffee", (Constants.coffeeConsumptionForBlackCoffee + Constants.wasteCoffeeConsumptionForBlackCoffee) * numberOfDrink);
				requiredMaterialForBlackCoffeeMap.put("Water", (Constants.waterConsumptionForBlackCoffee + Constants.wasteWaterConsumptionForBlackCoffee) * numberOfDrink);
				requiredMaterialForBlackCoffeeMap.put("Sugar", (Constants.sugarConsumptionForBlackCoffee + Constants.wasteSugarConsumptionForBlackCoffee) * numberOfDrink);
				requiredMaterialForBlackCoffeeMap.put("Tea", 0);
				requiredMaterialForBlackCoffeeMap.put("Milk", 0);
				
				if(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackCoffeeMap)){
					teaCoffeeMachineMaterial.setCoffeeContainer(teaCoffeeMachineMaterial.getCoffeeContainer() - requiredMaterialForBlackCoffeeMap.get("Coffee"));
					teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() - requiredMaterialForBlackCoffeeMap.get("Water"));
					teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer() - requiredMaterialForBlackCoffeeMap.get("Sugar"));
					
					wasteMaterial.setWasteCoffee(wasteMaterial.getWasteCoffee() + (Constants.wasteCoffeeConsumptionForBlackCoffee * numberOfDrink));
					wasteMaterial.setWasteWater(wasteMaterial.getWasteWater() + (Constants.wasteWaterConsumptionForBlackCoffee) * numberOfDrink);
					wasteMaterial.setWasteSugar(wasteMaterial.getWasteSugar() + (Constants.wasteSugarConsumptionForBlackCoffee) * numberOfDrink);
					logger.info("We pleased to serve you black coffee, Please pay "+(10*numberOfDrink)+" rupees...!\n");
				}
				else
					logger.error("no enough material available to prepare black coffee");
			}
			
			else if(typeOfDrink.equals("Tea")){
				teaCounter = teaCounter + numberOfDrink;
				Map<String,Integer> requiredMaterialForTea = new HashMap<String, Integer>();
				requiredMaterialForTea.put("Tea", (Constants.teaPowderConsumptionForTea + Constants.wasteTeaPowderConsumptionForTea) * numberOfDrink);
				requiredMaterialForTea.put("Sugar", (Constants.sugarConsumptionForTea + Constants.wasteSugarConsumptionForTea) * numberOfDrink);
				requiredMaterialForTea.put("Water", (Constants.waterConsumptionForTea + Constants.wasteWaterConsumptionForTea) * numberOfDrink);
				requiredMaterialForTea.put("Milk", (Constants.milkConsumptionForTea + Constants.wasteMilkConsumptionForTea) * numberOfDrink);
				requiredMaterialForTea.put("Coffee", 0);
				
				if(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForTea)){
					teaCoffeeMachineMaterial.setTeaContainer(teaCoffeeMachineMaterial.getTeaContainer() - requiredMaterialForTea.get("Tea"));
					teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer() - requiredMaterialForTea.get("Sugar"));
					teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() - requiredMaterialForTea.get("Water"));
					teaCoffeeMachineMaterial.setMilkContainer(teaCoffeeMachineMaterial.getMilkContainer() - requiredMaterialForTea.get("Milk"));
					
					wasteMaterial.setWasteTea(wasteMaterial.getWasteTea() + (Constants.wasteTeaPowderConsumptionForTea * numberOfDrink));
					wasteMaterial.setWasteSugar(wasteMaterial.getWasteSugar() + (Constants.wasteSugarConsumptionForTea * numberOfDrink));
					wasteMaterial.setWasteWater(wasteMaterial.getWasteWater() + (Constants.wasteWaterConsumptionForTea * numberOfDrink));
					wasteMaterial.setWasteMilk(wasteMaterial.getWasteMilk() + (Constants.wasteMilkConsumptionForTea * numberOfDrink));
					logger.info("We pleased to serve you tea, Please pay "+(10*numberOfDrink)+" rupees...!\n");
				}
				else
					logger.error("no enough material available to prepare Tea.");
			}
			
			else if(typeOfDrink.equals("Black Tea")){
				blackTeaCounter = blackTeaCounter + numberOfDrink;
				Map<String,Integer> requiredMaterialForBlackTea = new HashMap<String, Integer>();
				requiredMaterialForBlackTea.put("Tea", (Constants.teaPowderConsumptionForBlackTea + Constants.wasteTeaPowderConsumptionForBlackTea) * numberOfDrink);
				requiredMaterialForBlackTea.put("Sugar", (Constants.sugarConsumptionForBlackTea + Constants.wasteSugarConsumptionForBlackTea) * numberOfDrink);
				requiredMaterialForBlackTea.put("Water", (Constants.waterConsumptionForBlackTea + Constants.wasteWaterConsumptionForBlackTea) * numberOfDrink);
				requiredMaterialForBlackTea.put("Milk", 0);
				requiredMaterialForBlackTea.put("Coffee", 0);
				
				if(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackTea)){
					teaCoffeeMachineMaterial.setTeaContainer(teaCoffeeMachineMaterial.getTeaContainer() - requiredMaterialForBlackTea.get("Tea"));
					teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer() - requiredMaterialForBlackTea.get("Sugar"));
					teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() - requiredMaterialForBlackTea.get("Water"));
					
					wasteMaterial.setWasteTea(wasteMaterial.getWasteTea() + (Constants.wasteTeaPowderConsumptionForBlackTea * numberOfDrink));
					wasteMaterial.setWasteSugar(wasteMaterial.getWasteSugar() + (Constants.wasteSugarConsumptionForBlackTea * numberOfDrink));
					wasteMaterial.setWasteWater(wasteMaterial.getWasteWater() + (Constants.wasteWaterConsumptionForBlackTea * numberOfDrink));
					logger.info("We pleased to serve you coffee, Please pay "+(5*numberOfDrink)+" rupees...!\n");
				}
				else
					logger.error("no enough material available to prepare Black Tea.");
			}
			else{
				logger.error("Drink type is invalid, Please choose drink type from available list...!");
			}
			
	}

	
	public Integer refillContainer(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial,Integer refillCounter,Integer tea,Integer coffee,Integer sugar,Integer water,Integer milk){
		
			refillCounter++;
			
			if( teaCoffeeMachineMaterial.getTeaContainer() + tea <= 2000){
				teaCoffeeMachineMaterial.setTeaContainer(teaCoffeeMachineMaterial.getTeaContainer() + tea);
			}else
				logger.info("You Can refill tea powder container upto "+ (2000-teaCoffeeMachineMaterial.getTeaContainer()) +" grams.");
			
			if(teaCoffeeMachineMaterial.getCoffeeContainer() + coffee <= 2000){
				teaCoffeeMachineMaterial.setCoffeeContainer(teaCoffeeMachineMaterial.getCoffeeContainer() + coffee);
			}else
				logger.error("You Can refill coffee container upto "+ (2000-teaCoffeeMachineMaterial.getCoffeeContainer()) +" grams.");
			
			if(teaCoffeeMachineMaterial.getSugarContainer()  + sugar <= 8000){
				teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer()  + sugar);
			}else
				logger.error("You Can refill sugar container upto "+ (8000-teaCoffeeMachineMaterial.getSugarContainer()) +" grams.");
			
			if(teaCoffeeMachineMaterial.getWaterContainer() + water <= 15000){
				teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() + water);
			}else
				logger.error("You Can refill water container upto "+ (15000-teaCoffeeMachineMaterial.getWaterContainer()) +" liter.");
			
			if(teaCoffeeMachineMaterial.getMilkContainer() + milk <= 10000){
				teaCoffeeMachineMaterial.setMilkContainer(teaCoffeeMachineMaterial.getMilkContainer() + milk);
			}else
				logger.error("You Can refill milk container upto "+ (10000-teaCoffeeMachineMaterial.getMilkContainer()) +" liter.");
			
			return refillCounter;
	}

	public void checkTotalSale(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial, WasteMaterial wasteMaterial) {
		
		logger.info("Total Sale of drinks:\n");
		logger.info("Drink        | No. of Cups | Price");
		logger.info("----------------------------------");
		logger.info("Coffee       | "+coffeeCounter+"        | "+coffeeCounter*15);
		logger.info("Black Coffee | "+blackCoffeeCounter+"           | "+blackCoffeeCounter*10);
		logger.info("Tea          | "+teaCounter+"           | "+teaCounter*10);
		logger.info("BlackTea     | "+blackTeaCounter+"           | "+blackTeaCounter*5);
		logger.info("----------------------------------");
		logger.info("Total Cups   | "+(coffeeCounter+blackCoffeeCounter+teaCounter+blackTeaCounter)+"        | "
		+((coffeeCounter*15)+(blackCoffeeCounter*10)+(teaCounter*10)+(blackTeaCounter*5))+"\n");
		logger.info("******<--------Container Status-------->\n"+teaCoffeeMachineMaterial.toString()+"\n");
		logger.info("******<--------Waste Material Status------->\n"+wasteMaterial.toString()+"\n");
	}

	public void containerStatus(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial) {
		logger.info("Current Status of Container...!\n"+teaCoffeeMachineMaterial.toString()+"\n");
	}

	public void resetContainer(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial) {
		teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		logger.info("Container has been reset successfully...!\n"+teaCoffeeMachineMaterial.toString()+"\n");
	} 

}

