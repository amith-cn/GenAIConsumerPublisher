package com.dbs.controller;

import com.dbs.model.GenAiResponse;
import com.dbs.service.KafkaMessagePublisher;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 0; i <= 100000; i++) {
                publisher.sendMessageToTopic(message + " : " + i);
            }
            return ResponseEntity.ok("message published successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping(value = "/create")
    public String createMessage(@RequestBody GenAiResponse response) throws StreamWriteException, DatabindException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringVal = new StringWriter();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(stringVal, response);
        publisher.sendMessageToTopic( stringVal.toString());
        //kafkaMessageProducer.sendMessage(stringVal.toString());
        return "Success";
    }

    @PostMapping(value = "/sendMsg")
    public String sendGenAIResponseMessage(@RequestBody GenAiResponse genAiresponse) throws StreamWriteException, DatabindException, IOException {
        publisher.sendGenAIMessageToTopic(genAiresponse);
        return "Success";
    }

    @PostMapping(value = "/{topicName}/sendMsg")
    public String sendGenAIResponseMessage(@PathVariable String topicName,@RequestBody GenAiResponse response) throws StreamWriteException, DatabindException, IOException {
        publisher.sendGenAIMessageToTopic(topicName,response);
        return "Success";
    }


}
