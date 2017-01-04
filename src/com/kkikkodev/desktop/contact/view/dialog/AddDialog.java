package com.kkikkodev.desktop.contact.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.kkikkodev.desktop.contact.controller.ContactManager;
import com.kkikkodev.desktop.contact.model.Contact;

public class AddDialog extends JDialog {

	private JLabel jlName;
	private JTextField jtfName;
	private JLabel jlAge;
	private JTextField jtfAge;
	private JLabel jlPhone;
	private JTextField jtfPhone;
	private JButton jbAdd;
	private JButton jbCancel;
	private ContactManager contactManager;

	public AddDialog(ContactManager contactManager) {
		setModal(true);
		setTitle("����ó ���α׷� - �߰�");
		setSize(348, 200);
		setLayout(null);
		initMembers(contactManager);
		setEvents();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initMembers(ContactManager contactManager) {
		this.contactManager = contactManager;
		jlName = new JLabel("�̸� : ");
		jlName.setBounds(10, 10, 80, 30);
		add(jlName);
		jtfName = new JTextField();
		jtfName.setBounds(90, 10, 243, 30);
		add(jtfName);
		jlAge = new JLabel("���� : ");
		jlAge.setBounds(10, 50, 80, 30);
		add(jlAge);
		jtfAge = new JTextField();
		jtfAge.setBounds(90, 50, 243, 30);
		add(jtfAge);
		jlPhone = new JLabel("�޴��� ��ȣ : ");
		jlPhone.setBounds(10, 90, 80, 30);
		add(jlPhone);
		jtfPhone = new JTextField();
		jtfPhone.setBounds(90, 90, 243, 30);
		add(jtfPhone);
		jbAdd = new JButton("�߰�");
		jbAdd.setBounds(10, 130, 100, 30);
		add(jbAdd);
		jbCancel = new JButton("���");
		jbCancel.setBounds(233, 130, 100, 30);
		add(jbCancel);
	}

	private void setEvents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jtfName.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbAdd.doClick();
				}
			}
		});
		jtfAge.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbAdd.doClick();
				}
			}
		});
		jtfPhone.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbAdd.doClick();
				}
			}
		});
		jbAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = jtfName.getText();
				String age = jtfAge.getText();
				String phone = jtfPhone.getText();
				if (name.equals("") || age.equals("") || phone.equals("")) {
					JOptionPane.showMessageDialog(null, "�� �׸��� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				contactManager.add(new Contact(name, Integer.parseInt(age), phone));
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
