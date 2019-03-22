package com.hokumus.course.management.ui.yonetim;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.dom4j.util.UserDataAttribute;

import com.hokumus.course.management.dao.kullanici.UsersDAO;
import com.hokumus.course.management.dao.yonetim.GrupDAO;
import com.hokumus.course.management.dao.yonetim.KursDAO;
import com.hokumus.course.management.model.kullanici.Users;
import com.hokumus.course.management.model.yonetim.Grup;
import com.hokumus.course.management.model.yonetim.Kurs;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.color.CMMException;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;

public class KursListesi extends JFrame{
	private JTable table;
	private JTextField txtKursDurum;
	JComboBox cmbDurum;
	List<Grup> kurslistesi;
	JLabel lblUyari;
	public KursListesi() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 1200, 483);
		KursListesi.this.setDefaultCloseOperation(0);
		setTitle("Kurs Listesi");

		GrupDAO kursdao = new GrupDAO();
		KursDAO kursdao1 = new KursDAO();
		try {
			kurslistesi = kursdao.tumKayitlariGetir(new Grup());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[][] dataKurs = new String[kurslistesi.size()][11];
		String[] columnNames = {"Id", "Ad�", "Ba�lang��", "Biti�", "��retmen", "��renci","Salon","Kapasite", "Durum","G�n","Saat"};

		txtKursDurum = new JTextField();
		txtKursDurum.setEditable(false);
		txtKursDurum.setBounds(148, 391, 127, 20);
		getContentPane().add(txtKursDurum);
		txtKursDurum.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 23, 1139, 314);
		getContentPane().add(scrollPane);

		table = new JTable(dataKurs,columnNames);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		System.out.println("kurs say�s�:"+kurslistesi.size());

		String str1="", str2, strDate;
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
		for(int i=0;i<kurslistesi.size();i++)
		{
			table.setValueAt(Long.toString(kurslistesi.get(i).getId()), i, 0);
			table.setValueAt(kurslistesi.get(i).getKurs().getAdi(), i, 1);
			strDate = dateFormat.format(kurslistesi.get(i).getBaslamaTarihi());  
			table.setValueAt(strDate, i, 2);
			strDate = dateFormat.format(kurslistesi.get(i).getBitisTarihi());  
			table.setValueAt(strDate, i, 3);
			table.setValueAt(kurslistesi.get(i).getOgretmen().getAd()+" "+kurslistesi.get(i).getOgretmen().getSoyad(), i, 4);
			table.setValueAt(Integer.toString(kurslistesi.get(i).getOgrenciSayisi()), i, 5);
			table.setValueAt(kurslistesi.get(i).getSalon().getKod(), i, 6);
			table.setValueAt(Integer.toString(kurslistesi.get(i).getSalon().getKapasite()), i, 7);			
			table.setValueAt(kurslistesi.get(i).getKurs().getDurum(), i, 8);

			if(kurslistesi.get(i).getGun().getGun1()==1)
			{
				str1 = str1 + "1";
			}
			if(kurslistesi.get(i).getGun().getGun2()==1)
			{
				str1 = str1 + "2";
			}
			if(kurslistesi.get(i).getGun().getGun3()==1)
			{
				str1 = str1 + "3";
			}
			if(kurslistesi.get(i).getGun().getGun4()==1)
			{
				str1 = str1 + "4";
			}
			if(kurslistesi.get(i).getGun().getGun5()==1)
			{
				str1 = str1 + "5";
			}
			if(kurslistesi.get(i).getGun().getGun6()==1)
			{
				str1 = str1 + "6";
			}
			if(kurslistesi.get(i).getGun().getGun7()==1)
			{
				str1 = str1 + "7";
			}

			table.setValueAt(str1, i, 9);

			if(kurslistesi.get(i).getGun().getSaat()==0)
			{
				str2 = "Sabah";	
			}
			else if(kurslistesi.get(i).getGun().getSaat()==1)
			{
				str2 = "��len";
			}
			else
			{
				str2 = "Ak�am";
			}

			table.setValueAt(str2, i, 10);
		}

		JButton btnGuncelle = new JButton("G\u00FCncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean islem=true;
				lblUyari.setText("");
				String str="";
				txtKursDurum.setBackground(Color.white);
				cmbDurum.setBackground(Color.white);

				System.out.println("durum kontrol:"+txtKursDurum.getText()+" "+(String)cmbDurum.getSelectedItem()+
						"kosul: "+txtKursDurum.getText().equals((String)cmbDurum.getSelectedItem()));
				if(txtKursDurum.getText()=="Kapal�")
				{
					islem = false;
					str = str+"Kurs kapal�, i�lem yap�lamaz. ";
					txtKursDurum.setBackground(Color.red);
				}
				else
				{
					if(txtKursDurum.getText().equals((String)cmbDurum.getSelectedItem()))
					{
						islem = false;
						str = str+"Kurs durumu ile se�im ayn�! ";						
						txtKursDurum.setBackground(Color.red);
						cmbDurum.setBackground(Color.red);
					}
				}
				if(cmbDurum.getSelectedIndex()==0)
				{
					islem = false;
					str = str+"Durum se�iniz.";
					cmbDurum.setBackground(Color.red);
				}
				lblUyari.setText(str);

				if(islem)
				{
					int returnValue = 0;
					returnValue = JOptionPane.showConfirmDialog(KursListesi.this, "Durum bilgisi g�ncellensin mi?");
					if(returnValue==0)
					{
						Kurs kurs1 = new Kurs();				
						boolean devam=false;
						Long kursId= kurslistesi.get(table.getSelectedRow()).getKurs().getId();
						kurs1.setAdi(kurslistesi.get(table.getSelectedRow()).getKurs().getAdi());
						kurs1.setbaslamaTarihi(kurslistesi.get(table.getSelectedRow()).getKurs().getbaslamaTarihi());
						kurs1.setDurum((String)cmbDurum.getSelectedItem());
						kurs1.setEklemeTarihi(kurslistesi.get(table.getSelectedRow()).getKurs().getEklemeTarihi());
						kurs1.setEkleyen(kurslistesi.get(table.getSelectedRow()).getKurs().getEkleyen());
						kurs1.setFiyat(kurslistesi.get(table.getSelectedRow()).getKurs().getFiyat());
						kurs1.setGuncellemeTarihi(kurslistesi.get(table.getSelectedRow()).getKurs().getGuncellemeTarihi());
						kurs1.setGuncelleyen(kurslistesi.get(table.getSelectedRow()).getKurs().getGuncelleyen());
						kurs1.setId(kursId);
						if(cmbDurum.getSelectedIndex()==3)
						{
							devam=false;
						}
						kurs1.setKayitDurumu(devam);
						try {
							kursdao1.guncelle(kurs1);
							lblUyari.setText("Kurs durum bilgisi g�ncellendi.");
							kurslistesi.get(table.getSelectedRow()).getKurs().setDurum((String)cmbDurum.getSelectedItem());
						} catch (Exception e) {
							lblUyari.setText("Kurs durum bilgisi g�ncellenemedi!...");
							e.printStackTrace();
						}
					}
				}
			}
		});
		btnGuncelle.setBounds(962, 390, 90, 23);
		getContentPane().add(btnGuncelle);

		JLabel lblKursDurumu = new JLabel("Durum G\u00FCncelle :");
		lblKursDurumu.setBounds(334, 394, 97, 14);
		getContentPane().add(lblKursDurumu);

		cmbDurum = new JComboBox();
		cmbDurum.setModel(new DefaultComboBoxModel(new String[] {"Se\u00E7im Yap\u0131n\u0131z...", "Kay\u0131t A\u00E7\u0131k", "Ba\u015Flad\u0131", "Kapal\u0131"}));
		cmbDurum.setBounds(441, 391, 127, 20);
		getContentPane().add(cmbDurum);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 11, 1164, 341);
		getContentPane().add(panel_1);

		Thread kursDurumSorgula = new Thread()
		{
			public void run()
			{								
				for(;;)
				{
					System.out.println("tablo se�im say�s� thread : "+table.getSelectedRows().length);
					if(table.getSelectedRows().length!=0)
					{
						txtKursDurum.setText(kurslistesi.get(table.getSelectedRow()).getKurs().getDurum());
					}
					else
					{
						txtKursDurum.setText("");
					}
				}
			}
		};
		kursDurumSorgula.start();

		JLabel lblKursDurum = new JLabel("Kurs Durum : ");
		lblKursDurum.setBounds(41, 394, 97, 14);
		getContentPane().add(lblKursDurum);

		lblUyari = new JLabel("");
		lblUyari.setForeground(Color.RED);
		lblUyari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUyari.setBounds(605, 394, 347, 14);
		getContentPane().add(lblUyari);

		JButton btnKapat = new JButton("Kapat");
		btnKapat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kursDurumSorgula.stop();
				KursListesi.this.setVisible(false);

			}
		});
		btnKapat.setBounds(1062, 390, 89, 23);
		getContentPane().add(btnKapat);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 363, 1164, 70);
		getContentPane().add(panel);

		JLabel lblKursDurumuGncelleme = new JLabel("Kurs Durumu G\u00FCncelleme");
		lblKursDurumuGncelleme.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblKursDurumuGncelleme);
	}
}
