package com.test.study.util.work;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class PersInfoController {


	private PersInfoRepository persInfoRepository;

	private JdbcTemplate jdbcTemplate;

	private RestTemplate restTemplate;

	final static int capacity = 5;

	final static LinkedBlockingQueue QUEUE = new LinkedBlockingQueue<>(capacity);

	final static ExecutorService service = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, QUEUE);

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
//		List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT NAME_MAN,NAME_WOMAN,OP_TYPE,CERT_NUM_MAN,CERT_NUM_WOMAN  FROM MZ_RC_MARRY_QD WHERE (CERT_NUM_MAN='" + "110108630623001" + "' OR CERT_NUM_WOMAN='" + "110108630623001" + "') ORDER BY OP_DATE DESC  FETCH FIRST 1 ROW ONLY");
//		System.out.println(list.get(0).get("op_type"));
//		System.out.println(list.get(0).get("name_man"));

		Pageable pageable = PageRequest.of(0, 50_0000);
		List<PersInfo> persInfoList = persInfoRepository.findByBzOrderByDjrqDesc("-1", pageable);
		System.out.println(persInfoList.size());
		System.out.println(persInfoList.get(0));

		for (int i = 0; i < capacity - QUEUE.size(); i++) {
			persInfoList.get(0);
			persInfoList.remove(0);
		}

//		System.out.println(postWl("刘忠林", "370281198505195712"));


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
			System.out.println(jsonObject.get("BODY").getAsJsonObject());
			JsonElement houseid = jsonObject.get("BODY").getAsJsonObject().get("realownerList").getAsJsonArray().get(0).getAsJsonObject().get("houseid");
			if (houseid.isJsonNull()) {
				result = "0";
			} else {
				result = "1";
				System.out.println(houseid);
			}
		}
		return result;
	}
}
