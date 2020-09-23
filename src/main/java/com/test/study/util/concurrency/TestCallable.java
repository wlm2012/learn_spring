package com.test.study.util.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TestCallable implements Callable {

	private long time;

	public void setTime(long time) {
		this.time = time;
	}


	@Override
	public Object call() throws Exception {
		TimeUnit.SECONDS.sleep(time);
		return 10 / time + "";
	}
}
