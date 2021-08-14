package com.myspace.finder.constant;

public class MySpaceRoomFinderConstant {
	
	private MySpaceRoomFinderConstant() {
		throw new IllegalStateException("MySpaceRoomFinderConstant class");
	}
	
	public static final String REQUEST_TYPE_VACANCY    = "VACANCY";
	public static final String REQUEST_TYPE_BOOK       = "BOOK";
	public static final String NO_VACANT_ROOM    = "NO_VACANT_ROOM";
	public static final String INCORRECT_INPUT   = "INCORRECT_INPUT";
	public static final int MIN_NUMBER_OF_PERSON = 2;
	public static final int MAX_NUMBER_OF_PERSON = 20;
	public static final int TIME_INTERVAL_IN_MINUTE = 15;
	public static final int FIRST_MEETING_ROOM_SIZE = 3;
	public static final int SECOND_MEETING_ROOM_SIZE = 7;
	public static final int MAX_SLOT_PER_DAY = 96;
	public static final String DATE_TIME_PATTERN = "HH:mm";
	public static final String FIRST_MEETING_ROOM_NAME    = "C-Cave";
	public static final String SECOND_MEETING_ROOM_NAME   = "D-Tower";
	public static final String THIRD_MEETING_ROOM_NAME    = "G-Mansion";
	
	// --- buffer slots ( kept as per 15 min interval  from 00:00)
	public static final int MORNING_9AM_SLOT_BEGIN  = 36;
	public static final int MORNING_9AM_SLOT_END    = 37;
	public static final int NOON_1PM_SLOT_BEGIN     = 53;
	public static final int NOON_1PM_SLOT_END       = 55;
	public static final int EVE_645_SLOT_BEGIN      = 75;
	public static final int EVE_645_SLOT_END        = 76;
	
	
	
}
