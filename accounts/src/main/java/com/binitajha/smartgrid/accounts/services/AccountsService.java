package com.binitajha.smartgrid.accounts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binitajha.smartgrid.accounts.adapter.AccountMapper;
import com.binitajha.smartgrid.accounts.adapter.DeviceMapper;
import com.binitajha.smartgrid.accounts.repos.AccountRepository;
import com.binitajha.smartgrid.accounts.repos.DeviceRepository;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Device;
import com.binitajha.smartgrid.accounts.view.AccountView;
import com.binitajha.smartgrid.accounts.view.DeviceView;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountsService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private DeviceRepository deviceRepo;

	private AccountMapper accMapper = new AccountMapper();
	private DeviceMapper devMapper = new DeviceMapper();

    public List<DeviceView> getDevices(String accountId) {
    	log.info("Account ID : " + accountId);

    	List<Device> entitylist = deviceRepo.findByAccountId(accountId);
    	List<DeviceView> list = entitylist.stream().map(entity -> devMapper.to(entity)).collect(Collectors.toList());
    	return list;
    }

    public List<AccountView> getAllAccounts() {

    	Iterable<Account> accts = accountRepo.findAll();
    	List<AccountView> acctViews = new ArrayList<>();
    	accts.iterator().forEachRemaining(acct -> acctViews.add(accMapper.to(acct)));
    	return acctViews;
    }

    public AccountView getAccount(String accountid) {
    	log.info("Account ID : " + accountid);
    	Optional<Account> a = accountRepo.findById(accountid);
    	return a.isPresent() ? accMapper.to(a.get()) : null;
    }

	public DeviceView getDevice(Long deviceid) {
		Optional<Device> device = deviceRepo.findById(deviceid);
		return device.isPresent() ? devMapper.to(device.get()) : null;
	}


}
