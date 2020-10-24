package com.test.study;

import com.test.study.util.work.wenjianController;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class work {

    @Test
    public void nullTest() {
        String s = null;
        System.out.println("1,2,3".contains(s));
    }

    @Test
    public void wenjian() throws IOException {
        wenjianController test=new wenjianController();
        test.wenjian();
    }
}
