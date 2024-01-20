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
        JobThread jobThread = IpRouterExecutor.loadJobThread(triggerParam.getExecutorHandler());
        IJobHandler jobHandler = jobThread != null ? jobThread.getHandler() : null;
        String removeOldReason = null;
        // valid：jobHandler + jobThread
        // 不同的运行模式
        // new jobhandler
        // 调用之前存入的handler进行处理
        IJobHandler newJobHandler = IpRouterExecutor.loadJobHandler(triggerParam.getExecutorHandler());

        // valid old jobThread
        if (jobThread != null && jobHandler != newJobHandler) {
            // change handler, need kill old thread
            removeOldReason = "change jobhandler or glue type, and terminate the old job thread.";

            jobThread = null;
            jobHandler = null;
        }

        // valid handler
        if (jobHandler == null) {
            jobHandler = newJobHandler;
            if (jobHandler == null) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, "job handler [" + triggerParam.getExecutorHandler() + "] not found.");
            }
        }

        // replace thread (new or exists invalid)
        // 第一次调用，将线程信息存放起来
        if (jobThread == null) {
            jobThread = IpRouterExecutor.registJobThread(triggerParam.getExecutorHandler(), jobHandler, removeOldReason);
        }

        // push data to queue
        ReturnT<String> pushResult = jobThread.pushTriggerQueue(triggerParam);
        return pushResult;
    }


}

