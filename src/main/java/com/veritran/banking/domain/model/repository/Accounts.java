package com.veritran.banking.domain.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veritran.banking.domain.model.Account;

/**
 * Client repository.
 *
 */
@Repository
public interface Accounts extends JpaRepository<Account, UUID> {
	
}
