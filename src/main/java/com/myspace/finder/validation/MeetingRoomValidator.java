package com.myspace.finder.validation;

import java.util.Date;

import com.myspace.finder.constant.MySpaceRoomFinderConstant;
import com.myspace.finder.utility.MeetingRoomUtility;

public class MeetingRoomValidator {
	MeetingRoomUtility meetingRoomUtility = new MeetingRoomUtility();
	
	public boolean validateDateTime(String startDate, String endDate)
	{
	   	Date startDt = meetingRoomUtility.convertDateTime(startDate);
	   	boolean result = false;
   		if ( startDt != null &&  meetingRoomUtility.multipleOfMeetingInterval(startDt.getMinutes()) ) {
   			Date endDt = meetingRoomUtility.convertDateTime(endDate);
   			if ( endDt != null &&  meetingRoomUtility.multipleOfMeetingInterval(endDt.getMinutes()) ) {
   				// verify if end date time is greater than start date time
   				if ( meetingRoomUtility.compareDate(startDate, endDate) ) {
   					result = true;
   				}
   			}
   			
   		}
   		return result;
	}
	
	
		
	  public int validateInputForNewRecords(String startDate, String endDate, int person)
	  {
		  int result = -1;
		  if ( person >= MySpaceRoomFinderConstant.MIN_NUMBER_OF_PERSON && person <= MySpaceRoomFinderConstant.MAX_NUMBER_OF_PERSON)
		  {
			   if ( validateDateTime(startDate, endDate) )
			   {
				   result = 1;
			   }
			   else
				   result = 0;
		  }
		  
		  return  result;
	  }
	  
	  public boolean validateInputForViewRecords(String startDate, String endDate)
	  {
		  boolean result = false;
			if (validateDateTime(startDate, endDate)) {
				result = true;
			}
		  return result;
	  }
	  
}
