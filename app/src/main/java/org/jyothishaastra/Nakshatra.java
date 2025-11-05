package org.jyothishaastra;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

// Need Niraayana and absolute longitudes of Chandra
public class Nakshatra {
	// the prefixes are Paadas of 4 in each Raashis otherwise it has all 4 Padas in same Nakshatra
	static String nakshatras[] = { "Ashwini", "Bharani", "Krithika13", "Rohini", "Mrigashira22", "Aradra", "Punarvasu31", "Pushya", "Ashlesha", "Magha", "Poorva Phalguni Pubha", "Uttara Phalguni13", "Hashta", "Chitta22", "Swathi", "Vishakha31", "Anuradha", "Jyeshta", "Moola", "Purvashaada", "Uttarashaada13", "Shravana", "Dhanishta22", "Shathabisha", "Poorvabadha31", "Uttarabadha", "Revathi" };

	static double amrutha[] = { 16.8,19.2,21.6,20.8,15.2,14,21.6,17.6,22.4,21.6,17.6,16.8,18,17.6,15.2,15.2,13.6,15.2,17.6,19.2,17.6,13.6,13.6,16.8,16,19.2,21.6};

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
	public double amruthaEnd(){
		// duration of Naks * 1.6/24;
		return naksDuration * 1.6/24;
	}

	public Calendar amruthaStart(){
		// start time of Naks + X/24;
		double hrs = (int)amrutha[nakshaIndex]/24;
		amruthaStart = nakshatraStart;

		amruthaStart.add(Calendar.HOUR, (int)hrs);
		amruthaStart.add(Calendar.MINUTE, (int)(hrs-(int)hrs) * 10);
		return amruthaStart;
	}

	public static int[] getElapsed(){
		elapsedArr = DegMinSec.getGeoCoordsFromDegree(elapsed);
		return elapsedArr;
	}

	public static Calendar getNakshatraEnd(){
		nakshatraEnd = nakshatraStart;
		nakshatraEnd.add(Calendar.HOUR_OF_DAY, endArr[0]);
		nakshatraEnd.add(Calendar.MINUTE, endArr[1]);
		nakshatraEnd.add(Calendar.SECOND, endArr[2]);
		return nakshatraEnd;
	}

	public static double getDuration(){
		LocalDateTime ld = nakshatraEnd.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime ld2 = nakshatraStart.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		naksDuration = Duration.between(ld, ld2).getSeconds() / (60 * 60);
		return naksDuration;
	}

	public static int[] amrutha(Calendar date){
		date.add(Calendar.HOUR_OF_DAY, -1 * elapsedArr[0]);
		date.add(Calendar.MINUTE, -1 * elapsedArr[1]);
		date.add(Calendar.SECOND, -1 * elapsedArr[2]);

		nakshatraStart = date;
		return DegMinSec.getGeoCoordsFromDegree(elapsed);
	}

	// end time in hours
	public static double end(int[] chaMot, double remainingDistance) throws Exception {
		//        return DegMinSec.degrees(new int[]{tithiSector * 12, 0, 0}) - tithiDeg;
		/* 
		   System.out.printf("1 is %4.9f\n", DegMinSec.degrees(chaMot) * 60);
		   System.out.printf("2 is %4.9f\n", DegMinSec.degrees(surMot) * 60);
		   System.out.printf("3 is %4.9f\n", DegMinSec.toMinutes(remainingDistance));
		   */
		// endTime = RD/ (DMC - DMS) * 24
		 end = (remainingDistance/ DegMinSec.toDegrees(chaMot))  * 24;
		 return end;
	}
}
