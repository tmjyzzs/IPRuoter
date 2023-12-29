package com.thgykj.router.core.biz.impl;

import com.thgykj.router.core.biz.ExecutorBiz;
import com.thgykj.router.core.executor.IpRouterExecutor;
import com.thgykj.router.core.handler.IJobHandler;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.model.TriggerParam;
import com.thgykj.router.core.thread.JobThread;
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

    @Override
    public ReturnT<String> run(TriggerParam triggerParam) {

        // load old：jobHandler + jobThread
        JobThread jobThread = IpRouterExecutor.loadJobThread(triggerParam.getJobId());
        IJobHandler jobHandler = jobThread!=null?jobThread.getHandler():null;
        String removeOldReason = null;
        return null;
    }
}
