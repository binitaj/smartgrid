package com.binitajha.smartgrid.reads.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;

import com.binitajha.smartgrid.reads.view.DeviceView;


@RunWith(SpringJUnit4ClassRunner.class)
@RestClientTest(DeviceRESTClient.class)
@ContextConfiguration(classes= {DeviceRESTClient.class })
public class DeviceRESTClientTest {
	
	@TestConfiguration
	static class TestConfig {

		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder();
		}
		
	}
	
    @Autowired
    private MockRestServiceServer server;
    
    @Autowired
    private DeviceRESTClient restClient;
	
    @Before
    public void setUp() throws Exception {
        
    	ReflectionTestUtils.setField(restClient, "deviceURI", "/api/devices");
    	String detailsString = "{\"_id\":{\"deviceNumber\":75118300,\"meterType\":\"WATER\",\"deviceType\":\"METER\"},\"meterSize\":0.7,\"account\":\"032-002790-01\",\"amrIdentifier\":\"86696858\",\"meterid_2\":82550377,\"serviceRoute\":\"032\",\"usageUom\":\"GAL\",\"status\":\"ACTIVE\"}";
         
        this.server.expect(requestTo("/api/devices/75118300")).andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
        this.server.expect(requestTo("/api/devices/12345")).andRespond(withNoContent());//detailsString, MediaType.APPLICATION_JSON));
        this.server.expect(requestTo("/api/devices/12346")).andRespond(withBadRequest());//detailsString, MediaType.APPLICATION_JSON));
    }
	
	
	@Test
	public void testCallDeviceREST() {
		DeviceView res = restClient.callDeviceREST(75118300l);
		assertNotNull(res);
		res = restClient.callDeviceREST(12345l);
		assertNotNull(res);
	}

}
