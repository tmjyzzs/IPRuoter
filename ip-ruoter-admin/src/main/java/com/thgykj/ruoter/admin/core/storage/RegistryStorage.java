package com.thgykj.ruoter.admin.core.storage;

import com.thgykj.router.core.thread.JobThread;
import com.thgykj.ruoter.admin.core.model.XxlJobRegistry;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Description 存放客户端的注册信息
 * DATA 2024-01-12
 *
 * @Author ttt
 */
public class RegistryStorage {

    // 不可以存放重复元素
    private static Set<XxlJobRegistry> RegistrySet = new HashSet<XxlJobRegistry>();


    public static void addXxlRegistry(XxlJobRegistry  xxlJobRegistry){
        RegistrySet.add(xxlJobRegistry);
    }

    public static XxlJobRegistry getXxlRegistry(){
        // TODO 先简单获取，后期需要判断是否为空，还可以添加轮询机制
          return RegistrySet.iterator().next();
    }
}
