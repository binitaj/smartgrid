package com.binitajha.smartgrid.accounts.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.binitajha.smartgrid.accounts.adapter.DeviceMapper;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Address;
import com.binitajha.smartgrid.accounts.repos.model.Device;
import com.binitajha.smartgrid.accounts.view.DeviceView;

public class DeviceMapperTest {

	@Test
	public void test() {
		DeviceMapper dm = new DeviceMapper();
	    Address address = new Address(null, null, "123 Amazonia Drive", "53990", "Themyscira", "Amazonia", 34.054960, -84.318397, "dnh19r90wbwm");
	    Account ww = new Account("12-3456-789", "Diana Prince", address, "12", "A", "Residential");
	    address.setAccount(ww);

	    Device device = new Device(new Long(0), "WATER", "METER", 0.75, ww, "86696858", "82550377", "032", "GAL", "ACTIVE");
	    DeviceView dv = dm.to(device);
	    assertEquals(device.getAmrIdentifier(), dv.getAmrIdentifier());
	}
}
