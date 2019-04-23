// FILE: MusicLibrary.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Entry point of Music Library program.
//              Includes high level exception handling.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: UserInterface.java
// Last Mod: 27/09/2018
// Modification history:
//  None

import java.io.*;
import java.util.*;

public class MusicLibrary
{
    public static void main ( String[] args )
    {
        try
        {
            UserInterface ui = new UserInterface();
            ui.menu();
        }
        catch ( IllegalStateException stateEx )
        {
            System.out.println( "Could not read input from the terminal. " +
                "Exiting.");
        }
        catch ( NoSuchElementException eofEx )
        {
            System.out.println( "End Of File (Crtl-D) reached " +
                "on the terminal. Exiting." );
        }
        catch ( FileNotFoundException fileEx )
        {
            System.out.println( "Could not read file. Exiting." );
        }
        catch ( IOException ioEx )
        {
            System.out.println( "An error occured while " +
                "processing the file. Exiting.");
        }
        catch ( IllegalArgumentException argEx )
        {
            System.out.println( "The input file is empty. Exiting." );
        }
    }
}
