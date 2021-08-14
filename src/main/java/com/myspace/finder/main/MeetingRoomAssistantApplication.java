package com.myspace.finder.main;

import java.util.List;
import com.myspace.finder.constant.MySpaceRoomFinderConstant;
import com.myspace.finder.file.MeetingRoomInputReader;
import com.myspace.finder.service.MeetingRoomScheduler;

public class MeetingRoomAssistantApplication {
	private static MeetingRoomInputReader meetingRoomInputReader = new MeetingRoomInputReader();

	public static void main(String[] args) {
		try {
			if (meetingRoomInputReader.setupInput(args)) {
				processBookingInfo(meetingRoomInputReader);
			} else {
				System.out.println("INCORRECT_INPUT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void processBookingInfo(MeetingRoomInputReader meetingRoomInputReader) {
		MeetingRoomScheduler meetingRoomScheduler = new MeetingRoomScheduler();
		try {
			String line = meetingRoomInputReader.readFileInputByLine();
			while (line != null) {
				String[] tokens = line.split(" ");
				switch (tokens[0]) {
				case MySpaceRoomFinderConstant.REQUEST_TYPE_BOOK:
					String output = meetingRoomScheduler.AddNewEntry(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
					System.out.println(" " + output);
					break;
				case MySpaceRoomFinderConstant.REQUEST_TYPE_VACANCY:
					List<String> output3 = meetingRoomScheduler.ViewAddedEntries(tokens[1], tokens[2]);
					for (int idx = 0; idx < output3.size(); idx++) {
						System.out.print(" " + output3.get(idx));
					}
					System.out.println("");
					break;
				default:
					break;
				}
				line = meetingRoomInputReader.readFileInputByLine();
			}
			meetingRoomInputReader.closeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
