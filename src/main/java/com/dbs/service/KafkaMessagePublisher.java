package com.dbs.service;

import com.dbs.clconnbc.additionalNameapi.model.AdditionalNameResponseAvro;
import com.dbs.clconnbc.additionalNameapi.model.AdditionalNamesAvro;
import com.dbs.clconnbc.api.model.GenAiNameIdentificationRequestMsg;
import com.dbs.clconnbc.api.model.GenAiNameIdentificationResponseMsg;
import com.dbs.clconnbc.api.model.KafkaResponseAvro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imtf.dbs.namescreening.common.kafka.schemaold.GenAiResponseRecord;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    private Producer<String, byte[]> kafkaProducer;

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String,Object> template;

    @Autowired
    @Qualifier("byteKafkaTemplate")
    private KafkaTemplate<String, Object> byteKafkaTemplate;


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


    public void sendAdditionalMsg(String topic, AdditionalNameResponseAvro payload) throws Exception {
       /* SpecificDatumWriter<AdditionalNameResponseAvro> writer =
                new SpecificDatumWriter<>(AdditionalNameResponseAvro.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);*/

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        DatumWriter<GenericRecord> writer = new GenericDatumWriter<>(AdditionalNameResponseAvro.getClassSchema());
        writer.write(payload, encoder);
        encoder.flush();

        byte[] avroBytes = out.toByteArray();


        writer.write(payload, encoder);
        encoder.flush();

      //  byte[] avroBytes = out.toByteArray();
        //producer.send(new ProducerRecord<>(topic, avroBytes));
        if (avroBytes != null) {
            CompletableFuture<SendResult<String, Object>> future = byteKafkaTemplate.send(topic, avroBytes);
            future.whenComplete((result,ex)->{
                if (ex == null) {
                    System.out.println("Sent Serialized message=[" + avroBytes +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]" +"to topic "+ topic);
                } else {
                    System.out.println("Unable to send message=[" +
                            avroBytes + "] due to : " + ex.getMessage());
                }
            });
        }
    }
    public void sendMsg(String topic, String key, Object screeningPayload) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        byte[] messageBytes = mapper.writeValueAsBytes(screeningPayload);
        if (messageBytes != null) {
            CompletableFuture<SendResult<String, Object>> future = byteKafkaTemplate.send(topic, messageBytes);
            future.whenComplete((result,ex)->{
                if (ex == null) {
                    System.out.println("Sent Serialized message=[" + messageBytes +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]" +"to topic "+ topic);
                } else {
                    System.out.println("Unable to send message=[" +
                            messageBytes + "] due to : " + ex.getMessage());
                }
            });
        }
    }
}
