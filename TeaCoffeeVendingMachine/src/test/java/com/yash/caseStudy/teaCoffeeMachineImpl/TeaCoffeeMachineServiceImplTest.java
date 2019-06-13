package com.yash.caseStudy.teaCoffeeMachineImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.caseStudy.bean.TeaCoffeeMachineMaterial;
import com.yash.caseStudy.bean.WasteMaterial;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeMachineServiceImplTest {
	
	@Mock
	private DrinkMaterialChecker drinkMaterialChecker;
	
	@InjectMocks
	private TeaCoffeeMachineServiceImpl teaCoffeeMachineServiceImpl;

	@Test()
	public void shouldPrepareCoffeeAndSetUpdatedMaterialToContainerAndWasteMaterial(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		Integer numberOfDrink = 2;
		String typeOfDrink = "Coffee";
		Map<String,Integer> requiredMaterialForCoffeeMap = new HashMap<String, Integer>();
		requiredMaterialForCoffeeMap.put("Coffee", (Constants.coffeeConsumptionForCoffee + Constants.wasteCoffeeConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForCoffeeMap.put("Milk", (Constants.milkConsumptionForCoffee + Constants.wasteMilkConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForCoffeeMap.put("Sugar", (Constants.sugarConsumptionForCoffee + Constants.wasteSugarConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForCoffeeMap.put("Water", (Constants.waterConsumptionForCoffee + Constants.wasteWaterConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForCoffeeMap.put("Tea", 0);
		WasteMaterial expected = new WasteMaterial();
		expected.setWasteCoffee(2);
		expected.setWasteMilk(16);
		expected.setWasteSugar(4);
		expected.setWasteTea(0);
		expected.setWasteWater(6);
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForCoffeeMap)).thenReturn(true);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForCoffee, Constants.sugarConsumptionForCoffee, Constants.waterConsumptionForCoffee, Constants.milkConsumptionForCoffee, typeOfDrink,numberOfDrink);
		
		verify(drinkMaterialChecker,times(1)).checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForCoffeeMap);
		
	}
	
	@Test()
	public void shouldDisplayErrorMessageWhenContainerHasLessMaterialForCoffee(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		Map<String,Integer> requiredMaterialForCoffeeMap = new HashMap<String, Integer>();
		Integer numberOfDrink = 2;
		String typeOfDrink = "Coffee";
		
		teaCoffeeMachineMaterial.setCoffeeContainer(4); 
		teaCoffeeMachineMaterial.setMilkContainer(0);
		teaCoffeeMachineMaterial.setSugarContainer(0);
		teaCoffeeMachineMaterial.setTeaContainer(0);
		teaCoffeeMachineMaterial.setWaterContainer(0);
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForCoffeeMap)).thenReturn(false);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForCoffee, Constants.sugarConsumptionForCoffee, Constants.waterConsumptionForCoffee, Constants.milkConsumptionForCoffee, typeOfDrink,numberOfDrink);
	
	}
	
	@Test()
	public void shouldPrepareBlackCoffeeAndSetUpdatedMaterialToContainerAndWasteMaterial(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		
		Integer numberOfDrink = 2;
		String typeOfDrink = "Black Coffee";
		Map<String,Integer> requiredMaterialForBlackCoffeeMap = new HashMap<String, Integer>();
		requiredMaterialForBlackCoffeeMap.put("Coffee", (Constants.coffeeConsumptionForBlackCoffee + Constants.wasteCoffeeConsumptionForBlackCoffee) * numberOfDrink);
		requiredMaterialForBlackCoffeeMap.put("Water", (Constants.waterConsumptionForBlackCoffee + Constants.wasteWaterConsumptionForBlackCoffee) * numberOfDrink);
		requiredMaterialForBlackCoffeeMap.put("Sugar", (Constants.sugarConsumptionForBlackCoffee + Constants.wasteSugarConsumptionForBlackCoffee) * numberOfDrink);
		requiredMaterialForBlackCoffeeMap.put("Tea", 0);
		requiredMaterialForBlackCoffeeMap.put("Milk", 0);
		
		WasteMaterial expected = new WasteMaterial();
		expected.setWasteCoffee(0);
		expected.setWasteMilk(0);
		expected.setWasteSugar(4);
		expected.setWasteTea(0);
		expected.setWasteWater(24);
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackCoffeeMap)).thenReturn(true);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForBlackCoffee, Constants.sugarConsumptionForBlackCoffee, Constants.waterConsumptionForBlackCoffee, 0, typeOfDrink,numberOfDrink);
		
		verify(drinkMaterialChecker,times(1)).checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackCoffeeMap);
		
	}
	
	@Test()
	public void shouldDisplayErrorMessageWhenContainerHasLowMaterialForBlackCoffee(){
		
		WasteMaterial wasteMaterial = new WasteMaterial();
		Map<String,Integer> requiredMaterialForBlackCoffeeMap = new HashMap<String, Integer>();
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		teaCoffeeMachineMaterial.setCoffeeContainer(4); 
		Integer numberOfDrink = 2;
		String typeOfDrink = "Black Coffee";
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackCoffeeMap)).thenReturn(false);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForBlackCoffee, Constants.sugarConsumptionForBlackCoffee, Constants.waterConsumptionForBlackCoffee, 0, typeOfDrink,numberOfDrink);
	
	}
	
	@Test()
	public void shouldPrepareTeaAndSetUpdatedMaterialToContainerAndWasteMaterial(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		Integer numberOfDrink = 2;
		String typeOfDrink = "Tea";
		Map<String,Integer> requiredMaterialForTea = new HashMap<String, Integer>();
		requiredMaterialForTea.put("Tea", (Constants.teaPowderConsumptionForTea + Constants.wasteTeaPowderConsumptionForTea) * numberOfDrink);
		requiredMaterialForTea.put("Sugar", (Constants.sugarConsumptionForTea + Constants.wasteSugarConsumptionForTea) * numberOfDrink);
		requiredMaterialForTea.put("Water", (Constants.waterConsumptionForTea + Constants.wasteWaterConsumptionForTea) * numberOfDrink);
		requiredMaterialForTea.put("Milk", (Constants.milkConsumptionForTea + Constants.wasteMilkConsumptionForTea) * numberOfDrink);
		requiredMaterialForTea.put("Coffee", 0);
		WasteMaterial expected = new WasteMaterial();
		expected.setWasteCoffee(0);
		expected.setWasteMilk(8);
		expected.setWasteSugar(4);
		expected.setWasteTea(2);
		expected.setWasteWater(10);
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForTea)).thenReturn(true);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, Constants.teaPowderConsumptionForTea, 0, Constants.sugarConsumptionForTea, Constants.waterConsumptionForTea, Constants.milkConsumptionForTea, typeOfDrink,numberOfDrink);
		
		verify(drinkMaterialChecker, times(1)).checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForTea);
		
	}
	
	@Test()
	public void shouldDisplayErrorMessageWhenContainerHasLowMaterialForTea(){
		
		WasteMaterial wasteMaterial = new WasteMaterial();
		Map<String,Integer> requiredMaterialForTea = new HashMap<String, Integer>();
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		teaCoffeeMachineMaterial.setTeaContainer(2); 
		Integer numberOfDrink = 2;
		String typeOfDrink = "Tea";
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForTea)).thenReturn(false);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, Constants.teaPowderConsumptionForTea, 0, Constants.sugarConsumptionForTea, Constants.waterConsumptionForTea, Constants.milkConsumptionForTea, typeOfDrink,numberOfDrink);
	
	}
	
	@Test()
	public void shouldPrepareBlackTeaAndSetUpdatedMaterialToContainerAndWasteMaterial(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		Integer numberOfDrink = 2;
		String typeOfDrink = "Black Tea";
		Map<String,Integer> requiredMaterialForBlackTea = new HashMap<String, Integer>();
		requiredMaterialForBlackTea.put("Tea", (Constants.teaPowderConsumptionForBlackTea + Constants.wasteTeaPowderConsumptionForBlackTea) * numberOfDrink);
		requiredMaterialForBlackTea.put("Sugar", (Constants.sugarConsumptionForBlackTea + Constants.wasteSugarConsumptionForBlackTea) * numberOfDrink);
		requiredMaterialForBlackTea.put("Water", (Constants.waterConsumptionForBlackTea + Constants.wasteWaterConsumptionForBlackTea) * numberOfDrink);
		requiredMaterialForBlackTea.put("Milk", 0);
		requiredMaterialForBlackTea.put("Coffee", 0);
		WasteMaterial expected = new WasteMaterial();
		expected.setWasteCoffee(0);
		expected.setWasteMilk(0);
		expected.setWasteSugar(4);
		expected.setWasteTea(0);
		expected.setWasteWater(24);
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackTea)).thenReturn(true);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, Constants.teaPowderConsumptionForBlackTea, 0, Constants.sugarConsumptionForBlackTea, Constants.waterConsumptionForBlackTea, 0, typeOfDrink,numberOfDrink);
		
		verify(drinkMaterialChecker,times(1)).checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackTea);
		
	}
	
	@Test()
	public void shouldDisplayErrorMessageWhenTeaCoffeeMachineContainerHasLowMaterialThanRequiredMaterialForBlackTea(){
		
		WasteMaterial wasteMaterial = new WasteMaterial();
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		teaCoffeeMachineMaterial.setTeaContainer(2); 
		Integer numberOfDrink = 2;
		String typeOfDrink = "Black Tea";
		Map<String,Integer> requiredMaterialForBlackTea = new HashMap<String, Integer>();
		
		when(drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForBlackTea)).thenReturn(false);
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, Constants.teaPowderConsumptionForBlackTea, 0, Constants.sugarConsumptionForBlackTea, Constants.waterConsumptionForBlackTea, 0, typeOfDrink,numberOfDrink);
	
	}
	
	@Test
	public void shouldIncrementRefillCounterByOneWhenRefillAmountNotMakingOverflowToContainer(){
		
		Integer refillCounter=0;
		Integer expected = 1;
		TeaCoffeeMachineServiceImpl coffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		teaCoffeeMachineMaterial.setTeaContainer(teaCoffeeMachineMaterial.getTeaContainer() - 100);
		teaCoffeeMachineMaterial.setCoffeeContainer(teaCoffeeMachineMaterial.getCoffeeContainer() - 100);
		teaCoffeeMachineMaterial.setMilkContainer(teaCoffeeMachineMaterial.getMilkContainer() - 100);
		teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer() - 100);
		teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() - 100);
		
		Integer actual = coffeeMachineServiceImpl.refillContainer(teaCoffeeMachineMaterial, refillCounter,100,100,100,100,100);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void shouldDisplayMessageWhenRefillAmountMakesOverflowToContainer(){
		
		Integer refillCounter=0;
		Integer expected = 1;
		TeaCoffeeMachineServiceImpl coffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		teaCoffeeMachineMaterial.setTeaContainer(teaCoffeeMachineMaterial.getTeaContainer() - 100);
		teaCoffeeMachineMaterial.setCoffeeContainer(teaCoffeeMachineMaterial.getCoffeeContainer() - 100);
		teaCoffeeMachineMaterial.setMilkContainer(teaCoffeeMachineMaterial.getMilkContainer() - 100);
		teaCoffeeMachineMaterial.setSugarContainer(teaCoffeeMachineMaterial.getSugarContainer() - 100);
		teaCoffeeMachineMaterial.setWaterContainer(teaCoffeeMachineMaterial.getWaterContainer() - 100);
		
		Integer actual = coffeeMachineServiceImpl.refillContainer(teaCoffeeMachineMaterial, refillCounter,101,200,101,200,101);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void shouldDisplayTotalSaleReportDetails(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		WasteMaterial wasteMaterial = new WasteMaterial();
		TeaCoffeeMachineServiceImpl teaCoffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		
		teaCoffeeMachineServiceImpl.checkTotalSale(teaCoffeeMachineMaterial, wasteMaterial);
		
	}
	
	@Test 
	public void shouldDisplayCurrentStatusOfContainer(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		TeaCoffeeMachineServiceImpl teaCoffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		
		teaCoffeeMachineServiceImpl.containerStatus(teaCoffeeMachineMaterial);
		
	}
	
	@Test
	public void shouldResetContainerCapacityWithItsHighestCapacity(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		TeaCoffeeMachineServiceImpl teaCoffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		
		teaCoffeeMachineServiceImpl.resetContainer(teaCoffeeMachineMaterial);
		
	}
	
	@Test 
	public void shouldDisplayErrorMessageWhenUserPassInvalidDrinkType(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		TeaCoffeeMachineServiceImpl teaCoffeeMachineServiceImpl = new TeaCoffeeMachineServiceImpl();
		WasteMaterial wasteMaterial = new WasteMaterial();
		String typeOfDrink="InvalidDrinkType";
		Integer numberOfDrink=2;
		
		teaCoffeeMachineServiceImpl.makeDrink(teaCoffeeMachineMaterial,wasteMaterial, 0, Constants.coffeeConsumptionForCoffee, Constants.sugarConsumptionForCoffee, Constants.waterConsumptionForCoffee, Constants.milkConsumptionForCoffee, typeOfDrink,numberOfDrink);
		
	}
	
	@Test
	public void shouldReturnTrueWhenAllConditionsAreTrue(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Integer numberOfDrink=2;
		Boolean expected = true,actual=null;
		requiredMaterialForDrink.put("Coffee", (Constants.coffeeConsumptionForCoffee + Constants.wasteCoffeeConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Milk", (Constants.milkConsumptionForCoffee + Constants.wasteMilkConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Sugar", (Constants.sugarConsumptionForCoffee + Constants.wasteSugarConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Water", (Constants.waterConsumptionForCoffee + Constants.wasteWaterConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Tea", (Constants.teaPowderConsumptionForTea + Constants.wasteTeaPowderConsumptionForTea) * numberOfDrink);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenAllConditionsAreFalse(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Integer numberOfDrink=2000;
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", (Constants.coffeeConsumptionForCoffee + Constants.wasteCoffeeConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Milk", (Constants.milkConsumptionForCoffee + Constants.wasteMilkConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Sugar", (Constants.sugarConsumptionForCoffee + Constants.wasteSugarConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Water", (Constants.waterConsumptionForCoffee + Constants.wasteWaterConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Tea", (Constants.teaPowderConsumptionForTea + Constants.wasteTeaPowderConsumptionForTea) * numberOfDrink);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForCoffeeIsFalseAndRestAllTrue(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Integer numberOfDrink=2;
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * numberOfDrink);
		requiredMaterialForDrink.put("Milk", (Constants.milkConsumptionForCoffee + Constants.wasteMilkConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Sugar", (Constants.sugarConsumptionForCoffee + Constants.wasteSugarConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Water", (Constants.waterConsumptionForCoffee + Constants.wasteWaterConsumptionForCoffee) * numberOfDrink);
		requiredMaterialForDrink.put("Tea", (Constants.teaPowderConsumptionForTea + Constants.wasteTeaPowderConsumptionForTea) * numberOfDrink);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForCoffeeIsTrueAndRestAllFalse(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Integer numberOfDrink=2;
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 100 * numberOfDrink);
		requiredMaterialForDrink.put("Milk", 10000 * numberOfDrink);
		requiredMaterialForDrink.put("Sugar", 8000 * numberOfDrink);
		requiredMaterialForDrink.put("Water", 15000 * numberOfDrink);
		requiredMaterialForDrink.put("Tea", 2000 * numberOfDrink);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForMilkIsFalseAndRestAllTrue(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Integer numberOfDrink=1;
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * numberOfDrink);
		requiredMaterialForDrink.put("Milk", 10000 * 2);
		requiredMaterialForDrink.put("Sugar", 8000 * numberOfDrink);
		requiredMaterialForDrink.put("Water", 15000 * numberOfDrink);
		requiredMaterialForDrink.put("Tea", 2000 * numberOfDrink);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForMilkIsTrueAndRestAllFalse(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Integer numberOfDrink=1;
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 2);
		requiredMaterialForDrink.put("Milk", 10000 * numberOfDrink);
		requiredMaterialForDrink.put("Sugar", 8000 * 2);
		requiredMaterialForDrink.put("Water", 15000 * 2);
		requiredMaterialForDrink.put("Tea", 2000 * 2);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForSugarIsFalseAndRestAllTrue(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 1);
		requiredMaterialForDrink.put("Milk", 10000 * 1);
		requiredMaterialForDrink.put("Sugar", 8000 * 2);
		requiredMaterialForDrink.put("Water", 15000 * 1);
		requiredMaterialForDrink.put("Tea", 2000 * 1);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForSugarIsTrueAndRestAllFalse(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 2);
		requiredMaterialForDrink.put("Milk", 10000 * 2);
		requiredMaterialForDrink.put("Sugar", 8000 * 1);
		requiredMaterialForDrink.put("Water", 15000 * 2);
		requiredMaterialForDrink.put("Tea", 2000 * 2);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForWaterIsFalseAndRestAllTrue(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 1);
		requiredMaterialForDrink.put("Milk", 10000 * 1);
		requiredMaterialForDrink.put("Sugar", 8000 * 1);
		requiredMaterialForDrink.put("Water", 15000 * 2);
		requiredMaterialForDrink.put("Tea", 2000 * 1);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForWaterIsTrueAndRestAllFalse(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 2);
		requiredMaterialForDrink.put("Milk", 10000 * 2);
		requiredMaterialForDrink.put("Sugar", 8000 * 2);
		requiredMaterialForDrink.put("Water", 15000 * 1);
		requiredMaterialForDrink.put("Tea", 2000 * 2);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForTeaIsFalseAndRestAllTrue(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 1);
		requiredMaterialForDrink.put("Milk", 10000 * 1);
		requiredMaterialForDrink.put("Sugar", 8000 * 1);
		requiredMaterialForDrink.put("Water", 15000 * 1);
		requiredMaterialForDrink.put("Tea", 2000 * 2);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldReturnFalseWhenConditionForTeaIsTrueAndRestAllFalse(){
		
		TeaCoffeeMachineMaterial teaCoffeeMachineMaterial = new TeaCoffeeMachineMaterial();
		Map<String,Integer> requiredMaterialForDrink = new HashMap<String, Integer>();
		Boolean expected = false,actual=null;
		requiredMaterialForDrink.put("Coffee", 2000 * 2);
		requiredMaterialForDrink.put("Milk", 10000 * 2);
		requiredMaterialForDrink.put("Sugar", 8000 * 2);
		requiredMaterialForDrink.put("Water", 15000 * 2);
		requiredMaterialForDrink.put("Tea", 2000 * 1);
		DrinkMaterialChecker drinkMaterialChecker = new DrinkMaterialChecker() {
		};
		
		actual = drinkMaterialChecker.checkMaterial(teaCoffeeMachineMaterial, requiredMaterialForDrink);
		
		assertEquals(expected, actual);
	}
}
