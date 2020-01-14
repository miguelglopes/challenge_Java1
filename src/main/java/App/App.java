package App;

import Controller.Parser;
import Exceptions.*;
import Model.Callers;

public class App {

    public static void main(String[] args) throws InvalidArgumentsException, InvalidInputLineException, InvalidFileException, InvalidTimeException, EmptyFileException {

        if (validateArgs(args)) {
            Callers cm = Parser.getCallers(args[0]);
            cm.printTotalCost();

            printOptionalArgs(cm);
        }

    }

    private static void printOptionalArgs(Callers cm) {
        //validate if optional argument is active
        if (Config.ARGS_MAP.get(Config.PRINT_ALL_CALLS)) {
            System.out.println("--------- All Calls ---------");
            System.out.println("Duration(min)\tCost(Cents)");
            cm.printAllCalls();
        }

        //validate if optional argument is active
        if (Config.ARGS_MAP.get(Config.PRINT_CHARGED_CALLS)) {
            System.out.println("--------- Charged Calls ---------");
            System.out.println("Duration(min)\tCost(Cents)");
            cm.printChargedCalls();
        }
    }

    private static boolean validateArgs(String[] args) throws InvalidArgumentsException {
        if(args.length <= 0)
            throw new InvalidArgumentsException();
        else if (args.length >= 2) { //activate optional arguments
            for (int i = 1; i < args.length; i++) {
                if (Config.ARGS_MAP.containsKey(args[i]))
                    Config.ARGS_MAP.put(args[i], true);
                else
                    throw new InvalidArgumentsException();
            }
        } else if (args[0].equals(Config.HELP_ARG)) { //help
            System.out.println(Config.HELP_MESSAGE);
            return false;
        }

        return true;

    }
}
