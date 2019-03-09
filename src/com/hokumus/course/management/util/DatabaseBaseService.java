/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hokumus.course.management.util;

import java.lang.reflect.Field;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vektorel
 */
public class DatabaseBaseService<T> implements IDatabase<T>{
    private Session ss;
    private Transaction tt;
    private void baglantiAc()
    {
        ss = HBUtil.getSessionFactory().openSession();
        tt = ss.beginTransaction();
    }
    
    private void baglantiKapat()
    {
        tt.commit();
        ss.close();
    }
    
    @Override
    public boolean kaydet(T temp) throws Exception{
        baglantiAc();
        ss.save(temp);
        //tt.rollback();
        baglantiKapat();
        return true;
    }

    @Override
    public boolean guncelle(T temp) throws Exception{
        baglantiAc();
        ss.update(temp);
        //tt.rollback();
        baglantiKapat();
        return true;
    }

    @Override
    public boolean sil(T temp) throws Exception{
        baglantiAc();
        ss.delete(temp);
        //tt.rollback();
        baglantiKapat();
        return true;    }

    @Override
    public List<T> tumKayitlariGetir(T temp) throws Exception{
        baglantiAc();
        Criteria cr = ss.createCriteria(temp.getClass());
        List<T> liste = cr.list();
        baglantiKapat();
        return liste;
    }

    @Override
    public T kayitBul(int id, T temp) throws Exception{
        baglantiAc();
        Criteria cr = ss.createCriteria(temp.getClass());
        cr.add(Restrictions.eq("id",id));
        T instance = (T)cr.uniqueResult();
        baglantiKapat();
        return instance;
    }

    @Override
    public List<T> KayitAra(String kolonadi, String aranan, T temp) throws Exception{
        baglantiAc();
        Criteria cr = ss.createCriteria(temp.getClass());
        cr.add(Restrictions.ilike(kolonadi,"'%"+aranan+"%'"));
        List<T> liste = cr.list();
        baglantiKapat();
        return liste;
    }

    @Override
    public List<T> KayitAra(T temp) throws Exception{
        List<T> listem = null;
        Class cl = temp.getClass();
        Field[] fl = cl.getDeclaredFields();
        
        baglantiAc();
        Criteria cr = ss.createCriteria(temp.getClass());
        cr.addOrder(Order.asc("id"));
        for(int i=0; i<fl.length; i++)
        {
            fl[i].setAccessible(true); /*islem yapabilmek icin accessible olmali*/
            if(fl[i].get(temp)!=null && !fl[i].get(temp).toString().equals("0"))
            {
                cr.add(Restrictions.ilike(fl[i].getName(), "'%"+fl[i].get(temp)+"%'"));
                // get name kolon ismini, get ise icerisindeki degeri getirir.
                //instanceof tipi integer ise like olmayan restriction yaz
            }
        }
        listem = cr.list();
        
        return listem;
    }
    
}
