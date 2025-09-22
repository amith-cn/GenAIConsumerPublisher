package com.dbs;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GenAiConsumerPublisherApplication {

    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("1","Amith");
        addProps(map);
        System.out.println(map);
        List list=List.of("1,2,3");
       // list.stream().forEach(System.out::println);
        List list1=List.of("1");
        list1.forEach(System.out::println);


        SpringApplication.run(GenAiConsumerPublisherApplication.class, args);

    }

    private static void addProps(Map map) {
        map.put("2","Yamuna");
    }

}
