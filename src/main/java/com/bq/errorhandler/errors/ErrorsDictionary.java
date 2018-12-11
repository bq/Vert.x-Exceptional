package com.bq.errorhandler.errors;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Error codes used internally by the server
 */
public enum ErrorsDictionary {

    /** Unknown error */
    UNKNOWN_ERROR(0),

    /** Request finished normally */
    UNEXPECTED_ERROR(1),

    /** Invalid request inbound entity */
    INVALID_INBOUND_ENTITY(2),

    /** Entity not found */
    ENTITY_NOT_FOUND(3);

    private Integer type;
    private static final Map<Integer, ErrorsDictionary> lookUp = new HashMap<>();

    static {
        for (ErrorsDictionary errorsDictionary : EnumSet.allOf(ErrorsDictionary.class))
            lookUp.put(errorsDictionary.getCode(), errorsDictionary);
    }

    ErrorsDictionary(Integer type){
        this.type = type;
    }

    public Integer getCode() {
        if (null != type) return type;
        else return  0;
    }

    public static ErrorsDictionary getType(final Integer code) {
        if (lookUp.containsKey(code))
            return lookUp.get(code);

        return UNKNOWN_ERROR;
    }

}
