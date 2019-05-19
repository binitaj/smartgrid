package com.binitajha.smartgrid.reads.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.binitajha.smartgrid.reads.view.DeviceId;
import com.binitajha.smartgrid.reads.view.DeviceView;

@Component
public class DeviceRESTClient {

	@Value("services.device.uri")
	private String deviceURI;
	
	private final RestTemplate restTemplate;

	public DeviceRESTClient(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}

	public DeviceView callDeviceREST(Long deviceId) {
		DeviceView dvResp = restTemplate.getForObject(String.format("%s/%d", deviceURI, deviceId), DeviceView.class);

		final DeviceView dv = dvResp == null ? defaultDevice(deviceId) : dvResp;
		return dv;
	}
	
	private DeviceView defaultDevice(Long deviceId) {
		String deviceIdStr = String.valueOf(deviceId);
		return new DeviceView(new DeviceId(deviceId, "", ""), 0, deviceIdStr, deviceIdStr, 0, "TBD", "-", "TBD");
	}

}
