package com.binitajha.smartgrid.reads.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.binitajha.smartgrid.reads.adapter.ReadMapper;
import com.binitajha.smartgrid.reads.repos.ReadRepository;
import com.binitajha.smartgrid.reads.repos.model.Read;
import com.binitajha.smartgrid.reads.view.DeviceView;
import com.binitajha.smartgrid.reads.view.ReadView;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReadsService {

	@Autowired
	private ReadRepository readRepo;
	
	@Autowired
	private DeviceRESTClient restClient;

	private ReadMapper readMapper = new ReadMapper();

	public List<ReadView> getReads(Long deviceId, short pageNum) {
		log.info("Device ID : " + deviceId);

		List<Read> entityList = readRepo.findPageByDeviceId(deviceId, PageRequest.of(pageNum, 10));
		List<ReadView> list = mapToView(deviceId, entityList);

		return list;
	}

	private List<ReadView> mapToView(Long deviceId, List<Read> entityList) {
		final DeviceView dv = restClient.callDeviceREST(deviceId);

		List<ReadView> list = entityList.stream().map(entity -> {
			ReadView rv = readMapper.to(entity);
			rv.setDevice(dv);
			return rv;
		}).collect(Collectors.toList());
		return list;
	}


	protected List<ReadView> getAllReads(Long deviceId) {
		log.info("Device ID : " + deviceId);

		List<Read> entityList = readRepo.findByDeviceId(deviceId);
		List<ReadView> list = mapToView(deviceId, entityList);

		return list;
	}

}
