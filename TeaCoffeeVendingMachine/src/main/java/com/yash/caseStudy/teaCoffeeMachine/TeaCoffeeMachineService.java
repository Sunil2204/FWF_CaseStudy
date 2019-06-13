package com.yash.caseStudy.teaCoffeeMachine;

import com.yash.caseStudy.bean.TeaCoffeeMachineMaterial;
import com.yash.caseStudy.bean.WasteMaterial;

public interface TeaCoffeeMachineService {
	
	public void makeDrink(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial,WasteMaterial wasteMaterial, Integer tea, Integer coffee, Integer sugar, Integer water, Integer milk, String typeOfDrink,Integer numberOfDrink);
	public Integer refillContainer(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial,Integer refillCounter,Integer tea,Integer coffee,Integer sugar,Integer water,Integer milk);
	public void checkTotalSale(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial, WasteMaterial wasteMaterial);
	public void containerStatus(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial);
	public void resetContainer(TeaCoffeeMachineMaterial teaCoffeeMachineMaterial);

}
