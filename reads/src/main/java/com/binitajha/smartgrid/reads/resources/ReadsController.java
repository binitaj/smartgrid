package com.binitajha.smartgrid.reads.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binitajha.smartgrid.reads.service.ReadsService;
import com.binitajha.smartgrid.reads.view.ReadView;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class ReadsController {

	@Autowired
	private ReadsService readService;


	@RequestMapping("/api/reads?device=${device}")
	public ResponseEntity<List<ReadView>> getReads(@RequestParam(value = "device", defaultValue = "0") Long deviceId,
			@RequestParam(value = "page", defaultValue = "0") short pageNum) {
		log.info("Device ID : " + deviceId);
		List<ReadView> list = readService.getReads(deviceId, pageNum);
		// TO DO: fix retrieving inner classes. Test case ReadRepositoryTest fetches
		return list.size() == 0 ? new ResponseEntity<List<ReadView>>(list, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<ReadView>>(list, HttpStatus.OK);

	}

}
