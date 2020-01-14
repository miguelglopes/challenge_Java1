package App;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/*
 * General Constants needed.
 */
public final class Config {

    /*
    Call Config
     */
    public static final double CALL_THRESHOLD = 5;//5min
    public static final double FIRST_COST = 5;//5cents/min
    public static final double SECOND_COST = 2;//2cents/min

    /*
    Print Config
     */
    public static final NumberFormat COST_FORMAT = new DecimalFormat("#0.00");
    public static final NumberFormat DURATION_FORMAT = new DecimalFormat("#0.000");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    /*
    Arguments Config
     */
    public static final String PROGRAM_NAME = "totalCallsCost";
    public static final String HELP_ARG = "help";
    public static final String PRINT_ALL_CALLS = "-printAllCalls";
    public static final String PRINT_CHARGED_CALLS = "-printChargedCalls";
    public static final String USAGE = "usage: " + PROGRAM_NAME + " <input_file> [<optional_args>]";
    public static final String TRY_HELP = "\nTry '" + Config.PROGRAM_NAME + " help' for more information on input restrictions.";
    //this map will hold the optional arguments and if they are active or not
    public static Map<String, Boolean> ARGS_MAP;
    static {
        ARGS_MAP = new HashMap<String, Boolean>();
        ARGS_MAP.put(PRINT_ALL_CALLS, false);
        ARGS_MAP.put(PRINT_CHARGED_CALLS, false);

    }
    public static final String HELP_MESSAGE = USAGE +
            "\n<input_file> should have multiple lines with the following format: " +
            "<time_of_start>;<time_of_finish>;<call_from>;<call_to>" +
            "\n<time_of_start> and <time_of_finish> should be in the format " + DATE_FORMAT.toPattern() +
            "\nExample: 09:11:30;09:15:22;+351914374373;+351215355312" +
            "\nAvailable <optional_arg>: " + ARGS_MAP.keySet().toString();


}
