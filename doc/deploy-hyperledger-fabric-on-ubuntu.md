### 基本操作

\# 安装docker

> apt install docker.io

\# 查看docker 版本

> docker --version

\# 下载镜像并创建标签

> docker pull yeasy/hyperledger:0.6-dp  
> docker tag yeasy/hyperledger:0.6-dp hyperledger/fabric-baseimage:latest  
> docker pull yeasy/hyperledger-peer:0.6-dp  

\# 查看下载镜像

> docker images

\# 导出镜像

> docker save yeasy/hyperledger-peer:0.6-dp > ./yeasy_hyperledger-peer_0.6-dp.tar  
> docker save yeasy/hyperledger:0.6-dp > ./yeasy_hyperledger_0.6-dp.tar

\# 导入镜像

> docker load < ./yeasy_hyperledger-peer_0.6-dp.tar  
> docker load < ./yeasy_hyperledger_0.6-dp.tar

### 功能测试



### 参考资料

> https://github.com/yeasy/docker-hyperledger-peer


