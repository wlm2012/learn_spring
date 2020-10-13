package com.test.study.util.work;

import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import com.test.study.util.StringUtil.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

public class PersTestCalable implements Callable {


	private PersInfo persInfo;

	private JdbcTemplate jdbcTemplate;

	private PersInfoRepository persInfoRepository;


	public PersTestCalable(PersInfo info, JdbcTemplate jdbcTemplate, PersInfoRepository persInfoRepository) {
		persInfo = info;
		this.jdbcTemplate = jdbcTemplate;
		this.persInfoRepository = persInfoRepository;
	}

	@Override
	public Object call() {
		String name = persInfo.getXingming();
		String zjhm = persInfo.getZjhm();
		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT NAME_MAN,NAME_WOMAN,OP_TYPE,CERT_NUM_MAN,CERT_NUM_WOMAN  FROM MZ_RC_MARRY_QD WHERE (CERT_NUM_MAN='" + zjhm + "' OR CERT_NUM_WOMAN='" + zjhm + "') ORDER BY OP_DATE DESC  FETCH FIRST 1 ROW ONLY");
		if (list.size() > 0 && list.get(0).get("op_type").toString().contains("A")) {
			String cert_num_man = list.get(0).get("cert_num_man").toString();
			String cert1 = "";
			String name1 = "";
			if (zjhm.equals(cert_num_man)) {
				cert1 = list.get(0).get("cert_num_woman").toString();
				name1 = list.get(0).get("name_woman").toString();
			} else {
				cert1 = list.get(0).get("cert_num_man").toString();
				name1 = list.get(0).get("name_man").toString();
			}
			Optional<PersInfo> woman = persInfoRepository.findById(cert1);
			if (woman.isPresent()) {
				PersInfo peiou = woman.get();
				if (!"000000".equals(peiou.getDjgy()) || StringUtil.isEmpty(peiou.getBeiz())) {
					peiou.setBeiz(zjhm);
					peiou.setEmail(name);
					peiou.setDjgy("000000");
					persInfoRepository.saveAndFlush(peiou);
				}
			}
			persInfo.setBeiz(cert1);
			persInfo.setEmail(name1);
			persInfo.setMz("00");
		}
		persInfo.setDjgy("000000");
		persInfoRepository.saveAndFlush(persInfo);
		return null;
	}
}
