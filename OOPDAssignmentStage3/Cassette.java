// FILE: Cassette.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Class to contain cassettes.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: None
// Last Mod: 11/10/2018
// Modification history:
//  None

public class Cassette extends Music
{
    //public class constants
    //all valid ranges are represented in the form [min, max)
    public static final int TRACK_NUMBER_MIN = 0;
    public static final int TRACK_NUMBER_MAX = 21;
    public static final double START_TIME_MIN = 0.0;
    public static final double START_TIME_MAX = 160.01;

    public static final String TRACK_NUMBER_ERROR = "Track number " +
        "must be a valid integer between 0 and 20 inclusive.";
    public static final String START_TIME_ERROR = "Start time must be a " +
        "valid time between 0.0 and 160.0 inclusive. " +
        "Check documentation.txt for more information.";
    public static final String SIDE_ERROR  = "The only supported cassette " +
        "sides are 'A' and 'B'. " +
        "Check documentation.txt for more information.";




    //private class constants
    //This is private, because final does not prevent the contents of arrays
    //  being modified in java.
    private static final char[] SIDE_VALID = { 'A', 'B' };




    //private class fields
    private int trackNumber;
    private double startTime;
    private char side;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORT: Address of the new Cassette object.
    //ASSERTION: name = "a", artist = "a", duration = 0.01, trackNumber = 0,
    //  startTime = 0.0, side = 'A'

    public Cassette()
    {
        super();
        trackNumber = TRACK_NUMBER_MIN;
        startTime = START_TIME_MIN;
        side = SIDE_VALID[0];
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inCassette (Cassette)
    //EXPORT: Address of the new Cassette object.
    //ASSERTION: The created cassette will have
    //  identical state to inCassette.

    public Cassette( Cassette inCassette )
    {
        //super constructor will detect for null
        super( inCassette );
        trackNumber = inCassette.getTrackNumber();
        startTime = inCassette.getStartTime();
        side = inCassette.getSide();
    }




    //ALTERNATE CONSTRUCTOR
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inTrackNumber (Integer), inStartTime (Real), inSide (Character)
    //EXPORT: Address of the new Cassette object.
    //ASSERTION: Creates the object if
    //  all imports are valid and FAILS otherwise.

    public Cassette( String inName, String inArtist, double inDuration,
        int inTrackNumber, double inStartTime, char inSide )
    {
        super( inName, inArtist, inDuration );

        char inSideUpper = Character.toUpperCase( inSide );
        validateCassette( inTrackNumber, inStartTime, inSideUpper );

        trackNumber = inTrackNumber;
        startTime = inStartTime;
        side = inSideUpper;
    }




    //SUBMODULE: CSV Constructor
    //IMPORTS: csv (String)
    //EXPORT: None
    //ASSERTIONS: Creates the object if the csv line is valid,
    //  and FAILS otherwise.

    public Cassette( String csv )
    {
        super();
        String[] data;
        double inStartTime;
        char inSideUpper;
        int inTrackNumber;
        String parseError = "";

        //ensure that we can read from the csv data
        if ( csv == null )
        {
            throw new IllegalArgumentException( "CSV data cannot be null." );
        }

        data = csv.split( "," );
        //ensure that we will not get any
        //  exceptions when accessing array elements.
        if ( data.length != 7 )
        {
            throw new IllegalArgumentException(
                "Incorrect number of columns." );
        }
        if ( !( data[0].equals( "C" ) || data[0].equals( "c" ) ) )
        {
            throw new IllegalArgumentException( "Incorrect music type." );
        }

        //ensure that the numbers are convertable.
        try
        {
            parseError = TRACK_NUMBER_ERROR;
            inTrackNumber = Integer.parseInt( data[4] );
            parseError = START_TIME_ERROR;
            inStartTime = Double.parseDouble( data[5] );
        }
        catch ( NumberFormatException ex )
        {
            throw new IllegalArgumentException( parseError );
        }

        //ensure that the side is convertable to a char
        if ( data[6].length() != 1 )
        {
            throw new IllegalArgumentException( SIDE_ERROR );
        }
        inSideUpper = Character.toUpperCase( data[6].charAt( 0 ) );

        validateCassette( inTrackNumber, inStartTime, inSideUpper );

        super.inputCSV( Music.subArrayCopy( data, 1, 4 ) );
        trackNumber = inTrackNumber;
        startTime = inStartTime;
        side = inSideUpper;
    }

    //PRIVATE CONSTRUCTOR FOR CSV


    //ACCESSORS

    //SUBMODULE: getTrackNumber
    //IMPORTS: None
    //EXPORT: trackNumber
    //ASSERTIONS: None

    public int getTrackNumber()
    {
        return trackNumber;
    }




    //SUBMODULE: getStartTime
    //IMPORTS: None
    //EXPORT: startTime
    //ASSERTIONS: None

    public double getStartTime()
    {
        return startTime;
    }




    //SUBMODULE: getSide
    //IMPORTS: None
    //EXPORT: side
    //ASSERTIONS: None

    public char getSide()
    {
        return side;
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




    //SUBMODULE: setStartTime
    //IMPORTS: inStartTime (Real)
    //EXPORT: None
    //ASSERTIONS: Sets startTime to inStartTime if valid, FAILS otherwise

    public void setStartTime( double inStartTime )
    {
        if ( !validateStartTime( inStartTime ) )
        {
            throw new IllegalArgumentException( START_TIME_ERROR );
        }
        startTime = inStartTime;
    }




    //SUBMODULE: setSide
    //IMPORTS: inSide (Character)
    //EXPORT: None
    //ASSERTIONS: Sets side to inSide if valid, FAILS otherwise

    public void setSide( char inSide )
    {
        char inSideUpper = Character.toUpperCase( inSide );
        if ( !validateSide( inSideUpper ) )
        {
            throw new IllegalArgumentException( SIDE_ERROR );
        }
        side = inSideUpper;
    }




    //SUBMODULE: equals
    //IMPORTS: inObj (Object)
    //EXPORT: same (Boolean)
    //ASSERTIONS: All private class fields must be equal
    //  for two cassettes to be equal.
    @Override
    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Cassette )
        {
            Cassette inCassette = (Cassette)inObj;
            same =  super.equals( inCassette ) &&
                    inCassette.getTrackNumber() == trackNumber &&
                    inCassette.getStartTime() == startTime &&
                    inCassette.getSide() == side;
        }

        return same;
    }




    //SUBMODULE: clone
    //IMPORTS: None
    //EXPORT: clone of cassette
    //ASSERTIONS: None

    public Cassette clone()
    {
        return new Cassette( this );
    }




    //SUBMODULE: toString
    //IMPORTS: None
    //EXPORT: cassetteString
    //ASSERTIONS: None
    @Override
    public String toString()
    {
        String cassetteString = super.toString() + ", track number: " +
            trackNumber + ", start time: " + startTime + ", side: " + side;
        return cassetteString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None
    @Override
    public String outputCSV()
    {
        String output = "C," + super.outputCSV() + "," +
            trackNumber + "," + String.format( "%.2f", startTime)
            + "," + side;
        return output;
    }




    //SUBMODULE: search
    //IMPORTS: inSide (String)
    //EXPORT: exists (Boolean)
    //ASSERTIONS: If inSide EQUALS side, exists = TRUE

    public boolean search( String inSide )
    {
        boolean exists = false;
        if ( inSide != null )
        {
            if ( inSide.length() == 1 )
            {
                exists = side == inSide.toUpperCase().charAt(0);
            }
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
            ", it starts at " + String.format( "%.2f", startTime ) +
            " minutes, on side " + side + ".";
        return playString;
    }




    //NAME: validateCassette
    //PURPOSE: Determines if cassette data is valid.
    //IMPORTS: inTrackNumber (Integer),
    //  inStartTime (Real), inSide (Character)
    //EXPORTS: None
    //ASSERTIONS: Throws if invalid

    private static void validateCassette( int inTrackNumber,
        double inStartTime, char inSide )
    {
        if ( !validateTrackNumber( inTrackNumber ) )
        {
            throw new IllegalArgumentException( TRACK_NUMBER_ERROR );
        }
        if ( !validateStartTime( inStartTime ) )
        {
            throw new IllegalArgumentException( START_TIME_ERROR );
        }
        if ( !validateSide( inSide ) )
        {
            throw new IllegalArgumentException( SIDE_ERROR );
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




    //NAME: validateStartTime
    //PURPOSE: Determines if a startTime is valid.
    //IMPORTS: Start time
    //EXPORTS: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the start time is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a cassette start time is
    //  defined in documentation.txt.

    private static boolean validateStartTime( double inStartTime )
    {
        return Music.validateTime(
            inStartTime, START_TIME_MIN, START_TIME_MAX );
    }




    //NAME: validateSide
    //PURPOSE: Determines if a side is valid.
    //IMPORTS: Cassette side
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the side is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a cassette side is defined in
    //  documentation.txt.

    private static boolean validateSide( char inSide )
    {
        boolean valid = false;
        //possible optimization could be done with short circuit evaluation
        //  after finding correct value, but not worth it with only
        //  2 elements
        for ( int i = 0; i != SIDE_VALID.length; i++ )
        {
            if ( inSide == SIDE_VALID[i] )
            {
                valid = true;
            }
        }
        return valid;
    }
}
