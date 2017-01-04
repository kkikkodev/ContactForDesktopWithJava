package com.kkikkodev.desktop.contact.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import com.kkikkodev.desktop.contact.model.Contact;

public class ContactManager {

	private static final String DIRECTORY_NAME = "res";
	private static final String FILE_PATH = ".\\" + DIRECTORY_NAME + "\\contact_list.txt";

	private LinkedList<Contact> contacts;

	public ContactManager() {
		contacts = new LinkedList<Contact>();
	}

	public boolean add(Contact contact) {
		return contacts.add(contact);
	}

	public ArrayList<Integer> searchByName(String name) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getName().equals(name)) {
				indexes.add(i);
			}
		}
		return indexes;
	}

	public ArrayList<Integer> searchByAge(int age) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getAge() == age) {
				indexes.add(i);
			}
		}
		return indexes;
	}

	public ArrayList<Integer> searchByPhone(String phone) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getPhone().equals(phone)) {
				indexes.add(i);
				return indexes;
			}
		}
		return indexes;
	}

	public void printAll() {
		System.out.println("[연락처 정보 전체 출력 (총 " + contacts.size() + " 명)]");
		for (Contact contact : contacts) {
			contact.print();
		}
		System.out.println();
	}

	public Contact getContactByIndex(int index) {
		return contacts.get(index);
	}

	public void delete(int index) {
		contacts.remove(index);
	}

	public void update(int index, Contact contact) {
		contacts.set(index, contact);
	}

	public void save() {
		File directory = new File(DIRECTORY_NAME);
		if (!directory.exists()) {
			directory.mkdir();
		} else {
			File file = new File(FILE_PATH);
			if (file.exists()) {
				file.delete();
			}
		}
		try {
			PrintWriter pw = new PrintWriter(FILE_PATH);
			for (Contact contact : contacts) {
				pw.println(contact);
			}
			pw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean load() {
		if (!new File(FILE_PATH).exists()) {
			System.out.println(FILE_PATH + " 파일이 없어서 로드할 수 없습니다.");
			return false;
		}
		try {
			clear();
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				String[] splittedData = line.split("/");
				String name = splittedData[0];
				int age = Integer.parseInt(splittedData[1]);
				String phone = splittedData[2];
				add(new Contact(name, age, phone));
			}
			br.close();
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getContactsSize() {
		return contacts.size();
	}

	private void clear() {
		contacts.clear();
	}
}
