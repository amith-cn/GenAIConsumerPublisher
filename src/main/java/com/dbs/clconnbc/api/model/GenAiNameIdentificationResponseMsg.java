package com.dbs.clconnbc.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenAiNameIdentificationResponseMsg {

    @JsonProperty("caseId")
    private String caseId;

    @JsonProperty("personId")
    private String personId;

    @JsonProperty("correlationId")
    private String correlationId;

    @JsonProperty("originator")
    private String originator;

    @JsonProperty("additionalNames")
    private List<AdditionalName> additionalNames;

    @JsonProperty("errorString")
    private String errorString;

    @Override
    public String toString() {
        return "GenAiNameIdentificationResponseMsg{" + "caseId='"
                + caseId + '\'' + ", personId='"
                + personId + '\'' + ", correlationId='"
                + correlationId + '\'' + ", additionalNames="
                + additionalNames + ", errorString='"
                + errorString + '\'' + '}';
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class AdditionalName {
        @JsonProperty("name")
        private String name;

        @JsonProperty("type")
        private String type;

        @JsonProperty("screening")
        private String screening;

        @JsonProperty("relationshipWithBO")
        private String relationshipWithBO;

        @JsonProperty("BO")
        private String BO;

        @JsonProperty("byGenAI")
        private boolean byGenAI;

        @Override
        public String toString() {
            return "AdditionalName{" + "name='"
                    + name + '\'' + ", type='"
                    + type + '\'' + ", screening='"
                    + screening + '\'' + ", relationshipWithBO='"
                    + relationshipWithBO + '\'' + ", BO='"
                    + BO + '\'' + ", byGenAI="
                    + byGenAI + '}';
        }
    }
}
