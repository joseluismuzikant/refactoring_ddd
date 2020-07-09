package com.veritran.banking.domain.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veritran.banking.domain.model.Client;

/**
 * Client repository.
 *
 */
@Repository
public interface Clients extends JpaRepository<Client, UUID> {
	Client  findByName(String name);
}
