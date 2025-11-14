package org.jyothishaastra;

import java.util.Arrays;
import java.util.Calendar;

// 8 parts in day time only
public class Kaalas {

	static int raahu[] = { 7, 1, 6, 4, 5, 3, 2 };
	static int guli[] = { 6, 5, 4, 3, 2, 1, 0 };
	static int yama[] = { 4, 3, 2, 1, 0, 6, 5 }; 
	static double durmuhurtha[][] = {{10.4}, {6.4, 8.8}, {2.4, 4.8}, {5.6}, {4, 8.8}, {2.4, 6.4}, {1.6}}; 
	static enum Kaala { Raahu, Guli, Yama };

	private static int []dayLength;

	// needed only for day == 2 or Tuesday
	private static int []nightLength;

	static int [][]durm = {{}, {}};
	static double duration; 
	static double durmDuration; 

	// day starts from 0 for Sunday
	// returns hh mm ss of kaala start
	// pass sunrise and sunset times based on the destination or the location's time zone
	public static int[] kaala(int sunrise[], int sunset[], int day, double dayLen, Kaala kaala) throws Exception {

		if(day > 6)
			day = day - 7;

		// dayLength = DegMinSec.minusMoreThreeSixty(sunset, sunrise);
		dayLength = DegMinSec.getGeoCoordsFromDegree(dayLen);

		// System.out.printf("Sunr  is %s\n", Arrays.toString(sunrise));
		// System.out.printf("Suns  is %s\n", Arrays.toString(sunset));
		// System.out.printf("Day length is %s\n", Arrays.toString(dayLength));
		var sunr = DegMinSec.toDegreesMoreThreeSixty(sunrise);
		// var sunr = sunrise[0] + sunrise[1]/60.0 + sunrise[2]/60.0;
		// System.out.printf("Sunr is %8.9f\n", sunr);
		int X = 0;

		if (kaala == Kaala.Raahu)
			X = raahu[day];
		else if (kaala == Kaala.Guli)
			X = guli[day];
		else 
			X = yama[day];
		duration = dayLen/8.0;
		var res = sunr + duration * X;
		return DegMinSec.getGeoCoordsFromDegree(res);
	}

	// needed only for day == 2 or Tuesday
	public static void setNightLength(int sunrise[], int sunriseNextDay[]) throws Exception {
		if(dayLength == null) {
			System.out.printf("Please run kaala() to set dayLength before running this method\n");	
			return;
		}

		int[] sunset = DegMinSec.addMoreThreeSixty(sunrise, dayLength);
		nightLength = DegMinSec.minusMoreThreeSixty(sunset, sunriseNextDay);
	}

	public static void durmuhurtha(int sunrise[], int day) throws Exception {

		if(day > 6)
			day = day - 7;

		double dayLen = DegMinSec.toDegrees(dayLength);
		var las = DegMinSec.toDegrees(dayLength) * durmuhurtha[day][0]/12.0;
		var durmuhurtha_ = DegMinSec.addMoreThreeSixty(sunrise, DegMinSec.getGeoCoordsFromDegree(las));
		durm[0] = durmuhurtha_;
		System.out.printf("Durm 1 %s\n", Arrays.toString(durm[0]));

		if(durmuhurtha[day].length == 2){
			// if day is Tuesday or day == 2, durmuhurtha needs duration of night * 4.8/12 (starting from sunset)
			if(day == 2){
				if(nightLength == null) {
					System.out.printf("Please run setNightLength() to set nightLength before running this method\n");	
					return;
				}
			}

			las = dayLen * durmuhurtha[day][1]/12.0;
			durmuhurtha_ = DegMinSec.addMoreThreeSixty(sunrise, DegMinSec.getGeoCoordsFromDegree(las));
			durm[1] = durmuhurtha_;
			System.out.printf("Durm 2 %s\n", Arrays.toString(durm[1]));
		}
		durmDuration = dayLen * 0.8/12;
		System.out.printf("Durm duration is %s\n", Arrays.toString(DegMinSec.getGeoCoordsFromDegree(durmDuration)));

		// return durm;
	}
}
