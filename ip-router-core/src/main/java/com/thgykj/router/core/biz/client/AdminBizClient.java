package com.thgykj.router.core.biz.client;

import com.thgykj.router.core.biz.AdminBiz;
import com.thgykj.router.core.biz.model.HandleCallbackParam;
import com.thgykj.router.core.model.RegistryParam;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.util.XxlJobRemotingUtil;

import java.util.List;

/**
 * Description 远程客户端
 * 存放远程客户端的基础信息
 * DATA 2023-12-29
 *
 * @Author ttt
 */
public class AdminBizClient implements AdminBiz {

    public AdminBizClient() {
    }
    public AdminBizClient(String addressUrl, String accessToken) {
        this.addressUrl = addressUrl;
        this.accessToken = accessToken;

        // valid
        if (!this.addressUrl.endsWith("/")) {
            this.addressUrl = this.addressUrl + "/";
        }
    }

    private String addressUrl ;
    private String accessToken;
    private int timeout = 3;
    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return XxlJobRemotingUtil.postBody(addressUrl + "api/registry", accessToken, timeout, registryParam, String.class);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return null;
    }

    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return XxlJobRemotingUtil.postBody(addressUrl+"api/callback", accessToken, timeout, callbackParamList, String.class);
    }
}
