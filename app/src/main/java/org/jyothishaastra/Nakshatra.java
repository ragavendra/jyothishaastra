package org.jyothishaastra;

// Need Niraayana and absolute longitudes of Chandra
public class Nakshatra {
	// the prefixes are Paadas of 4 in each Raashis otherwise it has all 4 Padas in same Nakshatra
	static String nakshatras[] = { "Ashwini", "Bharani", "Krithika13", "Rohini", "Mrigashira22", "Aradra", "Punarvasu31", "Pushya", "Ashlesha", "Magha", "Poorva Phalguni Pubha", "Uttara Phalguni13", "Hashta", "Chitta22", "Swathi", "Vishakha31", "Anuradha", "Jyeshta", "Moola", "Purvashaada", "Uttarashaada13", "Shravana", "Dhanishta22", "Shathabisha", "Poorvabadha31", "Uttarabadha", "Revathi" };

	private static int nakshaIndex;

	// public static double (int[] chandraNiraayana){
	// raashi starting from 0 for Mesha
	public static String byWholeDegrees(int[] chaNirAbs){
		double nakshSectorSize = (360.0 / 27.0);
		nakshaIndex = (int) (chaNirAbs[0]/nakshSectorSize);
		return nakshatras[nakshaIndex];
	}

	private static int nakshatraIndex;
    private static double nakshDeg;
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
		double elapsed = nakshDeg % nakshSectorSize;
        // tithiIndex = (int) Math.round(tithi);

		return "%s - %4.9f deg have elapsed".formatted(nakshatras[nakshatraIndex], elapsed);
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
		return (remainingDistance/ DegMinSec.toDegrees(chaMot))  * 24;
	}

}
