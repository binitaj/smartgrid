package com.binitajha.smartgrid.accounts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.binitajha.smartgrid.accounts.adapter.AccountMapper;
import com.binitajha.smartgrid.accounts.adapter.DeviceMapper;
import com.binitajha.smartgrid.accounts.repos.AccountRepository;
import com.binitajha.smartgrid.accounts.repos.DeviceRepository;
import com.binitajha.smartgrid.accounts.repos.model.Account;
import com.binitajha.smartgrid.accounts.repos.model.Device;
import com.binitajha.smartgrid.accounts.view.AccountView;
import com.binitajha.smartgrid.accounts.view.DeviceView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DataSetupController {

	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AccountRepository accountRepo;


	@Autowired
	private DeviceRepository deviceRepo;

	@Value("${smartgrid.accounts.data.file}")
	private String accfile;

	@Value("${smartgrid.devices.data.file}")
	private String devfile;

	@Value("${smartgrid.accounts.data.file}")
	private String readfile;

	private AccountMapper accMapper = new AccountMapper();
	private DeviceMapper devMapper = new DeviceMapper();

//	@PostConstruct
	public void setup() {
		List<AccountView> accountViews = readAccounts(accfile);
		List<Account> accounts = accountViews.stream().map(acc -> accMapper.to(acc)).collect(Collectors.toList());
		accounts.stream().forEach(entity -> accountRepo.save(entity));
		System.err.println(accounts);
		System.err.println(accounts.size() + " ACCOUNTS SETUP");

		List<DeviceView> devices = readDevices(devfile);
//		devices.stream().map(dev -> deviceRepo.save(devMapper.to(dev)));
		List<Device> dvcs = devices.stream().map(device -> devMapper.to(device)).collect(Collectors.toList());
		dvcs.stream().forEach(entity -> {
			Account account = accountRepo.findById(entity.getAccount().getId()).get();
			entity.setAccount(account);
			deviceRepo.save(entity);
		});
		System.err.println(devices.size() + " DEVICES SETUP");

	}

	public List<AccountView> readAccounts(String filename) {
		List<AccountView> accounts = new ArrayList<>();
		try {
			accounts = mapper.readValue(new File(filename), new TypeReference<List<AccountView>>() {
			});
			System.err.println("From " + filename + " read " + accounts);
		} catch (IOException e) {
			log.error(String.format("Error reading accounts: %s;%s", e.getMessage(), e.getLocalizedMessage()));
		}
		return accounts;
	}

	public List<DeviceView> readDevices(String filename) {
		List<DeviceView> devices = new ArrayList<>();
		try {
			devices = mapper.readValue(new File(filename), new TypeReference<List<DeviceView>>() {
			});
		} catch (IOException e) {
			log.error(String.format("Error reading accounts: %s;%s", e.getMessage(), e.getLocalizedMessage()));
		}
		return devices;
	}

}
