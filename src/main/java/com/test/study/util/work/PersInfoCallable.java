package com.test.study.util.work;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;

public class PersInfoCallable implements Callable {

	private PersInfo persInfo;

	private JdbcTemplate jdbcTemplate;

	private PersInfoRepository persInfoRepository;


	public PersInfoCallable(PersInfo info, JdbcTemplate jdbcTemplate, PersInfoRepository persInfoRepository) {
		persInfo = info;
		this.jdbcTemplate = jdbcTemplate;
		this.persInfoRepository = persInfoRepository;
	}

	@Override
	public Object call() throws Exception {

		String name = persInfo.getXingming();
		String zjhm = persInfo.getZjhm();
		String result = postWl(name, zjhm);

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = localDateTime.format(dtf);

		if (!"1".equals(result)) {

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
				result = postWl(name1, cert1);
				Optional<PersInfo> woman = persInfoRepository.findById(cert1);
				if (woman.isPresent()) {
					PersInfo peiou = woman.get();
					peiou.setBz(result);
					peiou.setLastUpdateTime(now);
					peiou.setBeiz(zjhm);
					peiou.setEmail(name);
					peiou.setDjgy("000000");
					persInfoRepository.saveAndFlush(peiou);
					System.out.println(peiou);
				}
				persInfo.setBeiz(cert1);
				persInfo.setEmail(name1);
				persInfo.setMz("00");
			}
		}

		persInfo.setBz(result);
		persInfo.setLastUpdateTime(now);
		persInfo.setDjgy("000000");
		persInfoRepository.saveAndFlush(persInfo);
		System.out.println(persInfo);
		return null;
	}


	public String postWl(String name, String zjhm) {

		String result = "-2";
		// 设置超时时间
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(50 * 1000);
		requestFactory.setConnectTimeout(50 * 1000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// 组装body参数
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, String> body = new HashMap();
		body.put("name", name);
		body.put("cardno", zjhm);
		map.put("BODY", body);

		// 将map转为json串，放入restTemplate的参数对象中
		Gson gson = new Gson();
		String JsonData = gson.toJson(map);
		HttpEntity<String> request = new HttpEntity<>(JsonData, headers);

		// 发起请求
		String url = "http://172.16.234.24:12306/HttpServer/HTTP_RECIVECORE_SVR/OR007I0032";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
		if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
			JsonObject jsonObject = gson.fromJson(responseEntity.getBody(), JsonObject.class);
			String RET_CODE = jsonObject.get("SYS_HEAD").getAsJsonObject().get("TRAN_RET").getAsJsonArray().get(0)
					.getAsJsonObject().get("RET_CODE").getAsString();

			if ("00000000".equals(RET_CODE)) {
				JsonElement houseid = jsonObject.get("BODY").getAsJsonObject().get("realownerList").getAsJsonArray().get(0)
						.getAsJsonObject().get("houseid");
				if (houseid.isJsonNull()) {
					result = "0";
					System.out.println("houseid" + "    zjhm=" + zjhm + "   name=" + name);
				} else {
					result = "1";
					System.out.println(houseid + "    zjhm=" + zjhm + "   name=" + name);
				}
			}
		}
		System.out.println(result + "    zjhm=" + zjhm + "   name=" + name);
		return result;
	}
}
