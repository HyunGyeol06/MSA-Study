package com.msapay.money.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msapay.common.CountDownLatchManager;
import com.msapay.common.LoggingProducer;
import com.msapay.common.RechargingMoneyTask;
import com.msapay.common.SubTask;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
@Log4j2
public class RechargingMoneyResultConsumer {

    private final KafkaConsumer<String, String> consumer;

    private final LoggingProducer loggingProducer;

    @NotNull
    private final CountDownLatchManager countDownLatchManager;

    public RechargingMoneyResultConsumer(
            @Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
            @Value("${task.result.topic}") String topic,
            LoggingProducer loggingProducer,
            CountDownLatchManager countDownLatchManager){
        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                while(true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        log.info("Received message: " + record.value() + " / "+ record.value());

                        RechargingMoneyTask task;

                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        List<SubTask> subTaskList = task.getSubTaskList();

                        boolean taskResult = true;

                        for(SubTask subTask : subTaskList) {
                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;
                                break;
                            }
                        }

                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.getTaskID(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "success");
                        } else {
                            this.loggingProducer.sendMessage(task.getTaskID(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "failed");
                        }

//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }

                        this.countDownLatchManager.getCountDownLatch(task.getTaskID()).countDown();
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }


}
