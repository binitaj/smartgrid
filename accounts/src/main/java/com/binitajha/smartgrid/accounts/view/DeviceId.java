package com.binitajha.smartgrid.accounts.view;

import lombok.Data;

@Data
public class DeviceId {
    private long deviceNumber;
    private String meterType;
    private String deviceType;
}