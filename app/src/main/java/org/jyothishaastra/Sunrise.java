package org.jyothishaastra;

import java.util.Calendar;
import java.util.TimeZone;

// half of a Tithi or a day
public class Sunrise {

	static Calendar sunrise;
	static Calendar sunset;
	static double duration;

	static double j2ts(double j){
		return (j - 2440587.5) * 86400;
	}

	static double ts2Julian(double ts){
		return (ts / 86400.0) + 2440587.5;
	}

	static void calc(Calendar date, double f, double l_w, String debugtz){
		double elevation = 0.0;

		// System.out.printf("Now %s\n", date.getTime());

		var J_date = ts2Julian(date.getTimeInMillis()/1000);
		// System.out.printf("Julian date            j_date  = %8.9f days\n", J_date);

		// # Julian day
		var n = Math.ceil(J_date - (2451545.0 + 0.0009) + 69.184 / 86400.0);
		// System.out.printf("Julian day             n       = %8.9f days\n", n);

		var J_ = n + 0.0009 - l_w / 360.0;
		// System.out.printf ("Mean solar time        J_      = %8.9f days\n", J_);

		// # Solar mean anomaly
		var M_degrees = (357.5291 + 0.98560028 * J_)% 360.0;
		// System.out.printf ("Solar mean anomaly     M       = %8.9f\n", M_degrees);

		double toRads = (22.0/7.0) * (1.0/180.0);

		var M_radians = M_degrees * toRads;

		// # Equation of the center
		var C_degrees = 1.9148 * Math.sin(M_radians) + 0.02 * Math.sin(2 * M_radians) + 0.0003 * Math.sin(3 * M_radians);
		// System.out.printf("Equation of the center C       = %8.9f\n", C_degrees);

		// # Ecliptic longitude
		var L_degrees = (M_degrees + C_degrees + 180.0 + 102.9372)% 360.0;
		// System.out.printf("Ecliptic longitude     L       = %8.9f\n", L_degrees);

		var Lambda_radians = L_degrees * toRads;

		// # Solar transit (Julian date)
		var J_transit = (2451545.0 + J_ + 0.0053 * Math.sin(M_radians) - 0.0069 * Math.sin(2 * Lambda_radians));
		// System.out.printf("Solar transit time     J_trans = %8.9f\n", J_transit);

		// # Declination of the Sun
		var sin_d = Math.sin(Lambda_radians) * Math.sin(23.4397 * toRads);

		// # cos_d = sqrt(1-sin_d**2) # exactly the same precision, but 1.5 times slower
		var cos_d = Math.cos(Math.asin(sin_d));

		// # Hour angle
		var some_cos = (Math.sin((-0.833 - 2.076 * Math.sqrt(elevation) / 60.0) * toRads) - Math.sin(f * toRads) * sin_d) / (Math.cos(f * toRads) * cos_d);

		// has try catch here
		var w0_radians = Math.acos(some_cos);

		double toDegrees = (7.0/22.0) * 180.0;
		var w0_degrees = w0_radians * toDegrees;  //  0...180
		// System.out.printf("Hour angle             w0      = %8.9f\n", w0_degrees);

		var j_rise = J_transit - w0_degrees / 360.0;
		var j_set = J_transit + w0_degrees / 360.0;

		var sr = j2ts(j_rise);
		sunrise = Calendar.getInstance();
		sunrise.setTimeInMillis((long) sr * 1000);
		System.out.printf("Sunrise at: %s\n", sunrise.getTime());

		var ss = j2ts(j_set);
		sunset = Calendar.getInstance();
		sunset.setTimeInMillis((long) ss * 1000);
		System.out.printf("Sunset at: %s\n", sunset.getTime());
		duration = w0_degrees / (180.0 / 24.0);
		System.out.printf("Day length: %8.9f hours\n", duration);
	}
}
