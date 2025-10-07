package com.dbs.controller;

import com.dbs.clconnbc.api.model.AdditionalNameResponseAvro;
import com.dbs.clconnbc.api.model.AdditionalNamesAvro;
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

    @PostMapping(value = "/{topicName}/testsendMsg")
    public String sendTestGenAISchemaMessage(@PathVariable String topicName,@RequestBody GenAiResponse response) throws StreamWriteException, DatabindException, IOException {

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
        return "Success";
    }


    @PostMapping(value = "/{topicName}/publishAsBytes")
    public String sendGenAISchemaMessage(@PathVariable String topicName,@RequestBody GenAiNameIdentificationResponseMsg response) throws Exception {

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

        return "Success";
    }

}
