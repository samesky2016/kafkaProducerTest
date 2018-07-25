package com.jieshun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
  public static String dateToStr(Date currentPeriod)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    if (currentPeriod != null) {
      return sdf.format(currentPeriod);
    }
    return "";
  }
  
  public static String dateToStrTime(Date date)
  {
    if (date == null) {
      return null;
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String strDate = formatter.format(date);
    return strDate;
  }
  
  public static String dateToStrTime2(Date date)
  {
    if (date == null) {
      return null;
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String strDate = formatter.format(date);
    return strDate;
  }
  
  public static String dateToStr(Date currentPeriod, String formatStr)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
    if (currentPeriod != null) {
      return sdf.format(currentPeriod);
    }
    return "19000101";
  }
  
  public static String drawDate2String(Date drawDate)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
    return sdf.format(drawDate);
  }
  
  public static String cathecticTime2String(Date drawDate)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd  HH:mm:ss");
    return sdf.format(drawDate);
  }
  
  public static Date dateSubtractMinute(Date inDate, int minute)
  {
    long ld = inDate.getTime();
    ld -= minute * 60 * 1000;
    return new Date(ld);
  }
  
  public static Date dateAddMinute(Date inDate, int minute)
  {
    long ld = inDate.getTime();
    ld += minute * 60 * 1000;
    return new Date(ld);
  }
  
  public static String delayTimeStr(String timeStr, int minute)
  {
    Date date = Calendar.getInstance().getTime();
    String dateStr = dateToStr(date);
    String timeStrSec = dateStr + " " + timeStr;
    Date dateSec = strToDateReLoad(timeStrSec);
    long ld = dateSec.getTime();
    ld += minute * 60 * 1000;
    Date dateThr = new Date(ld);
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String timeStrThrStr = sdf.format(dateThr);
    return timeStrThrStr;
  }
  
  public static Date strToDate(String dateStr)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false);
    Date retDate = null;
    try
    {
      retDate = sdf.parse(dateStr);
    }
    catch (ParseException localParseException)
    {
      return null;
    }
    return retDate;
  }
  
  public static Date strToDateReLoad(String dateStr)
  {
    if ((dateStr == null) || ("".equals(dateStr))) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    sdf.setLenient(false);
    Date retDate = null;
    try
    {
      retDate = sdf.parse(dateStr);
    }
    catch (ParseException localParseException)
    {
      return null;
    }
    return retDate;
  }
  
  public static Date strToDateReLoad3(String dateStr)
  {
    if ((dateStr == null) || ("".equals(dateStr))) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    sdf.setLenient(false);
    Date retDate = null;
    try
    {
      retDate = sdf.parse(dateStr);
    }
    catch (ParseException localParseException)
    {
      return null;
    }
    return retDate;
  }
  
  public static Date strToDateReLoad2(String dateStr)
  {
    if ((dateStr == null) || ("".equals(dateStr))) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    sdf.setLenient(false);
    Date retDate = null;
    try
    {
      retDate = sdf.parse(dateStr);
    }
    catch (ParseException localParseException)
    {
      return null;
    }
    return retDate;
  }
  
  public static String nDaysAftertoday(int n)
  {
    return dateToStr(nDaysAfterNowDate(n));
  }
  
  public static Date nDaysAfterNowDate(int n)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.add(6, n);
    return calendar.getTime();
  }
  
  public static String nDaysAfterOneDateString(String basicDate, int n)
  {
    Date date = strToDate(basicDate);
    return dateToStr(nDaysAfterOneDate(date, n));
  }
  
  public static Date nDaysAfterOneDate(Date basicDate, int n)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(basicDate);
    calendar.set(6, calendar.get(6) + n);
    return calendar.getTime();
  }
  
  public static Date nMonthsAfterOneDate(Date basicDate, int n)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(basicDate);
    calendar.set(2, calendar.get(2) + n);
    return calendar.getTime();
  }
  
  public static Date nYearsAfterOneDate(Date basicDate, int n)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(basicDate);
    calendar.set(1, calendar.get(1) + n);
    return calendar.getTime();
  }
  
  public static int nDaysBetweenTwoDate(Date firstDate, Date secondDate)
  {
    Calendar aCalendar = Calendar.getInstance();
    aCalendar.setTime(firstDate);
    
    int day1 = aCalendar.get(6);
    aCalendar.setTime(secondDate);
    int day2 = aCalendar.get(6);
    return day2 - day1;
  }
  
  public static String getCurrMonthMinDay()
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(5, 1);
    String beginTime = dateFormat.format(cal.getTime());
    return beginTime;
  }
  
  public static String getCurrDateTimeStr()
  {
    return dateToStrTime(new Date());
  }
  
  public static String getCurrMonthMaxDay()
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    cal.set(5, 1);
    cal.roll(5, -1);
    String endTime = dateFormat.format(cal.getTime());
    return endTime;
  }
}
