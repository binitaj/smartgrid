package com.binitajha.smartgrid.accounts.repos.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Account")
@Table(name = "account")
public class Account {
	@Id
	@Column(name="id")
    private String id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Address address;
    private String bc;
    private String s;
    private String st;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="account")
    private Set<Device> devices;

	public Account(String id, String name, Address address, String bc, String s, String st) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.bc = bc;
		this.s = s;
		this.st = st;
	}


}
