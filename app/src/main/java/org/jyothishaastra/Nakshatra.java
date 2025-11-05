package org.jyothishaastra;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

// Need Niraayana and absolute longitudes of Chandra
public class Nakshatra {
	// the prefixes are Paadas of 4 in each Raashis otherwise it has all 4 Padas in same Nakshatra
	static String nakshatras[] = { "Ashwini", "Bharani", "Krithika13", "Rohini", "Mrigashira22", "Aradra", "Punarvasu31", "Pushya", "Ashlesha", "Magha", "Poorva Phalguni Pubha", "Uttara Phalguni13", "Hashta", "Chitta22", "Swathi", "Vishakha31", "Anuradha", "Jyeshta", "Moola", "Purvashaada", "Uttarashaada13", "Shravana", "Dhanishta22", "Shathabisha", "Poorvapaada31", "Uttarapaada", "Revathi" };

	static double amrutha[] = { 16.8,19.2,21.6,20.8,15.2,14,21.6,17.6,22.4,21.6,17.6,16.8,18,17.6,15.2,15.2,13.6,15.2,17.6,19.2,17.6,13.6,13.6,16.8,16,19.2,21.6};

	static double staticdoublevarjya[]= {20,9.6,12,16,5.6,8.4,12,8,12.8,12,8,7.2,8.4,8,5.6,5.6,4,5.6, 8,22.4, 9.6,8,4,4,7.2,6.4,9.6,12};

	private static int nakshaIndex;

	// public static double (int[] chandraNiraayana){
	// raashi starting from 0 for Mesha
	public static String byWholeDegrees(int[] chaNirAbs){
		double nakshSectorSize = (360.0 / 27.0);
		nakshaIndex = (int) (chaNirAbs[0]/nakshSectorSize);
		return nakshatras[nakshaIndex];
	}

	public static int nakshatraIndex;
    private static double nakshDeg;
    public static double elapsed;
    public static int[] elapsedArr;
	static Calendar nakshatraStart;
	static Calendar amruthaStart;
	static Calendar amruthaEnd;
	static Calendar nakshatraEnd;
	static double naksDuration;
    private static double end;
    private static int[] endArr;
	static double remainingDistance;

	// raashi no. like Mesha is 1.
	public static String nakshatra(int chaNirAbs[]) throws Exception {

		nakshDeg = DegMinSec.toDegrees(chaNirAbs);
        // System.out.printf("Chandra deg is %4.9f\n", nakshDeg);

		if(nakshDeg > 360)
			nakshDeg = nakshDeg - 360;

		double nakshSectorSize = (360.0 / 27.0);

		// Each Karana is 12/2 == 6 degrees
		nakshatraIndex = (int) ( nakshDeg / nakshSectorSize );

		double endLimit = (nakshatraIndex + 1) * nakshSectorSize;
		remainingDistance = endLimit - nakshDeg;
		// System.out.println("Remaining " + remainingDistance);
		elapsed = nakshDeg % nakshSectorSize;
        // tithiIndex = (int) Math.round(tithi);

		return "%s - %4.9f deg have elapsed".formatted(nakshatras[nakshatraIndex], elapsed);
	}

	// in hours
	public static Calendar amruthaEnd(){
		// duration of Naks * 1.6/24;
		double amrEnd = naksDuration * 1.6/24;
		amruthaEnd = (Calendar) amruthaStart.clone();
		// System.out.printf("amrEnd is %s\n", amrEnd);	
		if(amrEnd > 24.0){
			amruthaEnd.add(Calendar.DATE, 1);
			amrEnd = amrEnd - 24.0;
		}

		if(amrEnd >= 1){
			int no = (int) amrEnd;
			amruthaEnd.add(Calendar.HOUR_OF_DAY, no);
			amrEnd = amrEnd - no; 
		}

		amruthaEnd.add(Calendar.MINUTE, (int) (amrEnd * 100));

		return amruthaEnd;
	}

	// in hours
	public static double amruthaEnd_(){
		// duration of Naks * 1.6/24;
		return naksDuration * 1.6/24;
	}

	private static double hrMnInDec(Calendar date){
		double hr = date.get(Calendar.HOUR_OF_DAY);
		double mn = date.get(Calendar.MINUTE);
		double res = hr + mn/60.0;
		return res;
	}

	public static Calendar amruthaStart(){
		// start time of Naks + X/24 * durationOfNakshatra;
		double X = amrutha[nakshaIndex];

		// get only hours:mm:ss in say decimal to use the formula
		int daysDiff = nakshatraEnd.get(Calendar.DATE) - nakshatraStart.get(Calendar.DATE);
		int addHours = 0;
		if(daysDiff > 0)
			addHours = 24 * daysDiff;

		double staInDec = hrMnInDec(nakshatraStart); 
		double endInDec = hrMnInDec(nakshatraEnd) + addHours; 
		// double diff = endInDec - staInDec;
		naksDuration = endInDec - staInDec;
		// System.out.printf("Naks duration is %s\n", naksDuration);	

		double amrutha = staInDec + X/24 * naksDuration;

		amruthaStart = (Calendar) nakshatraStart.clone();

		if(amrutha > 24.0) {
			amruthaStart.add(Calendar.DATE, 1);
			amrutha = amrutha - 24.0;
		}

		if(amrutha >= 1){
			int no = (int) amrutha;
			amruthaStart.add(Calendar.HOUR_OF_DAY, no);
			amrutha = amrutha - no;
		}

		amruthaStart.add(Calendar.MINUTE, (int) (amrutha * 100));

		return amruthaStart;
	}

	public static Calendar getNakshatraEnd(Calendar date){
		nakshatraEnd = (Calendar) date.clone();
		nakshatraEnd.add(Calendar.HOUR_OF_DAY, endArr[0]);
		nakshatraEnd.add(Calendar.MINUTE, endArr[1]);
		nakshatraEnd.add(Calendar.SECOND, endArr[2]);
		return nakshatraEnd;
	}

	public static Calendar start(Calendar date){
		// elapsed - 00:00 UT - is this the end time of the previous Nakshatra as well?
		nakshatraStart = (Calendar) date.clone();
		elapsedArr = DegMinSec.getGeoCoordsFromDegree(elapsed);
		nakshatraStart.add(Calendar.HOUR_OF_DAY, -1 * elapsedArr[0]);
		nakshatraStart.add(Calendar.MINUTE, -1 * elapsedArr[1]);
		nakshatraStart.add(Calendar.SECOND, -1 * elapsedArr[2]);

		return nakshatraStart;
	}

	// end time in hours
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
		 return end;
	}
}
