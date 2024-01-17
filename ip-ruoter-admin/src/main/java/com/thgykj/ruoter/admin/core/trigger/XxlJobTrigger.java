package com.thgykj.ruoter.admin.core.trigger;


import com.thgykj.router.core.biz.ExecutorBiz;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.model.TriggerParam;
import com.thgykj.router.core.util.ThrowableUtil;
import com.thgykj.ruoter.admin.core.conf.XxlJobAdminConfig;

import com.thgykj.ruoter.admin.core.model.XxlJobGroup;
import com.thgykj.ruoter.admin.core.model.XxlJobInfo;
import com.thgykj.ruoter.admin.core.model.XxlJobLog;
import com.thgykj.ruoter.admin.core.model.XxlJobRegistry;
import com.thgykj.ruoter.admin.core.router.ExecutorRouteStrategyEnum;
import com.thgykj.ruoter.admin.core.scheduler.XxlJobScheduler;
import com.thgykj.ruoter.admin.core.storage.RegistryStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * xxl-job trigger
 * Created by xuxueli on 17/7/13.
 */
public class XxlJobTrigger {
    private static Logger logger = LoggerFactory.getLogger(XxlJobTrigger.class);

    /**
     * trigger job
     *
     * @param jobId
     * @param triggerType
     * @param failRetryCount
     * 			>=0: use this param
     * 			<0: use param from job info config
     * @param executorShardingParam
     * @param executorParam
     *          null: use job param
     *          not null: cover job param
     * @param addressList
     *          null: use executor addressList
     *          not null: cover
     */
    public static void trigger(int jobId,
                               TriggerTypeEnum triggerType,
                               int failRetryCount,
                               String executorShardingParam,
                               String executorParam,
                               String addressList) {

        // load data
        // 从数据库中加载job的信息   -- 在使用job之前就需要页面中添加job的信息
//        XxlJobInfo jobInfo = XxlJobAdminConfig.getAdminConfig().getXxlJobInfoDao().loadById(jobId);
//         模拟数据库的参数加载
        XxlJobInfo jobInfo = new XxlJobInfo();
        jobInfo.setExecutorHandler("demoJobHandler");

        // todo 修改为从集合中获取注册信息
        // 模拟数据
//        jobInfo.
        if (jobInfo == null) {
            logger.warn(">>>>>>>>>>>> trigger fail, jobId invalid，jobId={}", jobId);
            return;
        }
        if (executorParam != null) {
            jobInfo.setExecutorParam(executorParam);
        }
        // 获取组id
        XxlJobRegistry xxlRegistry = RegistryStorage.getXxlRegistry();
        // 先替换掉数据库查询封装为 XxlJobGroup 对象
        XxlJobGroup group = new XxlJobGroup();
        if(xxlRegistry != null){
            group.setId(xxlRegistry.getId());
            group.setAddressList(xxlRegistry.getRegistryValue());

        }
//        XxlJobGroup group = XxlJobAdminConfig.getAdminConfig().getXxlJobGroupDao().load(jobInfo.getJobGroup());

        // cover addressList
        if (addressList!=null && addressList.trim().length()>0) {
            group.setAddressType(1);
            group.setAddressList(addressList.trim());
        }

        // sharding param
        int[] shardingParam = null;
        if (executorShardingParam!=null){
            String[] shardingArr = executorShardingParam.split("/");
            if (shardingArr.length==2 && isNumeric(shardingArr[0]) && isNumeric(shardingArr[1])) {
                shardingParam = new int[2];
                shardingParam[0] = Integer.valueOf(shardingArr[0]);
                shardingParam[1] = Integer.valueOf(shardingArr[1]);
            }
        }
        processTrigger(group, jobInfo, 0, triggerType, 0, group.getRegistryList().size());
//        if (ExecutorRouteStrategyEnum.SHARDING_BROADCAST== ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null)
//                && group.getRegistryList()!=null && !group.getRegistryList().isEmpty()
//                && shardingParam==null) {
//            for (int i = 0; i < group.getRegistryList().size(); i++) {
//                processTrigger(group, jobInfo, 0, triggerType, i, group.getRegistryList().size());
//            }
//        } else {
//            if (shardingParam == null) {
//                shardingParam = new int[]{0, 1};
//            }
//            // 执行任务
//            processTrigger(group, jobInfo, 0, triggerType, shardingParam[0], shardingParam[1]);
//        }

    }

    private static boolean isNumeric(String str){
        try {
            int result = Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param group                     job group, registry list may be empty
     * @param jobInfo
     * @param finalFailRetryCount
     * @param triggerType
     * @param index                     sharding index
     * @param total                     sharding index
     */
    private static void processTrigger(XxlJobGroup group, XxlJobInfo jobInfo, int finalFailRetryCount, TriggerTypeEnum triggerType, int index, int total){

//        // 1、save log-id
//        XxlJobLog jobLog = new XxlJobLog();
//        jobLog.setJobGroup(jobInfo.getJobGroup());
//        jobLog.setJobId(jobInfo.getId());
//        jobLog.setTriggerTime(new Date());
////        XxlJobAdminConfig.getAdminConfig().getXxlJobLogDao().save(jobLog);
//        logger.debug(">>>>>>>>>>> xxl-job trigger start, jobId:{}", jobLog.getId());

        // 2、init trigger-param
        TriggerParam triggerParam = new TriggerParam();
        triggerParam.setExecutorHandler(jobInfo.getExecutorHandler());
//        triggerParam.setExecutorParams(jobInfo.getExecutorParam());
//        triggerParam.setExecutorBlockStrategy(jobInfo.getExecutorBlockStrategy());
//        triggerParam.setExecutorTimeout(jobInfo.getExecutorTimeout());
//        triggerParam.setGlueType(jobInfo.getGlueType());
//        triggerParam.setGlueSource(jobInfo.getGlueSource());
        triggerParam.setBroadcastIndex(index);
        triggerParam.setBroadcastTotal(total);

        // 3、init address
        // 获取远程调用的接口
        String address = group.getRegistryList().get(0);
        runExecutor(triggerParam, address);
    }

    /**
     * run executor
     * @param triggerParam
     * @param address
     * @return
     */
    public static ReturnT<String> runExecutor(TriggerParam triggerParam, String address){
        ReturnT<String> runResult = null;
        try {
            ExecutorBiz executorBiz = XxlJobScheduler.getExecutorBiz(address);
            // 执行任务
            runResult = executorBiz.run(triggerParam);
        } catch (Exception e) {
            logger.error(">>>>>>>>>>> xxl-job trigger error, please check if the executor[{}] is running.", address, e);
            runResult = new ReturnT<String>(ReturnT.FAIL_CODE, ThrowableUtil.toString(e));
        }

        return runResult;
    }

}
