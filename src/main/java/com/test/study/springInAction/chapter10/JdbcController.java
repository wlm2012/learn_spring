package com.test.study.springInAction.chapter10;


import com.test.study.entity.TPersInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JdbcController {

	private JdbcTPresInfoRepository jdbcTPresInfoRepository;


	@Autowired
	public void setJdbcTPresInfoRepository(JdbcTPresInfoRepository jdbc) {
		jdbcTPresInfoRepository = jdbc;
	}


	@RequestMapping("/JdbcController/{grzh}")
	public void jdbcTest(@PathVariable String grzh) {

		TPersInfo tPersInfo = jdbcTPresInfoRepository.findOne(grzh);
		System.out.println(tPersInfo.getXingming());
		System.out.println(tPersInfo.getGrzh());
		tPersInfo.setGrzh("1110");
		jdbcTPresInfoRepository.insertTPersInfo(tPersInfo);
	}
}
