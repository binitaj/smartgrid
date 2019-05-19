package com.binitajha.smartgrid.accounts.repos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Device")
@Table(name = "device")
public class Device {


	@Id
    private Long deviceNumber;
    private String meterType;
    private String deviceType;

    private double meterSize;

    @ManyToOne
    @JoinColumn(name="accountId")
    @EqualsAndHashCode.Exclude private Account account;
    private String amrIdentifier;
    private String meterid_2;
    private String serviceRoute;
    private String usageUom;
    private String status;

}
