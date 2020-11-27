package com.test.study;


import com.test.study.springInAction.chapter4.ConcertConfig;
import com.test.study.springInAction.chapter4.Master;
import com.test.study.springInAction.chapter4.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConcertConfig.class)
public class AspectTest {

	private Performance performance;

	@Autowired
	public void setPerformance(Performance performance) {
		this.performance = performance;
	}

	@Test
	public void Aspect() {

		performance.getOut();
		performance.perform(5);
	}

	@Test
	public void AspectNo() {
		Performance performance1=new Master();
		performance1.getOut();
		performance1.perform(5);
	}

}
