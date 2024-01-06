package com.thgykj.router.core.biz.client;


import com.thgykj.router.core.biz.ExecutorBiz;
import com.thgykj.router.core.biz.model.LogResult;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.model.TriggerParam;
import com.thgykj.router.core.util.XxlJobRemotingUtil;

/**
 * admin api test
 *
 * @author xuxueli 2017-07-28 22:14:52
 */
public class ExecutorBizClient implements ExecutorBiz {

    public ExecutorBizClient() {
    }
    public ExecutorBizClient(String addressUrl, String accessToken) {
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
    public ReturnT<String> beat() {
        return XxlJobRemotingUtil.postBody(addressUrl+"beat", accessToken, timeout, "", String.class);
    }

//    @Override
//    public ReturnT<String> idleBeat(IdleBeatParam idleBeatParam){
//        return XxlJobRemotingUtil.postBody(addressUrl+"idleBeat", accessToken, timeout, idleBeatParam, String.class);
//    }

    @Override
    public ReturnT<String> run(TriggerParam triggerParam) {
        // 调用post请求远程访问 netty 中的接口    // addressUrl 有问题
        return XxlJobRemotingUtil.postBody(addressUrl + "run", accessToken, timeout, triggerParam, String.class);
    }

//    @Override
//    public ReturnT<String> kill(KillParam killParam) {
//        return XxlJobRemotingUtil.postBody(addressUrl + "kill", accessToken, timeout, killParam, String.class);
//    }
//
//    @Override
//    public ReturnT<LogResult> log(LogParam logParam) {
//        return XxlJobRemotingUtil.postBody(addressUrl + "log", accessToken, timeout, logParam, LogResult.class);
//    }

}
