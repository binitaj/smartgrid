package com.binitajha.smartgrid.accounts.view;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class AccountView {
	@JsonProperty(value="_id")
    private String id;
    private String name;
    private Address address;
    private String bc;
    private String s;
    private String st;

}
