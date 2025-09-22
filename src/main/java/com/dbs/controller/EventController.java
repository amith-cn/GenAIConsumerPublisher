package com.dbs.controller;

import com.dbs.clconnbc.additionalNameapi.model.AdditionalNameResponseAvro;
import com.dbs.clconnbc.additionalNameapi.model.AdditionalNamesAvro;
import com.dbs.clconnbc.api.model.GenAiNameIdentificationRequestMsg;
import com.dbs.clconnbc.api.model.GenAiNameIdentificationResponseMsg;
import com.dbs.clconnbc.api.model.KafkaResponseAvro;
import com.dbs.clconnbc.api.model.QualificationAvro;
import com.dbs.model.GenAiResponse;
import com.dbs.service.KafkaMessagePublisher;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.imtf.dbs.namescreening.common.kafka.schemaold.GenAiResponseQualification;
import com.imtf.dbs.namescreening.common.kafka.schemaold.GenAiResponseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                    .setRawContent(response.getRawContent())
                    .setIsQualificationGenerated(response.getIsQualificationGenerated())
                    .setIsSummaryGenerated(response.getIsSummaryGenerated())
                    .setQualification(qualification2)
                    .build();
        }
        publisher.sendTestGenAISchemaToTopic(topicName,msg);
        return "Success";
    }

    @PostMapping(value = "/{topicName}/publish")
    public String sendGenAISchemaMessage(@PathVariable String topicName,@RequestBody GenAiResponse response) throws StreamWriteException, DatabindException, IOException {


        String hitUrl= response.getHitUrl();
        if(!hitUrl.equals("null")) {
            String hitId = response.getHitId();
            GenAiResponse.Qualification qualification = response.getQualification();
            GenAiResponseQualification qualification2;
            if(qualification!=null && response.getIsQualificationGenerated()) {
                List reasonList = qualification.getReason();
                if (reasonList != null) {
                    for (int i = 0; i < reasonList.size(); i++) {
                        System.out.println("Reason " + i + " : " + reasonList.get(i));
                    }
                }

            }
        }

        KafkaResponseAvro msg = null;
        if(response.getErrorString()!=null){
            msg = KafkaResponseAvro.newBuilder()
                    .setCaseId(response.getCaseId())
                    .setPersonId(response.getPersonId())
                    .setRiskcheckId(response.getRiskCheckId())
                    .setScreeningHitId(response.getScreeningHitId())
                    .setHitId(response.getHitId())
                    .setHitUrl(response.getHitUrl())
                    .setRmLocation("2")
                    .setBookingCentre("Hong Kong")
                    .setErrorString(response.getErrorString())
                    .build();
        }
        else {
            QualificationAvro qualification= new QualificationAvro();
            if(response.getQualification()!=null ) {
                List<String> reason = response.getQualification().getReason();
                List<CharSequence> reasonList = new ArrayList<>();
                if(reason!=null) {
                    reasonList.addAll(reason);
                }
                qualification.setHitJustification(response.getQualification().getHitJustification());
                qualification.setIdentification(response.getQualification().getIdentification());
                qualification.setHitRelevancy(response.getQualification().getHitRelevancy());
                qualification.setJustification(response.getQualification().getJustification());
                qualification.setMateriality(response.getQualification().getMateriality());
                qualification.setOtherReason(response.getQualification().getOtherReason());
                qualification.setReason(reasonList);
            }
            //qualification2.setReason(response.getQualification());

            msg = KafkaResponseAvro.newBuilder()
                    .setCaseId(response.getCaseId())
                    .setPersonId(response.getPersonId())
                    .setRiskcheckId(response.getRiskCheckId())
                    .setScreeningHitId(response.getScreeningHitId())
                    .setHitUrl(response.getHitUrl())
                    .setHitId(response.getHitId())
                    .setRmLocation("2")
                    .setBookingCentre("Hong Kong")
                    .setErrorString(response.getErrorString())
                    .setRawContent(response.getRawContent())
                    .setGenAiSummary(response.getGenAiSummary())
                    .setIsQualificationGenerated(response.getIsQualificationGenerated())
                    .setIsSummaryGenerated(response.getIsSummaryGenerated())
                    .setQualification(qualification)
                    .build();
        }
        publisher.sendGenAISchemaToTopic(topicName,msg);
        //publisher.sendMsg(topicName,response.getScreeningHitId(),msg);
        return "Success";
    }


    @PostMapping(value = "/{topicName}/publishAsBytes")
    public String sendGenAISchemaMessage(@PathVariable String topicName,@RequestBody GenAiNameIdentificationResponseMsg response) throws Exception {

        /*GenAiNameIdentificationResponseMsg payload = new GenAiNameIdentificationResponseMsg();
        payload.setCaseId(response.getCaseId()); // Y2FzZXM6Ly9jYXNlcy9lMGFlYjFhMy0yY2E0LTQ1Y2UtYWNmNS00ZjA4ZjFhZWIyMzg caseOid
        // cases://cases/e0aeb1a3-2ca4-45ce-acf5-4f08f1aeb238"
        payload.setPersonId(response.getPersonId()); // 73444b71-5eee-470f-9ff2-76d09b8bb933
        payload.setPersonId(response.getPersonId()); // swaroop
        //payload.setEntityType(response.getEntityType());

       List<GenAiNameIdentificationResponseMsg.AdditionalName> additionalNames=response.getAdditionalNames();

       for(GenAiNameIdentificationResponseMsg.AdditionalName additionalName:additionalNames) {
           additionalName.getName();

       }*/

        // familyBackground,additionalRelatedNames
        // AKANames,aliazingNames,remarks-> why its needed
        // fetch all required EntityTypes based on NP/LP
        // add Source of Wealth extra parameters in next phase

        /*AdditionalNameResponseAvro msg = new AdditionalNameResponseAvro();
        msg.setCaseId(response.getCaseId());
        msg.setPersonId(response.getPersonId());
        msg.setCorrelationId(response.getCorrelationId());
        msg.setOriginator(response.getOriginator());*/
        List<AdditionalNamesAvro> namesAvros = new ArrayList();

        for (GenAiNameIdentificationResponseMsg.AdditionalName additionalName : response.getAdditionalNames()) {
               AdditionalNamesAvro avro= new AdditionalNamesAvro();
               avro.setName(additionalName.getName());
               avro.setType(additionalName.getType());
               avro.setScreening(additionalName.getScreening());
               avro.setRelationshipWithBO(additionalName.getRelationshipWithBO());
               avro.setBO(additionalName.getBO());
               avro.setByGenAI(additionalName.isByGenAI());
               namesAvros.add(avro);
         }
        //msg.setAdditionalNames(namesAvros);
        //msg.setErrorString(response.getErrorString());

       List avros= namesAvros.stream().collect(Collectors.toList());
        AdditionalNameResponseAvro avroMsg = AdditionalNameResponseAvro.newBuilder().
                setCaseId(response.getCaseId()).
                setPersonId(response.getPersonId()).
                setCorrelationId(response.getCorrelationId()).
                setOriginator(response.getOriginator()).
                setAdditionalNames(avros).
                setErrorString(response.getErrorString()).build();

        System.out.println("payload is"+ avroMsg);
        publisher.sendAdditionalMsg(topicName,avroMsg);
        //publisher.sendMsg(topicName,response.getPersonId(), response);
        return "Success";
    }

}
