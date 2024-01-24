package model;

import java.time.LocalDate;

public class Member {
	private int memberNumber;
	private String customerNumber;
	private String genderMember;
	private LocalDate memberBirthDay;
	private String memberIdCard;
	private String memberPhoneNumber;
	private String memberHomeTown;
	private String memberName;
	
	public Member(int memberNumber, String customerNumber) {
		super();
		this.memberNumber = memberNumber;
		this.customerNumber = customerNumber;
	}

	public Member(int memberNumber, String customerNumber, String genderMember, LocalDate memberBirthDay,
			String memberIdCard, String memberPhoneNumber, String memberHomeTown,String memberName) {
		super();
		this.memberNumber = memberNumber;
		this.customerNumber = customerNumber;
		this.genderMember = genderMember;
		this.memberBirthDay = memberBirthDay;
		this.memberIdCard = memberIdCard;
		this.memberPhoneNumber = memberPhoneNumber;
		this.memberHomeTown = memberHomeTown;
		this.memberName = memberName;
	}
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getGenderMember() {
		return genderMember;
	}

	public void setGenderMember(String genderMember) {
		this.genderMember = genderMember;
	}

	public LocalDate getMemberBirthDay() {
		return memberBirthDay;
	}

	public void setMemberBirthDay(LocalDate memberBirthDay) {
		this.memberBirthDay = memberBirthDay;
	}

	public String getMemberIdCard() {
		return memberIdCard;
	}

	public void setMemberIdCard(String memberIdCard) {
		this.memberIdCard = memberIdCard;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public String getMemberHomeTown() {
		return memberHomeTown;
	}

	public void setMemberHomeTown(String memberHomeTown) {
		this.memberHomeTown = memberHomeTown;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

}
