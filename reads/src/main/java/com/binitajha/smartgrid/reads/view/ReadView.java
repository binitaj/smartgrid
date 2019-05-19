package com.binitajha.smartgrid.reads.view;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReadView {

	private String _id;
	private DeviceView device;
	private long radio;
	private String commodity;
	@JsonProperty(value = "date")
	private Date createdAt;
	@JsonProperty(value = "lastUpdate")
	private Date lastUpdatedAt;
	private List<Register> registers;
	private List<Interval> intervals;
}
