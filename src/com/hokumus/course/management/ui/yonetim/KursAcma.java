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
import java.math.BigDecimal;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
import com.hokumus.course.management.util.CourseUtils;

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
	int basTarihi, bitTarihi, tabloBoyOgr, tabloBoySalon;
	private JTable tableGunSecimi;
	private JLabel lblGnSeimi;
	private JLabel lblUyari;
	private JPanel panel_1;
	private JLabel lblSorgulamaSonucu;
	private JLabel lblretmen;
	private JComboBox comboBox;
	Date datebas = new Date();
	Date datebit = new Date();
	String[][] dataSal;
	String[][] dataOgr;
	String saatTanim="SÖA";
	private JLabel lblFiyattl;
	private JTextField txtFiyat;
	private JTable table_1;
	String secilenKurs="";
	JComboBox cmbSaat;
	private JTextField txtGrupAdi;
	JComboBox cmbSalon;

	public KursAcma() {
		getContentPane().setLayout(null);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(36, 209, 149, -170);
		getContentPane().add(scrollPane_3);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
						"New column", "New column", "New column", "New column"
				}
				));
		table_1.setBounds(326, 199, 125, -139);
		getContentPane().add(table_1);
		setTitle("Kurs A\u00E7ma");
		setBackground(SystemColor.desktop);
		setBounds(100,100,790,780);//setEnabled(false);

		OgretmenDAO ogrdao = new OgretmenDAO();
		KursDAO kursdao = new KursDAO();
		GunDAO gundao = new GunDAO();
		GrupDAO grupdao = new GrupDAO();
		SalonDAO slndao = new SalonDAO();

		//ogretmen ve salon database'ini oku
		try {
			listeogr = ogrdao.tumKayitlariGetir(new Ogretmen());
			listesalon = slndao.tumKayitlariGetir(new Salon());
			listekurs = kursdao.tumKayitlariGetir(new Kurs());
			listegun = gundao.tumKayitlariGetir(new Gun());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		comboBox = new JComboBox();
		String [] str1 = new String[listeogr.size()+1];
		str1[0] = "Öðretmen seçiniz...";
		for(int i=0;i<listeogr.size();i++)
		{
			str1[i+1] = listeogr.get(i).getAd()+" "+listeogr.get(i).getSoyad();
		}
		comboBox.setModel(new DefaultComboBoxModel(str1));
		comboBox.setBounds(116, 141, 155, 20);
		getContentPane().add(comboBox);

		cmbSalon = new JComboBox();
		String [] str2 = new String[listesalon.size()+1];
		str2[0] = "Salon seçiniz...";
		for(int i=0;i<listesalon.size();i++)
		{
			str2[i+1] = listesalon.get(i).getKod();
		}
		cmbSalon.setModel(new DefaultComboBoxModel(str2));		
		cmbSalon.setBounds(116, 172, 155, 20);
		getContentPane().add(cmbSalon);		

		tabloBoyOgr = listeogr.size();
		tabloBoySalon = listesalon.size();
		System.out.println("tablo boylari ogr, salon:"+tabloBoyOgr+","+tabloBoySalon);
		
		String[][] dataOgrtmn = null;
		dataOgrtmn = new String[tabloBoyOgr][9];
		String[][] dataSalon = null;
		dataSalon = new String[tabloBoySalon][9];
		getContentPane().setLayout(null);

		lblKurs = new JLabel("Kurs :");
		lblKurs.setBounds(21, 45, 46, 14);
		getContentPane().add(lblKurs);

		cmbKurs = new JComboBox();
		cmbKurs.setBounds(116, 45, 155, 20);
		cmbKurs.setModel(new DefaultComboBoxModel(new String[] {"Bir Kurs Se\u00E7iniz...", ".NET Yaz\u0131l\u0131m Uzmanl\u0131\u011F\u0131", "Java ve Android Yaz\u0131l\u0131m", "Grafik Tasar\u0131m", "IOS (Objective C)", "Phyton Programlama", "Microsoft A\u011F ve Sistem", "Web Tasar\u0131m", "Mobil Yaz\u0131l\u0131m Android", "Cisco (CCNA)", "\u0130leri C# 6.0"}));
		getContentPane().add(cmbKurs);

		lblBalangTarihi = new JLabel("Ba\u015Flang\u0131\u00E7 Tarihi :");
		lblBalangTarihi.setBounds(21, 209, 88, 14);
		getContentPane().add(lblBalangTarihi);

		dChsrBaslangicTarihi = new JDateChooser();
		dChsrBaslangicTarihi.setBounds(116, 203, 155, 20);
		Date dd = dChsrBaslangicTarihi.getDate();
		getContentPane().add(dChsrBaslangicTarihi);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(21, 326, 719, 220);
		getContentPane().add(scrollPane_2);

		tableOgrtmn = new JTable(dataOgrtmn,ogrtmColumnNames);
		//tableOgrtmn.setEnabled(false);
		tableOgrtmn.setColumnSelectionAllowed(false);
		tableOgrtmn.setRowSelectionAllowed(true);
		tableOgrtmn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tableOgrtmn);

		JLabel lblretmenListesi = new JLabel("\u00D6\u011Fretmen Listesi");
		lblretmenListesi.setBounds(21, 302, 121, 14);
		getContentPane().add(lblretmenListesi);

		JLabel lblSalonListesi = new JLabel("Salon Listesi");
		lblSalonListesi.setBounds(21, 557, 88, 14);
		getContentPane().add(lblSalonListesi);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 582, 719, 140);
		getContentPane().add(scrollPane_1);

		tableSalon = new JTable(dataSalon,salonColumnNames);
		tableSalon.setColumnSelectionAllowed(false);
		tableSalon.setRowSelectionAllowed(true);
		tableSalon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableSalon);

		lblMinKapasitekii = new JLabel("Minimum Kay\u0131t (Ki\u015Fi) : ");
		lblMinKapasitekii.setBounds(504, 147, 144, 14);
		getContentPane().add(lblMinKapasitekii);

		txtAcmaSarti = new JTextField();
		txtAcmaSarti.setBounds(635, 144, 85, 20);
		getContentPane().add(txtAcmaSarti);
		txtAcmaSarti.setColumns(10);

		btnKursAc = new JButton("KURS A\u00C7");
		btnKursAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean uyari=false;
				String str="",str2="";
				tableGunSecimi.setBackground(Color.white);
				txtIndirimOrani.setBackground(Color.white);
				txtAcmaSarti.setBackground(Color.white);
				comboBox.setBackground(Color.white);
				txtFiyat.setBackground(Color.white);
				cmbSalon.setBackground(Color.white);
				cmbSaat.setBackground(Color.white);
				txtGrupAdi.setBackground(Color.white);
				cmbKurs.setBackground(Color.white);

				datebas = dChsrBaslangicTarihi.getDate();
				datebit = dChsrBitisTarihi.getDate();
				System.out.println("tarihler:"+datebas+", "+datebit);
				//
				/*dChsrBaslangicTarihi*/
				dChsrBaslangicTarihi.setBackground(Color.white);
				dChsrBitisTarihi.setBackground(Color.white);

				//hatali giris kontrolu:
				//hatali giris olursa hatali girilen yerleri kirmizi ile boya, uyari label'ini doldur.
				if((datebas==null)||(datebit==null))
				{
					uyari = false;
					str2 = "Tarihleri kontrol edin. ";					
					dChsrBaslangicTarihi.setBackground(Color.red);
					dChsrBitisTarihi.setBackground(Color.red);
				}
				else if(datebit.compareTo(datebas)<0)
				{
					uyari = false;
					str2 = "Tarihleri kontrol edin. ";
					dChsrBaslangicTarihi.setBackground(Color.red);
					dChsrBitisTarihi.setBackground(Color.red);
				}				
				
				//saat dilimlerini kontrol et
				if(tableGunSecimi.getSelectedRows().length==0)
				{
					uyari = true;
					tableGunSecimi.setBackground(Color.red);
				}
				if(cmbKurs.getSelectedIndex()==0)
				{
					uyari = true;
					cmbKurs.setBackground(Color.red);
				}
				if(rbtnIndirimVar.isSelected() && (txtIndirimOrani.getText().equals("")))
				{
					uyari = true;
					txtIndirimOrani.setBackground(Color.red);
				}
				if(txtAcmaSarti.getText().equals(""))
				{
					uyari = true;
					txtAcmaSarti.setBackground(Color.red);
				}
				if(txtFiyat.getText().equals(""))
				{
					uyari = true;
					txtFiyat.setBackground(Color.red);
				}
				if(comboBox.getSelectedIndex()==0)
				{
					uyari = true;
					comboBox.setBackground(Color.red);
				}
				if(cmbSalon.getSelectedIndex()==0)
				{
					uyari = true;
					cmbSalon.setBackground(Color.red);
				}
				if(cmbSaat.getSelectedIndex()==0)
				{
					uyari = true;
					cmbSaat.setBackground(Color.red);
				}
				if(txtGrupAdi.getText().equals(""))
				{
					uyari = true;
					txtGrupAdi.setBackground(Color.red);
				}

				if(uyari)
				{
					str = "Boþ alanlarý giriniz. "+str2;
				}

				lblUyari.setText(str);
				if(!uyari)
				{
					int returnValue;
					returnValue = JOptionPane.showConfirmDialog(KursAcma.this, "Kurs açýlsýn mý?");
					if(returnValue==0)
					{
						try {
							//ogretmen ve salon degerleri tarihlere uygun mu kontrol et
							//verileri database'e ekle.
							//ilgili salonu, id bilgisinden bul
							//ilgili ogretmen id bilgisini bul
							secilenKurs = (String)cmbKurs.getSelectedItem();
							Date dt1 = new Date();

							/*Tablodan secilen indisleri al*/
							int[] secilenGunler = tableGunSecimi.getSelectedRows();
							int[] secim = new int[7];
							for(int i=0;i<7;i++)
							{
								secim[i] = 0;
							}
							for(int i=0;i<secilenGunler.length;i++)
							{
								secim[secilenGunler[i]] = 1;
							}

							Gun gun1 = new Gun();
							gun1.setEklemeTarihi(dt1);
							gun1.setEkleyen(CourseUtils.userName);
							gun1.setGun1(secim[0]);
							gun1.setGun2(secim[1]);
							gun1.setGun3(secim[2]);
							gun1.setGun4(secim[3]);
							gun1.setGun5(secim[4]);
							gun1.setGun6(secim[5]);
							gun1.setGun7(secim[6]);
							gun1.setGuncellemeTarihi(dt1);
							gun1.setGuncelleyen("");
							gun1.setKayitDurumu(true);
							//gun1.setId(id); /*otomatik eklenecek mi bakalim*/
							gun1.setSaat(cmbSaat.getSelectedIndex()-1);
							gundao.kaydet(gun1);

							Kurs kurs1 = new Kurs();
							kurs1.setAdi(secilenKurs);
							kurs1.setbaslamaTarihi(datebas);
							kurs1.setDurum("Kayýt açýk");
							kurs1.setEklemeTarihi(dt1);
							kurs1.setEkleyen(CourseUtils.userName);
							BigDecimal bd = new BigDecimal(txtFiyat.getText());
							kurs1.setFiyat(bd);
							kurs1.setGuncellemeTarihi(dt1);
							kurs1.setGuncelleyen("");
							//kurs1.setId(id); /*otomatik eklenecek mi bakalim*/
							kurs1.setKayitDurumu(true);
							kursdao.kaydet(kurs1);

							Ogretmen ogr1 = new Ogretmen();
							ogr1.setAd(listeogr.get(comboBox.getSelectedIndex()-1).getAd());;
							ogr1.setAdres(listeogr.get(comboBox.getSelectedIndex()-1).getAdres());
							ogr1.setEklemeTarihi(listeogr.get(comboBox.getSelectedIndex()-1).getEklemeTarihi());
							ogr1.setEkleyen(listeogr.get(comboBox.getSelectedIndex()-1).getEkleyen());
							ogr1.setGuncellemeTarihi(listeogr.get(comboBox.getSelectedIndex()-1).getGuncellemeTarihi());
							ogr1.setGuncelleyen(listeogr.get(comboBox.getSelectedIndex()-1).getGuncelleyen());
							ogr1.setId(listeogr.get(comboBox.getSelectedIndex()-1).getId());
							ogr1.setKayitDurumu(listeogr.get(comboBox.getSelectedIndex()-1).getKayitDurumu());
							ogr1.setKayitTarihi(listeogr.get(comboBox.getSelectedIndex()-1).getKayitTarihi());
							ogr1.setMail(listeogr.get(comboBox.getSelectedIndex()-1).getMail());
							ogr1.setSoyad(listeogr.get(comboBox.getSelectedIndex()-1).getSoyad());
							ogr1.setTel(listeogr.get(comboBox.getSelectedIndex()-1).getTel());
							ogr1.setUcret(listeogr.get(comboBox.getSelectedIndex()-1).getUcret());

							Salon sln1 = new Salon();
							sln1.setAdi(listesalon.get(cmbSalon.getSelectedIndex()-1).getAdi());
							sln1.setEklemeTarihi(listesalon.get(cmbSalon.getSelectedIndex()-1).getEklemeTarihi());
							sln1.setEkleyen(listesalon.get(cmbSalon.getSelectedIndex()-1).getEkleyen());
							sln1.setGuncellemeTarihi(listesalon.get(cmbSalon.getSelectedIndex()-1).getGuncellemeTarihi());
							sln1.setGuncelleyen(listesalon.get(cmbSalon.getSelectedIndex()-1).getGuncelleyen());
							sln1.setId(listesalon.get(cmbSalon.getSelectedIndex()-1).getId());
							sln1.setKapasite(listesalon.get(cmbSalon.getSelectedIndex()-1).getKapasite());
							sln1.setKayitDurumu(listesalon.get(cmbSalon.getSelectedIndex()-1).getKayitDurumu());
							sln1.setKod(listesalon.get(cmbSalon.getSelectedIndex()-1).getKod());

							Grup grp1 = new Grup();
							//grp1.setAdi(adi);/*EKLE*/
							grp1.setBaslamaTarihi(datebas);
							grp1.setBitisTarihi(datebit);
							grp1.setEklemeTarihi(dt1);
							grp1.setEkleyen(CourseUtils.userName);
							grp1.setGun(gun1);//gun bilgisi
							grp1.setGuncellemeTarihi(dt1);
							grp1.setGuncelleyen("");
							//grp1.setId(id); /*otomatik eklenecek mi bakalim*/
							grp1.setKurs(kurs1);//kurs bilgisi
							grp1.setOgrenciSayisi(0);
							grp1.setOgretmen(ogr1);//ogretmen bilgisi
							grp1.setSalon(sln1);//salon bilgisi
							grp1.setKayitDurumu(true);
							grupdao.kaydet(grp1);
							lblUyari.setText("Yeni kurs açýldý.");
						} catch (Exception ex) {
							Logger.getLogger(KursAcma.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
			}
		});
		btnKursAc.setBackground(SystemColor.controlHighlight);
		btnKursAc.setBounds(620, 234, 100, 20);
		getContentPane().add(btnKursAc);

		btnSorgula = new JButton("SORGULA");
		btnSorgula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean islemYap=true;
				String uyariStr="";
				//btnKursAc.setEnabled(false);
				try {
					listegrup = grupdao.tumKayitlariGetir(new Grup());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				datebas = dChsrBaslangicTarihi.getDate();
				datebit = dChsrBitisTarihi.getDate();
				System.out.println("tarihler:"+datebas+", "+datebit);
				//
				/*dChsrBaslangicTarihi*/
				dChsrBaslangicTarihi.setBackground(Color.white);
				dChsrBitisTarihi.setBackground(Color.white);

				//hatali giris kontrolu:
				//hatali giris olursa hatali girilen yerleri kirmizi ile boya, uyari label'ini doldur.
				if((datebas==null)||(datebit==null))
				{
					islemYap = false;
					uyariStr = uyariStr + " Tarihleri kontrol edin. ";		
					dChsrBaslangicTarihi.setBackground(Color.red);
					dChsrBitisTarihi.setBackground(Color.red);
				}
				else if(datebit.compareTo(datebas)<0)
				{
					islemYap = false;
					uyariStr = uyariStr + " Tarihleri kontrol edin. ";
					dChsrBaslangicTarihi.setBackground(Color.red);
					dChsrBitisTarihi.setBackground(Color.red);
				}

				lblUyari.setText(uyariStr);

				if(islemYap)
				{    
					//btnKursAc.setEnabled(true);
					UnqSlnId.clear();
					UnqOgrtId.clear();
					String str;
					secilenKurs = (String)cmbKurs.getSelectedItem();
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

					dataSal = new String[tabloBoySalon][9];
					dataOgr = new String[tabloBoyOgr][9];

					for(int i=0;i<tabloBoySalon;i++)
					{
						for(int j=2;j<9;j++)
						{
							dataSal[i][j] = "SÖA";	                                               
						}
					}

					for(int i=0;i<tabloBoyOgr;i++)
					{
						for(int j=2;j<9;j++)
						{
							dataOgr[i][j] = "SÖA";						
						}
					}

					System.out.println("grup sayýsý:"+listegrup.size());

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
							int indis3, indis4;

							if(listegrup.get(i).getGun().getGun1()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][2].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][2]  = StringDegeriCikar(dataSal[indis][2], indis4);
								}
								indis4 = dataOgr[indis2][2].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][2] = StringDegeriCikar(dataOgr[indis2][2], indis4);
								}                                                        
							}
							if(listegrup.get(i).getGun().getGun2()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][3].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][3]  = StringDegeriCikar(dataSal[indis][3], indis4);
								}
								indis4 = dataOgr[indis2][3].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][3] = StringDegeriCikar(dataOgr[indis2][3], indis4);
								} 						
							}
							if(listegrup.get(i).getGun().getGun3()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][4].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][4]  = StringDegeriCikar(dataSal[indis][4], indis4);
								}
								indis4 = dataOgr[indis2][4].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][4] = StringDegeriCikar(dataOgr[indis2][4], indis4);
								} 						
							}
							if(listegrup.get(i).getGun().getGun4()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][5].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][5]  = StringDegeriCikar(dataSal[indis][5], indis4);
								}
								indis4 = dataOgr[indis2][5].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][5] = StringDegeriCikar(dataOgr[indis2][5], indis4);
								} 						
							}
							if(listegrup.get(i).getGun().getGun5()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][6].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][6]  = StringDegeriCikar(dataSal[indis][6], indis4);
								}
								indis4 = dataOgr[indis2][6].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][6] = StringDegeriCikar(dataOgr[indis2][6], indis4);
								} 						
							}
							
							if(listegrup.get(i).getGun().getGun6()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][7].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][7]  = StringDegeriCikar(dataSal[indis][7], indis4);
								}
								indis4 = dataOgr[indis2][7].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][7] = StringDegeriCikar(dataOgr[indis2][7], indis4);
								} 						
							}
							if(listegrup.get(i).getGun().getGun7()==1)
							{
								indis3 = listegrup.get(i).getGun().getSaat();
								str = saatTanim.substring(indis3,indis3+1);
								indis4 = dataSal[indis][8].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataSal[indis][8]  = StringDegeriCikar(dataSal[indis][8], indis4);
								}
								indis4 = dataOgr[indis2][8].indexOf(str);
								if(indis4!=-1)
								{
									//indis4'teki elemani cikar
									dataOgr[indis2][8] = StringDegeriCikar(dataOgr[indis2][8], indis4);
								} 
							}
						}
					}

					//listede olmayan ogretmen ve salonlari tum gunlere uygun olarak isaretle.
					/*listesalon.get(0).getId();
				listeogr.get(0).getId();*/
					int count=0;
					for(int i=0;i<listesalon.size();i++)
					{
						if(doubleArrayBul(dataSal, UnqSlnId.size(), 0, Long.toString(listesalon.get(i).getId()))==-1)
						{
							dataSal[UnqSlnId.size()+count][0] = Long.toString(listesalon.get(i).getId());
							dataSal[UnqSlnId.size()+count][1] = listesalon.get(i).getKod();
							count++;
						}
					}
					count=0;
					for(int i=0;i<listeogr.size();i++)
					{
						if(doubleArrayBul(dataOgr, UnqOgrtId.size(), 0, Long.toString(listeogr.get(i).getId()))==-1)
						{
							dataOgr[UnqOgrtId.size()+count][0] = Long.toString(listeogr.get(i).getId());
							dataOgr[UnqOgrtId.size()+count][1] = listeogr.get(i).getAd()+" "+listeogr.get(i).getSoyad();
							count++;
						}
					}

					for(int i=0;i<listeogr.size();i++)
					{
						for(int j=0;j<9;j++)
						{
							System.out.println("dataogr:"+i+","+j+" : "+dataOgr[i][j]);
							tableOgrtmn.setValueAt(dataOgr[i][j], i, j);							
						}
					}

					for(int i=0;i<listesalon.size();i++)
					{
						for(int j=0;j<9;j++)
						{
							System.out.println("datasalon:"+i+","+j+" : "+dataSal[i][j]);
							tableSalon.setValueAt(dataSal[i][j], i, j);							
						}
					}					
				}
			}
		});
		btnSorgula.setBackground(SystemColor.controlHighlight);
		btnSorgula.setBounds(491, 234, 100, 20);
		getContentPane().add(btnSorgula);

		txtIndirimOrani = new JTextField();
		txtIndirimOrani.setBounds(635, 109, 85, 20);
		getContentPane().add(txtIndirimOrani);
		txtIndirimOrani.setColumns(10);

		JLabel lblIndirim = new JLabel("\u0130ndirim : ");
		lblIndirim.setBounds(504, 79, 72, 14);
		getContentPane().add(lblIndirim);

		rbtnIndirimVar = new JRadioButton("Var");
		rbtnIndirimVar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rbtnIndirimVar.isSelected())
				{
					rdbtnYok.setSelected(false);
					txtIndirimOrani.setEnabled(true);
				}
				else
				{
					rdbtnYok.setSelected(true);		
					txtIndirimOrani.setEnabled(false);
				}	
			}
		});
		rbtnIndirimVar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbtnIndirimVar.isSelected())
				{
					rdbtnYok.setSelected(false);
					txtIndirimOrani.setEnabled(true);
				}
				else
				{
					rdbtnYok.setSelected(true);		
					txtIndirimOrani.setEnabled(false);
				}		
			}
		});
		rbtnIndirimVar.setBounds(582, 79, 52, 23);
		getContentPane().add(rbtnIndirimVar);

		rdbtnYok = new JRadioButton("Yok");
		rdbtnYok.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnYok.isSelected())
				{
					rbtnIndirimVar.setSelected(false);
					txtIndirimOrani.setEnabled(false);
				}
				else
				{
					rbtnIndirimVar.setSelected(true);
					txtIndirimOrani.setEnabled(true);
				}
			}
		});
		rdbtnYok.setSelected(true);
		rdbtnYok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(rdbtnYok.isSelected())
				{
					rbtnIndirimVar.setSelected(false);
					txtIndirimOrani.setEnabled(false);
				}
				else
				{
					rbtnIndirimVar.setSelected(true);					
					txtIndirimOrani.setEnabled(true);
				}							
			}
		});
		rdbtnYok.setBounds(636, 79, 46, 23);
		getContentPane().add(rdbtnYok);

		JLabel lblIndirimOranyzde = new JLabel("\u0130ndirim Oran\u0131 (Y\u00FCzde) : ");
		lblIndirimOranyzde.setBounds(504, 112, 150, 14);
		getContentPane().add(lblIndirimOranyzde);

		JLabel lblBitiTarihi = new JLabel("Biti\u015F Tarihi : ");
		lblBitiTarihi.setBounds(314, 209, 64, 14);
		getContentPane().add(lblBitiTarihi);

		dChsrBitisTarihi = new JDateChooser();
		dChsrBitisTarihi.setBounds(409, 203, 155, 20);
		getContentPane().add(dChsrBitisTarihi);

		JLabel lblSaat = new JLabel("Saat : ");
		lblSaat.setBounds(21, 112, 78, 14);
		getContentPane().add(lblSaat);

		cmbSaat = new JComboBox();
		cmbSaat.setModel(new DefaultComboBoxModel(new String[] {"Saat se\u00E7iniz...", "Sabah", "\u00D6\u011Flen", "Ak\u015Fam"}));
		cmbSaat.setBounds(116, 109, 155, 20);
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
		tableGunSecimi.setBounds(336, 70, 121, 112);
		getContentPane().add(tableGunSecimi);

		lblGnSeimi = new JLabel("G\u00FCn Se\u00E7imi :");
		lblGnSeimi.setBounds(336, 45, 73, 14);
		getContentPane().add(lblGnSeimi);

		lblUyari = new JLabel("");
		lblUyari.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUyari.setForeground(Color.RED);
		lblUyari.setBounds(59, 240, 327, 14);
		getContentPane().add(lblUyari);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 274, 747, 462);
		getContentPane().add(panel_1);

		lblSorgulamaSonucu = new JLabel("Sorgulama Sonucu");
		lblSorgulamaSonucu.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblSorgulamaSonucu);

		lblretmen = new JLabel("\u00D6\u011Fretmen : ");
		lblretmen.setBounds(21, 144, 64, 14);
		getContentPane().add(lblretmen);

		lblFiyattl = new JLabel("Fiyat (TL) : ");
		lblFiyattl.setBounds(504, 45, 103, 14);
		getContentPane().add(lblFiyattl);

		txtFiyat = new JTextField();
		txtFiyat.setBounds(635, 45, 85, 20);
		getContentPane().add(txtFiyat);
		txtFiyat.setColumns(10);

		JLabel lblSalon = new JLabel("Salon : ");
		lblSalon.setBounds(21, 175, 78, 14);
		getContentPane().add(lblSalon);

		JLabel lblGrupAd = new JLabel("Grup Ad\u0131 : ");
		lblGrupAd.setBounds(21, 79, 64, 14);
		getContentPane().add(lblGrupAd);

		txtGrupAdi = new JTextField();
		txtGrupAdi.setBounds(116, 76, 155, 20);
		getContentPane().add(txtGrupAdi);
		txtGrupAdi.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 747, 256);
		getContentPane().add(panel);
		
		JLabel lblKursBilgileri = new JLabel("Kurs Bilgileri");
		lblKursBilgileri.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblKursBilgileri);


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

	public String StringDegeriCikar(String str, int index)
	{
		String sonuc;
		int boyut = str.length();

		if(boyut==1)
		{
			sonuc = "";
		}
		else
		{
			if(index==0)
			{
				sonuc = str.substring(1,boyut);
			}
			else if(index==(boyut-1))
			{
				sonuc = str.substring(0,boyut-1);
			}
			else
			{
				sonuc = str.substring(0,index)+str.substring(index+1,boyut);
			}
		}

		return sonuc;
	}

	public int doubleArrayBul(String[][] array, int size, int index1, String deger)
	{
		int sonuc=-1;


		if(array[0][index1]==null)
		{
			return sonuc;
		}

		for(int i=0;i<size;i++)
		{
			System.out.println(" kontrol: size:"+size+" index: "+index1+","+i+" array : "+array[i][index1]+" deger:"+deger);

			if(array[i][index1].equals(deger))
			{
				sonuc = i;
				break;
			}
		}

		return sonuc;
	}
}
