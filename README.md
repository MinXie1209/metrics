# JMX Exporter

一个收集应用的metrics指标的agent

## 怎么使用?
jmx_agent 提供了assembly打包插件，可通过assembly:assembly 打出一个带依赖的包
jmx_agent-1.0-SNAPSHOT-jar-with-dependencies.jar

这个包是一个agent的包，可以通过-javaagent启动
例如
> java -jar -javaagent:jmx_agent-1.0-SNAPSHOT-jar-with-dependencies.jar=12345 demo.jar


## 示例图
![dashboard](./docs/dashboard01.jpg)
![dashboard](./docs/dashboard02.jpg)
![dashboard](./docs/dashboard03.jpg)

--------------------

# 别再裸奔了，该给应用加监控了

虽然我是一个纯纯的CRUD工程师，但是我闲时也会捣鼓一些不一样的东西，比如说监控。这篇文章就是记录我自己为做系统监控的过程中，遇到的一些问题，以及我是如何解决的。
如果你也在写一个监控系统，或者你想给你的线上应用加上监控，那么这篇文章可能会对你有所帮助。

## 为什么要监控

监控是一个很大的话题，我这里只是简单的介绍一下我为什么要做监控，以及我做监控的目的是什么。
在我的认知里，如果系统没有监控，那就是在裸跑，裸跑带来什么后果呢


1. 你不知道系统的运行状态，比如说系统的CPU使用率，内存使用率，磁盘使用率，网络流量等等，这些都是系统的运行状态，如果你不知道，那么你就无法判断系统是否正常运行，如果系统出现问题，你也无法判断问题出在哪里。
2. 你不知道线程的运行状态，比如说有多少线程在运行，有多少线程在等待，有多少线程在阻塞，这些都是线程的运行状态，如果你不知道，那么你就无法判断线程是否正常运行，如果线程出现问题，你也无法判断问题出在哪里。
3. 你不知道JVM的运行状态，比如说JVM的CPU使用率，JVM的内存使用率，JVM的GC次数，JVM的GC时间，这些都是JVM的运行状态，如果你不知道，那么你就无法判断JVM是否正常运行，如果JVM出现问题，你也无法判断问题出在哪里。


## 监控的目的

监控的目的是什么呢，我认为监控的主要目的是

1. 监控系统的运行状态，如果系统出现问题，可以及时发现问题，然后解决问题。

## 监控的方式


监控的方式有很多，今天介绍业界常用的监控组件，以及我是如何结合这些组件做好系统监控的。
使用的组件包括Grafana，Prometheus，以及JMX Agent。


### Grafana

Grafana是一个开源的监控系统，它可以用来监控各种各样的数据，比如说CPU使用率，内存使用率，磁盘使用率，网络流量等等。
Grafana的安装非常简单，只需要下载一个包含二进制文件，然后执行就可以了。

```bash
curl -O https://dl.grafana.com/enterprise/release/grafana-enterprise-9.4.7.darwin-amd64.tar.gz
tar -zxvf grafana-enterprise-9.4.7.darwin-amd64.tar.gz
cd grafana-enterprise-9.4.7.darwin-amd64
./bin/grafana-server web
```

然后就可以通过浏览器访问`http://localhost:3000`，然后使用默认的用户名和密码`admin/admin`就可以登录了。

![Grafana登录界面](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eeb1e680241347f89e196317a9f7b923~tplv-k3u1fbpfcp-zoom-1.image)

![监控示例](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6b63e9808d8c4ec0aa572655f8619c2b~tplv-k3u1fbpfcp-zoom-1.image)

### Prometheus介绍


Prometheus是一个开源系统监控和警报工具包。
Prometheus可以将收集的指标存储为时间序列数据，然后通过PromQL语句查询这些数据，然后通过Grafana展示这些数据。

Prometheus的安装也是一样的，只需要下载一个包含二进制文件，然后执行就可以了。

#### Prometheus安装

```bash
curl -O https://github.com/prometheus/prometheus/releases/download/v2.37.6/prometheus-2.37.6.darwin-amd64.tar.gz
tar -zxvf prometheus-2.37.6.darwin-amd64.tar.gz
cd prometheus-2.37.6.darwin-amd64
./prometheus --config.file=prometheus.yml
```

然后就可以通过浏览器访问`http://localhost:9090`了。
![Prometheus登录界面](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/98a146552bcb4a029f8a88c1377802de~tplv-k3u1fbpfcp-zoom-1.image)

### Grafana + Prometheus

Grafana和Prometheus可以配合使用，Grafana可以用来展示Prometheus采集到的数据。
首先，我们需要在Grafana中添加一个数据源，然后选择Prometheus，然后填写Prometheus的地址，然后点击`Save & Test`，如果显示`Data source is working`，那么就说明数据源配置成功了。
![Grafana添加数据源](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d927db31fc87454e80ae137fa34e14e6~tplv-k3u1fbpfcp-zoom-1.image)

![image-20230403210918835](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f9641329cc36410589abf52e5c446671~tplv-k3u1fbpfcp-zoom-1.image)

然后我们就可以在Grafana中添加一个Dashboard了，导入我准备好的配置文件 [dashboard.json](https://github.com/MinXie1209/jmx_minxie/blob/main/docs/grafana-dashboards.json)



![image-20230403211132411](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a7637fb43956491088bdefb449e4fb08~tplv-k3u1fbpfcp-zoom-1.image)

虽然配置好了展示的数据规则，但是我们还产生数据的来源，需要借助JMX Exporter来生成JVM指标的数据。

#### JMX Exporter

Grafana + Prometheus可以用来监控各种各样的数据，但是如果我们想要监控JVM的数据，那么就需要用到JMX Exporter。
JMX Exporter是我开发的一个Javaagent库，它可以将JVM的数据采集到Prometheus中，然后通过Grafana展示出来。

1. 下载[JMX Exporter ](https://github.com/MinXie1209/jmx_minxie/releases/download/1.0-SNAPSHOT/jmx_agent-1.0-SNAPSHOT-jar-with-dependencies.jar)

2. 在你的Java应用启动的启动加上 javaagent 命令: --javaagent:/path/to/jmx_agent-1.0-SNAPSHOT-jar-with-dependencies.jar=12345

3. 启动应用，然后访问 http://localhost:12345/metrics
   有数据返回，说明JMX Exporter已经启动成功了。
   ![image-20230403213723689](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/123ba9d594c4477094d780fc23645cd9~tplv-k3u1fbpfcp-zoom-1.image)

#### Prometheus拉取JMX Exporter的数据
虽然JMX Exporter已经启动成功了，但是Prometheus还没有配置采集规则，所以我们需要在Prometheus中配置采集规则。

```yaml
# my global config
global:
  scrape_interval: 5s # Set the scrape interval to every 15 seconds. Default is every 1 minute.
  evaluation_interval: 5s # Evaluate rules every 15 seconds. The default is every 1 minute.
  # scrape_timeout is set to the global default (10s).

# Alertmanager configuration
alerting:
  alertmanagers:
    - static_configs:
        - targets:
          # - alertmanager:9093

# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: "prometheus"

    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.

    static_configs:
      - targets: ["localhost:9090"]
  - job_name: "java"

    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.

    static_configs:
      - targets: ["localhost:12345"]
```
这里配置了新的采集规则，`job_name`是采集的任务名称，`targets`是采集的目标地址，这里我们采集的是JMX Exporter的数据，所以我们填写的是JMX Exporter的地址。

关闭Prometheus，然后重新启动Prometheus，然后就可以在Grafana中看到JVM的数据了。

打开Grafana看板，可以看到JVM的数据了。
![image-20230403214051933](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d9865efe51f54234981ec4ffe3016b7e~tplv-k3u1fbpfcp-zoom-1.image)
![image-20230403214122981](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/449150eca98847c7b636836f0774cdf4~tplv-k3u1fbpfcp-zoom-1.image)


> 上面的例子是在本地启动的，如果你想要在生产环境中使用，那么你需要将JMX Exporter打包到你的Java应用中，然后在启动的时候加上javaagent参数，然后在Prometheus中配置采集规则，然后在Grafana中导入Dashboard。


> 项目地址：https://github.com/MinXie1209/jmx_minxie ，感兴趣的大佬们可以点个star，后期会继续完善。


