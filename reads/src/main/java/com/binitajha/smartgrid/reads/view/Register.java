package com.binitajha.smartgrid.reads.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Register {

	private double v;

	@JsonProperty(value = "rQ")
	private String rQ;
	@JsonProperty(value = "eT")
	private String eT;
	@JsonProperty(value = "iT")
	private String iT;
	private double mul;
	private String m;
	@JsonProperty(value = "iL")
	private String iL;
	@JsonProperty(value = "dS")
	private String dS;
}