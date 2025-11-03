package org.jyothishaastra;

public class DegMinSec {

	// res is in "ddd.mmss" format
	// get in degrees
	public static double toDegrees(int degree, int minutes, int seconds){
        int abs = Math.abs(degree);
/* 
        if(degree == 0) {
			// System.out.println("deg is 0");
            degree = 1;
		}
*/
		double res = Math.signum(degree) * (abs + (minutes / 60.0) + (seconds / 3600.0));

		if(res > 360.0)
			res = res - 360.0;

		return 	res;
	}

	public static double toDegreesMoreThreeSixty(int degree, int minutes, int seconds){
        if(degree == 0) {
			// System.out.println("deg is 0");
            degree = 1;
		}
		int abs = Math.abs(degree);
		double res = Math.signum(degree) * (abs + (minutes / 60.0) + (seconds / 3600.0));

		return 	res;
	}

	public static double toDegreesMoreThreeSixty(int arr[]){
		return toDegreesMoreThreeSixty(arr[0], arr[1], arr[2]);
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
	public static int[] getGeoCoordsFromDegree(double resDegrees) {
		/* 
		   boolean flag = false;
		   if(resDegrees < 0.0){
		   flag = true;
		   resDegrees = -1 * resDegrees;
		   }
		   */
		if(resDegrees < 0.0)
			resDegrees = 360.0 + resDegrees;

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
		/* 
		   if(flag){
		   d = 360 - d;
		   }
		   */

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

	public static int[] minusMoreThreeSixty(int dms1[], int dms2[]) {
		// System.out.println("dms1 is " + Arrays.toString(dms1));	
		// System.out.println("dms2 is " + Arrays.toString(dms2));	
		double d1 = toDegreesMoreThreeSixty(dms1);
		double d2 = toDegreesMoreThreeSixty(dms2);

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
}
