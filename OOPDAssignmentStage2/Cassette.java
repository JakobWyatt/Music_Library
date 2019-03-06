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

public class Cassette
{
    //public class constants
    //all valid ranges are represented in the form [min, max)
    public static final int TRACK_NUMBER_MIN = 0;
    public static final int TRACK_NUMBER_MAX = 21;
    public static final double DURATION_MIN = 0.01;
    public static final double DURATION_MAX = 9.60;
    public static final double START_TIME_MIN = 0.0;
    public static final double START_TIME_MAX = 160.01;
    public static final int SECOND_MAX = 60;

    public static final String NAME_ERROR = "Names cannot be empty.";
    public static final String ARTIST_ERROR = "Artist names " +
        "cannot be empty.";
    public static final String DURATION_ERROR = "Duration must be a valid " +
        "time between 0.0 and 9.59 inclusive. " +
        "Check documentation.txt for more information.";
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
    private String name;
    private String artist;
    private double duration;
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
        name = "a";
        artist = "a";
        duration = 0.01;
        trackNumber = 0;
        startTime = 0.0;
        side = SIDE_VALID[0];
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inCassette (Cassette)
    //EXPORT: Address of the new Cassette object.
    //ASSERTION: The created cassette will have
    //  identical state to inCassette.

    public Cassette( Cassette inCassette )
    {
        if ( inCassette == null )
        {
            throw new IllegalArgumentException( "Invalid Cassette." );
        }
        name = inCassette.getName();
        artist = inCassette.getArtist();
        duration = inCassette.getDuration();
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
        char inSideUpper = Character.toUpperCase( inSide );

        validateCassette( inName, inArtist, inDuration,
            inTrackNumber, inStartTime, inSideUpper );

        name = inName;
        artist = inArtist;
        duration = inDuration;
        trackNumber = inTrackNumber;
        startTime = inStartTime;
        side = inSideUpper;
    }




    //CSV CONSTRUCTOR
    //IMPORTS: csv (String)
    //EXPORT: Address of the new cassette object.
    //ASSERTIONS: Creates the object if the csv line is valid,
    //  and FAILS otherwise.

    public Cassette( String csv )
    {
        String[] data;
        double inDuration, inStartTime;
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
            parseError = DURATION_ERROR;
            inDuration = Double.parseDouble( data[3] );
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

        validateCassette( data[1], data[2], inDuration, inTrackNumber,
            inStartTime, inSideUpper );

        name = data[1];
        artist = data[2];
        duration = inDuration;
        trackNumber = inTrackNumber;
        startTime = inStartTime;
        side = inSideUpper;
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

    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Cassette )
        {
            Cassette inCassette = (Cassette)inObj;
            same =  inCassette.getName().equals( name ) &&
                    inCassette.getName().equals( artist )&&
                    inCassette.getDuration() == duration &&
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

    public String toString()
    {
        String cassetteString = "name: " + name + ", artist: " + artist +
            ", duration: " + duration + ", track number: " + trackNumber +
            ", start time: " + startTime + ", side: " + side;
        return cassetteString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None

    public String outputCSV()
    {
        String output = "C," + name + "," + artist + "," +
            String.format( "%.2f", duration ) + "," +
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
        String playString = name + " is track number " + trackNumber +
            ", it starts at " + String.format( "%.2f", startTime ) +
            " minutes, on side " + side + ".";
        return playString;
    }




    //SUBMODULE: addDuration
    //IMPORTS: duration (Double)
    //EXPORT: The cassettes duration plus the imported duration (Double).
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




    //NAME: validateCassette
    //PURPOSE: Determines if cassette data is valid.
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inTrackNumber (Integer), inStartTime (Real), inSide (Character)
    //EXPORTS: None
    //ASSERTIONS: Throws if invalid

    private static void validateCassette( String inName, String inArtist,
        double inDuration, int inTrackNumber,
        double inStartTime, char inSide )
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
        if ( !validateStartTime( inStartTime ) )
        {
            throw new IllegalArgumentException( START_TIME_ERROR );
        }
        if ( !validateSide( inSide ) )
        {
            throw new IllegalArgumentException( SIDE_ERROR );
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
        boolean valid = true;
        int seconds = (int)( inStartTime * 100.0 ) % 100;

        if ( inStartTime < START_TIME_MIN || inStartTime >= START_TIME_MAX )
        {
            valid = false;
        }
        if ( seconds < 0 || seconds >= SECOND_MAX )
        {
            valid = false;
        }

        return valid;
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
