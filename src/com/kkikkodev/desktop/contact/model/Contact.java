package com.kkikkodev.desktop.contact.model;

public class Contact {

	private String name;
	private int age;
	private String phone;

	public Contact() {
		this.name = "�̸�����";
		this.age = 1;
		this.phone = "�޴�������";
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
		System.out.println("[����ó ���� ���]");
		System.out.println("�̸� : " + name);
		System.out.println("���� : " + age);
		System.out.println("�޴��� ��ȣ : " + phone);
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
