package model;

public class Building {
	private String buidingNumber;
	private String address;
	
	public Building() {
		super();
	}
	public Building(String buidingNumber, String address) {
		super();
		this.buidingNumber = buidingNumber;
		this.address = address;
	}
	public String getBuidingNumber() {
		return buidingNumber;
	}
	public void setBuidingNumber(String buidingNumber) {
		this.buidingNumber = buidingNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
