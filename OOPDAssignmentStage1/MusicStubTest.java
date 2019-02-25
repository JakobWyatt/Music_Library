public class MusicStubTest
{
    public static void main(String args[])
    {
        testRecord();
        testDigital();
        testCassette();
    }

    public static void testRecord()
    {
        String name, artist, playSpeed;
        double duration;
        int trackNumber;
        System.out.println("********* TESTING RECORDS *************");

        // Invalid Everything - negative
        System.out.println("\n***Test 1 - Invalid: name/artist, duration, track, speed");
        name = "";
        artist = "";
        duration = -2.3;
        trackNumber = -2;
        playSpeed = "fast";
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);

        // Invalid Everything - exceed max
        System.out.println("\n***Test 2 - Invalid: duration, track");
        name = "Street Rat";
        artist = "Aladin";
        duration = 10.57;
        trackNumber = 42;
        playSpeed = "33 1/3 RPM";
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);

        // valid
        System.out.println("\n***Test 3 - valid: 45 rpm");
        name = "Street Rat";
        artist = "Aladin";
        duration = 2.57;
        trackNumber = 1;
        playSpeed = "45 RPM";
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);

        // valid
        System.out.println("\n***Test 4 - valid: 78 rpm");
        name = "Part of Your World";
        artist = "Ariel";
        duration = 4.26;
        trackNumber = 6;
        playSpeed = "78 RPM";
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);

        // valid
        System.out.println("\n***Test 5 - valid: 33 1/3 rpm");
        name = "Zero to Hero";
        artist = "the Muses";
        duration = 4.37;
        trackNumber = 3;
        playSpeed = "33 1/3 RPM";
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);

        // add to max count
        System.out.println("\n***Test 6 - max count");
        for(int i =0; i < 7; i++)
        {
            MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);
        }

        //exceed the max count
        MusicStub.addRecord(name, artist, duration, trackNumber, playSpeed);
    }

    public static void testDigital()
    {
        String name, artist, type;
        double duration;
        System.out.println("\n\n********* TESTING DIGITAL *************");

        // Invalid Everything - negative
        System.out.println("\n***Test 1 - Invalid: name/artist, duration, type");
        name = "";
        artist = "";
        duration = -2.3;
        type = "sdf";
        MusicStub.addDigital(name, artist, duration, type);

        // Invalid Everything - exceed max
        System.out.println("\n***Test 2 - Invalid: duration");
        name = "Make a Man Out Of You";
        artist = "Mulan and Chang";
        duration = 10.57;
        type = "Wav";
        MusicStub.addDigital(name, artist, duration, type);

        // valid
        System.out.println("\n***Test 3 - valid: WAV");
        name = "Make a Man Out Of You";
        artist = "Mulan and Chang";
        duration = 2.57;
        type = "WAV";
        MusicStub.addDigital(name, artist, duration, type);

        // valid
        System.out.println("\n***Test 4 - valid: MP3");
        name = "Let It Go";
        artist = "Elsa";
        duration = 4.26;
        type = "MP3";
        MusicStub.addDigital(name, artist, duration, type);

        // valid
        System.out.println("\n***Test 5 - valid: ACC");
        name = "How Far I'll Go";
        artist = "Moana";
        duration = 4.37;
        type = "ACC";
        MusicStub.addDigital(name, artist, duration, type);

        // add to max count
        System.out.println("\n***Test 6 - max count");
        for(int i =0; i < 7; i++)
        {
            MusicStub.addDigital(name, artist, duration, type);
        }

        //exceed the max count
        MusicStub.addDigital(name, artist, duration, type);
    }


    public static void testCassette()
    {
        String name, artist, playSpeed;
        double duration, start;
        int trackNumber;
        char side;
        System.out.println("********* TESTING RECORDS *************");

        // Invalid Everything - negative
        System.out.println("\n***Test 1 - Invalid: name/artist, duration, track, start, side");
        name = "";
        artist = "";
        duration = -2.3;
        trackNumber = -2;
        start = -2.3;
        side = 'z';
        MusicStub.addCassette(name, artist, duration, trackNumber, start, side);

        // Invalid Everything - exceed max
        System.out.println("\n***Test 2 - Invalid: duration, track, start");
        name = "Be Our Guest";
        artist = "The Dishes";
        duration = 10.57;
        trackNumber = 42;
        start = 589.96;
        side = '*';
        MusicStub.addCassette(name, artist, duration, trackNumber, start, side);

        // valid
        System.out.println("\n***Test 3 - valid: side a");
        name = "Be Our Guest";
        artist = "The Dishes";
        duration = 2.57;
        trackNumber = 1;
        start = 2.3;
        side = 'a';
        MusicStub.addCassette(name, artist, duration, trackNumber, start, side);

        // valid
        System.out.println("\n***Test 4 - valid: side A");
        name = "You've got a Friend in Me";
        artist = "Toys Credits";
        duration = 4.26;
        trackNumber = 6;
        start = 65.6;
        side = 'A';
        MusicStub.addCassette(name, artist, duration, trackNumber, start, side);

        // valid
        System.out.println("\n***Test 5 - valid: side B");
        name = "Hakuna Matata";
        artist = "Timon and Pumba and Simba";
        duration = 4.37;
        trackNumber = 3;
        start = 98.67;
        side = 'B';
        MusicStub.addCassette(name, artist, duration, trackNumber, start, side);

        // add to max count
        System.out.println("\n***Test 6 - max count");
        for(int i =0; i < 7; i++)
        {
            MusicStub.addCassette(name, artist, duration, trackNumber, start, side);
        }

        //exceed the max count
        MusicStub.addCassette(name, artist, duration, trackNumber, start, side);

    }

}
