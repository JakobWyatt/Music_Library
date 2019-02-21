// FILE: Validation.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Validation for music file inputs.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: None
// Last Mod: 27/09/2018
// Modification history:
//  None

import java.util.*;

public class Validation
{
    //These error messages are commonly used by the Validation class.
    public static final String NAME_ERROR = "Names cannot be empty.";
    public static final String ARTIST_ERROR = "Artist names cannot be empty.";
    public static final String DURATION_ERROR = "Duration must be a valid " +
        "time between 0.0 and 9.59 inclusive. " +
        "Check documentation.txt for more information.";
    public static final String TRACK_NUMBER_ERROR = "Track number must be a " +
        "valid integer between 0 and 20 inclusive.";
    public static final String PLAY_SPEED_ERROR = "The only supported play " +
        "speeds are '33 1/3 RPM', '45 RPM', and '78 RPM'. " +
        "Check documentation.txt for more information.";
    public static final String TYPE_ERROR = "The only supported digital " +
        "music file types are 'wav', 'mp3', and 'acc'. " +
        "Check documentation.txt for more information.";
    public static final String START_TIME_ERROR = "Start time must be a " +
        "valid time between 0.0 and 160.0 inclusive. " +
        "Check documentation.txt for more information.";
    public static final String SIDE_ERROR  = "The only supported cassette " +
        "sides are 'A' and 'B'. " +
        "Check documentation.txt for more information.";




    //NAME: recordErrors
    //PURPOSE:
    //  Returns a string, detailing any errors in the record information.
    //  If no errors are found, return null.
    //IMPORTS: name, artist, duration, trackNumber, playSpeed
    //EXPORT: error
    //Assertions:
    //  Pre: None.
    //  Post: If there are no errors, return null.
    //REMARKS: None.

    public static String recordErrors( String name,
        String artist, double duration, int trackNumber, String playSpeed )
    {
        String error = "";
        //Construct the error message,
        //  including every error occuring in the record.
        if ( !isValidName( name ) )
        {
            error += NAME_ERROR + "\n";
        }
        if ( !isValidArtist( artist ) )
        {
            error += ARTIST_ERROR + "\n";
        }
        if ( !isValidDuration( duration ) )
        {
            error += DURATION_ERROR + "\n";
        }
        if ( !isValidTrackNumber( trackNumber ) )
        {
            error += TRACK_NUMBER_ERROR + "\n";
        }
        if ( !isValidPlaySpeed( playSpeed ) )
        {
            error += PLAY_SPEED_ERROR + "\n";
        }
        //If there are no errors, then error.length() == 0,
        //  as the if statements above always append newlines to error.
        if ( error.length() == 0 )
        {
            error = null;
        }

        return error;
    }




    //NAME: digitalErrors
    //PURPOSE:
    //  Returns a string, detailing any errors in
    //      the digital music file information.
    //  If no errors are found, return null.
    //IMPORTS: name, artist, duration, type
    //EXPORT: valid
    //Assertions:
    //  Pre: None.
    //  Post: If there are no errors, return null.
    //REMARKS: None.

    public static String digitalErrors( String name,
        String artist, double duration, String type )
    {
        String error = "";
        //Construct the error message,
        //  including every error occuring in the digital music file.
        if ( !isValidName( name ) )
        {
            error += NAME_ERROR + "\n";
        }
        if ( !isValidArtist( artist ) )
        {
            error += ARTIST_ERROR + "\n";
        }
        if ( !isValidDuration( duration ) )
        {
            error += DURATION_ERROR + "\n";
        }
        if ( !isValidType( type ) )
        {
            error += TYPE_ERROR + "\n";
        }
        //If there are no errors, then error.length() == 0,
        //  as the if statements above always append newlines to error.
        if ( error.length() == 0 )
        {
            error = null;
        }

        return error;
    }




    //NAME: cassetteErrors
    //PURPOSE:
    //  Returns a string, detailing any errors in the cassette information.
    //  If no errors are found, return null.
    //IMPORTS: name, artist, duration, trackNumber, startTime, side
    //EXPORT: valid
    //Assertions:
    //  Pre: None.
    //  Post: If there are no errors, return null.
    //REMARKS: None.

    public static String cassetteErrors( String name,
        String artist, double duration, int trackNumber, double startTime,
        String side )
    {
        String error = "";
        //Construct the error message,
        //  including every error occuring in the cassette.
        if ( !isValidName( name ) )
        {
            error += NAME_ERROR + "\n";
        }
        if ( !isValidArtist( artist ) )
        {
            error += ARTIST_ERROR + "\n";
        }
        if ( !isValidDuration( duration ) )
        {
            error += DURATION_ERROR + "\n";
        }
        if ( !isValidTrackNumber( trackNumber ) )
        {
            error += TRACK_NUMBER_ERROR + "\n";
        }
        if ( !isValidStartTime( startTime ) )
        {
            error += START_TIME_ERROR + "\n";
        }
        if ( !isValidSide( side ) )
        {
            error += SIDE_ERROR + "\n";
        }
        //If there are no errors, then error.length() == 0,
        //  as the if statements above always append newlines to error.
        if ( error.length() == 0 )
        {
            error = null;
        }

        return error;
    }




    //NAME: isValidName
    //PURPOSE: Determines if a song name is valid.
    //IMPORTS: Song name
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the song name is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a song name is defined in documentation.txt.

    private static boolean isValidName( String name )
    {
        boolean valid = !nullOrEmpty( name );
        return valid;
    }




    //NAME: isValidArtist
    //PURPOSE: Determines if an artist's name is valid.
    //IMPORTS: Artist name
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the artist name is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of an artist name is
    //  defined in documentation.txt.

    private static boolean isValidArtist( String artist )
    {
        boolean valid = !nullOrEmpty( artist );
        return valid;
    }




    //NAME: isValidDuration
    //PURPOSE: Determines if a duration is valid.
    //IMPORTS: Song duration
    //EXPORTS: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the duration is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a song duration is
    //  defined in documentation.txt.

    private static boolean isValidDuration( double duration )
    {
        boolean valid = true;
        int seconds = (int)( duration * 100.0 ) % 100;

        if ( duration <= 0.0 || duration >= 9.60 )
        {
            valid = false;
        }
        if ( seconds < 0 || seconds >= 60 )
        {
            valid = false;
        }

        return valid;
    }




    //NAME: isValidTrackNumber
    //PURPOSE: Determines if a track number is valid.
    //IMPORTS: Track number
    //EXPORTS: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the trackNumber is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a track number is
    //  defined in documentation.txt.

    private static boolean isValidTrackNumber( int trackNumber )
    {
       boolean valid = true;
       if ( trackNumber < 0 || trackNumber > 20 )
       {
           valid = false;
       }

       return valid;
    }




    //NAME: isValidPlaySpeed
    //PURPOSE: Determines if a play speed is valid.
    //IMPORTS: Record play speed
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the play speed is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a play speed is defined in documentation.txt.

    private static boolean isValidPlaySpeed( String playSpeed )
    {
        String playSpeedUpper = playSpeed.toUpperCase();
        boolean valid = playSpeedUpper.equals( "33 1/3 RPM" ) ||
                        playSpeedUpper.equals( "45 RPM" ) ||
                        playSpeedUpper.equals( "78 RPM" );

        return valid;
    }




    //NAME: isValidType
    //PURPOSE: Determines if a digital music type is valid.
    //IMPORTS: Digital music file type
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the digital music type is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a digital music file type is
    //  defined in documentation.txt.

    private static boolean isValidType( String type )
    {
        String typeLower = type.toLowerCase();
        boolean valid = typeLower.equals( "wav" ) ||
                        typeLower.equals( "mp3" ) ||
                        typeLower.equals( "acc" );

        return valid;
    }




    //NAME: isValidStartTime
    //PURPOSE: Determines if a start time is valid.
    //IMPORTS: Start time of a cassette
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the start time is valid.
    //          Otherwise, valid = false.
    //REMARKS: The validity of a song name is defined in documentation.txt.

    private static boolean isValidStartTime( double startTime )
    {
        boolean valid = true;
        int seconds = (int)( startTime * 100.0 ) % 100;

        if ( startTime < 0.0 || startTime > 160.0 )
        {
            valid = false;
        }

        if ( seconds < 0 || seconds >= 60 )
        {
            valid = false;
        }

        return valid;
    }




    //NAME: isValidSide
    //PURPOSE: Determines if a cassette side is valid.
    //IMPORTS: Side of a cassette (as a string)
    //EXPORTS: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the side is valid.
    //          Otherwise, valid = FALSE.
    //REMARKS: The validity of a cassette side is
    //  defined in documentation.txt.

    private static boolean isValidSide( String side )
    {
        String sideUpper = side.toUpperCase();
        boolean valid = sideUpper.equals( "A" ) ||
                        sideUpper.equals( "B" );
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
        if ( x.length() == 0 )
        {
            isNullOrEmpty = true;
        }

        return isNullOrEmpty;
    }
}
