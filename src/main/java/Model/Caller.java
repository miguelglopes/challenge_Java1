package Model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Caller is a list of calls.
 */
public class Caller extends ArrayList<Call> {
    private boolean isLongestCaller = false;
    private PhoneNumber number;

    public static Caller getNewCaller(PhoneNumber number, Call call) {
        Caller caller = new Caller();
        caller.setNumber(number);
        caller.add(call);
        return caller;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    private void setNumber(PhoneNumber number) {
        this.number = number;
    }

    void setLongestCaller() {
        isLongestCaller = true;
    }

    boolean isLongestCaller() {
        return isLongestCaller;
    }

    BigDecimal getCost() {
        BigDecimal cost = new BigDecimal(0);
        if (isLongestCaller)
            return cost;
        else {
            for (Call call : this)
                cost = cost.add(call.getCost());
            return cost;
        }
    }

    BigDecimal getTotalDuration() {
        BigDecimal duration = new BigDecimal(0);
        for (Call call : this)
            duration = duration.add(call.getDurationInMinutes());
        return duration;
    }

    public void printCalls() {
        System.out.println(this.getNumber().id);
        for (Call c : this)
            c.printCall();
    }
}
