package com.hokumus.course.management.ui;

import java.util.Date;
import java.util.List;

import com.hokumus.course.management.dao.UsersDAO;
import com.hokumus.course.management.model.Role;
import com.hokumus.course.management.model.Users;

public class Runner {

	public static void main(String[] args) {
		UsersDAO dao = new UsersDAO();
		List<Users> liste=null;
		try {
			liste = dao.tumKayitlariGetir(new Users());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(liste.size()<=0)
		{
			Users usr = new Users();
			usr.setUserName("mgureken");
			usr.setPassword("123");
			usr.setName("Murat");
			usr.setEmail("aaaa@gg.com");
			usr.setRdate(new Date());
			usr.setRole(Role.ADMIN);
			usr.setSurname("GÜREKEN");
			try {
				dao.kaydet(usr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				new EnterUser().setVisible(true);
			}
		}
		else
		{
			new EnterUser().setVisible(true);

		}
	}

}
