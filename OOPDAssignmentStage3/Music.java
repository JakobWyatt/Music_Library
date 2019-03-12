//FILENAME: Music.java
//AUTHOR: Jakob Wyatt
//PURPOSE: Abstract class for Music Objects.
//REFERENCES: None
//COMMENTS: None
//REQUIRES: None
//DATE CREATED: 24/10/2018

abstract public class Music
{
    //public class constants
    //all valid ranges are represented in the form [min, max)
    public static final double DURATION_MIN = 0.01;
    public static final double DURATION_MAX = 9.60;
    public static final int SECOND_MAX = 60;
    public static final String NAME_ERROR = "Names cannot be empty.";
    public static final String ARTIST_ERROR = "Artist names " +
        "cannot be empty.";
    public static final String DURATION_ERROR = "Duration must be a valid " +
        "time between 0.0 and 9.59 inclusive. " +
        "Check documentation.txt for more information.";




    //private class fields
    private String name;
    private String artist;
    private double duration;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORT:
    //ASSERTION: name = "a", artist = "a", duration = 0.01

    protected Music()
    {
        name = "a";
        artist = "a";
        duration = DURATION_MIN;
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inMusic (Music)
    //EXPORT:
    //ASSERTION: None

    protected Music( Music inMusic )
    {
        if ( inMusic == null )
        {
            throw new IllegalArgumentException( "Invalid Music object." );
        }
        name = inMusic.getName();
        artist = inMusic.getArtist();
        duration = inMusic.getDuration();
    }




    //ALTERNATE CONSTRUCTOR
    //IMPORTS: inName (String), inArtist (String), inDuration (Real)
    //EXPORT:
    //ASSERTION: None

    protected Music( String inName, String inArtist, double inDuration )
    {
        validateMusic( inName, inArtist, inDuration );
        name = inName;
        artist = inArtist;
        duration = inDuration;
    }




    //SUBMODULE: inputCSV
    //IMPORTS: csv (ARRAY OF String)
    //EXPORT: None
    //ASSERTIONS: Sets the object if the csv line is valid,
    //  and FAILS otherwise.

    protected void inputCSV( String[] csv )
    {
        //ensure that we can read from the csv data
        if ( csv == null )
        {
            throw new IllegalArgumentException( "CSV data cannot be null." );
        }
        for ( int i = 0; i != csv.length; i++ )
        {
            if ( csv[i] == null )
            {
                throw new IllegalArgumentException(
                    "CSV data cannot be null." );
            }
        }

        //ensure that we will not get any
        //  exceptions when accessing array elements.
        if ( csv.length != 3 )
        {
            throw new IllegalArgumentException(
                "Incorrect number of columns." );
        }
        //ensure that the numbers are convertable.
        try
        {
            duration = Double.parseDouble( csv[2] );
        }
        catch ( NumberFormatException ex )
        {
            throw new IllegalArgumentException( DURATION_ERROR );
        }

        validateMusic( csv[0], csv[1], duration );

        name = csv[0];
        artist = csv[1];
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




    //MUST BE OVERRIDDEN

    //SUBMODULE: equals
    //IMPORTS: inObj (Object)
    //EXPORT: same (Boolean)
    //ASSERTIONS: All private class fields must be equal for
    //  two music objects to be equal.

    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Music )
        {
            Music inMusic = (Music)inObj;
            same =  inMusic.getName().equals( name ) &&
                    inMusic.getArtist().equals( artist ) &&
                    inMusic.getDuration() == duration;
        }

        return same;
    }




    //SUBMODULE: toString
    //IMPORTS: None
    //EXPORT: musicString
    //ASSERTIONS: None

    public String toString()
    {
        String musicString = "name: " + name + ", artist: " + artist +
            ", duration: " + duration;
        return musicString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None

    public String outputCSV()
    {
        String output = name + "," + artist + "," +
            String.format( "%.2f", duration );
        return output;
    }




    //ABSTRACT METHODS
    public abstract Music clone();
    public abstract boolean search( String x );
    public abstract String play();




    //STATIC METHODS

    //NAME: addDuration
    //IMPORTS: x, y (Real)
    //EXPORT: sum (Real)
    //ASSERTIONS: sum will be the added durations of x and y

    public static double addDuration( double x, double y )
    {
        int minutes, seconds;
        double sum;

        minutes = (int)x + (int)y;
        seconds =   (int)( x * 100.0 ) % 100 +
                    (int)( y * 100.0 ) % 100;
        minutes += seconds / 60;
        seconds = seconds % 60;

        sum = (double)minutes + (double)seconds / 100.0;
        return sum;
    }




    //PROTECTED METHODS

    //NAME: validateTime
    //PURPOSE: Determines if a time between two ranges is valid.
    //IMPORTS: time, minTime, maxTime (Real)
    //EXPORT: valid (Boolean)
    //ASSERTIONS: The time is valid if seconds < SECOND_MAX
    //  and is in the range [minTime, maxTime)

    protected static boolean validateTime( double time, double minTime,
        double maxTime )
    {
        boolean valid = true;
        int seconds = (int)( time * 100.0 ) % 100;

        if ( time < minTime || time >= maxTime )
        {
            valid = false;
        }
        if ( seconds < 0 || seconds >= SECOND_MAX )
        {
            valid = false;
        }

        return valid;
    }




    //NAME: subArrayCopy
    //PURPOSE: Copies elements of an array into a sub-array, given by
    //  [low, high)
    //IMPORTS: arr (ARRAY OF String), low (Integer), high (Integer)
    //EXPORT: subArr (ARRAY OF String)
    //ASSERTIONS: FAILS if indicies are invalid, or if indexes are invalid.

    protected static String[] subArrayCopy( String[] arr, int low, int high )
    {
        String[] subArr;

        if ( arr == null )
        {
            throw new IllegalArgumentException( "Array cannot be null." );
        }
        if ( low < 0 || high > arr.length || high <= low )
        {
            throw new IllegalArgumentException( "Invalid array indexes." );
        }

        subArr = new String[high - low];
        for ( int i = low; i != high; i++ )
        {
            //String is immutable, therefore we do not perform a deep copy
            subArr[i - low] = arr[i];
        }

        return subArr;
    }




    //PRIVATE METHODS

    //NAME: validateMusic
    //PURPOSE: Determines if music data is valid.
    //IMPORTS: inName (String), inArtist (String), inDuration (Real)
    //EXPORTS: None
    //ASSERTIONS: FAILS if invalid

    private static void validateMusic(
        String inName, String inArtist, double inDuration )
    {
        boolean valid = true;
        String error = "";

        if ( !validateName( inName ) )
        {
            valid = false;
            error += NAME_ERROR;
        }
        if ( !validateArtist( inArtist ) )
        {
            valid = false;
            error += ARTIST_ERROR;
        }
        if ( !validateDuration( inDuration ) )
        {
            valid = false;
            error += DURATION_ERROR;
        }

        if ( !valid )
        {
            throw new IllegalArgumentException( error );
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
    //EXPORT: valid (Boolean)
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the duration is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a song duration is
    //  defined in documentation.txt.

    private static boolean validateDuration( double inDuration )
    {
        return validateTime( inDuration, DURATION_MIN, DURATION_MAX );
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
