package com.test.study.util.work;


import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PersInfoController {


	private PersInfoRepository persInfoRepository;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void SetPersInfoRepository(PersInfoRepository repository) {
		persInfoRepository = repository;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbc) {
		jdbcTemplate = jdbc;
	}


	@RequestMapping("/persInfo")
	public void PersInfo() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT NAME_MAN,NAME_WOMAN,OP_TYPE,CERT_NUM_MAN,CERT_NUM_WOMAN  FROM MZ_RC_MARRY_QD WHERE (CERT_NUM_MAN='" + "110108630623001" + "' OR CERT_NUM_WOMAN='" + "110108630623001" + "') ORDER BY OP_DATE DESC  FETCH FIRST 1 ROW ONLY");
		System.out.println(list.get(0).get("op_type"));
		System.out.println(list.get(0).get("name_man"));
	}
}
