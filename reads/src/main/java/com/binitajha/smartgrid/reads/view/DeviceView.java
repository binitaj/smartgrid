package com.binitajha.smartgrid.reads.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceView {

	private DeviceId _id;

	private double meterSize;
	private String account;
	private String amrIdentifier;
	private long meterid_2;
	private String serviceRoute;
	private String usageUom;
	private String status;
}
