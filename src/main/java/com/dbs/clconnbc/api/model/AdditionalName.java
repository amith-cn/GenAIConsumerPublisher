package com.dbs.clconnbc.api.model;

public class AdditionalName {

    private String name;
    private String type;
    private String screening;
    private String relationshipWithBO;
    private String BO;
    private boolean byGenAI;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScreening() {
        return screening;
    }

    public void setScreening(String screening) {
        this.screening = screening;
    }

    public String getRelationshipWithBO() {
        return relationshipWithBO;
    }

    public void setRelationshipWithBO(String relationshipWithBO) {
        this.relationshipWithBO = relationshipWithBO;
    }

    public String getBO() {
        return BO;
    }

    public void setBO(String BO) {
        this.BO = BO;
    }

    public boolean isByGenAI() {
        return byGenAI;
    }

    public void setByGenAI(boolean byGenAI) {
        this.byGenAI = byGenAI;
    }
}
