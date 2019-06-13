package com.yash.caseStudy.teaCoffeeMachineImpl;

import java.util.Map;

import com.yash.caseStudy.bean.TeaCoffeeMachineMaterial;


public interface DrinkMaterialChecker {
	
	default boolean checkMaterial(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial, Map<String,Integer> requiredMaterialForDrink){
		
		if(teaCoffeeMachineMaterial.getCoffeeContainer() >= requiredMaterialForDrink.get("Coffee") && teaCoffeeMachineMaterial.getMilkContainer() >= requiredMaterialForDrink.get("Milk") 
				&& teaCoffeeMachineMaterial.getSugarContainer() >= requiredMaterialForDrink.get("Sugar") && teaCoffeeMachineMaterial.getWaterContainer() >= requiredMaterialForDrink.get("Water") 
				&& teaCoffeeMachineMaterial.getTeaContainer() >= requiredMaterialForDrink.get("Tea")){
			return true;
		}
		else
			return false;
	}

}
