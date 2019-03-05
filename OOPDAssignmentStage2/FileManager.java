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
    //NAME: writeFile
    //PURPOSE: A string array is saved to fileName.
    //  An exception will be thrown if the output fails.
    //IMPORTS: fileName (String), fileData (ARRAY OF String)
    //EXPORT: None
    //Assertions:
    //  Pre: None
    //  Post: None
    //REMARKS: If the file cannot be found, FileNotFoundException is thrown.
    //  If there was an error writing the file, IOException is thrown.
    //  If there was an invalid argument, IllegalArgumentException is thrown.

    public static void writeFile( String fileName, String[] fileData )
        throws IOException
    {
        if ( fileName == null || fileData == null )
        {
            throw new IllegalArgumentException(
                "Filename and Filedata cannot be null.");
        }
        PrintWriter writer = null;

        try
        {
            //PrintWriter is buffered to memory,
            //  and is therefore fast, by default.
            writer = new PrintWriter( fileName );
            for ( int i = 0; i != fileData.length; i++ )
            {
                if ( fileData[i] == null )
                {
                    throw new IllegalArgumentException(
                        "Filedata lines cannot be null." );
                }
                writer.println( fileData[i] );
            }
            writer.close();
            if ( writer.checkError() ) {
                throw new IOException(
                    "Error: Could not write to the file." );
            }
        }
        //writer.close() doesn't need to be called in this catch statement,
        //  as this exception is thrown during initialization of writer.
        catch ( FileNotFoundException fileEx )
        {
            throw new FileNotFoundException(
                "Error: Could not create the file." );
        }
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

    public static String[] readFile( String fileName ) throws IOException
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
}
