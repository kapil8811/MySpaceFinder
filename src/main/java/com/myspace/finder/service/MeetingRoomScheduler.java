package com.myspace.finder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myspace.finder.component.MeetingRoom;
import com.myspace.finder.constant.MySpaceRoomFinderConstant;
import com.myspace.finder.utility.MeetingRoomUtility;
import com.myspace.finder.validation.MeetingRoomValidator;

public class MeetingRoomScheduler {

	private Map<String, MeetingRoom> meetingEntries;
	private MeetingRoomValidator meetingRoomValidator;
	private MeetingRoomUtility meetingRoomUtility;

	public MeetingRoomScheduler() {
		meetingEntries = new HashMap<>();
		meetingRoomValidator = new MeetingRoomValidator();
		meetingRoomUtility = new MeetingRoomUtility();
	}

	private String lookForAvailableMeetingRoom(MeetingRoom room, String startDateTime, String endDateTime,
			boolean fillSlots) {
		// get starting slot
		String output = MySpaceRoomFinderConstant.NO_VACANT_ROOM;
		int startSlot = meetingRoomUtility.getSlot(startDateTime);
		int endSlot = meetingRoomUtility.getSlot(endDateTime);
		if (!room.slotOccupied(startSlot, endSlot)) {
			// check if buffer slots
			if (!meetingRoomUtility.bufferSlots(startSlot, endSlot)) {
				if (fillSlots)
					room.insertSlots(startSlot, endSlot);
				output = MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_NAME;
			}
		}
		return output;
	}

	private String verifyAndAllocateRoom(MeetingRoom room, String roomName, String startDt, String endDt) {
		String output;
		if (room == null) {
			room = new MeetingRoom();
		}

		output = this.lookForAvailableMeetingRoom(room, startDt, endDt, true);
		if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
			output = roomName;
		}
		meetingEntries.put(roomName, room);
		return output;
	}

	private String LookInFirstMeetingRoom(String startDateTime, String endDateTime) {
		// add entry to the first meeting room
		// check if the slot is available
		String output;
		MeetingRoom room = meetingEntries.get(MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_NAME);

		output = verifyAndAllocateRoom(room, MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_NAME, startDateTime,
				endDateTime);
		if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
			output = MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_NAME;
			return output;
		} else {
			MeetingRoom secondRoom = meetingEntries.get(MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME);
			output = verifyAndAllocateRoom(secondRoom, MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME,
					startDateTime, endDateTime);
			if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
				output = MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME;
				return output;
			} else {
				MeetingRoom thirdRoom = meetingEntries.get(MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME);
				output = verifyAndAllocateRoom(thirdRoom, MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME,
						startDateTime, endDateTime);
				if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
					output = MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME;
					return output;
				}
			}
		}
		return output;
	}

	private String LookInSecondMeetingRoom(String startDateTime, String endDateTime) {
		// add entry to the first meeting room
		// check if the slot is available
		MeetingRoom secondRoom = meetingEntries.get(MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME);
		String output = verifyAndAllocateRoom(secondRoom, MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME,
				startDateTime, endDateTime);
		if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
			output = MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME;
			return output;
		} else {
			MeetingRoom thirdRoom = meetingEntries.get(MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME);
			output = verifyAndAllocateRoom(thirdRoom, MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME, startDateTime,
					endDateTime);
			if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
				output = MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME;
				return output;
			}
		}

		return output;
	}

	private String LookInThirdMeetingRoom(String startDateTime, String endDateTime) {
		// add entry to the first meeting room
		// check if the slot is available
		MeetingRoom thirdRoom = meetingEntries.get(MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME);
		String output = verifyAndAllocateRoom(thirdRoom, MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME,
				startDateTime, endDateTime);
		if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
			output = MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME;
			return output;
		}
		return output;
	}

	public String AddNewEntry(String startDateTime, String endDateTime, int person) {
		String output;
		int resultCode = meetingRoomValidator.validateInputForNewRecords(startDateTime, endDateTime, person);
		if (resultCode == 1) {
			// check if any of the meeting room is available
			if (person <= MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_SIZE) {

				output = this.LookInFirstMeetingRoom(startDateTime, endDateTime);
			} else if (person <= MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_SIZE) {
				output = this.LookInSecondMeetingRoom(startDateTime, endDateTime);
			} else {
				output = this.LookInThirdMeetingRoom(startDateTime, endDateTime);
			}
		} else {
			if (resultCode == 0)
				output = MySpaceRoomFinderConstant.INCORRECT_INPUT;
			else
				output = MySpaceRoomFinderConstant.NO_VACANT_ROOM;
		}
		return output;
	}

	private void viewAndAddRoomToList(MeetingRoom room, String roomName, List<String> availableRooms,
			String startDateTime, String endDateTime) {
		String output;
		if (room == null)
			room = new MeetingRoom();
		output = this.lookForAvailableMeetingRoom(room, startDateTime, endDateTime, false);
		if (!output.equals(MySpaceRoomFinderConstant.NO_VACANT_ROOM)) {
			availableRooms.add(roomName);
		}

	}

	public List<String> ViewAddedEntries(String startDateTime, String endDateTime) {
		List<String> availableRooms = new ArrayList<>();

		if (meetingRoomValidator.validateDateTime(startDateTime, endDateTime)) {
			MeetingRoom room = meetingEntries.get(MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_NAME);
			this.viewAndAddRoomToList(room, MySpaceRoomFinderConstant.FIRST_MEETING_ROOM_NAME, availableRooms,
					startDateTime, endDateTime);
			MeetingRoom secondRoom = meetingEntries.get(MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME);
			this.viewAndAddRoomToList(secondRoom, MySpaceRoomFinderConstant.SECOND_MEETING_ROOM_NAME, availableRooms,
					startDateTime, endDateTime);
			MeetingRoom thirdRoom = meetingEntries.get(MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME);
			this.viewAndAddRoomToList(thirdRoom, MySpaceRoomFinderConstant.THIRD_MEETING_ROOM_NAME, availableRooms,
					startDateTime, endDateTime);
			if (availableRooms.isEmpty()) {
				availableRooms.add(MySpaceRoomFinderConstant.NO_VACANT_ROOM);
			}
		} else
			availableRooms.add(MySpaceRoomFinderConstant.INCORRECT_INPUT);

		return availableRooms;
	}

}
