package com.veritran.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veritran.banking.model.Client;

/**
 * Client repository.
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
	
}
