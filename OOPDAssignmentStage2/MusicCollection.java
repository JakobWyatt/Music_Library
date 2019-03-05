// FILE: MusicCollection.java
// AUTHOR: Jakob Wyatt
// UNIT: OOPD
// PURPOSE: Class that controls arrays of music objects.
// REFERENCES: None
// COMMENTS: None
// REQUIRES: None
// Last Mod: 11/10/2018
// Modification history:
//  None

public class MusicCollection
{
    //constants

    public static final int MUSIC_MAX = 10;

    //class fields
    private Record[] records = new Record[MUSIC_MAX];
    private Digital[] digitals = new Digital[MUSIC_MAX];
    private Cassette[] cassettes = new Cassette[MUSIC_MAX];
    private int recordNum;
    private int digitalNum;
    private int cassetteNum;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORTS: Address of new MusicCollection object.
    //ASSERTIONS: Music arrays will be empty

    public MusicCollection()
    {
        recordNum = 0;
        digitalNum = 0;
        cassetteNum = 0;
    }




    //ALTERNATE CONSTRUCTOR
    //IMPORTS: inRecords (ARRAY OF Record), inDigitals (ARRAY OF Digitals),
    //  inCassettes (ARRAY OF Cassettes)
    //EXPORTS: Address of new MusicCollection object.
    //ASSERTIONS: All class data will be read in from the arrays.

    public MusicCollection(
        Record[] inRecords, Digital[] inDigitals, Cassette[] inCassettes )
    {
        this.setRecords( inRecords );
        this.setDigitals( inDigitals );
        this.setCassettes( inCassettes );
    }




    //COPY CONSTRUCTOR
    //IMPORTS: inMusicCollection (MusicCollection)
    //EXPORT: Address of new MusicCollection object.
    //ASSERTIONS: The created MusicCollection will have identical state to
    //  inMusicCollection

    public MusicCollection( MusicCollection inMusicCollection )
    {
        if ( inMusicCollection == null )
        {
            throw new IllegalArgumentException(
                "Music collection cannot be null" );
        }
        this.setRecords( inMusicCollection.getRecords() );
        this.setDigitals( inMusicCollection.getDigitals() );
        this.setCassettes( inMusicCollection.getCassettes() );
    }




    //SUBMODULE: getRecords
    //IMPORTS: None
    //EXPORT: Copy of records
    //ASSERTIONS: Only the filled portion of the array will be copied.

    public Record[] getRecords()
    {
        Record[] outRecords = new Record[recordNum];
        for ( int i = 0; i != recordNum; i++ )
        {
            outRecords[i] = records[i].clone();
        }
        return outRecords;
    }




    //SUBMODULE: getDigitals
    //IMPORTS: None
    //EXPORT: Copy of digital music files
    //ASSERTIONS: Only the filled portion of the array will be copied.

    public Digital[] getDigitals()
    {
        Digital[] outDigitals = new Digital[digitalNum];
        for ( int i = 0; i != digitalNum; i++ )
        {
            outDigitals[i] = digitals[i].clone();
        }
        return outDigitals;
    }




    //SUBMODULE: getCassettes
    //IMPORTS: None
    //EXPORT: Copy of cassettes
    //ASSERTIONS: Only the filled portion of the array will be copied.

    public Cassette[] getCassettes()
    {
        Cassette[] outCassettes = new Cassette[cassetteNum];
        for ( int i = 0; i != cassetteNum; i++ )
        {
            outCassettes[i] = cassettes[i].clone();
        }
        return outCassettes;
    }




    //SUBMODULE: setRecords
    //IMPORTS: inRecords (ARRAY OF Records)
    //EXPORT: None
    //ASSERTIONS: All elements will be copied into records.
    //  If any elements are null, an IllegalArgumentException will be thrown.

    public void setRecords( Record[] inRecords )
    {
        if ( inRecords == null )
        {
            throw new IllegalArgumentException(
                "Record array cannot be null." );
        }
        if ( inRecords.length > MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        //records is mutated after null checking,
        //  so that we guarentee strong exception safety.
        for ( int i = 0; i != inRecords.length; i++ )
        {
            if ( inRecords[i] == null )
            {
                throw new IllegalArgumentException(
                    "Records cannot be null." );
            }
        }
        for ( int i = 0; i != inRecords.length; i++ )
        {
            records[i] = inRecords[i].clone();
        }
        recordNum = inRecords.length;
    }




    //SUBMODULE: setDigitals
    //IMPORTS: inDigitals (ARRAY OF Digitals)
    //EXPORT: None
    //ASSERTIONS: All elements will be copied into digitals.
    //  If any elements are null, an IllegalArgumentException will be thrown.

    public void setDigitals( Digital[] inDigitals )
    {
        if ( inDigitals == null )
        {
            throw new IllegalArgumentException(
                "Digital array cannot be null." );
        }
        if ( inDigitals.length > MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        //digitals is mutated after null checking,
        //  so that we guarentee strong exception safety.
        for ( int i = 0; i != inDigitals.length; i++ )
        {
            if ( inDigitals[i] == null )
            {
                throw new IllegalArgumentException(
                    "Digitals cannot be null." );
            }
        }
        for ( int i = 0; i != inDigitals.length; i++ )
        {
            digitals[i] = inDigitals[i].clone();
        }
        digitalNum = inDigitals.length;
    }




    //SUBMODULE: setCassettes
    //IMPORTS: inCassettes (ARRAY OF Cassettes)
    //EXPORT: None
    //ASSERTIONS: All elements will be copied into cassettes.
    //  If any elements are null, an IllegalArgumentException will be thrown.

    public void setCassettes( Cassette[] inCassettes )
    {
        if ( inCassettes == null )
        {
            throw new IllegalArgumentException(
                "Cassette array cannot be null." );
        }
        if ( inCassettes.length > MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        //cassettes is mutated after null checking,
        //  so that we guarentee strong exception safety.
        for ( int i = 0; i != inCassettes.length; i++ )
        {
            if ( inCassettes[i] == null )
            {
                throw new IllegalArgumentException(
                    "Cassettes cannot be null." );
            }
        }
        for ( int i = 0; i != inCassettes.length; i++ )
        {
            cassettes[i] = inCassettes[i].clone();
        }
        cassetteNum = inCassettes.length;
    }




    //SUBMODULE: addRecord
    //IMPORTS: Adds a record to the MusicCollection.
    //EXPORT: None
    //ASSERTIONS: Will fail if the array is full.

    public void addRecord( Record inRecord )
    {
        if ( inRecord == null )
        {
            throw new IllegalArgumentException( "Record cannot be null." );
        }
        if ( recordNum >= MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        records[recordNum] = inRecord.clone();
        recordNum++;
    }




    //SUBMODULE: addDigital
    //IMPORTS: Adds a digital music file to the MusicCollection.
    //EXPORT: None
    //ASSERTIONS: Will fail if the array is full.

    public void addDigital( Digital inDigital )
    {
        if ( inDigital == null )
        {
            throw new IllegalArgumentException( "Digital cannot be null." );
        }
        if ( digitalNum >= MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        digitals[digitalNum] = inDigital.clone();
        digitalNum++;
    }




    //SUBMODULE: addCassette
    //IMPORTS: Adds a cassette to the MusicCollection.
    //EXPORT: None
    //ASSERTIONS: Will fail if the array is full.

    public void addCassette( Cassette inCassette )
    {
        if ( inCassette == null )
        {
            throw new IllegalArgumentException( "Cassette cannot be null." );
        }
        if ( cassetteNum >= MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        cassettes[cassetteNum] = inCassette.clone();
        cassetteNum++;
    }




    //SUBMODULE: equals
    //IMPORTS: inObj (Object)
    //EXPORTS: same (Boolean)
    //ASSERTIONS: Two MusicCollections are the same if the filled
    //  portions of the arrays are the same.

    public boolean equals( Object inObj )
    {
        boolean same = false;
        if ( inObj instanceof MusicCollection )
        {
            MusicCollection inMusicCollection = (MusicCollection)inObj;
            same =  this.recordsEqual( inMusicCollection.getRecords() ) &&
                    this.digitalsEqual( inMusicCollection.getDigitals() ) &&
                    this.cassettesEqual( inMusicCollection.getCassettes() );
        }
        return same;
    }




    //SUBMODULE: toString
    //IMPORTS: None
    //EXPORTS: MusicCollectionString (String)
    //ASSERTIONS: MusicCollectionString will be a textual
    //  representation of the information in the class.

    public String toString()
    {
        String MusicCollectionString = "";
        for ( int i = 0; i != recordNum; i++ )
        {
            MusicCollectionString += records[i].toString() + "\n";
        }
        for ( int i = 0; i != digitalNum; i++ )
        {
            MusicCollectionString += digitals[i].toString() + "\n";
        }
        for ( int i = 0; i != cassetteNum; i++ )
        {
            MusicCollectionString += cassettes[i].toString() + "\n";
        }

        return MusicCollectionString;
    }




    //SUBMODULE: clone
    //IMPORTS: None
    //EXPORTS: Clone of MusicCollection
    //ASSERTIONS: None

    public MusicCollection clone()
    {
        return new MusicCollection( this );
    }




    //SUBMODULE: duration
    //IMPORTS: None
    //EXPORTS: The total duration of all songs in the library (Real)
    //ASSERTIONS: None

    public double duration()
    {
        double totalDuration = 0.0;

        for ( int i = 0; i != recordNum; i++ )
        {
            totalDuration = records[i].addDuration( totalDuration );
        }
        for ( int i = 0; i != digitalNum; i++ )
        {
            totalDuration = digitals[i].addDuration( totalDuration );
        }
        for ( int i = 0; i != cassetteNum; i++ )
        {
            totalDuration = cassettes[i].addDuration( totalDuration );
        }

        return totalDuration;
    }




    //SUBMODULE: playAll
    //IMPORTS: None
    //EXPORTS: instructions (String)
    //ASSERTIONS: None

    public String playAll()
    {
        String instructions = "";
        for ( int i = 0; i != recordNum; i++ )
        {
            instructions += records[i].play() + "\n";
        }
        for ( int i = 0; i != digitalNum; i++ )
        {
            instructions += digitals[i].play() + "\n";
        }
        for ( int i = 0; i != cassetteNum; i++ )
        {
            instructions += cassettes[i].play() + "\n";
        }

        return instructions;
    }




    //SUBMODULE: playSong
    //IMPORTS: inName (String)
    //EXPORT: instructions (String)
    //ASSERTIONS: The play instructions will be for the first found song
    //  with the given name. A music object is not returned at this stage,
    //  due to the type limitations in Stage 2 of the assignment.

    public String playSong( String inName )
    {
        int rIter = 0;
        int dIter = 0;
        int cIter = 0;
        String instructions = "";
        if ( inName == null )
        {
            throw new IllegalArgumentException( "Name cannot be null." );
        }
        //set each iterator to the first value of each array
        //  which has the correct name.
        //If there are no elements that have the correct name,
        //  the iterator is set to the size of the array.
        while ( rIter != recordNum &&
                !inName.equals( records[rIter].getName() ) )
        {
            rIter++;
        }
        while ( dIter != digitalNum &&
                !inName.equals( digitals[dIter].getName() ) )
        {
            dIter++;
        }
         while ( cIter != cassetteNum &&
            !inName.equals( cassettes[cIter].getName() ) )
        {
            cIter++;
        }

        //get the instructions for each valid input,
        //  and throw an exception if the name couldnt be found.
        if ( rIter == recordNum )
        {
            if ( dIter == digitalNum )
            {
                if ( cIter == cassetteNum )
                {
                    throw new IllegalArgumentException(
                        "The song was not found in the library." );
                } else
                {
                    instructions = cassettes[cIter].play();
                }
            } else
            {
                instructions = digitals[dIter].play();
            }
        } else
        {
            instructions = records[rIter].play();
        }

        return instructions;
    }




    //SUBMODULE: searchRecords
    //IMPORTS: inPlaySpeed (String)
    //EXPORT: Array of all records that have the given play speed.
    //ASSERTIONS: None

    public Record[] searchRecords( String inPlaySpeed )
    {
        //This method only calls search once per record.
        //First, an array of indexes is created,
        //  storing each valid index number.
        //Once this is done, the valid indexes are copied into the final
        //  record array.
        int indexNum = 0;
        int[] indexes = new int[recordNum];

        for ( int i = 0; i != recordNum; i++ )
        {
            if ( records[i].search( inPlaySpeed ) )
            {
                indexes[indexNum] = i;
                indexNum++;
            }
        }

        Record[] outRecords = new Record[indexNum];
        for ( int i = 0; i != indexNum; i++ )
        {
            outRecords[i] = records[indexes[i]].clone();
        }

        return outRecords;
    }




    //SUBMODULE: searchDigitals
    //IMPORTS: inType (String)
    //EXPORT: Array of all digitals that have the given type.
    //ASSERTIONS: None

    public Digital[] searchDigitals( String inType )
    {
        //This method only calls search once per digital.
        //First, an array of indexes is created,
        //  storing each valid index number.
        //Once this is done, the valid indexes are copied into the final
        //  digital array.
        int indexNum = 0;
        int[] indexes = new int[digitalNum];

        for ( int i = 0; i != digitalNum; i++ )
        {
            if ( digitals[i].search( inType ) )
            {
                indexes[indexNum] = i;
                indexNum++;
            }
        }

        Digital[] outDigitals = new Digital[indexNum];
        for ( int i = 0; i != indexNum; i++ )
        {
            outDigitals[i] = digitals[indexes[i]].clone();
        }

        return outDigitals;
    }




    //SUBMODULE: searchCassettes
    //IMPORTS: inSide (String)
    //EXPORT: Array of all cassettes that have the given side.
    //ASSERTIONS: None

    public Cassette[] searchCassettes( String inSide )
    {
        //This method only calls search once per cassette.
        //First, an array of indexes is created,
        //  storing each valid index number.
        //Once this is done, the valid indexes are copied into the final
        //  cassette array.
        int indexNum = 0;
        int[] indexes = new int[cassetteNum];

        for ( int i = 0; i != cassetteNum; i++ )
        {
            if ( cassettes[i].search( inSide ) )
            {
                indexes[indexNum] = i;
                indexNum++;
            }
        }

        Cassette[] outCassettes = new Cassette[indexNum];
        for ( int i = 0; i != indexNum; i++ )
        {
            outCassettes[i] = cassettes[indexes[i]].clone();
        }

        return outCassettes;
    }




    //SUBMODULE: inputCSV
    //PURPOSE: Inputs CSV encoded information
    //  (as shown in documentation.txt), and uses it to add music to
    //  MusicCollection. Exports a transcript of correct and incorrect
    //  csv rows.
    //IMPORTS: csv (ARRAY OF String)
    //EXPORT: transcript (String)
    //ASSSERTIONS: None

    public String inputCSV( String[] csv )
    {
        String transcript = "";

        if ( csv == null )
        {
            throw new IllegalArgumentException( "Array cannot be null." );
        }
        if ( csv.length == 0 )
        {
            throw new IllegalArgumentException( "Array cannot be empty." );
        }
        //The array is checked for nulls before calling processline.
        // This ensures that the class has strong exception safety.
        for ( int i = 0; i != csv.length; i++ )
        {
            if ( csv[i] == null )
            {
                throw new IllegalArgumentException(
                    "Elements of array cannot be null." );
            }
        }

        for ( int i = 0; i != csv.length; i++ )
        {
            transcript += this.processLine( csv[i], i ) + "\n";
        }
        return transcript;
    }




    //SUBMODULE: outputCSV
    //IMPORTS: None
    //EXPORTS: Outputs the data in MusicCollection as a CSV file.
    //ASSERTIONS: Throws IllegalArgumentException if fileIO failed.

    public String[] outputCSV()
    {
        String[] output = new String[recordNum + digitalNum + cassetteNum];
        int outputNum = 0;
        for ( int i = 0; i != recordNum; i++ )
        {
            output[outputNum] = records[i].outputCSV();
            outputNum++;
        }
        for ( int i = 0; i != digitalNum; i++ )
        {
            output[outputNum] = digitals[i].outputCSV();
            outputNum++;
        }
        for ( int i = 0; i != cassetteNum; i++ )
        {
            output[outputNum] = cassettes[i].outputCSV();
            outputNum++;
        }

        return output;
    }




    //SUBMODULE: processLine
    //IMPORTS: data (String), lineNum (Integer)
    //EXPORTS: transcript (String)
    //ASSERTIONS: None

    private String processLine( String data, int lineNum )
    {
        String transcript;
        String discard = "Discarding line " + ( lineNum + 1 ) + ": ";

        if ( data.length() == 0 )
        {
            transcript = discard + "Empty CSV row.";
        } else
        {
            switch ( data.charAt( 0 ) )
            {
                case 'R': case 'r':
                    try
                    {
                        this.addRecord( new Record( data ) );
                        transcript = "Record details correct";
                    }
                    catch ( IllegalArgumentException ex )
                    {
                        transcript = discard + ex.getMessage();
                    }
                    break;
                case 'D': case 'd':
                    try
                    {
                        this.addDigital( new Digital( data ) );
                        transcript = "Digital details correct";
                    }
                    catch ( IllegalArgumentException ex )
                    {
                        transcript = discard + ex.getMessage();
                    }
                    break;
                case 'C': case 'c':
                    try
                    {
                        this.addCassette( new Cassette( data ) );
                        transcript = "Cassette details correct.";
                    }
                    catch ( IllegalArgumentException ex )
                    {
                        transcript = discard + ex.getMessage();
                    }
                    break;
                default:
                    transcript = discard + "Invalid Music Type.";
            }
        }

        return transcript;
    }




    //SUBMODULE: recordsEqual
    //IMPORTS: inRecords (ARRAY OF Records)
    //EXPORTS: same (Boolean)
    //ASSERTIONS: Two records arrays are equal if they have the
    //  same number of elements, and equivalent array elements.

    private boolean recordsEqual( Record[] inRecords )
    {
        boolean same = false;
        if ( inRecords.length == recordNum )
        {
            //we set same to true here, as only 2 single records must
            //  not be equal for the arrays to be inequal.
            same = true;
            for ( int i = 0; i != recordNum; i++ )
            {
                if ( !records[i].equals( inRecords[i] ) )
                {
                    same = false;
                }
            }
        }

        return same;
    }




    //SUBMODULE: digitalsEqual
    //IMPORTS: inDigitals (ARRAY OF Digitals)
    //EXPORTS: same (Boolean)
    //ASSERTIONS: Two digitals arrays are equal if they have the
    //  same number of elements, and equivalent array elements.

    private boolean digitalsEqual( Digital[] inDigitals )
    {
        boolean same = false;
        if ( inDigitals.length == digitalNum )
        {
            //we set same to true here, as only 2 single digital music
            //  files must not be equal for the arrays to be inequal.
            same = true;
            for ( int i = 0; i != digitalNum; i++ )
            {
                if ( !digitals[i].equals( inDigitals[i] ) )
                {
                    same = false;
                }
            }
        }

        return same;
    }




    //SUBMODULE: cassettesEqual
    //IMPORTS: inCassettes (ARRAY OF Cassette)
    //EXPORTS: same (Boolean)
    //ASSERTIONS: Two cassettes arrays are equal if they have the
    //  same number of elements, and equivalent array elements.

    private boolean cassettesEqual( Cassette[] inCassettes )
    {
        boolean same = false;
        if ( inCassettes.length == cassetteNum )
        {
            //we set same to true here, as only 2 single cassettes must
            //  not be equal for the arrays to be inequal.
            same = true;
            for ( int i = 0; i != cassetteNum; i++ )
            {
                if ( !cassettes[i].equals( inCassettes[i] ) )
                {
                    same = false;
                }
            }
        }

        return same;
    }
}
