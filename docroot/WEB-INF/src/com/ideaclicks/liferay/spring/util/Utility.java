/*package com.ideaclicks.liferay.spring.util;

//ACC_STMT_CHANGES - Start
import com.polaris.orbidirect.lookup.LookupCom;
import com.polaris.orbidirect.lookup.LookupIntf;
//ACC_STMT_CHANGES - End
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Serializable;//MASH_TIME_OUT_CHGS
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.Vector;
import java.util.Locale;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
import com.orbidirect.aps.common.ENTRYLevel;
import com.orbidirect.aps.common.EXITLevel;
import com.polaris.cib.util.InterfaceUtil;
import com.polaris.od.common.DataManager;
import com.polaris.od.common.OrbiProperties; // CODE_SCAN
import com.polaris.od.msginfo.common.SQLMaster;
import com.polaris.od.pwdutil.exception.PwdUtilException; //Account Statement - Performance Fix - Starts
import com.polaris.orbidirect.common.JSPIOConstants;
import com.polaris.orbioneonline.utils.MessageManager;

public class Utility  implements Serializable //MASH_TIME_OUT_CHGS
{
	private String ORA_SOURCE="OraSource";
	//STATIC_VAR_CLEANUP - Starts
	//private static String HASH_MAP_POSITION="26";
	//private static int VER_NO_POS=16;
	//private static int TXN_STATUS_POS=15;
	private static final String HASH_MAP_POSITION="26";
	private static final int VER_NO_POS=16;
	private static final int TXN_STATUS_POS=15;
	//STATIC_VAR_CLEANUP - Ends
	private int HASH_CONN_POSITION=1;
	 Account Statement - Performance Fix - Starts 
	private static final String PAGINATE_HEADER = " SELECT * FROM ( SELECT PAGINATED_TBL.*, ROWNUM RNUM FROM ( ";
  private static final String PAGINATE_FOOTER = " ) PAGINATED_TBL WHERE ROWNUM <= ? ) WHERE RNUM >= ?";
	 Account Statement - Performance Fix - Ends 

	public Utility()
	{
	}

	
	 * Gets the cached HashMap from InputVector. Same as getJSPHashMap method
	 * except that other transaction related data is also populated in the return HashMap.
	 * @param	Vector 	Contains TI framework defined fields in positions
	 * 	ranging from 0 to 28+
	 * @param	Map	Cached HashMap with additional data from Vector	 
	 
	public Map getAugmentedCachedHashMap(Vector inputVector)  //STATIC_METHOD_CLEANUP
	{
	HashMap map = (HashMap) inputVector.get(Integer.parseInt(HASH_MAP_POSITION));
	map.put(JSPIOConstants.INPUT_REFERENCE_NO,inputVector.get(TIConstants.REFERENCE_NO_POS));
	map.put(TRConstants.VER_NO,inputVector.get(VER_NO_POS));
	map.put(TIConstants.TXN_STATUS,inputVector.get(TXN_STATUS_POS));
	return map;	    
	}
	
	public HashMap getJSPHashMap(Vector inputVector){
	HashMap map = (HashMap) inputVector.get(Integer.parseInt(HASH_MAP_POSITION));
	map.put(JSPIOConstants.INPUT_REFERENCE_NO,inputVector.get(11));
	map.put(TRConstants.VER_NO,inputVector.get(16));
	//System.out.println("MAP values in UTILITY=======" +map);
	return map;
	}
  public Connection getConnection(){
  	Connection connection = null;
      Context ctx = null;
      try{
          Properties map = new Properties();
          map.put(Context.INITIAL_CONTEXT_FACTORY,getConnectionString("JNDI_FACTORY"));
          map.put(Context.PROVIDER_URL,getConnectionString("PROVIDER_URL"));
          ctx = new InitialContext(map);
          DataSource ds = (DataSource)ctx.lookup(getConnectionString("DATASOURCE_NAME"));
          connection = ds.getConnection();
     
      }catch(Exception exception){
          getPrintStackTrace(exception);
	}
	return connection;
  }

 for cyclic dependencies
	public Connection getConnection(){
	Connection connection = null;
	try{
	DataManager datamanager = new DataManager();
	connection = datamanager.getConnection();
	}catch(Exception exception){
	//System.out.println("Exception in getConnection()"+exception);
	}
	return connection;
	}

	public Connection getHashConnection(Vector inputVector){
	Connection connection = null;
	try{
	connection=(Connection)inputVector.get(inputVector.size()-HASH_CONN_POSITION);
	}catch(Exception exception){
	//System.out.println("Exception in getConnection()"+exception);
	}
	return connection;
	}
	//This method replaces null to empty string present in the arraylist of hashmap
	//@param Arraylist Arraylist of HashMap containg null values
	//@return ArrayList HashMap null values replaced with empty string
	public static ArrayList replaceNull(ArrayList arrList)
	{
	int size=0;
	HashMap eleMap=null;
	Iterator itr=null;
	String key=null;
	String value=null;

	if(arrList != null)
	{
	size=arrList.size();
	for(int index=0;index<size;index++)
	{
	try
	{
	eleMap=(HashMap)arrList.get(index);
	}catch(Exception ex)
	{
	//Catching the exception..continuing with the next element
	continue;
	}

	itr=eleMap.keySet().iterator();
	while(itr.hasNext())
	{
	key=(String)itr.next();
	try
	{
	value=(String)eleMap.get(key);
	if(value==null)
	eleMap.put(key,new String(""));
	}
	catch(Exception ex)
	{
	//Catching the exception..continuing with the next element
	continue;
	}
	}
	}
	}
	return arrList;
	}

	public String getGUID(Connection conn)throws Exception
	{
	String msgId="";
	PreparedStatement stmt=null;//FB_CODE_STAND
	ResultSet rs = null;

	String query = "select lpad(SEQ_TRADE_TI.nextVal,6,'0') || "+
	   "to_char(sysdate,'ddmmyyyyhh24miss') from dual";
	try
	{	
	//FB_CODE_STAND starts	
	stmt = conn.prepareStatement(query);
	rs = stmt.executeQuery();
	//FB_CODE_STAND ends

	if(rs.next()){	
	msgId = rs.getString(1);
	}
	}catch(SQLException ex){
	getPrintStackTrace(ex);	//FB_CODE_STAND
	}catch(Exception e){
	getPrintStackTrace(e);//FB_CODE_STAND	
	}
	finally{
	try
	{
	if(rs!=null)
	rs.close();
	}catch(SQLException sqe){
	//sqe.printStackTrace();//FB_CODE_STAND
	}catch(Exception exc){
	//exc.printStackTrace();//FB_CODE_STAND
	}
	try
	{
	if(stmt!=null)
	stmt.close();
	}catch(SQLException sq){
	//sq.printStackTrace();//FB_CODE_STAND
	}catch(Exception excep){
	//excep.printStackTrace();//FB_CODE_STAND
	}
	}
	return msgId;
	}
	//DIT-001-Starts
	public static String globalTeleCheck(String teleNo)throws Exception{
	String splitDot[];
	String returnValue="";
	if(!"".equals(teleNo)){
	splitDot = teleNo.split("-");
	for(int i=0;i<splitDot.length;i++){
	if("NA".equals(splitDot[i].toString())){
	returnValue+="";
	}else{
	returnValue+=splitDot[i]+"-";
	}
	}
	}
	if(!"".equals(returnValue)){
	returnValue=returnValue.substring(0, returnValue.length()-1);
	}
	return returnValue;
	}
	//DIT-001-Ends
	public static String getQuotedCSV(String s)throws Exception
	{
	StringBuffer quotedCSV=null;
	StringTokenizer sr=new StringTokenizer(s,",");
	quotedCSV = new StringBuffer();
	while(sr.hasMoreTokens())
	{
	quotedCSV.append(getQuoted(sr.nextToken()));
	quotedCSV.append(",");
	}
	String quotedStr = quotedCSV.toString();
	quotedStr=quotedStr.substring(0,quotedStr.length()-1);
	return quotedStr;
	}
	
	public static String getQuoted(String s)throws Exception
	{
	if(s==null)
	return null;
	else
	s="'"+s+"'";

	return s;
	}


	public static String[] convertStringToArray(String content,int upperLimit)throws Exception
  {
	String cmName="[Utility.convertStringToArray]";	
	int len=0;
	int count=0;
	int start=0;
	int limit=upperLimit;
	int i=0;
	String[] result=null;
	try{

	len = content.length();
	//System.out.println(cmName+" len::"+len);
	count = len / limit;
	count = (len % limit > 0) ? count+1 : count; 
	//System.out.println(cmName+" count::"+count);
	result = new String[count];
	for(i=0;i<count-1;i++){
	if(limit > len){
	result[i] = content.substring(start);
	break;
	}
	result[i] = content.substring(start,limit);
	start=limit;
	limit=limit+upperLimit; 	
	}
	result[i] = content.substring(start);	
	}catch(Exception e){
	getPrintStackTrace(e);//FB_CODE_STAND	
	}	
	return result;
  }

	public static HashMap formHashMap(String id1, String id2, String id3, HashMap id4)
	{
	HashMap elementHash = new HashMap();	
	elementHash.put("id1",id1);
	elementHash.put("id2",id2);
	elementHash.put("id3",id3);
	elementHash.put("id4",id4);
	return elementHash;
	}	

	public void writMapToFile(String fileName,HashMap queryMap) 
	{
	//FORTIFY_CHG starts
	FileOutputStream out = null;
	ObjectOutputStream s = null;
	try{
	out = new FileOutputStream(fileName);
	s = new ObjectOutputStream(out);
	//FORTIFY_CHG ends
	if(queryMap != null) {
	s.writeObject(queryMap);
	s.flush();
	}
	s.close();
	out.close();
	}
	catch(Exception exception) {
	//System.out.println("Exception in writMapToFile() "+exception);
	}
	//FORTIFY_CHG starts
	finally
	{
	try
	{
	if(s != null)
	s.close();
	}
	catch(Exception e)
	{
	//logger.logError(cmName,Utility.getPrintStackTrace(e));
	}
	try
	{
	if(out != null)
	out.close();
	}
	catch(Exception e)
	{
	//logger.logError(cmName,Utility.getPrintStackTrace(e));
	}	
	}
	//FORTIFY_CHG ends

	}

	public HashMap getMapFromFile(String fileName) 
	{
	HashMap inMap = null;
	//FORTIFY_CHG starts
	FileInputStream in = null;
	ObjectInputStream s1 = null;
	try{
	in = new FileInputStream(fileName);
	s1 = new ObjectInputStream(in);
	//FORTIFY_CHG ends
	inMap = (HashMap)s1.readObject();
	s1.close();
	in.close();
	}
	catch(Exception exception) {
	//System.out.println("Exception in getMapFromFile() "+exception);
	}
	//FORTIFY_CHG starts
	finally
	{
	try
	{
	if(s1 != null)
	s1.close();
	}
	catch(Exception e)
	{
	//logger.logError(cmName,Utility.getPrintStackTrace(e));
	}
	try
	{
	if(in != null)
	in.close();
	}
	catch(Exception e)
	{
	//logger.logError(cmName,Utility.getPrintStackTrace(e));
	}	
	}
	//FORTIFY_CHG ends
	if(inMap == null)
	return new HashMap();
	return inMap;
	}

	*//**
	 * Replace a single quote by two single quotes
	 * @param value
	 * @return String	 
	 *//*
	public static String replaceSingleQuote(String value)
	{
	value = replaceAll(value,"'","''");
	return value;
	}

	private static String replaceAll(String value, String from, String to)
	{
	  if (value==null || from ==null || to==null || from.length()<1 || value.indexOf(from)==-1)
	  return value;
	  StringBuffer replaced = new StringBuffer();
	  int index = value.indexOf(from);
	  while(index!=-1){
	  replaced.append(value.substring(0, index)).append(to);
	  value = value.substring(index+from.length());
	  index = value.indexOf(from);	
	  }
	  return replaced.append(value).toString();	
	}

	public static java.sql.Date HostDateFormater(String strDate) 
	{
	java.sql.Date dt=null;
	String date = "";
	String month = "";
	String year = "";
	String formatedDate = "";
	if("".equals(strDate))
	return java.sql.Date.valueOf(formatedDate);
	   StringTokenizer st = new StringTokenizer(strDate,"/");
	   int count =0;
	while (st.hasMoreTokens()) 
	{
	if(count == 0)
	date =st.nextToken(); 
	else if(count == 1)
	month =st.nextToken();
	else if(count == 2)
	year =st.nextToken();
	count++;
	}
	formatedDate = year+"-"+month+"-"+date;
	dt = java.sql.Date.valueOf(formatedDate);
	return dt;
	}

	public static String[] HostAddrFormater(String address) 
	{
	String[] addr=new String[3];	
	
	for(int i=0;i<3;i++)
	addr[i] = new String();
	
	if("".equals(address))
	return addr;
	StringTokenizer st = new StringTokenizer(address,"\n");
	int count=0;
	while (st.hasMoreTokens()) 
	{
	addr[count] = st.nextToken();
	count++;
	}
	return addr;
	}

	public static Double HostAmtFormater(String amount) 
	{
	Double amt=null;
	if(!"".equals(amount))
	amt = new Double(amount);
	else amt = new Double(0);
	return amt;
	}

	public String dateFormater(String strDate) 
	{

	String date = "";
	String month = "";
	String year = "";
	String formatedDate = "";
	if("".equals(strDate))
	return formatedDate;
	   StringTokenizer st = new StringTokenizer(strDate,"-");
	   int count =0;
	while (st.hasMoreTokens()) {
	if(count == 0)
	year =st.nextToken(); 
	else if(count == 1)
	month =st.nextToken();
	else if(count == 2)
	date =st.nextToken();
	count++;
	}
	formatedDate = date+"/"+month+"/"+year;
	return formatedDate;
	}

	public static String getPropertyString(String value)
	{	
	String entity="";
	try{
	//CHG003
	if(!"".equals(value))
	{
	ResourceBundle res = ResourceBundle.getBundle("hostDataMapping");
	entity = res.getString(value);	
	}
	else
	entity = value;

	}catch(Exception e){
	getPrintStackTrace(e);//FB_CODE_STAND	
	}
	return entity;
	}
	public static String getPropertyFileString(String properties,String value)
	{	
	String entity="";
	try{
	//CHG003
	if(!"".equals(value))
	{
	ResourceBundle res = ResourceBundle.getBundle(properties);
	entity = res.getString(value);	
	}
	else
	entity = value;

	}catch(Exception e){
	getPrintStackTrace(e);//FB_CODE_STAND
	}
	return entity;
	}

  public static String getConnectionString(String value)
	{	
	String entity="";
	try{
	ResourceBundle res = ResourceBundle.getBundle("orbionedirect");
	entity = res.getString(value);	
	}catch(Exception e){
	getPrintStackTrace(e);//FB_CODE_STAND	
	}
	return entity;
	}
  

*//** CHG001
  * The <code>getSystemDate</code>
  * @ param HashMap
  * @ return HashMap
  * @ desc This method is used to get The System data
  *//*
	public static String getSystemDate(Vector inputVector){
      HashMap headerMap = new HashMap();
      Object in = null;
      String dateValue = "";
      String delimiter="/";
      in = inputVector.get(inputVector.size()-2);
      if(in instanceof HashMap)
	{
	headerMap = (HashMap)in;
	String action = (String)headerMap.get("dateformat");
          if(action!=null || !"".equals(action))
          {
              if(action.indexOf("/")!=-1)
                  delimiter = "/";
              else if(action.indexOf(".")!=-1)
                  delimiter = ".";
              else if(action.indexOf("-")!=-1)
                  delimiter = "-";
              else if(action.indexOf("#")!=-1)
                  delimiter = "#";
              String counter[] = removeValue(delimiter,action);
              dateValue = checkDateFormat(counter,action,delimiter);
         }
      }else{
          Calendar cal = Calendar.getInstance();     
          String calDate = String.valueOf(cal.get(Calendar.DATE));
          String calMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
          if(calDate.length()==1)
              calDate = "0"+calDate;

          if(calMonth.length()==1)
              calMonth = "0"+calMonth;
      
         
           dateValue = calDate+"/"+calMonth+"/"+cal.get(Calendar.YEAR);
      }
      return dateValue;
	}
  // CHG001
  public static String[] removeValue(String delimiter,String splitValue){
      String array[] = new String[3];
      if(splitValue!=null){
          array[0]=splitValue.substring(0,splitValue.indexOf(delimiter));
          array[1]=splitValue.substring(splitValue.indexOf(delimiter)+1,splitValue.lastIndexOf(delimiter));
          array[2]=splitValue.substring(splitValue.lastIndexOf(delimiter)+1,splitValue.length());
      }
      return array;
      
  }

  // CHG001
  private static String checkDateFormat(String counter[] , String format , String delimiter)
  {

      Calendar cal = Calendar.getInstance();
      String formatter[] = removeValue(delimiter,format);
      int vTemp = 0;
      int mon = 0;
      int day = 0;
      int yr = 0;
      String returnData = "";

      String calDate = String.valueOf(cal.get(Calendar.DATE));
      String calMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
      if(calDate.length()==1)
          calDate = "0"+calDate;

      if(calMonth.length()==1)
          calMonth = "0"+calMonth;


      for(int i=0;i<counter.length;i++)
      {
          
           if(counter[i].length()==4 && (formatter[i].equals("YYYY") || formatter[i].equals("yyyy"))){
              yr = 1;
           }else if(counter[i].length()==2 && (formatter[i].equals("YY") || formatter[i].equals("yy"))){
              yr = 1;
           }

           if(counter[i].length()==3 && (formatter[i].equals("MON") || formatter[i].equals("mon") || formatter[i].equals("MMM"))){
              mon =1;
           }else if(counter[i].length()==2 && (formatter[i].equals("MM") || formatter[i].equals("mm"))){
               mon =1;
           }

           if(counter[i].length()==2 && (formatter[i].equals("DD") || formatter[i].equals("dd")))
             day=1;
          
          
          if(day==1)
          {
             vTemp ++;
             if(vTemp==3)
                  returnData = returnData + calDate ;
              else
                 returnData = returnData + calDate + delimiter;
              day=0;
          }else if(mon==1){
              vTemp ++;
              if(vTemp==3)
                  returnData = returnData + calMonth ;
              else
                 returnData = returnData + calMonth + delimiter;
                mon=0;
          }else if(yr==1){
              vTemp ++;
              if(vTemp==3)
                  returnData = returnData + cal.get(Calendar.YEAR) ;
              else
                  returnData = returnData + cal.get(Calendar.YEAR) + delimiter;
                yr=0;
          }
         
        }
       return returnData;
   }

    // CHG001 Overloaded mthod
  public static String checkDateFormat(String counter[] , String format)
  {
      String returnData= "";
      String dateString = "";
  try
    {   //CHG002
      SimpleDateFormat simpFormat 
               = new SimpleDateFormat ("dd/MM/yyyy");
        dateString = counter[0]+"/"+counter[1]+"/"+counter[2];
        Date d1 = simpFormat.parse(dateString);
        SimpleDateFormat formatter1 =
         new SimpleDateFormat (format);
        returnData  =  formatter1.format(d1);
    }catch(Exception ex){
  	  getPrintStackTrace(ex);//FB_CODE_STAND	
          return dateString;
    }

       return returnData;
   }

	public static String getCurrentDate()
	{
      Calendar cal = Calendar.getInstance();
      String calDate = String.valueOf(cal.get(Calendar.DATE));
      String calMonth = String.valueOf((cal.get(Calendar.MONTH)+1));

      if(calDate.length()==1)
          calDate = "0"+calDate;

      if(calMonth.length()==1)
          calMonth = "0"+calMonth;

      String dateValue = calDate+"/"+calMonth+"/"+cal.get(Calendar.YEAR);        
      return dateValue;
	}

	*//**
	 * Gets Current System time in 24 Hour:Minute format  
	 * @return	String 	Current time
	 *//*
  public static String getCurrentTime()
  {
      String currTime="";
      GregorianCalendar gc=null;
      String calHour="";
      String calMin="";
      
  	gc=(GregorianCalendar)Calendar.getInstance();
  	calHour=String.valueOf(gc.get(Calendar.HOUR_OF_DAY));    	
  	calMin=String.valueOf(gc.get(Calendar.MINUTE));    	
  	
  	if(calHour.length()==1)
  	    calHour="0"+calHour;

  	if(calMin.length()==1)
  	    calMin="0"+calMin;
  	
  	currTime=calHour+":"+calMin;
  	return currTime;
  }
  
  *//**
	 * Compares two Time String and returns True if the first Time String is less than or equal to the 
	 * second Time String
	 * The method assumes that timeValue1 and timeValue2 are in Format "HH:mm" (24 Hour and Minute format)
	 * @param 	timeValue1 	String value of timeValue1
	 * @param 	timeValue2	String value of timeValue2
	 * @param	boolean	Indicates whether equality check is to be carried out or not 
	 * @return 	boolean	True if the first time is less than or equal to the second one. 
	 * 	False otherwise.
   *//*
  public static boolean isTimeLessThanOrEqualTo(String timeValue1,String timeValue2,boolean equalCheck)
  {
      boolean result=false;
  	GregorianCalendar time1=null;
  	GregorianCalendar time2=null;
  
  	//If one of fields is empty or null, return false
  	if(timeValue1==null || timeValue1.equals("") || timeValue2==null || timeValue2.equals(""))
  	    return result;
  	
  	time1=(GregorianCalendar)Calendar.getInstance();
  	time2=(GregorianCalendar)Calendar.getInstance();
  	
	time1.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeValue1.substring(0,timeValue1.indexOf(":"))));
	time1.set(Calendar.MINUTE,Integer.parseInt(timeValue1.substring(timeValue1.indexOf(":")+1)));        

	time2.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeValue2.substring(0,timeValue2.indexOf(":"))));
	time2.set(Calendar.MINUTE,Integer.parseInt(timeValue2.substring(timeValue2.indexOf(":")+1)));        
	
	if(equalCheck)
	{
	if(time1.before(time2) || time1.equals(time2))
	    result=true;	    
	}
	else
	    if(time1.before(time2))
	        result=true;
	
      return result;
  }
  
  
  *//**
	 * Compares two Time String and returns True if the first Time String is greater than or equals to the
	 * second Time String
	 * The method assumes that timeValue1 and timeValue2 are in Format "HH:mm" (24 Hour and Minute format)
	 * @param 	timeValue1 	String value of timeValue1
	 * @param 	timeValue2	String value of timeValue2
	 * @param	boolean	Indicates whether equality check is to be carried out or not
	 * @return 	boolean	True if the first time is greater than or equal to the second one. 
	 * 	False otherwise.
   *//*
  public static boolean isTimeGreaterThanOrEqualTo(String timeValue1,String timeValue2,boolean equalCheck)
  {
      boolean result=false;
  	GregorianCalendar time1=null;
  	GregorianCalendar time2=null;
  
  	//If one of fields is empty or null, return false
  	if(timeValue1==null || timeValue1.equals("") || timeValue2==null || timeValue2.equals(""))
  	    return result;
  	
  	time1=(GregorianCalendar)Calendar.getInstance();
  	time2=(GregorianCalendar)Calendar.getInstance();
  	
	time1.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeValue1.substring(0,timeValue1.indexOf(":"))));
	time1.set(Calendar.MINUTE,Integer.parseInt(timeValue1.substring(timeValue1.indexOf(":")+1)));        

	time2.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeValue2.substring(0,timeValue2.indexOf(":"))));
	time2.set(Calendar.MINUTE,Integer.parseInt(timeValue2.substring(timeValue2.indexOf(":")+1)));        
	
	if(equalCheck)
	{
	if(time1.after(time2) || time1.equals(time2))
	    result=true;	    
	}
	else
	    if(time1.after(time2))
	        result=true;
	
      return result;
  }
  
	public static ArrayList subList(ArrayList list, int startIndex, int endIndex)
	{
	if(endIndex >= list.size()){
	endIndex = list.size();	
	}
	ArrayList returnList = new ArrayList();
	for(int i=startIndex; i<endIndex; i++){
	returnList.add(list.get(i));
	}
	return returnList;
	}

	public void serializeTxnData(String filePath, HashMap hmOnlineData)  //STATIC_METHOD_CLEANUP
	{
	//FORTIFY_CHG starts
	FileOutputStream out = null;
	ObjectOutputStream s = null;
	try
	{
	out = new FileOutputStream(filePath);
	s = new ObjectOutputStream(out);
	//FORTIFY_CHG ends
	s.writeObject(hmOnlineData);
	s.flush();
	s.close();
	out.close();
	}catch (Exception ee){
	
	}
	//FORTIFY_CHG starts
	finally
	{
	try
	{
	if(s != null)
	s.close();
	}
	catch(Exception e)
	{
	//logger.logError(cmName,Utility.getPrintStackTrace(e));
	}
	try
	{
	if(out != null)
	out.close();
	}
	catch(Exception e)
	{
	//logger.logError(cmName,Utility.getPrintStackTrace(e));
	}	
	}
	//FORTIFY_CHG ends
	}

	public static String getPrintStackTrace(Exception ex){
  
	StringWriter writer=null;
	PrintWriter pWriter=null;
	try
	{
	writer = new StringWriter();
	pWriter = new PrintWriter(writer);
	ex.printStackTrace(pWriter);
	}catch(Exception ep){
	}
	return (writer.getBuffer()).toString();
	}

  //CHG004 - Start
	public static String getDateTime()
	{
	String mon[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	String day[] = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	TimeZone t = TimeZone.getDefault();
	Calendar calendar = new GregorianCalendar(t);
	Date trialTime = new Date();
	StringBuffer sbufFilename= new StringBuffer();

	calendar.setTime(trialTime);
	sbufFilename.append(day[(calendar.get(Calendar.DATE))-1]);
	sbufFilename.append(mon[(calendar.get(Calendar.MONTH))].toUpperCase());
	sbufFilename.append(Integer.toString(calendar.get(Calendar.YEAR)));
	sbufFilename.append("_"+new Integer(calendar.get(Calendar.HOUR_OF_DAY)).toString());
	sbufFilename.append(new Integer(calendar.get(Calendar.MINUTE)).toString());
	sbufFilename.append(new Integer(calendar.get(Calendar.SECOND)).toString());
	sbufFilename.append(new Integer(calendar.get(Calendar.MILLISECOND)).toString());
	return sbufFilename.toString();
	}
  //CHG004 - End


  public static String getFormatedDate(String dateValue,String format){

      String delimiter="";
      if(!"".equals(dateValue))
      {
          if(dateValue.indexOf("/")!=-1)
              delimiter = "/";
          else if(dateValue.indexOf(".")!=-1)
              delimiter = ".";
          else if(dateValue.indexOf("-")!=-1)
              delimiter = "-";
          else if(dateValue.indexOf("#")!=-1)
              delimiter = "#";

          String array[]    = Utility.removeValue(delimiter,dateValue);
          String returnDate = Utility.checkDateFormat(array,format);
          return returnDate;
      }
      return dateValue;
   }

  
  * Applies decimal formatting to amount value
  * @param amtVal Input Amount Value to be converted
  * @return String Formatted amount (eg, 3253.50)
  
	public static String getAmount(String amtVal) {//Naga
	String amountStr = null;
      if("".equals(amtVal))
          return amtVal;

	amtVal=removeCommas(amtVal);

	NumberFormat formatter = new DecimalFormat("####################0.00");
	amountStr = formatter.format(Double.parseDouble(amtVal));  	
	return amountStr;	
	}
	
	public static String removeCommasMinorCcy(String amount)
	{
	String str="";
	if (amount==null)
	amount="";
	
	amount = new InterfaceUtil().formatResponseData(amount, "", "", "AMT");  //STATIC_METHOD_CLEANUP
	
	StringTokenizer st = new StringTokenizer(amount,",");
	while(st.hasMoreElements())
	{
	str += st.nextToken();
	}
	if("".equals(str))
	str = amount;
	
	return str;
	}
	
	public static String removeCommas(String amount)
	{
	String str="";
	if (amount==null)
	amount="";
	StringTokenizer st = new StringTokenizer(amount,",");
	while(st.hasMoreElements())
	{
	str += st.nextToken();
	}
	if("".equals(str))
	str = amount;
	
	return str;
	}

	//Added by CHG002_29796
	public static String getNFormat(String amountParse,String language){
  String parseConverter="";
	String appender="";
	try
	{
	if(!"".equals(amountParse) && amountParse!=null){
	String lang = language.substring(0,language.indexOf("_"));
	String cntry = language.substring(language.indexOf("_")+1,language.length());
	Locale locale = new Locale(lang,cntry);
	DecimalFormat dformat = new DecimalFormat("###,##0.00");
	NumberFormat nformat = dformat.getNumberInstance(locale);
	double lng = Double.parseDouble(amountParse);
	parseConverter = nformat.format(lng);

	if(parseConverter.indexOf(".")!=-1)
	{
	String str = parseConverter;
	str = str.substring(str.indexOf(".")+1,str.length());
	if(str.length()!=2)
	  appender = "0";

	}
	else if(parseConverter.indexOf(".")==-1)
	{	
	String str = parseConverter;
	str = str.substring(str.length());
	appender = ".00";
	}
	parseConverter = parseConverter+appender;
	}
	 }catch(NumberFormatException ep){
	 getPrintStackTrace(ep);//FB_CODE_STAND
	 }catch(Exception ex){
	 getPrintStackTrace(ex);//FB_CODE_STAND	
	 }
	 return parseConverter;
	}
	//Ended by CHG002_29796
	public static String getNFormatWithThreeDecimals(String amountParse,String language){
	    String parseConverter="";

	try
	{
	if(!"".equals(amountParse) && amountParse!=null){
	String lang = language.substring(0,language.indexOf("_"));
	String cntry = language.substring(language.indexOf("_")+1,language.length());
	Locale locale = new Locale(lang,cntry);
	DecimalFormat dformat = new DecimalFormat("###,##0.00");
	NumberFormat nformat = dformat.getNumberInstance(locale);
	double lng = Double.parseDouble(amountParse);
	parseConverter = nformat.format(lng);

	if(parseConverter.indexOf(".")!=-1)
	{
	String str = parseConverter;
	str = str.substring(str.indexOf(".")+1,str.length());
	if(str.length()!=2)
	  appender = "0";

	}
	else if(parseConverter.indexOf(".")==-1)
	{	
	String str = parseConverter;
	str = str.substring(str.length());
	appender = ".00";
	}
	parseConverter = parseConverter+appender;
	}
	 }catch(NumberFormatException ep){
	 getPrintStackTrace(ep);//FB_CODE_STAND	
	 }catch(Exception ex){
	 getPrintStackTrace(ex);//FB_CODE_STAND	
	 }
	 return parseConverter;
	}
	*//**
	 * Checks for whether any one of the fields are present.
	 * @param  String[] array of field names of which atleast one is required
	 * @return boolean returns true if any one of the fields are present 
	 * else false 	
	 *//*
	public static boolean isOneOfFieldsPresent(String fields[])
	{
	if(fields !=null)
	{
	for (int arrIndex=0; arrIndex<fields.length; arrIndex++)
	{
	if (fields[arrIndex]!=null && !fields[arrIndex].trim().equals(""))
	return true;
	}
	return false;	
	}
	else
	return false;
	}
	//Added for Canned reports by 66826 - Start
	*//**
	 * Checks for whether all Mandatory fields are present.
	 * @param  String[] array of field names of which all are required
	 * @return boolean returns true if all of the fields are present 
	 * else false 	
	 *//*
	public static boolean isMandatoryFieldsPresent(String fields[])
	{
	if(fields !=null)
	{
	for (int arrIndex=0; arrIndex<fields.length; arrIndex++)
	{
	if (fields[arrIndex]==null && fields[arrIndex].trim().equals(""))
	return false;
	}
	return true;	
	}
	else
	return true;
	}
	//Added for Canned reports by 66826 - End
*//**
	*	Replaces value of the String with an empty String if the value is null.
	*   @param fieldValue- String which is to be changed as an empty string if the value is null.
	*   @return returns an empty String if the value is null ,else original String
	*//*
	public static String replaceNull(String fieldValue)
	{
	if (fieldValue == null)
	return "";
	else
	return fieldValue;
	}

	*//**
	 * Finds the description for the key from the vertical based properties if
	 * the code and bundle are not an empty
	 * @param code - Key for which the description has to be found from the property file
	 * @param bundle - property file name
	 * @param langId - Language Id for referring the property file
	 * @param prefix - prefix
	 * @return Returns the description for the code
	 *//*
	public static String findDescription(String code, String bundle, String langId, String prefix)
	{
	String description = "";
	
	if (code!=null && !code.equals("") && bundle!=null && !bundle.equals(""))
	{
	if(prefix!=null && !prefix.equals(""))
	code = prefix + code;
	if(langId!=null && !langId.equals(""))
	description = MessageManager.getMessage(bundle,code,langId);
	else
	description = MessageManager.getMessage(bundle,code);
	}
	else
	description = "";
	
	return description;
	}
	 
	  * Gets Comma separated String out of List object sent
	  * @param	List 	List object whose values are to be converted to String
	  * 	separated by comma
	  * @return	String	Comma seperated String of list values 
	  
	 public static String getCommaSeparatedString(List list)
	 {
	     int loop=0;
	     int len=0;	     
	     String retStr="";
	     
	     if(list!=null && list.size()>0)
	     {
	         len=list.size();
	     for(loop=0;loop<len;loop++)
	         retStr+=(String)list.get(loop)+",";
	     retStr=retStr.substring(0,retStr.length()-1);
	     }
	     return retStr;
	 }
	 
	*//**
	 * Gets a set of String as String array given a comma separated list - like "100,101,102" gives a array of 3 elements
	 * @param list - The delimited string that has to be divided
	 * @return String[] The array of strings.
	 *//*
	  public static String[] getCommaSeparatedList(String list)
	  {
	  return getDelimitedList(list,",");
	  }

	*//**
	 * Gets a set of String as String array given a delimiter separated list - like "100^101^102" gives a array of 3 elements if delimiter is given as ^
	 * @param list - The delimited string that has to be divided
	 * @return String[] The array of strings.
	 *//*
	  public static String[] getDelimitedList(String list, String delimiter)
	  {
	  if(list==null || delimiter ==null) 
	  return null;
	  StringTokenizer tok = new StringTokenizer(list, delimiter);
	  int count = tok.countTokens();
	  String sepList[] = null;
	  if(count != 0){
	  sepList = new String[count];
	  for(int i=0; i < count; i++){
	sepList[i] = tok.nextToken();	
	  }
	  }
	  return sepList;
	  }	 
	
	 * Converts date value from One TimeZone to another.
	 * As of now, an empty implementation is provided. 
	 * @param	String	Date Value that is to be converted
	 * @param	String	Timezone of the incoming date value
	 * @param	String	Timezone in which the date value is to be converted
	 * @return	String	Date value in Target Timezone 
	 
	public String convertToTimeZone(String dateValue, String srcTimeZone,String targetTimeZone)  //STATIC_METHOD_CLEANUP
	{
	    return dateValue;
	}
  *//**
   * Thie API used to find the delimiter value
   * @param dateStr - Date string to find the delimiter
   * @return Returns delimiter value if incoming date value contains any one of / . - #
   * else returns empty string
   *//*
  private static String findDelimiter(String dateStr)
  {
      logger.debug("Inside the method");
      String delimiter="";
      if (dateStr.indexOf("/") != -1)
      {
          delimiter = "/";
      }
      else if (dateStr.indexOf(".") != -1)
      {
          delimiter = ".";
      }
      else if (dateStr.indexOf("-") != -1)
      {
          delimiter = "-";
      }   
      else if (dateStr.indexOf("#") != -1)
      {
          delimiter = "#";
      }
      logger.debug("Leaving from method and delimiter value is :"+delimiter);
      return delimiter;
  }	
	*//**
	 * This method is used to convert standard date value to GregorianCalendar object
	 * The presumption made here is incoming date value always be in standard format(dd/MM/yyyy).
	 * Delimiter could be / . - #. However order should date(dd) followed by month(mm) followed by year(yyyy)
	 * @param datevalue - date value need to be convert as GregorianCalendar
	 * @return Returns GregorianCalendar instance
	 *//*
  public static GregorianCalendar parseDateStringToCalendar(String datevalue){
      //String cmName = "JSPUtil.parseDateStringToCalendar";//FB_CODE_STAND
      logger.debug("Inside parseDateStringToCalendar()");
      logger.debug("Date and date format are:"+datevalue);
      String DELIMATOR= findDelimiter(datevalue);        
      StringTokenizer st =new StringTokenizer(datevalue,DELIMATOR);
      String[] tokens=new String[3];
      for(int index=0;st.hasMoreTokens();index++){
          tokens[index] = new String(st.nextToken());
      }
      // made another assumption of frist one is date, second one is month and last one is year
      int date = Integer.parseInt(tokens[0]);
      int month = Integer.parseInt(tokens[1]);
      int year = Integer.parseInt(tokens[2]);
      GregorianCalendar cale =new GregorianCalendar(year,month-1,date);                
      logger.debug("Leaving parseDateStringToCalendar()");
      return cale;
  }	
  
	*//**
	 * Compares two Dates and returns True if the first Date is greater than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * @param 	dateValue1 	String value of dateValue1
	 * @param 	dateValue2	String value of dateValue2
	 * @return 	boolean	True if the first date is greater than or equal to the second one. 
	 * 	False otherwise.
	 *//*
	public static boolean isDateGreaterThanOrEqualTo(String dateValue1,String dateValue2) 
	{
	    return isDateGreaterThanOrEqualTo(dateValue1,dateValue2,true);
	}
	
	*//**
	 * Compares two Dates and returns True if the first Date is greater than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * Equality check will be carried out based on value sent for thrid parameter
	 * @param 	dateValue1 	String value of dateValue1
	 * @param 	dateValue2	String value of dateValue2
	 * @param	equalCheck	boolean to indicate if equality check is to be carried out or not
	 * @return 	boolean	True if the first date is greater than or equal to the second one. 
	 * 	False otherwise.
	 *//*
	public static boolean isDateGreaterThanOrEqualTo(String dateValue1,String dateValue2,boolean equalCheck) 
	{
	boolean result = false;
	//boolean isBefore=false;//FB_CODE_STAND
	GregorianCalendar date1 = null;
	GregorianCalendar date2 = null;
	
	//If one of comparison fields is null/empty, return false
	if((dateValue1==null || dateValue1.equals("")) || (dateValue2==null || dateValue2.equals("")))
	    return result;
	
	date1 = parseDateStringToCalendar(dateValue1);
	date2 = parseDateStringToCalendar(dateValue2);
	
	if(equalCheck)
	{
	if(date1.after(date2) || date1.equals(date2))
	    result=true;	    
	}
	else
	    if(date1.after(date2))
	        result=true;
	
	return result;
	}
	
	*//**
	 * Compares two Dates and returns True if the first Date is less than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * @param 	dateValue1 	String value of dateValue1
	 * @param 	dateValue2	String value of dateValue2
	 * @return 	boolean	True if the first date is less than or equal to the second one. 
	 * 	False otherwise.
	 *//*
	public static boolean isDateLessThanOrEqualTo(String dateValue1,String dateValue2) 
	{
	    return isDateLessThanOrEqualTo(dateValue1,dateValue2,true);
	}	

	*//**
	 * Compares two Dates and returns True if the first Date is less than or equal to the second Date
	 * The method assumes that dateValue1 and dateValue2 are in Standard Date Format"dd/MM/yyyy"
	 * Equality check will be carried out based on value sent for thrid parameter
	 * @param 	dateValue1 	String value of dateValue1
	 * @param 	dateValue2	String value of dateValue2
	 * @param	equalCheck	boolean to indicate if equality check is to be carried out or not
	 * @return 	boolean	True if the first date is less than or equal to the second one. 
	 * 	False otherwise.
	 *//*
	public static boolean isDateLessThanOrEqualTo(String dateValue1,String dateValue2,boolean equalCheck) 
	{
	boolean result = false;
	//boolean isAfter=false;//FB_CODE_STAND
	GregorianCalendar date1 = null;
	GregorianCalendar date2 = null;
	
	//If one of comparison fields is null/empty, return false
	if((dateValue1==null || dateValue1.equals("")) || (dateValue2==null || dateValue2.equals("")))
	    return result;
	
	date1 = parseDateStringToCalendar(dateValue1);
	date2 = parseDateStringToCalendar(dateValue2);
	
	if(equalCheck)
	{
	if(date1.before(date2) || date1.equals(date2))
	    result=true;	    
	}
	else
	    if(date1.before(date2))
	        result=true;
	
	return result;
	}	

	*//**
	 * Checks if the given date is present in the month passed. 
	 * @param 	String	Month value used in check
	 * @param 	String	Date value to be checked for presence in the month
	 * @return 	boolean	True if the given date is present in the month 
	 * 	False otherwise.
	 *//*
	public static boolean isValidDateInMonth(int month,int date) 
	{
	boolean result = true;
	int minDate=1;
	int maxDate=31;
	
	//If month is February, last date is 29 (considering leap year)
	if(month==2)
	    maxDate=29;
	// If month is August ,last date is 31
	else if(month==8)
	maxDate=31;
	//If month is even one other than February, last date is 30
	else if(month%2==0)
	    maxDate=30;
	
	if(date<minDate || date >maxDate)
	    result=false;
	
	return result;
	}	
	
	 
	 * move()
	 * Move the files from one folder to another folder and deletes the source file
	 * @param: String - Source file name
	 * @param: String - Destination file name
	 
	public void move(String srcFileName, String destFileName)   //STATIC_METHOD_CLEANUP
	{
	logger.log(ENTRYLevel.ENTRY,"Entered into move()");
	try 
	{
	File srcFile = new File(srcFileName);
	File destFile = new File(destFileName);
	
	FileReader fileReader = new FileReader(srcFile);
	FileWriter fileWriter = new FileWriter(destFile);
	
	BufferedReader br = new BufferedReader(fileReader);
	        BufferedWriter bw = new BufferedWriter(fileWriter); 
	        int read = 0;
	        while((read = br.read()) != -1)
	             bw.write(read);
	        
	        br.close();
	        bw.close();
	        fileReader.close();
	        fileWriter.close();
	        srcFile.delete();
	   } 
	   catch (FileNotFoundException filenotfoundExcp) 
	   {
	       logger.error("File Not Found Exception occured while moving the files and the error message is: " + filenotfoundExcp.getMessage());
	   } 
	   catch (IOException ioexcp) 
	   {
	   	logger.error("Exception occured while moving the files and the error message is: " + ioexcp.getMessage());
	   }
	   logger.log(EXITLevel.EXIT,"Exited from move()");
	}
	
//Splitter to split the list of columns separated by ','
	public static ArrayList splitString(String str)
	{
	String[] strArray = str.split(",");
	 ArrayList strList = new ArrayList(strArray.length);
	for (int i = 0; i<strArray.length; i++) {
	
	strList.add(strArray[i]);	
	}
	
	return strList;
	
	}
	
	//Method to create a string of gcif from array of gcifs
	public String gcifList(String[] gcif)   //STATIC_METHOD_CLEANUP
	{
	
	StringBuffer str = new StringBuffer();
	for( int i = 0;i<gcif.length;i++)
	{
	str.append("'"+gcif[i]+"'");
	if(i !=(gcif.length-1))
	{
	str.append(",");
	}
	}

	return str.toString();
	}
	

	*//**
	 * Helper method to populate name value pairs.
	 * @param	String	 String to be formatted
	 * @return	ArrayList
	 *//*
*//**CHG005_58992 - Starts **//*
public static HashMap getParsingHashDetails(String sValue) throws Exception
{
	HashMap dataMap = null;
	StringTokenizer strMainToken = null;
	StringTokenizer strSubToken = null;
	String tokens = "";
	String key = "";
	String value = "";
	//String cmName="getParsingHashDetails";
	
	try{
	dataMap = new HashMap();
	logger.debug("getParsingHashDetails"+"sValue==>"+sValue);

	strMainToken = new StringTokenizer(sValue, JSPIOConstants.INTEG_DELIMITERS);
	
	while(strMainToken.hasMoreTokens())
	{
	tokens = (String)strMainToken.nextElement();
	
	if(tokens.endsWith("="))
	tokens = tokens+"null";
	
	strSubToken = new StringTokenizer(tokens, JSPIOConstants.EQULAS_SYM);

	while(strSubToken.hasMoreTokens())
	{
	key = strSubToken.nextToken();
	value = strSubToken.nextToken();
	dataMap.put(key,value);
	}
	}
	}catch(Exception e){
	logger.error("getParsingHashDetails"+Utility.getPrintStackTrace(e));
	}
	return dataMap;
}
	
*//**CHG005_58992 - Ends **//*	
	

	*//**
	 * Helper method to populate name value pairs.
	 * @param	String	 String to be formatted
	 * @return	ArrayList
	 *//*
	public static ArrayList getParsingDetails(String resultString)
	{
	logger.log(ENTRYLevel.ENTRY,"Entered into getParsingDetails");

	ArrayList resultList=new ArrayList();
	HashMap resultMap=new HashMap();
	logger.debug("String value to be parsed: "+resultString);
	StringTokenizer strMainToken = new StringTokenizer(resultString, JSPIOConstants.INTEG_DELIMITERS);
	//FB_CODE_STAND starts
	String tokens = null;
	String key=null;
	String value=null;
	StringTokenizer strSubToken=null;
	//FB_CODE_STAND ends
	while (strMainToken.hasMoreTokens()) 
	{
	tokens = (String)strMainToken.nextElement();//FB_CODE_STAND
	if(tokens.endsWith("="))
	{
	tokens=tokens+"null";
	}
	strSubToken = new StringTokenizer(tokens,JSPIOConstants.EQULAS_SYM);//FB_CODE_STAND
	while (strSubToken.hasMoreTokens()) 
	{
	//FB_CODE_STAND starts
	key=strSubToken.nextToken();
	value=strSubToken.nextToken();
	//FB_CODE_STAND ends
	if(resultMap.containsKey(key))
	{
	resultList.add(resultMap);
	resultMap=new HashMap();
	resultMap.put(key,value);
	}
	else
	{
	resultMap.put(key, value);
	}
	}
	}
	resultList.add(resultMap);
	logger.debug("After Parser result list value: "+resultList);
	logger.log(EXITLevel.EXIT,"Exited from getParsingDetails");
	return resultList;
	}
	
	//FILE_NAME_DISP - Starts
	public String formatFileName(String val)

	{
	logger.debug("formatFileName()"+"val- "+val);
	if(val!=null)
	if(val.indexOf("_")==-1)
	return val;
	Pattern unitpattern = Pattern.compile("\\d\\d\\_\\d\\_\\d\\d\\d\\d");
	Pattern doublePattern = Pattern.compile("\\d\\d\\_\\d\\d\\_\\d\\d\\d\\d");
	Pattern SDSMpattern = Pattern.compile("\\d\\_\\d\\_\\d\\d\\d\\d");
	Pattern SDDMpattern = Pattern.compile("\\d\\_\\d\\d\\_\\d\\d\\d\\d");
	if (val==null || "".equals(val))
	{
	return "";
	}
	int i=0,j=0,k=0;
	do
	{
	j=val.indexOf('_',j+1);  //Changed By Udesh for New FileFormat appending Format Type
	if(i==0)	
	{
	if(unitpattern.matcher(val.substring(j+1,j+10)).matches()||doublePattern.matcher(val.substring(j+1,j+11)).matches()||SDSMpattern.matcher(val.substring(j+1,j+9)).matches()||SDDMpattern.matcher(val.substring(j+1,j+10)).matches())
	k=7;
	else
	k=2;
	}
	if(j==-1)
	break;
	if(j>0)
	i++;
	}while(i<k);
	logger.debug("formatFileName()"+"i "+i+", j "+j+", val-  "+val.substring(j+1));
	if(i==k)
	return val.substring(j+1);
	return val;
	}
	//FILE_NAME_DISP - Ends
	
	// DEF 241	- Balaji Added
	public String getSixthMonthDate()throws Exception
	{
	String dateValue="";
	Statement stmt=null;
	ResultSet rs = null;
	Connection conn=null;
	String query = "select TO_CHAR (ADD_MONTHS(sysdate,-6), 'dd/MM/yyyy') CMP_DATE from dual";
	try
	{	
	conn = getConnection();
	stmt = conn.createStatement();
	rs = stmt.executeQuery(query);

	if(rs.next()){	
	dateValue = rs.getString("CMP_DATE");
	}
	}catch(SQLException ex){
	//ex.printStackTrace();//FORTIFY_CHG	
	}catch(Exception e){
	//e.printStackTrace();//FORTIFY_CHG
	}
	finally{
	try
	{
	//FORTIFY_CHG STARTS
	try
	{
	  if(rs!=null)
	 rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	  if(stmt!=null)
	 stmt.close();
	    }
	    catch(Exception e)
	    {
	
	    }
	    try
	    {
	  if(conn!=null)
	 conn.close();
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    
	}
	//catch(SQLException sqe){
	//logger.error("getParsingHashDetails"+Utility.getPrintStackTrace(sqe));//FB_CODE_STAND
	//}
	//FORTIFY_CHG ENDS
	catch(Exception exc){
	logger.error("getParsingHashDetails"+Utility.getPrintStackTrace(exc));//FB_CODE_STAND
	}
	
	}
	logger.debug("dateValue*********** "+ dateValue);
	return dateValue;
	}
	
	
public static String getAmountFormat(String amount,String format,String currency){
	
	    String amtSplit = "";
	int inDot=0;
	int formatlength=0;
	     boolean isCurrencycheck=false;
	     String tempamount="";
	     String afterDotamt="";
	    
	    if(amount.length()>0 && amount!=null && !"".equals(amount))
	    {
	 	for(int i=0;i<format.length();i++){
	if(format.substring(i,i+1).equals("#"))
	formatlength++;
	else if(format.substring(i,i+1).equals("."))
	break;
	
	}
	  	
	amount = amount.toString();
	
	if(amount.charAt(0)=='.' || amount.charAt(0)=='0')
	{
	    tempamount=amount;
	        return tempamount;
	}

	
	
	if(amount.indexOf(".")!=-1)
	{
	String splitDot[] = amount.split("[.]");
	String befDotamt="";
	
	for(int i=0;i<splitDot.length;i++)
	{
	if(i==0)
	{
	befDotamt=splitDot[0];
	}
	else if(i>0)
	{
	afterDotamt=splitDot[1];
	}
	}
	inDot = 1;
	amount=befDotamt;
	}
	   	
	 tempamount=amount;
	if(amount.length()<formatlength){
	for(int i=0;i<formatlength-(amount.length());i++){
	tempamount="0"+tempamount;
	
	}
	}
	  }	

	  String myformat="";
	  for(int i=0;i<format.length();i++){
	if(format.substring(i,i+1).equals("."))
	break;	
	else
	myformat=myformat+format.substring(i,i+1);
	  }

	  String spli[] = myformat.split("[,]");
	  for(int i=(spli.length-1);i>=0;i--){
	 
	 	int indx = spli[i].length();
	int instate = i*indx;
	  	String stamtSplit =  tempamount.substring(instate,instate+indx);
	  	
	if(i==0)
	  {
	amtSplit = stamtSplit +amtSplit;
	
	  }
	else
	amtSplit = ","+stamtSplit +amtSplit;
	
	  }
	  String tempAmpSplit;
	  String temp;
	  int index = 0;
	  int s1=formatlength+(spli.length-1);
	  	   
	  for(int i=0;i<s1;i++)
	  {
	temp=amtSplit.substring(i,i+1);
	
	if(temp.equals("0")|| temp.equals(","))
	  {
	
	  }
	else
	 {
	index=i;
	break;
	 }
	  }
	  
	  
	  tempAmpSplit=amtSplit.substring(index,s1);
	 	
	   String retData = "";
	   String appChar="";
	   int the_length=tempAmpSplit.length();
	  	  
	   if(tempAmpSplit.charAt(the_length-1)==','){
	  retData = tempAmpSplit.substring(0,tempAmpSplit.length()-1);
	 	  
	   }
	   else{
	  	retData = tempAmpSplit.substring(0,tempAmpSplit.length());
	
	   }
	if(inDot==1)
	{
	retData = retData +"."+afterDotamt;
	
	}
	
	// Added by ameen
	
	if(retData.indexOf(".")!=-1)
	{
	String str = retData;
	str = str.substring(str.indexOf(".")+1,str.length());
	if(str.length()!=2) ////CHG006
	appChar = "0";
	}else if(retData.indexOf(".")==-1)
	{	
	String str = retData;
	str = str.substring(str.length());
	appChar = ".00";
	}
	
	retData = retData+appChar;
	
	   //
	   if(isCurrencycheck)
	   {
	   	 	 String currencyList="KWD%3,OMR%4,JPY%0";
	    	 String arr[]=currencyList.split("[,]");
	        
	 String decimalString = "";
	 int strPositions = 0;
	 boolean flag=true;
	 String convertedAmt;
	     	 for(int i=0;i<arr.length;i++)
	 {
	  String cardNumber[] = arr[i].split("[%]");
	  if(cardNumber[0]==currency)
	  {
	   strPositions=Integer.parseInt(cardNumber[1]);
	   flag=false;
	   break;
	  }
	 }
	 if(flag)
	strPositions=2;
	 if(retData.indexOf(".") != -1){
	String[] amountArr = retData.split("[.]");
	if(strPositions != amountArr[1].length()){
	decimalString = amountArr[1];
	for(int i=amountArr[1].length();i<strPositions;i++){
	decimalString = decimalString + "0";
	}
	retData = amountArr[0]+decimalString;
	}
	 }
	 
	    }
	return retData; 
	}

//Added against DEF11	
public static String getAmountFormat(String amount,String format,String currency){
	
	    String amtSplit = "";
	int inDot=0;
	int formatlength=0;
	     boolean isCurrencycheck=true;
	     String tempamount="";
	     String afterDotamt="";
	     String retData = "";
	     if(amount.length()>0 && !amount.equals("0") && amount!=null && !amount.equals(""))
	     {
	    for(int i=0;i<format.length();i++){
	if(format.substring(i,i+1).equals("#"))
	formatlength++;
	else if(format.substring(i,i+1).equals("."))
	break;
	
	}
	amount = amount.toString();
	
	if(amount.charAt(0)=='.')
	{
	tempamount="0"+amount;
	        return tempamount;
	}
	if(amount.charAt(0)=='.' || amount.charAt(0)=='0')
	{
	        tempamount=amount;
	        return tempamount;
	}

	
	
	if(amount.indexOf(".")!=-1)
	{
	String splitDot[] = amount.split("[.]");
	String befDotamt="";
	
	for(int i=0;i<splitDot.length;i++)
	{
	if(i==0)
	{
	befDotamt=splitDot[0];
	}
	else if(i>0)
	{
	afterDotamt=splitDot[1];
	}
	}
	inDot = 1;
	amount=befDotamt;
	}
	 tempamount=amount;
	if(amount.length()<formatlength){
	for(int i=0;i<formatlength-(amount.length());i++){
	tempamount="0"+tempamount;
	}
	}
	  String myformat="";
	  for(int i=0;i<format.length();i++){
	if(format.substring(i,i+1).equals("."))
	break;	
	else
	myformat=myformat+format.substring(i,i+1);
	  }

	  String spli[] = myformat.split("[,]");
	  for(int i=(spli.length-1);i>=0;i--){
	 
	 	int indx = spli[i].length();
	int instate = i*indx;
	  	String stamtSplit =  tempamount.substring(instate,instate+indx);
	if(i==0)
	  {
	amtSplit = stamtSplit +amtSplit;
	  }
	else
	amtSplit = ","+stamtSplit +amtSplit;
	  }
	  
	  for(int i=0;i < myformat.length();i++){
	if(myformat.substring(i,i+1).equals(",")){
	amtSplit = amtSplit+",";
	tempamount = amtSplit+tempamount.substring(i, tempamount.length());
	}else{
	amtSplit = amtSplit + tempamount.substring(i,i+1);
	}
	}
	  
	  String tempAmpSplit;
	  String temp;
	  int index = 0;
	  int s1=formatlength+(spli.length-1);
	  for(int i=0;i<s1;i++)
	  {
	temp=amtSplit.substring(i,i+1);
	if(temp.equals("0")|| temp.equals(","))
	  {
	
	  }
	else
	 {
	index=i;
	break;
	 }
	  }
	  
	  
	  tempAmpSplit=amtSplit.substring(index,s1);
	   String appChar="";
	   int the_length=tempAmpSplit.length();
	  
	   if(tempAmpSplit.charAt(the_length-1)==','){
	  retData = tempAmpSplit.substring(0,tempAmpSplit.length()-1);
	   }
	   else{
	 	retData = tempAmpSplit.substring(0,tempAmpSplit.length());
	   }
	if(inDot==1)
	{
	retData = retData +"."+afterDotamt;
	}
	
	// Added by ameen
	if(!isCurrencycheck)
	{
	if(retData.indexOf(".")!=-1)
	{
	String str = retData;
	str = str.substring(str.indexOf(".")+1,str.length());
	if(str.length()!=2) ////CHG006
	appChar = "0";
	}else if(retData.indexOf(".")==-1)
	{	
	String str = retData;
	str = str.substring(str.length());
	appChar = ".00";
	}
	}	
	retData = retData+appChar;
	    
	   //Currency Check
	   if(isCurrencycheck)
	   {  	
	     
	   	 	 //String currencyList="KWD%3,OMR%4,JPY%0";
	   	ResourceBundle res = ResourceBundle.getBundle("infra");
	   	String currencyList = res.getString("PAYCCYDECIMAL");
	    	String arr[]=currencyList.split("[,]");
	        String decimalString = "";
	 int strPositions = 0;
	 boolean flag=true;
	// String convertedAmt;//FB_CODE_STAND
	     	 for(int i=0;i<arr.length;i++)
	 {
	  String cardNumber[] = arr[i].split("[%]");
	  if(cardNumber[0].equals(currency))
	  {
	   strPositions=Integer.parseInt(cardNumber[1]);
	   flag=false;
	   break;
	  }
	 }
	 if(flag)
	strPositions=2;
	 if(retData.indexOf(".") != -1){
	String[] amountArr = retData.split("[.]");
	if(strPositions != amountArr[1].length()){
	decimalString = amountArr[1];
	
	if(decimalString.length()<strPositions)
	{
	for(int i=amountArr[1].length();i<strPositions;i++){
	decimalString = decimalString + "0";
	}	
	}else{
	decimalString = decimalString.substring(0, strPositions);
	
	}
	if("JPY".equals(currency)){
	retData = amountArr[0];
	}else{
	retData = amountArr[0]+"."+decimalString;
	}
	
	}
	 }else if(retData.indexOf(".") == -1){
	 	for(int i=0;i < strPositions;i++){
	decimalString = decimalString + "0";
	}
	 	if(!"JPY".equals(currency)){
	 	retData = retData +"."+decimalString;
	 	}
	 }
	   	   }
	    }
	return retData; 
	}
	// DEF 241
	//Added by 66826 - Start
	public String getNFormat(String amountParse){
  String parseConverter="";
	String appender="";
	try
  {
	 String cmName = "[DashBoardDao.getNFormat]";
	 if(!"".equals(amountParse) && amountParse!=null){
	Locale locale = Locale.getDefault();
	        DecimalFormat dformat = new DecimalFormat();
	        NumberFormat nformat = dformat.getNumberInstance(locale);
	double lng = Double.parseDouble(amountParse);
	parseConverter = nformat.format(lng);
	if(parseConverter.indexOf(".")!=-1)
	{
	String str = parseConverter;
	str = str.substring(str.indexOf(".")+1,str.length());
	if(str.length()!=2)
	  appender = "0";
	}else if(parseConverter.indexOf(".")==-1)
	{	
	String str = parseConverter;
	str = str.substring(str.length());
	appender = ".00";
	}
	parseConverter = parseConverter+appender;
	}
	 }catch(NumberFormatException ep){
	 logger.error("getNFormat"+Utility.getPrintStackTrace(ep));//FB_CODE_STAND
   }catch(Exception ex){
  	 logger.error("getNFormat"+Utility.getPrintStackTrace(ex));//FB_CODE_STAND
   }	     
	 return parseConverter;
	}
	//Downloaod sync-up starts	
	public static String getNoFormat(String amountParse)
	{
	    String parseConverter="0.00";
	String appender="";
	try
	    {
	//REGRESSION_101
	if(!"".equals(amountParse) && amountParse!=null)
	{
	if(amountParse.contains("."))
	return amountParse;
	}
	if(!"".equals(amountParse) && amountParse!=null ){
	Locale locale = Locale.getDefault();
	        DecimalFormat dformat = new DecimalFormat("###,###.##");
	        //NumberFormat nformat = dformat.getNumberInstance(locale);//FB_CODE_STAND
	double lng = Double.parseDouble(amountParse);
	parseConverter = dformat.format(lng); 
	if(parseConverter.indexOf(".")!=-1)
	{
	String str = parseConverter;
	str = str.substring(str.indexOf(".")+1,str.length());
	if(str.length()!=2)
	  appender = "0";
	}else if(parseConverter.indexOf(".")==-1)
	{	
	String str = parseConverter;
	str = str.substring(str.length());
	appender = ".00";
	}
	parseConverter = parseConverter+appender;
	}
	 }catch(NumberFormatException ep){
	 logger.error("getNFormat"+Utility.getPrintStackTrace(ep));//FB_CODE_STAND
	     }catch(Exception ex){
	    	 logger.error("getNFormat"+Utility.getPrintStackTrace(ex));//FB_CODE_STAND
	     }
	 return parseConverter;
	}
	//Downloaod sync-up ends
	public String getDateFormat(String inputDate)
	{
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
	     SimpleDateFormat nformatter = new SimpleDateFormat("dd/MM/yyyy");
	     Date newDate;
	     String convertDate = "";
	     try {
	            newDate = formatter.parse(inputDate);
	            convertDate = nformatter.format(newDate);
	        } catch (ParseException e) {        	
	        	  e.printStackTrace();
	        }   
	return convertDate;
	}
	//Added by 66826 - End
	public static String checknull (String value)
	{
	if(value == null )
	return "";
	//Regression starts
	else if(value.equals("null"))
	return "";
	else if(value.contains("null"))
	return "";
	//Regression ends
	else
	return value;
	}
	
	*//**
   * This method used to replace the null value with given String value. Typically it
   * returns "Empty String" for Null value.. We can replace the null value with custom value
   * by passing custom value as an argument.
   *//*
  public String getNullReplacedValue(String value){
  	// By default it will change null to empty
  	return getNullReplacedValue(value, "");
  }
  
  public String getNullReplacedValue(String value, String valueToBeReplaced){
  	if(value == null){
  	return valueToBeReplaced;
  	}else{
  	if(value.trim().length() < 1 || "Null".equalsIgnoreCase(value)){
  	return valueToBeReplaced;
  	}
  	}
  	return value;    	
  }
  
  
   * To get All the currency codes and their currency discriptions
   * @return: HashMap - reurns a HashMap whcih contains currency codes as key and their currency discriptions as values
   * Added for Mashreq Exchange Rate Inquiry by VRM 
   
  
  public HashMap getCurrencyNames(){
  	logger.info("Enter in to getCurrencyNames()");
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String query = "select cod_ccy, txt_ccydesc from cib_orbicash_currency";
  	HashMap ccyMap	= new HashMap();
  	try{
  	logger.info("Executing Query : " + query);
      	   	 
  	con = getConnection();
  	if(con != null){
  	stmt = con.createStatement();
  	rs = stmt.executeQuery(query);
  	//int recordCount = 1;
  	while(rs.next()){
  	//logger.debug("Fetching the Record : " + recordCount);
  	ccyMap.put(rs.getString(1), rs.getString(2));
  	//logger.debug("Key : " + rs.getString(recordCount)+" Value : " + rs.getString(recordCount++));
  	}
  	logger.debug("The ccyMap : " + ccyMap);
  	}else{
  	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the specified Current Details.");
  	}
  	}catch (Exception e) {
	//FB_CODE_STAND starts
  	logger.error("getCurrencyNames"+Utility.getPrintStackTrace(e));
	}finally
	{
	if(rs != null)
	{
	try{ rs.close(); }
	catch (Exception e3) { //e3.printStackTrace();
	 }
	}
	
	if(stmt != null)
	{
	try{ stmt.close(); }
	catch (Exception e2) { //e2.printStackTrace(); 
	}
	}
	
	if(con != null)
	{
	try{ con.close(); }
	catch (Exception e1) { //e1.printStackTrace(); 
	}
	}
	//FB_CODE_STAND ends
	}
	logger.info("Exited from getCurrencyNames()");
	
	  	return ccyMap;
  }
  
	
	*//** 
	 *  To get the current date which is specified in Liquidity.
	 * @return: String - returns Specified current date in Liquidity.  If it is not exist then returns null.
	 *//*
	public String getSpecifiedCurrentDate() throws Exception{
	logger.info("Enter in to getSpecifiedCurrentDate()");
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String specifiedCurrentDate = null;
	try{
	StringBuilder query = new StringBuilder(75);
	query.append("select to_char(DAT_TODAY, 'yyyy-mm-dd') from OLM_SYSTEMDATES");
	logger.info("Executing Query : " + query);
	 
	con = getConnection();
	if(con != null){
	stmt = con.createStatement();
	rs = stmt.executeQuery(query.toString());
	if(rs.next()){
	specifiedCurrentDate = rs.getString(1);
	}
	logger.info("Specified Current Date : " + specifiedCurrentDate);
	}else{
	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the specified Current Date.");
	}
	}catch (Exception e) {
	//FB_CODE_STAND starts
	logger.error("getSpecifiedCurrentDate"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}finally{
	if(rs != null){
	try{ rs.close(); }
	catch (Exception e3) {// e3.printStackTrace();
	}
	}
	
	if(stmt != null){
	try{ stmt.close(); }
	catch (Exception e2) { //e2.printStackTrace(); 
	}
	}
	if(con != null){
	try{ con.close(); }
	catch (Exception e1) { //e1.printStackTrace();
	}
	 //FB_CODE_STAND ends
	}
	}
	logger.info("Exited from getSpecifiedCurrentDate()");
	
	return specifiedCurrentDate;
	}
	
	//Added for Mashreq - start
	public static Date getStringToDate(String strDate)
	{
	logger.info("Enter in to getStringToDate()");
	SimpleDateFormat formatter ; 
	    Date date = null; 
	    formatter = new SimpleDateFormat("dd/MM/yyyy");
	    try
	    {
	    	logger.debug("Date String:: "+strDate);
	    	date = (Date)formatter.parse(strDate);
	    }catch(ParseException e){
	    	logger.error("getStringToDate"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	    }
	    logger.debug("Date Value:: "+date);
	    return date;
	}
	
	//Added By Muthuraj -Start
	public String getRatesForCurrency(String paramDepCcy) throws Exception
	{
	logger.info("Enter in to getRatesForDepositCurrency()");
	Connection con = null;
	//Tech Mahindra code review changes for Sql injection starts
	//Statement stmt = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String rateDepCcy = null;
	try{
	StringBuilder query = new StringBuilder(100);
	query.append("SELECT BUY_RATE FROM OD_CCY_CONVERSION_TBL WHERE OD_CCY_CODE='"+paramDepCcy+"'");
	logger.info("Utility::getRatesForDepositCurrency()::Executing Query : " + query);
	con = getConnection();
	logger.info("Utility::getRatesForDepositCurrency()::Connection : "+con);
	if(con != null){
	//stmt = con.createStatement();
	 stmt = con.prepareStatement(query.toString());
	logger.info("Utility::getRatesForDepositCurrency()::Statement : "+stmt);
	//rs = stmt.executeQuery(query.toString());
	rs = stmt.executeQuery();
	logger.info("Utility::getRatesForDepositCurrency()::ResultSet : "+rs);
	if(rs.next()){
	rateDepCcy = rs.getString(1);
	}
	logger.info("Utility::getRatesForDepositCurrency():: Current Rate : " + rateDepCcy);
	}else{
	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the specified Current Date.");
	}
	}catch (Exception e) {
	logger.error("getRatesForCurrency"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}finally{
	if(rs != null){
	try{ rs.close(); }
	//FB_CODE_STAND starts
	catch (Exception e) { //e.printStackTrace();
	}
	}
	
	if(stmt != null){
	try{ stmt.close(); }
	catch (Exception e) { //e.printStackTrace(); 
	}
	}
	
	if(con != null){
	try{ con.close(); }
	catch (Exception e) { //e.printStackTrace(); 
	}
	//FB_CODE_STAND ends
	}
	}
	logger.info("Exited from Utility::getRatesForDepositCurrency()");
	
	return rateDepCcy;
	}

	
	public String getAccountTitle(String acNo) throws Exception
	{
	logger.info("Enter in to getAccountTitle()");
	Connection con = null;
	//Statement stmt = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String acTitle = "";
	try{
	StringBuilder query = new StringBuilder(100);
	query.append("SELECT ACCOUNT_NAME  FROM VW_GLOBAL_ACCOUNT_MASTER WHERE AC_NO='"+acNo+"'");//Sanity_Fix
	logger.info("Utility::getAccountTitle()::Executing Query : " + query);
	 
	con = getConnection();
	logger.info("Utility::getAccountTitle()::Connection : "+con);
	if(con != null){
	//stmt = con.createStatement();
	stmt = con.prepareStatement(query.toString());
	logger.info("Utility::getAccountTitle()::Statement : "+stmt);
	//rs = stmt.executeQuery(query.toString());
	rs = stmt.executeQuery();
	logger.info("Utility::getAccountTitle()::ResultSet : "+rs);
	if(rs.next()){
	acTitle = rs.getString(1);
	}
	logger.info("Utility::getAccountTitle()::Specified Current Date : " + acTitle);
	}else{
	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the specified Current Date.");
	}
	}catch (Exception e) {
	logger.error("getAccountTitle"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}finally{
	if(rs != null){
	try{ rs.close(); }
	//FB_CODE_STAND starts
	catch (Exception e3) { //e3.printStackTrace(); 
	}
	}
	
	if(stmt != null){
	try{ stmt.close(); }
	catch (Exception e2) { //e2.printStackTrace(); 
	}
	}
	
	if(con != null){
	try{ con.close(); }
	catch (Exception e1) { //e1.printStackTrace(); 
	}
	//FB_CODE_STAND ends
	}
	}
	logger.info("Utility::Exited from getAccountTitle()");
	
	return acTitle;
	}
	
	//Added By Muthuraj - End
	//Added for Mashreq - end
	//CHG-001-EXG -Starts - Thangamani Added
	public String getCIFForAccNo(String accNo)throws Exception
	{
	logger.info("Entered into getCIFForAccNo");
	String CIFId="";
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs = null;
	PreparedStatement pStmt = null;//ETH ISSUE
	DataManager dManager = null;
	String query = "select cif from vw_gid_unit_mapping where od_acc_no=?";
	
	logger.info("Actual Query"+query);
	try
	{
	dManager = new DataManager();
	conn = dManager.getConnection();
	//ETH Issue Starts
	pStmt=conn.prepareStatement(query);
	pStmt.setString(1,accNo);
	//stmt = conn.createStatement();
	//rs = stmt.executeQuery(query);
	rs = pStmt.executeQuery();
	//ETH Issue Ends
	if(rs.next()){	
	CIFId = rs.getString(1);
	}
	}catch(SQLException ex){
	logger.error("getCIFForAccNo"+Utility.getPrintStackTrace(ex));//FB_CODE_STAND	
	}catch(Exception e){
	logger.error("Error in Utility::Exited from getCIFId()"+CIFId);
	logger.error("getCIFForAccNo"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}
	finally{
	try
	{
	if(rs!=null)
	rs.close();
	}catch(SQLException sqe){
	//sqe.printStackTrace();//FB_CODE_STAND
	}catch(Exception exc){
	//exc.printStackTrace();//FB_CODE_STAND
	}
	try
	{
	if(stmt!=null)
	stmt.close();
	// FB_CODE_STAND STARTS
	if(pStmt != null)
	   pStmt.close();
	   
	// FB_CODE_STAND ENDS  	
	}catch(SQLException sq){
	//sq.printStackTrace();//FB_CODE_STAND
	}catch(Exception excep){
	//excep.printStackTrace();//FB_CODE_STAND
	}
	// FB_CODE_STAND STARTS
	try
	{
	if(conn != null)
	conn.close();
	
	}
	catch(Exception excep){
	//excep.printStackTrace();//FB_CODE_STAND
	}
	// FB_CODE_STAND ENDS	
	}
	logger.info("Utility::Exited from getCIFId()"+CIFId);
	return CIFId;
	}
	//CHG-001-ExG -Ends
	 public static int alertEngineInsert(HashMap inputMap) 
	 {
	 return 0;
	  
	    String myName	= " [Utility.alertEngineInsert] ";
	logger.debug(myName+"Entered into method alertEngineInsert");
	//  CallableStatement lcStmt	= null;
	PreparedStatement ps	= null;
	    int retVal	= 0;
	StringBuffer sql	= new StringBuffer();
	Utility util=new Utility();
	 Connection con =null;
	    try {
	       
	             logger.debug(myName+"inputVector : "+inputMap);
	       
	         con = util.getConnection();
	 logger.debug(myName+"Conn is closed " + con.isClosed());
	/*
	 lcStmt = lcon.prepareCall( "{call MAIL_INSERT_SP(?,?,?,?,?,?,?,?)}" );
	         lcStmt.setString(1, (String) inputVector.get(1) ); // refNo
	         lcStmt.setString(2, (String) inputVector.get(2) ); // userid
	         lcStmt.setString(3, (String) inputVector.get(3) ); // cifno
	         lcStmt.setString(4, HEAD_OFF_BR_CODE);
	         lcStmt.setString(5, (String) inputVector.get(4)); // subject
	         lcStmt.setString(6, (String) inputVector.get(5)); // MailBody
	         lcStmt.setString(7, "U"); 

	         lcStmt.registerOutParameter(8,Types.VARCHAR);

	         lcStmt.execute();
	
	sql.append("INSERT INTO OD_ALERT_ENGINE_MB (");
	sql.append("PRODUCT");
	sql.append(",SUB_PRODUCT");
	sql.append(",OD_REF_NO"); 
	sql.append(",TXN_STATUS");
	sql.append(",MAIL_SUBJECT");
	sql.append(",MAIL_REF"); 
	sql.append(",ALERT_FLAG");
	sql.append(",ALERT_DATE");
	sql.append(",ERROR_CODE) "); 
	sql.append("VALUES (");
	sql.append("'prod'");
	sql.append(","+checknull((String)inputMap.get("SUB_PRODUCT")));
	sql.append(","+checknull((String)inputMap.get("OD_REF_NO")));
	sql.append(","+checknull((String)inputMap.get("TXN_STATUS")));
	sql.append(","+checknull((String)inputMap.get("MAIL_SUBJECT")));
	sql.append(","+checknull((String)inputMap.get("MAIL_REF")));
	sql.append(",'N'");
	sql.append(",sysdate");
	sql.append(",'ERR'");
	sql.append(")");

	        String lretString = sql.toString();

	logger.debug(myName+"SQL: "+lretString);

	ps = con.prepareStatement(lretString);
	ps.execute();

	 String Query=SQLMaster.ALERT_ENGINE_INSERT;
	//create hashtable to subsitute run time value
	
	ps = con.prepareStatement(Query);
	ps.setString(1, "prod");
	ps.setString(2, checknull((String)inputMap.get("SUB_PRODUCT")));
	ps.setString(3, checknull((String)inputMap.get("OD_REF_NO")));
	ps.setString(4, checknull((String)inputMap.get("TXN_STATUS")));
	ps.setString(5, checknull((String)inputMap.get("MAIL_SUBJECT")));
	ps.setString(6, checknull((String)inputMap.get("MAIL_REF")));
	ps.setString(7, "N");
	ps.setString(8,"ERR2");
	ps.setString(9,checknull((String)inputMap.get("TYPE")));
	ps.setString(10,checknull((String)inputMap.get("GCIF")));
	ps.setString(11,checknull((String)inputMap.get("USER_NO")));
	//getting records from result set
	ps.executeQuery();
	      
	        }catch(Exception e) {
	            retVal = -1;
	            getPrintStackTrace(e);
	
	       }
	       finally {
	            try {
	                /*if( lcStmt != null )
	                lcStmt.close();
	                if( ps != null )
	                ps.close();
	                if( con != null )
	                	con.close();
	            }catch(Exception eignore){ getPrintStackTrace(eignore);}
	       }
	       return retVal; 
	    }	 
	//Added By Muthuraj - End
	public static ArrayList splitString(String strVal,int maxLen){
	//String cmName = "splitString()";//FB_CODE_STAND
	logger.info("Entered into splitString()");
	int j = 0;
	int k=0;
	int strCnt = 0;
	int strLen = 0;
	ArrayList strList = null;
	if(strVal!=null){
	strList = new ArrayList();
	strLen = strVal.length();
	double strTempCnt = (strLen /(float)maxLen);
	strCnt = (int)Math.ceil(strTempCnt);
	if(strLen <= maxLen){
	strVal = strVal.substring(0,strLen);
	strList.add(0, strVal);
	}
	else 
	{
	for(int i=0;i<strCnt;i++){
	k+=maxLen;
	if(k <= strLen)
	strList.add(i, strVal.substring(j, k));
	else
	strList.add(i, strVal.substring(j, strLen));
	j=k;
	}
	}
	}
	logger.debug("Exiting from splitString()...");
      return strList;
	}
	
	//Added for Mashreq By Thangamani -Starts
	public static HashMap getParseCommonDetails(String sValue) throws Exception
	{
	logger.debug("Enter in to getParseCommonDetails");
	HashMap dataMap = null;
	StringTokenizer strMainToken = null;
	StringTokenizer strSubToken = null;
	String tokens = "";
	String key = "";
	String value = "";
	String CommonData="";
	
	try
	{
	dataMap = new HashMap();
	logger.debug("Input HashDetail sValue==>"+sValue);
	    
	    int end=sValue.indexOf("##");
	if (end!=-1)
	{
	CommonData=sValue.substring(0,end);
	    logger.debug("Before Parse Value ====>"+CommonData);	
	}
	else
	{
	 CommonData=sValue;
	}
	strMainToken = new StringTokenizer(CommonData, JSPIOConstants.INTEG_DELIMITERS);
	while(strMainToken.hasMoreTokens())
	{
	tokens = (String)strMainToken.nextElement();
	if(tokens.endsWith("="))
	tokens = tokens+"null";
	strSubToken = new StringTokenizer(tokens, JSPIOConstants.EQULAS_SYM);
	while(strSubToken.hasMoreTokens())
	{
	key = strSubToken.nextToken();
	value = strSubToken.nextToken();
	dataMap.put(key,value);
	}
	}
	}
	catch(Exception e)
	{
	logger.error("getParsingHashDetails"+Utility.getPrintStackTrace(e));
	}
	return dataMap;
	}
//Ends 
	//Added for Mashreq - end
	
	public String getTemplateName(String tempID) throws Exception
	{
	logger.info("Enter in to getTemplateName()");
	Connection con = null;
	//Statement stmt = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String tempName = null;
	try{
	StringBuilder query = new StringBuilder(100);
	query.append("SELECT A.TEMPLATE_NAME FROM SYN_FILE_TEMP_MST A WHERE TEMPLATE_ID='"+tempID+"'");
	logger.info("Utility::getTemplateName()::Executing Query : " + query);
	 
	con = getConnection();
	logger.info("Utility::getTemplateName()::Connection : "+con);
	if(con != null){
	//stmt = con.createStatement();
	stmt = con.prepareStatement(query.toString());
	logger.info("Utility::getTemplateName()::Statement : "+stmt);
	//rs = stmt.executeQuery(query.toString());
	rs = stmt.executeQuery();
	logger.info("Utility::getTemplateName()::ResultSet : "+rs);
	if(rs.next()){
	tempName = rs.getString(1);
	}
	logger.info("Utility::getTemplateName()::Specified Template : " + tempName);
	}else{
	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the specified Current Date.");
	}
	}catch (Exception e) {
	logger.error("getTemplateName"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}finally{
	if(rs != null){
	try{ rs.close(); }
	//FB_CODE_STAND starts
	catch (Exception e3) { //e3.printStackTrace(); 
	}
	}
	
	if(stmt != null){
	try{ stmt.close(); }
	catch (Exception e2) { //e2.printStackTrace(); 
	}
	}
	
	if(con != null){
	try{ con.close(); }
	catch (Exception e1) { //e1.printStackTrace(); 
	}
	//FB_CODE_STAND ends
	}
	}
	logger.info("Utility::Exited from getAccountTitle()");
	
	return tempName;
	}
	//CHG001_59862 Starts
	 *//**
	 * Used to get splitted Response value,
	 * @param content -  Contains response value
	 * @return List<HashMap>  Returns response value
	 *//*
	public static  ArrayList getConstructedNVPList(String content)throws Exception
	{
	//String cmName = "[UTILITY.getConstructedNVPList()]";//FB_CODE_STAND
	String actualRecords="";
	String records="";
	HashMap recordMap=null;
	ArrayList aList= new ArrayList();
	
	   try{
	
	   int startPos=content.indexOf("##");
	   int endPos=content.lastIndexOf("##");
	   
	 if (startPos!=-1 && endPos!=-1)
	{
	actualRecords=content.substring(startPos+2,endPos);
	
	}
	 else
	{
	actualRecords = content;
	}
	  
	   if(actualRecords.contains("@@"))
	   {
	   int staticIndex=actualRecords.indexOf("@@");
	   actualRecords=actualRecords.substring(0,staticIndex);
	   }
	   
	   StringTokenizer st = new StringTokenizer(actualRecords,"%%");
	while(st.hasMoreTokens())
	{
	try	{
	
	records=st.nextToken();
	logger.debug("records"+records);
	if(records.contains("^^"))
	{
	String capList = "";
	StringTokenizer st2 = new StringTokenizer(actualRecords,"^^");
	System.out.println("2222");
	while(st2.hasMoreTokens())
	{
	capList = st2.nextToken();
	System.out.println("capList"+capList);
	recordMap = new HashMap();
	recordMap=constructMap(capList);
	aList.add(recordMap);
	}
	}
	else
	{
	logger.debug("records"+records);
	recordMap = new HashMap();
	recordMap=constructMap(records);
	aList.add(recordMap);
	}
	
	
	}catch(Exception e){
	logger.error("Exception"+e.getMessage());
	}
    }

	}catch(Exception ex)
	{
	logger.error("Exception"+ex.getMessage());
	 throw ex;
	}
	 	return aList;
	}
	*//**
	 * Used to get key value pair from the Response value,
	 * @param commonloop -  Contains response value
	 * @return HashMap  Returns response value
	 *//*
	public static HashMap constructMap(String commonloop)
  {
	String key="";
	String value="";
	logger.debug("commonloop" + commonloop);
	StringTokenizer recordlist = new StringTokenizer(commonloop,"~*");
	HashMap recordMap=new HashMap();
	logger.debug("recordlist.hasMoreTokens()" + recordlist.hasMoreTokens());
	while(recordlist.hasMoreTokens())
	{
	
	commonloop=recordlist.nextToken();
	if(commonloop.contains("=")){
	key = commonloop.substring(0,commonloop.indexOf("=")).trim();
	value = commonloop.substring(commonloop.indexOf("=")+1,commonloop.length());
	recordMap.put(key,value);
	}
	
	}
	logger.debug("recordMap" + recordMap);
	return recordMap;
  }
	*//**
	 * Used to get the static data from the Response value,
	 * @param content -  Contains response value
	 * @return HashMap  Returns static data map
	 *//*
	public static HashMap constructStaticMap(String content)
  {
	String staticData="";
	HashMap recordMap = new HashMap();
	String actualRecords="";
	   try{
	int startPos=content.indexOf("##");
	int endPos=content.lastIndexOf("##");
	   if (startPos!=-1 && endPos!=-1)
	{
	actualRecords=content.substring(startPos+2,endPos);
	
	}else{
	actualRecords = content;
	}
	 if(actualRecords.contains("@@"))
	   {
	   int staticIndex=actualRecords.indexOf("@@");
	   staticData=actualRecords.substring(staticIndex+2,actualRecords.length()-2);
	   actualRecords=actualRecords.substring(0,staticIndex);
	   	recordMap = new HashMap();
	recordMap=constructMap(staticData);
	   }
	   }catch(Exception e)
	   {
	   logger.error("constructStaticMap"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	   }
	return recordMap;
  }
	public static String getDateFormater(String inputDate,String inputFormat,String outputFormat){
	//String cmName = "[Converter.getDateFormater()]";//FB_CODE_STAND
      SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);        
      SimpleDateFormat nformatter = new SimpleDateFormat(outputFormat);
      Date newDate;
      String convertDate = "";
      try {
          newDate = formatter.parse(inputDate);
          convertDate = nformatter.format(newDate);
      } catch (ParseException e) {        	
      	  //e.printStackTrace();//FORTIFY_CHG
      }   
	return convertDate;
	}
	*//**
	 * Used to get the error data from the Response value,
	 * @param content -  Contains response value
	 * @return HashMap  Returns error map
	 *//*
	public static HashMap constructErrorMap(String content)
  {
	String errorData="";
	HashMap recordMap = new HashMap();
	try{
	int startPos=content.indexOf("##");
	int endPos=content.lastIndexOf("##");
	   if (startPos!=-1 && endPos!=-1)
	{
	errorData = content.substring(endPos+2,content.length()-2);
	recordMap = new HashMap();
	recordMap=constructMap(errorData);
	
	   }
	}catch(Exception e)
	   {
	 logger.error("constructErrorMap"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	   }
	return recordMap;
	}

	*//**
	 * Used to get the error data from the Response value,
	 * @param content -  Contains response value
	 * @return HashMap  Returns error map
	 *//*
	public static HashMap dashboardConstructErrorMap(String content)////Changed for Export Details
	{


	String errorData="";
	HashMap recordMap = new HashMap();
	try{
	int startPos=content.indexOf("##");
	int endPos=content.lastIndexOf("##");
	if (startPos!=-1 && endPos!=-1)
	{
	//errorData = content.substring(endPos+2,content.length()-2);
	errorData = content.substring(endPos+2,content.length());
	//logger.debug("content2::::::::::: " + content);
	recordMap = new HashMap();
	recordMap=constructMap(errorData);

	}
	}catch(Exception e)
	{
	 logger.error("dashboardConstructErrorMap"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}
	return recordMap;
	}


	//SIT_66826
	*//**
	 * To get formatted Date
	 *  
	 
	public static String convertDateFormat(Date date)
	{
	String cDate = "";
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
	if(date!=null)
	{
	cDate = formatter.format(date);
	}
	return cDate;
	}*//*
	public static String convertDateFormat(Object date)
	{
	String cDate = "";
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
	if(date!=null)
	{
	cDate = formatter.format(date);
	}
	return cDate;
	}
	
	//CHG001_59862 ends
	*//** 
	 *  To get the Sub Product Name based on Sub Product Code..
	 * @return: String - returns Sub Product Name.  If it is not exist or null then sub product code to be assigned to sub product name.
	 *//*
	public String getSubProductNameFromCode(String subProdCode) throws Exception{
	logger.info("Enter in to getSubProductNameFromCode()");
	String subProdName = null;
	Connection con = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	try{
	StringBuilder query = new StringBuilder(75);
	query.append("select TXT_SUBPROD from OLM_SUBPRODUCT WHERE COD_SUBPROD = ?");
	logger.info("Executing Query : " + query);
	logger.info("Executing Query Parameter : " + subProdCode);
	 
	con = getConnection();
	if(con != null){
	pStmt = con.prepareStatement(query.toString());
	pStmt.setString(1, subProdCode);
	rs = pStmt.executeQuery();
	if(rs.next()){	
	subProdName = rs.getString(1);
	}	
	}else{
	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the Sub Product Name for the Sub Produt Code : " + subProdCode);
	}
	
	if(subProdName != null && subProdName.trim().length() > 0){
	// Do Nothing..
	}else{
	logger.warn("Required Sub Product Name is invalid. Sub Product Code is assigned to Sub Product Name. Invalid Sub Product Name is : " + subProdName);
	subProdName = subProdCode;
	}
	
	logger.info("Sub Product Name is : " + subProdName + " and its code is : " + subProdCode);
	}catch (Exception e) {
	logger.error("getSubProductNameFromCode"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}finally{
	if(rs != null){
	try{ rs.close(); }
	//FB_CODE_STAND starts
	catch (Exception e3) { //e3.printStackTrace(); 
	}
	}
	
	if(pStmt != null){
	try{ pStmt.close(); }
	catch (Exception e2) { //e2.printStackTrace();
	}
	}
	
	if(con != null){
	try{ con.close(); }
	catch (Exception e1) { //e1.printStackTrace(); 
	}
	//FB_CODE_STAND ends
	}
	}	
	logger.info("Exited from getSpecifiedCurrentDate()");
	
	return subProdName;
	}
	//This is for the response date from integrator
	 public static String changeDateFormat(String dateValue){

	        String delimiter="";
	        if(!"".equals(dateValue))
	        {
	            if(dateValue.indexOf("/")!=-1)
	                delimiter = "/";
	            else if(dateValue.indexOf(".")!=-1)
	                delimiter = ".";
	            else if(dateValue.indexOf("-")!=-1)
	                delimiter = "-";
	            else if(dateValue.indexOf("#")!=-1)
	                delimiter = "#";

	            String array[]    = Utility.removeValue(delimiter,dateValue);
	            String returnDate = array[2]+"/"+array[1]+"/"+array[0];
	            return returnDate;
	        }
	        return dateValue;
	     }
	 //Added By Dhasthagir.M
	 public String getBranchForAccNo(String accNo)throws Exception
	{
	logger.info("Entered into getBranchForAccNo");
	String branchCode="";
	Connection conn=null;
	//Statement stmt=null;
	PreparedStatement stmt=null;
	ResultSet rs = null;
	//String query = "SELECT CMS_POSTING_BR from VW_GID_UNIT_MAPPING where OD_ACC_NO='"+accNo+"'";
	String query = "SELECT BRANCH CMS_POSTING_BR from vw_global_account_master where AC_NO='"+accNo+"'";
	
	logger.info("Actual Query"+query);
	try
	{
	conn = getConnection();
	//stmt = conn.createStatement();
	stmt = conn.prepareStatement(query);
	//rs = stmt.executeQuery(query);
	rs = stmt.executeQuery();
	if(rs.next()){	
	branchCode = rs.getString(1);
	}
	}catch(SQLException ex){
	logger.error("Error in Utility::Exited from getBranchForAccNo()"+accNo);
	logger.error("getBranchForAccNo"+Utility.getPrintStackTrace(ex));//FB_CODE_STAND
	}catch(Exception e){
	logger.error("getBranchForAccNo"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}
	finally{
	try
	{   
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null)
	  rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(stmt!=null)
	  stmt.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(conn!=null)
	  conn.close();
	
	    }
	catch(Exception e)
	{
	
	}	
	//FORTIFY_CHG ENDS
	}
	//catch(SQLException sqe){
	//sqe.printStackTrace();//FB_CODE_STAND
	//}
	catch(Exception exc){
	//exc.printStackTrace();//FB_CODE_STAND
	}
	//FORTIFY_CHG STARTS
	
	try
	{
	if(stmt!=null)
	stmt.close();
	}catch(SQLException sq){
	//sq.printStackTrace();//FB_CODE_STAND
	}catch(Exception excep){
	//excep.printStackTrace();//FB_CODE_STAND
	}
	
	//FORTIFY_CHG ENDS
	}
	logger.info("Utility::Exited from getBranchForAccNo()"+branchCode);
	return branchCode;
	}

	// Balaji - Added for Mashreq - Starts
	public String getErrorCode(String hostErroCode){
	logger.info("Enter in to getErrorCode()"+hostErroCode);
	String errCode="";
	String[] successCode=null;
	try{
	successCode=hostErroCode.split("-");
	errCode=successCode[3];
	}catch(Exception e){
	logger.error("Error in Utility::Exited from getErrorCode()"+errCode);
	//e.printStackTrace();
	errCode="NA";
	}
	logger.info("Exiting from getErrorCode()"+errCode);
	return errCode;
	}
	// Balaji - Added for Mashreq - Ends
	
	 
	 *//** Added for Trade Service Request changes start **//*
	 *//**
	 * @param userNo
	 * @return HashMap
	 * This method is used for fetching the user name based on user no
	 *//*
	public HashMap getUserDetails(String userNo)
	{
	logger.log(ENTRYLevel.ENTRY,"Inside getUserDetails API");
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	HashMap user = new HashMap();
	String query = "";
	Utility util = new Utility();

	try
	{	
	query = "SELECT nvl(first_name,'') || ' ' || nvl(middle_name,'') || ' ' || nvl(last_name,'') USER_NAME FROM OD_USERS_MB WHERE OD_USER_NO = ? ";

	connection = util.getConnection();
	ps = connection.prepareStatement(query);
	ps.setString(1,userNo);
	rs = ps.executeQuery();

	if(rs.next())
	{
	user.put("USER_NAME", rs.getString("USER_NAME"));
	}
	logger.debug("user="+user);

	}catch(Exception e){   
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));
	}
	finally{
	try{
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null)
	  rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(ps!=null)
	  ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(connection!=null)
	  connection.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS
	}catch(Exception e){
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));	
	}
	}
	return user;
	}
	public String getCorporateAddress(String gcif)
	    {
	    	
	    	logger.log(ENTRYLevel.ENTRY,"Inside getCorporateAddress API");
	    	Connection connection=null;
	    	PreparedStatement ps=null;
	    	ResultSet rs=null;
	    	//HashMap address = new HashMap();
	    	String corpName = "";
	    	String query = "";
	    	Utility util = new Utility();

	    	try
	    	{	
	    	query = "SELECT OD_FIRST_NAME ||' '|| OD_MIDDLE_NAME ||' '|| OD_LAST_NAME CORP_NAME FROM "+
	    	" OD_CUSTOMER_MASTER_TBL WHERE OD_GCIF = ? ";

	    	connection = util.getConnection();
	    	ps = connection.prepareStatement(query);
	    	ps.setString(1,gcif);
	    	rs = ps.executeQuery();

	    	if(rs.next())
	    	{
	    	corpName = rs.getString("CORP_NAME");
	    	}
	    	logger.debug("CORP_NAME : "+corpName);

	    	}catch(Exception e){   
	    	logger.error("Caught Exception in getCorporateAddress method : "+Utility.getPrintStackTrace(e));
	    	}
	    	finally{
	    	try{
	    	//FORTIFY_CHG STARTS
	    	try
	    	{
	    	   if(rs!=null)
	    	  rs.close();
	    	    }
	    	    catch(Exception e)
	    	       	{
	    	
	    	    }
	    	    try
	    	    {
	    	   if(ps!=null)
	    	  ps.close();
	    	        }
	    	    catch(Exception e)
	    	    {
	    	
	    	    }	
	    	        try
	    	        {
	    	   if(connection!=null)
	    	  connection.close();
	    	    }
	    	        catch(Exception e)
	    	        {
	
	    	        }
	    	        //FORTIFY_CHG  ENDS
	    	}catch(Exception e){
	    	logger.error("Caught Exception in getCorporateAddress method : "+Utility.getPrintStackTrace(e));	
	    	}
	    	}
	    	return corpName;
	    }
	*//** Added for Trade Service Request changes end **//*
	
	// Added By Seetharaman UAT_TRAINING_ISS_99 starts
	public String getAccountCurrency(String acNo) throws Exception
	{
	logger.info("Enter in to getAccountTitle()");
	Connection con = null;
	//Statement stmt = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String accCurrency = null;
	try{
	StringBuilder query = new StringBuilder(100);
	query.append("SELECT CURRENCY FROM VW_GLOBAL_ACCOUNT_MASTER WHERE AC_NO='"+acNo+"'");//Sanity_Fix
	logger.info("Utility::getAccountTitle()::Executing Query : " + query);
	 
	con = getConnection();
	logger.info("Utility::getAccountCurrency()::Connection : "+con);
	if(con != null){
	//stmt = con.createStatement();
	stmt = con.prepareStatement(query.toString());
	logger.info("Utility::getAccountCurrency()::Statement : "+stmt);
	//rs = stmt.executeQuery(query.toString());
	rs = stmt.executeQuery();
	//Tech Mahindra code review changes for Sql injection ends
	logger.info("Utility::getAccountCurrency()::ResultSet : "+rs);
	if(rs.next()){
	accCurrency = rs.getString(1);
	}
	logger.info("Utility::getAccountCurrency()::Specified Current Date : " + accCurrency);
	}else{
	logger.debug("SEVERE: Since the Required Connection is Null, We can not get the specified Current Date.");
	}
	}catch (Exception e) {
	logger.error("getAccountCurrency"+Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}finally{
	if(rs != null){
	try{ rs.close(); }
	//FB_CODE_STAND starts
	catch (Exception e3) { //e3.printStackTrace(); 
	}
	}
	
	if(stmt != null){
	try{ stmt.close(); }
	catch (Exception e2) { //e2.printStackTrace();
	}
	}
	
	if(con != null){
	try{ con.close(); }
	catch (Exception e1) {// e1.printStackTrace(); 
	}
	//FB_CODE_STAND ends
	}
	}
	logger.info("Utility::Exited from getAccountTitle()");
	
	return accCurrency;
	}
	// Added By Seetharaman ends
	
	
	
	//Added for Mashreq Salary Upload Masking
	public static String getMaskedAmount(String amount)
	{
	StringBuffer maskStrBuffer = null;
	maskStrBuffer = new StringBuffer();
	 if(amount!=null && !"".equals(amount))
	 {
	 maskStrBuffer.append("XXXXX.XX");
	 	 char maskChar = 'X';
	 amount=removeCommas(amount).trim();
	  String amtBefDot = amount.substring(0,amount.lastIndexOf("."));
	 String amtAftDot = amount.substring(amount.lastIndexOf(".")+1,amount.length());
	 for(int i=0;i<amtBefDot.length();i++)
	 {
	 maskStrBuffer.append(maskChar);
	 }
	 maskStrBuffer.append(".");
	 for(int i=0;i<amtAftDot.length();i++)
	 {
	 maskStrBuffer.append(maskChar);
	 }
	   	}
	 else
	 {
	 maskStrBuffer.append("");
	 }
	return maskStrBuffer.toString();
	}
	
	 Account Statement - Performance Fix - Starts 
	// UAT_TRAINING_ISSUE_308 starts  Added By seetharaman For getting additional details for accountstatement from view.
	public HashMap getAccStatementAdditionalDetails(String refNo)
	{
	logger.log(ENTRYLevel.ENTRY,"Inside getAccStatementAdditionalDetails API");
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	HashMap accAdditionalValue = null;
	HashMap mainMap = new HashMap();
	ArrayList recList = null;
	String query = "";
	Utility util = new Utility();

	try
	{	
	query = "select MBOL_REF_NO,CORE_REF_NO,DEBIT_ACC,CUSTOMER_REF,TRAN_TYPE,PURP_PYMNT from VM_PMT_ACCOUNT_STMNT_HELPER WHERE CORE_REF_NO IN (" +
	refNo +
	")";
	logger.debug("query " +query);
	connection = util.getConnection();
	ps = connection.prepareStatement(query);
	//ps.setString(1,refNo);
	rs = ps.executeQuery();

	while(rs.next())
	{
	accAdditionalValue = new HashMap();
	recList=new ArrayList();
	accAdditionalValue.put("MBOL_REF_NO", rs.getString("MBOL_REF_NO"));
	accAdditionalValue.put("DEBIT_ACC", rs.getString("DEBIT_ACC"));
	accAdditionalValue.put("CUSTOMER_REF", rs.getString("CUSTOMER_REF"));
	accAdditionalValue.put("TRAN_TYPE", rs.getString("TRAN_TYPE"));
	accAdditionalValue.put("PURP_PYMNT", rs.getString("PURP_PYMNT"));
	recList.add(accAdditionalValue);
	mainMap.put(rs.getString("CORE_REF_NO"), recList);
	}
	logger.debug("mainMap="+mainMap);
	}catch(Exception e){   
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));
	}
	finally{
	try{
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null)
	  rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(ps!=null)
	  ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	if(connection!=null)
	connection.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS
	}catch(Exception e){
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));	
	}
	}
	return mainMap;
	}
	// // UAT_TRAINING_ISSUE_308 ends
	
	public ArrayList getAccStatementDetails(int fromRow, int toRow, String sortBy, String sortByOrder,HashMap objHMInputParam) // ACC_STMT_COLL_CHANGES//PRD_ACC_STMT_09DEC
	{
	
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	HashMap accAdditionalValue = null;
	HashMap mainMap = new HashMap();
	ArrayList mailList = new ArrayList();
	logger.debug("fromRow$$$$" +fromRow);
	logger.debug("toRow$$$$" +toRow);
	String query = "";
	//ACC_STMT_CHANGES - Start
	ArrayList queryList = null;
	String query1 = null;
	String query2 = null;
	String query3 = null;
	String query4 = null;
	String sortDef = "";
	logger.log(ENTRYLevel.ENTRY,"Inside getAccStatementAdditionalDetails API");
	//query += PAGINATE_HEADER;
	//ACC_STMT_CHANGES - End
	//PRD_ACC_STMT_09DEC - Start
	String strUsrNo = "";
	String strDownMode = "";
	String strAccNo = ""; 
	//PRD_ACC_STMT_09DEC - End
	String repType=(String)objHMInputParam.get("REP_TYPE");//ACCOUNT_SUMM_LAST10
	if(sortBy == null || "".equals(sortBy) || "null".equals(sortBy))
	{
	sortBy = "TRANS_REF";
	sortDef = "TRANS_REF";
	}
	if("DES".equals(sortByOrder) || "".equals(sortByOrder) || sortByOrder==null)
	sortByOrder = "DESC";
	Utility util = new Utility();
	if("TRANS_REF".equals(sortBy))
	sortBy = "ORDER BY A.TXN_SEQUENCE ASC ";
	else if("TRAN_DATE".equals(sortBy))
	sortBy = "ORDER BY A.TRAN_DATE "+sortByOrder;
	else if("VALUE_DATE".equals(sortBy))
	sortBy = "ORDER BY A.VALUE_DATE "+sortByOrder;	
	else if("DEB_AMT".equals(sortBy))
	sortBy = "ORDER BY A.TRAN_TYPE DESC, A.AMOUNT "+sortByOrder;
	else if("CRED_AMT".equals(sortBy))
	sortBy = "ORDER BY A.TRAN_TYPE ASC, A.AMOUNT "+sortByOrder;
	try 
	{	
	//PRD_ACC_STMT_09DEC - Start
	strUsrNo = (String)objHMInputParam.get("SEESION_ID");
	strDownMode = (String)objHMInputParam.get("DOWNTIME");
	strAccNo = (String)objHMInputParam.get("ACCOUNT_NUMBER");
	//PRD_ACC_STMT_09DEC - End
	
	//ACC_STMT_CHANGES - Start
	try
	{
	LookupIntf objInfra = new LookupCom();
	queryList = objInfra.getQueryFromTable("ACC_STMT_SELECT_QRY");
	query1 = (String)queryList.get(1)==null || "".equals((String)queryList.get(1))?"":(String)queryList.get(1);
	query2 = (String)queryList.get(2)==null || "".equals((String)queryList.get(2))?"":(String)queryList.get(2);
	query3 = (String)queryList.get(3)==null || "".equals((String)queryList.get(3))?"":(String)queryList.get(3);
	query4 = (String)queryList.get(4)==null || "".equals((String)queryList.get(4))?"":(String)queryList.get(4);
	}
	catch(Exception e)
	{
	logger.error("Error in Query .. Please check the query framed in DB"+Utility.getPrintStackTrace(e));
	}
	query += "SELECT A.REQ_ID, A.ACC_NUMBER, A.TRAN_REF, TO_CHAR(A.TRAN_DATE, 'yyyy-mm-dd') TRAN_DATE, A.AMOUNT, A.TRAN_TYPE, A.TRAN_DESC, A.CURRENCY, " +
	"TO_CHAR(A.VALUE_DATE, 'yyyy-mm-dd') VALUE_DATE, A.RUNNING_BALANCE, A.ACC_COMMENTS, " +
	"B.MBOL_REF_NO, B.DEBIT_ACC, B.CUSTOMER_REF, B.PURP_PYMNT, A.TRAN_CODE " +////////////////////A.ACC_COMMENTS, 
	"FROM " +
	"ACC_STMT_TEMP_TXN A, " +
	"VM_PMT_ACCOUNT_STMNT_HELPER B " +
	"WHERE A.REQ_ID = '" +
	userNo +
	"' " +
	"AND A.TRAN_REF = B.core_ref_no(+) "+
	
	//query += sortBy + PAGINATE_FOOTER;
	
	logger.info("query1 :" +query1);
	logger.info("query2 :" +query2);
	logger.info("query3 :" +query3);
	logger.info("query4 :" +query4);
	
	connection = util.getConnection();
	ps = connection.prepareStatement(PAGINATE_HEADER+" "+query1+" "+query2+" "+query3+" "+query4+" "+sortBy+" "+PAGINATE_FOOTER);
	//PRD_ACC_STMT_09DEC - Start 
	ps.setString(1,strUsrNo);
	ps.setString(2,strAccNo);
	ps.setInt(3,toRow);
	ps.setInt(4,fromRow);
	//PRD_ACC_STMT_09DEC - End
	rs = ps.executeQuery();
	while(rs.next())
	{
	accAdditionalValue = new HashMap();
	accAdditionalValue.put("REQ_ID", rs.getString("REQ_ID"));
	accAdditionalValue.put("ACC_NUMBER", rs.getString("ACC_NUMBER"));
	
	// ACC_STMT_COLL_CHANGES starts
	// Only for csv download ADD_INFO_1 assigned to Bank ref field.
	if(strDownMode!=null && !"".equals(strDownMode) && "CSVGEN".equals(strDownMode))//PRD_ACC_STMT_09DEC
	{
	logger.info("Inside CSVDownMode :");
	
	if(rs.getString("ADD_INFO_1")!=null && !"".equals(rs.getString("ADD_INFO_1")) && !"null".equals(rs.getString("ADD_INFO_1")))
	accAdditionalValue.put("BANK_REF", rs.getString("ADD_INFO_1"));
	else if(rs.getString("PAYPLUS_REF_NO")!=null && !"".equals(rs.getString("PAYPLUS_REF_NO")) && !"null".equals(rs.getString("PAYPLUS_REF_NO")))
	accAdditionalValue.put("BANK_REF", rs.getString("PAYPLUS_REF_NO"));
	else if(rs.getString("MBOL_REF_NO")!=null && !"".equals(rs.getString("MBOL_REF_NO")) && !"null".equals(rs.getString("MBOL_REF_NO")))
	accAdditionalValue.put("BANK_REF", rs.getString("MBOL_REF_NO"));
	else
	accAdditionalValue.put("BANK_REF", rs.getString("TRAN_REF"));
	}
	else
	{
	accAdditionalValue.put("BANK_REF", rs.getString("TRAN_REF"));
	}
	// ACC_STMT_COLL_CHANGES ends
	
	if(rs.getString("PAYPLUS_REF_NO")!=null && !"".equals(rs.getString("PAYPLUS_REF_NO")) && !"null".equals(rs.getString("PAYPLUS_REF_NO")))
	accAdditionalValue.put("TRAN_REF", rs.getString("PAYPLUS_REF_NO"));
	else if(rs.getString("MBOL_REF_NO")!=null && !"".equals(rs.getString("MBOL_REF_NO")) && !"null".equals(rs.getString("MBOL_REF_NO")))
	accAdditionalValue.put("TRAN_REF", rs.getString("MBOL_REF_NO"));
	else
	accAdditionalValue.put("TRAN_REF", rs.getString("TRAN_REF"));
	
	accAdditionalValue.put("TRAN_DATE", rs.getString("TRAN_DATE"));
	accAdditionalValue.put("AMOUNT", rs.getString("AMOUNT"));
	accAdditionalValue.put("TRAN_TYPE", rs.getString("TRAN_TYPE"));
	accAdditionalValue.put("TRAN_DESC", checknull(rs.getString("TRAN_DESC")));
	accAdditionalValue.put("CURRENCY", rs.getString("CURRENCY"));
	accAdditionalValue.put("CUSTOMER_REF", checknull(rs.getString("CUSTOMER_REF")));
	
	// ACC_STMT_COLL_CHANGES starts
	// If CUSTOMER_REF is empty then ADD_INFO_2 assigned to CustomerRef
	if( rs.getString("CUSTOMER_REF")==null || "".equals(rs.getString("CUSTOMER_REF")) )
	accAdditionalValue.put("CUSTOMER_REF", checknull(rs.getString("ADD_INFO_2")));
	// ACC_STMT_COLL_CHANGES ends
	
	if("C".equals(rs.getString("TRAN_TYPE"))){
	accAdditionalValue.put("ACC_CREDIT_AMT", rs.getString("AMOUNT"));
	accAdditionalValue.put("ACC_DEBIT_AMT", "");
	}
	else{
	accAdditionalValue.put("ACC_DEBIT_AMT", rs.getString("AMOUNT"));
	accAdditionalValue.put("ACC_CREDIT_AMT", "");
	}
	String debitAmt = (String)accAdditionalValue.get("ACC_DEBIT_AMT");
	if(debitAmt!=null && !"".equals(debitAmt) && !"0".equals(debitAmt) && !"0.0".equals(debitAmt) && !"0.00".equals(debitAmt))
	accAdditionalValue.put("PURP_PYMNT", checknull(rs.getString("ACC_COMMENTS")) +"  "+checknull(rs.getString("PURP_PYMNT")));
	else
	accAdditionalValue.put("PURP_PYMNT", checknull(rs.getString("ACC_COMMENTS")));
	
	accAdditionalValue.put("VALUE_DATE", rs.getString("VALUE_DATE"));
	
	if("TRANS_REF".equals(sortDef)&&!"LT".equals(repType))//ACCOUNT_SUMM_LAST10	
	accAdditionalValue.put("ACC_RUNNING_BALANCE", rs.getString("RUNNING_BALANCE"));
	else
	accAdditionalValue.put("ACC_RUNNING_BALANCE", "");
	
	accAdditionalValue.put("TRAN_CODE", rs.getString("TRAN_CODE")); // FIX&CSV_ACCSTMT
	//ACC_STMT_CHANGES - End	
	
	mailList.add(accAdditionalValue);
	}
	logger.debug("mailList="+mailList);
	}catch(Exception e){   
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));
	}
	finally{
	try{
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null)
	  rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(ps!=null)
	  ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(connection!=null)
	  connection.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS
	}catch(Exception e){
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));	
	}
	}
	return mailList;
	}
	//UATR3_170 Starts
	
	// EPM_332 starts 
	public HashMap getBalanceMap(HashMap objHMInputParam)
	{
	logger.log(ENTRYLevel.ENTRY,"Inside getBalanceMap API");
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	HashMap balanceMap = null;
	String userNo = "";
	userNo = (String)objHMInputParam.get("SEESION_ID");
	logger.debug("userNo " +userNo);
	String query = "";
	try 
	{	
	query = "SELECT OPENING_BAL, CLOSING_BAL, SORT_BY, SORT_ORDER FROM ACC_STMT_TEMP_BAL WHERE REQ_ID = '"+userNo+"'";
	logger.debug("query " +query);
	Utility util = new Utility();
	connection = util.getConnection();
	ps = connection.prepareStatement(query);
	rs = ps.executeQuery();
	if(rs.next())
	{
	balanceMap = new HashMap();
	balanceMap.put("OpeningBalance", rs.getString("OPENING_BAL"));
	balanceMap.put("ClosingBalance", rs.getString("CLOSING_BAL"));
	balanceMap.put("sortBy", rs.getString("SORT_BY"));
	balanceMap.put("sortByOrder", rs.getString("SORT_ORDER"));
	}
	logger.debug("balanceMap="+balanceMap);
	}catch(Exception e){   
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));
	}
	finally{
	try{
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null)
	  rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(ps!=null)
	  ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(connection!=null)
	  connection.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS
	}catch(Exception e){
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));	
	}
	}
	return balanceMap;
	}
	// EPM_332 ends 
	
	public DataManager getDataConnection()  throws Exception {
      
	DataManager dataManager = null;
        String cmName = "[Utility]::getDataConnection";  
	try{            
	dataManager = new DataManager();
	}catch(Exception exception){
        throw new Exception(); 
	}	
	return dataManager;	
	}
	
  public int[] executeBatch (String[] sqlBatch,
  	     DataManager  dataManager) throws Exception{       
  	int batchCount=0;
  	        int[] result ;
  	        Statement staBatch = null;
  	        String sqlQuery=null;;
  	        boolean errorOccured=false;
  	        String cmName = "[Utility]::executeBatch";  
  	try{
  	            batchCount= sqlBatch.length;   
  	            //start the tranasaction by setting autocommit false       
  	            //dataManager.startTransaction();            
  	            staBatch = dataManager.getStatement();
  	            int i=0;
  	            for(i=0;i < batchCount; i++){
  	                sqlQuery=sqlBatch[i];
  	                //adding into batch
  	                if(sqlQuery!=null && !"".equals(sqlQuery))
  	                staBatch.addBatch(sqlQuery);
  	            }
  	            //execute batch
  	            if(i==batchCount)
  	               result = staBatch.executeBatch();
  	           else
  	               result = new int[0];
  	}        
  	        catch(java.sql.BatchUpdateException  batchException){
  	  errorOccured=true;
  	          throw new PwdUtilException("Batch ",
  	          batchException.getMessage());
  	}        
  	catch(SQLException  sqlException){
  	  errorOccured=true;            
  	          throw new PwdUtilException(cmName,
  	          sqlException.getMessage());
  	}
  	catch(Exception  exception){
  	          errorOccured=true;            
  	          throw new PwdUtilException(cmName,
  	          exception.getMessage());
  	}
  	finally{
  	try{
  	//closing statement and connection	
  	if(staBatch!=null)staBatch.close();
  	                //if error occured is true, the rollback the transaction                
  	                if(errorOccured==true){
  	                    
  	                    dataManager.rollBackTransaction();
  	                }
  	                    
  	}catch(Exception ex){
  	}
  	}
  	        
  	        return result;
  	        
  	}//END OF EXECUTE BATCH METHOD
  
  public void deleteSessionValues (String sessionId) throws Exception{       
	    String cmName = "[Utility]::deleteSessionValues";  
	    PreparedStatement ps = null;
	    Connection con =null;
	    String strQuery="";
	try{
	con = getConnection();
	strQuery = "DELETE FROM ACC_STMT_TEMP_TXN WHERE REQ_ID = ?";
	ps = con.prepareStatement(strQuery);
	ps.setString(1,sessionId);
	ps.executeUpdate();
	// FB_CODE_STAND STARTS
	//FORTIFY_CHG STARTS
	try
	{
	   if (ps != null)
	           ps.close(); 
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS	
	// FB_CODE_STAND ENDS
	// EPM_332 startsS
	ps = null;
	strQuery = "DELETE FROM ACC_STMT_TEMP_BAL WHERE REQ_ID = ?";
	ps = con.prepareStatement(strQuery);
	ps.setString(1,sessionId);
	ps.executeUpdate();
	// EPM_332 ends
	//CRITERIA_ENTL_CHECK - Starts
	try
	{
	   if (ps != null)
	           ps.close(); 
	}
	catch(Exception e)
	{
	
	}
	ps = null;
	strQuery = "DELETE FROM OD_TEMP_TXN_LIST WHERE OD_SESSION_ID = ?";
	ps = con.prepareStatement(strQuery);
	ps.setString(1,sessionId);
	ps.executeUpdate();
	//CRITERIA_ENTL_CHECK - Ends
	}        
	catch(Exception  exception){
	exception.printStackTrace();
	}
	finally{
	try{
	//FORTIFY_CHG STARTS
	try
	{
	   if(ps!=null)
	            	  ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{	
	               if(con!=null)
	            	  con.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS	
	             
	}catch(Exception ex){
	}
	}
 	}
	
	public String getTotalAmount(String sessionId, String creditDebit)   //STATIC_METHOD_CLEANUP
	{
	  String cmName="getTotalAmount";
	  logger.log(ENTRYLevel.ENTRY,"Inside getTotalAmount API");
	  String totalAmount = "";
	  String sql = null;
	  Connection conUser=null;
	  Utility util = new Utility();
	  PreparedStatement psUser=null;
	  ResultSet rsUser=null;
	  try{
	  conUser=util.getConnection();
	  sql = "SELECT SUM(AMOUNT) TOTAL FROM ACC_STMT_TEMP_TXN WHERE TRAN_TYPE = ? AND REQ_ID = ?";
	  logger.debug(cmName+"Query:::: "+sql);
	  psUser=conUser.prepareStatement(sql);
	  psUser.setString(1,creditDebit);
	  psUser.setString(2,sessionId);
	  rsUser= psUser.executeQuery();
	  if(rsUser.next())
	  totalAmount=(String)rsUser.getString("TOTAL");
	    }
	  catch(Exception e){
	logger.error(cmName+Utility.getPrintStackTrace(e));
	}
	  finally 
	{      
	try 
	  {
	//FORTIFY_CHG STARTS
	    try
	    {
	  if(rsUser!=null)
	 rsUser.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	  if(psUser!=null)
	 psUser.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	  if(conUser!=null)
	 conUser.close();
	    }
	catch(Exception e)
	{
	
	}
	  }
	  //FORTIFY_CHG ENDS	
	catch(Exception exp)
	{
	logger.error(cmName+ "General exception while getTotalAmount: " + Utility.getPrintStackTrace(exp));	
	}
	}  
	
	  logger.debug(cmName+"totalAmount:::: "+totalAmount);
	  return totalAmount;
	 }
	 Account Statement - Performance Fix - Ends 
	
	public String getCIFNameforcif(String cifNum)   //STATIC_METHOD_CLEANUP
	{
	  String cmName="getCIFNameforcif";
	  logger.log(ENTRYLevel.ENTRY,"Inside getCIFNameforcif API");
	  String cifName = "";
	  String sql = null;
	  Connection conUser=null;
	  Utility util = new Utility();
	  PreparedStatement psUser=null;
	  ResultSet rsUser=null;
	  try{
	  
	  conUser=util.getConnection();
	  if(cifNum!=null && !"".equals(cifNum)){
	  sql = " select od_cif_name from od_cif_details where od_cif= ? ";
	  logger.debug(cmName+"Query:::: "+sql);
	  psUser=conUser.prepareStatement(sql);
	  psUser.setString(1,cifNum);
	  rsUser= psUser.executeQuery();
	  if(rsUser.next())
	  cifName=(String)rsUser.getString("od_cif_name");
	  }
	    }
	  catch(Exception e){
	logger.error(cmName+Utility.getPrintStackTrace(e));
	}
	  finally 
	{      
	  try 
	{
	  //FORTIFY_CHG STARTS
	  try
	  {
	     if(rsUser!=null)
	    rsUser.close();
	  }
	  catch(Exception e)
	  {
	
	  }
	  try
	  {
	     if(psUser!=null)
	    psUser.close();
	  }
	  catch(Exception e)
	  {
	
	  }
	  try
	  {
	     if(conUser!=null)
	    conUser.close();
	  }
	  catch(Exception e)
	  {
	
	  }
	  //FORTIFY_CHG ENDS
	}
	catch(Exception exp)
	{
	logger.error(cmName+ "General exception while getting getUserRouting : " + Utility.getPrintStackTrace(exp));	
	}
	}  
	  logger.debug(cmName+"cifName:::: "+cifName);
	  return cifName;
	 }
	//UATR3_170 Ends
	*//** This method is used to get the profile country based on the account number **//*
	public String getProfileCountry(String accNo)   //STATIC_METHOD_CLEANUP
	{
	String cmName = "[InfraCom.getProfileCountry]";
	logger.log(ENTRYLevel.ENTRY," Entered into Method: ");
	String sql = "";
	String profileCountry = "";
	ResultSet rs = null;
	Connection con	= null;
	PreparedStatement ps = null;
	
	try
	{
	Utility utility = new Utility();
	con = utility.getConnection();
	sql="SELECT VUM.COUNTRY FROM VW_GLOBAL_ACCOUNT_MASTER VGAM,VW_UNIT_MASTER VUM WHERE VGAM.UNIT_ID=VUM.UNIT_ID AND VGAM.AC_NO=?";
	ps 	= con.prepareStatement(sql);
	ps.setString(1,accNo);
	rs	= ps.executeQuery();
	if(rs.next())
	{
	profileCountry = rs.getString(1);
	}
	logger.debug(cmName+"profileCountry for Account Number "+ accNo + " is : "+ profileCountry);

	}
	catch(SQLException ex) 
	{
	logger.error(cmName+"Exception while Executing the query ==>"+Utility.getPrintStackTrace(ex));
	}
	finally
	{
	try
	{
	   //FORTIFY_CHG STARTS
	   try
	   {
	  if(null != rs)
	 rs.close();
	   }
	   catch(Exception e)
	   {
	
	   }
	   try
	   {
	  if(null != ps)
	 ps.close();
	   }
	   catch(Exception e)
	   {
	
	   }
	   try
	   {
	  if(null != con)
	 con.close();
	   }
	   catch(Exception e)
	   {
	
	   }
	   //FORTIFY_CHG ENDS
	}
	catch(Exception e)
	{
	logger.error(cmName+"Exception while Closing the Connection ==>"+Utility.getPrintStackTrace(e));
	}
	}
	return profileCountry;
	}
//Onsite Issue_changes starts	
	public String getBranchCode(String accNo){   //STATIC_METHOD_CLEANUP
	
	String cmName="[Utility.getBranchCode]";
	
	logger.debug(cmName+" Entered into Method: ");
	
	Connection con        = null;
	
	PreparedStatement ps  = null;
	
	ResultSet rs          = null;
	
	String branchCode = "";
	
	Utility utility = new Utility();
	
	try{
	
	con =  utility.getConnection();;
	
	logger.debug(cmName+"accNo ... "+accNo);
	
	String statusSql = "select branch from vw_global_account_master where ac_no = ?";
	
	logger.debug(cmName+"strQuery to get branch Code-> "+statusSql);
	
	ps = con.prepareStatement(statusSql);
	
	ps.setString(1,accNo);
	
	rs = ps.executeQuery();
	
	while(rs.next()) 
	
	{
	
	branchCode  = rs.getString("BRANCH");	
	
	}
	
	logger.debug(cmName+"branchCode ... "+branchCode);	
	
	}catch(Exception ex){
	
	logger.error(Utility.getPrintStackTrace(ex));
	
	}finally
	
	{
	
	try{
	
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null) rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(ps!=null) ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(con!=null) con.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS
	}
	
	catch(Exception e)
	
	{
	
	logger.error(Utility.getPrintStackTrace(e));
	
	}	
	
	}
	
	return branchCode;
	
	}
//Onsite Issue_changes Ends

	//This method is used to convert the date with hours appended to personalize format
	public static String getHrsDateConverter(String dateValue,String format)
	{
	String delimiter="";
	String strdate="";
	String strHrs="";
	if(dateValue!=null)
	{
	  strdate = dateValue.substring(0,10);
	  strHrs = dateValue.substring(11,(dateValue.length()));
	  
	}
	    	if(!"".equals(strdate))
	{
	if(strdate.indexOf("/")!=-1)
	delimiter = "/";
	else if(strdate.indexOf(".")!=-1)
	delimiter = ".";
	else if(strdate.indexOf("-")!=-1)
	delimiter = "-";
	else if(strdate.indexOf("#")!=-1)
	delimiter = "#";
	
	String array[]=Utility.removeValue(delimiter,strdate);
	String returnDate = Utility.checkDateFormat(array,format)+" "+strHrs;
	return returnDate;
	}
	return dateValue;
	}
	//EPMS_133 - Start
	*//**
	 * Used to get CIF details,
	 * @param content -  Contains response value
	 * @return ArrayList - CIF list
	 *//*
	public ArrayList constructCIFList(String content)
  {
	String cmName="[Utility.constructCIFList()]";
	logger.log(ENTRYLevel.ENTRY," Entered into Method... ");
	String strData="";
	ArrayList cifList = new ArrayList();
	try
	{
	String key="";
	String value="";
	String strReturn = "";
	logger.debug(cmName+" Input Staring : " + content);
	int startPos=content.indexOf("^^");
	    if (startPos!=-1)
	    {
	   strData = content.substring(0,startPos);
	   logger.debug(cmName+" After tokenize  : " + strData);
	StringTokenizer recordlist = new StringTokenizer(strData,"~*");
	while(recordlist.hasMoreTokens())
	{
	strData=recordlist.nextToken();	 
	if(strData.contains("="))
	{
	key = strData.substring(0,strData.indexOf("=")).trim();
	value = strData.substring(strData.indexOf("=")+1,strData.length());	
	if("CIFId".equalsIgnoreCase(key.trim()))
	{
	strReturn = strReturn+"'"+value+"',";
	}
	}	
	}
	if(!"".equalsIgnoreCase(value))
	{
	strReturn = strReturn.substring(0,strReturn.length()-1);
	logger.debug(cmName+" Result String of CIF : " + strReturn);
	cifList = getCIFDetails(strReturn);
	}
	   }
	   logger.debug("Result lit for CIF : " + cifList);
	}
	catch(Exception e)
	{
	   //e.printStackTrace();//FORTIFY_CHG
	}
	return cifList;
	}
	*//**
	 * Used to get CIF details,
	 * @param content -  Contains CIF ids
	 * @return ArrayList - CIF list
	 *//*
	public ArrayList getCIFDetails(String strCIF)throws Exception
	{
	String cmName="[Utility.getCIFDetails()]";
	logger.log(ENTRYLevel.ENTRY," Entered into Method... ");
	ArrayList cifList = null;
	Statement stmt=null;
	ResultSet rs = null;
	Connection conn=null;
	HashMap objHMCIF = null; 
	try
	{
	cifList = new ArrayList();
	String query = "SELECT CIF,CUSTOMER_NAME FROM VW_GLOBAL_CUSTOMER_MASTER WHERE CIF IN ("+strCIF+")";
	logger.debug(cmName+" Query : " + query);
	conn = getConnection();
	stmt = conn.createStatement();
	rs = stmt.executeQuery(query);
	while(rs.next())
	{
	objHMCIF = new HashMap();
	objHMCIF.put("CIF",rs.getString("CIF"));
	objHMCIF.put("CUSTOMER_NAME",rs.getString("CUSTOMER_NAME"));
	cifList.add(objHMCIF);
	}
	}
	catch(SQLException ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));//FB_CODE_STAND	
	}
	catch(Exception e)
	{
	logger.error(Utility.getPrintStackTrace(e));//FB_CODE_STAND
	}
	finally
	{
	try
	{
	//FORTIFY_CHG STARTS
	try
	{
	   if(rs!=null)
	  rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(stmt!=null)
	  stmt.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	   if(conn!=null)
	  conn.close();
	}
	catch(Exception e)
	{
	
	}
	//FORTIFY_CHG ENDS
	}
	//catch(SQLException sqe)
	//{
	//logger.error(Utility.getPrintStackTrace(sqe));//FB_CODE_STAND
	//}
	//FORTIFY_CHG ENDS
	catch(Exception exc)
	{
	logger.error(Utility.getPrintStackTrace(exc));//FB_CODE_STAND
	}
	
	}
	logger.debug(cmName+" Return CIF List : "+ cifList);
	return cifList;
	}
	//EPMS_133 - Start
	
	//SCH_PERF_CHGS - Starts
	
	public void updateScheAuditMaster(String accountNo,String resStatus,String scheStatus)//Alerts Changes
	{
	String cmName="[Utility.updateScheAuditMaster]";
      Connection connection = null;
	HashMap retMap = null;
	PreparedStatement pstmt = null;
      ResultSet rset = null;
      Utility util=null;
      int resultCount = 0;
	String query = "UPDATE SCHE_AUDIT_MASTER SET STATUS = ?,SCHE_STATUS = ? WHERE ACCOUNT_NUMBER = ?  ";
	try{
	util = new Utility();
	connection = util.getConnection();
	pstmt = connection.prepareStatement(query);
	pstmt.setString(1,resStatus);
	pstmt.setString(2,scheStatus);
	pstmt.setString(3,accountNo);
	resultCount = pstmt.executeUpdate();
	}catch(Exception ex){
	logger.error(Utility.getPrintStackTrace(ex));
	}
	finally
	{
	//FORTIFY_CHG STARTS
	//try{
	
	try
	{
	if(rset!=null) rset.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	if(pstmt!=null) pstmt.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	if(connection!=null) connection.close();
	}
	catch(Exception e)
	{
	
	}
	
	//}
	//catch(SQLException sqe){
	//logger.error(Utility.getPrintStackTrace(sqe));
	//}
	//FORTIFY_CHG ENDS
	}
	
	}	
	
	
	public boolean generateEmptyFile(String msg,String path){
	String cmName = "ReportGenerator:generateMT940Document";
	FileWriter fstream = null;
      BufferedWriter out = null;	
	boolean status = false;
	try{	
	fstream = new FileWriter(path);
          out = new BufferedWriter(fstream);           
          status = true;            
          out.write(msg);
	out.close();
	}catch(Exception e){
	status = false;
	logger.error(Utility.getPrintStackTrace(e));
	}finally{
	try{
	if(out != null) out.close();
	}catch(Exception e){
	logger.error(Utility.getPrintStackTrace(e));
	}
	}
	return status;
	}
//EPMS_416 starts	
	public static ArrayList getParseDetails(String resultString)
	{
	logger.log(ENTRYLevel.ENTRY,"Entered into getParseDetails");

	ArrayList resultList=new ArrayList();
	HashMap resultMap=new HashMap();
	logger.debug("String value to be parsed: "+resultString);
	StringTokenizer strMainToken = new StringTokenizer(resultString, JSPIOConstants.INTEG_DELIMITERS);
	//FB_CODE_STAND starts
	String tokens = null;
	String key=null;
	String value=null;
	StringTokenizer strSubToken=null;
	//FB_CODE_STAND ends
	String strArrayRes[]=resultString.split("\\~\\*");
	int strLen=strArrayRes.length;
	int i=0;
	if(strLen>0)
	{
	while(i<strLen)
	{
	tokens=(String)strArrayRes[i];
	if(tokens.endsWith("="))
	{
	tokens=tokens+"null";
	}
	strSubToken = new StringTokenizer(tokens,JSPIOConstants.EQULAS_SYM);//FB_CODE_STAND
	while (strSubToken.hasMoreTokens()) 
	{
	//FB_CODE_STAND starts
	key=strSubToken.nextToken();
	value=strSubToken.nextToken();
	//FB_CODE_STAND ends
	if(resultMap.containsKey(key))
	{
	resultList.add(resultMap);
	resultMap=new HashMap();
	resultMap.put(key,value);
	}
	else
	{
	resultMap.put(key, value);
	}
	}
	i++;
	}
	
	}
	resultList.add(resultMap);
	logger.debug("After Parser result list value: "+resultList);
	logger.log(EXITLevel.EXIT,"Exited from getParsingDetails");
	return resultList;
	}
//EPMS_416 ends	
	// SCH_PERF_CHGS - Ends
	
	// ISG_FIX starts
	public boolean accessCheck(String gcif, String userNo , String fileName,String module) {
	
	PreparedStatement ps = null;	
	ResultSet rs = null;
	Connection con = null;
	    String cmName="MailData.accessCheck";	
	String subProd="";
	    boolean mailCheck=false; 
	logger.debug(cmName+" Entered into accessCheck Method: ");
	String strQuery=null;
	try
	{	
	logger.debug(cmName+"The File Name:==>"+fileName+"User Number :==>"+userNo+"GCIF==>"+gcif+"module>>"+module);
	DataManager dataManager = new DataManager();
	con = dataManager.getConnection();	
	if(module!=null && "MAIL".equals(module))
	strQuery="select OD_MAIL_SUBJECT from OD_MAIL_REPOSITORY  WHERE OD_MAIL_SUBJECT LIKE '%"+fileName+"%'  AND OD_TO_USER_NO = '"+userNo+"' AND OD_TO_GCIF_NO = '"+gcif+"'";
	else if(module!=null &&"SCHEDULE".equals(module))
	strQuery="select FILE_NAME from ACCOUNT_STATEMENT_REPORT_DET  WHERE FILE_NAME LIKE '%"+fileName+"%'   AND GCIF = '"+gcif+"'";
	logger.debug(cmName+":strQuery==>"+strQuery);
	ps = con.prepareStatement(strQuery);
	rs = ps.executeQuery();
	if(rs.next())
	{
	mailCheck=true;
	}
	logger.debug(cmName+":File Acces check returns =====>"+mailCheck);
	}
	catch(Exception exception)
	{
	logger.error(Utility.getPrintStackTrace(exception));	  
	}                      
	finally
	{
	try
	{
	    if(rs!=null)
	    	try{	rs.close();}catch(Exception e){}
	     if(ps!=null)
	    	 try{ps.close();}catch(Exception e){}
	if(con!=null)
	try{con.close();}catch(Exception e){}
	}
	catch(Exception ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));
	}
	}
	return mailCheck;
	
	}
//CODE_SCAN starts
	
	 * Inserts the list of refnumbers in to the temporary table
	 * @param	alRef 	List of Reference numbers
	 * @param	gcif 	GCIF
	 * @param	module 	Module Name
	 * @param	sessonId 	Session Id
	 * 	 
	 
public void insertIntoTxnList(ArrayList alRef, String gcif , String usrNo,String module,String sessonId) {
	
	PreparedStatement ps = null;	
	ResultSet rs = null;
	Connection con = null;
	    String cmName="Utility.insertIntoTxnList";	
	String subProd="";
	    boolean mailCheck=false; 
	logger.debug(cmName+" Entered into getFunctionCode Method: ");
	String strQuery=null;
	try
	{
	con=getConnection();
	initializeModuleAccess(sessonId,usrNo,con);
	strQuery="insert into OD_TEMP_TXN_LIST(OD_SESSION_ID,OD_GCIF,OD_USER_NO,OD_MODULE,OD_REF_NO)VALUES(?,?,?,?,?)"; 
	ps=con.prepareStatement(strQuery);
	for(int i=0;i<alRef.size();i++)
	{
	ps.setString(1, sessonId);
	ps.setString(2, gcif);
	ps.setString(3, usrNo);
	ps.setString(4, module);
	ps.setString(5, (String)alRef.get(i));
	ps.addBatch();
	}
	ps.executeBatch();
	
	}
	catch(Exception exception)
	{
	logger.error(Utility.getPrintStackTrace(exception));	  
	}                      
	finally
	{
	try
	{
	    if(rs!=null)
	    	try{	rs.close();}catch(Exception e){}
	     if(ps!=null)
	    	 try{ps.close();}catch(Exception e){}
	if(con!=null)
	try{con.close();}catch(Exception e){}
	}
	catch(Exception ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));
	}
	}
	
	
	}

* Delete  the records in temporary table based on session id and user number
* @param	sessonId	Session id 	
* @param	usrNo	User number 
* @param	con	Connection
* 	 

	public void initializeModuleAccess(String sessonId, String usrNo,Connection con) {
	
	PreparedStatement ps = null;	
	ResultSet rs = null;
	    String cmName="Utility.initializeModuleAccess";	
	String subProd="";
	    boolean mailCheck=false; 
	logger.debug(cmName+" Entered into initializeModuleAccess Method: ");
	String strQuery=null;
	try
	{
	strQuery="DELETE FROM OD_TEMP_TXN_LIST WHERE OD_SESSION_ID = ? AND OD_USER_NO = ? "; 
	ps=con.prepareStatement(strQuery);
	ps.setString(1, sessonId);
	ps.setString(2, usrNo);
	ps.executeQuery();
	
	}
	catch(Exception exception)
	{
	logger.error(Utility.getPrintStackTrace(exception));	  
	}                      
	finally
	{
	try
	{
	    if(rs!=null)
	    	try{	rs.close();}catch(Exception e){}
	     if(ps!=null)
	    	 try{ps.close();}catch(Exception e){}
	}
	catch(Exception ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));
	}
	}
	}
	
	 * Check the particular record was present or not if record was there it will return true or false
	 * @param	alRef	List of reference numbers	 	
	 * @param	usrNo	 	User number
	 * @param	sessonId	session id
	 * 	 
	 	
public boolean accessCheck(ArrayList alRef, String usrNo,String module,String sessonId) {
	
	PreparedStatement ps = null;	
	ResultSet rs = null;
	Connection con = null;
	    String cmName="Utility.accessCheck";	
	String subProd="";
	    boolean accessCheck=false; 
	logger.debug(cmName+" Entered into accessCheck Method: ");
	String strQuery=null;
	StringBuffer strBuffTmp=new StringBuffer();
	int iCount=0;
	try
	{
	con=getConnection();
	for(int i=0; i<alRef.size(); i++){

	if(i==0)
	strBuffTmp.append("'"+(String)alRef.get(i));
	else
	strBuffTmp.append("','"+(String)alRef.get(i));
	}

	strBuffTmp.append("'");
	logger.debug(cmName+" List Of Ref NO "+strBuffTmp.toString());
	strQuery="SELECT COUNT(*) FROM OD_TEMP_TXN_LIST WHERE OD_SESSION_ID = ? and OD_USER_NO = ? and OD_MODULE = ? AND OD_REF_NO IN ("+strBuffTmp.toString()+")"; 
	ps=con.prepareStatement(strQuery);
	
	ps.setString(1, sessonId);
	ps.setString(2, usrNo);
	ps.setString(3, module);
	rs=ps.executeQuery();
	if(rs.next())
	iCount = rs.getInt(1);
	
	if(iCount==alRef.size())
	accessCheck= true;
	else
	accessCheck= false;
	}
	catch(Exception exception)
	{
	logger.error(Utility.getPrintStackTrace(exception));	  
	}                      
	finally
	{
	try
	{
	    if(rs!=null)
	    	try{	rs.close();}catch(Exception e){}
	     if(ps!=null)
	    	 try{ps.close();}catch(Exception e){}
	if(con!=null)
	try{con.close();}catch(Exception e){}
	}
	catch(Exception ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));
	}
	}
	return accessCheck;
	}
//CODE_SCAN ends
	

	//CODE_SCAN starts
	
	 * Check the particular record was present or not if record was there it will return true or false
	 * @param	accountNo	Selected AccountNo	 	
	 * @param	usrNo	 	userNo from  session
	 * @param	gcif	gcif from session
	 * 	@param	function	Function from session
	 * 	 
	 	
	public boolean getAccessCheckCanRepServlet(String accNo,String userNo,String gcif,String functionCode) {
	
	PreparedStatement ps = null;	
	ResultSet rs = null;
	Connection con = null;
	    String cmName="MailData.getAccessCheckCanRepServlet";	
	String subProd="";
	    boolean accessCheck=false; 
	logger.debug(cmName+" Entered into getAccessCheckCanRepServlet Method: ");
	String strQuery=null;
	try
	{	
	logger.debug(cmName+"The AccNo ==>"+accNo+"User Number :==>"+userNo+"GCIF==>"+gcif);
	DataManager dataManager = new DataManager();
	con = dataManager.getConnection();	
	strQuery="select * from OD_USER_FUNCTION_MB  WHERE OD_ACC_NO = ? AND  OD_USER_NO = ?  AND OD_GCIF = ? AND OD_FUNCTION_CODE = ? ";
	logger.debug(cmName+":strQuery==>"+strQuery);
	ps = con.prepareStatement(strQuery);
	ps.setString(1, accNo);
	ps.setString(2, userNo);
	ps.setString(3, gcif);
	ps.setString(4, functionCode);
	rs = ps.executeQuery();	
	if(rs.next())
	{
	accessCheck=true;
	}
	logger.debug(cmName+":getAccessCheckCanRepServlet check returns =====>"+accessCheck);
	}
	catch(Exception exception)
	{
	logger.error(Utility.getPrintStackTrace(exception));	  
	}                      
	finally
	{
	try
	{
	    if(rs!=null)
	    	try{	rs.close();}catch(Exception e){}
	     if(ps!=null)
	    	 try{ps.close();}catch(Exception e){}
	if(con!=null)
	try{con.close();}catch(Exception e){}
	}
	catch(Exception ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));
	}
	}
	return accessCheck;
	
	}
	
	
	 * Check the particular record was present or not if record was there it will return true or false	 	
	 * @param	usrNo	 	ParseRule from  request
	 * @param	gcif	gcif from session	
	 * 	 
	 	
	public boolean getAccessParseRuleCheck(String gcif,String parsedRule) {
	
	PreparedStatement ps = null;	
	ResultSet rs = null;
	Connection con = null;
	    String cmName="MailData.getAccessParseRuleCheck";	
	String subProd="";
	    boolean accessCheck=false; 
	logger.debug(cmName+" Entered into getAccessParseRuleCheck Method: ");
	String strQuery=null;
	try
	{	
	logger.debug(cmName+"gcif ==>"+gcif+"parsedRule :==>"+parsedRule);
	DataManager dataManager = new DataManager();
	con = dataManager.getConnection();	
	strQuery="SELECT OD_RULE_PARSE_ID FROM OD_RULES_MB A,OD_RULES_DEF_MB B WHERE A.OD_RULE_ID = B.Od_RULE_ID AND A.OD_GCIF= ? AND B.OD_RULE_PARSE_ID= ? ";
	logger.debug(cmName+":strQuery==>"+strQuery);
	ps = con.prepareStatement(strQuery);
	ps.setString(1, gcif);
	ps.setString(2, parsedRule);	
	rs = ps.executeQuery();	
	if(rs.next())
	{
	accessCheck=true;
	}
	logger.debug(cmName+":Acces check returns =====>"+accessCheck);
	}
	catch(Exception exception)
	{
	logger.error(Utility.getPrintStackTrace(exception));	  
	}                      
	finally
	{
	try
	{
	    if(rs!=null)
	    	try{	rs.close();}catch(Exception e){}
	     if(ps!=null)
	    	 try{ps.close();}catch(Exception e){}
	if(con!=null)
	try{con.close();}catch(Exception e){}
	}
	catch(Exception ex)
	{
	logger.error(Utility.getPrintStackTrace(ex));
	}
	}
	return accessCheck;
	
	}
	//CODE_SCAN ends


	// CODE_SCAN starts
	*//**
	 * Used to validate the invalid characters and file extension
	 * @param content -  FilenName and FileForm
	 * @return Boolean - Return true or false
	 *//*
	public boolean getFileNameCheck(String fileName,String fileFrom) {
	boolean fileNameCheck=false; 
	char invChars[] = null;
	try{
	String invalidString=OrbiProperties.getProperty("FILE_INVALID_CHARS");
	String acceptedFormats=OrbiProperties.getProperty("ACCEPT_FILE_FORMATS");
	invChars = invalidString.toCharArray();
	int falg = 0;
	
	if(!"".equals(invalidString))
	{
	
	for(int j=0;j<invChars.length;j++)
	{	
	if(fileName.indexOf(invChars[j])!=-1 )
	{
	falg =  1;
	
	}
	
	}
	}	
	
	//logger.debug(":falg for returns =====>"+falg);
	if(!"ESCDWRP".equals(fileFrom) && (falg!=1))
	{
	int i = 0;
	StringTokenizer acceptFormat = new StringTokenizer(acceptedFormats, ",");	
	while (acceptFormat.hasMoreElements()) {
	String validFileFormat = acceptFormat.nextElement().toString();	
	//logger.debug(":validFileFormat =====>"+validFileFormat);
	if((fileName.toUpperCase()).indexOf(validFileFormat)!=-1){
	i++;
	}
	}
	
	if(i>0)
	falg = 0;
	else
	falg = 1;
	}	
	
	//logger.debug(":falg returns =====>"+falg);
	
	if(falg==1)
	fileNameCheck = false;
	else
	fileNameCheck = true;
	}catch(Exception e){logger.error("Caught Exception in getFileNameCheck method : "+Utility.getPrintStackTrace(e));}
	
	logger.debug(":fileNameCheck check returns =====>"+fileNameCheck);
	
	return fileNameCheck;
	}
	// CODE_SCAN ends
	
//CHG_DU_CR_4 starts	
	public ArrayList getCustAccStatementDetails(int fromRow, int toRow, String sortBy, String sortByOrder,HashMap objHMInputParam) // ACC_STMT_COLL_CHANGES//PRD_ACC_STMT_09DEC
	{
	
	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	HashMap accAdditionalValue = null;
	HashMap mainMap = new HashMap();
	ArrayList mailList = new ArrayList();
	logger.debug("fromRow$$$$" +fromRow);
	logger.debug("toRow$$$$" +toRow);
	String query = "";
	ArrayList queryList = null;
	String query1 = null;
	String query2 = null;
	String query3 = null;
	String query4 = null;
	String sortDef = "";
	logger.log(ENTRYLevel.ENTRY,"Inside getAccStatementAdditionalDetails API");
	String strUsrNo = "";
	String strDownMode = "";
	String strAccNo = ""; 
	String repType=(String)objHMInputParam.get("REP_TYPE");//ACCOUNT_SUMM_LAST10
	if(sortBy == null || "".equals(sortBy) || "null".equals(sortBy))
	{
	sortBy = "TRANS_REF";
	sortDef = "TRANS_REF";
	}
	if("DES".equals(sortByOrder) || "".equals(sortByOrder) || sortByOrder==null)
	sortByOrder = "DESC";
	Utility util = new Utility();
	if("TRANS_REF".equals(sortBy))
	sortBy = "ORDER BY A.TXN_SEQUENCE ASC ";
	else if("TRAN_DATE".equals(sortBy))
	sortBy = "ORDER BY A.TRAN_DATE "+sortByOrder;
	else if("VALUE_DATE".equals(sortBy))
	sortBy = "ORDER BY A.VALUE_DATE "+sortByOrder;	
	else if("DEB_AMT".equals(sortBy))
	sortBy = "ORDER BY A.TRAN_TYPE DESC, A.AMOUNT "+sortByOrder;
	else if("CRED_AMT".equals(sortBy))
	sortBy = "ORDER BY A.TRAN_TYPE ASC, A.AMOUNT "+sortByOrder;
	try 
	{	
	strUsrNo = (String)objHMInputParam.get("SEESION_ID");
	strDownMode = (String)objHMInputParam.get("DOWNTIME");
	strAccNo = (String)objHMInputParam.get("ACCOUNT_NUMBER");
	try
	{
	LookupIntf objInfra = new LookupCom();
	queryList = objInfra.getQueryFromTable("CUS_ACC_STMT_SEL_QRY");
	query1 = (String)queryList.get(1)==null || "".equals((String)queryList.get(1))?"":(String)queryList.get(1);
	query2 = (String)queryList.get(2)==null || "".equals((String)queryList.get(2))?"":(String)queryList.get(2);
	query3 = (String)queryList.get(3)==null || "".equals((String)queryList.get(3))?"":(String)queryList.get(3);
	query4 = (String)queryList.get(4)==null || "".equals((String)queryList.get(4))?"":(String)queryList.get(4);
	}
	catch(Exception e)
	{
	logger.error("Error in Query .. Please check the query framed in DB"+Utility.getPrintStackTrace(e));
	}
	
	logger.info("query1 :" +query1);
	logger.info("query2 :" +query2);
	logger.info("query3 :" +query3);
	logger.info("query4 :" +query4);
	
	connection = util.getConnection();
	ps = connection.prepareStatement(PAGINATE_HEADER+" "+query1+" "+query2+" "+query3+" "+query4+" "+sortBy+" "+PAGINATE_FOOTER);
	//PRD_ACC_STMT_09DEC - Start 
	ps.setString(1,strUsrNo);
	ps.setString(2,strAccNo);
	ps.setInt(3,toRow);
	ps.setInt(4,fromRow);
	//PRD_ACC_STMT_09DEC - End
	rs = ps.executeQuery();
	while(rs.next())
	{
	accAdditionalValue = new HashMap();
	accAdditionalValue.put("REQ_ID", rs.getString("REQ_ID"));
	accAdditionalValue.put("ACC_NUMBER", rs.getString("ACC_NUMBER"));
	
	// ACC_STMT_COLL_CHANGES starts
	// Only for csv download ADD_INFO_1 assigned to Bank ref field.
	if(strDownMode!=null && !"".equals(strDownMode) && "CSVGEN".equals(strDownMode))//PRD_ACC_STMT_09DEC
	{
	logger.info("Inside CSVDownMode :");
	
	if(rs.getString("ADD_INFO_1")!=null && !"".equals(rs.getString("ADD_INFO_1")) && !"null".equals(rs.getString("ADD_INFO_1")))
	accAdditionalValue.put("BANK_REF", rs.getString("ADD_INFO_1"));
	else if(rs.getString("PAYPLUS_REF_NO")!=null && !"".equals(rs.getString("PAYPLUS_REF_NO")) && !"null".equals(rs.getString("PAYPLUS_REF_NO")))
	accAdditionalValue.put("BANK_REF", rs.getString("PAYPLUS_REF_NO"));
	else if(rs.getString("MBOL_REF_NO")!=null && !"".equals(rs.getString("MBOL_REF_NO")) && !"null".equals(rs.getString("MBOL_REF_NO")))
	accAdditionalValue.put("BANK_REF", rs.getString("MBOL_REF_NO"));
	else
	accAdditionalValue.put("BANK_REF", rs.getString("TRAN_REF"));
	}
	else
	{
	accAdditionalValue.put("BANK_REF", rs.getString("TRAN_REF"));
	}
	// ACC_STMT_COLL_CHANGES ends
	
	if(rs.getString("PAYPLUS_REF_NO")!=null && !"".equals(rs.getString("PAYPLUS_REF_NO")) && !"null".equals(rs.getString("PAYPLUS_REF_NO")))
	accAdditionalValue.put("TRAN_REF", rs.getString("PAYPLUS_REF_NO"));
	else if(rs.getString("MBOL_REF_NO")!=null && !"".equals(rs.getString("MBOL_REF_NO")) && !"null".equals(rs.getString("MBOL_REF_NO")))
	accAdditionalValue.put("TRAN_REF", rs.getString("MBOL_REF_NO"));
	else
	accAdditionalValue.put("TRAN_REF", rs.getString("TRAN_REF"));
	
	accAdditionalValue.put("TRAN_DATE", rs.getString("TRAN_DATE"));
	accAdditionalValue.put("AMOUNT", rs.getString("AMOUNT"));
	accAdditionalValue.put("TRAN_TYPE", rs.getString("TRAN_TYPE"));
	accAdditionalValue.put("TRAN_DESC", checknull(rs.getString("TRAN_DESC")));
	accAdditionalValue.put("CURRENCY", rs.getString("CURRENCY"));
	accAdditionalValue.put("CUSTOMER_REF", checknull(rs.getString("CUSTOMER_REF")));
	
	// ACC_STMT_COLL_CHANGES starts
	// If CUSTOMER_REF is empty then ADD_INFO_2 assigned to CustomerRef
	if( rs.getString("CUSTOMER_REF")==null || "".equals(rs.getString("CUSTOMER_REF")) )
	accAdditionalValue.put("CUSTOMER_REF", checknull(rs.getString("ADD_INFO_2")));
	// ACC_STMT_COLL_CHANGES ends
	
	if("C".equals(rs.getString("TRAN_TYPE"))){
	accAdditionalValue.put("ACC_CREDIT_AMT", rs.getString("AMOUNT"));
	accAdditionalValue.put("ACC_DEBIT_AMT", "");
	}
	else{
	accAdditionalValue.put("ACC_DEBIT_AMT", rs.getString("AMOUNT"));
	accAdditionalValue.put("ACC_CREDIT_AMT", "");
	}
	String debitAmt = (String)accAdditionalValue.get("ACC_DEBIT_AMT");
	if(debitAmt!=null && !"".equals(debitAmt) && !"0".equals(debitAmt) && !"0.0".equals(debitAmt) && !"0.00".equals(debitAmt))
	accAdditionalValue.put("PURP_PYMNT", checknull(rs.getString("ACC_COMMENTS")) +"  "+checknull(rs.getString("PURP_PYMNT")));
	else
	accAdditionalValue.put("PURP_PYMNT", checknull(rs.getString("ACC_COMMENTS")));
	
	accAdditionalValue.put("VALUE_DATE", rs.getString("VALUE_DATE"));
	
	if("TRANS_REF".equals(sortDef)&&!"LT".equals(repType))//ACCOUNT_SUMM_LAST10	
	accAdditionalValue.put("ACC_RUNNING_BALANCE", rs.getString("RUNNING_BALANCE"));
	else
	accAdditionalValue.put("ACC_RUNNING_BALANCE", "");
	
	accAdditionalValue.put("TRAN_CODE", rs.getString("TRAN_CODE")); // FIX&CSV_ACCSTMT
	accAdditionalValue.put("BENE_NAME", rs.getString("BENE_NAME"));
	//ACC_STMT_CHANGES - End	
	
	mailList.add(accAdditionalValue);
	}
	logger.debug("mailList="+mailList);
	}catch(Exception e){   
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));
	}
	finally{
	try{
	    try
	    {
	  if(rs!=null)
	 rs.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	  if(ps!=null)
	  ps.close();
	}
	catch(Exception e)
	{
	
	}
	try
	{
	  if(connection!=null)
	  connection.close();
	    }
	catch(Exception e)
	{
	
	}
	
	}catch(Exception e){
	logger.error("Caught Exception in getUserDetails method : "+Utility.getPrintStackTrace(e));	
	}
	}
	return mailList;
	}
//CHG_DU_CR_4 	ends
	private static Logger logger=Logger.getLogger(Utility.class);
}

*/