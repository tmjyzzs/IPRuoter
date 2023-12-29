package com.thgykj.ruoter.admin.controller;

import com.thgykj.router.core.model.ReturnT;
import com.thgykj.ruoter.admin.core.thread.JobTriggerPoolHelper;
import com.thgykj.ruoter.admin.core.trigger.TriggerTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description TODO
 * DATA 2023-12-29
 *
 * @Author ttt
 */
@Controller
@RequestMapping("/jobinfo")
public class JobInfoController {

    @RequestMapping("/trigger")
    @ResponseBody
    //@PermissionLimit(limit = false)
    public ReturnT<String> triggerJob(int id, String executorParam, String addressList) {
        // force cover job param
        if (executorParam == null) {
            executorParam = "";
        }
        // 执行任务
        JobTriggerPoolHelper.trigger(id, TriggerTypeEnum.MANUAL, -1, null, executorParam, addressList);
        return ReturnT.SUCCESS;
    }
}
