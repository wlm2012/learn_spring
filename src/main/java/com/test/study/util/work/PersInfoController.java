package com.test.study.util.work;


import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.CrosscuttingMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

@RestController
@Slf4j
public class PersInfoController {


	final static int capacity = 100;


	@Value(value = "${persCore}")
	private int core;

	final LinkedBlockingQueue QUEUE = new LinkedBlockingQueue<>(capacity);

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

/*		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String now = localDateTime.format(dtf);
		System.out.println(now);*/


		if (executor == null) {
			executor = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, QUEUE);
		}

//		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		if (persInfoList == null || persInfoList.size() == 0) {
			Pageable pageable = PageRequest.of(0, 50_0000);
			persInfoList = persInfoRepository.findByBzOrderByDjrqDesc("-1", pageable);
		}

		for (int i = 0; i < capacity - QUEUE.size(); i++) {
			if (persInfoList.size() > 0) {
				PersInfoCallable callable = new PersInfoCallable(persInfoList.get(0), jdbcTemplate, persInfoRepository);
				executor.execute(callable);
				persInfoList.remove(0);
			}
		}

		System.out.println(((ThreadPoolExecutor) executor).getActiveCount());

	}

	@RequestMapping("/test")
	public void test() throws ExecutionException, InterruptedException {
//		CompletionService<String> service = new ExecutorCompletionService<>(executor);

		if (executor == null) {
			executor = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, QUEUE);
		}

		if (persInfoList == null || persInfoList.size() == 0) {
			Pageable pageable = PageRequest.of(0, 1600);
			persInfoList = persInfoRepository.findByBzAndDjgyNotOrderByDjrqDesc("-1", "000000", pageable);
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
	public void logTest() {
		log.info("log");
	}


	@RequestMapping("/core")
	public void core(int num) {
		executor.setCorePoolSize(num);
		executor.setMaximumPoolSize(num);
	}


	@Scheduled(cron = "0 0 8 * * ?")
	public void changeCore() {
		executor.setCorePoolSize(1);
		executor.setMaximumPoolSize(1);
	}

	@Scheduled(cron = "0 0 19 * * ?")
	public void addCore() {
		executor.setCorePoolSize(5);
		executor.setMaximumPoolSize(5);
	}


}

