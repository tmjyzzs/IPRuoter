package com.thgykj.ruoter.admin.controller;

import com.thgykj.router.core.biz.AdminBiz;
import com.thgykj.router.core.model.RegistryParam;
import com.thgykj.router.core.model.ReturnT;
import com.thgykj.router.core.util.GsonTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description 对外提供api
 * DATA 2024-01-02
 *
 * @Author ttt
 */

@Controller
@RequestMapping("/api")
public class JobApiController {

    @Resource
    private AdminBiz adminBiz;

    @RequestMapping("/{uri}")
    @ResponseBody
//    @PermissionLimit(limit=false)
    public ReturnT<String> api(HttpServletRequest request, @PathVariable("uri") String uri, @RequestBody(required = false) String data) {

        if ("registry".equals(uri)) {
            // 远程注册接口
            RegistryParam registryParam = GsonTool.fromJson(data, RegistryParam.class);
            return adminBiz.registry(registryParam);
        }

        return null;
    }


}
