# ip-router

## 创造原因
1. 本来是想写一个简单的ip路由转发的工具。可以在公司的前端快速在工具上转发来连接不同的后端;
2. 看了一下netty 突发奇想 能不能写一个rpc的通信框架；
3. 开源的rpc框架都是推荐和注册中心一起使用，想编写一个轻量级的rpc框架。不需要注册中心的参与；
4. 在一些小型公司里面，公司里面都是一些springboot项目，springboot之间的调用。可能都是一些简单的调用。比例说多个项目都是共用一个用户体系。那么在解决这些项目调用同一个用户体系。通过http调用又让人觉得不够优雅，通过快一些开源的rpc框架，需要配合注册中心一起使用(有一些也不用)。但是还是希望能够自己编写一个简单易用的rpc框架；
5. 