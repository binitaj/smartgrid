package com.binitajha.smartgrid.accounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.binitajha.smartgrid.accounts.view.AccountView;
import com.binitajha.smartgrid.accounts.view.DeviceView;

public class IntitializeDataTest {


	DataSetupController ia = new DataSetupController();

	@Test
	public void testAccountReads() {
		List<AccountView> accounts = ia.readAccounts("src\\test\\resources\\accounts.json");
		assertNotNull(accounts);
		assertEquals(3, accounts.size());
	}


	@Test
	public void testReadDevices() {
		List<DeviceView> devices = ia.readDevices("src\\test\\resources\\devices.json");
		assertNotNull(devices);
		assertEquals(4, devices.size());
		assertEquals("046-087070-04", devices.get(0).getAccount());
	}
}
