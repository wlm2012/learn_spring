package com.test.study.springInAction.chapter1.javaConfig;

import com.test.study.springInAction.chapter1.autoconfig.CompactDisc;

public class CDPlayer {

	public CDPlayer(CompactDisc compactDisc) {
		System.out.println("play");
		System.out.println(compactDisc == null);
	}
}
