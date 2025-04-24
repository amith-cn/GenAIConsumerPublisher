package com.dbs.service;

import com.dbs.clconnbc.api.model.KafkaResponseAvro;
import com.imtf.dbs.namescreening.common.kafka.schemaold.GenAiResponseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    /*//recive all the message and
    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("topicB", message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }


    public void sendGenAIMessageToTopic(com.dbs.model.GenAiResponse message){
        CompletableFuture<SendResult<String, Object>> future = template.send("GenAIResponseFindItTopic", message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }

    public void sendGenAIMessageToTopic(String topicName,com.dbs.model.GenAiResponse message){
        CompletableFuture<SendResult<String, Object>> future = template.send(topicName, message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }

    public void sendGenAISchemaToTopic(String topicName, GenAiResponseRecord message){
        CompletableFuture<SendResult<String, Object>> future = template.send(topicName, message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent Serialized message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]" +"to topic "+ topicName);
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }*/


    public void sendTestGenAISchemaToTopic(String topicName, GenAiResponseRecord message){
        CompletableFuture<SendResult<String, Object>> future = template.send(topicName, message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent Serialized message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]" +"to topic "+ topicName);
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }


    public void sendGenAISchemaToTopic(String topicName, KafkaResponseAvro message){
        CompletableFuture<SendResult<String, Object>> future = template.send(topicName, message);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent Serialized message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]" +"to topic "+ topicName);
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

    }
}
