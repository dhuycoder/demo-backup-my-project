package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class phong implements Comparable<phong> {
	private String roomNumber;
	private String price;
	private String roomType;
	private String buildingNumber;
	private String roomState;
	
	public phong() {
		super();
	}
	
	public phong(String roomNumber, String price, String roomType, String buildingNumber,String roomState) {
		super();
		this.roomNumber = roomNumber;
		this.price = price;
		this.roomType = roomType;
		this.buildingNumber = buildingNumber;
		this.roomState = roomState;
	}
	
	public phong(String roomNumber) {
		super();
		this.roomNumber = roomNumber;
	}

	
	public String getRoomState() {
		return roomState;
	}

	public void setRoomState(String roomState) {
		this.roomState = roomState;
	}

	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String d) {
		this.price = d;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getBuildingNumber() {
		return buildingNumber;
	}


	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}

	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(buildingNumber, price, roomNumber, roomState, roomType);
	}

	public boolean equals1(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		phong other = (phong) obj;
		return Objects.equals(buildingNumber, other.buildingNumber)
				&& Objects.equals(roomNumber, other.roomNumber) && Objects.equals(roomState, other.roomState)
				&& Objects.equals(roomType, other.roomType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		phong other = (phong) obj;
		return Objects.equals(roomNumber, other.roomNumber);
	}
	
	@Override
	public String toString() {
		return "phong [roomNumber=" + roomNumber + ", price=" + price + ", roomType=" + roomType + ", buildingNumber="
				+ buildingNumber + "]";
	}

	@Override
	public int compareTo(phong o) {
		return this.roomNumber.compareTo(o.roomNumber);
	}
	
}
