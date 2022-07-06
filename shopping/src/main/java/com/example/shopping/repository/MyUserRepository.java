package com.example.shopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shopping.model.MyUser;

@Repository
public interface MyUserRepository extends CrudRepository<MyUser, Long>{
    @Query("select u from MyUser u where username = :username")
    MyUser findByUsername(@Param("username") String username);

}
