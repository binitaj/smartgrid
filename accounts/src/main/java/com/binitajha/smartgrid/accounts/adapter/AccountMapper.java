package com.binitajha.smartgrid.accounts.adapter;

import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Address;
import com.binitajha.smartgrid.accounts.view.AccountView;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class AccountMapper {
	static private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();

	static {
		mapperFactory.classMap(Account.class, AccountView.class).byDefault().register();
		mapperFactory.classMap(AccountView.class, Account.class).byDefault().register();

		mapperFactory.classMap(Address.class, com.binitajha.smartgrid.accounts.view.Address.class).byDefault().exclude("account").register();
		mapperFactory.classMap(com.binitajha.smartgrid.accounts.view.Address.class, Address.class).byDefault().exclude("account").register();

	}

	public AccountView to(Account a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, AccountView.class);
	}

	public Account to(AccountView a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, Account.class);
	}


	public com.binitajha.smartgrid.accounts.view.Address to(Address a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, com.binitajha.smartgrid.accounts.view.Address.class);
	}

	public Address to(com.binitajha.smartgrid.accounts.view.Address a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, Address.class);
	}
}