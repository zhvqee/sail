package org.example;

import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class SendMsgMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        String brokers = "127.0.0.1:9094,127.0.0.1:9094";
        props.put("bootstrap.servers", brokers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("retries", 2);
        props.put("linger.ms", 1000);
        String topic = "test_topic";
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);
        AtomicInteger atomicInteger = new AtomicInteger();
        Faker faker = new Faker();
        while (true) {
            String substring = faker.lorem().paragraph().substring(0, 10);
            substring="abc";
            String id = atomicInteger.getAndIncrement() + "";
            ProducerRecord<String, String> record =
                    new ProducerRecord<>(topic, id, substring);
            Future<RecordMetadata> send = kafkaProducer.send(record);
            while (!send.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("id:" + id + ",msg:" + substring + ", result:" + send.get().toString());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}