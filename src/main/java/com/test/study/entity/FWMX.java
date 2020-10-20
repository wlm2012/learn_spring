package com.test.study.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "FWMX")
public class FWMX {

	@Id
	private int id;
	private String distCity;
	private String crtBrchenno;
	private String fullName;
	private String certNo;
	private String houseLoc;
	private double structArea;
	private String matDesc;
	private Date bkkDate;
	private String xingming;
	private String zjhm;
	private String grzh;
	private double tqje;
	private String regTlr;
	private String attCertNo;
	private String attNo;
	private String attRela;
	private Date attDate;

}
