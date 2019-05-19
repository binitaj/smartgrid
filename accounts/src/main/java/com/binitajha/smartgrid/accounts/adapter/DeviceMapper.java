package com.binitajha.smartgrid.accounts.adapter;

import com.binitajha.smartgrid.accounts.repos.model.Device;
import com.binitajha.smartgrid.accounts.view.DeviceView;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class DeviceMapper {
	static private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	static {
		mapperFactory.classMap(Device.class, DeviceView.class).field("account.id", "account").field("deviceNumber", "_id.deviceNumber").field("meterType", "_id.meterType").field("deviceType", "_id.deviceType").byDefault().register();
		mapperFactory.classMap(DeviceView.class, Device.class).field("account", "account.id").field("_id.deviceNumber", "deviceNumber").field("_id.meterType", "meterType").field("_id.deviceType", "deviceType").byDefault().register();
	}

	public DeviceView to(Device a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, DeviceView.class);
	}

	public Device to(DeviceView a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, Device.class);
	}

}