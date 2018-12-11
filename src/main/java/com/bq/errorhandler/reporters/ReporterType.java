package com.bq.errorhandler.reporters;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ReporterType {

    STDOUT("stdout"),

    SENTRY("sentry"),

    UNKNOWN("unknown");

    private String type;
    private static final Map<String, ReporterType> lookUp = new HashMap<>();

    static {
        for(ReporterType activityEnum : EnumSet.allOf(ReporterType.class))
            lookUp.put(activityEnum.getCode(), activityEnum);
    }

    ReporterType(String type) {
        this.type = type;
    }

    public String getCode() {
        if (null != type) return type;
        else return  "unknown";
    }

    public ReporterType getType(final String code){
        if (lookUp.containsKey(code))
            return lookUp.get(code);
        return UNKNOWN;
    }

}
