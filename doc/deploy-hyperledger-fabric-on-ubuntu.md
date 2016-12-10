### docker 化部署hyperledger fabric 集群

### 基本操作

\# 安装docker

> apt install docker.io

\# 安装docker-compose

> curl -L "https://github.com/docker/compose/releases/download/1.9.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose  
>  
> chmod +x /usr/local/bin/docker-compose  

\# 查看docker 版本

> docker --version

\# 下载镜像并创建标签

因为fabric 处于孵化期，每个版本变化较大，因此选用功能基本具备并且使用比较广泛的0.6 版本进行部署、开发和测试。  

> docker pull yeasy/hyperledger:0.6-dp  

fabric 部署链码时会使用一个基础镜像创建链码的镜像（好象可以在core.yaml 中配置），然后作为容器启动运行。因此需要使用指定的hyperledger/fabric-baseimage:latest 命名基础镜像。  

> docker tag yeasy/hyperledger:0.6-dp hyperledger/fabric-baseimage:latest  

fabric 节点镜像，包含成员服务程序membersrvc 与节点服务程序peer 。  

> docker pull yeasy/hyperledger-peer:0.6-dp  

\# 查看下载镜像

> docker images

\# 导出、导入镜像

为了避免每次部署重新下载镜像，可以将镜像导出为文件，在没有这些镜像的服务器直接通过导出数据文件导入镜像。  

> \# 导出  
> docker save yeasy/hyperledger-peer:0.6-dp > ./yeasy_hyperledger-peer_0.6-dp.tar  
> docker save yeasy/hyperledger:0.6-dp > ./yeasy_hyperledger_0.6-dp.tar
>  
> \# 导入  
> docker load < ./yeasy_hyperledger-peer_0.6-dp.tar  
> docker load < ./yeasy_hyperledger_0.6-dp.tar

### docker-compose 部署单机pbft membersrvc 集群

\# 下载相关项目文件

> https://github.com/yeasy/docker-compose-files

\# docker-compose 部署并启动fabric

> cd $docker-compose-files/hyperledger/0.6/pbft/  
> docker-compose -f 4-peers-with-membersrvc.yml up -d  

### 链码测试

```
\# 请求：部署链码并调用初始化方法（不通过注册用户）
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "jsonrpc": "2.0",
  "method": "deploy",
  "params": {
    "type": 1,
    "chaincodeID":{
        "path":"github.com/hyperledger/fabric/examples/chaincode/go/chaincode_example02"
    },
    "ctorMsg": {
        "args":["init", "a", "1000", "b", "2000"]
    }
  },
  "id": 1
}' 'http://172.18.0.6:7050/chaincode/'

\# 返回：
{"jsonrpc":"2.0","error":{"code":-32602,"message":"Invalid params","data":"Must supply username for chaincode when security is enabled."},"id":1}

\# 要求通过注册用户发送请求，正确。
```

```
\# 请求：注册用户
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "enrollId": "jim",
  "enrollSecret": "6avZQLwcUe9b"
}
' 'http://172.18.0.6:7050/registrar'

\# 返回：
{"OK":"Login successful for user 'jim'."}

\# 用户注册成功，正确。
```

```
\# 请求：部署链码并调用初始化方法（通过注册用户）
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "jsonrpc": "2.0",
  "method": "deploy",
  "params": {
    "type": 1,
    "chaincodeID":{
        "path":"github.com/hyperledger/fabric/examples/chaincode/go/chaincode_example02"
    },
    "ctorMsg": {
        "args":["init", "a", "1000", "b", "2000"]
    },
    "secureContext": "jim"
  },
  "id": 1
}' 'http://172.18.0.6:7050/chaincode/'

\# 返回：
{"jsonrpc":"2.0","result":{"status":"OK","message":"04233c6dd8364b9f0749882eb6d1b50992b942aa0a664182946f411ab46802a88574932ccd75f8c75e780036e363d52dd56ccadc2bfde95709fc39148d76f050"},"id":1}

\# 链码部署成功，正确。
这一步返回成功信息但并不一定就是链码部署成功，之后调用链码可能会失败。
需要确认每个vp节点是否运行了链码的docker 容器，每个vp节点都应该启动一个运行链码的docker 容器。
```

```
\# 请求：通过部署链码的节点调用链码（需通过该节点的注册用户）
curl -X POST -d '{
   "jsonrpc": "2.0",
   "method": "query",
   "params": {
     "type": 4,     
     "chaincodeID":{
       "name":"04233c6dd8364b9f0749882eb6d1b50992b942aa0a664182946f411ab46802a88574932ccd75f8c75e780036e363d52dd56ccadc2bfde95709fc39148d76f050"
     },
     "ctorMsg": {
         "args":["query", "a"]
     },
    "secureContext": "jim"
   },
   "ID": 5  
}' 'http://172.18.0.6:7050/chaincode/'

\# 返回：
{"jsonrpc":"2.0","result":{"status":"OK","message":"1000"},"id":5}

\# 链码调用成功，返回正确查询结果。
```

```
\# 请求：在非链码部署节点注册用户，需要使用与其他节点注册用户不同的用户。
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{
  "enrollId": "alice",
  "enrollSecret": "CMS10pEQlB16"
}
' 'http://172.18.0.4:7050/registrar'

\# 返回：
{"OK":"Login successful for user 'alice'."}

\# 用户注册成功，正确。
```

```
\# 请求：通过非部署链码的节点调用链码（通过非该节点注册的用户名）
curl -X POST -d '{
   "jsonrpc": "2.0",
   "method": "query",
   "params": {
     "type": 4,     
     "chaincodeID":{
       "name":"04233c6dd8364b9f0749882eb6d1b50992b942aa0a664182946f411ab46802a88574932ccd75f8c75e780036e363d52dd56ccadc2bfde95709fc39148d76f050"
     },
     "ctorMsg": {
         "args":["query", "a"]
     },
    "secureContext": "jim"
   },
   "ID": 5  
}' 'http://172.18.0.4:7050/chaincode/'

\# 返回：
{"jsonrpc":"2.0","error":{"code":-32000,"message":"Registration missing","data":"User not logged in. Use the '/registrar' endpoint to obtain a security token."},"id":5}

\# 链码调用失败，要求用户注册获取令牌，正确。
```

```
\# 请求：通过非部署链码的节点调用链码（需通过该节点的注册用户）
curl -X POST -d '{
   "jsonrpc": "2.0",
   "method": "query",
   "params": {
     "type": 4,     
     "chaincodeID":{
       "name":"04233c6dd8364b9f0749882eb6d1b50992b942aa0a664182946f411ab46802a88574932ccd75f8c75e780036e363d52dd56ccadc2bfde95709fc39148d76f050"
     },
     "ctorMsg": {
         "args":["query", "a"]
     },
    "secureContext": "alice"
   },
   "ID": 5  
}' 'http://172.18.0.4:7050/chaincode/'

\# 返回：
{"jsonrpc":"2.0","result":{"status":"OK","message":"1000"},"id":5}

\# 链码调用成功，返回正确查询结果。
```

### 集群部署



### 参考资料

> http://hyperledger-fabric.readthedocs.io/en/latest/  
>  
> https://docs.docker.com/compose/install/  
>  
> https://github.com/yeasy/docker-hyperledger-peer  
>  
> https://github.com/yeasy/docker-compose-files#hyperledger  
