package com.dbs.config.component;

import com.imtf.dbs.namescreening.common.kafka.old.GenAiResponseMsg;

//@Component
public class KafkaConsumer {

    //@KafkaListener(topics = "TestGenAiResponseTopic", groupId = "test-consumer-group")
    public void consume(GenAiResponseMsg message) {
        System.out.println(message.toString());
    }

}
