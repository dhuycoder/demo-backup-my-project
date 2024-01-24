package model;

import java.util.Objects;

public class Service {
	private String serviceNumber;
	private String builldingNumber;
	private String serviceName;
	private String servicePrice;
	public Service() {
		// TODO Auto-generated constructor stub
	}
	
	public Service(String serviceNumber, String builldingNumber) {
		super();
		this.serviceNumber = serviceNumber;
		this.builldingNumber = builldingNumber;
	}

	public Service(String serviceNumber, String builldingNumber, String serviceName, String servicePrice) {
		super();
		this.serviceNumber = serviceNumber;
		this.builldingNumber = builldingNumber;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
	}
	public String getServiceNumber() {
		return serviceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		this.serviceNumber = serviceNumber;
	}
	public String getBuilldingNumber() {
		return builldingNumber;
	}
	public void setBuilldingNumber(String builldingNumber) {
		this.builldingNumber = builldingNumber;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(String servicePrice) {
		this.servicePrice = servicePrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(serviceNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		return Objects.equals(serviceNumber, other.serviceNumber);
	}
	

}
