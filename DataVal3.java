package com.testClass.pro;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/******************* CORRECT OUTPUT ******************************/
public class DataVal3 {
	public static void main(String[] args) {
		String data = "2023-10-30 18:00:00.0,2023-10-30 18:15:00.0,2023-10-30 18:30:00.0,2023-10-30 18:45:00.0,2023-10-30 21:00:00.0,2023-10-30 22:00:00.0,2023-10-30 23:00:00.0,2023-10-30 17:15:00.0,2023-10-30 17:30:00.0,2023-10-30 17:00:00.0,2023-10-30 17:45:00.0,2023-10-30 16:30:00.0,2023-10-30 16:00:00.0,2023-10-30 15:30:00.0,2023-10-30 15:00:00.0,2023-10-30 14:30:00.0,2023-10-30 14:00:00.0,2023-10-30 13:30:00.0,2023-10-30 13:00:00.0,2023-10-30 12:30:00.0,2023-10-30 12:00:00.0,2023-10-30 11:30:00.0,2023-10-30 11:00:00.0,2023-10-30 10:30:00.0,2023-10-30 10:00:00.0,2023-10-30 09:30:00.0,2023-10-30 09:00:00.0,2023-10-30 08:30:00.0,2023-10-30 08:00:00.0,2023-10-30 07:30:00.0,2023-10-30 07:00:00.0,2023-10-30 06:30:00.0,2023-10-30 06:00:00.0,2023-10-30 05:30:00.0,2023-10-30 05:00:00.0,2023-10-30 04:30:00.0,2023-10-30 04:00:00.0,2023-10-30 03:30:00.0,2023-10-30 03:00:00.0,2023-10-30 02:30:00.0,2023-10-30 02:00:00.0,2023-10-30 01:30:00.0,2023-10-30 00:30:00.0,2023-10-29 23:30:00.0,2023-10-29 23:00:00.0,2023-10-29 22:30:00.0,2023-10-29 22:00:00.0,2023-10-29 21:30:00.0,2023-10-29 21:00:00.0,2023-10-29 20:30:00.0,2023-10-29 20:00:00.0,2023-10-29 19:30:00.0,2023-10-29 19:00:00.0,2023-10-29 18:30:00.0,2023-10-29 18:00:00.0,2023-10-29 17:30:00.0,2023-10-29 17:00:00.0,2023-10-29 16:30:00.0,2023-10-29 16:00:00.0,2023-10-29 15:30:00.0,2023-10-29 15:00:00.0,2023-10-29 14:30:00.0,2023-10-29 14:00:00.0";
		String specificDate = "2023-10-30";
		List<Integer> problematicHours = analyzeData(data, specificDate);
		System.out.println("Problematic Hours: " + problematicHours);
	}

	public static List<Integer> analyzeData(String data, String specificDate) {
		List<Integer> problematicHours = new ArrayList<>();
		Map<Integer, Integer> hourCountMap = new HashMap<>();

		String[] entries = data.split(",");
		for (String entry : entries) {
			String[] dateTime = entry.trim().split(" ");
			String date = dateTime[0];
			String time = dateTime[1];

			if (date.equals(specificDate)) {
				String[] timeParts = time.split(":");
				int hour = Integer.parseInt(timeParts[0]);

				hourCountMap.put(hour, hourCountMap.getOrDefault(hour, 0) + 1);
			}
		}

		for (Map.Entry<Integer, Integer> entry : hourCountMap.entrySet()) {
			int hour = entry.getKey();
			int count = entry.getValue();
			if (count < 4) {
				problematicHours.add(hour);
				problematicHours.add((hour + 1) % 24); // Next HOUR
			}
		}
		return problematicHours;
	}
}
