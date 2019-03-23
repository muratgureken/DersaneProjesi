package com.hokumus.course.management.ui.yonetim;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hokumus.course.management.dao.yonetim.SalonDAO;
import com.hokumus.course.management.model.yonetim.Salon;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Font;
import com.hokumus.course.management.util.CourseUtils;

public class SalonAcma extends JFrame{
	private JTable tableGuncelle;
	JScrollPane scrollPane;
	JButton btnYeniSalonAc, btnSalonBilgileriniGncelle;
	private JButton btnSalonlargster;
	private JLabel lblAdI;
	private JLabel lblKodu;
	private JLabel lblKapasite;
	private JTextField txtAd;
	private JTextField txtKod;
	private JTextField txtKapasite;
	private JButton btnTamam;
	private JButton btnVazgec;
	List<Salon> salonliste;
	int tabloBoy;
	private JLabel lblUyari;
	int secim=0;
	String[] columnNames = {"Id", "Adý", "Kodu", "Kapasite"};

	public SalonAcma() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 547, 372);
		setTitle("Salon Açma");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 65, 409, 152);
		getContentPane().add(scrollPane);

		SalonDAO salondao = new SalonDAO();
		try {
			salonliste = salondao.tumKayitlariGetir(new Salon());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String[][] dataSalon = new String[salonliste.size()][4];

		tableGuncelle = new JTable(dataSalon,columnNames);
		scrollPane.setViewportView(tableGuncelle);
		tableGuncelle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableGuncelle.setVisible(false);
		tableGuncelle.setEnabled(false);
		scrollPane.setVisible(false);
		tabloBoy = salonliste.size();
		System.out.println("salon boyutu:"+salonliste.size());

		for(int i=0;i<salonliste.size();i++)
		{
			System.out.println("salon degerleri:"+salonliste.get(i).getId()+" "+
					salonliste.get(i).getAdi()+" "+salonliste.get(i).getKod()+" "+salonliste.get(i).getKapasite());
			tableGuncelle.setValueAt(Long.toString(salonliste.get(i).getId()), i, 0);							
			tableGuncelle.setValueAt(salonliste.get(i).getAdi(), i, 1);							
			tableGuncelle.setValueAt(salonliste.get(i).getKod(), i, 2);							
			tableGuncelle.setValueAt(Integer.toString(salonliste.get(i).getKapasite()), i, 3);							
		}

		btnSalonBilgileriniGncelle = new JButton("Salon G\u00FCncelle");
		btnSalonBilgileriniGncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUyari.setText("");
				secim = 1;
				tableGuncelle.setVisible(true);
				tableGuncelle.setEnabled(true);
				scrollPane.setVisible(true);
				btnTamam.setVisible(true);
				btnVazgec.setVisible(true);
				lblAdI.setVisible(false);
				lblKapasite.setVisible(false);
				lblKodu.setVisible(false);
				txtAd.setVisible(false);
				txtKapasite.setVisible(false);
				txtKod.setVisible(false);
				
				for(int i=0;i<salonliste.size();i++)
				{
					System.out.println("salon degerleri:"+salonliste.get(i).getId()+" "+
							salonliste.get(i).getAdi()+" "+salonliste.get(i).getKod()+" "+salonliste.get(i).getKapasite());
					tableGuncelle.setValueAt(Long.toString(salonliste.get(i).getId()), i, 0);							
					tableGuncelle.setValueAt(salonliste.get(i).getAdi(), i, 1);							
					tableGuncelle.setValueAt(salonliste.get(i).getKod(), i, 2);							
					tableGuncelle.setValueAt(Integer.toString(salonliste.get(i).getKapasite()), i, 3);							
				}
			}
		});
		btnSalonBilgileriniGncelle.setBounds(181, 11, 145, 23);
		getContentPane().add(btnSalonBilgileriniGncelle);

		btnYeniSalonAc = new JButton("Yeni Salon A\u00E7");
		btnYeniSalonAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secim = 2;
				lblUyari.setText("");
				tableGuncelle.setVisible(true);
				tableGuncelle.setEnabled(false);
				scrollPane.setVisible(true);
				btnTamam.setVisible(true);
				btnVazgec.setVisible(true);
				lblAdI.setVisible(true);
				lblKapasite.setVisible(true);
				lblKodu.setVisible(true);
				txtAd.setVisible(true);
				txtKapasite.setVisible(true);
				txtKod.setVisible(true);				
			}
		});
		btnYeniSalonAc.setBounds(336, 11, 145, 23);
		getContentPane().add(btnYeniSalonAc);

		btnSalonlargster = new JButton("Salonlar\u0131 G\u00F6ster");
		btnSalonlargster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUyari.setText("");
				tableGuncelle.setVisible(true);
				tableGuncelle.setEnabled(false);
				scrollPane.setVisible(true);
				btnTamam.setVisible(false);
				btnVazgec.setVisible(false);
				lblAdI.setVisible(false);
				lblKapasite.setVisible(false);
				lblKodu.setVisible(false);
				txtAd.setVisible(false);
				txtKapasite.setVisible(false);
				txtKod.setVisible(false);
			}
		});
		btnSalonlargster.setBounds(26, 11, 145, 23);
		getContentPane().add(btnSalonlargster);

		lblAdI = new JLabel("Ad\u0131 : ");
		lblAdI.setBounds(52, 239, 63, 14);
		getContentPane().add(lblAdI);

		lblKodu = new JLabel("Kodu : ");
		lblKodu.setBounds(52, 264, 63, 14);
		getContentPane().add(lblKodu);

		lblKapasite = new JLabel("Kapasite : ");
		lblKapasite.setBounds(52, 289, 63, 14);
		getContentPane().add(lblKapasite);

		txtAd = new JTextField();
		txtAd.setBounds(126, 236, 109, 20);
		getContentPane().add(txtAd);
		txtAd.setColumns(10);

		txtKod = new JTextField();
		txtKod.setBounds(126, 261, 109, 20);
		getContentPane().add(txtKod);
		txtKod.setColumns(10);

		txtKapasite = new JTextField();
		txtKapasite.setBounds(126, 286, 109, 20);
		getContentPane().add(txtKapasite);
		txtKapasite.setColumns(10);

		btnTamam = new JButton("Tamam");
		btnTamam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Salon salon1 = new Salon();
				int returnValue;
				if(secim==1)
				{
					returnValue = JOptionPane.showConfirmDialog(SalonAcma.this, "Salon bilgileri güncellensin mi?");

					if(returnValue==0)
					{
						for(int i=0;i<tabloBoy;i++)
						{
							salon1.setId(salonliste.get(i).getId());
							salon1.setAdi((String)tableGuncelle.getValueAt(i, 1));
							salon1.setEklemeTarihi(new Date());
							salon1.setEkleyen("");
							salon1.setGuncellemeTarihi(new Date());
							salon1.setGuncelleyen(CourseUtils.userName);
							salon1.setKapasite(Integer.parseInt((String)tableGuncelle.getValueAt(i, 3)));
							salon1.setKayitDurumu(true);
							salon1.setKod((String)tableGuncelle.getValueAt(i, 2));
							try {
								salondao.guncelle(salon1);
								lblUyari.setText("Salonlar güncellendi.");
							} catch (Exception e1) {
								lblUyari.setText("Güncelleme baþarýsýz!...");
								e1.printStackTrace();
							}
						}
					}
				}
				else if(secim==2)
				{
					returnValue = JOptionPane.showConfirmDialog(SalonAcma.this, "Yeni salon eklensin mi?");

					if(returnValue==0)
					{
						salon1.setAdi(txtAd.getText());
						salon1.setEklemeTarihi(new Date());
						salon1.setEkleyen("CourseUtils.userName");
						salon1.setGuncellemeTarihi(new Date());
						salon1.setGuncelleyen("");
						salon1.setKapasite(Integer.parseInt(txtKapasite.getText()));
						salon1.setKayitDurumu(true);
						salon1.setKod(txtKod.getText());
						
						salonliste.add(salon1);
						try {
							salondao.kaydet(salon1);
							lblUyari.setText("Salon eklendi.");
							//tabloyu guncelle
							/*int i=salonliste.size() - 1;
							System.out.println("güncelle size:"+(i+1));
							tableGuncelle.setSize(i+1, 4);
							tableGuncelle.setValueAt(Long.toString(salonliste.get(i).getId()), i, 0);							
							tableGuncelle.setValueAt(salonliste.get(i).getAdi(), i, 1);							
							tableGuncelle.setValueAt(salonliste.get(i).getKod(), i, 2);							
							tableGuncelle.setValueAt(Integer.toString(salonliste.get(i).getKapasite()), i, 3);	
							System.out.println("yeni salon:"+salonliste.get(i).getId()+" "+salonliste.get(i).getAdi()+" "+
							" "+salonliste.get(i).getKod());
							tableGuncelle.repaint();*/
						} catch (Exception e1) {
							lblUyari.setText("Salon ekleme baþarýz!...");
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btnTamam.setBounds(273, 285, 89, 23);
		btnTamam.setVisible(false);
		getContentPane().add(btnTamam);

		btnVazgec = new JButton("Vazge\u00E7");
		btnVazgec.setBounds(372, 285, 89, 23);
		btnVazgec.setVisible(false);
		getContentPane().add(btnVazgec);

		lblUyari = new JLabel("");
		lblUyari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUyari.setForeground(Color.RED);
		lblUyari.setBounds(273, 239, 188, 14);
		getContentPane().add(lblUyari);

		btnTamam.setVisible(false);
		btnVazgec.setVisible(false);
		lblAdI.setVisible(false);
		lblKapasite.setVisible(false);
		lblKodu.setVisible(false);
		txtAd.setVisible(false);
		txtKapasite.setVisible(false);
		txtKod.setVisible(false);
	}

}
