/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hokumus.course.management.util;

import java.util.List;

/**
 *
 * @author vektorel
 */
public interface IDatabase<T> {
    public boolean kaydet(T temp) throws Exception;
    public boolean guncelle(T temp)throws Exception;
    public boolean sil(T temp)throws Exception;
    public List<T> tumKayitlariGetir(T t)throws Exception;
    public T kayitBul(int id, T t)throws Exception;
    public List<T> KayitAra(String kolonadi, String aranan, T temp)throws Exception;
    public List<T> KayitAra(T temp)throws Exception;
}
