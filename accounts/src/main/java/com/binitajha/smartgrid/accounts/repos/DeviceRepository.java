package com.binitajha.smartgrid.accounts.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.binitajha.smartgrid.accounts.repos.model.Device;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

	@Query("SELECT d FROM Device d where d.account.id = :accountId")
	public List<Device> findByAccountId(String accountId);

}
