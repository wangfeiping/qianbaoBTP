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

\# hyperledger fabric 成员服务membersrvc

```
docker run -d --name=membersrvc \
    --restart=unless-stopped \
    -p 7054:7054 \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -e CORE_PEER_ID=msrv \
    -e CORE_PEER_ADDRESSAUTODETECT=true \
    -e CORE_NOOPS_BLOCK_TIMEOUT=10 \
    yeasy/hyperledger-peer:0.6-dp membersrvc
```

\# hyperledger fabric 验证节点vp0

```
docker run -v /home/gychain/fabric/core.yaml:/go/src/github.com/hyperledger/fabric/peer/core.yaml \
    -v /home/gychain/fabric/examples/chaincode/go:/go/src/github.com/hyperledger/fabric/examples/chaincode/go/ \
    --name=vp0 \
    -e CORE_PEER_ID=node_vp0 \
    -e CORE_PBFT_GENERAL_N=4 \
    --net="host" \
    --restart=unless-stopped \
    -it \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -e CORE_LOGGING_LEVEL=debug \
    -e CORE_PEER_ADDRESSAUTODETECT=false \
    -e CORE_PEER_NETWORKID=dev \
    -e CORE_PEER_VALIDATOR_CONSENSUS_PLUGIN=pbft \
    -e CORE_PBFT_GENERAL_TIMEOUT_REQUEST=10s \
    yeasy/hyperledger-peer:0.6-dp peer node start
```

\# hyperledger fabric 验证节点vp1

\# hyperledger fabric 验证节点vp2

\# hyperledger fabric 验证节点vp3

### 参考资料

> https://github.com/yeasy/docker-hyperledger-peer


