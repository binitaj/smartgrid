package com.binitajha.smartgrid.accounts.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binitajha.smartgrid.accounts.services.AccountsService;
import com.binitajha.smartgrid.accounts.view.AccountView;
import com.binitajha.smartgrid.accounts.view.DeviceView;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AccountsController {

	@Autowired
	private AccountsService service;

    @RequestMapping("/api/accounts/{accountId}/devices")
    public ResponseEntity<List<DeviceView>> getDevices(@PathVariable String accountId) {
    	log.info("Account ID : " + accountId);

    	List<DeviceView> list = service.getDevices(accountId);

    	return list.size() == 0 ? new ResponseEntity<>(list, HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
    }

	@RequestMapping("/api/accounts/")
    public ResponseEntity<List<AccountView>> getAllAccounts() {

		List<AccountView> acctViews = service.getAllAccounts();

    	return acctViews != null && acctViews.size() >= 0 ?
    			new ResponseEntity<List<AccountView>>(acctViews, HttpStatus.OK) :
    			new ResponseEntity<List<AccountView>>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping("/api/devices/{deviceid}")
    public ResponseEntity<DeviceView> getDevice(@PathVariable(value="deviceid") Long deviceid) {
    	log.info("Account ID : " + deviceid);

    	DeviceView deviceView = service.getDevice(deviceid);

    	return deviceView != null ? new ResponseEntity<DeviceView>(deviceView, HttpStatus.OK) : new ResponseEntity<DeviceView>(HttpStatus.NOT_FOUND);

    }

	@RequestMapping("/api/accounts/{accountid}")
    public ResponseEntity<AccountView> getAccount(@PathVariable(value="accountid") String accountid) {
    	log.info("Account ID : " + accountid);

    	AccountView accountView = service.getAccount(accountid);

    	return accountView != null ? new ResponseEntity<AccountView>(accountView, HttpStatus.OK) : new ResponseEntity<AccountView>(HttpStatus.NOT_FOUND);

    }


}
