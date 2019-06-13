package com.yash.caseStudy.bean;

public class TeaCoffeeMachineMaterial {

	private Integer teaContainer;
	private Integer coffeeContainer;
	private Integer sugarContainer;
	private Integer waterContainer;
	private Integer milkContainer;

	public TeaCoffeeMachineMaterial(){
		this.teaContainer = 2000;
		this.coffeeContainer = 2000;
		this.sugarContainer = 8000;
		this.waterContainer = 15000;
		this.milkContainer = 10000;
	}

	

	public Integer getTeaContainer() {
		return teaContainer;
	}

	public void setTeaContainer(Integer teaContainer) {
		this.teaContainer = teaContainer;
	}

	public Integer getCoffeeContainer() {
		return coffeeContainer;
	}

	public void setCoffeeContainer(Integer coffeeContainer) {
		this.coffeeContainer = coffeeContainer;
	}

	public Integer getSugarContainer() {
		return sugarContainer;
	}

	public void setSugarContainer(Integer sugarContainer) {
		this.sugarContainer = sugarContainer;
	}

	public Integer getWaterContainer() {
		return waterContainer;
	}

	public void setWaterContainer(Integer waterContainer) {
		this.waterContainer = waterContainer;
	}

	public Integer getMilkContainer() {
		return milkContainer;
	}

	public void setMilkContainer(Integer milkContainer) {
		this.milkContainer = milkContainer;
	}

	@Override
	public String toString() {
		return "TeaCoffeeMachine [teaContainer=" + teaContainer + ", coffeeContainer=" + coffeeContainer
				+ ", sugarContainer=" + sugarContainer + ", waterContainer=" + waterContainer + ", milkContainer="
				+ milkContainer + "]";
	}


}
