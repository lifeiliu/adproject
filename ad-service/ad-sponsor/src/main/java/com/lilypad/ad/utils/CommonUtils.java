package com.lilypad.ad.utils;


import com.lilypad.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class CommonUtils {
    private static String[] patterns ={
      "yyyy-MM-dd", "yyyy/MM/dd","MM/dd/yyy","yyyy.MM.dd"
    };
    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }
    public static Date parseStringDate(String dateToParse) throws AdException {
        try{
            return DateUtils.parseDate(dateToParse,patterns);
        }catch (Exception e){
            throw  new AdException(e.getMessage());
        }
    }
}
