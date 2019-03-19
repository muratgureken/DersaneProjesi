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
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class KursAcma extends JFrame{
	JComboBox cmbKurs;
	JLabel lblKurs;
	JLabel lblBalangTarihi;
	JDateChooser dChsrBaslangicTarihi, dChsrBitisTarihi;
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
	JLabel lblAciklama;
	JTextPane textPane;
	JLabel lblIndirimOranyzde;
	JRadioButton rbtnIndirimVar;
	JRadioButton rdbtnYok;
	private JTextField txtIndirimOrani;

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
		cmbKurs.setBounds(105, 57, 155, 20);
		cmbKurs.setModel(new DefaultComboBoxModel(new String[] {"Bir Kurs Se\u00E7iniz...", ".NET Yaz\u0131l\u0131m Uzmanl\u0131\u011F\u0131", "Java ve Android Yaz\u0131l\u0131m", "Grafik Tasar\u0131m", "IOS (Objective C)", "Phyton Programlama", "Microsoft A\u011F ve Sistem", "Web Tasar\u0131m", "Mobil Yaz\u0131l\u0131m Android", "Cisco (CCNA)", "\u0130leri C# 6.0"}));
		getContentPane().add(cmbKurs);


		lblBalangTarihi = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi :");
		lblBalangTarihi.setBounds(294, 60, 88, 14);
		getContentPane().add(lblBalangTarihi);

		dChsrBaslangicTarihi = new JDateChooser();
		dChsrBaslangicTarihi.setBounds(398, 57, 155, 20);
		Date dd = dChsrBaslangicTarihi.getDate();
		getContentPane().add(dChsrBaslangicTarihi);

		lblSaatDilimi = new JLabel("Saat Dilimi : ");
		lblSaatDilimi.setBounds(21, 91, 76, 14);
		getContentPane().add(lblSaatDilimi);

		cmbSaatDilimi = new JComboBox();
		cmbSaatDilimi.setBounds(105, 88, 155, 20);
		cmbSaatDilimi.setModel(new DefaultComboBoxModel(new String[] {"Bir saat se\u00E7in...", "Sabah", "\u00D6\u011Flen", "Ak\u015Fam"}));
		getContentPane().add(cmbSaatDilimi);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(21, 166, 406, 187);
		getContentPane().add(scrollPane_2);

		tableOgrtmn = new JTable(dataOgrtmn,ogrtmColumnNames);
		//tableOgrtmn.setEnabled(false);
		tableOgrtmn.setColumnSelectionAllowed(false);
		tableOgrtmn.setRowSelectionAllowed(true);
		tableOgrtmn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tableOgrtmn);

		JLabel lblretmenListesi = new JLabel("\u00D6\u011Fretmen Listesi");
		lblretmenListesi.setBounds(21, 142, 121, 14);
		getContentPane().add(lblretmenListesi);

		JLabel lblSalonListesi = new JLabel("Salon Listesi");
		lblSalonListesi.setBounds(465, 142, 88, 14);
		getContentPane().add(lblSalonListesi);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(465, 166, 212, 187);
		getContentPane().add(scrollPane_1);

		tableSalon = new JTable(dataSalon,salonColumnNames);
		tableSalon.setColumnSelectionAllowed(false);
		tableSalon.setRowSelectionAllowed(true);
		tableSalon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableSalon);

		lblMinKapasitekii = new JLabel("Minimum Kay\u0131t (Ki\u015Fi) : ");
		lblMinKapasitekii.setBounds(21, 433, 103, 14);
		getContentPane().add(lblMinKapasitekii);

		txtAcmaSarti = new JTextField();
		txtAcmaSarti.setBounds(144, 430, 52, 20);
		getContentPane().add(txtAcmaSarti);
		txtAcmaSarti.setColumns(10);

		btnKursAc = new JButton("KURS A\u00C7");
		btnKursAc.setBackground(SystemColor.textHighlight);
		btnKursAc.setBounds(581, 412, 96, 20);
		btnKursAc.setEnabled(false);
		getContentPane().add(btnKursAc);

		btnSorgula = new JButton("SORGULA");
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnKursAc.setVisible(false);
				//diðer parametreleri kontrol et
				//1.combox'larin index'i 1 ve ustu mu?
				//bitis tarihi baslangi tarihinden sonra mi?
				//hatali olan alanlari kirmizi ile boya
				
				
				//kurs kayitlarini getir.
				//tarih-saat/kurs ve tarih-saat/ogretmen ikilemeleri çikar.
				//sonuclarla, arayuzden girilen tarihler arasinda kesismeyenleri bul.
				//bulunan degerlerden (ogretmen-id) database'den ogretmenleri bul
				//ogretmenleri tabloya yaz
				//bulunan salon-id degerlerinden salon kodlarini cek.
				//salonlari tabloya yaz
			}
		});
		btnSorgula.setBackground(SystemColor.textHighlight);
		btnSorgula.setBounds(589, 70, 88, 20);
		getContentPane().add(btnSorgula);

		lblUyar = new JLabel("Uyar\u0131");
		lblUyar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblUyar.setForeground(Color.RED);
		lblUyar.setBounds(21, 21, 225, 14);
		getContentPane().add(lblUyar);

		JLabel lblIndirim = new JLabel("\u0130ndirim : ");
		lblIndirim.setBounds(21, 378, 46, 14);
		getContentPane().add(lblIndirim);

		rbtnIndirimVar = new JRadioButton("Var");
		rbtnIndirimVar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				System.out.println("1");
				if(rbtnIndirimVar.isSelected())
				{
					rdbtnYok.setSelected(false);
				}
				else
				{
					rdbtnYok.setSelected(true);					
				}	
			}
		});
		rbtnIndirimVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("2");
				if(rbtnIndirimVar.isSelected())
				{
					rdbtnYok.setSelected(false);
				}
				else
				{
					rdbtnYok.setSelected(true);					
				}		
			}
		});
		rbtnIndirimVar.setBounds(90, 374, 52, 23);
		getContentPane().add(rbtnIndirimVar);

		rdbtnYok = new JRadioButton("Yok");
		rdbtnYok.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("3");
				if(rdbtnYok.isSelected())
				{
					rbtnIndirimVar.setSelected(false);
				}
				else
				{
					rbtnIndirimVar.setSelected(true);					
				}
			}
		});
		rdbtnYok.setSelected(true);
		rdbtnYok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				System.out.println("4");
				if(rdbtnYok.isSelected())
				{
					rbtnIndirimVar.setSelected(false);
				}
				else
				{
					rbtnIndirimVar.setSelected(true);					
				}							
			}
		});
		rdbtnYok.setBounds(150, 374, 46, 23);
		getContentPane().add(rdbtnYok);

		txtIndirimOrani = new JTextField();
		txtIndirimOrani.setBounds(144, 404, 52, 20);
		getContentPane().add(txtIndirimOrani);
		txtIndirimOrani.setColumns(10);

		JLabel lblIndirimOranyzde = new JLabel("\u0130ndirim Oran\u0131 (Y\u00FCzde) : ");
		lblIndirimOranyzde.setBounds(21, 406, 121, 14);
		getContentPane().add(lblIndirimOranyzde);

		JLabel lblBitiTarihi = new JLabel("Biti\u015F Tarihi : ");
		lblBitiTarihi.setBounds(294, 91, 88, 14);
		getContentPane().add(lblBitiTarihi);

		dChsrBitisTarihi = new JDateChooser();
		dChsrBitisTarihi.setBounds(398, 88, 155, 20);
		getContentPane().add(dChsrBitisTarihi);

		lblAciklama = new JLabel("A\u00E7\u0131klama : ");
		lblAciklama.setBounds(245, 378, 103, 14);
		getContentPane().add(lblAciklama);

		textPane = new JTextPane();
		textPane.setBounds(245, 403, 285, 47);
		getContentPane().add(textPane);


		//scrollPane.setColumnHeaderView(table);
		//scrollPane_1.setColumnHeaderView(table_1);
	}
}
