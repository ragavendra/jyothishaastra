package org.jyothishaastra;
import java.util.Arrays;
import java.util.Calendar;

public class DegMinSec {

	// res is in "ddd.mmss" format
	// get in degrees
	public static double toDegrees(int degree, int minutes, int seconds){
        int abs = Math.abs(degree);

        if(degree == 0) {
			// System.out.println("deg is 0");
            degree = 1;
		}

		return Math.signum(degree) * (abs + (minutes / 60.0) + (seconds / 3600.0));
	}

	public static double toDegrees(int arr[]){
		return toDegrees(arr[0], arr[1], arr[2]);
	}

	public static double toMinutes(double degrees){
        int no = (int) degrees;
        double result = no * 60 + (degrees - no) * 60;
        return result;
	}

	// get in degrees, minutes and seconds, can be used as ar[0]-ar[1]-ar[2]
	public static int[] getGeoCoords(int degree, int minutes, int seconds) {

		double resDegrees = toDegrees(degree, minutes, seconds);
		return getGeoCoordsFromDegree(resDegrees);
	}

	public static int[] getGeoCoords(int arr[]) {
		return getGeoCoords(arr[0], arr[1], arr[2]);
	}

	public static int[] getGeoCoordsFromDegree(double resDegrees) {
		int d = (int)resDegrees;  // Truncate the decimals
		double t1 = (resDegrees - d) * 60;
		int m = (int)t1;
		double s = (t1 - m) * 60.0;
		int sec = (int) Math.round(s);
		if (sec == 60){
			m = m+1;
			sec =0;
			// System.out.println("In seconds minu loop");
		}

		if (m > 60) {
			d = d++;
			m = m - 60;
			System.out.println("In minutes minu loop");
		}

		int ar[] = { d, m, sec};

		return ar;
	}

	public static int[] add(int dms1[], int dms2[]) {
		double d1 = toDegrees(dms1);
		double d2 = toDegrees(dms2);

		double res = d1 + d2;
		// System.out.println("Add is " + res);	

		// if more than 360 subtract it from 360
		if(res > 360)
			return minus(getGeoCoordsFromDegree(res), getGeoCoordsFromDegree(toDegrees(360, 0, 0)));

		return getGeoCoordsFromDegree(res);
	}

	public static int[] minus(int dms1[], int dms2[]) {
		// System.out.println("dms1 is " + Arrays.toString(dms1));	
		// System.out.println("dms2 is " + Arrays.toString(dms2));	
		double d1 = toDegrees(dms1);
		double d2 = toDegrees(dms2);

		// System.out.println("d1 is " + d1);	
		// System.out.println("d2 is " + d2);	
		// swap
		if(d1 > d2){
			double d = d2;
			d2 = d1;
			d1 = d;
		}

		double res = d2 - d1;
		// System.out.println("Sub is " + res);	

		return getGeoCoordsFromDegree(res);
	}

	public static int absGeo(int raashi, int[] grahaGeo) throws Exception {
		return (grahaGeo[0] + 30 * ( raashi - 1));
	}

	/* 
	   javac jyotishaastra/Ayanamsha.java
	   java jyotishaastra/DegMinSec.java
	   */
	public static void main(String ags[]) throws Exception {

		// Mesha is 1
		 //  for 22nd Oct
		 int surRaashi = 7;
		 int chaRaashi = 8;
		 int swissEphermesisMoon[] = { 4, 7, 00 };
		 int ar1[] = { 4, 7, 0 }; // of next day's which is in Mesha raashi 9
		 int ar2[] = { 16, 1, 0 }; // of next day's which is in Mesha raashi 9
		 int swissEphermesisSun[] = { 28, 50, 42 };
		 int ar3[] = { 28, 50, 42 };
		 int ar4[] = { 29, 50, 25 };

		// India Ephemersis doesnt have Ayanamsha
		Calendar date = Calendar.getInstance();
		date.set(2025, 9, 22); 

		/*

		// for 15 July 2009
		int surRaashi = 4; // Karkaataka
		int chaRaashi = 1; // Mesha
		int swissEphermesisMoon[] = { 17, 41, 00 };
		int ar1[] = { 17, 41, 00 }; // of next day's which is in Mesha raashi 9
		int ar2[] = { 0, 50, 0 }; // of next day's which is Vrushabha
		int swissEphermesisSun[] = { 22, 39, 28 };
		int ar3[] = { 22, 39, 28 };
		int ar4[] = { 23, 36, 42 };

		// India Ephemersis doesnt have Ayanamsha
		Calendar date = Calendar.getInstance();
		date.set(2009, 6, 15); // for 15 July 2009
							   // date.set(2009, 7, 15); // for 15 July 2009
		 */
		/* 
		   From Indian Astronomical ephemeris we straightaway get the Nirayana
		   longitudes for Ravi and Chandra at 5:29 AM IST on 15-7-2009. The values
are:
		   Ravi = 88-39’-45’
		   Chandra = 353-40’-26”

		   112-39’-28”
		   - 23-59’-34”
		   ------------
		   88-39’-54” which is same as that we obtained from Indian
		   astronomical ephemeris.

		   Chandra’s longitude is 17-41’ in Aries. Subtracting Ayanamsa we get
		   353-41’-26” very near to that we obtain from Indian ephemeris.
		 */

		double ayanamsha = Ayanaamsha.ayanamsha(date);
		System.out.printf("Aya is %s degrees for %s\n", ayanamsha, date.toInstant());	
		// System.out.printf("Aya is %s degrees\n", Arrays.toString(DegMinSec.getGeoCoordsFromDegree(ayanamsha)));	
		// ayanamsha = 23.9928962;

		int chandraAbs[] = swissEphermesisMoon;
		chandraAbs[0] = DegMinSec.absGeo(chaRaashi, swissEphermesisMoon);

		int sooryaAbs[] = swissEphermesisSun;
		sooryaAbs[0] = DegMinSec.absGeo(surRaashi, swissEphermesisSun);
		System.out.printf("Abs geo of Surya is %d & Chandra is %d\n", sooryaAbs[0], chandraAbs[0]);

		System.out.printf("Tithi is %s and remaining distance is %4.9f.\n", Tithi.tithi(chandraAbs, sooryaAbs), Tithi.remainingDistance);

		/*uncomment when Chandra has moved to next Raashi 
		*/
		// ar1[0] = DegMinSec.absGeo(chaRaashi, ar1);
		chaRaashi = chaRaashi + 1; // since it is moving to next raashi
		ar2[0] = DegMinSec.absGeo(chaRaashi, ar2);

		int surMot[] = DegMinSec.minus(ar3, ar4);
		System.out.printf("Daily motion of Soorya is %s\n", Arrays.toString(surMot));

		int chaMot[] = DegMinSec.minus(ar1, ar2);
		System.out.printf("Daily motion of Chandra is %s\n", Arrays.toString(chaMot));

		// Time taken to cover remainingDistance
		System.out.printf("Tithi ends at %s from 5:30 IST or minus from 7am in PST?\n", Arrays.toString(DegMinSec.getGeoCoordsFromDegree(Tithi.end(surMot, chaMot, Tithi.remainingDistance))));

		System.out.printf("Karana is %s and remaining distance is %4.9f.\n", Karana.karana(chandraAbs, sooryaAbs), Karana.remainingDistance);
		// System.out.printf("Karana ends at %s from 5:30 IST or minus from 7am in PST?\n", Arrays.toString(DegMinSec.getGeoCoordsFromDegree(Karana.tithiEnd(surMot, chaMot, Tithi.remainingDistance))));

		int surNir[] = DegMinSec.getGeoCoordsFromDegree(Ayanaamsha.nirayaana(ayanamsha, sooryaAbs));
		// System.out.printf("Soorya niraayana on %s from Swiss is %s\n", date.getTime(), Arrays.toString(surNir));	

		int[] chaNir = DegMinSec.getGeoCoordsFromDegree(Ayanaamsha.nirayaana(ayanamsha, chandraAbs));
		System.out.printf("Chandra niraayana on %s from Swiss is %s\n", date.getTime(), Arrays.toString(chaNir));	

		// static from pdf, this is not from Indian Eph, not from Swiss Eph calc, but something newer, lol
		// chaNir = new int[]{ 353, 41, 52 };

		System.out.printf("Raashi is %s and remaining distance is %4.9f for Chandra Niraayana %s.\n", Raashi.raashi(chaNir), Raashi.remainingDistance, Arrays.toString(chaNir));

		// System.out.printf("Raashi ends at %s from 5:30 IST or minus from 7am in PST?\n", Arrays.toString(DegMinSec.getGeoCoordsFromDegree(Raashi.tithiEnd(surMot, chaMot, Tithi.remainingDistance))));
		System.out.printf("Nakshatra is %s and remaining distance is %4.9f.\n", Nakshatra.nakshatra(chaNir), Nakshatra.remainingDistance);

		// "Nakshatra ends at %s from 5:30 IST or minus from 7am in PST?\n"
		System.out.printf("Nakshatra ends at %s from 5:30 IST or minus from 7am in PST?\n", Nakshatra.end(chaMot, Nakshatra.remainingDistance));
		// System.out.printf("Nakshatra ends at %s for Cha mot %s and RD %s\n", Arrays.toString(DegMinSec.getGeoCoordsFromDegree(Nakshatra.end(chaMot, Nakshatra.remainingDistance))), Arrays.toString(chaMot), Arrays.toString(DegMinSec.getGeoCoordsFromDegree(Nakshatra.remainingDistance)));
	}
}
