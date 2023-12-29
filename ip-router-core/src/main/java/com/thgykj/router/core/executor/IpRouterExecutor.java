package com.thgykj.router.core.executor;


import com.thgykj.router.core.biz.AdminBiz;
import com.thgykj.router.core.biz.client.AdminBizClient;
import com.thgykj.router.core.server.EmbedServer;
import com.thgykj.router.core.util.IpUtil;
import com.thgykj.router.core.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.thgykj.router.core.util.IpUtil.getIp;

/**
 * Description IpRouterExecutor
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public class IpRouterExecutor {
    private static final Logger logger = LoggerFactory.getLogger(IpRouterExecutor.class);

    // ---------------------- param ----------------------
    private String adminAddresses;
    private String accessToken;
    private String appname;
    private String address;
    private String ip;
    private int port;
    private String logPath;
    private int logRetentionDays;

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setAppname(String appname) {
        this.appname = appname;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
    public void setLogRetentionDays(int logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }

    // ---------------------- start + stop ----------------------

    public void start() throws Exception {

        // todo some code need study
        // 和远程服务端建立连接
        initAdminBizList(adminAddresses, accessToken);
        // init executor-server
        // 初始化 netty 服务器
        initEmbedServer(address, ip, port, appname, accessToken);
    }


    // ---------------------- admin-client (rpc invoker) ----------------------
    private static List<AdminBiz> adminBizList;

    private void initAdminBizList(String adminAddresses, String accessToken) throws Exception {
        if (adminAddresses!=null && adminAddresses.trim().length()>0) {
            for (String address: adminAddresses.trim().split(",")) {
                if (address!=null && address.trim().length()>0) {

                    // 初始化远程机器的 ip + accessToken
                    AdminBiz adminBiz = new AdminBizClient(address.trim(), accessToken);

                    if (adminBizList == null) {
                        adminBizList = new ArrayList<AdminBiz>();
                    }
                    // 将 adminBiz添加到 list
                    adminBizList.add(adminBiz);
                }
            }
        }
    }

    public static List<AdminBiz> getAdminBizList(){
        return adminBizList;
    }

    // ---------------------- executor-server (rpc provider) ----------------------
    private EmbedServer embedServer = null;

    private void initEmbedServer(String address, String ip, int port, String appname, String accessToken) throws Exception {

        // fill ip port
        // 获取端口号  默认9999 校验 不合格 递减端口
        port = port>0?port: NetUtil.findAvailablePort(9999);
        // 解析本机地址   ip.getIp() 获取当前机器的ip地址
        ip = (ip!=null&&ip.trim().length()>0)?ip: getIp();

        // generate address
        // address 通过解析ip得到
        if (address==null || address.trim().length()==0) {
            String ip_port_address = IpUtil.getIpPort(ip, port);   // registry-address：default use address to registry , otherwise use ip:port if address is null
            // 当前机器的地址
            address = "http://{ip_port}/".replace("{ip_port}", ip_port_address);
            logger.info("本地的ip地址>>>{}",address);
        }

        // accessToken
        // 登入的accessToken
        if (accessToken==null || accessToken.trim().length()==0) {
            logger.warn(">>>>>>>>>>> ip-router accessToken is empty. To ensure system security, please set the accessToken.");
        }

        // start
        embedServer = new EmbedServer();
        embedServer.start(address, port, appname, accessToken);
    }




}
