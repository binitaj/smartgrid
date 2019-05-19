package com.binitajha.smartgrid.accounts.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.binitajha.smartgrid.accounts.adapter.AccountMapper;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Address;
import com.binitajha.smartgrid.accounts.view.AccountView;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class AccountAdapterTest {


//	@Test
	public void test() {
		AccountMapper am = new AccountMapper();

	    Address address = new Address(null, null, "123 Amazonia Drive", "53990", "Themyscira", "Amazonia", 34.054960, -84.318397, "dnh19r90wbwm");
	    Account ww = new Account("12-3456-789", "Diana Prince", address, "12", "A", "Residential");

	    com.binitajha.smartgrid.accounts.view.Address addressv = am.to(address);
	    System.err.println(addressv);

	    AccountView wwv = am.to(ww);
	    System.err.println(wwv);
	    assertEquals(ww.getName(), wwv.getName());
	    assertEquals(ww.getAddress().getCity(), wwv.getAddress().getCity());
	}

	//AccountView(id=032-002790-01, name=Joe Smith, address=Address(line1=888 smartgrid St, zip=30004, city=Atlanta, state=GA, lat=30.5901448, lon=-97.790054, geohash=9v6mwd0y), bc=03, s=A, st=Residential)

	@Test
	public void test2() {
		AccountMapper am = new AccountMapper();

	    Address address = new Address(null, null, "888 smartgrid St", "888", "Atlanta", "GA", 30.5901448, -97.790054, "9v6mwd0y");
	    Account ww = new Account("032-002790-01", "Joe Smith", address, "03", "A", "Residential");
	    com.binitajha.smartgrid.accounts.view.Address addressv = am.to(address);
	    System.err.println(addressv);

	    AccountView wwv = am.to(ww);
	    System.err.println(wwv);
	    ww = am.to(wwv);
	    System.err.println(ww);

	}
}
