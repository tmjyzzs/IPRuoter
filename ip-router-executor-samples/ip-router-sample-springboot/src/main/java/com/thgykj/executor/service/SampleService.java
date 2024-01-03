package com.thgykj.executor.service;

import com.thgykj.router.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * Description TODO
 * DATA 2024-01-03
 *
 * @Author ttt
 */
@Component
public class SampleService {

    @XxlJob("demoJobHandler")
    public void testAnno(){
        System.out.println("213123123");
    }
}
