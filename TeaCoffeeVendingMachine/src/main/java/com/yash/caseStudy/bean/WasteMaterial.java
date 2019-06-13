package com.yash.caseStudy.bean;

public class WasteMaterial {
	private Integer wasteTea;
	private Integer wasteCoffee;
	private Integer wasteSugar;
	private Integer wasteWater;
	private Integer wasteMilk;
	
	public WasteMaterial(){
		this.wasteTea=0;
		this.wasteCoffee=0;
		this.wasteSugar=0;
		this.wasteWater=0;
		this.wasteMilk=0;
	}
	
	public Integer getWasteTea() {
		return wasteTea;
	}
	public void setWasteTea(Integer wasteTea) {
		this.wasteTea = wasteTea;
	}
	public Integer getWasteCoffee() {
		return wasteCoffee;
	}
	public void setWasteCoffee(Integer wasteCoffee) {
		this.wasteCoffee = wasteCoffee;
	}
	public Integer getWasteSugar() {
		return wasteSugar;
	}
	public void setWasteSugar(Integer wasteSugar) {
		this.wasteSugar = wasteSugar;
	}
	public Integer getWasteWater() {
		return wasteWater;
	}
	public void setWasteWater(Integer wasteWater) {
		this.wasteWater = wasteWater;
	}
	public Integer getWasteMilk() {
		return wasteMilk;
	}
	public void setWasteMilk(Integer wasteMilk) {
		this.wasteMilk = wasteMilk;
	}

	@Override
	public String toString() {
		return "WasteMaterial [wasteTea=" + wasteTea + ", wasteCoffee=" + wasteCoffee + ", wasteSugar=" + wasteSugar
				+ ", wasteWater=" + wasteWater + ", wasteMilk=" + wasteMilk + "]";
	}

	
}
