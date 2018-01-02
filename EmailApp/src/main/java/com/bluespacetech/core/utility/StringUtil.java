package com.bluespacetech.core.utility;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import org.apache.log4j.Logger;

public final class StringUtil
{
  static final Logger logger = Logger.getLogger(StringUtil.class);
  static final boolean isDebugEnabled = logger.isDebugEnabled();
  public static final String TOKEN_XHDR_BEGIN = "10.";
  public static final String TOKEN_XHDR_END = ".0";
  private static final String localPart = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*";
  private static final String remotePart = "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])+";
  private static final String intraPart = "@[a-z0-9](?:[a-z0-9-]*[a-z0-9])+";
  
  public static boolean isEmpty(String str)
  {
    return (str == null) || (str.trim().length() == 0);
  }
  
  public static String cut(String str, int len)
  {
    if ((str == null) || (str.length() <= len) || (len < 0)) {
      return str;
    }
    return str.substring(0, len);
  }
  
  public static String cutWithDots(String str, int len)
  {
    if ((str == null) || (str.length() <= len) || (len < 0)) {
      return str;
    }
    if (str.length() > len) {
      return str.substring(0, len) + "...";
    }
    return str;
  }
  
  public static String removeQuotes(String data)
  {
    if (data == null) {
      return data;
    }
    StringTokenizer st = new StringTokenizer(data, "\"'");
    StringBuffer sb = new StringBuffer();
    while (st.hasMoreTokens()) {
      sb.append(st.nextToken());
    }
    return sb.toString();
  }
  
  public static String addrToString(Address[] addrs)
  {
    return addrToString(addrs, true);
  }
  
  public static String addrToString(Address[] addrs, boolean removeDisplayName)
  {
    if ((addrs == null) || (addrs.length == 0)) {
      return null;
    }
    String str = addrs[0].toString();
    if (removeDisplayName) {
      str = removeDisplayName(str);
    }
    for (int i = 1; i < addrs.length; i++) {
      if (removeDisplayName) {
        str = str + "," + removeDisplayName(addrs[i].toString());
      } else {
        str = str + "," + addrs[i].toString();
      }
    }
    return str;
  }
  
  public static String removeDisplayName(String addr)
  {
    return removeDisplayName(addr, true);
  }
  
  public static String removeDisplayName(String addr, boolean toLowerCase)
  {
    if (isEmpty(addr)) {
      return addr;
    }
    int at_pos = addr.lastIndexOf("@");
    if (at_pos > 0)
    {
      int pos1 = addr.lastIndexOf("<", at_pos);
      int pos2 = addr.indexOf(">", at_pos + 1);
      if ((pos1 >= 0) && (pos2 > pos1)) {
        addr = addr.substring(pos1 + 1, pos2);
      }
    }
    if (toLowerCase) {
      return addr.toLowerCase();
    }
    return addr;
  }
  
  public static boolean hasDisplayName(String addr)
  {
    if (isEmpty(addr)) {
      return false;
    }
    return addr.matches("^\\s*\\S+.{0,250}\\<.+\\>\\s*$");
  }
  
  public static String getDisplayName(String addr)
  {
    if (isEmpty(addr)) {
      return null;
    }
    int at_pos = addr.lastIndexOf("@");
    if (at_pos > 0)
    {
      int pos1 = addr.lastIndexOf("<", at_pos);
      int pos2 = addr.indexOf(">", at_pos + 1);
      if ((pos1 >= 0) && (pos2 > pos1))
      {
        String dispName = addr.substring(0, pos1);
        return dispName.trim();
      }
    }
    return null;
  }
  
  public static int compareEmailAddrs(String addr1, String addr2)
  {
    if (addr1 == null)
    {
      if (addr2 != null) {
        return -1;
      }
      return 0;
    }
    if (addr2 == null) {
      return 1;
    }
    addr1 = removeDisplayName(addr1, true);
    addr2 = removeDisplayName(addr2, true);
    return addr1.compareToIgnoreCase(addr2);
  }
  
  public static String getEmailDomainName(String addr)
  {
    if (isEmpty(addr)) {
      return null;
    }
    int pos;
    if ((pos = addr.lastIndexOf("@")) > 0)
    {
      String domain = addr.substring(pos + 1).trim();
      if (domain.endsWith(">")) {
        domain = domain.substring(0, domain.length() - 1);
      }
      return domain.length() == 0 ? null : domain;
    }
    return null;
  }
  
  public static void stripAll(ArrayList<Object> list)
  {
    if (list == null) {
      return;
    }
    for (int i = 0; i < list.size(); i++)
    {
      Object obj = list.get(i);
      if ((obj != null) && ((obj instanceof String))) {
        list.set(i, ((String)obj).trim());
      }
    }
  }
  
  public static void stripAll(Object[] array)
  {
    if (array == null) {
      return;
    }
    for (int i = 0; i < array.length; i++)
    {
      Object obj = array[i];
      if ((obj != null) && ((obj instanceof String))) {
        array[i] = ((String)obj).trim();
      }
    }
  }
  
  public static void stripAll(Object obj)
  {
    if (obj == null) {
      return;
    }
    Method[] methods = obj.getClass().getDeclaredMethods();
    try
    {
      Class<?>[] setParms = { Class.forName("java.lang.String") };
      for (int i = 0; i < methods.length; i++)
      {
        Method method = methods[i];
        Class<?>[] parmTypes = method.getParameterTypes();
        int mod = method.getModifiers();
        if ((Modifier.isPublic(mod)) && (!Modifier.isAbstract(mod)) && (!Modifier.isStatic(mod))) {
          if ((method.getName().startsWith("get")) && (parmTypes.length == 0) && 
            (method.getReturnType().getName().equals("java.lang.String")))
          {
            String str = (String)method.invoke(obj, (Object[])parmTypes);
            if (str != null)
            {
              String setMethodName = "set" + method.getName().substring(3);
              try
              {
                Method setMethod = obj.getClass().getMethod(setMethodName, setParms);
                if (setMethod != null)
                {
                  String[] strParms = { str.trim() };
                  setMethod.invoke(obj, (Object[])strParms);
                }
              }
              catch (Exception localException1) {}
            }
          }
        }
      }
    }
    catch (Exception e)
    {
      System.err.println("ERROR: Exception caught during reflection - " + e);
      e.printStackTrace();
    }
  }
  
  public static String replaceAll(String body, String replFrom, String replWith)
  {
    if ((body == null) || (body.trim().length() == 0)) {
      return body;
    }
    if ((replFrom == null) || (replWith == null))
    {
      logger.warn("replaceAll() - either replFrom or replyWith is null.");
      return body;
    }
    StringBuffer sb = new StringBuffer();
    int newpos = 0;int pos = 0;
    while ((newpos = body.indexOf(replFrom, pos)) >= 0)
    {
      sb.append(body.substring(pos, newpos));
      sb.append(replWith);
      pos = newpos + Math.max(1, replFrom.length());
    }
    sb.append(body.substring(pos, body.length()));
    return sb.toString();
  }
  
  public static String removeFirstString(String body, String removeStr)
  {
    return removeString(body, removeStr, false);
  }
  
  public static String removeLastString(String body, String removeStr)
  {
    return removeString(body, removeStr, true);
  }
  
  private static String removeString(String body, String removeStr, boolean removeLast)
  {
    if ((body == null) || (body.trim().length() == 0)) {
      return body;
    }
    if ((removeStr == null) || (removeStr.trim().length() == 0)) {
      return body;
    }
    int pos = -1;
    if (removeLast) {
      pos = body.lastIndexOf(removeStr);
    } else {
      pos = body.indexOf(removeStr);
    }
    if (pos >= 0) {
      body = body.substring(0, pos) + body.substring(pos + removeStr.length());
    }
    return body;
  }
  
  public static String getPeriods(int level)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < level; i++) {
      sb.append(".");
    }
    return sb.toString();
  }
  
  public static String removeCRLFTabs(String str)
  {
    StringTokenizer sTokens = new StringTokenizer(str, "\r\n\t");
    StringBuffer sb = new StringBuffer();
    while (sTokens.hasMoreTokens()) {
      sb.append(sTokens.nextToken());
    }
    return sb.toString();
  }
  
  private static final Pattern remotePattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])+$", 2);
  private static final Pattern intraPattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@[a-z0-9](?:[a-z0-9-]*[a-z0-9])+$", 2);
  private static final Pattern localPattern = Pattern.compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*$", 2);
  
  public static String getEmailRegex()
  {
    return "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])+";
  }
  
  public static boolean isRemoteEmailAddress(String string)
  {
    if (string == null) {
      return false;
    }
    Matcher matcher = remotePattern.matcher(string);
    return matcher.matches();
  }
  
  public static boolean isRemoteOrIntranetEmailAddress(String string)
  {
    if (string == null) {
      return false;
    }
    if (isRemoteEmailAddress(string)) {
      return true;
    }
    Matcher matcher = intraPattern.matcher(string);
    return matcher.matches();
  }
  
  public static boolean isRemoteOrLocalEmailAddress(String string)
  {
    if (string == null) {
      return false;
    }
    if (isRemoteOrIntranetEmailAddress(string)) {
      return true;
    }
    Matcher matcher = localPattern.matcher(string);
    return matcher.matches();
  }
  
  public static boolean isValidEmailLocalPart(String string)
  {
    Matcher matcher = localPattern.matcher(string);
    return matcher.matches();
  }
  
  private static String bounceRegex = "\\s*\\W?((\\w+)\\-(" + "10." + "\\d+" + 
    ".0" + ")\\-(.+\\=.+)\\@(.+\\w))\\W?\\s*";
  private static Pattern bouncePattern = Pattern.compile(bounceRegex);
  private static String removeRegex = "\\s*\\W?((\\w+)\\-(\\w+)\\-(.+\\=.+)\\@(.+\\w))\\W?\\s*";
  private static Pattern removePattern = Pattern.compile(removeRegex);
  
  public static boolean isVERPAddress(String recipient)
  {
    if (isEmpty(recipient)) {
      return false;
    }
    Matcher bounceMatcher = bouncePattern.matcher(recipient);
    Matcher removeMatcher = removePattern.matcher(recipient);
    return (bounceMatcher.matches()) || (removeMatcher.matches());
  }
  
  public static String getDestAddrFromVERP(String verpAddr)
  {
    Matcher bounceMatcher = bouncePattern.matcher(verpAddr);
    if (bounceMatcher.matches()) {
      if (bounceMatcher.groupCount() >= 5)
      {
        String destAddr = bounceMatcher.group(2) + "@" + bounceMatcher.group(5);
        return destAddr;
      }
    }
    Matcher removeMatcher = removePattern.matcher(verpAddr);
    if (removeMatcher.matches()) {
      if (removeMatcher.groupCount() >= 5)
      {
        String destAddr = removeMatcher.group(2) + "@" + removeMatcher.group(5);
        return destAddr;
      }
    }
    return verpAddr;
  }
  
  public static String getOrigAddrFromVERP(String verpAddr)
  {
    Matcher bounceMatcher = bouncePattern.matcher(verpAddr);
    if (bounceMatcher.matches()) {
      if (bounceMatcher.groupCount() >= 4)
      {
        String origAddr = bounceMatcher.group(4).replace('=', '@');
        return origAddr;
      }
    }
    Matcher removeMatcher = removePattern.matcher(verpAddr);
    if (removeMatcher.matches()) {
      if (removeMatcher.groupCount() >= 4)
      {
        String origAddr = removeMatcher.group(4).replace('=', '@');
        return origAddr;
      }
    }
    return verpAddr;
  }
}
