package com.thgykj.router.core.biz;

import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.model.TriggerParam;

/**
 * Description TODO
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public interface ExecutorBiz {

    /**
     * beat
     * @return
     */
    public ReturnT<String> beat();

    /**
     * run
     * @param triggerParam
     * @return
     */
    public ReturnT<String> run(TriggerParam triggerParam);
}
