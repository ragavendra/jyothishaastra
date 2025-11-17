package org.jyothishaastra;

import java.util.Arrays;
import java.util.Calendar;

// Need Niraayana and absolute longitudes of Chandra
public class Nakshatra {
	// the prefixes are Paadas of 4 in each Raashis otherwise it has all 4 Padas in same Nakshatra
	static String nakshatras[] = { "Ashwini", "Bharani", "Krithika13", "Rohini", "Mrigashira22", "Aradra", "Punarvasu31", "Pushya", "Ashlesha", "Magha", "Poorva Phalguni Pubha", "Uttara Phalguni13", "Hashta", "Chitta22", "Swathi", "Vishakha31", "Anuradha", "Jyeshta", "Moola", "Purvashaada", "Uttarashaada13", "Shravana", "Dhanishta22", "Shathabisha", "Poorvapaada31", "Uttarapaada", "Revathi" };

	static double amrutha[] = { 16.8,19.2,21.6,20.8,15.2,14,21.6,17.6,22.4,21.6,17.6,16.8,18,17.6,15.2,15.2,13.6,15.2,17.6,19.2,17.6,13.6,13.6,16.8,16,19.2,21.6 };

	static double varjya[]= { 20,9.6,12,16,5.6,8.4,12,8,12.8,12,8,7.2,8.4,8,5.6,5.6,4,5.6, 8 , 9.6,8,4,4,7.2,6.4,9.6,12 };

	public static int nakshatraIndex;
    private static double nakshDeg;
    public static double elapsed;
    // public static int[] elapsedArr;
	static Calendar nakshatraStart;
	static Calendar amruthaStart;
	static Calendar varjyaStart;
	static Calendar amruthaEnd;
	static Calendar varjyaEnd;
	static Calendar nakshatraEnd;
	static double naksDuration;
    private static double end;
    private static double start;
    private static int[] endArr;
    private static int[] staArr;
	static double remainingDistance;
	// static double naksStartAgo;

	// raashi no. like Mesha is 1.
	public static String nakshatra(int chaNirAbs[]) throws Exception {

		nakshDeg = DegMinSec.toDegrees(chaNirAbs);
        // System.out.printf("Chandra deg is %4.9f\n", nakshDeg);

		if(nakshDeg > 360)
			nakshDeg = nakshDeg - 360;

        // System.out.printf("Nakshatra deg is %4.9f\n", nakshDeg);

		double nakshSectorSize = (360.0 / 27.0);

		// Each Karana is 12/2 == 6 degrees
		nakshatraIndex = (int) ( nakshDeg / nakshSectorSize );

		double endLimit = (nakshatraIndex + 1) * nakshSectorSize;
		remainingDistance = endLimit - nakshDeg;

		/* 
		double naksSecStart;
		if(nakshatraIndex != 0)
			naksSecStart = nakshatraIndex * nakshSectorSize;
		else
			naksSecStart = 0;

		naksStartAgo = nakshDeg - naksSecStart;

		System.out.println("NaksStartAgo " + naksStartAgo);
		*/

		// System.out.println("Remaining " + remainingDistance);
		elapsed = nakshDeg % nakshSectorSize;
        // tithiIndex = (int) Math.round(tithi);

		return "%s - %4.9f deg have elapsed".formatted(nakshatras[nakshatraIndex], elapsed);
	}

	// in hours
	public static Calendar amruVarjEnd(boolean amrutha){
		// duration of Naks * 1.6/24;
		double amrEnd = naksDuration * 1.6/24;

		Calendar amruVarjEnd;

		if(amrutha)
			amruVarjEnd = (Calendar) amruthaStart.clone();
		else
			amruVarjEnd = (Calendar) varjyaStart.clone();

		// System.out.printf("amrEnd is %s\n", amrEnd);	
		if(amrEnd > 24.0){
			amruVarjEnd.add(Calendar.DATE, 1);
			amrEnd = amrEnd - 24.0;
		}

		if(amrEnd >= 1){
			int no = (int) amrEnd;
			amruVarjEnd.add(Calendar.HOUR_OF_DAY, no);
			amrEnd = amrEnd - no; 
		}

		amruVarjEnd.add(Calendar.MINUTE, (int) (amrEnd * 100));

		// copy to class var
		if(amrutha)
			amruthaEnd = (Calendar) amruVarjEnd.clone();
		else
			varjyaEnd = (Calendar) amruVarjEnd.clone();

		return amruVarjEnd;
	}

	private static double CalToHrs(Calendar date){
		double hr = date.get(Calendar.HOUR_OF_DAY);
		double mn = date.get(Calendar.MINUTE);
		double res = hr + mn/60.0;
		return res;
	}
		
	// only diff bw amru and varj start and end is the X value
	public static Calendar amruVarjStart(boolean amrutha_){
		// start time of Naks + X/24 * durationOfNakshatra;
		double X;

		if(amrutha_)
			X = amrutha[nakshatraIndex];
		else
			X = varjya[nakshatraIndex];

		// get only hours:mm:ss in say decimal to use the formula
		int daysDiff = nakshatraEnd.get(Calendar.DATE) - nakshatraStart.get(Calendar.DATE);
		int addHours = 0;
		if(daysDiff > 0)
			addHours = 24 * daysDiff;

/* 
		long diffInMillis = nakshatraEnd.getTimeInMillis() - nakshatraStart.getTimeInMillis();
        double hoursDiff = diffInMillis / (1000 * 60 * 60.0 );
		System.out.printf("Hours duration is %8.9f\n", hoursDiff);	
*/

		double staInHrs = CalToHrs(nakshatraStart); 
		double endInHrs = CalToHrs(nakshatraEnd) + addHours; 
		// double diff = endInDec - staInDec;
		naksDuration = endInHrs - staInHrs;
		/* 
		System.out.printf("Naks duration is %8.9f\n", naksDuration);	
		System.out.printf("Start time of Nakshatra is %8.9f\n", staInHrs);	
		System.out.printf("X is %8.9f for naksIndex %d\n", X, nakshatraIndex);	
		System.out.printf("Naks start is %s\n", nakshatraStart.toInstant());	
		System.out.printf("Naks end is %s\n", nakshatraEnd.toInstant());	
		*/

		double amruVarj = staInHrs + X/24 * naksDuration;
		// System.out.printf("amruVarj is %8.9f\n", amruVarj);	

		Calendar amruVarjStart = (Calendar) nakshatraStart.clone();
		amruVarjStart.set(Calendar.HOUR_OF_DAY, 0);
		amruVarjStart.set(Calendar.MINUTE, 0);
		amruVarjStart.set(Calendar.SECOND, 0);
		// System.out.printf("amruVarjStart is %s\n", amruVarjStart.toInstant());	

		/* 
		if(amruVarj > 24.0) {
			amruVarjStart.add(Calendar.DATE, 1);
			amruVarj = amruVarj - 24.0;
		}

		if(amruVarj >= 1){
			int no = (int) amruVarj;
			amruVarjStart.add(Calendar.HOUR_OF_DAY, no);
			amruVarj = amruVarj - no;
		}

		amruVarjStart.add(Calendar.MINUTE, (int) (amruVarj * 100));
		*/
		amruVarjStart.add(Calendar.HOUR, (int) amruVarj);
		amruVarjStart.add(Calendar.MINUTE, (int)((amruVarj - (int) amruVarj) * 60));

		// copy to class var
		if(amrutha_)
			amruthaStart = (Calendar) amruVarjStart.clone();
		else
			varjyaStart = (Calendar) amruVarjStart.clone();


		return amruVarjStart;
	}

	// get end in Cal type
	public static Calendar absEnd(Calendar date){
		nakshatraEnd = (Calendar) date.clone();
		nakshatraEnd.add(Calendar.HOUR_OF_DAY, endArr[0]);
		nakshatraEnd.add(Calendar.MINUTE, endArr[1]);
		nakshatraEnd.add(Calendar.SECOND, endArr[2]);
		return nakshatraEnd;
	}
/* 
	// subtract the elapsed for the given date
	public static Calendar start(Calendar date){
		System.out.printf("Now %s\n", date.toInstant());
		// elapsed - 00:00 UT - is this the end time of the previous Nakshatra as well?
		nakshatraStart = (Calendar) date.clone();
		elapsedArr = DegMinSec.getGeoCoordsFromDegree(elapsed);
		System.out.printf("Elapsed arr %s\n", Arrays.toString(elapsedArr));
		nakshatraStart.add(Calendar.HOUR_OF_DAY, -1 * elapsedArr[0]);
		nakshatraStart.add(Calendar.MINUTE, -1 * elapsedArr[1]);
		nakshatraStart.add(Calendar.SECOND, -1 * elapsedArr[2]);

		return nakshatraStart;
	}

*/

	// subtract the elapsed for the given date
	public static Calendar absStart(Calendar date){
		// System.out.printf("Now %s\n", date.toInstant());
		// elapsed - 00:00 UT - is this the end time of the previous Nakshatra as well?
		nakshatraStart = (Calendar) date.clone();
		// elapsedArr = DegMinSec.getGeoCoordsFromDegree(elapsed);
		// System.out.printf("Elapsed arr %s\n", Arrays.toString(staArr));
		nakshatraStart.add(Calendar.HOUR_OF_DAY, -1 * staArr[0]);
		nakshatraStart.add(Calendar.MINUTE, -1 * staArr[1]);
		nakshatraStart.add(Calendar.SECOND, -1 * staArr[2]);

		return nakshatraStart;
	}

	// start time in hours using the formula
	public static double start(int[] chaMot, double transitDistance) throws Exception {
		//        return DegMinSec.degrees(new int[]{tithiSector * 12, 0, 0}) - tithiDeg;
		/* 
		   System.out.printf("1 is %4.9f\n", DegMinSec.degrees(chaMot) * 60);
		   System.out.printf("2 is %4.9f\n", DegMinSec.degrees(surMot) * 60);
		   System.out.printf("3 is %4.9f\n", DegMinSec.toMinutes(remainingDistance));
		   */
		// endTime = (RD/ DMC) * 24
		 start = (transitDistance/ DegMinSec.toDegrees(chaMot))  * 24;
		 staArr = DegMinSec.getGeoCoordsFromDegree(start);
		 // System.out.printf("Naks start arr %s\n", Arrays.toString(staArr));
		 return start;
	}
	// end time in hours using the formula
	public static double end(int[] chaMot, double remainingDistance) throws Exception {
		//        return DegMinSec.degrees(new int[]{tithiSector * 12, 0, 0}) - tithiDeg;
		/* 
		   System.out.printf("1 is %4.9f\n", DegMinSec.degrees(chaMot) * 60);
		   System.out.printf("2 is %4.9f\n", DegMinSec.degrees(surMot) * 60);
		   System.out.printf("3 is %4.9f\n", DegMinSec.toMinutes(remainingDistance));
		   */
		// endTime = (RD/ DMC) * 24
		 end = (remainingDistance/ DegMinSec.toDegrees(chaMot))  * 24;
		 endArr = DegMinSec.getGeoCoordsFromDegree(end);
		 // System.out.printf("End arr %s\n", Arrays.toString(endArr));
		 return end;
	}
}
