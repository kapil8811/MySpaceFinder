package com.myspace.finder.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

import com.myspace.finder.constant.MySpaceRoomFinderConstant;

public class MeetingRoomUtility {

	public Date convertDateTime(String startDate) {
		Date dateTime = null;
		try {
			dateTime = new SimpleDateFormat(MySpaceRoomFinderConstant.DATE_TIME_PATTERN, Locale.ENGLISH)
					.parse(startDate);

		} catch (DateTimeParseException de) {
			System.out.println("Exception occured while date time parsing:" + de.getMessage());
			dateTime = null;
		} catch (Exception e) {
			System.out.println("Exception occured while date time conversion:" + e.getMessage());
			dateTime = null;
		}
		return dateTime;
	}

	public boolean multipleOfMeetingInterval(int minutes) {
		boolean result = true;
		if (minutes != 0) {
			if (minutes % MySpaceRoomFinderConstant.TIME_INTERVAL_IN_MINUTE != 0)
				result = false;
		}

		return result;
	}
	
	public boolean bufferSlots( int startSlot, int endSlot ) {
		boolean result = false;
		if ( startSlot < MySpaceRoomFinderConstant.MORNING_9AM_SLOT_END &&  endSlot > MySpaceRoomFinderConstant.MORNING_9AM_SLOT_BEGIN ) {
			result = true;
		}
		if ( startSlot < MySpaceRoomFinderConstant.NOON_1PM_SLOT_END &&  endSlot > MySpaceRoomFinderConstant.NOON_1PM_SLOT_BEGIN) {
			result = true;
		}
		if ( startSlot < MySpaceRoomFinderConstant.EVE_645_SLOT_END &&  endSlot > MySpaceRoomFinderConstant.EVE_645_SLOT_BEGIN) {
			result = true;
		}
		return result;
	}

	public boolean compareDate(String startDt, String endDt) {
		boolean result = false;
		// compare with end date
		try {
			Date start = new SimpleDateFormat(MySpaceRoomFinderConstant.DATE_TIME_PATTERN, Locale.ENGLISH)
					.parse(startDt);
			Date end = new SimpleDateFormat(MySpaceRoomFinderConstant.DATE_TIME_PATTERN, Locale.ENGLISH).parse(endDt);

			if (start.compareTo(end) < 0) {
				result = true;
			} 
		} catch (ParseException e) {
			System.out.println("unable to compare dates, Parsing execption occured: " + e.getMessage());
		}
		return result;
	}

	public int getSlot(String dateTime) {

		int slot = -1;
		// compare with end date
		try {
			Date simpledateTime = new SimpleDateFormat(MySpaceRoomFinderConstant.DATE_TIME_PATTERN, Locale.ENGLISH)
					.parse(dateTime);
			double hourInMins = (double)simpledateTime.getHours() * 60;
			double mins = (double)simpledateTime.getMinutes();
			double total = hourInMins + mins;
			slot = (int) (total / 60 * 4);

		} catch (ParseException e) {
			System.out.println("Parsing execption occured: " + e.getMessage());
		}
		return slot;
	}

}
