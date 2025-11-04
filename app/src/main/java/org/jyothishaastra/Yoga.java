package org.jyothishaastra;

// Need Niraayana and absolute longitudes of Chandra
public class Yoga {
	// the prefixes are Paadas of 4 in each Raashis otherwise it has all 4 Padas in same Nakshatra
	static String yogas[] = { "Vishkambha", "Priti", "Ayushman", "Soubhagya", "Sobhana", "Atiganda", "Sukarma", "Dhrithi", "Soola", "Ganda", "Vriddhi", "Dhruva", "Vyaghata", "Harshana", "Vajra", "Siddhi", "Vyatipata", "Variyana", "Parigha", "Siva", "Siddha", "Sadhya", "Subha", "Sukla", "Brahma", "Eindra (Indra)", "Vaidhrthi" };

	private static int yogaIndex;
	// public static double (int[] chandraNiraayana){
	// raashi starting from 0 for Mesha
	public static String byWholeDegrees(int[] chaNirAbs){
		double yogaSectorSize = (360.0 / 27.0);
		yogaIndex = (int) (chaNirAbs[0]/yogaSectorSize);
		return yogas[yogaIndex];
	}

	private static int yoga_Index;
    private static double yogaDeg;
	static double remainingDistance;

	// raashi no. like Mesha is 1.
	public static String yoga(int chaNirAbs[], int surNirAbs[]) throws Exception {

		yogaDeg = DegMinSec.toDegrees(DegMinSec.addMoreThreeSixty(chaNirAbs, surNirAbs));
        System.out.printf("Yoga deg is %4.9f\n", yogaDeg);

		if(yogaDeg > 360)
			yogaDeg = yogaDeg - 360;

		// 13 deg 20 min for each sector for 27 yogas
		double yogaSectorSize = (360.0 / 27.0);

		// Each Karana is 12/2 == 6 degrees
		// Each Yoga is 27 degrees
		// yoga_Index = (int) ( yogaDeg / yogaSectorSize );
		double yoga_Index_d = ( yogaDeg / yogaSectorSize );
		System.out.println("Yoga elapsed " + yoga_Index_d);

		yoga_Index = (int) yoga_Index_d;

		double endLimit = (yoga_Index + 1) * yogaSectorSize;
		remainingDistance = endLimit - yogaDeg;
		// System.out.println("Remaining " + remainingDistance);
		double elapsed = yogaDeg % yogaSectorSize;
        // tithiIndex = (int) Math.round(tithi);

		// no minus 1 as we dealing with array
		return "%s - %4.9f deg have elapsed".formatted(yogas[yoga_Index], elapsed);
	}

	// end time in hours
	public static double end(int[] chaMot, int[] surMot) throws Exception {
		// endTime = RD/ (DMC + DMS) * 24
		/* 
if(remainingDistance == 0.0)
			throw new Exception("Remaining distance is 0.0");

		if(yogaDeg == 0.0)
			throw new Exception("yogaDeg distance is 0.0");
		*/
		// return (remainingDistance/ DegMinSec.toDegrees(chaMot) + DegMinSec.toDegrees(surMot))  * 24;
		double sum = DegMinSec.toDegrees(DegMinSec.add(chaMot, surMot));
		System.out.printf("Sum is %4.9f", sum);
		return (remainingDistance / sum)  * 24;
	}

}
