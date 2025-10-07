package com.dbs.clconnbc.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GenAiNameIdentificationRequestMsg {

    private String caseId;
    private String personId;
    private String screenedName;
    private String personType;
    private String entityType;

    /*private String caseId;
    private String personId;
    private String correlationId;
    private String rmLocation;
    private String bookingCentre;
    private String name;
    private String familyBackground;
    private List<String> remarks = new ArrayList<>();
    private String originator;
    private String AKANames;
    private String aliazingNames;
    @JsonIgnore
    private String personType;
    private List<String> additionalRelatedNames = new ArrayList<>();*/



    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getScreenedName() {
        return screenedName;
    }

    public void setScreenedName(String screenedName) {
        this.screenedName = screenedName;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public GenAiNameIdentificationRequestMsg(
            String caseId, String personId, String screenedName, String personType, String entityType) {
        this.caseId = caseId;
        this.personId = personId;
        this.screenedName = screenedName;
        this.personType = personType;
        this.entityType = entityType;
    }

    public GenAiNameIdentificationRequestMsg() {
        super();
    }
}
