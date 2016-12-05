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

\# vi /home/gychain/fabric/core_vp0.yaml

```
......
peer:
    \# The Address this Peer will listen on
    listenAddress: 192.168.1.51:7061
    \# The Address this Peer will bind to for providing services
    address: 192.168.1.51:7061
......
security:
    enabled: true
    enrollID: test_vp0
    enrollSecret: MwYpmSRjupbT
    privacy: true
......
```

\# enrollID 与enrollSecret 可用取值

```
fabric/membersrvc/membersrvc.yaml

......
test_vp0: 4 MwYpmSRjupbT
test_vp1: 4 5wgHK9qqYaPy
test_vp2: 4 vQelbRvja7cJ
test_vp3: 4 9LKqKH5peurL
......
```

\# 启动验证节点vp0

```
docker run -d -v /home/gychain/fabric/core_vp0.yaml:/go/src/github.com/hyperledger/fabric/peer/core.yaml \
    -v /home/gychain/fabric/chaincode:/go/src/github.com/chaincode/ \
    --name=vp0 \
    -e CORE_PEER_ID=vp0 \
    -e CORE_PBFT_GENERAL_N=4 \
    --restart=unless-stopped \
    --net="host" \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -e CORE_LOGGING_LEVEL=debug \
    -e CORE_PEER_ADDRESSAUTODETECT=false \
    -e CORE_PEER_NETWORKID=dev \
    -e CORE_PEER_VALIDATOR_CONSENSUS_PLUGIN=pbft \
    -e CORE_PBFT_GENERAL_TIMEOUT_REQUEST=10s \
    yeasy/hyperledger-peer:0.6-dp peer node start
```

\# hyperledger fabric 验证节点vp1

\# vi /home/gychain/fabric/core_vp1.yaml

```
cli:
    address: 0.0.0.0:7072
rest:
    address: 0.0.0.0:7070
......
peer:
    \# The Address this Peer will listen on
    listenAddress: 192.168.1.51:7071
    \# The Address this Peer will bind to for providing services
    address: 192.168.1.51:7071
    ......
    validator:
        ......
        events:
            address: 0.0.0.0:7073
......
security:
    enabled: true
    enrollID: test_vp1
    enrollSecret: 5wgHK9qqYaPy
    privacy: true
......
```

```
docker run -d -v /home/gychain/fabric/core_vp1.yaml:/go/src/github.com/hyperledger/fabric/peer/core.yaml \
    -v /home/gychain/fabric/chaincode:/go/src/github.com/chaincode/ \
    --name=vp1 \
    -e CORE_PEER_ID=vp1 \
    -e CORE_PBFT_GENERAL_N=4 \
    --restart=unless-stopped \
    --net="host" \
    -e CORE_LOGGING_LEVEL=debug \
    -e CORE_PEER_ADDRESSAUTODETECT=false \
    -e CORE_PEER_NETWORKID=dev \
    -e CORE_PEER_VALIDATOR_CONSENSUS_PLUGIN=pbft \
    -e CORE_PBFT_GENERAL_TIMEOUT_REQUEST=10s \
    -e CORE_PEER_DISCOVERY_ROOTNODE=192.168.1.51:7061 \
    yeasy/hyperledger-peer:0.6-dp peer node start
```

\# hyperledger fabric 验证节点vp2

\# vi /home/gychain/fabric/core_vp2.yaml

```
cli:
    address: 0.0.0.0:7082
rest:
    address: 0.0.0.0:7080
......
peer:
    \# The Address this Peer will listen on
    listenAddress: 192.168.1.51:7081
    \# The Address this Peer will bind to for providing services
    address: 192.168.1.51:7081
    ......
    validator:
        ......
        events:
            address: 0.0.0.0:7083
......
security:
    enabled: true
    enrollID: test_vp2
    enrollSecret: vQelbRvja7cJ
    privacy: true
......
```

```
docker run -d -v /home/gychain/fabric/core_vp2.yaml:/go/src/github.com/hyperledger/fabric/peer/core.yaml \
    -v /home/gychain/fabric/chaincode:/go/src/github.com/chaincode/ \
    --name=vp2 \
    -e CORE_PEER_ID=vp2 \
    -e CORE_PBFT_GENERAL_N=4 \
    --restart=unless-stopped \
    --net="host" \
    -e CORE_LOGGING_LEVEL=debug \
    -e CORE_PEER_ADDRESSAUTODETECT=false \
    -e CORE_PEER_NETWORKID=dev \
    -e CORE_PEER_VALIDATOR_CONSENSUS_PLUGIN=pbft \
    -e CORE_PBFT_GENERAL_TIMEOUT_REQUEST=10s \
    -e CORE_PEER_DISCOVERY_ROOTNODE=192.168.1.51:7061 \
    yeasy/hyperledger-peer:0.6-dp peer node start
```

\# hyperledger fabric 验证节点vp3

\# vi /home/gychain/fabric/core_vp3.yaml

```
cli:
    address: 0.0.0.0:7092
rest:
    address: 0.0.0.0:7090
......
peer:
    \# The Address this Peer will listen on
    listenAddress: 192.168.1.51:7091
    \# The Address this Peer will bind to for providing services
    address: 192.168.1.51:7091
    ......
    validator:
        ......
        events:
            address: 0.0.0.0:7093
......
security:
    enabled: true
    enrollID: test_vp3
    enrollSecret: 9LKqKH5peurL
    privacy: true
......
```

```
docker run -d -v /home/gychain/fabric/core_vp3.yaml:/go/src/github.com/hyperledger/fabric/peer/core.yaml \
    -v /home/gychain/fabric/chaincode:/go/src/github.com/chaincode/ \
    --name=vp3 \
    -e CORE_PEER_ID=vp3 \
    -e CORE_PBFT_GENERAL_N=4 \
    --restart=unless-stopped \
    --net="host" \
    -e CORE_LOGGING_LEVEL=debug \
    -e CORE_PEER_ADDRESSAUTODETECT=false \
    -e CORE_PEER_NETWORKID=dev \
    -e CORE_PEER_VALIDATOR_CONSENSUS_PLUGIN=pbft \
    -e CORE_PBFT_GENERAL_TIMEOUT_REQUEST=10s \
    -e CORE_PEER_DISCOVERY_ROOTNODE=192.168.1.51:7061 \
    yeasy/hyperledger-peer:0.6-dp peer node start
```

### 参考资料

> https://github.com/yeasy/docker-hyperledger-peer


