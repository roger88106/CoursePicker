package com.example.demo.my_class;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public  class LocalTimeUtils {
    //把字串轉換為時間格式
    public static LocalTime toLocalTime(String item) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(item,formatter);
    }
}
