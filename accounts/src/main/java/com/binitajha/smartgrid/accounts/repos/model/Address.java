package com.binitajha.smartgrid.accounts.repos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Address")
@Table(name = "address")
public class Address {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToOne(mappedBy = "address")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Account account;

    private String line1;
    private String zip;
    private String city;
    private String state;
    private double lat;
    private double lon;
    private String geohash;


	public Address(Long id, String line1, String zip, String city, String state, double lat, double lon, String geohash) {
		super();
		this.id = id;
		this.line1 = line1;
		this.zip = zip;
		this.city = city;
		this.state = state;
		this.lat = lat;
		this.lon = lon;
		this.geohash = geohash;
	}


}
