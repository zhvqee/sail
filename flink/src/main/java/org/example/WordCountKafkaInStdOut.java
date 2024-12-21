package org.example;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class WordCountKafkaInStdOut {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setMaxParallelism(1);
        executionEnvironment.setParallelism(1);
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9094");
        String topic = "test_topic";

        FlinkKafkaConsumer<String> flinkKafkaConsumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), properties);

        DataStreamSource<String> addSource = executionEnvironment.addSource(flinkKafkaConsumer);
        // Transformation
        // 使用Flink  API对输入流的文本进行操作
        // 按空格切词、计数、分区、设置时间窗口、聚合
        DataStream<Tuple2<String, Integer>> wordCount = addSource
                .flatMap((String line, Collector<Tuple2<String, Integer>> collector) -> {
                    String[] tokens = line.split("\\s");
                    // 输出结果
                    for (String token : tokens) {
                        if (token.length() > 0) {
                            collector.collect(new Tuple2<>(token, 1));
                        }
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1);

        wordCount.print();
        JobExecutionResult execute = executionEnvironment.execute("WordCountKafkaInStdOut");
        System.out.println(execute.toString());
    }
}
