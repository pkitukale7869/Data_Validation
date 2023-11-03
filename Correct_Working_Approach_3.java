package com.testClass.pro;

import java.util.*;

/*************** LASTLY APPROVED ***************************/
public class Correct_Working_Approach_3 {
	public static void main(String[] args) {
		String data = "2023-10-30 00:00:00.0,2023-10-30 00:30:00.0,2023-10-30 00:15:00.0,2023-10-30 00:45:00.0,2023-10-30 01:00:00.0,2023-10-30 01:30:00.0,2023-10-30 01:15:00.0,2023-10-30 01:45:00.0,2023-10-30 02:00:00.0,2023-10-30 03:30:00.0,2023-10-30 03:00:00.0,2023-10-30 03:45:00.0,2023-10-30 03:15:00.0,2023-10-30 04:30:00.0,2023-10-30 04:00:00.0,2023-10-30 04:45:00.0,2023-10-30 04:15:00.0,2023-10-30 07:15:00.0,2023-10-30 12:15:00.0,2023-10-30 12:30:00.0,2023-10-30 12:45:00.0,2023-10-30 12:00:00.0,2023-10-30 14:15:00.0,2023-10-30 16:00:00.0,2023-10-30 16:15:00.0,2023-10-30 16:30:00.0,2023-10-30 16:45:00.0,2023-10-30 17:00:00.0,2023-10-30 17:15:00.0,2023-10-30 17:30:00.0,2023-10-30 17:45:00.0,2023-10-30 20:00:00.0,2023-10-30 20:15:00.0,2023-10-30 20:30:00.0,2023-10-30 20:45:00.0,2023-10-30 23:00:00.0,2023-10-30 23:15:00.0,2023-10-30 23:30:00.0,2023-10-30 23:45:00.0,2023-10-31 00:00:00.0,2023-10-31 00:15:00.0,2023-10-31 00:30:00.0,2023-10-31 00:45:00.0,2023-10-31 01:00:00.0,2023-10-31 01:15:00.0,2023-10-31 01:30:00.0,2023-10-31 01:45:00.0,2023-10-31 04:45:00.0,2023-10-31 04:15:00.0,2023-10-31 04:00:00.0,2023-10-31 04:30:00.0,2023-10-31 14:00:00.0,2023-10-31 14:15:00.0,2023-10-31 14:30:00.0,2023-10-31 14:45:00.0,2023-10-31 23:30:00.0,2023-10-31 23:00:00.0,2023-10-31 23:45:00.0,2023-10-31 23:15:00.0,2023-11-01 00:00:00.0";

		String[] timestamps = data.split(",");

		Map<String, List<String>> dateToTimestamps = new HashMap<>();

		for (String timestamp : timestamps) {
			String date = timestamp.split(" ")[0];
			dateToTimestamps.computeIfAbsent(date, k -> new ArrayList<>()).add(timestamp);
		}

		// Iterate through each date and find missing data
		for (Map.Entry<String, List<String>> entry : dateToTimestamps.entrySet()) {
			String date = entry.getKey();
			System.out.println("\r");
			List<String> timestampList = entry.getValue();

			Map<Integer, Integer> hourOccurrences = new HashMap<>();
			for (String dateTime : timestampList) {
				int hour = Integer.parseInt(dateTime.split(" ")[1].split(":")[0]);
				hourOccurrences.put(hour, hourOccurrences.getOrDefault(hour, 0) + 1);
			}

			// Find and output missing data for the current date
			boolean missingData = false;
			int startHour = -1;
			int endHour = -1;

			for (int hour = 0; hour < 24; hour++) {
				int occurrences = hourOccurrences.getOrDefault(hour, 0);

				if (occurrences < 4) {
					if (!missingData) {
						startHour = hour;
					}
					missingData = true;
					endHour = hour;
				} else {
					if (missingData) {
						int duration = endHour - startHour + 1;
						if (duration > 3) {
							for (int i = startHour; i <= endHour - 1; i += 3) {
								int blockStartHour = i;
								int blockEndHour = Math.min(i + 3, endHour);
								System.out.println("Day " + date.split("-")[2] + " Start Hour: " + blockStartHour
										+ " End Hour: " + blockEndHour);
							}
						} else {
							System.out.println("Day " + date.split("-")[2] + " Start Hour: " + startHour + " End Hour: "
									+ (endHour + 1));
						}
					}
					missingData = false;
				}
			}

			if (missingData) {
				int duration = endHour - startHour + 1;
				if (duration > 3) {
					for (int i = startHour; i <= endHour - 1; i += 3) {
						int blockStartHour = i;
						int blockEndHour = Math.min(i + 3, endHour);
						System.out.println("Day " + date.split("-")[2] + " Start Hour: " + blockStartHour
								+ " End Hour: " + blockEndHour);
					}
				} else {
					System.out.println(
							"Day " + date.split("-")[2] + " Start Hour: " + startHour + " End Hour: " + (endHour + 1));
				}
			}
		}
	}
}