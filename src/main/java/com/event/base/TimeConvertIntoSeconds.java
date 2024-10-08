package com.event.base;

public class TimeConvertIntoSeconds {
	
	// Helper method to convert "hours : minutes : seconds" into total seconds
    public int extractTotalSeconds(String remainingTime) {
        // Example input: "1 hours : 3 Mins : 22 seconds"
        String[] timeParts = remainingTime.split(":");
        
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        // Ensure we are dealing with spaces and correct parsing
        for (String part : timeParts) {
            part = part.trim();  // Trim any leading or trailing spaces
            if (part.contains("hours")) {
                hours = Integer.parseInt(part.replaceAll("[^0-9]", ""));
            } else if (part.contains("Mins")) {
                minutes = Integer.parseInt(part.replaceAll("[^0-9]", ""));
            } else if (part.contains("seconds")) {
                seconds = Integer.parseInt(part.replaceAll("[^0-9]", ""));
            }
        }

        // Convert to total seconds
        return (hours * 3600) + (minutes * 60) + seconds;
    }
    
}
