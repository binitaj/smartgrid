package com.binitajha.smartgrid.reads.resources;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.binitajha.smartgrid.reads.service.ReadsService;
import com.binitajha.smartgrid.reads.view.ReadView;
import com.binitajha.smartgrid.reads.view.Register;


@RunWith(SpringRunner.class)
public class ReadsControllerTest {
	
	@Mock
	private ReadsService readsService;

	@InjectMocks public ReadsController rc;

	public static final String TEST_ACCT_ID = "032-002790-01";

    @Before
    public void setUp() throws Exception {
        String detailsString2 = "{\"_id\":{\"deviceNumber\":35653439,\"meterType\":\"WATER\",\"deviceType\":\"METER\"},\"meterSize\":0.75,"
        		+ "\"account\":\"032-002790-01\",\"amrIdentifier\":\"86696858\",\"meterid_2\":82550377,\"serviceRoute\":\"032\",\"usageUom\":\"GAL\",\"status\":\"ACTIVE\"}";
//        this.server.expect(requestTo("/services.device.uri/35653439")).andRespond(withSuccess(detailsString2, MediaType.APPLICATION_JSON));
        createResponse();
        
//		Mockito.when(repo.findByDeviceId(35653439l)).thenReturn(reads);
//		Mockito.when(repo.findPageByDeviceId(Mockito.any(Long.class), Mockito.any(Pageable.class))).thenReturn(reads);
    }


	private List<ReadView> createResponse() {
		List<ReadView> reads = new ArrayList<>();
        ReadView read = new ReadView();
        Register register = new Register();
        read.setRegisters(Collections.singletonList(register));
        reads.add(read);
        return reads;
	}


	@Test
	public void testGetReads() {
		Mockito.when(readsService.getReads(Mockito.anyLong(), Mockito.anyShort())).thenReturn(createResponse());
		
		ResponseEntity<List<ReadView>> response = rc.getReads(35653439l, (short)0);
		List<ReadView> reads = response.getBody();
		System.err.println("*** testGetReads: " + reads);
		assertNotNull(reads);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, reads.size());
		assertNotNull(reads.get(0));
		assertNotNull(reads.get(0).getRegisters());
		assertTrue(reads.get(0).getRegisters().size() > 0);

	}

}
