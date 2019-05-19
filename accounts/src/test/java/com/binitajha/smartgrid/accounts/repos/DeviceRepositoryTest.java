package com.binitajha.smartgrid.accounts.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.binitajha.smartgrid.accounts.repos.DeviceRepository;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Address;
import com.binitajha.smartgrid.accounts.repos.model.Device;


@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	DeviceRepository repo;


	@Test
	public void whenFindByDeviceNumber_thenReturnDevice() {
	    // given
	    Address address = new Address(null, null, "123 Amazonia Drive", "53990", "Themyscira", "Amazonia", 34.054960, -84.318397, "dnh19r90wbwm");
	    Account ww = new Account("12-3456-789", "Diana Prince", address, "12", "A", "Residential");
	    address.setAccount(ww);
	    entityManager.persist(ww);

	    Device device = new Device(new Long(0), "WATER", "METER", 0.75, ww, "86696858", "82550377", "032", "GAL", "ACTIVE");
	    device.setAccount(ww);
//	    entityManager.persist(device);
//	    entityManager.flush();

	    repo.save(device);

	    // when
	    Optional<Device> found = repo.findById(device.getDeviceNumber());

	    // then
	    assertTrue(found.isPresent());
	    assertEquals(device.getMeterid_2(), found.get().getMeterid_2());
	    assertEquals(device.getAccount().getName(), found.get().getAccount().getName());
	}
}
