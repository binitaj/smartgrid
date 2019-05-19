package com.binitajha.smartgrid.reads.adapter;

import java.util.stream.Collectors;

import com.binitajha.smartgrid.reads.repos.model.Interval;
import com.binitajha.smartgrid.reads.repos.model.Read;
import com.binitajha.smartgrid.reads.repos.model.Register;
import com.binitajha.smartgrid.reads.view.ReadView;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class ReadMapper {
	static private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	static {
		mapperFactory.classMap(Read.class, ReadView.class).field("deviceId", "device._id").field("id", "_id")
				.byDefault().register();
		mapperFactory.classMap(ReadView.class, Read.class).field("device._id", "deviceId").field("_id", "id")
				.byDefault().register();

		mapperFactory.classMap(Register.class, com.binitajha.smartgrid.reads.view.Register.class).byDefault().register();
		mapperFactory.classMap(com.binitajha.smartgrid.reads.view.Register.class, Register.class).byDefault().register();

		mapperFactory.classMap(Interval.class, com.binitajha.smartgrid.reads.view.Interval.class).byDefault().register();
		mapperFactory.classMap(com.binitajha.smartgrid.reads.view.Interval.class, Interval.class).byDefault().register();

	}

	public ReadView to(Read a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		ReadView rv = mapper.map(a, ReadView.class);
		rv.setIntervals(a.getIntervals().stream().map(interval -> this.to(interval)).collect(Collectors.toList()));
		rv.setRegisters(a.getRegisters().stream().map(register -> this.to(register)).collect(Collectors.toList()));
		return rv;
	}

	public Read to(ReadView a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, Read.class);
	}

	public com.binitajha.smartgrid.reads.view.Register to(Register a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, com.binitajha.smartgrid.reads.view.Register.class);
	}

	public Register to(com.binitajha.smartgrid.reads.view.Register a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, Register.class);
	}

	public com.binitajha.smartgrid.reads.view.Interval to(Interval a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, com.binitajha.smartgrid.reads.view.Interval.class);
	}

	public Interval to(com.binitajha.smartgrid.reads.view.Interval a) {
		MapperFacade mapper = mapperFactory.getMapperFacade();
		return mapper.map(a, Interval.class);
	}

}
