package com.kkikkodev.desktop.contact.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.kkikkodev.desktop.contact.controller.ContactManager;
import com.kkikkodev.desktop.contact.model.Contact;

public class DetailDialog extends JDialog {

	private JLabel jlName;
	private JTextField jtfName;
	private JLabel jlAge;
	private JTextField jtfAge;
	private JLabel jlPhone;
	private JTextField jtfPhone;
	private JButton jbConfirm;
	private JButton jbCancel;
	private ContactManager contactManager;
	private int index;

	public DetailDialog(ContactManager contactManager, int index) {
		setModal(true);
		setTitle("연락처 프로그램 - 상세");
		setSize(348, 200);
		setLayout(null);
		initMembers(contactManager, index);
		setEvents();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initMembers(ContactManager contactManager, int index) {
		this.contactManager = contactManager;
		this.index = index;
		Contact contact = contactManager.getContactByIndex(index);
		jlName = new JLabel("이름 : ");
		jlName.setBounds(10, 10, 80, 30);
		add(jlName);
		jtfName = new JTextField(contact.getName());
		jtfName.setBounds(90, 10, 243, 30);
		jtfName.setEditable(false);
		add(jtfName);
		jlAge = new JLabel("나이 : ");
		jlAge.setBounds(10, 50, 80, 30);
		add(jlAge);
		jtfAge = new JTextField("" + contact.getAge());
		jtfAge.setBounds(90, 50, 243, 30);
		jtfAge.setEditable(false);
		add(jtfAge);
		jlPhone = new JLabel("휴대폰 번호 : ");
		jlPhone.setBounds(10, 90, 80, 30);
		add(jlPhone);
		jtfPhone = new JTextField(contact.getPhone());
		jtfPhone.setBounds(90, 90, 243, 30);
		jtfPhone.setEditable(false);
		add(jtfPhone);
		jbConfirm = new JButton("확인");
		jbConfirm.setBounds(10, 130, 100, 30);
		add(jbConfirm);
		jbCancel = new JButton("취소");
		jbCancel.setBounds(233, 130, 100, 30);
		add(jbCancel);
	}

	private void setEvents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jbConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jbCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
