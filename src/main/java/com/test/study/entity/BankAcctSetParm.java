package com.test.study.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Table(name = "T_BANK_ACCT_SET_PARM")
public class BankAcctSetParm {

	@Id
	private String bankCls;

	private String noticeDepType;
	private String noticeDepAcct;
	private String fixDepType;
	private String fixDepAcct;
	private String regTlr;
	private Date regDate;
	private String rsv;
	private String rsv1;
	private String rsv2;

}
