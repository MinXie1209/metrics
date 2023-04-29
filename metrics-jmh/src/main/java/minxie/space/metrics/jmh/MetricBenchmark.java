package minxie.space.metrics.jmh;

import minxie.space.jvm.vo.metrics.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.Throughput, Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 0, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 1000, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
public class MetricBenchmark {

    @Benchmark
    public String classMetric() {
        return new ClassMetricVo().toString();
    }

    @Benchmark
    public String jvmInfoMetric() {
        return JvmInfoMetricVo.INSTANCE.toString();
    }


    @Benchmark
    public String memoryMetric() {
        return new MemoryMetricVo().toString();
    }


    @Benchmark
    public String gcMetric() {
        return new GcMetricVo().toString();
    }


    @Benchmark
    public String processMetric() {
        return new ProcessMetricVo().toString();
    }

    @Benchmark
    public String threadMetric() {
        return new ThreadMetricVo().toString();
    }

    @Benchmark
    public String threadMetricWithCloseDeadLock() {
        ThreadMetricVo threadMetricVo = new ThreadMetricVo();
        threadMetricVo.setClose(true);
        return threadMetricVo.toString();
    }

}