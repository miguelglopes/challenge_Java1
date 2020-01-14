package Model;

import App.Config;

import java.math.BigDecimal;
import java.util.HashMap;

public class Callers extends HashMap<PhoneNumber, Caller> {

    /*
    Put call into the right caller
     */
    public void put(Call c) {
        PhoneNumber key = c.getFrom();

        if (this.containsKey(key))
            this.get(key).add(c);
        else
            this.put(key, Caller.getNewCaller(key, c));
    }

    /*
     * In order to get the total cost, we need to first setLongestCaller() for the current map,
     * since this caller will have cost 0.
     */
    public BigDecimal getTotalCost() {
        this.setLongestCaller();
        BigDecimal totalCost = new BigDecimal(0);
        for (Caller c : this.values())
            totalCost = totalCost.add(c.getCost());
        return totalCost;
    }

    /*
     * If there are more than 1 caller with the maximum duration of calls,
     * we still choose only one of them, for simplicity
     */
    private void setLongestCaller() {
        Caller maxCaller = null;
        BigDecimal totalDuration = new BigDecimal(0);
        for (Caller caller : this.values()) {
            if (caller.getTotalDuration().compareTo(totalDuration) > 0) {
                maxCaller = caller;
                totalDuration = caller.getTotalDuration();
            }
        }
        maxCaller.setLongestCaller();
    }

    public void printTotalCost() {
        System.out.println(Config.COST_FORMAT.format(this.getTotalCost()));
    }

    public void printAllCalls() {
        for (Caller caller : this.values())
            caller.printCalls();
    }

    public void printChargedCalls() {
        for (Caller caller : this.values()) {
            if (!caller.isLongestCaller())
                caller.printCalls();
        }
    }
}
