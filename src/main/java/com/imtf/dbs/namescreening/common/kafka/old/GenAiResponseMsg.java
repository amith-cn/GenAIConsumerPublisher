package com.imtf.dbs.namescreening.common.kafka.old;

import java.util.List;


public class GenAiResponseMsg {
    private String caseId;
    private String personId;
    private String riskCheckId;
    private String screeningHitId;
    private HitUrl hitUrl;

    public static class HitUrl {
        private String hitId;
        private String hitUrl;
        private String rmLocation;
        private String bookingCentre;
        private String errorString;

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
            private String justification;
        }

    }
}
