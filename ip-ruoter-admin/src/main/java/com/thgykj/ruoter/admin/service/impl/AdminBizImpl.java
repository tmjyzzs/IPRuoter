package com.thgykj.ruoter.admin.service.impl;

import com.thgykj.router.core.biz.AdminBiz;
import com.thgykj.router.core.biz.model.HandleCallbackParam;
import com.thgykj.router.core.model.RegistryParam;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.ruoter.admin.core.thread.JobRegistryHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description 本地调用接口实现
 * DATA 2024-01-02
 *
 * @Author ttt
 */
@Service
public class AdminBizImpl  implements AdminBiz {


    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registry(registryParam);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return null;
    }

    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return null;
    }
}
