package com.hokumus.course.management.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.hokumus.course.management.dao.UsersDAO;
import com.hokumus.course.management.model.Users;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class EnterUser extends JFrame{
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JButton btnCancel;
	private JButton btnEnter;
	JLabel lbUserName;
	JLabel lblPassword;
	
	protected Object e;
	private JLabel lblUyari;
	public EnterUser() {
		initialize();
	}

	private void initialize() {
		setTitle("Kullanýcý Giriþ Ekraný");
		setBounds(100,100,300,250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lbUserName = new JLabel("Kullanici Ad\u0131");
		lbUserName.setBounds(37, 30, 46, 14);
		getContentPane().add(lbUserName);
		
		lblPassword = new JLabel("\u015Eifre");
		lblPassword.setBounds(37, 87, 46, 14);
		getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(147, 27, 86, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(147, 84, 86, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		btnCancel = new JButton("\u0130ptal");
		btnCancel.setBounds(20, 139, 91, 23);
		getContentPane().add(btnCancel);
		
		btnEnter = new JButton("Giri\u015F");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEnter_Action_Performed(e);
			}

		});
		btnEnter.setBounds(126, 139, 91, 23);
		getContentPane().add(btnEnter);
		
		lblUyari = new JLabel("Uyar\u0131");
		lblUyari.setBounds(20, 198, 184, 14);
		getContentPane().add(lblUyari);
		
	}

	protected void btnEnter_Action_Performed(Object e2) {
		UsersDAO dao = new UsersDAO();
		Users usr = new Users();
		usr.setUserName(txtUsername.getText());
		usr.setPassword(txtPassword.getText());
		try {
			List<Users> liste = dao.KayitAra(usr);
			if(liste.size()<=0)
			{
				lblUyari.setText("Girdiðiniz Kullanýcý Bulunamadi..!");
			}
			else
			{
				//yeni ekran acilir.
				lblUyari.setText("Kullanici Giriþi Baþarýlý");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
