package com.test.study.util.work;


import com.test.study.entity.PersInfo;
import com.test.study.primaryMapper.PersInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PersInfoController {


	final static int capacity = 100;


	@Value(value = "${persCore}")
	private int core;

	@Value(value = "${MaxCore}")
	private int MaxCore;

	@Value(value = "${MinCore}")
	private int MinCore;

	final LinkedBlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<>(capacity);

	private ThreadPoolExecutor executor = null;

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


		if (executor == null) {
			executor = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, QUEUE);
		}

//		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		if (persInfoList == null || persInfoList.size() == 0) {
			Pageable pageable = PageRequest.of(0, 10_0000);
			persInfoList = persInfoRepository.findByBzOrderByDjrqDesc("-1", pageable);
		}

		for (int i = 0; i < capacity - QUEUE.size(); i++) {
			if (persInfoList.size() > 0) {
				PersInfoCallable callable = new PersInfoCallable(persInfoList.get(0), jdbcTemplate, persInfoRepository);
				executor.execute(callable);
				persInfoList.remove(0);
			} else {
				Pageable pageable = PageRequest.of(0, 10_0000);
				persInfoList = persInfoRepository.findByBzOrderByDjrqDesc("-1", pageable);
			}
		}

		System.out.println(executor.getActiveCount());

	}

	@RequestMapping("/test")
	public void test() throws ExecutionException, InterruptedException {
//		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		if (executor == null) {
			executor = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, QUEUE);
		}

		if (persInfoList == null || persInfoList.size() == 0) {
			Pageable pageable = PageRequest.of(0, 40_0000);
			persInfoList = persInfoRepository.findByDjgyNotOrderByDjrqDesc("000000", pageable);
			System.out.println(persInfoList.size());
		}

		while (persInfoList.size() > 0) {
			System.out.println(((ThreadPoolExecutor) executor).getActiveCount());
			if (capacity - QUEUE.size() > 0) {
				for (int i = 0; i < capacity - QUEUE.size(); i++) {
					if (persInfoList.size() > 0) {
						PersTestCalable callable = new PersTestCalable(persInfoList.get(0), jdbcTemplate, persInfoRepository);
						executor.submit(callable);
						persInfoList.remove(0);
					}

				}
			}

		}
	}


	@RequestMapping("/log1")
	public void logTest(@RequestParam String num) {
		System.out.println("num==" + num);
		log.info("log==" + num);
	}


	@RequestMapping("/core")
	public void core(int num) {
		System.out.println(num);
		System.out.println(executor.getCorePoolSize());

		if (num > executor.getCorePoolSize()) {
			executor.setMaximumPoolSize(num);
			executor.setCorePoolSize(num);
		} else {
			executor.setCorePoolSize(num);
			executor.setMaximumPoolSize(num);
		}

		System.out.println(executor.getCorePoolSize());
	}


	@Scheduled(cron = "0 0 8 * * ?")
	public void MinCore() {
		if (MinCore > executor.getCorePoolSize()) {
			executor.setMaximumPoolSize(MinCore);
			executor.setCorePoolSize(MinCore);
		} else {
			executor.setCorePoolSize(MinCore);
			executor.setMaximumPoolSize(MinCore);
		}

	}

	@Scheduled(cron = "0 0 19 * * ?")
	public void MaxCore() {
		if (MaxCore > executor.getCorePoolSize()) {
			executor.setMaximumPoolSize(MaxCore);
			executor.setCorePoolSize(MaxCore);
		} else {
			executor.setCorePoolSize(MaxCore);
			executor.setMaximumPoolSize(MaxCore);
		}

	}


}

