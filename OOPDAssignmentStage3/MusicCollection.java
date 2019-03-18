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

    public static final int MUSIC_MAX = 30;

    //class fields
    private Music[] music;
    private int musicNum;




    //DEFAULT CONSTRUCTOR
    //IMPORTS: None
    //EXPORTS: Address of new MusicCollection object.
    //ASSERTIONS: Music arrays will be empty

    public MusicCollection()
    {
        music = new Music[MUSIC_MAX];
        musicNum = 0;
    }




    //ALTERNATE CONSTRUCTOR
    //IMPORTS: inMusic (ARRAY OF Music)
    //EXPORTS: Address of new MusicCollection object.
    //ASSERTIONS: All class data will be read in from the arrays.

    public MusicCollection( Music[] inMusic )
    {
        this.setMusic( inMusic );
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
        this.setMusic( inMusicCollection.getMusic() );
    }




    //SUBMODULE: getMusic
    //IMPORTS: None
    //EXPORT: Copy of music
    //ASSERTIONS: Only the filled portion of the array will be copied.

    public Music[] getMusic()
    {
        Music[] outMusic = new Music[musicNum];
        for ( int i = 0; i != musicNum; i++ )
        {
            outMusic[i] = music[i].clone();
        }
        return outMusic;
    }




    //SUBMODULE: setMusic
    //IMPORTS: inMusic (ARRAY OF Music)
    //EXPORT: None
    //ASSERTIONS: All elements will be copied into music.
    //  If any elements are null, an IllegalArgumentException will be thrown.

    public void setMusic( Music[] inMusic )
    {
        if ( inMusic == null )
        {
            throw new IllegalArgumentException(
                "Music array cannot be null." );
        }
        if ( inMusic.length > MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        //music is mutated after null checking,
        //  so that we guarentee strong exception safety.
        for ( int i = 0; i != inMusic.length; i++ )
        {
            if ( inMusic[i] == null )
            {
                throw new IllegalArgumentException(
                    "Music cannot be null." );
            }
        }
        for ( int i = 0; i != inMusic.length; i++ )
        {
            music[i] = inMusic[i].clone();
        }
        musicNum = inMusic.length;
    }




    //SUBMODULE: addMusic
    //IMPORTS: Adds music to the MusicCollection.
    //EXPORT: None
    //ASSERTIONS: Will fail if the array is full.

    public void addMusic( Music inMusic )
    {
        if ( inMusic == null )
        {
            throw new IllegalArgumentException( "Music cannot be null." );
        }
        if (musicNum >= MUSIC_MAX )
        {
            throw new IllegalArgumentException(
                "Only " + MUSIC_MAX +
                " of each music type can be accepted." );
        }
        music[musicNum] = inMusic.clone();
        musicNum++;
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
            same = arrayEqual(
                inMusicCollection.getMusic(), this.getMusic() );
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
        for ( int i = 0; i != musicNum; i++ )
        {
            MusicCollectionString += music[i].toString() + "\n";
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

        for ( int i = 0; i != musicNum; i++ )
        {
            totalDuration = Music.addDuration(
                totalDuration, music[i].getDuration() );
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
        for ( int i = 0; i != musicNum; i++ )
        {
            instructions += music[i].play() + "\n";
        }

        return instructions;
    }




    //SUBMODULE: playSong
    //IMPORTS: inName (String)
    //EXPORT: instructions (String)
    //ASSERTIONS: The play instructions will be for the first found song
    //  with the given name.

    public String playSong( String inName )
    {
        int musicIter = 0;
        if ( inName == null )
        {
            throw new IllegalArgumentException( "Name cannot be null." );
        }
        //set the iterator to the first valid element
        while ( musicIter != musicNum &&
                !inName.equals( music[musicIter].getName() ) )
        {
            musicIter++;
        }

        //throw an exception if the name couldnt be found
        if ( musicIter == musicNum )
        {
            throw new IllegalArgumentException(
                "The song was not found in the library." );
        }

        return music[musicIter].play();
    }




    //SUBMODULE: searchMusic
    //IMPORTS: inSearch (String)
    //EXPORT: Array of all Music objects that return true to
    //  .search( inSearch )
    //ASSERTIONS: None

    public Music[] searchMusic( String inSearch )
    {
        //This method only calls search once per music object.
        //First, an array of indexes is created,
        //  storing each valid index number.
        //Once this is done, the valid indexes are copied into the final
        //  music array.
        int indexNum = 0;
        int[] indexes = new int[musicNum];

        for ( int i = 0; i != musicNum; i++ )
        {
            if ( music[i].search( inSearch ) )
            {
                indexes[indexNum] = i;
                indexNum++;
            }
        }

        Music[] outMusic = new Music[indexNum];
        for ( int i = 0; i != indexNum; i++ )
        {
            outMusic[i] = music[indexes[i]].clone();
        }

        return outMusic;
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
    //ASSERTIONS: None

    public String[] outputCSV()
    {
        String[] output = new String[musicNum];
        for ( int i = 0; i != musicNum; i++ )
        {
            output[i] = music[i].outputCSV();
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
                        this.addMusic( new Record( data ) );
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
                        this.addMusic( new Digital( data ) );
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
                        this.addMusic( new Cassette( data ) );
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




    //SUBMODULE: arrayEqual
    //IMPORTS: x, y (ARRAY OF Object)
    //EXPORTS: same (Boolean)
    //ASSERTIONS: Two arrays are equal if each elements
    //  .equals() method returns true
    //Arrays must not be null

    private static boolean arrayEqual( Object[] x, Object[] y )
    {
        boolean same = false;
        if ( x.length == y.length )
        {
            same = true;
            for ( int i = 0; i != x.length; i++ )
            {
                if ( !x[i].equals( y[i] ) )
                {
                    same = false;
                }
            }
        }

        return same;
    }
}
