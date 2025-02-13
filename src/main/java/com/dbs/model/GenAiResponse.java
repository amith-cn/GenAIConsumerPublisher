package com.dbs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GenAiResponse {

    @JsonProperty("caseId")
    private String caseId;

    @JsonProperty("personId")
    private String personId;

    @JsonProperty("riskcheckId")
    private String riskCheckId;

    @JsonProperty("screeningHitId")
    private String screeningHitId;

    @JsonProperty("hitUrl")
    private HitUrl hitUrl;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class HitUrl {

        @JsonProperty("hitId")
        private String hitId;

        @JsonProperty("hitUrl")
        private String hitUrl;

        @JsonProperty("errorString")
        private String errorString;

        @JsonProperty("isSummaryGenerated")
        private Boolean isSummaryGenerated;

        @JsonProperty("genAiSummary")
        private String genAiSummary;

        @JsonProperty("rawContent")
        private String rawContent;

        @JsonProperty("isQualificationGenerated")
        private Boolean isQualificationGenerated;

        @JsonProperty("qualification")
        private Qualification qualification;

        @Getter
        @Setter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static class Qualification {

            @JsonProperty("hitRelevancy")
            private String hitRelevancy;

            @JsonProperty("identification")
            private String identification;

            @JsonProperty("hitJustification")
            private String hitJustification;

            @JsonProperty("materiality")
            private String materiality;

            @JsonProperty("reason")
            private List<String> reason;

            @JsonProperty("otherReason")
            private String otherReason;

            @JsonProperty("justification")
            private String justification;
        }
    }
}

