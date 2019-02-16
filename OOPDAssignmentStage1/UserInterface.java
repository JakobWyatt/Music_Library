// FILE: UserInterface.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: User IO for Music Library.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: FileManager.java
// Last Mod: 27/09/2018
// Modification history:
//  None

import java.io.*;
import java.util.*;

public class UserInterface
{
    //NAME: menu
    //PURPOSE: Display the top level menu to interface with the user.
    //IMPORTS: None
    //EXPORT: None
    //Assertion:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.
    //  If the file could not be found,
    //      a FileNotFoundException error is thrown.
    //  If there was an error while reading the file,
    //      an IOException is thrown.

    public static void menu() throws IOException
    {

        int option = 0;
        String prompt = "\n1: Add songs to your library.\n" +
            "2: Find the total duration of music in your library.\n" +
            "3: Print all play instructions for your entire library.\n" +
            "4: Print the play instructions for a specific song.\n" +
            "5: Find the number of songs and total run time of a given " +
                "media type.\n" +
            "6: Output your library to a file.\n" +
            "7: Exit the program.\n";

        System.out.print( "\nWelcome to Music Library, the easy to use " +
            "program that manages your music.\n" +
            "Please enter a file name to create your library.\n\n" );

        //As the library must be initialized before the program starts,
        //  if file input fails, we will exit the program.
        try
        {
            FileManager.inputFile();
        }
        catch ( IOException ioEx )
        {
            throw ioEx;
        }

        while ( option != 7 )
        {
            option = promptRangedIntInput( prompt, 1, 8 );
            switch ( option )
            {
                case 1:
                    notImplemented( "Adding songs to your library" );
                    break;
                case 2:
                    notImplemented( "Finding the total duration of your library" );
                    break;
                case 3:
                    notImplemented( "Printing play instructions for all your songs" );
                    break;
                case 4:
                    notImplemented( "Searching for a specific song" );
                    break;
                case 5:
                    mediaSearchMenu();
                    break;
                case 6:
                    FileManager.outputFile();
                    break;
                case 7:
                    System.out.println( "Goodbye." );
                    break;
            }
        }
    }




    //NAME: mediaSearchMenu
    //PURPOSE: Display the menu of different media types
    //IMPORTS: None
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private static void mediaSearchMenu()
    {
        String prompt = "Please enter the media type to search for.\n" +
            "1: Record\n" +
            "2: Digital\n" +
            "3: Cassette\n" +
            "4: Exit the menu\n";
        int option = promptRangedIntInput( prompt, 1, 5 );

        switch( option )
        {
            case 1:
                recordSearchMenu();
                break;
            case 2:
                digitalSearchMenu();
                break;
            case 3:
                cassetteSearchMenu();
                break;
        }
    }




    //NAME: recordSearchMenu
    //PURPOSE: Display the submenu for the record media type.
    //IMPORTS: None
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private static void recordSearchMenu()
    {
        String prompt = "Please enter the play speed of the record.\n" +
            "1: 33 1/3 RPM\n" +
            "2: 45 RPM\n" +
            "3: 78 RPM\n" +
            "4: Exit the menu\n";
        int option = promptRangedIntInput( prompt, 1, 5 );
        if ( option != 4 )
        {
            notImplemented( "Finding the number of songs and total run " +
                "time of a given media type" );
        }
    }




    //NAME: digitalSearchMenu
    //PURPOSE: Display the submenu for the digital media type.
    //IMPORTS: None
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private static void digitalSearchMenu()
    {
        String prompt = "Please enter the codec of the music file.\n" +
            "1: wav\n" +
            "2: mp3\n" +
            "3: acc\n" +
            "4: Exit the menu\n";
        int option = promptRangedIntInput( prompt, 1, 5 );
        if ( option != 4 )
        {
            notImplemented( "Finding the number of songs and total run " +
                "time of a given media type" );
        }
    }




    //NAME: cassetteSearchMenu
    //PURPOSE: Display the submenu for the cassette media type.
    //IMPORTS: None
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private static void cassetteSearchMenu()
    {
        String prompt = "Please enter the side of the cassette.\n" +
            "1: Side A\n" +
            "2: Side B\n" +
            "3: Exit the menu\n";
        int option = promptRangedIntInput( prompt, 1, 4 );
        if ( option != 3 )
        {
            notImplemented( "Finding the number of songs and total run " +
                "time of a given media type" );
        }
    }




    //NAME: notImplemented
    //PURPOSE: Inform the user that a certain feature is not implemented yet.
    //IMPORTS: The feature that is not currently implemented
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: None

    private static void notImplemented( String feature )
    {
        System.out.println( feature + " is not yet implemented." );
    }




    //NAME: promptRangedIntInput
    //PURPOSE: Prompt the user to input an input in the range [low, high)
    //IMPORTS: User Prompt, low, high
    //EXPORT: User Input
    //Assertions:
    //  Pre: low < high
    //  Post: User Input is in the range [low, high)
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private static int promptRangedIntInput( String prompt,
        int low, int high )
    {
        Scanner sc = new Scanner( System.in );
        String error = "Please enter an integer between " + low +
            " and " +  ( high - 1 ) + " inclusive.\n";
        String strout = prompt;
        int input = high;

        while ( input < low || input >= high )
        {
            try
            {
                System.out.print( strout );
                strout = error;
                input = sc.nextInt();
            }
            catch ( NoSuchElementException e )
            {
                //if EOF has been reached,
                //  then sc.next() will throw a NoSuchElementException
                sc.next();
                input = high;
            }
        }
        return input;
    }
}
