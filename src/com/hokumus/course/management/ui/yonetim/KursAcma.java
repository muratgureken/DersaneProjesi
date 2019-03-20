package com.hokumus.course.management.ui.yonetim;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.hokumus.course.management.dao.ik.OgretmenDAO;
import com.hokumus.course.management.dao.kullanici.UsersDAO;
import com.hokumus.course.management.dao.yonetim.GrupDAO;
import com.hokumus.course.management.dao.yonetim.GunDAO;
import com.hokumus.course.management.dao.yonetim.KursDAO;
import com.hokumus.course.management.dao.yonetim.SalonDAO;
import com.hokumus.course.management.model.ik.Ogretmen;
import com.hokumus.course.management.model.kullanici.Users;
import com.hokumus.course.management.model.yonetim.Grup;
import com.hokumus.course.management.model.yonetim.Gun;
import com.hokumus.course.management.model.yonetim.Kurs;
import com.hokumus.course.management.model.yonetim.Salon;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class KursAcma extends JFrame{
	JComboBox cmbKurs;
	JLabel lblKurs;
	JLabel lblBalangTarihi;
	JDateChooser dChsrBaslangicTarihi, dChsrBitisTarihi;
	private JTable table;
	JScrollPane scrollPane;
	String[] ogrtmColumnNames = {"Id","Ad Soyad","Pzt","Salý","Çrþ","Prþ","Cuma","Cmt","Pzr"};
	String[] salonColumnNames = {"Id","Salon Kodu","Pzt","Salý","Çrþ","Prþ","Cuma","Cmt","Pzr"};
	private JTable tableOgrtmn;
	JScrollPane scrollPane_2;
	private JTable tableSalon;
	JScrollPane scrollPane_1;
	private JLabel lblMinKapasitekii;
	private JTextField txtAcmaSarti;
	private JButton btnKursAc;
	JLabel lblUyar;
	JButton btnSorgula;
	JLabel lblIndirimOranyzde;
	JRadioButton rbtnIndirimVar;
	JRadioButton rdbtnYok;
	private JTextField txtIndirimOrani;
	List<Ogretmen> listeogr;
	List<Salon> listesalon;
	List<Kurs> listekurs;
	List<Gun> listegun;
	List<Grup> listegrup;
	LinkedList<Long> UnqOgrtId = new LinkedList<Long>();
	LinkedList<Long> UnqSlnId = new LinkedList<Long>();
	int basTarihi, bitTarihi;
	private JTable tableGunSecimi;
	private JLabel lblGnSeimi;
	private JLabel lblUyari;
	private JPanel panel_1;
	private JLabel lblSorgulamaSonucu;
	private JPanel panel;
	private JLabel lblSorgulama;
	private JLabel lblretmen;
	private JComboBox comboBox;
	private JPanel panel_2;
	private JLabel lblSeim;
	Date datebas = new Date();
	Date datebit = new Date();
	String[][] dataSal;
	String[][] dataOgr;
	String saatTanim="SÖA";
	
	public KursAcma() {
		setTitle("Kurs A\u00E7ma");
		setBackground(SystemColor.desktop);
		//setEnabled(false);

		OgretmenDAO ogrdao = new OgretmenDAO();
		KursDAO kursdao = new KursDAO();
		GunDAO gundao = new GunDAO();
		GrupDAO grupdao = new GrupDAO();
		SalonDAO slndao = new SalonDAO();

		String[][] dataOgrtmn = null;
		dataOgrtmn = new String[50][9];
		String[][] dataSalon = null;
		dataSalon = new String[50][9];
		getContentPane().setLayout(null);

		lblKurs = new JLabel("Kurs :");
		lblKurs.setBounds(21, 39, 46, 14);
		getContentPane().add(lblKurs);

		cmbKurs = new JComboBox();
		cmbKurs.setBounds(71, 36, 155, 20);
		cmbKurs.setModel(new DefaultComboBoxModel(new String[] {"Bir Kurs Se\u00E7iniz...", ".NET Yaz\u0131l\u0131m Uzmanl\u0131\u011F\u0131", "Java ve Android Yaz\u0131l\u0131m", "Grafik Tasar\u0131m", "IOS (Objective C)", "Phyton Programlama", "Microsoft A\u011F ve Sistem", "Web Tasar\u0131m", "Mobil Yaz\u0131l\u0131m Android", "Cisco (CCNA)", "\u0130leri C# 6.0"}));
		getContentPane().add(cmbKurs);

		lblBalangTarihi = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi :");
		lblBalangTarihi.setBounds(272, 42, 88, 14);
		getContentPane().add(lblBalangTarihi);

		dChsrBaslangicTarihi = new JDateChooser();
		dChsrBaslangicTarihi.setBounds(376, 39, 155, 20);
		Date dd = dChsrBaslangicTarihi.getDate();
		getContentPane().add(dChsrBaslangicTarihi);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(21, 156, 656, 220);
		getContentPane().add(scrollPane_2);

		tableOgrtmn = new JTable(dataOgrtmn,ogrtmColumnNames);
		//tableOgrtmn.setEnabled(false);
		tableOgrtmn.setColumnSelectionAllowed(false);
		tableOgrtmn.setRowSelectionAllowed(true);
		tableOgrtmn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tableOgrtmn);

		JLabel lblretmenListesi = new JLabel("\u00D6\u011Fretmen Listesi");
		lblretmenListesi.setBounds(21, 132, 121, 14);
		getContentPane().add(lblretmenListesi);

		JLabel lblSalonListesi = new JLabel("Salon Listesi");
		lblSalonListesi.setBounds(21, 387, 88, 14);
		getContentPane().add(lblSalonListesi);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 412, 656, 141);
		getContentPane().add(scrollPane_1);

		tableSalon = new JTable(dataSalon,salonColumnNames);
		tableSalon.setColumnSelectionAllowed(false);
		tableSalon.setRowSelectionAllowed(true);
		tableSalon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableSalon);

		lblMinKapasitekii = new JLabel("Minimum Kay\u0131t (Ki\u015Fi) : ");
		lblMinKapasitekii.setBounds(172, 684, 103, 14);
		getContentPane().add(lblMinKapasitekii);

		txtAcmaSarti = new JTextField();
		txtAcmaSarti.setBounds(285, 681, 52, 20);
		getContentPane().add(txtAcmaSarti);
		txtAcmaSarti.setColumns(10);

		btnKursAc = new JButton("KURS A\u00C7");
		btnKursAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//saat dilimlerini kontrol et
				
			}
		});
		btnKursAc.setBackground(SystemColor.textHighlight);
		btnKursAc.setBounds(577, 636, 100, 20);
		btnKursAc.setEnabled(false);
		getContentPane().add(btnKursAc);

		//ogretmen database'ini oku
		try {
			listeogr = ogrdao.tumKayitlariGetir(new Ogretmen());
			listesalon = slndao.tumKayitlariGetir(new Salon());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		btnSorgula = new JButton("SORGULA");
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					listekurs = kursdao.tumKayitlariGetir(new Kurs());
					listegun = gundao.tumKayitlariGetir(new Gun());
					listegrup = grupdao.tumKayitlariGetir(new Grup());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				datebas = dChsrBaslangicTarihi.getDate();
				datebit = dChsrBitisTarihi.getDate();

				//btnKursAc.setVisible(false);
				//diðer parametreleri kontrol et
				//1.combox'larin index'i 1 ve ustu mu?
				//bitis tarihi baslangi tarihinden sonra mi?
				//hatali olan alanlari kirmizi ile boya

				UnqSlnId.clear();
				UnqOgrtId.clear();

				for(int i=0;i<listegrup.size();i++)
				{
					if(UnqSlnId.indexOf(listegrup.get(i).getSalon().getId())==-1)
					{
						UnqSlnId.add(listegrup.get(i).getSalon().getId());						
					}
					if(UnqOgrtId.indexOf(listegrup.get(i).getOgretmen().getId())==-1)
					{
						UnqOgrtId.add(listegrup.get(i).getOgretmen().getId());
					}
				}

				dataSal = new String[UnqSlnId.size()][9];
				dataOgr = new String[UnqOgrtId.size()][9];

				for(int i=0;i<UnqSlnId.size();i++)
				{
					for(int j=2;j<9;j++)
					{
						dataSal[i][j] = "S Ö A";						
					}
				}

				for(int i=0;i<UnqOgrtId.size();i++)
				{
					for(int j=2;j<9;j++)
					{
						dataOgr[i][j] = "S Ö A";						
					}
				}

				for(int i=0;i<listegrup.size();i++)
				{
					if(AralikIcinde(listegrup.get(i).getBaslamaTarihi(), listegrup.get(i).getBitisTarihi()))
					{
						int indis = UnqSlnId.indexOf(listegrup.get(i).getSalon().getId());
						int indis2 = UnqOgrtId.indexOf(listegrup.get(i).getOgretmen().getId());
						dataSal[indis][0] = Long.toString(listegrup.get(i).getSalon().getId());
						dataSal[indis][1] = listegrup.get(i).getSalon().getKod();
						dataOgr[indis2][0] = Long.toString(listegrup.get(i).getOgretmen().getId());
						dataOgr[indis2][1] = listegrup.get(i).getOgretmen().getAd()+" "+listegrup.get(i).getOgretmen().getSoyad();
						String str;
						int indis3, indis4;
						
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							indis3 = listegrup.get(i).getGun().getSaat();
							str = saatTanim.substring(indis3,indis3+1);
							indis4 = dataSal[indis][2].indexOf(str);
							//indis4'teki elemani cikar
							dataSal[indis][2]  = dataSal[indis][2]+saatTanim.substring(indis3,indis3+1);
							dataOgr[indis2][2] = dataOgr[indis2][2]+saatTanim.substring(indis3,indis3+1);
						}
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							dataSal[indis][3]  = dataSal[indis][3]+listegrup.get(i).getGun().getSaat();
							dataOgr[indis2][3] = dataOgr[indis2][3]+listegrup.get(i).getGun().getSaat();
						}
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							dataSal[indis][4]  = dataSal[indis][4]+listegrup.get(i).getGun().getSaat();
							dataOgr[indis2][4] = dataOgr[indis2][4]+listegrup.get(i).getGun().getSaat();
						}
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							dataSal[indis][5]  = dataSal[indis][5]+listegrup.get(i).getGun().getSaat();
							dataOgr[indis2][5] = dataOgr[indis2][5]+listegrup.get(i).getGun().getSaat();
						}
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							dataSal[indis][6]  = dataSal[indis][6]+listegrup.get(i).getGun().getSaat();
							dataOgr[indis2][6] = dataOgr[indis2][6]+listegrup.get(i).getGun().getSaat();
						}
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							dataSal[indis][7]  = dataSal[indis][7]+listegrup.get(i).getGun().getSaat();
							dataOgr[indis2][7] = dataOgr[indis2][7]+listegrup.get(i).getGun().getSaat();
						}
						if(listegrup.get(i).getGun().getGun1()==1)
						{
							dataSal[indis][8]  = dataSal[indis][8]+listegrup.get(i).getGun().getSaat();
							dataOgr[indis2][8] = dataOgr[indis2][8]+listegrup.get(i).getGun().getSaat();
						}
					}
				}
				
				//listede olmayan ogretmen ve salonlari tum gunlere uygun olarak isaretle.
				/*listesalon.get(0).getId();
				listeogr.get(0).getId();*/
				
				tableSalon = new JTable(dataSal,salonColumnNames);
				tableOgrtmn = new JTable(dataOgr,ogrtmColumnNames);

				//listegrup.get(0).getOgretmen().equals(obj);

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
		btnSorgula.setBounds(589, 49, 88, 20);
		getContentPane().add(btnSorgula);

		lblUyar = new JLabel("Uyar\u0131");
		lblUyar.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblUyar.setForeground(Color.RED);
		lblUyar.setBounds(21, 73, 206, 14);
		getContentPane().add(lblUyar);

		JLabel lblIndirim = new JLabel("\u0130ndirim : ");
		lblIndirim.setBounds(172, 615, 46, 14);
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
		rbtnIndirimVar.setBounds(223, 611, 52, 23);
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
		rdbtnYok.setBounds(276, 611, 46, 23);
		getContentPane().add(rdbtnYok);

		txtIndirimOrani = new JTextField();
		txtIndirimOrani.setBounds(285, 650, 52, 20);
		getContentPane().add(txtIndirimOrani);
		txtIndirimOrani.setColumns(10);

		JLabel lblIndirimOranyzde = new JLabel("\u0130ndirim Oran\u0131 (Y\u00FCzde) : ");
		lblIndirimOranyzde.setBounds(172, 653, 121, 14);
		getContentPane().add(lblIndirimOranyzde);

		JLabel lblBitiTarihi = new JLabel("Biti\u015F Tarihi : ");
		lblBitiTarihi.setBounds(272, 73, 88, 14);
		getContentPane().add(lblBitiTarihi);

		dChsrBitisTarihi = new JDateChooser();
		dChsrBitisTarihi.setBounds(376, 70, 155, 20);
		getContentPane().add(dChsrBitisTarihi);

		JLabel lblSaat = new JLabel("Saat : ");
		lblSaat.setBounds(362, 615, 46, 14);
		getContentPane().add(lblSaat);

		JComboBox cmbSaat = new JComboBox();
		cmbSaat.setModel(new DefaultComboBoxModel(new String[] {"Saat se\u00E7iniz...", "Sabah", "\u00D6\u011Flen", "Ak\u015Fam"}));
		cmbSaat.setBounds(427, 612, 121, 20);
		getContentPane().add(cmbSaat);

		tableGunSecimi = new JTable();
		tableGunSecimi.setBorder(new LineBorder(Color.LIGHT_GRAY));
		tableGunSecimi.setModel(new DefaultTableModel(
				new Object[][] {
					{"Pazartesi"},
					{"Sal\u0131"},
					{"\u00C7ar\u015Famba"},
					{"Per\u015Fembe"},
					{"Cuma"},
					{"Cumartesi"},
					{"Pazar"},
				},
				new String[] {
						"New column"
				}
				));
		tableGunSecimi.setBounds(21, 611, 121, 112);
		getContentPane().add(tableGunSecimi);

		lblGnSeimi = new JLabel("G\u00FCn Se\u00E7imi :");
		lblGnSeimi.setBounds(21, 586, 73, 14);
		getContentPane().add(lblGnSeimi);

		lblUyari = new JLabel("uyari2");
		lblUyari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUyari.setForeground(Color.RED);
		lblUyari.setBounds(374, 684, 245, 14);
		getContentPane().add(lblUyari);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 119, 681, 444);
		getContentPane().add(panel_1);

		lblSorgulamaSonucu = new JLabel("Sorgulama Sonucu");
		lblSorgulamaSonucu.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblSorgulamaSonucu);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 681, 99);
		getContentPane().add(panel);

		lblSorgulama = new JLabel("Sorgulama");
		lblSorgulama.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblSorgulama);

		lblretmen = new JLabel("\u00D6\u011Fretmen : ");
		lblretmen.setBounds(360, 653, 78, 14);
		getContentPane().add(lblretmen);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u00D6\u011Fretmen se\u00E7iniz..."}));
		comboBox.setBounds(427, 650, 121, 20);
		getContentPane().add(comboBox);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 574, 681, 162);
		getContentPane().add(panel_2);

		lblSeim = new JLabel("Se\u00E7im");
		lblSeim.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_2.add(lblSeim);


		//scrollPane.setColumnHeaderView(table);
		//scrollPane_1.setColumnHeaderView(table_1);
	}

	public boolean AralikIcinde(Date bas, Date bit)
	{
		boolean sonuc=true;
		if((bit.compareTo(datebas)<0)||(bas.compareTo(datebit)>0))
		{
			sonuc = false;
		}

		return sonuc;
	}
}
