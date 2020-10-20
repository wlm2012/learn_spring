package com.test.study.util.work;

import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.*;

public class WdAuthDet {



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


	@Scheduled(cron = "${cron}")
	public void PersInfo() throws InterruptedException, ExecutionException {



		if (executor == null) {
			executor = new ThreadPoolExecutor(core, core, 60, TimeUnit.SECONDS, QUEUE);
		}

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
}
