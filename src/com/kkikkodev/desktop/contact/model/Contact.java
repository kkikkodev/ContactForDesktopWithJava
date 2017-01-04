package com.kkikkodev.desktop.contact.model;

public class Contact {

	private String name;
	private int age;
	private String phone;

	public Contact() {
		this.name = "이름미정";
		this.age = 1;
		this.phone = "휴대폰미정";
	}

	public Contact(String name, int age, String phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}

	public Contact(Contact otherCompanyContact) {
		this.name = otherCompanyContact.name;
		this.age = otherCompanyContact.age;
		this.phone = otherCompanyContact.phone;
	}

	public void print() {
		System.out.println("[연락처 정보 출력]");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("휴대폰 번호 : " + phone);
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return name + "/" + age + "/" + phone;
	}
}
