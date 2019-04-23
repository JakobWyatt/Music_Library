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

public class Record extends Music
{
    //public class constants
    //all valid ranges are represented in the form [min, max)
    public static final int TRACK_NUMBER_MIN = 0;
    public static final int TRACK_NUMBER_MAX = 21;
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
    private int trackNumber;
    private String playSpeed;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORT: Address of the new Record object.
    //ASSERTION: name = "a", artist = "a", duration = 0.01, trackNumber = 0,
    //  playSpeed = "33 1/3 RPM"

    public Record()
    {
        super();
        trackNumber = TRACK_NUMBER_MIN;
        playSpeed = PLAY_SPEED_VALID[0];
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inRecord (Record)
    //EXPORT: Address of the new Record object.
    //ASSERTION: The created record will have identical state to inRecord.

    public Record( Record inRecord )
    {
        //super constructor detects for null
        super( inRecord );
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
        super( inName, inArtist, inDuration );

        if ( inPlaySpeed == null )
        {
            throw new IllegalArgumentException( PLAY_SPEED_ERROR );
        }
        String inPlaySpeedUpper = inPlaySpeed.toUpperCase();

        validateRecord( inTrackNumber, inPlaySpeedUpper );

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
        super();
        String[] data;
        int inTrackNumber;
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
            inTrackNumber = Integer.parseInt( data[4] );
        }
        catch ( NumberFormatException ex )
        {
            throw new IllegalArgumentException( TRACK_NUMBER_ERROR );
        }

        inPlaySpeedUpper = data[5].toUpperCase();

        validateRecord( inTrackNumber, inPlaySpeedUpper );

        super.inputCSV( Music.subArrayCopy( data, 1, 4 ) );
        trackNumber = inTrackNumber;
        playSpeed = inPlaySpeedUpper;
    }




    //ACCESSORS

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
    @Override
    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Record )
        {
            Record inRecord = (Record)inObj;
            same =  super.equals( inRecord ) &&
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
    @Override
    public String toString()
    {
        String recordString = super.toString() + ", track number: " +
            trackNumber +
            ", play speed: " + playSpeed;
        return recordString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None
    @Override
    public String outputCSV()
    {
        String output = "R," + super.outputCSV() + "," +
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
        String playString = super.getName() + " is track number " +
            trackNumber +
            " and the record is to be played at " + playSpeed + ".";
        return playString;
    }




    //NAME: validateRecord
    //PURPOSE: Determines if record data is valid.
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inTrackNumber (Integer), inPlaySpeed (String)
    //EXPORTS: None
    //ASSERTIONS: Throws if invalid

    private static void validateRecord( int inTrackNumber,
        String inPlaySpeed )
    {
        if ( !validateTrackNumber( inTrackNumber ) )
        {
            throw new IllegalArgumentException( TRACK_NUMBER_ERROR );
        }
        if ( !validatePlaySpeed( inPlaySpeed ) )
        {
            throw new IllegalArgumentException( PLAY_SPEED_ERROR );
        }
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
}
