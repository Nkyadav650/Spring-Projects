package com.Eidiko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Eidiko.Entity.MailCredentials;

public interface MailCredentialsRepository extends JpaRepository<MailCredentials, Integer>{

	public MailCredentials findByEmail(String email);

}
