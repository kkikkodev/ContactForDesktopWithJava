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

public class UpdateDialog extends JDialog {

	private JLabel jlName;
	private JTextField jtfName;
	private JLabel jlAge;
	private JTextField jtfAge;
	private JLabel jlPhone;
	private JTextField jtfPhone;
	private JButton jbUpdate;
	private JButton jbCancel;
	private ContactManager contactManager;
	private int index;

	public UpdateDialog(ContactManager contactManager, int index) {
		setModal(true);
		setTitle("연락처 프로그램 - 편집");
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
		add(jtfName);
		jlAge = new JLabel("나이 : ");
		jlAge.setBounds(10, 50, 80, 30);
		add(jlAge);
		jtfAge = new JTextField("" + contact.getAge());
		jtfAge.setBounds(90, 50, 243, 30);
		add(jtfAge);
		jlPhone = new JLabel("휴대폰 번호 : ");
		jlPhone.setBounds(10, 90, 80, 30);
		add(jlPhone);
		jtfPhone = new JTextField(contact.getPhone());
		jtfPhone.setBounds(90, 90, 243, 30);
		add(jtfPhone);
		jbUpdate = new JButton("편집");
		jbUpdate.setBounds(10, 130, 100, 30);
		add(jbUpdate);
		jbCancel = new JButton("취소");
		jbCancel.setBounds(233, 130, 100, 30);
		add(jbCancel);
	}

	private void setEvents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jtfName.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbUpdate.doClick();
				}
			}
		});
		jtfAge.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbUpdate.doClick();
				}
			}
		});
		jtfPhone.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbUpdate.doClick();
				}
			}
		});
		jbUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = jtfName.getText();
				String age = jtfAge.getText();
				String phone = jtfPhone.getText();
				if (name.equals("") || age.equals("") || phone.equals("")) {
					JOptionPane.showMessageDialog(null, "빈 항목을 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				contactManager.update(index, new Contact(name, Integer.parseInt(age), phone));
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
