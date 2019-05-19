package com.binitajha.smartgrid.reads.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.binitajha.smartgrid.reads.adapter.ReadMapper;
import com.binitajha.smartgrid.reads.repos.model.Interval;
import com.binitajha.smartgrid.reads.repos.model.Read;
import com.binitajha.smartgrid.reads.repos.model.Register;
import com.binitajha.smartgrid.reads.service.ReadsService;
import com.binitajha.smartgrid.reads.view.ReadView;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ReadRepositoryTest {

	public static final String REPORT_NUMBER = "CEDAR-IntervalReport.20181208120000.csv;3049";

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ReadRepository repo;

	@Mock
	static ReadsService readService;
	
	@TestConfiguration
	static class TestConfig {
		
		@Bean
		public ReadsService readService() {
			return readService;
		}

	}
	
	ReadMapper rm = new ReadMapper();

	private final static java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZ");

	
	
	@Test
	public void whenFindByReadId_thenReturnRead() throws ParseException {
		System.out.println(toDate("2019-04-08T23:00:00.000-0400"));
		Long deviceId = 1000l;
		Read read = createRead(deviceId.toString());
		addRegisters(read);
		addIntervals(read);
//	    entityManager.persist(read);

		repo.save(read);

//	    entityManager.flush();
		// when
		Optional<Read> found = repo.findById(read.getId());
		// then
		assertTrue(found.isPresent());
		Read newOne = found.get();
		assertEquals(2, newOne.getIntervals().size());
		assertEquals(2, newOne.getRegisters().size());

		List<Read> reads = repo.findPageByDeviceId(deviceId, PageRequest.of(0, 10));
		assertEquals(1, reads.size());
		assertEquals(2, reads.get(0).getIntervals().size());
		assertEquals(2, reads.get(0).getRegisters().size());

		ReadView rv = rm.to(reads.get(0));
		System.err.println(rv);

	}

	public static Read createRead(String deviceId) throws ParseException {
		Read read = new Read();
		read.setId("35653439;85967588;20181208");
		read.setDeviceId(new Long(deviceId));
		read.setRadioId(85967588l);
		read.setCommodity("WATER");
		read.setCreatedAt(toDate("2019-04-08T20:00:00.000-0400"));
		read.setLastUpdatedAt(toDate("2019-04-09T11:18:47.889-0400"));
		return read;
	}

	public static void addRegisters(Read read) throws ParseException {
		Register register1 = new Register();
		register1.setV(0.0);
		register1.setRQ("R");
		register1.setET(toDate("2018-12-07T20:00:00.000-0500"));
		register1.setIT(toDate("2018-12-08T13:15:02.813-0500"));
		register1.setMul(1.0);
		register1.setM("GAL");
		register1.setIL("");
		register1.setDS(REPORT_NUMBER);

		Register register2 = new Register();
		register2.setV(0.0);
		register2.setRQ("R");
		register2.setET(toDate("2018-12-08T08:00:00.000-0500"));
		register2.setIT(toDate("2018-12-08T13:15:02.813-0500"));
		register2.setMul(1.0);
		register2.setM("GAL");
		register2.setIL("");
		register2.setDS(REPORT_NUMBER);

		read.addRegister(register1);
		read.addRegister(register2);
	}

	public static void addIntervals(Read read) throws ParseException {
		Interval interval1 = new Interval();
		interval1.setV(628016.5);
		interval1.setRQ("R");
		interval1.setET(toDate("2018-12-08T01:00:00.000-0500"));
		interval1.setIT(toDate("2018-12-08T13:15:03.018-0500"));
		interval1.setMul(1.0);
		interval1.setM("GAL");
		interval1.setIL("");
		interval1.setDS("CEDAR-IntervalReport.20181208120000.csv;3047");

		read.addInterval(interval1);

		Interval interval2 = new Interval();
		interval2.setV(628016.5);
		interval2.setRQ("R");
		interval2.setET(toDate("2018-12-08T12:00:00.000-0500"));
		interval2.setIT(toDate("2018-12-08T13:15:03.018-0500"));
		interval2.setMul(1.0);
		interval2.setM("GAL");
		interval2.setIL("");
		interval2.setDS("CEDAR-IntervalReport.20181208120000.csv;3047");
		read.addInterval(interval2);
	}

	public static java.sql.Date toDate(String date) throws ParseException {
		return new Date(sdf.parse(date).getTime());
	}
}
