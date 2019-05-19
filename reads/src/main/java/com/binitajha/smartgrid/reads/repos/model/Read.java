package com.binitajha.smartgrid.reads.repos.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "Read")
@Table(name = "read")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedEntityGraph(name = "Read.detail", attributeNodes = { @NamedAttributeNode("registers"),
		@NamedAttributeNode("intervals") })
public class Read {
	@Id
	private String id;

	private Long deviceId;

	private Long radioId;
	private String commodity;
	private Date createdAt;
	private Date lastUpdatedAt;

	@OneToMany(mappedBy = "read", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private Set<Register> registers = new HashSet<>();

	@OneToMany(mappedBy = "read", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	private Set<Interval> intervals = new HashSet<>();

	public void addRegister(Register register) {
		this.registers.add(register);
		register.setRead(this);
	}

	public void addInterval(Interval interval) {
		this.intervals.add(interval);
		interval.setRead(this);
	}

	public void removeRegister(Register register) {
		this.registers.remove(register);
		register.setRead(null);
	}

	public void removeInterval(Interval interval) {
		this.intervals.remove(interval);
		interval.setRead(null);
	}

}
