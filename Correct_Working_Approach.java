package com.testClass.pro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*****************
 * LOGIC WORKING FINE DATA SHOULD BE IN ORDER BY IN ASC
 ******************/
public class Correct_Working_Approach {
	public static void main(String[] args) {
		String data = "2023-10-30 00:00:00.0,2023-10-30 00:30:00.0,2023-10-30 00:15:00.0,2023-10-30 00:45:00.0,2023-10-30 01:00:00.0,2023-10-30 01:30:00.0,2023-10-30 01:15:00.0,2023-10-30 01:45:00.0,2023-10-30 02:00:00.0,2023-10-30 03:30:00.0,2023-10-30 03:00:00.0,2023-10-30 03:45:00.0,2023-10-30 03:15:00.0";

		List<String> fixedLengthList = Arrays.asList(data.split(","));

		HashMap<Integer, Integer> hourOccurrences = new HashMap<>();

		for (String dateTime : fixedLengthList) {
			int hour = Integer.parseInt(dateTime.split(" ")[1].split(":")[0]);
			hourOccurrences.put(hour, hourOccurrences.getOrDefault(hour, 0) + 1);
		}

		for (int hour = 0; hour < 10; hour++) {
			int occurrences = hourOccurrences.getOrDefault(hour, 0);

			if (occurrences < 4 || occurrences == 0) {
				int nextHour = (hour + 1) % 24;
				System.out.println("Missing data between " + String.format("%02d", hour) + ":00 and "
						+ String.format("%02d", nextHour) + ":00");
			}
		}
	}
}
