package App;

import Exceptions.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ClassLoader classLoader = getClass().getClassLoader();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testHelp() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{Config.HELP_ARG};
        App.main(args);
        Assert.assertEquals(Config.HELP_MESSAGE+"\r\n", outContent.toString());
    }

    @Test
    public void testOneLineFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputOneLine.txt").getPath()};
        App.main(args);
        Assert.assertEquals(Config.COST_FORMAT.format(0.00)+"\r\n", outContent.toString());
    }

    @Test
    public void testTwoLinesFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputTwoLines.txt").getPath()};
        App.main(args);
        Assert.assertEquals(Config.COST_FORMAT.format(19.33)+"\r\n", outContent.toString());
    }

    @Test
    public void testTwoEqualLengthLinesFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputTwoEqualLengthLines.txt").getPath()};
        App.main(args);
        Assert.assertEquals(Config.COST_FORMAT.format(29.73)+"\r\n", outContent.toString());
    }

    @Test
    public void testSimpleFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("input.txt").getPath()};
        App.main(args);
        Assert.assertEquals(Config.COST_FORMAT.format(48.35)+"\r\n", outContent.toString());
    }

    @Test
    public void testLongFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputLong.txt").getPath()};
        App.main(args);
        Assert.assertEquals(Config.COST_FORMAT.format(6979387.95)+"\r\n", outContent.toString());
    }

    @Test(expected = EmptyFileException.class)
    public void testEmptyFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputEmpty.txt").getPath()};
        App.main(args);
    }

    @Test(expected = InvalidFileException.class)
    public void testInvalidFile() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        App.main(new String[]{"invalidFile"});
    }

    @Test(expected = InvalidTimeException.class)
    public void testWrongChronology() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputWrongChronology.txt").getPath()};
        App.main(args);
    }

    @Test(expected = InvalidTimeException.class)
    public void testWrongTime() throws InvalidInputLineException, InvalidArgumentsException, InvalidTimeException, InvalidFileException, EmptyFileException {
        String[] args = new String[]{classLoader.getResource("inputWrongStartTime.txt").getPath()};
        App.main(args);
    }
}
