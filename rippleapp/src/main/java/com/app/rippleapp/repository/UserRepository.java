package com.app.rippleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.rippleapp.bean.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}