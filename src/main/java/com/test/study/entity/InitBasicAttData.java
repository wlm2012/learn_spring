package com.test.study.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "T_INIT_BASIC_ATT_DATA")
public class InitBasicAttData {

	@Id
	private String fjcode;

	private String fjname;


}
