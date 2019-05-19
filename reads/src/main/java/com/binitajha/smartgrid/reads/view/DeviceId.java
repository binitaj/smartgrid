package com.binitajha.smartgrid.reads.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceId {
	private long deviceNumber;
	private String meterType;
	private String deviceType;
}