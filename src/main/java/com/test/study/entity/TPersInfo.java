package com.test.study.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_pers_info")
public class TPersInfo {

	@Id
	private String grzh;
	private String xingming;
	private String xmqp;
	private String xingbie;
	private String gddhhm;
	private String sjhm;
	private String zjlx;
	private String zjhm;
	private String csny;
	private String hyzk;
	private String zhiye;
	private String zhichen;
	private String zhiwu;
	private String xueli;
	private String yzbm;
	private String jtzz;
	private BigDecimal jtysr;
	private Date csrq;
	private String email;
	private String mz;
	private String beiz;
	private Date djrq;
	private String djgy;
	private String khjg;
	private String last_update_time;

}
