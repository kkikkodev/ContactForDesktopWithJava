package com.kkikkodev.desktop.contact.view.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.kkikkodev.desktop.contact.controller.ContactManager;
import com.kkikkodev.desktop.contact.model.Contact;
import com.kkikkodev.desktop.contact.view.dialog.AddDialog;
import com.kkikkodev.desktop.contact.view.dialog.DetailDialog;
import com.kkikkodev.desktop.contact.view.dialog.PopupDialog;

public class MainFrame extends JFrame {

	private final String[] HEADER = { "이름", "나이", "휴대폰 번호" };
	private final int NAME_SEARCH_TYPE = 0;
	private final int AGE_SEARCH_TYPE = 1;
	private final int PHONE_SEARCH_TYPE = 2;

	private JComboBox<String> jcbSearchType;
	private JTextField jtfSearchKey;
	private JButton jbSearch;
	private JButton jbShowAll;
	private JButton jbAdd;
	private JScrollPane jspContacts;
	private JTable jtContacts;
	private DefaultTableModel dtmContacts;
	private ContactManager contactManager;
	private Object[][] contactsToPrint;
	private ArrayList<Integer> indexes;

	public MainFrame() {
		setTitle("연락처 프로그램");
		setSize(516, 595);
		setLayout(null);
		initMembers();
		setEvents();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initMembers() {
		contactManager = new ContactManager();
		contactManager.load();
		indexes = new ArrayList<Integer>();
		jcbSearchType = new JComboBox<String>();
		jcbSearchType.setModel(new DefaultComboBoxModel<String>(HEADER));
		jcbSearchType.setBounds(10, 21, 98, 21);
		add(jcbSearchType);
		jtfSearchKey = new JTextField();
		jtfSearchKey.setBounds(113, 21, 171, 23);
		add(jtfSearchKey);
		jbSearch = new JButton("검색");
		jbSearch.setBounds(288, 20, 62, 23);
		add(jbSearch);
		jbShowAll = new JButton("전체보기");
		jbShowAll.setBounds(351, 20, 103, 23);
		add(jbShowAll);
		jbAdd = new JButton("+");
		jbAdd.setFont(new Font("굴림", Font.BOLD, 15));
		jbAdd.setBounds(455, 20, 43, 23);
		add(jbAdd);
		dtmContacts = new DefaultTableModel(contactsToPrint, HEADER) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jtContacts = new JTable(dtmContacts);
		jtContacts.setFont(new Font("굴림", Font.PLAIN, 15));
		jtContacts.setRowHeight(50);
		jspContacts = new JScrollPane(jtContacts);
		jspContacts.setBounds(10, 52, 490, 503);
		add(jspContacts);
	}

	private void setEvents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				contactManager.save();
				dispose();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				if (jtfSearchKey.getText().equals("")) {
					updateAllContacts();
				}
			}
		});
		jtfSearchKey.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					jbSearch.doClick();
				}
			}
		});
		jbSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!jtfSearchKey.getText().equals("")) {
					searchByType(jcbSearchType.getSelectedIndex());
				}
			}
		});
		jbShowAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateAllContacts();
			}
		});
		jbAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddDialog(contactManager);
			}
		});
		jtContacts.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					int index = jtContacts.getSelectedRow();
					if (index != -1) {
						new PopupDialog(contactManager, index, e.getXOnScreen(), e.getYOnScreen());
					}
				} else if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
					new DetailDialog(contactManager, jtContacts.getSelectedRow());
				}
			}
		});
	}

	private void updateAllContacts() {
		indexes = new ArrayList<Integer>();
		for (int i = 0; i < contactManager.getContactsSize(); i++) {
			indexes.add(i);
		}
		makeContactsToPrint(indexes);
		updateTableUI();
	}

	private void updateSearchedContacts() {
		makeContactsToPrint(indexes);
		updateTableUI();
	}

	private void updateTableUI() {
		dtmContacts.setDataVector(contactsToPrint, HEADER);
		dtmContacts.fireTableDataChanged();
		jtfSearchKey.setText("");
		jtContacts.getColumnModel().getColumn(0).setPreferredWidth(160);
		jtContacts.getColumnModel().getColumn(1).setPreferredWidth(80);
		jtContacts.getColumnModel().getColumn(2).setPreferredWidth(250);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		jtContacts.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		jtContacts.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		jtContacts.getColumnModel().getColumn(2).setCellRenderer(dtcr);
	}

	private void makeContactsToPrint(ArrayList<Integer> indexes) {
		contactsToPrint = new Object[indexes.size()][HEADER.length];
		for (int i = 0; i < contactsToPrint.length; i++) {
			Contact contact = contactManager.getContactByIndex(indexes.get(i));
			contactsToPrint[i][0] = contact.getName();
			contactsToPrint[i][1] = contact.getAge();
			contactsToPrint[i][2] = contact.getPhone();
		}
	}

	private void searchByType(int searchType) {
		switch (searchType) {
		case NAME_SEARCH_TYPE:
			indexes = contactManager.searchByName(jtfSearchKey.getText());
			break;
		case AGE_SEARCH_TYPE:
			indexes = contactManager.searchByAge(Integer.parseInt(jtfSearchKey.getText()));
			break;
		case PHONE_SEARCH_TYPE:
			indexes = contactManager.searchByPhone(jtfSearchKey.getText());
			break;
		}
		updateSearchedContacts();
	}
}
