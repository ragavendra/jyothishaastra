### Panchaanga or Jyothi shaastra
I should called this Panchaanga as this deals with the calculations of Yoga, Waara (Day of week), Tithi (Day of month), Nakshatra (Star) and Raashi (Star sign) and more like Amrutha, Varjya, Durmuhurtha, Kaalas - all 3 and all.

This should work seamlessly for GMT or UT timezones. West of these TZs like for Vancouver, Canada say if the Tithi ends at [1, 43, 34] from GMT. Converting it to Pacific time means 7 or 8 hours from GMT. In that case the following day Tithi is valid for Vancouver. Similar issues with Yoga and others also can be witnessed.

The calculations were taken from a PDF document and most likely work correctly for TZs east of GMT like Sri Lanka or similar.

It is a lot of effort to make it complete but with manual intervention, calculations can be interpreted with precision.

I like to see like a horizontal time lapse scale representation that way, depending on the exact time one can know the acurate information for a TZ.

### Overview
One needs the Swiss ephermesis Sayyaana and remove Ayyana for Chandra and Soorya or the Sidereal ephermesis ( better ) with Niraayana for Panchaanga.

The Ephermesis have divided based on each Raashis for each planet mainly Chandra and Soorya for us making 30 degrees for each. Earth wrt Sun is in each raashi every month until all the raashis are traversed or a year is complete.
    Similarly the Moon wrt Earth is in each Raashi for about 2 days or Nakshatra (star) about a day or so until the month of about 29 - 30 days is complete.
    Refer to the NakshatraTest file for a sample of end to end usability. Date and lats of Moon and Sun is needed for the most. Lats of the location is needed to calculate the Sunrises/ sets which is used for calculating Kaalas.
    Degrees or Time is given in [ deg, min, sec ] format in many cases.

### Build or Running tests and app

```
./gradlew build
./gradlew run --args=fsd
```
