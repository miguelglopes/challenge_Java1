package Controller;

import App.Config;
import Exceptions.EmptyFileException;
import Exceptions.InvalidFileException;
import Exceptions.InvalidInputLineException;
import Exceptions.InvalidTimeException;
import Model.Call;
import Model.Callers;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Parser {

    public static Callers getCallers(String file) throws InvalidInputLineException, InvalidFileException,
            InvalidTimeException, EmptyFileException {

        //read lines
        List<String> lines = readLinesFromFile(file);

        //convert lines to calls and add them to callers
        Callers callers = new Callers();
        for (String line : lines)
            callers.put(parseCall(line));

        return callers;
    }

    private static Call parseCall(String line) throws InvalidInputLineException, InvalidTimeException {

        String[] parsedLine = line.split(";");

        //validate length
        if (parsedLine.length != 4)
            throw new InvalidInputLineException("Each line should have 4 elements separated by ';'");

        //validate start time
        Date startTime;
        try {
            startTime = Config.DATE_FORMAT.parse(parsedLine[0]);
        } catch (ParseException e) {
            throw new InvalidTimeException("Invalid start time " + parsedLine[0]);
        }

        //validate finish time
        Date finishTime;
        try {
            finishTime = Config.DATE_FORMAT.parse(parsedLine[1]);
        } catch (ParseException e) {
            throw new InvalidTimeException("Invalid finish time " + parsedLine[1]);
        }

        //validate time chronology
        if (startTime.after(finishTime))
            throw new InvalidTimeException("Finish time " + parsedLine[1] + " should come after start time " + parsedLine[0] + ".");

        //It is not needed, for the purpose of this exercise, to validate 'from' and 'to' arguments.

        return new Call(startTime, finishTime, parsedLine[2], parsedLine[3]);
    }

    private static List<String> readLinesFromFile(String filename) throws InvalidFileException, EmptyFileException {
        List<String> lines = new ArrayList<String>();
        try {

            Scanner sc = new Scanner(new File(filename), "utf-8");
            while (sc.hasNextLine())
                lines.add(sc.nextLine());

            //check empty file
            if (lines.size() <= 0)
                throw new EmptyFileException("The input file had no lines to read.");

        } catch (FileNotFoundException e) {
            throw new InvalidFileException(e.getMessage());
        }
        return lines;
    }

}
