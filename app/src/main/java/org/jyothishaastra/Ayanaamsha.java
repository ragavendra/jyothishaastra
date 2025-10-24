package org.jyothishaastra;

import java.util.Calendar;

public class Ayanaamsha {
	public static double ayanamsha(Calendar cal){
		int year = cal.get(Calendar.YEAR);
		// System.out.println("year is " + year);	
		double A = -6.92416f + 16.90709 * (year/1000.0) - 0.757371 * Math.pow((year/ 1000.0), 2);
		// System.out.println("A is " + A);	
		// double B = (cal.get(Calendar.MONTH) - 1 + cal.get(Calendar.DATE)/30.0) * 0.0011574074;
		// month in Java starts from 0, lol
		double B = (cal.get(Calendar.MONTH) + 1 - 1 + cal.get(Calendar.DATE)/30.0) * 0.0011574074;
		// System.out.println("B is " + B);	
		return A + B;
	}

	// Mesha is Raashi 1
	public static double nirayaana(double ayanamsha, int[] grahaAbsGeo) throws Exception {

		// add 360 if less than ayanamsha
		if(DegMinSec.toDegrees(grahaAbsGeo) < ayanamsha)
			grahaAbsGeo[0] = grahaAbsGeo[0] + 360;

		// System.out.println("Deg is " + DegMinSec.degrees(grahaGeo));
		double niraGraha = DegMinSec.toDegrees(grahaAbsGeo) - ayanamsha;
		return niraGraha;
	}
}


