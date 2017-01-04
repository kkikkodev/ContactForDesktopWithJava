package com.kkikkodev.desktop.contact.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.WindowConstants;

import com.kkikkodev.desktop.contact.controller.ContactManager;

public class PopupDialog extends JDialog {

	private JButton jbDetail;
	private JButton jbUpdate;
	private JButton jbDelete;
	private ContactManager contactManager;
	private int index;

	public PopupDialog(ContactManager contactManager, int index, int leftTopX, int leftTopY) {
		setModal(true);
		setBounds(leftTopX, leftTopY, 132, 150);
		setLayout(null);
		initMembers(contactManager, index);
		setEvents();
		setResizable(false);
		setVisible(true);
	}

	private void initMembers(ContactManager contactManager, int index) {
		this.contactManager = contactManager;
		this.index = index;
		jbDetail = new JButton("상세");
		jbDetail.setBounds(12, 8, 100, 30);
		add(jbDetail);
		jbUpdate = new JButton("편집");
		jbUpdate.setBounds(12, 46, 100, 30);
		add(jbUpdate);
		jbDelete = new JButton("삭제");
		jbDelete.setBounds(12, 84, 100, 30);
		add(jbDelete);
	}

	private void setEvents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		jbDetail.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DetailDialog(contactManager, index);
			}
		});
		jbUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UpdateDialog(contactManager, index);
			}
		});
		jbDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contactManager.delete(index);
				dispose();
			}
		});
	}
}
