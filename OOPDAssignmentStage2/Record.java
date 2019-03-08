// FILE: Record.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Class to contain records.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: None
// Last Mod: 10/10/2018
// Modification history:
//  None

public class Record
{
    //public class constants
    //all valid ranges are represented in the form [min, max)
    public static final int TRACK_NUMBER_MIN = 0;
    public static final int TRACK_NUMBER_MAX = 21;
    public static final double DURATION_MIN = 0.01;
    public static final double DURATION_MAX = 9.60;
    public static final int SECOND_MAX = 60;

    public static final String NAME_ERROR = "Names cannot be empty.";
    public static final String ARTIST_ERROR = "Artist names " +
        "cannot be empty.";
    public static final String DURATION_ERROR = "Duration must be a valid " +
        "time between 0.0 and 9.59 inclusive. " +
        "Check documentation.txt for more information.";
    public static final String TRACK_NUMBER_ERROR = "Track number " +
        "must be a valid integer between 0 and 20 inclusive.";
    public static final String PLAY_SPEED_ERROR = "The only supported " +
        "play speeds are '33 1/3 RPM', '45 RPM', and '78 RPM'. " +
        "Check documentation.txt for more information.";




    //private class constants
    //This is private, because final does not prevent the contents of arrays
    //  being modified in java.
    private static final String[] PLAY_SPEED_VALID =
        { "33 1/3 RPM", "45 RPM", "78 RPM" };




    //private class fields
    private String name;
    private String artist;
    private double duration;
    private int trackNumber;
    private String playSpeed;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORT: Address of the new Record object.
    //ASSERTION: name = "a", artist = "a", duration = 0.01, trackNumber = 0,
    //  playSpeed = "33 1/3 RPM"

    public Record()
    {
        name = "a";
        artist = "a";
        duration = 0.01;
        trackNumber = 0;
        playSpeed = PLAY_SPEED_VALID[0];
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inRecord (Record)
    //EXPORT: Address of the new Record object.
    //ASSERTION: The created record will have identical state to inRecord.

    public Record( Record inRecord )
    {
        if ( inRecord == null )
        {
            throw new IllegalArgumentException( "Invalid Record." );
        }
        name = inRecord.getName();
        artist = inRecord.getArtist();
        duration = inRecord.getDuration();
        trackNumber = inRecord.getTrackNumber();
        playSpeed = inRecord.getPlaySpeed();
    }




    //ALTERNATE CONSTRUCTOR
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inTrackNumber (Integer), inPlaySpeed (String)
    //EXPORT: Address of the new Record object.
    //ASSERTION: Creates the object if
    //  all imports are valid and FAILS otherwise.

    public Record( String inName, String inArtist, double inDuration,
        int inTrackNumber, String inPlaySpeed )
    {
        if ( inPlaySpeed == null )
        {
            throw new IllegalArgumentException( PLAY_SPEED_ERROR );
        }
        String inPlaySpeedUpper = inPlaySpeed.toUpperCase();

        validateRecord( inName, inArtist, inDuration,
            inTrackNumber, inPlaySpeedUpper );

        name = inName;
        artist = inArtist;
        duration = inDuration;
        trackNumber = inTrackNumber;
        playSpeed = inPlaySpeedUpper;
    }




    //CSV CONSTRUCTOR
    //IMPORTS: csv (String)
    //EXPORT: Address of the new record object.
    //ASSERTIONS: Creates the object if the csv line is valid,
    //  and FAILS otherwise.

    public Record( String csv )
    {
        String[] data;
        double inDuration;
        int inTrackNumber;
        String parseError = "";
        String inPlaySpeedUpper;

        //ensure that we can read from the csv data
        if ( csv == null )
        {
            throw new IllegalArgumentException( "CSV data cannot be null." );
        }

        data = csv.split( "," );
        //ensure that we will not get any
        //  exceptions when accessing array elements.
        if ( data.length != 6 )
        {
            throw new IllegalArgumentException(
                "Incorrect number of columns." );
        }
        if ( !( data[0].equals( "R" ) || data[0].equals( "r" ) ) )
        {
            throw new IllegalArgumentException( "Incorrect music type." );
        }
        //ensure that the numbers are convertable.
        try
        {
            parseError = DURATION_ERROR;
            inDuration = Double.parseDouble( data[3] );
            parseError = TRACK_NUMBER_ERROR;
            inTrackNumber = Integer.parseInt( data[4] );
        }
        catch ( NumberFormatException ex )
        {
            throw new IllegalArgumentException( parseError );
        }

        inPlaySpeedUpper = data[5].toUpperCase();

        validateRecord( data[1], data[2], inDuration, inTrackNumber,
            inPlaySpeedUpper );

        name = data[1];
        artist = data[2];
        duration = inDuration;
        trackNumber = inTrackNumber;
        playSpeed = inPlaySpeedUpper;
    }




    //ACCESSORS

    //SUBMODULE: getName
    //IMPORTS: None
    //EXPORT: name
    //ASSERTIONS: None

    public String getName()
    {
        return name;
    }




    //SUBMODULE: getArtist
    //IMPORTS: None
    //EXPORT: artist
    //ASSERTIONS: None

    public String getArtist()
    {
        return artist;
    }




    //SUBMODULE: getDuration
    //IMPORTS: None
    //EXPORT: duration
    //ASSERTIONS: None

    public double getDuration()
    {
        return duration;
    }




    //SUBMODULE: getTrackNumber
    //IMPORTS: None
    //EXPORT: trackNumber
    //ASSERTIONS: None

    public int getTrackNumber()
    {
        return trackNumber;
    }




    //SUBMODULE: getPlaySpeed
    //IMPORTS: None
    //EXPORT: playSpeed
    //ASSERTIONS: None

    public String getPlaySpeed()
    {
        return playSpeed;
    }




    //MUTATORS

    //SUBMODULE: setName
    //IMPORTS: inName (String)
    //EXPORT: None
    //ASSERTIONS: Sets name to inName if valid, FAILS otherwise

    public void setName( String inName )
    {
        if ( !validateName( inName ) )
        {
            throw new IllegalArgumentException( NAME_ERROR );
        }
        name = inName;
    }




    //SUBMODULE: setArtist
    //IMPORTS: inArtist (String)
    //EXPORT: None
    //ASSERTIONS: Sets artist to inArtist if valid, FAILS otherwise

    public void setArtist( String inArtist )
    {
        if ( !validateArtist( inArtist ) )
        {
            throw new IllegalArgumentException( ARTIST_ERROR );
        }
        artist = inArtist;
    }




    //SUBMODULE: setDuration
    //IMPORTS: inDuration (Real)
    //EXPORT: None
    //ASSERTIONS: Sets duration to inDuration if valid, FAILS otherwise

    public void setDuration( double inDuration )
    {
        if ( !validateDuration( inDuration ) )
        {
            throw new IllegalArgumentException( DURATION_ERROR );
        }
        duration = inDuration;
    }




    //SUBMODULE: setTrackNumber
    //IMPORTS: inTrackNumber (Integer)
    //EXPORT: None
    //ASSERTIONS: Sets trackNumber to inTrackNumber if valid, FAILS otherwise

    public void setTrackNumber( int inTrackNumber )
    {
        if ( !validateTrackNumber( inTrackNumber ) )
        {
            throw new IllegalArgumentException( TRACK_NUMBER_ERROR );
        }
        trackNumber = inTrackNumber;
    }




    //SUBMODULE: setPlaySpeed
    //IMPORTS: inPlaySpeed (String)
    //EXPORT: None
    //ASSERTIONS: Sets playSpeed to inPlaySpeed if valid, FAILS otherwise

    public void setPlaySpeed( String inPlaySpeed )
    {
        if ( inPlaySpeed == null )
        {
            throw new IllegalArgumentException( PLAY_SPEED_ERROR );
        }
        String inPlaySpeedUpper = inPlaySpeed.toUpperCase();
        if ( !validatePlaySpeed( inPlaySpeedUpper ) )
        {
            throw new IllegalArgumentException( PLAY_SPEED_ERROR );
        }
        playSpeed = inPlaySpeedUpper;
    }




    //SUBMODULE: equals
    //IMPORTS: inObj (Object)
    //EXPORT: same (Boolean)
    //ASSERTIONS: All private class fields must be equal
    //  for two records to be equal.

    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Record )
        {
            Record inRecord = (Record)inObj;
            same =  inRecord.getName().equals( name ) &&
                    inRecord.getName().equals( artist )&&
                    inRecord.getDuration() == duration &&
                    inRecord.getTrackNumber() == trackNumber &&
                    inRecord.getPlaySpeed().equals( playSpeed );
        }

        return same;
    }




    //SUBMODULE: clone
    //IMPORTS: None
    //EXPORT: clone of record
    //ASSERTIONS: None

    public Record clone()
    {
        return new Record( this );
    }




    //SUBMODULE: toString
    //IMPORTS: None
    //EXPORT: recordString
    //ASSERTIONS: None

    public String toString()
    {
        String recordString = "name: " + name + ", artist: " + artist +
            ", duration: " + duration + ", track number: " + trackNumber +
            ", play speed: " + playSpeed;
        return recordString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None

    public String outputCSV()
    {
        String output = "R," + name + "," + artist + "," +
            String.format( "%.2f", duration ) + "," +
            trackNumber + "," + playSpeed;
        return output;
    }




    //SUBMODULE: search
    //IMPORTS: inPlaySpeed (String)
    //EXPORT: exists (Boolean)
    //ASSERTIONS: If playSpeed EQUALS inPlaySpeed, exists = TRUE

    public boolean search( String inPlaySpeed )
    {
        boolean exists = false;
        if ( inPlaySpeed != null )
        {
            exists = inPlaySpeed.toUpperCase().equals( playSpeed );
        }
        return exists;
    }




    //SUBMODULE: play
    //IMPORTS: None
    //EXPORT: playString (String)
    //ASSERTIONS: None

    public String play()
    {
        String playString = name + " is track number " + trackNumber +
            " and the record is to be played at " + playSpeed + ".";
        return playString;
    }




    //SUBMODULE: addDuration
    //IMPORTS: duration (Double)
    //EXPORT: The records duration plus the imported duration (Double).
    //ASSERTIONS: Exports a valid duration.

    public double addDuration( double inDuration )
    {
        int minutes, seconds;
        double durationSum;

        minutes = (int)duration + (int)inDuration;
        seconds =   (int)( duration * 100.0 ) % 100 +
                    (int)( inDuration * 100.0 ) % 100;
        minutes += seconds / 60;
        seconds = seconds % 60;

        durationSum = (double)minutes + (double)seconds / 100.0;
        return durationSum;
    }




    //NAME: validateRecord
    //PURPOSE: Determines if record data is valid.
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inTrackNumber (Integer), inPlaySpeed (String)
    //EXPORTS: None
    //ASSERTIONS: Throws if invalid

    private static void validateRecord( String inName, String inArtist,
        double inDuration, int inTrackNumber, String inPlaySpeed )
    {
        if ( !validateName( inName ) )
        {
            throw new IllegalArgumentException( NAME_ERROR );
        }
        if ( !validateArtist( inArtist ) )
        {
            throw new IllegalArgumentException( ARTIST_ERROR );
        }
        if ( !validateDuration( inDuration ) )
        {
            throw new IllegalArgumentException( DURATION_ERROR );
        }
        if ( !validateTrackNumber( inTrackNumber ) )
        {
            throw new IllegalArgumentException( TRACK_NUMBER_ERROR );
        }
        if ( !validatePlaySpeed( inPlaySpeed ) )
        {
            throw new IllegalArgumentException( PLAY_SPEED_ERROR );
        }
    }




    //NAME: validateName
    //PURPOSE: Determines if a song name is valid.
    //IMPORTS: Song name
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the song name is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a song name is defined in documentation.txt.

    private static boolean validateName( String inName )
    {
        boolean valid = !nullOrEmpty( inName );
        return valid;
    }




    //NAME: validateArtist
    //PURPOSE: Determines if an artist's name is valid.
    //IMPORTS: Artist name
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the artist name is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of an artist name is
    //  defined in documentation.txt.

    private static boolean validateArtist( String inArtist )
    {
        boolean valid = !nullOrEmpty( inArtist );
        return valid;
    }




    //NAME: validateDuration
    //PURPOSE: Determines if a duration is valid.
    //IMPORTS: Song duration
    //EXPORTS: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the duration is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a song duration is
    //  defined in documentation.txt.

    private static boolean validateDuration( double inDuration )
    {
        boolean valid = true;
        int seconds = (int)( inDuration * 100.0 ) % 100;

        if ( inDuration < DURATION_MIN || inDuration >= DURATION_MAX )
        {
            valid = false;
        }
        if ( seconds < 0 || seconds >= SECOND_MAX )
        {
            valid = false;
        }

        return valid;
    }




    //NAME: validateTrackNumber
    //PURPOSE: Determines if a track number is valid.
    //IMPORTS: Track number
    //EXPORTS: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the trackNumber is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a track number is
    //  defined in documentation.txt.

    private static boolean validateTrackNumber( int inTrackNumber )
    {
        boolean valid = true;
        if (    inTrackNumber < TRACK_NUMBER_MIN ||
                inTrackNumber >= TRACK_NUMBER_MAX )
        {
            valid = false;
        }

        return valid;
    }




    //NAME: validatePlaySpeed
    //PURPOSE: Determines if a play speed is valid.
    //IMPORTS: Record play speed
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the play speed is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a play speed is defined in documentation.txt.

    private static boolean validatePlaySpeed( String inPlaySpeed )
    {
        boolean valid = false;
        //possible optimization could be done with short circuit evaluation
        //  after finding correct value, but not worth it with only
        //  3 elements
        for ( int i = 0; i != PLAY_SPEED_VALID.length; i++ )
        {
            if ( inPlaySpeed.equals( PLAY_SPEED_VALID[i] ) )
            {
                valid = true;
            }
        }
        return valid;
    }




    //NAME: nullOrEmpty
    //PURPOSE: Determines if a string is either null or empty.
    //IMPORTS: x
    //EXPORT: Null or Empty
    //Assertions:
    //  Pre: None
    //  Post: If the string is either null or empty, return true.
    //          Otherwise, return false.
    //REMARKS: None

    private static boolean nullOrEmpty( String x )
    {
        boolean isNullOrEmpty = false;
        if ( x == null )
        {
            isNullOrEmpty = true;
        }
        else
        {
            if ( x.length() == 0 )
            {
                isNullOrEmpty = true;
            }
        }

        return isNullOrEmpty;
    }
}
