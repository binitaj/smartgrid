package com.binitajha.smartgrid.accounts.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String line1;
    private String zip;
    private String city;
    private String state;
    private double lat;
    private double lon;
    private String geohash;


}
