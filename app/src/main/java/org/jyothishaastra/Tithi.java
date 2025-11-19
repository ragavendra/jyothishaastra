package org.jyothishaastra;

import java.util.Arrays;
import java.util.Calendar;

public class Tithi {

	// 30 Tithis or days with Poornima on 15th and Amavaasya on the 30th
	static String tithis[] = { "Padya", "Bidige", "Tadige", "Chauthi", "Panchami", "Sashti", "Saptami", "Ashtami", "Navami", "Dashami", "Ekadashi", "Dwadashi", "Triodashi", "Chaturdashi", "Poornima", "Padya", "Bidige", "Tadige", "Chauthi", "Panchami", "Sashti", "Saptami", "Ashtami", "Navami", "Dashami", "Ekadashi", "Dwadashi", "Triodashi", "Chaturdashi", "Amavaasya" };

	private static int tithiIndex;

	/* 
	// public static double (int[] chandraNiraayana){
	// raashi starting from 0 for Mesha
	public static String byWholeDegrees(int[] chandraWholeDeg, int raashi){
		nakshaIndex = chandraWholeDeg[0]/27;
		return tithis[nakshaIndex];
	}
	*/

	static double remainingDistance;
	static double elapsed;
    private static double tithiDeg;

	// raashi no. like Mesha is 1.
	// Each Tithi is 12 degrees
	public static String tithi(int chandraAbs[], int sooryaAbs[]) throws Exception {
		// Thithi = Chandra - Soorya Sayaana or Nirayaana
		// ar1 and ar3 are Chandra & Soorya'a Sayaana

		// if Chandra is less than Soorya, add 360 to it
		if(chandraAbs[0] < sooryaAbs[0]){
			chandraAbs[0] = chandraAbs[0] + 360;
			System.out.printf("Chandra abs %s\n", Arrays.toString(chandraAbs));
		}

		tithiDeg = DegMinSec.toDegrees(DegMinSec.minusMoreThreeSixty(sooryaAbs, chandraAbs));
        System.out.printf("Tithi deg is %4.9f\n", tithiDeg);

		// Each Tithi is 12 degrees
		tithiIndex = (int) ( tithiDeg / 12 );

        // Shukla Suddha tithi for less than 15
		String paksha = "";

		// 29th day is Amavasya; 14th day is Poornima
		if(tithiIndex > 0 && tithiIndex < 15){
			paksha = "Shukla Suddha";
		}

		if(tithiIndex > 15 && tithiIndex < 29){
			paksha = "Krishna Bahula";
		}

		double endLimit = (tithiIndex + 1)* 12;
		remainingDistance = endLimit - tithiDeg;
		// System.out.println("Remaining " + remainingDistance);
		elapsed = tithiDeg%12;
        // tithiIndex = (int) Math.round(tithi);

		return "%s - %s - %4.9f deg have elapsed".formatted(paksha, tithis[tithiIndex], elapsed);
	}

	// end time in hours
	public static double start(int[] surMot, int[] chaMot, double elapsed) throws Exception {
		return (DegMinSec.toMinutes(elapsed)/ (DegMinSec.toMinutes(DegMinSec.toDegrees(chaMot)) - DegMinSec.toMinutes(DegMinSec.toDegrees(surMot)) ))  * 24;
	}

	// end time in hours
	public static double end(int[] surMot, int[] chaMot, double remainingDistance) throws Exception {
		/* 
		   (RD/ DMC - DMS) * 24
		System.out.printf("chaMot and surMot is %s and %s\n", Arrays.toString(chaMot), Arrays.toString(surMot));
		//        return DegMinSec.degrees(new int[]{tithiSector * 12, 0, 0}) - tithiDeg;
		   System.out.printf("1 is %4.9f\n", DegMinSec.toDegrees(chaMot));
		   // System.out.printf("1 is %4.9f\n", DegMinSec.toMinutes(DegMinSec.toDegreesMoreThreeSixty(chaMot)));
		   System.out.printf("2 is %4.9f\n", DegMinSec.toDegrees(surMot));
		   // System.out.printf("2 is %4.9f\n", DegMinSec.toMinutes(DegMinSec.toDegreesMoreThreeSixty(surMot)));
		   System.out.printf("3 is %4.9f\n", DegMinSec.toMinutes(remainingDistance));
		// endTime = RD/ (DMC - DMS) * 24
		*/
		return (DegMinSec.toMinutes(remainingDistance)/ (DegMinSec.toMinutes(DegMinSec.toDegrees(chaMot)) - DegMinSec.toMinutes(DegMinSec.toDegrees(surMot)) ))  * 24;
	}
}
