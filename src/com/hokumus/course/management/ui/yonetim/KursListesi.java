package com.hokumus.course.management.ui.yonetim;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class KursListesi extends JFrame{
	private JTable table;
	public KursListesi() {
		getContentPane().setLayout(null);
		setTitle("Kurs Listesi");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 23, 711, 305);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id", "Adý", "Baþlangýç", "Bitiþ", "Öðretmen", "Salon", "Öðrenci Sayýsý","Kapasite", "Durum"
			}
		));
		
		JButton btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.setBounds(326, 390, 143, 23);
		getContentPane().add(btnGuncelle);
		
		JLabel lblKursDurumu = new JLabel("Kurs Durumu : ");
		lblKursDurumu.setBounds(42, 394, 97, 14);
		getContentPane().add(lblKursDurumu);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Kay\u0131t A\u00E7\u0131k", "Ba\u015Flad\u0131", "Kapat"}));
		comboBox.setBounds(146, 391, 110, 20);
		getContentPane().add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 11, 737, 341);
		getContentPane().add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 363, 737, 64);
		getContentPane().add(panel);
	}
}
