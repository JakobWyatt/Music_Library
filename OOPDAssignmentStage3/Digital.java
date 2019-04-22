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

public class Digital extends Music
{
    public static final String TYPE_ERROR = "The only supported digital " +
        "music file types are 'wav', 'mp3', and 'acc'. " +
        "Check documentation.txt for more information.";




    //private class constants
    //This is private, because final does not prevent the contents of arrays
    //  being modified in java.
    private static final String[] TYPE_VALID =
        { "wav", "mp3", "acc" };




    //private class fields
    private String type;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORT: Address of the new Digital object.
    //ASSERTION: name = "a", artist = "a", duration = 0.01,
    //  type = "wav"

    public Digital()
    {
        super();
        type = TYPE_VALID[0];
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inDigital (Digital)
    //EXPORT: Address of the new Digital object.
    //ASSERTION: The created digital music file will have
    //  identical state to inDigital.

    public Digital( Digital inDigital )
    {
        super( inDigital );
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
        super( inName, inArtist, inDuration );
        if ( inType == null )
        {
            throw new IllegalArgumentException( TYPE_ERROR );
        }
        String inTypeLower = inType.toLowerCase();

        validateDigital( inTypeLower );
        type = inTypeLower;
    }




    //CSV CONSTRUCTOR
    //IMPORTS: csv (String)
    //EXPORT: None
    //ASSERTIONS: Creates the object if the csv line is valid,
    //  and FAILS otherwise.

    public Digital( String csv )
    {
        super();
        String[] data;
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

        typeLower = data[4].toLowerCase();

        validateDigital( typeLower );

        super.inputCSV( Music.subArrayCopy( data, 1, 4 ) );
        type = typeLower;
    }




    //ACCESSORS

    //SUBMODULE: getType
    //IMPORTS: None
    //EXPORT: type
    //ASSERTIONS: None

    public String getType()
    {
        return type;
    }




    //MUTATORS

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
    @Override
    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof Digital )
        {
            Digital inDigital = (Digital)inObj;
            same =  super.equals( inDigital ) &&
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
    @Override
    public String toString()
    {
        String digitalString = super.toString() + ", type: " + type;
        return digitalString;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORT: CSV string representation of the data.
    //ASSERTIONS: None
    @Override
    public String outputCSV()
    {
        String output = "D," + super.outputCSV() + "," + type;
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
        String playString = super.getName() + " requires the " + type +
            " codec to play.";
        return playString;
    }




    //NAME: validateDigital
    //PURPOSE: Determines if digital data is valid.
    //IMPORTS: inName (String), inArtist (String), inDuration (Real),
    //  inType (String)
    //EXPORTS: None
    //ASSERTIONS: Throws if invalid

    private static void validateDigital( String inType )
    {
        if ( !validateType( inType ) )
        {
            throw new IllegalArgumentException( TYPE_ERROR );
        }
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
}
