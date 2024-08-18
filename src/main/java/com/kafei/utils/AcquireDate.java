package com.kafei.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AcquireDate {
    public Date getNowDate() throws ParseException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        Date date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(format);
        return date1;
    }
}
