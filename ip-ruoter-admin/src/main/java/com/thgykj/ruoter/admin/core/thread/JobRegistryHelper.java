package com.thgykj.ruoter.admin.core.thread;


import com.thgykj.router.core.enums.RegistryConfig;
import com.thgykj.router.core.model.RegistryParam;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.ruoter.admin.core.conf.XxlJobAdminConfig;
import com.thgykj.ruoter.admin.core.model.XxlJobGroup;
import com.thgykj.ruoter.admin.core.model.XxlJobRegistry;
import com.thgykj.ruoter.admin.core.storage.RegistryStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * job registry instance
 * @author xuxueli 2016-10-02 19:10:24
 */
public class JobRegistryHelper {
	private static Logger logger = LoggerFactory.getLogger(JobRegistryHelper.class);

	private static JobRegistryHelper instance = new JobRegistryHelper();

	public static JobRegistryHelper getInstance(){
		return instance;
	}

	private ThreadPoolExecutor registryOrRemoveThreadPool = null;
	private Thread registryMonitorThread;
	private volatile boolean toStop = false;

	public void start(){

		logger.info("创建各种线程信息>>>{}");
		// for registry or remove
		registryOrRemoveThreadPool = new ThreadPoolExecutor(
				2,
				10,
				30L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(2000),
				new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						return new Thread(r, "xxl-job, admin JobRegistryMonitorHelper-registryOrRemoveThreadPool-" + r.hashCode());
					}
				},
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						r.run();
						logger.warn(">>>>>>>>>>> xxl-job, registry or remove too fast, match threadpool rejected handler(run now).");
					}
				});

		// for monitor
		registryMonitorThread = new Thread(new Runnable() {
			@Override
			public void run() {

				logger.info(">>>>>>>>>>> xxl-job, job registry monitor thread stop");
			}
		});
		registryMonitorThread.setDaemon(true);
		registryMonitorThread.setName("xxl-job, admin JobRegistryMonitorHelper-registryMonitorThread");
		registryMonitorThread.start();
	}

	public void toStop(){
		toStop = true;

		// stop registryOrRemoveThreadPool
		registryOrRemoveThreadPool.shutdownNow();

		// stop monitir (interrupt and wait)
		registryMonitorThread.interrupt();
		try {
			registryMonitorThread.join();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}


	// ---------------------- helper ----------------------

	public ReturnT<String> registry(RegistryParam registryParam) {

		logger.info("远程注册信息>>>{}",registryParam);
		// valid
		if (!StringUtils.hasText(registryParam.getRegistryGroup())
				|| !StringUtils.hasText(registryParam.getRegistryKey())
				|| !StringUtils.hasText(registryParam.getRegistryValue())) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "Illegal Argument.");
		}

		// async execute
		registryOrRemoveThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				// 将注册的数据存放到数据库中
				// 修改将数据存放内存中
				XxlJobRegistry xxlJobRegistry = new XxlJobRegistry();
				xxlJobRegistry.setRegistryKey(registryParam.getRegistryKey());
				xxlJobRegistry.setRegistryValue(registryParam.getRegistryValue());
				xxlJobRegistry.setUpdateTime(new Date());
				RegistryStorage.addXxlRegistry(xxlJobRegistry);
//				int ret = XxlJobAdminConfig.getAdminConfig().getXxlJobRegistryDao().registryUpdate(registryParam.getRegistryGroup(), registryParam.getRegistryKey(), registryParam.getRegistryValue(), new Date());
//				if (ret < 1) {
//					XxlJobAdminConfig.getAdminConfig().getXxlJobRegistryDao().registrySave(registryParam.getRegistryGroup(), registryParam.getRegistryKey(), registryParam.getRegistryValue(), new Date());
//
//					// fresh
//					freshGroupRegistryInfo(registryParam);
//				}
			}
		});

		return ReturnT.SUCCESS;
	}



	private void freshGroupRegistryInfo(RegistryParam registryParam){
		// Under consideration, prevent affecting core tables
	}


}
