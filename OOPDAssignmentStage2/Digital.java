// FILE: Digital.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Class to contain digital music files.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: None
// Last Mod: 11/10/2018
// Modification history:
//  None

public class Digital
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
    public static final String TYPE_ERROR = "The only supported digital " +
        "music file types are 'wav', 'mp3', and 'acc'. " +
        "Check documentation.txt for more information.";




    //private class constants
    //This is private, because final does not prevent the contents of arrays
    //  being modified in java.
    private static final String[] TYPE_VALID =
        { "wav", "mp3", "acc" };




    //private class fields
    private String name;
    private String artist;
    private double duration;
    private String type;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORT: Address of the new Digital object.
    //ASSERTION: name = "a", artist = "a", duration = 0.01,
    //  type = "wav"

    public Digital()
    {
        name = "a";
        artist = "a";
        duration = 0.01;
        type = TYPE_VALID[0];
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inDigital (Digital)
    //EXPORT: Address of the new Digital object.
    //ASSERTION: The created digital music file will have
    //  identical state to inDigital.

    public Digital( Digital inDigital )
    {
        if ( inDigital == null )
        {
            throw new IllegalArgumentException( "Invalid Digital." );
        }
        name = inDigital.getName();
        artist = inDigital.getArtist();
        duration = inDigital.getDuration();
        type = inDigital.getType();
    }




    //ALTERNATE CONSTRUCTOR
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inType (String)
    //EXPORT: Address of the new Digital object.
    //ASSERTION: Creates the object if
    //  all imports are valid and FAILS otherwise.

    public Digital( String inName, String inArtist, double inDuration,
        String inType )
    {
        if ( inType == null )
        {
            throw new IllegalArgumentException( TYPE_ERROR );
        }
        String inTypeLower = inType.toLowerCase();

        validateDigital( inName, inArtist, inDuration, inTypeLower );

        name = inName;
        artist = inArtist;
        duration = inDuration;
        type = inTypeLower;
    }




    //CSV CONSTRUCTOR
    //IMPORTS: csv (String)
    //EXPORT: Address of the new digital object.
    //ASSERTIONS: Creates the object if the csv line is valid,
    //  and FAILS otherwise.

    public Digital( String csv )
    {
        String[] data;
        double inDuration;
        String typeLower;

        //ensure that we can read from the csv data
        if ( csv == null )
        {
            throw new IllegalArgumentException( "CSV data cannot be null." );
        }

        data = csv.split( "," );
        //ensure that we will not get any
        //  exceptions when accessing array elements.
        if ( data.length != 5 )
        {
            throw new IllegalArgumentException(
                "Incorrect number of columns." );
        }
        if ( !( data[0].equals( "D" ) || data[0].equals( "d" ) ) )
        {
            throw new IllegalArgumentException( "Incorrect music type." );
        }
        //ensure that the duration is convertable to a double.
        try
        {
            inDuration = Double.parseDouble( data[3] );
        }
        catch ( NumberFormatException ex )
        {
            throw new IllegalArgumentException( DURATION_ERROR );
        }
        typeLower = data[4].toLowerCase();

        validateDigital( data[1], data[2], inDuration, typeLower );

        name = data[1];
        artist = data[2];
        duration = inDuration;
        type = typeLower;
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




    //SUBMODULE: getType
    //IMPORTS: None
    //EXPORT: type
    //ASSERTIONS: None

    public String getType()
    {
        return type;
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




    //SUBMODULE: setType
    //IMPORTS: inType (String)
    //EXPORT: None
    //ASSERTIONS: Sets type to inType if valid, FAILS otherwise

    public void setType( String inType )
    {
        if ( inType == null )
        {
            throw new IllegalArgumentException( TYPE_ERROR );
        }
        String inTypeLower = inType.toLowerCase();
        if ( !validateType( inTypeLower ) )
        {
            throw new IllegalArgumentException( TYPE_ERROR );
        }
        type = inTypeLower;
    }




    //SUBMODULE: equals
    //IMPORTS: inObj (Object)
    //EXPORT: same (Boolean)
    //ASSERTIONS: All private class fields must be equal
    //  for two digital music files to be equal.

    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Digital )
        {
            Digital inDigital = (Digital)inObj;
            same =  inDigital.getName().equals( name ) &&
                    inDigital.getName().equals( artist )&&
                    inDigital.getDuration() == duration &&
                    inDigital.getType().equals( type );
        }

        return same;
    }




    //SUBMODULE: clone
    //IMPORTS: None
    //EXPORT: clone of digital
    //ASSERTIONS: None

    public Digital clone()
    {
        return new Digital( this );
    }




    //SUBMODULE: toString
    //IMPORTS: None
    //EXPORT: digitalString
    //ASSERTIONS: None

    public String toString()
    {
        String digitalString = "name: " + name + ", artist: " + artist +
            ", duration: " + duration + ", type: " + type;
        return digitalString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None

    public String outputCSV()
    {
        String output = "D," + name + "," + artist + "," +
            String.format( "%.2f", duration ) + "," + type;
        return output;
    }




    //SUBMODULE: search
    //IMPORTS: inType (String)
    //EXPORT: exists (Boolean)
    //ASSERTIONS: If type EQUALS inType, exists = TRUE

    public boolean search( String inType )
    {
        boolean exists = false;
        if ( inType != null )
        {
            exists = inType.toLowerCase().equals( type );
        }
        return exists;
    }




    //SUBMODULE: play
    //IMPORTS: None
    //EXPORT: playString (String)
    //ASSERTIONS: None

    public String play()
    {
        String playString = name + " requires the " + type +
            " codec to play.";
        return playString;
    }




    //SUBMODULE: addDuration
    //IMPORTS: duration (Double)
    //EXPORT: The digitals duration plus the imported duration (Double).
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




    //NAME: validateDigital
    //PURPOSE: Determines if digital data is valid.
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inType (String)
    //EXPORTS: None
    //ASSERTIONS: Throws if invalid

    private static void validateDigital( String inName, String inArtist,
        double inDuration, String inType )
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
        if ( !validateType( inType ) )
        {
            throw new IllegalArgumentException( TYPE_ERROR );
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




    //NAME: validateType
    //PURPOSE: Determines if a type is valid.
    //IMPORTS: Digital music type
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the digital music type is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a type is defined in documentation.txt.

    private static boolean validateType( String inType )
    {
        boolean valid = false;
        //possible optimization could be done with short circuit evaluation
        //  after finding correct value, but not worth it with only
        //  3 elements
        for ( int i = 0; i != TYPE_VALID.length; i++ )
        {
            if ( inType.equals( TYPE_VALID[i] ) )
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
