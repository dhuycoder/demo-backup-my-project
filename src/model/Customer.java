package model;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
	private String customerNumber;
	private String buildingNumber;
	private String roomNumber;
	private String customerName;
	private String customerGender;
	private String customerPhoneNumber;
	private String customerIdCard;
	private LocalDate customerBirthDay;
	private String customerHomeTown;
	private LocalDate customerArrivalDate;
	
	public Customer(String customerNumber, String buildingNumber) {
		super();
		this.customerNumber = customerNumber;
		this.buildingNumber = buildingNumber;
	}


	public Customer(String customerNumber, String buildingNumber, String roomNumber, String customerName,
			String customerGender, String customerIdCard, LocalDate customerBirthDay, String customerHomeTown,
			LocalDate customerArrivalDate,String phoneNumber) {
		super();
		this.customerNumber = customerNumber;
		this.buildingNumber = buildingNumber;
		this.roomNumber = roomNumber;
		this.customerName = customerName;
		this.customerGender = customerGender;
		this.customerIdCard = customerIdCard;
		this.customerBirthDay = customerBirthDay;
		this.customerHomeTown = customerHomeTown;
		this.customerArrivalDate = customerArrivalDate;
		this.customerPhoneNumber = phoneNumber;
	}
	

	public Customer() {
	}


	public String getCustomerNumber() {
		return customerNumber;
	}


	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}


	public String getBuildingNumber() {
		return buildingNumber;
	}


	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}


	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerGender() {
		return customerGender;
	}


	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}


	public String getCustomerIdCard() {
		return customerIdCard;
	}


	public void setCustomerIdCard(String customerIdCard) {
		this.customerIdCard = customerIdCard;
	}


	public LocalDate getCustomerBirthDay() {
		return customerBirthDay;
	}


	public void setCustomerBirthDay(LocalDate customerBirthDay) {
		this.customerBirthDay = customerBirthDay;
	}


	public String getCustomerHomeTown() {
		return customerHomeTown;
	}


	public void setCustomerHomeTown(String customerHomeTown) {
		this.customerHomeTown = customerHomeTown;
	}


	public LocalDate getCustomerArrivalDate() {
		return customerArrivalDate;
	}


	public void setCustomerArrivalDate(LocalDate customerArrivalDate) {
		this.customerArrivalDate = customerArrivalDate;
	}


	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}


	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(buildingNumber, other.buildingNumber)
				&& Objects.equals(customerNumber, other.customerNumber);
	}
	
	
}
