package com.epam.hostel.dao;

import com.epam.hostel.dao.exception.DAOException;

import java.util.List;

public interface IGenericDAO<E, K> {

    List<E> findAll() throws DAOException;

    E findById(Long id) ;

    long add(E entity) throws DAOException;

    boolean delete(K id) throws DAOException;

    K update(E entity) throws DAOException;
}
