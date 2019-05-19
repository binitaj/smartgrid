package com.binitajha.smartgrid.accounts.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.binitajha.smartgrid.accounts.repos.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, String> {
	public Account findByName(String name);
}
