package com.thgykj.router.core.biz.impl;

import com.thgykj.router.core.biz.ExecutorBiz;
import com.thgykj.router.core.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description TODO
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public class ExecutorBizImpl implements ExecutorBiz {

    private static Logger logger = LoggerFactory.getLogger(ExecutorBizImpl.class);
    @Override
    public ReturnT<String> beat() {
        return ReturnT.SUCCESS;
    }
}
