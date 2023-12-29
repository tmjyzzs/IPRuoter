package com.thgykj.router.core.biz;

import com.thgykj.router.core.biz.model.HandleCallbackParam;
import com.thgykj.router.core.model.RegistryParam;
import com.thgykj.router.core.model.ReturnT;

import java.util.List;

/**
 * Description TODO
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public interface AdminBiz {
    // ---------------------- registry ----------------------

    /**
     * registry
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registryRemove(RegistryParam registryParam);

    /**
     * callback
     *
     * @param callbackParamList
     * @return
     */
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);

}
