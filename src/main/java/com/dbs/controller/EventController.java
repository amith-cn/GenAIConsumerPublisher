package com.dbs.controller;

import com.dbs.model.GenAiResponse;
import com.dbs.service.KafkaMessagePublisher;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.imtf.dbs.namescreening.common.kafka.schema.GenAiResponseQualification;
import com.imtf.dbs.namescreening.common.kafka.schema.GenAiResponseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;
/*
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
       // ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringVal = new StringWriter();
        //objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //objectMapper.writeValue(stringVal, response);
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

    @PostMapping(value = "/{topicName}/sendMsg/v1")
    public String sendGenAISchemaMessage(@PathVariable String topicName,@RequestBody GenAiResponse response) throws StreamWriteException, DatabindException, IOException {
        GenAiResponse.HitUrl hitUrlObject = response.getHitUrl();
        String genAiSummary = hitUrlObject.getGenAiSummary();
        GenAiResponseHitUrl hitUrl= GenAiResponseHitUrl.newBuilder()
                .setHitUrl(hitUrlObject.getHitUrl())
                .setHitId(hitUrlObject.getHitId())
                .setRmLocation("India")
                .setBookingCentre("India")
                .setErrorString("Error")
                .setRawContent(hitUrlObject.getRawContent())
                .setIsSummaryGenerated(hitUrlObject.getIsSummaryGenerated())
                .setGenAiSummary(genAiSummary)
                .setIsQualificationGenerated(hitUrlObject.getIsQualificationGenerated())
                .build();

        GenAiResponseRecord msg = GenAiResponseRecord.newBuilder()
                .setCaseId(response.getCaseId())
                .setPersonId(response.getPersonId())
                .setRiskCheckId(response.getRiskCheckId())
                .setScreeningHitId(response.getScreeningHitId())
                .setHitUrl(hitUrl)
                .build();

        publisher.sendGenAISchemaToTopic(topicName,msg);
        return "Success";
    }*/

    @PostMapping(value = "/{topicName}/testsendMsg")
    public String sendTestGenAISchemaMessage(@PathVariable String topicName,@RequestBody GenAiResponse response) throws StreamWriteException, DatabindException, IOException {

        /*GenAiTestResponseRecord msg = GenAiTestResponseRecord.newBuilder()
                .setCaseId(response.getCaseId())
                .setPersonId(response.getPersonId())
                .setRiskCheckId(response.getRiskCheckId())
                .setScreeningHitId(response.getScreeningHitId())
                .setHitUrl(response.getHitUrl().getHitUrl())
                .setErrorString(response.getHitUrl().getErrorString())
                .build();*/

       String hitUrl= response.getHitUrl();
       if(!hitUrl.equals("null")) {
           String hitId = response.getHitId();
           GenAiResponse.Qualification qualification = response.getQualification();
           GenAiResponseQualification qualification2;
           if(qualification!=null) {
               List reasonList = qualification.getReason();
               if (reasonList != null) {
                   for (int i = 0; i < reasonList.size(); i++) {
                       System.out.println("Reason " + i + " : " + reasonList.get(i));
                   }
               }

           }
       }
        /*
        private String rawContent;
        private Boolean isSummaryGenerated;
        private String genAiSummary;
        private Boolean isQualificationGenerated;
        private Qualification qualification;

        public static class Qualification {
            private String hitRelevancy;
            private String identification;
            private String hitJustification;
            private String materiality;
            private List<String> reason;
            private String otherReason;
            private String justification;*/

        /*GenAiResponseHitUrl genAiResponseHitUrl=new GenAiResponseHitUrl();
        genAiResponseHitUrl.setHitId(hitId);
        genAiResponseHitUrl.setHitUrl(hitUrlValue);
        genAiResponseHitUrl.setGenAiSummary(response.getHitUrl().getGenAiSummary());
        genAiResponseHitUrl.setErrorString(response.getHitUrl().getErrorString());
        genAiResponseHitUrl.setIsQualificationGenerated(response.getHitUrl().getIsQualificationGenerated());
        genAiResponseHitUrl.setQualification(qualification2);*/
        //GenAiResponseQualification qualification2 = null;
        GenAiResponseRecord msg = null;
        if(response.getHitUrl()==null){
            msg = GenAiResponseRecord.newBuilder()
                    .setCaseId(response.getCaseId())
                    .setPersonId(response.getPersonId())
                    .setRiskcheckId(response.getRiskCheckId())
                    .setScreeningHitId(response.getScreeningHitId())
                    .setHitId(response.getHitId())
                    .setHitUrl(response.getHitUrl())
                    .setErrorString(response.getErrorString())
                    .build();
        }
        else {
            GenAiResponseQualification qualification2= new GenAiResponseQualification();
            if(response.getQualification()!=null) {
                List<String> reason = response.getQualification().getReason();
                List<CharSequence> reasonList = new ArrayList<>();
                if(reason!=null) {
                    reasonList.addAll(reason);
                }
                qualification2.setHitJustification(response.getQualification().getHitJustification());
                qualification2.setIdentification(response.getQualification().getIdentification());
                qualification2.setHitRelevancy(response.getQualification().getHitRelevancy());
                qualification2.setJustification(response.getQualification().getJustification());
                qualification2.setMateriality(response.getQualification().getMateriality());
                qualification2.setReason(reasonList);
            }
            //qualification2.setReason(response.getQualification());

            msg = GenAiResponseRecord.newBuilder()
                    .setCaseId(response.getCaseId())
                    .setPersonId(response.getPersonId())
                    .setRiskcheckId(response.getRiskCheckId())
                    .setScreeningHitId(response.getScreeningHitId())
                    .setHitUrl(response.getHitUrl())
                    .setHitId(response.getHitId())
                    .setGenAiSummary(response.getGenAiSummary())
                    .setErrorString(response.getErrorString())
                    .setIsQualificationGenerated(response.getIsQualificationGenerated())
                    .setQualification(qualification2)
                    .build();
        }
        publisher.sendTestGenAISchemaToTopic(topicName,msg);
        return "Success";
    }


}
