package com.thgykj.ruoter.admin.core.router.strategy;

import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.model.TriggerParam;
import com.thgykj.ruoter.admin.core.router.ExecutorRouter;

import java.util.List;

/**
 * Description
 * DATA 2024-01-03
 *
 * @Author ttt
 */
public class ExecutorRouteFirst extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList){
        return new ReturnT<String>(addressList.get(0));
    }

}
