package org.jyothishaastra;

import java.util.Calendar;

// 8 parts in day time only
public class Kaalas {

	static int raahu[] = { 7, 1, 6, 4, 5, 3, 2 };
	static int guli[] = { 6, 5, 4, 3, 2, 1, 0 };
	static int yama[] = { 4, 3, 2, 1, 0, 6, 5 }; 
	static enum Kaala { Raahu, Guli, Yama };

	private static int []dayLength;
	static double duration; 

	// day starts from 0 for Sunday
	// returns hh mm ss of kaala start
	public static int[] kaala(int sunrise[], int sunset[], int day, Kaala kaala) throws Exception {
		dayLength = DegMinSec.minusMoreThreeSixty(sunset, sunrise);

		var sunr = DegMinSec.toDegreesMoreThreeSixty(sunrise);
		int X = 0;

		if (kaala == Kaala.Raahu)
				X = raahu[day];
		else if (kaala == Kaala.Guli)
				X = guli[day];
		else 
			X = yama[day];
		duration = DegMinSec.toDegreesMoreThreeSixty(dayLength)/8;
		var res = sunr + duration * X;
		return DegMinSec.getGeoCoordsFromDegree(res);
	}
}
