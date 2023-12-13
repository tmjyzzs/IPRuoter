package com.thgykj.router.core.biz;

import com.thgykj.router.core.model.RegistryParam;
import com.thgykj.router.core.model.ReturnT;

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

}
