package com.thgykj.ruoter.admin.core.conf;

import com.thgykj.ruoter.admin.core.alarm.JobAlarmer;
import com.thgykj.ruoter.admin.core.scheduler.XxlJobScheduler;
import com.thgykj.ruoter.admin.dao.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */

// 初始化xxlJob的配置
@Component
public class XxlJobAdminConfig implements InitializingBean, DisposableBean {

    private static XxlJobAdminConfig adminConfig = null;
    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }


    // ---------------------- XxlJobScheduler ----------------------

    private XxlJobScheduler xxlJobScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        xxlJobScheduler = new XxlJobScheduler();
        xxlJobScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        xxlJobScheduler.destroy();
    }


    // ---------------------- XxlJobScheduler ----------------------

    // conf

    @Value("${xxl.job.accessToken}")
    private String accessToken;


    @Value("${xxl.job.triggerpool.fast.max}")
    private int triggerPoolFastMax;

    @Value("${xxl.job.triggerpool.slow.max}")
    private int triggerPoolSlowMax;

    @Value("${xxl.job.logretentiondays}")
    private int logretentiondays;

    // dao, service

    @Resource
    private XxlJobInfoDao xxlJobInfoDao;



    @Resource
    private DataSource dataSource;
    @Resource
    private JobAlarmer jobAlarmer;



    public String getAccessToken() {
        return accessToken;
    }



    public int getTriggerPoolFastMax() {
        if (triggerPoolFastMax < 200) {
            return 200;
        }
        return triggerPoolFastMax;
    }

    public int getTriggerPoolSlowMax() {
        if (triggerPoolSlowMax < 100) {
            return 100;
        }
        return triggerPoolSlowMax;
    }

    public int getLogretentiondays() {
        if (logretentiondays < 7) {
            return -1;  // Limit greater than or equal to 7, otherwise close
        }
        return logretentiondays;
    }


    public XxlJobInfoDao getXxlJobInfoDao() {
        return xxlJobInfoDao;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public JobAlarmer getJobAlarmer() {
        return jobAlarmer;
    }

}
