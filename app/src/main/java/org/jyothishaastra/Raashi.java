package org.jyothishaastra;

import java.util.Calendar;

public class Raashi {
	static String rashis[] = { "Mesha", "Vrushabha", "Mithuna", "Karkataka", "Simha", "Kanya", "Thula", "Vrishika", "Dhanushu", "Makara", "Kumbha", "Meena" };

	private static int rashiIndex;

	// public static double (int[] chandraNiraayana){
	// raashi starting from 0 for Mesha
	public static String byWholeDegrees(int[] chandraWholeDeg, int raashi){
		rashiIndex = chandraWholeDeg[0]/12;
		return rashis[rashiIndex];
	}

    private static double chaDeg;
	static double remainingDistance;

	// raashi no. like Mesha is 1.
	public static String raashi(int chaNirAbs[]) throws Exception {

		chaDeg = DegMinSec.toDegrees(chaNirAbs);
        // System.out.printf("Chandra deg is %4.9f\n", chaDeg);

		if(chaDeg > 360)
			chaDeg = chaDeg - 360;

		int raashiSectSize = (360 / 12);

		// Each Karana is 12/2 == 6 degrees
		rashiIndex = (int) ( chaDeg / raashiSectSize );

		double endLimit = (rashiIndex + 1) * raashiSectSize;
		remainingDistance = endLimit - chaDeg;
		// System.out.println("Remaining " + remainingDistance);
		double elapsed = chaDeg % raashiSectSize;
        // tithiIndex = (int) Math.round(tithi);

		return "%s - %4.9f deg have elapsed".formatted(rashis[rashiIndex], elapsed);
	}


}
