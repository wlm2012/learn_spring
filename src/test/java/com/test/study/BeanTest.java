package com.test.study;


import com.test.study.springInAction.chapter1.autoconfig.CompactDisc;
import com.test.study.springInAction.chapter1.javaConfig.CDPlayer;
import com.test.study.springInAction.chapter1.javaConfig.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class BeanTest {

	private CompactDisc compactDisc;

	private CDPlayer cdPlayer;

	@Autowired
	public void setCompactDisc(CompactDisc compactDisc) {
		this.compactDisc = compactDisc;
	}

	@Autowired
	public void setCdPlayer(CDPlayer cdPlayer) {
		this.cdPlayer = cdPlayer;
	}

	@Test
	public void cdTestNull() {
		assertNotNull(compactDisc);
	}

	@Test
	public void CDPlayNull() {
		assertNotNull(cdPlayer);
	}
}
