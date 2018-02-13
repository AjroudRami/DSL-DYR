package com.polytech.dsl.ssl.core.enums;

import java.util.Calendar;

public enum Offset {

    MILLISECOND(Calendar.MILLISECOND),
    SECOND(Calendar.SECOND),
    MINUTE(Calendar.MINUTE),
    HOUR(Calendar.HOUR),
    DAY(Calendar.DAY_OF_YEAR),
    WEEK(Calendar.WEEK_OF_YEAR),
    MONTH(Calendar.MONTH),
    YEAR(Calendar.YEAR);


    private int offset;

    Offset(int offset){
        this.offset = offset;
    }

    public int getOffset(){
        return this.offset;
    }

}
