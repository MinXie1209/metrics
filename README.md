## Metrics

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/MinXie1209/metrics/main/LICENSE)
[![Commit activity](https://img.shields.io/github/commit-activity/m/MinXie1209/metrics)](https://github.com/MinXie1209/metrics/graphs/commit-activity)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/minxie1209/metrics.svg)](http://isitmaintained.com/project/MinXie1209/metrics "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/MinXie1209/metrics.svg)](http://isitmaintained.com/project/MinXie1209/metrics "Percentage of issues still open")
[![codecov](https://codecov.io/github/minxie1209/metrics/branch/main/graph/badge.svg?token=WAIEL0SCX6)](https://app.codecov.io/github/minxie1209/metrics)

[Metrics](https://github.com/MinXie1209/metrics) 是一个暴露应用指标的平台，提供业务无侵入方式对生产级Java服务应用进行指标的监控和暴露。

🔥🔥🔥它提供多种指标的监控：


* 应用汇总
  * 健康状态 ✅ 
  * 应用启动时间 ✅
  * JVM版本信息 ✅
* CPU 情况 ✅
  * CPU使用率 ✅
  * CPU核数 ✅
  * CPU负载 ✅
* 文件句柄情况 ✅
  * 打开的文件句柄数 ✅
  * 最大可打开的文件句柄数 ✅
* 类加载情况 ✅
  * 已加载的类数量 ✅
  * 未加载的类数量 ✅
* Tomcat 运行情况 ✅
  * Tomcat 线程池使用情况 ✅
  * Tomcat 完成的任务数 ✅
* JDK 线程池运行情况 ✅
  * JDK 线程池使用情况 ✅
  * JDK 线程池完成的任务数 ✅
* 内存区域占用情况 ✅
  * 堆内存使用情况 ✅
* GC 垃圾回收情况 ✅
* 独立线程池运行情况 ✅
  * 运行中的线程数 ✅
  * 守护线程数 ✅
  * 线程状态分类汇总 ✅
  * 单个线程运行情况 ✅
* 。。。

-----------------

## 快速开始

Metrics 在所有主要操作系统上运行，只需要安装 Java JDK 版本 8 或更高版本。

```sh
$ java -version
java version "1.8.0_361"
```

### 使用 javaagent 的方式运行

- 可直接下载jar包

   1. [agent-1.0-SNAPSHOT.jar](https://github.com/MinXie1209/metrics/releases/download/1.0-SNAPSHOT/agent-1.0-SNAPSHOT.jar)
   2. [core-1.0-SNAPSHOT.jar](https://github.com/MinXie1209/metrics/releases/download/1.0-SNAPSHOT/core-1.0-SNAPSHOT.jar)

- 或通过 maven package 对项目进行打包



把 agent 包放到自定义目录下，如：/tmp/agent-1.0-SNAPSHOT.jar

把 core 包放到同级的 lib 目录下， 如：/tmp/lib/core-1.0-SNAPSHOT.jar

使用 -javaagent 命令启动

```sh
java -javaagent:/tmp/agent-1.0-SNAPSHOT.jar -jar demo.jar
```



MetricServer 会监听 http://127.0.0.1:12345/metrics ，确保该端口没有被本机其他人使用

也可通过命令指定监听的端口

```sh
java -javaagent:/tmp/agent-1.0-SNAPSHOT.jar=54321 -jar demo.jar
```



如果需要做监控大板，可参考 [Grafana + Prometheus 方案](https://github.com/MinXie1209/metrics/blob/main/docs/GrafanaPrometheus.md)



## 示例图

![dashboard](./docs/dashboard01.jpg)
![dashboard](./docs/dashboard02.jpg)
![dashboard](./docs/dashboard03.jpg)

--------------------

## 关注
看到这儿，请给项目一个 star，你的支持是我们前进的动力！

## 知识星球
![知识星球](https://p.ipic.vip/ktovur.png)


## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=MinXie1209/metrics&type=Date)](https://star-history.com/#MinXie1209/metrics&Date)
