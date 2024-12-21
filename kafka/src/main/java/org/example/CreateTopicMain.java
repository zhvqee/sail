package org.example;

import com.github.javafaker.Faker;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTopicMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 配置连接到Kafka集群的参数
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9094");

        // 创建AdminClient实例
        AdminClient admin = AdminClient.create(config);

        // 设置topic的名称、分区数和副本因子
        String topicName = "test_topic";
        int numPartitions = 1;
        short replicationFactor = 1;

        // 创建NewTopic对象
        NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);

        // 异步执行创建topic操作
        admin.createTopics(Collections.singleton(newTopic)).all()
                .whenComplete((ok, ex) -> {
            if (ex != null) {
                System.err.println("Topic creation failed: " + ex.getMessage());
            } else {
                System.out.println("Topic created successfully");
            }


            // 关闭AdminClient实例
            admin.close();
        });
        Thread.sleep(50000);
    }
}