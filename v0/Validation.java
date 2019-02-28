// FILE: Validation.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Validation for music file inputs.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: None
// Last Mod: 13/09/2018
// Modification history:
//  None

import java.util.*;

public class Validation
{
    //NAME: validRecord
    //PURPOSE: Determines if a records data is valid.
    //IMPORTS: name, artist, duration, trackNumber, playSpeed
    //EXPORT: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the record is valid.
    //REMARKS: None.

    public static boolean validRecord( String name,
        String artist, double duration, int trackNumber, String playSpeed )
    {
        boolean valid = validName( name ) &&
                        validArtist( artist ) &&
                        validDuration( duration ) &&
                        validTrackNumber( trackNumber ) &&
                        validPlaySpeed( playSpeed );
        return valid;
    }




    //NAME: validDigital
    //PURPOSE: Determines if a digital music files data is valid.
    //IMPORTS: name, artist, duration, type
    //EXPORT: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the digital music file is valid.
    //REMARKS: None.

    public static boolean validDigital( String name,
        String artist, double duration, String type )
    {
        boolean valid = validName( name ) &&
                        validArtist( name ) &&
                        validDuration( duration ) &&
                        validType( type );
        return valid;
    }




    //NAME: validCassette
    //PURPOSE: Determines if a cassettes data is valid.
    //IMPORTS: name, artist, duration, trackNumber, startTime, side
    //EXPORT: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the cassette is valid.
    //REMARKS: None.

    public static boolean validCassette( String name,
        String artist, double duration, int trackNumber, double startTime,
        String side )
    {
        boolean valid = validName( name ) &&
                        validArtist( name ) &&
                        validDuration( duration ) &&
                        validTrackNumber( trackNumber) &&
                        validStartTime( startTime ) &&
                        validSide( side );
        return valid;
    }




    //NAME: validName
    //PURPOSE: Determines if a name is valid.
    //IMPORTS: name
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the name is valid
    //REMARKS: None

    private static boolean validName( String name )
    {
        boolean valid = !nullOrEmpty( name );
        return valid;
    }




    //NAME: validArtist
    //PURPOSE: Determines if an artist is valid.
    //IMPORTS: artist
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the artist is valid
    //REMARKS: None

    private static boolean validArtist( String artist )
    {
        boolean valid = !nullOrEmpty( artist );
        return valid;
    }




    //NAME: validDuration
    //PURPOSE: Determines if a duration is valid.
    //IMPORTS: duration
    //EXPORTS: valid
    //Assertions:
    //  Pre: None.
    //  Post: valid = TRUE if the duration is valid
    //REMARKS: None

    private static boolean validDuration( double duration )
    {
        boolean valid = true;
        int seconds = (int)(duration * 100.0) % 100;

        if ( duration < 0.0 || duration >= 9.60 )
        {
            valid = false;
        }
        if ( seconds < 0 || seconds >= 60 )
        {
            valid = false;
        }

        return valid;
    }




    //NAME: validTrackNumber
    //PURPOSE: Determines if a track number is valid.
    //IMPORTS: trackNumber
    //EXPORTS: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the trackNumber is valid.
    //REMARKS: None

    private static boolean validTrackNumber( int trackNumber )
    {
       boolean valid = true;
       if ( trackNumber < 0 || trackNumber > 20 )
       {
           valid = false;
       }

       return valid;
    }




    //NAME: validPlaySpeed
    //PURPOSE: Determines if a play speed is valid.
    //IMPORTS: playSpeed
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the play speed is valid.
    //REMARKS: None

    private static boolean validPlaySpeed( String playSpeed )
    {
        String playSpeedUpper = playSpeed.toUpperCase();
        boolean valid = playSpeedUpper.equals( "33 1/3 RPM" ) ||
                        playSpeedUpper.equals( "45 RPM" ) ||
                        playSpeedUpper.equals( "78 RPM" );

        return valid;
    }




    //NAME: validType
    //PURPOSE: Determines if a digital music type is valid.
    //IMPORTS: type
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the digital music type is valid
    //REMARKS: None

    private static boolean validType( String type )
    {
        String typeLower = type.toLowerCase();
        boolean valid = typeLower.equals( "wav" ) ||
                        typeLower.equals( "mp3" ) ||
                        typeLower.equals( "acc" );

        return valid;
    }




    //NAME: validStartTime
    //PURPOSE: Determines if a start time is valid.
    //IMPORTS: start time of a cassette
    //EXPORT: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the start time is valid
    //REMARKS: None

    private static boolean validStartTime( double startTime )
    {
        boolean valid = true;
        int seconds = (int)(startTime * 100.0) % 100;

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




    //NAME: validSide
    //PURPOSE: Determines if a side is valid.
    //IMPORTS: Side of a cassette (as a string)
    //EXPORTS: valid
    //Assertions:
    //  Pre: None
    //  Post: valid = TRUE if the side is valid
    //REMARKS: None

    private static boolean validSide( String side )
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
