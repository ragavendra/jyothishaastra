package org.jyothishaastra;

import java.util.Calendar;

// half of a Tithi or a day
public class Karana {

	// 60 of them
	static String karanas[] = { "Kimstugna","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Bava","Baalava","Kauvala","Taitila","Gara","Vanija","Vishti(Bhadra)","Sakuna","Chatushpada","Naga" };

	private static int karanaIndex;
    private static double karanaDeg;
	static double remainingDistance;

	// public static double (int[] chandraNiraayana){
	// raashi starting from 0 for Mesha
	public static String byWholeDegrees(int[] chandraWholeDeg, int raashi){
		karanaIndex = chandraWholeDeg[0]/60;
		return karanas[karanaIndex];
	}

	// raashi no. like Mesha is 1.
	public static String karana(int chandraAbs[], int sooryaAbs[]) throws Exception {
		// Karana like Thithi = Chandra - Soorya Sayaana or Nirayaana

		// if Chandra is less than Soorya, add 360 to it
		if(chandraAbs[0] < sooryaAbs[0])
			chandraAbs[0] = chandraAbs[0] + 360;

		karanaDeg = DegMinSec.toDegrees(DegMinSec.minus(sooryaAbs, chandraAbs));
        // System.out.printf("Karana deg is %4.9f\n", karanaDeg);

		// Each Karana is 12/2 == 6 degrees
		karanaIndex = (int) ( karanaDeg / 6 );

		double endLimit = ( karanaIndex + 1 ) * 6;
		remainingDistance = endLimit - karanaDeg;
		// System.out.println("Remaining " + remainingDistance);
		double elapsed = karanaDeg % 6;
        // tithiIndex = (int) Math.round(tithi);

		return "%s - %4.9f deg have elapsed".formatted(karanas[karanaIndex], elapsed);
	}

}
