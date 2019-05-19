package com.binitajha.smartgrid.reads.repos.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity(name = "Register")
@Table(name = "register")
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	private double v;
	private String rQ;
	private Date eT;
	private Date iT;
	private double mul;
	private String m;
	private String iL;
	private String dS;

	@ManyToOne
	@JoinColumn(name = "readId", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Read read;

}