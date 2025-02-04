package com.eventmanagement.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
