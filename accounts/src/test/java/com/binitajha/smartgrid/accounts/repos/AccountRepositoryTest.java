package com.binitajha.smartgrid.accounts.repos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.binitajha.smartgrid.accounts.repos.AccountRepository;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Address;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	AccountRepository repo;


	@Test
	public void whenFindByName_thenReturnAccount() {
	    // given
	    Address address = new Address(null, null, "123 Amazonia Drive", "53990", "Themyscira", "Amazonia", 34.054960, -84.318397, "dnh19r90wbwm");
	    Account ww = new Account("12-3456-789", "Diana Prince", address, "12", "A", "Residential");
	    address.setAccount(ww);

	    repo.save(ww);

	    // when
	    Account found = repo.findByName(ww.getName());

	    // then
	    assertEquals(ww.getName(), found.getName());
	}
}
