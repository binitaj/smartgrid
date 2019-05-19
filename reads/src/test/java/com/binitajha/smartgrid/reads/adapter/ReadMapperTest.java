package com.binitajha.smartgrid.reads.adapter;

import java.text.ParseException;

import org.junit.Test;

import static com.binitajha.smartgrid.reads.repos.ReadRepositoryTest.*;
import static org.junit.Assert.assertEquals;

import com.binitajha.smartgrid.reads.repos.model.Read;
import com.binitajha.smartgrid.reads.view.ReadView;

public class ReadMapperTest {

	@Test
	public void test() throws ParseException {
		ReadMapper rm = new ReadMapper();

		Read read = createRead("0");
		addRegisters(read);
		addIntervals(read);
		ReadView rv = rm.to(read);
		assertEquals(REPORT_NUMBER, rv.getRegisters().iterator().next().getDS());
		assertEquals(REPORT_NUMBER, rv.getRegisters().iterator().next().getDS());

	}
}
