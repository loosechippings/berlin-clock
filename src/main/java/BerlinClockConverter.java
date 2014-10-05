import java.util.regex.Pattern;

public class BerlinClockConverter implements TimeConvert {
	@Override
	public String convert(String time) throws IllegalArgumentException {
		if (!isValidtimeString(time)) {
			throw new IllegalArgumentException();
		}
		return getSecondsLamp(time)+
				"\n"+
				getFiveHourLights(time)+
				"\n"+
				getOneHourLights(time)+
				"\n"+
				getFiveMinuteLights(time)+
				"\n"+
				getSingleMinuteLights(time);
	}

	public boolean isValidtimeString(String time) {
		Pattern timeString=Pattern.compile("[0-2][0-4]:[0-5][0-9]:[0-5][0-9]");
		return timeString.matcher(time).matches();
	}

	protected String getSecondsLamp(String time) {
		int seconds=Integer.parseInt(time.split(":")[2]);
		return (seconds%2==0)?"Y":"O";
	}

	protected String getFiveHourLights(String time) {
		int hours=getHoursFromTime(time);
		int wholeFiveHours=hours/5;
		return getLightRow(wholeFiveHours,4,"R");
	}

	protected String getOneHourLights(String time) {
		int hours=getHoursFromTime(time);
		int singleHours=hours%5;
		return getLightRow(singleHours,4,"R");
	}

	protected String getFiveMinuteLights(String time) {
		int minutes=getMinutesFromTime(time);
		int fiveMinutes=minutes/5;
		String lightRow=getLightRow(fiveMinutes,11,"Y");
		return lightRow.replaceAll("Y{3}?","YYR");
	}

	protected String getSingleMinuteLights(String time) {
		int minutes=getMinutesFromTime(time);
		int singleMinutes=minutes%5;
		return getLightRow(singleMinutes,4,"Y");
	}

	protected int getHoursFromTime(String time) {
		return Integer.parseInt(time.split(":")[0]);		
	}	

	protected int getMinutesFromTime(String time) {
		return Integer.parseInt(time.split(":")[1]);
	}

	protected String getLightRow(int lit,int rowLength,String litColour) {
		String lightRow="";
		for (int lights=1;lights<=rowLength;lights++) {
			lightRow+=(lit>=lights)?litColour:"O";
		}
		return lightRow;		
	}	
}