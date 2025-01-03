package com.myjdbc.jdbcdata.pcstore.repository;

import com.myjdbc.jdbcdata.pcstore.entity.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * this class is used to store the data in the database
 * this class implements jpa repository
 */

@Repository
public interface StoreRepository extends JpaRepository<Printer, Integer> {
    // Additional query methods can be defined here if needed

}
