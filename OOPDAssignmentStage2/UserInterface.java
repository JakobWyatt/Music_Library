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

//THIS CLASS SHOULD BE STATIC.
//THE ASSIGNMENT BRIEF STATED THAT IT MUST CONTAIN NO NON-STATIC METHODS.
//THEREFORE IT IS IMPLEMENTED THAT WAY.
//THIS CLASS HAS NO INTERNAL STATE.
public class UserInterface
{
    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORTS: The address of the UserInterface object.
    //REMARKS: THIS METHOD SHOULD NOT EXIST. IT DOES NOTHING.

    public UserInterface()
    {

    }




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
    //  If the input file is empty,
    //      an IllegalArgumentException is thrown.

    public void menu() throws IOException
    {
        int option = 0;
        MusicCollection library = new MusicCollection();
        String transcript;

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
            transcript = library.inputCSV(
                FileManager.readFile( this.getFileNameR() ) );
            System.out.println( transcript );
        }
        catch ( IOException ioEx )
        {
            throw ioEx;
        }

        while ( option != 7 )
        {
            option = this.promptRangedIntInput( prompt, 1, 8 );
            switch ( option )
            {
                case 1:
                    this.addSong( library );
                    break;
                case 2:
                    System.out.printf( "%.2f\n", library.duration() );
                    break;
                case 3:
                    System.out.print( library.playAll() );
                    break;
                case 4:
                    this.playSong( library );
                    break;
                case 5:
                    this.mediaSearchMenu( library );
                    break;
                case 6:
                    this.outputLibrary( library );
                    break;
                case 7:
                    System.out.println( "Goodbye." );
                    break;
            }
        }
    }




    //NAME: outputLibrary
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //ASSERTIONS: Inform the user if file io failed.

    private void outputLibrary( MusicCollection library )
    {
        try
        {
            FileManager.writeFile( this.getFileNameW(),
                library.outputCSV() );
        }
        catch ( FileNotFoundException fileEx )
        {
            System.out.println( fileEx.getMessage() );
        }
        catch ( IOException ioEx )
        {
            System.out.println( ioEx.getMessage() );
        }
    }




    //NAME: addSong
    //PURPOSE: Prompts the user to add a song to the library.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None

    private void addSong( MusicCollection library )
    {
        String prompt = "Please enter the media type you would like to " +
            "add to your library.\n" +
            "1. Record\n" +
            "2: Digital\n" +
            "3: Cassette\n" +
            "4: Exit the menu\n";
        int option = this.promptRangedIntInput( prompt, 1, 5 );

        switch ( option )
        {
            case 1:
                this.addRecord( library );
                break;
            case 2:
                this.addDigital( library );
                break;
            case 3:
                this.addCassette( library );
                break;
        }
    }




    //NAME: addRecord
    //PURPOSE: Add a record to the music library, via user input.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //ASSERTIONS: None

    private void addRecord( MusicCollection library )
    {
        String name, artist, playSpeed;
        int trackNumber;
        double duration;
        Scanner sc = new Scanner( System.in );

        System.out.print( "Please enter the song name: " );
        name = sc.nextLine();
        System.out.print( "Please enter the artist name: " );
        artist = sc.nextLine();
        System.out.print( "Please enter the duration: " );
        duration = this.getReal();
        System.out.print( "Please enter the track number: " );
        trackNumber = this.getInteger();
        System.out.print( "Please enter the play speed: " );
        playSpeed = sc.nextLine();

        try
        {
            library.addRecord( new Record( name, artist, duration,
                trackNumber, playSpeed ) );
        }
        catch ( IllegalArgumentException ex )
        {
            System.out.println( ex.getMessage() );
        }
    }




    //NAME: addDigital
    //PURPOSE: Add a digital to the music library, via user input.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //ASSERTIONS: None

    private void addDigital( MusicCollection library )
    {
        String name, artist, type;
        double duration;
        Scanner sc = new Scanner( System.in );

        System.out.print( "Please enter the song name: " );
        name = sc.nextLine();
        System.out.print( "Please enter the artist name: " );
        artist = sc.nextLine();
        System.out.print( "Please enter the duration: " );
        duration = this.getReal();
        System.out.print( "Please enter the digital music type: " );
        type = sc.nextLine();

        try
        {
            library.addDigital( new Digital( name, artist, duration,
                type ) );
        }
        catch ( IllegalArgumentException ex )
        {
            System.out.println( ex.getMessage() );
        }
    }




    //NAME: addCassette
    //PURPOSE: Add a cassette to the music library, via user input.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //ASSERTIONS: None

    private void addCassette( MusicCollection library )
    {
        String name, artist, side;
        int trackNumber;
        double duration, startTime;
        Scanner sc = new Scanner( System.in );

        System.out.print( "Please enter the song name: " );
        name = sc.nextLine();
        System.out.print( "Please enter the artist name: " );
        artist = sc.nextLine();
        System.out.print( "Please enter the duration: " );
        duration = this.getReal();
        System.out.print( "Please enter the track number: " );
        trackNumber = this.getInteger();
        System.out.print( "Please enter the start time: " );
        startTime = this.getReal();
        System.out.print( "Please enter the side: " );
        side = sc.nextLine();

        //Cassette constructor takes side as a char
        if ( side.length() != 1 )
        {
            System.out.println( Cassette.SIDE_ERROR );
        } else
        {
            try
            {
                library.addCassette( new Cassette( name, artist, duration,
                    trackNumber, startTime, side.charAt( 0 ) ) );
            }
            catch ( IllegalArgumentException ex )
            {
                System.out.println( ex.getMessage() );
            }
        }
    }




    //NAME: playSong
    //PURPOSE: Prompts the user for a song name,
    //  and print the play instructions.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //ASSERTIONS: If the song doesn't exist, notify the user.

    private void playSong( MusicCollection library )
    {
        Scanner sc = new Scanner( System.in );
        String input;
        String output;

        System.out.print( "Please enter the song name: " );
        input = sc.nextLine();

        try
        {
            output = library.playSong( input );
        }
        catch ( IllegalArgumentException ex )
        {
            output = ex.getMessage();
        }
        System.out.println( output );
    }




    //NAME: mediaSearchMenu
    //PURPOSE: Display the menu of different media types
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private void mediaSearchMenu( MusicCollection library )
    {
        String prompt = "Please enter the media type to search for.\n" +
            "1: Record\n" +
            "2: Digital\n" +
            "3: Cassette\n" +
            "4: Exit the menu\n";
        int option = this.promptRangedIntInput( prompt, 1, 5 );

        switch( option )
        {
            case 1:
                this.recordSearchMenu( library );
                break;
            case 2:
                this.digitalSearchMenu( library );
                break;
            case 3:
                this.cassetteSearchMenu( library );
                break;
        }
    }




    //NAME: recordSearchMenu
    //PURPOSE: Display the submenu for the record media type.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private void recordSearchMenu( MusicCollection library )
    {
        String prompt = "Please enter the play speed of the record.\n" +
            "1: 33 1/3 RPM\n" +
            "2: 45 RPM\n" +
            "3: 78 RPM\n" +
            "4: Exit the menu\n";
        int option = this.promptRangedIntInput( prompt, 1, 5 );
        Record[] records;
        double duration = 0.0;

        switch ( option )
        {
            case 1:
                records = library.searchRecords( "33 1/3 RPM" );
                System.out.println( "Number of songs: " + records.length );
                for ( int i = 0; i != records.length; i++ )
                {
                    duration = records[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
            case 2:
                records = library.searchRecords( "45 RPM" );
                System.out.println( "Number of songs: " + records.length );
                for ( int i = 0; i != records.length; i++ )
                {
                    duration = records[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
            case 3:
                records = library.searchRecords( "78 RPM" );
                System.out.println( "Number of songs: " + records.length );
                for ( int i = 0; i != records.length; i++ )
                {
                    duration = records[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
        }
    }




    //NAME: digitalSearchMenu
    //PURPOSE: Display the submenu for the digital media type.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private void digitalSearchMenu( MusicCollection library )
    {
        String prompt = "Please enter the codec of the music file.\n" +
            "1: wav\n" +
            "2: mp3\n" +
            "3: acc\n" +
            "4: Exit the menu\n";
        int option = this.promptRangedIntInput( prompt, 1, 5 );
        Digital[] digitals;
        double duration = 0.0;

        switch ( option )
        {
            case 1:
                digitals = library.searchDigitals( "wav" );
                System.out.println( "Number of songs: " + digitals.length );
                for ( int i = 0; i != digitals.length; i++ )
                {
                    duration = digitals[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
            case 2:
                digitals = library.searchDigitals( "mp3" );
                System.out.println( "Number of songs: " + digitals.length );
                for ( int i = 0; i != digitals.length; i++ )
                {
                    duration = digitals[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
            case 3:
                digitals = library.searchDigitals( "acc" );
                System.out.println( "Number of songs: " + digitals.length );
                for ( int i = 0; i != digitals.length; i++ )
                {
                    duration = digitals[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
        }
    }




    //NAME: cassetteSearchMenu
    //PURPOSE: Display the submenu for the cassette media type.
    //IMPORTS: library (MusicCollection)
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private void cassetteSearchMenu( MusicCollection library )
    {
        String prompt = "Please enter the side of the cassette.\n" +
            "1: Side A\n" +
            "2: Side B\n" +
            "3: Exit the menu\n";
        int option = this.promptRangedIntInput( prompt, 1, 4 );
        Cassette[] cassettes;
        double duration = 0.0;

        switch ( option )
        {
            case 1:
                cassettes = library.searchCassettes( "A" );
                System.out.println( "Number of songs: " + cassettes.length );
                for ( int i = 0; i != cassettes.length; i++ )
                {
                    duration = cassettes[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
            case 2:
                cassettes = library.searchCassettes( "B" );
                System.out.println( "Number of songs: " + cassettes.length );
                for ( int i = 0; i != cassettes.length; i++ )
                {
                    duration = cassettes[i].addDuration( duration );
                }
                System.out.printf( "Total Duration: %.2f\n", duration );
                break;
        }
    }




    //NAME: getReal
    //PURPOSE: Read a real number from a command line.
    //  Reprompt the user if it is not a valid number.
    //IMPORTS: None
    //EXPORTS: userInput (Real)
    //ASSERTIONS: None

    private double getReal()
    {
        boolean invalid = true;
        String strout = "";
        String error = "Please enter a valid real number.\n";
        double input = 0.0;
        Scanner sc = new Scanner( System.in );

        while ( invalid )
        {
            try
            {
                invalid = false;
                System.out.print( strout );
                strout = error;
                input = sc.nextDouble();
            }
            catch ( NoSuchElementException e )
            {
                sc.next();
                invalid = true;
            }
        }

        return input;
    }




    //NAME: getInteger
    //PURPOSE: Read an integer number from a command line.
    //  Reprompt the user if it is not a valid number.
    //IMPORTS: None
    //EXPORTS: userInput (Integer)
    //ASSERTIONS: None

    private int getInteger()
    {
        boolean invalid = true;
        String strout = "";
        String error = "Please enter a valid integer.\n";
        int input = 0;
        Scanner sc = new Scanner( System.in );

        while ( invalid )
        {
            try
            {
                invalid = false;
                System.out.print( strout );
                strout = error;
                input = sc.nextInt();
            }
            catch ( NoSuchElementException e )
            {
                sc.next();
                invalid = true;
            }
        }

        return input;
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

    private int promptRangedIntInput( String prompt,
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




    //NAME: getFileNameR
    //PURPOSE: Prompt the user to input a file name to read from.
    //  getFileNameR asks for input again if the file does not exist,
    //      or is a directory.
    //IMPORTS: None
    //EXPORTS: File path
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private String getFileNameR()
    {
        String prompt = "Enter the filename to read from: ";
        String strout = prompt;
        String filePath = "";
        Scanner sc = new Scanner( System.in );
        boolean validFileName = true;

        do
        {
            validFileName = true;
            System.out.print( strout );
            //sc.next() will throw if EOF has been reached
            filePath = sc.next();
            File outputFile = new File( filePath );
            //check if the file exists, and is not a directory
            if ( !outputFile.exists() )
            {
                validFileName = false;
                strout = "This file does not exist.\n" + prompt;
            }
            if ( outputFile.isDirectory() )
            {
                validFileName = false;
                strout = "Cannot read from a directory name.\n" + prompt;
            }
        } while ( !validFileName );
        return filePath;
    }




    //NAME: getFileNameW
    //PURPOSE: Prompt the user to input a file name to write to.
    //  getFileNameW asks for input again if the file name is a directory,
    //      and will ask for confirmation before overwriting existing files.
    //IMPORTS: None
    //EXPORTS: File Path
    //Assertions:
    //  Pre: None
    //  Post: The file path points to a valid, writeable file.
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private String getFileNameW()
    {
        String prompt = "Enter the filename to save to: ";
        String strout = prompt;
        String filePath = "";
        Scanner sc = new Scanner( System.in );
        boolean validFileName = true;

        do
        {
            validFileName = true;
            System.out.print( strout );
            //sc.next() will throw if EOF has been reached
            filePath = sc.next();
            File outputFile = new File( filePath );
            //if the file already exists,
            //  ask the user if they want to overwrite it
            if ( outputFile.isFile() )
            {
                validFileName = this.promptOverwrite();
                strout = prompt;
            }
            if ( outputFile.isDirectory() )
            {
                validFileName = false;
                strout = "Cannot write to a directory name.\n" + prompt;
            }
        } while ( !validFileName );

        return filePath;
    }




    //NAME: promptOverwrite
    //PURPOSE: Check if the user wants to overwrite a file.
    //IMPORTS: None
    //EXPORTS: TRUE if the file will be overwritten, FALSE otherwise.
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    private boolean promptOverwrite()
    {
        Scanner sc = new Scanner( System.in );
        System.out.println( "This file exists. Do you want to " +
            "overwrite it?\nYes (y) | No (n)" );
        boolean validInput = true;
        boolean overwrite = false;

        do
        {
            validInput = true;
            char input = sc.next().charAt( 0 );
            switch ( input )
            {
                case 'y': case 'Y':
                    overwrite = true;
                    break;
                case 'n': case 'N':
                    break;
                default:
                    validInput = false;
                    System.out.println( "Invalid input. Please try again." );
            }
        } while ( !validInput );

        return overwrite;
    }
}
