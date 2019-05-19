package com.binitajha.smartgrid.accounts.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.binitajha.smartgrid.accounts.adapter.AccountMapper;
import com.binitajha.smartgrid.accounts.adapter.DeviceMapper;
import com.binitajha.smartgrid.accounts.repos.AccountRepository;
import com.binitajha.smartgrid.accounts.repos.DeviceRepository;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Device;
import com.binitajha.smartgrid.accounts.view.AccountView;
import com.binitajha.smartgrid.accounts.view.Address;
import com.binitajha.smartgrid.accounts.view.DeviceView;


@RunWith(SpringRunner.class)
@DataJpaTest
@FlywayTest
public class AccountsControllerTest {

	@Autowired public AccountsController ac;

	public static final String TEST_ACCT_ID = "032-002790-01";

	@Autowired
	AccountRepository accRepo;

	AccountMapper am = new AccountMapper();
	DeviceMapper dm = new DeviceMapper();

	@Autowired
	DeviceRepository devRepo;

	@Test
	public void testGetAccounts() {
		ResponseEntity<AccountView> res = ac.getAccount("123");
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

		res = ac.getAccount(TEST_ACCT_ID);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		AccountView acct = res.getBody();
		Account a = am.to(acct);
		AccountView acct2 = am.to(a);
		Account a2 = am.to(acct2);

		Address addr1 = acct2.getAddress();
		com.binitajha.smartgrid.accounts.repos.model.Address addr2 = am.to(addr1);
		Address addr3 = am.to(addr2);
		assertEquals(addr1, addr3);

		assertEquals(a, a2);
		assertEquals(acct, acct2);

		assertNotNull(acct);
		assertEquals(TEST_ACCT_ID, acct.getId());

	}

	@Test
	public void testGetDevices() {
		ResponseEntity<List<DeviceView>> res = ac.getDevices("123");
		assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());

		res = ac.getDevices(TEST_ACCT_ID);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertNotNull(res.getBody());
		assertEquals(TEST_ACCT_ID, res.getBody().get(0).getAccount());

		DeviceView dv = res.getBody().get(0);

		Device d2 = dm.to(dv);

		DeviceView dv2 = dm.to(d2);

		assertEquals(dv, dv2);

	}


	@Test
	public void testGetDevice() {
		ResponseEntity<DeviceView> res = ac.getDevice(123l);
		assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
		res = ac.getDevice(75118300l);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertNotNull(res.getBody());
		DeviceView device = res.getBody();
		assertEquals(75118300l, device.get_id().getDeviceNumber());
		assertEquals(TEST_ACCT_ID, device.getAccount());
	    assertEquals("86696858",  device.getAmrIdentifier());
	    assertEquals(82550377l, device.getMeterid_2());
	    assertEquals("032", device.getServiceRoute());

	}


	@Test
	public void testGetAllAccounts() {
		ResponseEntity<List<AccountView>> acvcs = ac.getAllAccounts();
		List<AccountView> accs = acvcs.getBody();
		assertNotNull(accs);
		assertTrue(accs.size() > 0);
	}


}
