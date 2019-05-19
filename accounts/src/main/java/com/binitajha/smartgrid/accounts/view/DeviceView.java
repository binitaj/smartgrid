package com.binitajha.smartgrid.accounts.view;

import lombok.Data;

@Data
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
