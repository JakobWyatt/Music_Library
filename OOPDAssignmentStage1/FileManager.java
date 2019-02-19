// FILE: FileManager.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: File IO for Music Library.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: Validation.java
// Last Mod: 27/09/2018
// Modification history:
//  None

import java.util.*;
import java.io.*;
import java.text.NumberFormat;

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
    //  If the file could not be found,
    //      a FileNotFoundException error is thrown.
    //  If there was an error while reading the file,
    //      an IOException is thrown.

    public static void inputFile() throws IOException
    {
        String fileName;
        String[] fileData;

        fileName = getFileNameR();

        //FileNotFoundException is an unchecked exception, and therefore
        //  we won't catch and rethrow it.
        //ex is rethrown so that the calling function
        //  knows that file input failed.
        try
        {
            fileData = readFile( fileName );
        }
        catch ( IOException ex )
        {
            throw ex;
        }

        processCSV( fileData );
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
        String result, fileName;
        result = "Library has been written to file.";
        PrintWriter writer = null;

        try
        {
            fileName = getFileNameW();
            //PrintWriter is buffered to memory,
            //  and is therefore fast, by default.
            writer = new PrintWriter( fileName );
            writer.println( "Data storage not yet implemented." );
            writer.println( "This is a test file." );
            writer.println( "To ensure the output is correctly printed." );
            if ( writer.checkError() ) {
                result = "Error: An unknown error has ocurred while " +
                    "writing the library to the file.";
            }
            writer.close();
        }
        //writer.close() doesn't need to be called in this catch statement,
        //  as this exception is thrown during initialization of writer.
        catch ( FileNotFoundException fileEx )
        {
            result = "Error: Could not create the file.";
        }

        System.out.println( result );
    }




    //NAME: readFile
    //PURPOSE: Read all input from a file into a string array,
    //  with each entry being seperated by a newline.
    //  Inform the user if the file processing failed.
    //IMPORTS: File Name
    //EXPORTS: File Data
    //Assertions:
    //  Pre: File Name is not null.
    //  Post: None.
    //REMARKS:
    //  If the file could not be found,
    //      a FileNotFoundException error is thrown.
    //  If there was an error while reading the file,
    //      an IOException is thrown.

    private static String[] readFile( String fileName ) throws IOException
    {
        String line;
        FileReader fReader;
        BufferedReader bufReader = null;
        int lines, lineNum;
        String[] fileData = null;

        lineNum = 0;

        try
        {
            lines = fileLines( fileName );
            fileData = new String[lines];
            fReader = new FileReader( fileName );
            bufReader = new BufferedReader( fReader );

            line = bufReader.readLine();
            while ( line != null )
            {
                fileData[lineNum] = line;
                line = bufReader.readLine();
                ++lineNum;
            }
            bufReader.close();
        }
        catch ( FileNotFoundException fileEx )
        {
            //bufReader.close() is not called here,
            //  as fileEx is thrown during initialization of fReader
            throw fileEx;
        }
        catch ( IOException ioEx)
        {
            try
            {
                //bufReader.close() cannot throw a NullPointerException,
                //  as ioEx can only be thrown at bufReader.readLine(),
                //  which occurs after bufReader has been initialized
                bufReader.close();
            }
            catch ( IOException closeEx )
            {
                throw closeEx;
            }
            throw ioEx;
        }

        return fileData;
    }




    //NAME: fileLines
    //PURPOSE: Find the number of lines in a file.
    //IMPORTS: File Name
    //EXPORTS: Number of lines in the file
    //Assertions:
    //  Pre: File name is not null.
    //  Post: None.
    //REMARKS: Informs the user if file processing fails.
    //  Throws FileNotFoundException if the file was not found.
    //  Throws IOException if there was an error while reading the lines.

    private static int fileLines( String fileName ) throws IOException
    {
        FileReader fReader;
        BufferedReader bufReader = null;
        int lineCount = 0;

        try
        {
            fReader = new FileReader( fileName );
            bufReader = new BufferedReader( fReader );
            while ( bufReader.readLine() != null )
            {
                ++lineCount;
            }
            bufReader.close();
        }
        catch ( FileNotFoundException fileEx )
        {
            //bufReader.close() is not called here,
            //  as fileEx is thrown during initialization of fReader.
            throw fileEx;
        }
        catch ( IOException ex )
        {
            try
            {
                bufReader.close();
            }
            catch ( IOException closeEx )
            {
                throw closeEx;
            }
            throw ex;
        }
        return lineCount;
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

    private static String getFileNameW()
    {
        String prompt = "Please enter the filename to save to: ";
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
                validFileName = promptOverwrite();
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

    private static boolean promptOverwrite()
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




    //NAME: processCSV
    //PURPOSE: Read and validate a CSV file, stored in a string,
    //  and use it to initialize the music library.
    //IMPORTS: CSV File
    //EXPORTS: None
    //Assertions:
    //  Pre: CSV File is not null
    //  Post: None
    //REMARKS: None

    private static void processCSV( String[] csv )
    {
        int lineNum = 0;
        int recordNum = 0;
        int digitalNum = 0;
        int cassetteNum = 0;

        if ( csv.length == 0 )
        {
            System.out.print( "Discarding file: Empty File.\n\n" );
        }

        for ( int i = 0; i != csv.length; i++ )
        {
            ++lineNum;
            //Ensure that the line has characters in it.
            if ( csv[i].length() == 0 )
            {
                System.out.print( "Discarding line " + lineNum +
                    ": Empty CSV Row.\n\n" );
            } else
            {
                switch ( csv[i].charAt( 0 ) )
                {
                    case 'r': case 'R':
                        recordNum = processRecord( csv[i], lineNum,
                            recordNum );
                        break;
                    case 'd': case 'D':
                        digitalNum = processDigital( csv[i], lineNum,
                            digitalNum );
                        break;
                    case 'c': case 'C':
                        cassetteNum = processCassette( csv[i], lineNum,
                            cassetteNum );
                        break;
                    default:
                        System.out.print( "Discarding line " + lineNum +
                            ": Invalid Music Type.\n\n" );
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
        double duration, startTime;
        int trackNumber;
        String validationError, errorTemplate, allErrors, parseError;
        String[] data = rowData.split(",");

        //This template is prepended to every error
        errorTemplate = "Discarding line " + lineNum + ": ";
        allErrors = "";
        parseError = "";

        //ensure that the correct number of values is in the row
        if ( data.length != 6 )
        {
            allErrors += errorTemplate +
                "Invalid number of comma seperated values.\n";
        }
        else if ( recordNum >= 10 )
        {
            allErrors += errorTemplate +
                "Maximum number of records (10) has been reached.\n";
        }
        else
        {
            try
            {
                //Any numbers are parsed here,
                //  so that they aren't converted twice.
                //Parse error keeps track of which number is being parsed,
                //  so that if a parse method throws,
                //  an accurate error is given to the user.
                parseError = Validation.DURATION_ERROR;
                duration = Double.parseDouble( data[3] );
                parseError = Validation.TRACK_NUMBER_ERROR;
                trackNumber = Integer.parseInt( data[4] );

                validationError = Validation.recordErrors( data[1],
                    data[2], duration, trackNumber, data[5] );

                if ( validationError == null )
                {
                    ++recordNum;
                    MusicStub.addRecord( data[1], data[2], duration,
                        trackNumber, data[5] );
                } else
                {
                    allErrors += errorTemplate + validationError;
                }
            }
            catch ( NumberFormatException e )
            {
                allErrors += errorTemplate + parseError + "\n";
            }
        }

        System.out.println( allErrors );
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
        double duration, startTime;
        int trackNumber;
        String validationError, errorTemplate, allErrors;
        String[] data = rowData.split(",");

        //This template is prepended to every error
        errorTemplate = "Discarding line " + lineNum + ": ";
        allErrors = "";

        //ensure that the correct number of values is in the row
        if ( data.length != 5 )
        {
            allErrors += errorTemplate +
                "Invalid number of comma seperated values.\n";
        }
        else if ( digitalNum >= 10 )
        {
            allErrors += errorTemplate +
                "Maximum number of digital music files (10) " +
                " has been reached.\n";
        }
        else
        {
            try
            {
                //Any numbers are parsed here,
                //  so that they aren't converted twice.
                duration = Double.parseDouble( data[3] );

                validationError = Validation.digitalErrors( data[1],
                    data[2], duration, data[4] );

                if ( validationError == null )
                {
                    ++digitalNum;
                    MusicStub.addDigital( data[1], data[2], duration,
                        data[4] );
                } else
                {
                    allErrors += errorTemplate + validationError;
                }
            }
            catch ( NumberFormatException e )
            {
                allErrors += errorTemplate + Validation.DURATION_ERROR + "\n";
            }
        }

        System.out.println( allErrors );
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
        double duration, startTime;
        int trackNumber;
        String validationError, errorTemplate, allErrors, parseError;
        String[] data = rowData.split(",");

        //This template is prepended to every error
        errorTemplate = "Discarding line " + lineNum + ": ";
        allErrors = "";
        parseError = "";

        //ensure that the correct number of values is in the row
        if ( data.length != 7 )
        {
            allErrors += errorTemplate +
                "Invalid number of comma seperated values.\n";
        }
        else if ( cassetteNum >= 10 )
        {
            allErrors += errorTemplate +
                "Maximum number of cassettes (10) has been reached.\n";
        }
        else
        {
            try
            {
                //Any numbers are parsed here,
                //  so that they aren't converted twice.
                //Parse error keeps track of which number is being parsed,
                //  so that if a parse method throws,
                //  an accurate error is given to the user.
                parseError = Validation.DURATION_ERROR;
                duration = Double.parseDouble( data[3] );
                parseError = Validation.TRACK_NUMBER_ERROR;
                trackNumber = Integer.parseInt( data[4] );
                parseError = Validation.START_TIME_ERROR;
                startTime = Double.parseDouble( data[5] );

                validationError = Validation.cassetteErrors( data[1],
                    data[2], duration, trackNumber, startTime, data[6] );

                if ( validationError == null )
                {
                    ++cassetteNum;
                    MusicStub.addCassette( data[1], data[2], duration,
                        trackNumber, startTime, data[6].charAt( 0 ) );
                } else
                {
                    allErrors += errorTemplate + validationError;
                }
            }
            catch ( NumberFormatException e )
            {
                allErrors += errorTemplate + parseError + "\n";
            }
        }

        System.out.println( allErrors );
        return cassetteNum;
    }
}
