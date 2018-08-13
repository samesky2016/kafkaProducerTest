package com.jieshun.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public class StringUtil
{
  public static final HashMap<String, String> theNumToGb2312CharMap = new HashMap();
  public static final int MSG_TYPE_ASCII = 0;
  public static final int MSG_TYPE_WRITECARD = 3;
  public static final int MSG_TYPE_BINARY = 4;
  public static final int MSG_TYPE_UCS2 = 8;
  public static final int MSG_TYPE_CHINESE = 15;
  
  static
  {
    theNumToGb2312CharMap.put("+", "正");
    theNumToGb2312CharMap.put("-", "负");
    theNumToGb2312CharMap.put("0", "零");
    theNumToGb2312CharMap.put("1", "一");
    theNumToGb2312CharMap.put("2", "二");
    theNumToGb2312CharMap.put("3", "三");
    theNumToGb2312CharMap.put("4", "四");
    theNumToGb2312CharMap.put("5", "五");
    theNumToGb2312CharMap.put("6", "六");
    theNumToGb2312CharMap.put("7", "七");
    theNumToGb2312CharMap.put("8", "八");
    theNumToGb2312CharMap.put("9", "九");
    theNumToGb2312CharMap.put("10", "十");
    theNumToGb2312CharMap.put("100", "百");
    theNumToGb2312CharMap.put("1000", "千");
    theNumToGb2312CharMap.put("10000", "万");
  }
  
  public static boolean zero(String o)
  {
    return (o == null) || (o.length() <= 0);
  }
  
  public static boolean empty(String o)
  {
    return (o == null) || (o.length() <= 0) || (o.trim().equals(""));
  }
  
  public static String toZeroSafe(String str)
  {
    if (zero(str)) {
      return "";
    }
    return str;
  }
  
  public static String toZeroSafe(String str, String def)
  {
    if (zero(str)) {
      return def;
    }
    return str;
  }
  
  public static String toEmptySafe(String str)
  {
    if (empty(str)) {
      return "";
    }
    return str;
  }
  
  public static String toEmptySafe(String str, String def)
  {
    if (empty(str)) {
      return def;
    }
    return str;
  }
  
  public static String trim(String str)
  {
    if (empty(str)) {
      return str;
    }
    return str.trim();
  }
  
  public static boolean equals(String s1, String s2)
  {
    if (s1 == s2) {
      return true;
    }
    if (s1 == null) {
      return false;
    }
    return s1.equals(s2);
  }
  
  public static boolean equalsIgnoreCase(String s1, String s2)
  {
    if (s1 == s2) {
      return true;
    }
    if (s1 == null) {
      return false;
    }
    return s1.equalsIgnoreCase(s2);
  }
  
  public static String toString(char[] array)
  {
    return String.valueOf(array);
  }
  
  public static String toHexString(byte[] b)
  {
    if (b == null) {
      return "";
    }
    StringBuffer strBuffer = new StringBuffer(b.length * 3);
    for (int i = 0; i < b.length; i++)
    {
      strBuffer.append(Integer.toHexString(b[i] & 0xFF));
      strBuffer.append(" ");
    }
    return strBuffer.toString();
  }
  
  public static String toHexString(String str)
  {
    if (zero(str)) {
      return "";
    }
    return toHexString(str.getBytes());
  }
  
  public static String toHexString(String str, String enc)
  {
    if (zero(str)) {
      return "";
    }
    byte[] b = null;
    if (!empty(enc)) {
      try
      {
        b = str.getBytes(enc);
      }
      catch (UnsupportedEncodingException e)
      {
        return "UnsupportedEncodingException = " + e.getMessage();
      }
    }
    return toHexString(b);
  }
  
  public static String normalize(String str, String token, String escape)
  {
    if (empty(str)) {
      return "";
    }
    StringTokenizer tokenizer = new StringTokenizer(str, token);
    String fixed = new String("");
    while (tokenizer.hasMoreTokens()) {
      if (fixed.equals("")) {
        fixed = tokenizer.nextToken();
      } else {
        fixed = fixed + escape + token + tokenizer.nextToken();
      }
    }
    if (str.indexOf(escape) == 0) {
      fixed = escape + token + fixed;
    }
    if (str.lastIndexOf(escape) == str.length() - 1) {
      fixed = fixed + escape + token;
    }
    return fixed;
  }
  
  public static String replace(String src, char charOld, String strNew)
  {
    if (src == null) {
      return src;
    }
    if (strNew == null) {
      return src;
    }
    StringBuffer buf = new StringBuffer();
    int i = 0;
    for (int n = src.length(); i < n; i++)
    {
      char c = src.charAt(i);
      if (c == charOld) {
        buf.append(strNew);
      } else {
        buf.append(c);
      }
    }
    return buf.toString();
  }
  
  public static String commonPrefix(String s1, String s2)
  {
    if ((s1 == null) || (s2 == null)) {
      return null;
    }
    StringBuffer sb = new StringBuffer();
    int len = Math.min(s1.length(), s2.length());
    for (int i = 0; i < len; i++)
    {
      char c = s1.charAt(i);
      if (c != s2.charAt(i)) {
        break;
      }
      sb.append(c);
    }
    return sb.toString();
  }
  
  public static String upperFirst(String s)
  {
    String str = null;
    if (s != null) {
      if (s.length() == 1) {
        str = s.toUpperCase();
      } else {
        str = s.substring(0, 1).toUpperCase() + s.substring(1);
      }
    }
    return str;
  }
  
  public static void upperFirst(StringBuffer sb)
  {
    if ((sb != null) && (sb.length() > 0)) {
      sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    }
  }
  
  public static String lowerFirst(String s)
  {
    String str = null;
    if (s != null) {
      if (s.length() == 1) {
        str = s.toLowerCase();
      } else {
        str = s.substring(0, 1).toLowerCase() + s.substring(1);
      }
    }
    return str;
  }
  
  public static void lowerFirst(StringBuffer sb)
  {
    if ((sb != null) && (sb.length() > 0)) {
      sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
    }
  }
  
  public static String getLastSuffix(String str, String delima)
  {
    if (zero(delima)) {
      return str;
    }
    String suffix = "";
    if (!zero(str))
    {
      int index = str.lastIndexOf(delima);
      if (index >= 0) {
        suffix = str.substring(index + delima.length());
      } else {
        suffix = str;
      }
    }
    return suffix;
  }
  
  public static String getLastPrefix(String src, String delima)
  {
    if (zero(delima)) {
      return src;
    }
    String prefix = "";
    if (!zero(src))
    {
      int index = src.lastIndexOf(delima);
      if (index >= 0) {
        prefix = src.substring(0, index);
      }
    }
    return prefix;
  }
  
  public static String[] split2(String str, String delim)
  {
    if (empty(str)) {
      return new String[] { "" };
    }
    StringTokenizer st = new StringTokenizer(str, delim);
    String[] contents = new String[st.countTokens()];
    if (contents.length == 0) {
      return new String[] { "" };
    }
    int i = 0;
    while (st.hasMoreTokens()) {
      contents[(i++)] = st.nextToken();
    }
    return contents;
  }
  
  public static final String[] splitWithoutBackspace(String str, String delim)
  {
    if ((zero(str)) || (zero(delim))) {
      return new String[0];
    }
    List list = new ArrayList();
    for (StringTokenizer tokenizer = new StringTokenizer(str, delim); tokenizer.hasMoreTokens();)
    {
      String child = tokenizer.nextToken().trim();
      if (!child.equals("")) {
        list.add(child);
      }
    }
    return (String[])list.toArray(new String[list.size()]);
  }
  
  public static String[] splitNumAndStr(String oldStr)
  {
    if ((oldStr == null) || (oldStr.length() <= 0)) {
      return null;
    }
    String[] tempSeparator = new String[2];
    int i = 0;
    String tempChr = oldStr.substring(i++, i);
    while (i < oldStr.length())
    {
      int j = 0;
      for (j = 0; j <= 9; j++) {
        if (tempChr.equals(String.valueOf(j))) {
          break;
        }
      }
      if (j == 10)
      {
        tempSeparator[0] = oldStr.substring(0, --i);
        tempSeparator[1] = oldStr.substring(i, oldStr.length());
        return tempSeparator;
      }
      if (i < oldStr.length()) {
        tempChr = oldStr.substring(i++, i);
      }
    }
    return null;
  }
  
  public static String join(String[] set, String delim)
  {
    if ((set == null) || (set.length <= 0)) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    sb.append(toEmptySafe(set[0]));
    for (int i = 1; i < set.length; i++) {
      if (!empty(set[i]))
      {
        sb.append(delim);
        sb.append(toEmptySafe(set[i]));
      }
    }
    return sb.toString();
  }
  
  public static String join(String[] set, String delim, int fromIndex)
  {
    if ((set == null) || (set.length <= 0) || (fromIndex >= set.length)) {
      return "";
    }
    if (fromIndex < 0) {
      fromIndex = 0;
    }
    StringBuffer sb = new StringBuffer();
    sb.append(set[fromIndex]);
    for (int i = fromIndex + 1; i < set.length; i++)
    {
      sb.append(delim);
      sb.append(set[i]);
    }
    return sb.toString();
  }
  
  public static String joinWithSpace(String[] keys)
  {
    return join(keys, " ");
  }
  
  public static String appendDelimSuffix(String str, String suffix, String primaryDelim, String alternateDelim)
  {
    if (zero(str)) {
      str = "";
    }
    if (zero(suffix)) {
      suffix = "";
    }
    String delim = primaryDelim;
    if (str.indexOf(primaryDelim) >= 0) {
      delim = alternateDelim;
    }
    if (zero(delim)) {
      delim = "";
    }
    return str + delim + suffix;
  }
  
  public static String[] stringsCopy(String[] strings)
  {
    if (strings == null) {
      return null;
    }
    String[] copyStr = new String[strings.length];
    for (int i = 0; i < strings.length; i++) {
      if (strings[i] != null) {
        copyStr[i] = new String(strings[i]);
      } else {
        copyStr[i] = null;
      }
    }
    return copyStr;
  }
  
  public static String[] stringsCopyExcludeNull(String[] strings)
  {
    if (strings == null) {
      return null;
    }
    int iCount = 0;
    for (int i = 0; i < strings.length; i++) {
      if (strings[i] != null) {
        iCount++;
      }
    }
    String[] copyStr = new String[iCount];
    int i = 0;
    for (int j = 0; i < strings.length; i++) {
      if (strings[i] != null) {
        copyStr[(j++)] = new String(strings[i]);
      }
    }
    return copyStr;
  }
  
  public static String[] stringsCopyConvertNull(String[] strings)
  {
    if (strings == null) {
      return null;
    }
    String[] copyStr = new String[strings.length];
    for (int i = 0; i < strings.length; i++) {
      if (strings[i] != null) {
        copyStr[i] = new String(strings[i]);
      } else {
        copyStr[i] = "";
      }
    }
    return copyStr;
  }
  
  public static String copy(String str)
  {
    if (str == null) {
      return "";
    }
    return new String(str);
  }
  
  public static boolean contains(String str, char searchChar)
  {
    if ((str == null) || (str.length() == 0)) {
      return false;
    }
    return str.indexOf(searchChar) >= 0;
  }
  
  public static boolean contains(String str, String searchStr)
  {
    if ((str == null) || (searchStr == null)) {
      return false;
    }
    if (searchStr.length() == 0) {
      return false;
    }
    return str.indexOf(searchStr) >= 0;
  }
  
  public static boolean contains(StringBuffer str, String searchStr)
  {
    if ((str == null) || (searchStr == null)) {
      return false;
    }
    if (searchStr.length() == 0) {
      return false;
    }
    return contains(str.toString(), searchStr);
  }
  
  public static boolean contains(String str, String[] searchStrSet)
  {
    if ((str == null) || (searchStrSet == null)) {
      return false;
    }
    for (int i = 0; i < searchStrSet.length; i++)
    {
      String searchStr = searchStrSet[i];
      if (!contains(str, searchStr)) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean contains(String[] set, String searchStr)
  {
    if ((set == null) || (searchStr == null)) {
      return false;
    }
    for (int i = 0; i < set.length; i++)
    {
      String str = set[i];
      if (searchStr.equals(str)) {
        return true;
      }
    }
    return false;
  }
  
  public static int countMul(String src, String search)
  {
    StringTokenizer st = new StringTokenizer(src, search);
    return st.countTokens();
  }
  
  public static String reverse(String s)
  {
    if (s == null) {
      return null;
    }
    char[] c = s.toCharArray();
    char[] reverse = new char[c.length];
    for (int i = 0; i < c.length; i++) {
      reverse[i] = c[(c.length - i - 1)];
    }
    return new String(reverse);
  }
  
  public static Object removeExisted(String[] original, String searchKey)
  {
    Set set = new HashSet();
    for (int i = 0; i < original.length; i++) {
      set.add(original[i]);
    }
    set.remove(searchKey);
    
    return set;
  }
  
  public static String[] split(String dest, int maxLength)
  {
    int length = 0;
    
    length = dest.length();
    if (length <= maxLength) {
      return new String[] { dest };
    }
    int count = length / maxLength;
    if (length % maxLength > 0) {
      count++;
    }
    String[] contents = new String[count];
    int pointer = 0;
    for (int i = 0; i < count - 1; i++)
    {
      contents[i] = dest.substring(pointer, pointer + maxLength);
      pointer += maxLength;
    }
    contents[(count - 1)] = dest.substring(pointer);
    
    return contents;
  }
  
  public static String toGb2312Number(int num, boolean hasSeperator)
  {
    StringBuffer sb = new StringBuffer();
    
    String str = String.valueOf(num);
    int len = str.length();
    for (int i = 0; i < len; i++) {
      if ((num < 10) || (num >= 20) || (i != 0))
      {
        char c = str.charAt(i);
        String v = (String)theNumToGb2312CharMap.get(String.valueOf(c));
        if (v != null)
        {
          if ((hasSeperator) && (i != 0))
          {
            int key = (int)Math.pow(10.0D, len - i);
            sb.append((String)theNumToGb2312CharMap.get(String.valueOf(key)));
          }
          if ((c != '0') || (i != len - 1)) {
            sb.append(v);
          }
        }
      }
    }
    return sb.toString();
  }
  
  public static String format(int original, int length, char padding)
  {
    String str = String.valueOf(original);
    int feeLength = str.length();
    if (length < feeLength)
    {
      str = str.substring(0, length);
    }
    else if (length > feeLength)
    {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < length - feeLength; i++) {
        sb.append(padding);
      }
      str = str;
    }
    return str;
  }
  
  public static String fillBlank(String name, int bitNo)
  {
    while (bitNo >= 0)
    {
      if (bitNo == 0) {
        return name;
      }
      name = "0" + name;
      bitNo--;
      fillBlank(name, bitNo);
    }
    return name;
  }
  
  public static boolean isExistCH(String str)
  {
    str = str.trim();
    if (empty(str)) {
      return false;
    }
    for (int i = 0; i < str.length(); i++)
    {
      char c = str.charAt(i);
      if ((c < '!') || (c > '~')) {
        return true;
      }
    }
    return false;
  }
  
  public static String convertMap(Map<String, String> map)
  {
    StringBuilder sb = new StringBuilder();
    if ((map != null) && (map.size() > 0)) {
      for (Iterator localIterator = map.keySet().iterator(); localIterator.hasNext();)
      {
        Object key = localIterator.next();
        sb.append("'");
        sb.append(key);
        sb.append("'");
        sb.append(",");
      }
    }
    String ret = sb.toString();
    if (sb.length() > 0) {
      ret = sb.substring(0, sb.length() - 1);
    }
    return ret;
  }
  
  public static String convertArray(String[] array)
  {
    StringBuilder sb = new StringBuilder();
    if ((array != null) && (array.length > 0))
    {
      String[] arrayOfString = array;
      int j = array.length;
      for (int i = 0; i < j; i++)
      {
        String key = arrayOfString[i];
        sb.append("'");
        sb.append(key);
        sb.append("'");
        sb.append(",");
      }
    }
    String ret = sb.toString();
    if (sb.length() > 0) {
      ret = sb.substring(0, sb.length() - 1);
    }
    return ret;
  }
  
  public static String getUUID()
  {
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  public static boolean isEmpty(Object str)
  {
    if ((str == null) || ("".equals(String.valueOf(str).trim())) || ("null".equalsIgnoreCase(String.valueOf(str).trim()))) {
      return true;
    }
    return false;
  }
  
  public static boolean isNotEmpty(Object str)
  {
    return !isEmpty(str);
  }
  
  public static String parseClassName(String className)
  {
    if (className != null)
    {
      if (className.indexOf(".") >= 0) {
        return className.substring(className.lastIndexOf(".") + 1, className.length());
      }
      return className;
    }
    return "";
  }
  
  public static int get4RandomNum()
  {
    return new Random().nextInt(9) + new Random().nextInt(9) + new Random().nextInt(9) + new Random().nextInt(9);
  }
  
  public static String getRandomNum(int num)
  {
    StringBuffer str = new StringBuffer();
    for (int i = 0; i < num; i++) {
      str.append(new Random().nextInt(9));
    }
    return str.toString();
  }
  
//  获取0到num-1之间的随机数
  public static int getRandomNum1(int num)
  {
    return new Random().nextInt(num) ;
  }
  
  public static void main(String[] args) {
	  for(int i=0;i<10;i++){
		  System.out.println(getRandomNum1(2));
	  }
  }
}
