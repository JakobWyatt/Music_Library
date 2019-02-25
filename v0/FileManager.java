// FILE: FileManager.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: File IO for Music Library.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: Validation.java
// Last Mod: 13/09/2018
// Modification history:
//  None

import java.util.*;
import java.io.*;

public class FileManager
{
    //NAME: inputFile
    //PURPOSE: Prompt the user to input a filename.
    //  The current library is then initialized using data from this file.
    //IMPORTS: None
    //EXPORTS: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If a line of the file is invalid,
    //  then an error message will be printed,
    //  and the rest of the file will be read.
    //
    //If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    public static void inputFile()
    {
        String fileName = getFileNameR();
        String fileData = readFile( fileName );
        if ( fileData != null )
        {
            processCSV( fileData );
        }
    }




    //NAME: outputFile
    //PURPOSE: Prompt the user to input a filename.
    //  The current library is then saved to this new file.
    //  The user will be informed if the file write succeeds or fails.
    //IMPORTS: None
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If EOF has been reached on the command line,
    //      a NoSuchElementException will be thrown.
    //  If the input stream cannot be read from,
    //      an IllegalStateException will be thrown.

    public static void outputFile()
    {
        String result = "Library has been written to file.";

        try
        {
            String fileName = getFileNameW();
            PrintWriter writer = new PrintWriter( fileName );
            writer.println( "Data storage not yet implemented." );
            writer.println( "This is a test file." );
            writer.println( "To ensure the output is correctly printed." );
            if ( writer.checkError() ) {
                result = "Error: An unknown error has ocurred while " +
                    "writing the library to the file.";
            }
            writer.close();
        }
        //writer.close() doesn't need to be called in these catch statements,
        //  as these exceptions are thrown during initialization of writer.
        catch ( SecurityException secEx )
        {
            result = "Error: Could not access this file.";
        }
        catch ( FileNotFoundException fileEx )
        {
            result = "Error: Could not create the file.";
        }

        System.out.println( result );
    }




    //NAME: readFile
    //PURPOSE: Read all input from a file into a string.
    //  Inform the user if the file processing failed.
    //IMPORTS: File Name
    //EXPORTS: File Data
    //Assertions:
    //  Pre: File Name is not null
    //  Post: If processing failed, then File Data is null.
    //REMARKS: None

    private static String readFile( String fileName )
    {
        String result = "";
        String fileData = "";
        FileReader fReader;
        BufferedReader bufReader = null;

        try
        {
            fReader = new FileReader( fileName );
            bufReader = new BufferedReader( fReader );
            String line = bufReader.readLine();

            while ( line != null )
            {
                fileData += line + "\n";
                line = bufReader.readLine();
            }
            bufReader.close();
        }
        catch ( FileNotFoundException fileEx )
        {
            //bufReader.close() is not called here,
            //  as fileEx is thrown during initialization of fReader
            result = "Error: The file could not be accessed.\n";
            fileData = null;
        }
        catch ( IOException ioEx)
        {
            result = "Error: Could not read the file.\n";
            fileData = null;
            try
            {
                //bufReader.close() cannot throw a NullPointerException,
                //  as ioEx can only be thrown at bufReader.readLine(),
                //  which occurs after bufReader has been initialized
                bufReader.close();
            }
            catch ( IOException closeEx )
            {
                result = "Error: Unable to close the file. " +
                    "Restarting the program is recommended.\n";
            }
        }

        System.out.print( result );
        return fileData;
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
    
    private static String getFileNameR()
    {
        String prompt = "Please enter the filename to read from: ";
        String strout = prompt;
        String filePath = "";
        Scanner sc = new Scanner( System.in );
        boolean validFileName = true;

        do
        {
            try
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
                    strout = "Cannot write to a directory name.\n" + prompt;
                }
            }
            catch ( SecurityException e )
            {
                validFileName = false;
                strout = "This file cannot be accessed.\n" + prompt;
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

    private static String getFileNameW()
    {
        String prompt = "Please enter the filename to save to: ";
        String strout = prompt;
        String filePath = "";
        Scanner sc = new Scanner( System.in );
        boolean validFileName = true;

        do
        {
            try
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
                    validFileName = promptOverwrite();
                    strout = prompt;
                }
                if ( outputFile.isDirectory() )
                {
                    validFileName = false;
                    strout = "Cannot write to a directory name.\n" + prompt;
                }
            }
            catch ( SecurityException e )
            {
                validFileName = false;
                strout = "This file cannot be accessed.\n" + prompt;
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

    private static boolean promptOverwrite()
    {
        Scanner sc = new Scanner( System.in );
        System.out.println( "This file exists. Do you want to " +
            "overwrite it?\nYes (y) | No (n)" );
        boolean validInput = true;
        boolean overwrite = false;

        do
        {
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
    



    //NAME: processCSV
    //PURPOSE: Read and validate a CSV file, stored in a string,
    //  and use it to initialize the music library.
    //IMPORTS: CSV File
    //EXPORTS: None
    //Assertions:
    //  Pre: CSV File is not null
    //  Post: None
    //REMARKS: None

    private static void processCSV( String data )
    {
        int lineNum = 0;
        int recordNum = 0;
        int digitalNum = 0;
        int cassetteNum = 0;
        StringTokenizer strTok = new StringTokenizer( data, "\n" );
        if ( data.length() == 0 )
        {
            System.out.println( "Error in file processing: Empty File." );
        }

        while ( strTok.hasMoreTokens() )
        {
            ++lineNum;
            String line = strTok.nextToken();
            //Ensure that the line has characters in it.
            if ( line.length() == 0 )
            {
                System.out.println( "Discarding line " + lineNum +
                    ": Empty CSV Row." );
            } else
            {
                switch ( line.charAt( 0 ) )
                {
                    case 'r': case 'R':
                        recordNum = processRecord( line, lineNum,
                            recordNum );
                        break;
                    case 'd': case 'D':
                        digitalNum = processDigital( line, lineNum,
                            digitalNum );
                        break;
                    case 'c': case 'C':
                        cassetteNum = processCassette( line, lineNum,
                            cassetteNum );
                        break;
                    default:
                        System.out.println( "Discarding line " + lineNum + 
                            ": Invalid Music Type." );
                }
            }
        }
    }




    //NAME: processRecord
    //PURPOSE: Parse and validate a CSV row
    //  containing music library data for a record.
    //IMPORTS: Row data, row line number,
    //  number of records already processed.
    //EXPORTS: Number of records processed so far.
    //Assertions:
    //  Pre: First character of Row data is either 'r' or 'R'.
    //  Post: None
    //REMARKS: If there were errors during processing,
    //  the row line number is not incremented.

    private static int processRecord( String rowData, int lineNum,
        int recordNum )
    {
        StringTokenizer strTok = new StringTokenizer( rowData, "," );
        int tokenNum = strTok.countTokens();

        //ensure that the correct number of values is in the row
        if ( tokenNum != 6 )
        {
            System.out.println( "Discarding line " + lineNum +
                ": Invalid number of comma seperated values." );
        }
        else if ( recordNum >= 10 )
        {
            System.out.println( "Discarding line " + lineNum +
                ": Maximum number of records (10) has been reached." );
        }
        else
        {
            try
            {
                //Discard the music type
                strTok.nextToken();
                String name = strTok.nextToken();
                String artist = strTok.nextToken();
                String durationStr = strTok.nextToken();
                String trackNumberStr = strTok.nextToken();
                String playSpeed = strTok.nextToken();
                double duration = Double.parseDouble( durationStr );
                int trackNumber = Integer.parseInt( trackNumberStr );
                //duration and trackNumber are not passed
                //  to Validation as strings, as we do not want to
                //  convert them twice.
                if ( Validation.validRecord( name, artist, duration,
                    trackNumber, playSpeed ) )
                {
                    ++recordNum;
                    MusicStub.addRecord( name, artist, duration,
                        trackNumber, playSpeed );
                } else {
                    System.out.println( "Invalid." );
                }
            }
            catch ( NumberFormatException e )
            {
                System.out.println( "Discarding line " + lineNum +
                    ": Invalid number.");
            }
        }

        return recordNum;
    }




    //NAME: processDigital
    //PURPOSE: Parse and validate a CSV row
    //  containing music library data for a digital music file.
    //IMPORTS: Row data, row line number,
    //  number of records already processed.
    //EXPORTS: Number of digital music files processed so far.
    //Assertions:
    //  Pre: First character of Row data is either 'd' or 'D'.
    //  Post: None
    //REMARKS: If there were errors during processing,
    //  the row line number is not incremented.

    private static int processDigital( String rowData, int lineNum,
        int digitalNum )
    {
        StringTokenizer strTok = new StringTokenizer( rowData, "," );
        int tokenNum = strTok.countTokens();

        //ensure that the correct number of values is in the row
        if ( tokenNum != 5 )
        {
            System.out.println( "Discarding line " + lineNum +
                ": Invalid number of comma seperated values." );
        }
        else if ( digitalNum >= 10 )
        {
            System.out.println( "Discarding line " + lineNum +
                ": Maximum number of digital music files (10) " +
                "has been reached." );
        }
        else
        {
            try
            {
                //Discard the music type
                strTok.nextToken();
                String name = strTok.nextToken();
                String artist = strTok.nextToken();
                String durationStr = strTok.nextToken();
                String type = strTok.nextToken();
                double duration = Double.parseDouble( durationStr );
                //duration is not passed to Validation as a string,
                //  as we do not want to convert it twice.
                if ( Validation.validDigital( name, artist,
                    duration, type ) )
                {
                    ++digitalNum;
                    MusicStub.addDigital( name, artist, duration,
                        type );
                } else {
                    System.out.println( "Invalid." );
                }
            }
            catch ( NumberFormatException e )
            {
                System.out.println( "Discarding line " + lineNum +
                    ": Invalid number.");
            }
        }

        return digitalNum;
    }
    



    //NAME: processCassette
    //PURPOSE: Parse and validate a CSV row
    //  containing music library data for a cassette.
    //IMPORTS: Row data, row line number,
    //  number of cassettes already processed.
    //EXPORTS: Number of cassettes processed so far.
    //Assertions:
    //  Pre: First character of Row data is either 'c' or 'C'.
    //  Post: None
    //REMARKS: If there were errors during processing,
    //  the row line number is not incremented.

    private static int processCassette( String rowData, int lineNum,
        int cassetteNum )
    {
        StringTokenizer strTok = new StringTokenizer( rowData, "," );
        int tokenNum = strTok.countTokens();

        //ensure that the correct number of values is in the row
        if ( tokenNum != 7 )
        {
            System.out.println( "Discarding line " + lineNum +
                ": Invalid number of comma seperated values." );
        }
        else if ( cassetteNum >= 10 )
        {
            System.out.println( "Discarding line " + lineNum +
                ": Maximum number of cassettes (10) has been reached." );
        }
        else
        {
            try
            {
                //Discard the music type
                strTok.nextToken();
                String name = strTok.nextToken();
                String artist = strTok.nextToken();
                String durationStr = strTok.nextToken();
                String trackNumberStr = strTok.nextToken();
                String startTimeStr = strTok.nextToken();
                String sideStr = strTok.nextToken();
                double duration = Double.parseDouble( durationStr );
                int trackNumber = Integer.parseInt( trackNumberStr );
                double startTime = Double.parseDouble( startTimeStr );
                //sideStr is passed to Validation as a string, as the strings
                //  length is required to determine if it is valid.
                //duration, trackNumber, and startTime are not passed
                //  to Validation as strings, as we do not want to
                //  convert them twice.
                if ( Validation.validCassette( name, artist,
                    duration, trackNumber, startTime, sideStr ) )
                {
                    ++cassetteNum;
                    MusicStub.addCassette( name, artist, duration,
                        trackNumber, startTime, sideStr.charAt( 0 ) );
                } else
                {
                    System.out.println( "Invalid." );
                }
            }
            catch ( NumberFormatException e )
            {
                System.out.println( "Discarding line " + lineNum +
                    ": Invalid number.");
            }
        }

        return cassetteNum;
    }
}
