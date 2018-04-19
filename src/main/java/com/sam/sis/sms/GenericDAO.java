/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sam.sis.sms;

import java.util.List;

/**
 *
 * @author Sam
 */
public interface GenericDAO<T>{
    List<T> getAll();
    void insert(T t);
    void update(T t);
    void saveorupdate(T t);
    boolean delete(int id);
    T getbyId(int id);
}
