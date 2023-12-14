package com.thgykj.executor.core.config;

import com.thgykj.router.core.executor.impl.IpRouterSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description config
 * DATA 2023-12-13
 *
 * @Author ttt
 */
@Configuration
public class IpRouterConfig {

    private Logger logger = LoggerFactory.getLogger(IpRouterConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.address}")
    private String address;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean
    public IpRouterSpringExecutor ipRouterSpringExecutor() {
        IpRouterSpringExecutor ipRouterSpringExecutor = new IpRouterSpringExecutor();
        ipRouterSpringExecutor.setAppname(appname);
        ipRouterSpringExecutor.setPort(port);
        ipRouterSpringExecutor.setAddress(address);
        ipRouterSpringExecutor.setAccessToken(accessToken);
        ipRouterSpringExecutor.setIp(ip);
        ipRouterSpringExecutor.setLogPath(logPath);
        ipRouterSpringExecutor.setAdminAddresses(adminAddresses);
        ipRouterSpringExecutor.setLogRetentionDays(logRetentionDays);
        return ipRouterSpringExecutor;
    }
}
