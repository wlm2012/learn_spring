package com.test.study.util.work;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import com.test.study.util.StringUtil.StringUtil;
import com.test.study.util.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
public class PersInfoCallable implements Runnable {

	private PersInfo persInfo;

	private JdbcTemplate jdbcTemplate;

	private PersInfoRepository persInfoRepository;


	public PersInfoCallable(PersInfo info, JdbcTemplate jdbcTemplate, PersInfoRepository persInfoRepository) {
		persInfo = info;
		this.jdbcTemplate = jdbcTemplate;
		this.persInfoRepository = persInfoRepository;
	}

	@Override
	public void run() {

		Optional<PersInfo> benren = persInfoRepository.findById(persInfo.getZjhm());
		benren.ifPresent(info -> persInfo = info);
		String bz = persInfo.getBz();
		if ("1".equals(bz) || "0".equals(bz)) {
			return;
		}

		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = localDateTime.format(dtf);

		String beiz = persInfo.getBeiz();
		String sfz = persInfo.getZjhm();
		String xm = persInfo.getXingming();
		String result = postWl(xm, sfz);

		if ("1".equals(result)) {
			if (!StringUtil.isEmpty(beiz)) {
				Optional<PersInfo> women = persInfoRepository.findById(persInfo.getBeiz());
				if (women.isPresent()) {
					PersInfo peiou = women.get();
					peiou.setBz(result);
					peiou.setLastUpdateTime(now);
					persInfoRepository.saveAndFlush(peiou);
//					System.out.println(peiou);
					log.info(peiou.toString());
				}
			}
		} else {
			if (!StringUtil.isEmpty(beiz)) {
				result = postWl(persInfo.getEmail(), persInfo.getBeiz());
				Optional<PersInfo> women = persInfoRepository.findById(persInfo.getBeiz());
				if (women.isPresent()) {
					PersInfo peiou = women.get();
					peiou.setBz(result);
					peiou.setLastUpdateTime(now);
					persInfoRepository.saveAndFlush(peiou);
//					System.out.println(peiou);
					log.info(peiou.toString());
				}
			}
		}

		persInfo.setBz(result);
		persInfo.setLastUpdateTime(now);
		persInfoRepository.saveAndFlush(persInfo);
//		System.out.println(persInfo);
		log.info(persInfo.toString());
		return;
	}


	public String postWl(String name, String zjhm) {
		Instant startTime = Instant.now();
		String result = "-2";
		// 设置超时时间
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(100 * 1000);
		requestFactory.setConnectTimeout(100 * 1000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		// 组装body参数
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, String> body = new HashMap();
		body.put("name", name);
		body.put("cardno", zjhm);
		map.put("BODY", body);


/*		// 组装body参数
		HashMap<String, Object> map = new HashMap<>();
		HashMap<String, Object> body = new HashMap();
		HashMap<String, Object> ParamJson = new HashMap<>();
		ParamJson.put("nameList", name);
		ParamJson.put("cardNoList", zjhm);
		body.put("ParamJson", ParamJson);
		map.put("BODY", body);*/

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
				} else {
					result = "1";
				}
			}
		}
//		System.out.println("time:   " + Duration.between(startTime, Instant.now()).toMillis() + "    result = " + result + "    zjhm=" + zjhm + "   name=" + name);
		log.info("time:   " + Duration.between(startTime, Instant.now()).toMillis() + "    result = " + result + "    zjhm=" + zjhm + "   name=" + name);
		return result;


/*		// 发起请求
		String url = "http://172.16.234.24:12306/HttpServer/HTTP_RECIVECORE_SVR/OR003I0040";
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
		if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
			JsonObject jsonObject = gson.fromJson(responseEntity.getBody(), JsonObject.class);
			String RET_CODE = jsonObject.get("SYS_HEAD").getAsJsonObject().get("TRAN_RET").getAsJsonArray().get(0)
					.getAsJsonObject().get("RET_CODE").getAsString();

			if ("00000000".equals(RET_CODE)) {
				result = jsonObject.get("BODY").getAsJsonObject().get("result").getAsString();
			}
		}
		System.out.println("time:   " + Duration.between(startTime, Instant.now()).toMillis() + "    result = " + result + "    zjhm=" + zjhm + "   name=" + name);
		return result;*/
	}
}
