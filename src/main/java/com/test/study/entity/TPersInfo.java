package com.test.study.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "t_pers_info")
public class TPersInfo {

	@Id
	private String grzh;
	private String xingming;
	private String xmqp;
	private String zjhm;

	private String bz;
	private String beiz;
	private String email;
	private String mz;
	private String lastUpdateTime;
	private Date djrq;
	private String djgy;
}
