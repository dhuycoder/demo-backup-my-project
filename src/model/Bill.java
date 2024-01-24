package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bill {
	private int billNumber;
	private int accountNumber;
	private String buildingNumber;
	private String roomNumber;
	private LocalDate invoiceDate;
	private String billPrice;
	private String customerNumber;
	private int electricNumber;
	
	
	public Bill(int billNumber) {
		super();
		this.billNumber = billNumber;
		
	}
	
	
	
	public Bill(int billNumber, int accountNumber, String buildingNumber, String roomNumber, LocalDate invoiceDate,
			String billPrice, String customerNumber, int electricNumber) {
		super();
		this.billNumber = billNumber;
		this.accountNumber = accountNumber;
		this.buildingNumber = buildingNumber;
		this.roomNumber = roomNumber;
		this.invoiceDate = invoiceDate;
		this.billPrice = billPrice;
		this.customerNumber = customerNumber;
		this.electricNumber = electricNumber;
	}



	public Bill(int accountNumber, String buildingNumber, String roomNumber, LocalDate invoiceDate, String billPrice,
			String customerNumber, int electricNumber) {
	
		this.accountNumber = accountNumber;
		this.buildingNumber = buildingNumber;
		this.roomNumber = roomNumber;
		this.invoiceDate = invoiceDate;
		this.billPrice = billPrice;
		this.customerNumber = customerNumber;
		this.electricNumber = electricNumber;
	}



	public String getCustomerNumber() {
		return customerNumber;
	}



	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}



	public int getElectricNumber() {
		return electricNumber;
	}



	public void setElectricNumber(int electricNumber) {
		this.electricNumber = electricNumber;
	}



	public Bill(int billNumber, int accountNumber, String buildingNumber, String roomNumber, LocalDate invoiceDate,
			String billPrice) {
		super();
		this.billNumber = billNumber;
		this.accountNumber = accountNumber;
		this.buildingNumber = buildingNumber;
		this.roomNumber = roomNumber;
		this.invoiceDate = invoiceDate;
		this.billPrice = billPrice;
	}

	public Bill() {
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
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

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getBillPrice() {
		return billPrice;
	}

	public void setBillPrice(String billPrice) {
		this.billPrice = billPrice;
	}

	
}
