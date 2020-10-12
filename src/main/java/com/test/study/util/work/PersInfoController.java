package com.test.study.util.work;


import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.*;

@RestController
public class PersInfoController {


	final static int capacity = 100;


	@Value(value = "${persCore}")
	private int core;

	final LinkedBlockingQueue QUEUE = new LinkedBlockingQueue<>(capacity);

	private ExecutorService executor = null;

	private PersInfoRepository persInfoRepository;

	private List<PersInfo> persInfoList = null;

	private JdbcTemplate jdbcTemplate;


	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbc) {
		jdbcTemplate = jdbc;
	}

	@Autowired
	public void SetPersInfoRepository(PersInfoRepository repository) {
		persInfoRepository = repository;
	}


	//	@Scheduled(fixedRate = 10000)
//	@Scheduled(cron = "0/10 * 0,1,2,3,4,5,16,17,18,19,20,21,22,23 * * ? ")
	@Scheduled(cron = "${cron}")
	public void PersInfo() throws InterruptedException, ExecutionException {

/*		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = localDateTime.format(dtf);
		System.out.println(now);*/


		if (executor == null) {
			executor = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, QUEUE);
		}

		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		if (persInfoList == null || persInfoList.size() == 0) {
			Pageable pageable = PageRequest.of(0, 50_0000);
			persInfoList = persInfoRepository.findByBzOrderByDjrqDesc("-1", pageable);
		}

		for (int i = 0; i < capacity - QUEUE.size(); i++) {
			PersInfoCallable callable = new PersInfoCallable(persInfoList.get(0), jdbcTemplate, persInfoRepository);
//			System.out.println(persInfoList.get(0));
			service.submit(callable);
			Future future = service.poll();
			if (future != null) {
				System.out.println(future.get());
			}
			persInfoList.remove(0);
		}

		System.out.println(((ThreadPoolExecutor) executor).getActiveCount());

	}

	@RequestMapping("/test")
	public void test() throws ExecutionException, InterruptedException {
//		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		if (persInfoList == null || persInfoList.size() == 0) {
			Pageable pageable = PageRequest.of(0, 50);
			persInfoList = persInfoRepository.findByBzOrderByDjrqDesc("-1", pageable);
		}

		for (int i = 0; i < 6; i++) {
			PersTestCalable callable = new PersTestCalable(persInfoList.get(0).getZjhm(), persInfoRepository);
			Future future = executor.submit(callable);
			System.out.println(persInfoList.get(0));
			future.get();
			persInfoList.remove(0);
		}
	}
}
