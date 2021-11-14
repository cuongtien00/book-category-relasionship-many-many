package com.cuongtien.service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    T findById(int id);
    List<T> findAll();
    boolean insert(T t,int[] arr) throws SQLException;
    boolean update(T t, int[] arr) throws SQLException;
    boolean delete(int id)throws SQLException;
    List<T> sortByName();
}
