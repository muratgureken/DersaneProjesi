package com.hokumus.course.management.ui.yonetim;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KursAcma extends JFrame{
	private JTextField textField;
	JComboBox cmbKurs;
	JLabel lblKurs;
	JLabel lblSre;
	JLabel lblBalangTarihi;
	JDateChooser dChsrBaslangicTarihi;
	private JLabel lblSaatDilimi;
	private JComboBox cmbSaatDilimi;
	private JTable table;
	JScrollPane scrollPane;
	String[] ogrtmColumnNames = {"Adi","Soyadi","Telefon","email"};
	String[] salonColumnNames = {"Salon Kodu","Kapasite"};
	private JTable tableOgrtmn;
	JScrollPane scrollPane_2;
	private JTable tableSalon;
	JScrollPane scrollPane_1;
	private JLabel lblMinKapasitekii;
	private JTextField txtAcmaSarti;
	private JButton btnKursAc;
	JLabel lblUyar;
	JButton btnSorgula;
	
	public KursAcma() {
		setBackground(SystemColor.desktop);
		//setEnabled(false);

		String[][] dataOgrtmn = null;
		dataOgrtmn = new String[50][4];
		String[][] dataSalon = null;
		dataSalon = new String[50][2];
		getContentPane().setLayout(null);
		
		lblKurs = new JLabel("Kurs :");
		lblKurs.setBounds(21, 60, 46, 14);
		getContentPane().add(lblKurs);

		cmbKurs = new JComboBox();
		cmbKurs.setBounds(116, 57, 155, 20);
		cmbKurs.setModel(new DefaultComboBoxModel(new String[] {"Bir Kurs Se\u00E7iniz...", ".NET Yaz\u0131l\u0131m Uzmanl\u0131\u011F\u0131", "Java ve Android Yaz\u0131l\u0131m", "Grafik Tasar\u0131m", "IOS (Objective C)", "Phyton Programlama", "Microsoft A\u011F ve Sistem", "Web Tasar\u0131m", "Mobil Yaz\u0131l\u0131m Android", "Cisco (CCNA)", "\u0130leri C# 6.0"}));
		getContentPane().add(cmbKurs);

		lblSre = new JLabel("S\u00FCre :");
		lblSre.setBounds(21, 91, 46, 14);
		getContentPane().add(lblSre);

		textField = new JTextField();
		textField.setBounds(116, 88, 155, 20);
		textField.setEditable(false);
		getContentPane().add(textField);
		textField.setColumns(10);


		lblBalangTarihi = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi :");
		lblBalangTarihi.setBounds(21, 125, 88, 14);
		getContentPane().add(lblBalangTarihi);

		dChsrBaslangicTarihi = new JDateChooser();
		dChsrBaslangicTarihi.setDateFormatString("yyyy.MM.dd");
		dChsrBaslangicTarihi.setBounds(116, 119, 155, 20);
		Date dd = dChsrBaslangicTarihi.getDate();
		getContentPane().add(dChsrBaslangicTarihi);

		lblSaatDilimi = new JLabel("Saat Dilimi : ");
		lblSaatDilimi.setBounds(21, 153, 76, 14);
		getContentPane().add(lblSaatDilimi);

		cmbSaatDilimi = new JComboBox();
		cmbSaatDilimi.setBounds(116, 150, 155, 20);
		cmbSaatDilimi.setModel(new DefaultComboBoxModel(new String[] {"Bir saat se\u00E7in...", "Sabah", "\u00D6\u011Flen", "Ak\u015Fam"}));
		getContentPane().add(cmbSaatDilimi);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(313, 57, 211, 187);
		getContentPane().add(scrollPane_2);
		
		tableOgrtmn = new JTable(dataOgrtmn,ogrtmColumnNames);
		//tableOgrtmn.setEnabled(false);
		tableOgrtmn.setColumnSelectionAllowed(false);
		tableOgrtmn.setRowSelectionAllowed(true);
		tableOgrtmn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tableOgrtmn);
		
		JLabel lblretmenListesi = new JLabel("\u00D6\u011Fretmen Listesi");
		lblretmenListesi.setBounds(313, 33, 121, 14);
		getContentPane().add(lblretmenListesi);
		
		JLabel lblSalonListesi = new JLabel("Salon Listesi");
		lblSalonListesi.setBounds(542, 33, 88, 14);
		getContentPane().add(lblSalonListesi);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(542, 57, 155, 187);
		getContentPane().add(scrollPane_1);
		
		tableSalon = new JTable(dataSalon,salonColumnNames);
		tableSalon.setColumnSelectionAllowed(false);
		tableSalon.setRowSelectionAllowed(true);
		tableSalon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableSalon);
		
		lblMinKapasitekii = new JLabel("A\u00E7ma \u015Eart\u0131 (Ki\u015Fi) : ");
		lblMinKapasitekii.setBounds(21, 188, 88, 14);
		getContentPane().add(lblMinKapasitekii);
		
		txtAcmaSarti = new JTextField();
		txtAcmaSarti.setBounds(116, 185, 155, 20);
		getContentPane().add(txtAcmaSarti);
		txtAcmaSarti.setColumns(10);
		
		btnKursAc = new JButton("KURS A\u00C7");
		btnKursAc.setBackground(SystemColor.textHighlight);
		btnKursAc.setBounds(183, 216, 88, 28);
		btnKursAc.setEnabled(false);
		getContentPane().add(btnKursAc);
		
		btnSorgula = new JButton("SORGULA");
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnKursAc.setVisible(false);
			}
		});
		btnSorgula.setBackground(SystemColor.textHighlight);
		btnSorgula.setBounds(85, 216, 88, 28);
		getContentPane().add(btnSorgula);
		
		lblUyar = new JLabel("Uyar\u0131");
		lblUyar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblUyar.setForeground(Color.RED);
		lblUyar.setBounds(21, 21, 225, 14);
		getContentPane().add(lblUyar);
		



		//scrollPane.setColumnHeaderView(table);
		//scrollPane_1.setColumnHeaderView(table_1);
	}
}
