package minxie.space.metrics.jmh;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


class JmhMain {
    public void start() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MetricBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .result("./metrics-jmh/target/jmh-result.json")
                .build();
        new Runner(opt).run();
    }

    public static void main(String[] args) throws RunnerException {
        System.out.println("JmhMain start");
        new JmhMain().start();
        System.out.println("JmhMain end");
    }
}