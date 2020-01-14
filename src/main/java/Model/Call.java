package Model;

import App.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Call {

    /*
    since these numbers will be used to calculate the cost, we want them to be bigdecimal
     */
    private static final BigDecimal callThreshold = new BigDecimal(Config.CALL_THRESHOLD);//5min
    private static final BigDecimal firstCost = new BigDecimal(Config.FIRST_COST);//5cents/min
    private static final BigDecimal secondCost = new BigDecimal(Config.SECOND_COST);//2cents/min

    private Date startTime;
    private Date finishTime;
    private PhoneNumber from;
    private PhoneNumber to; // this is never used in this program

    private BigDecimal durationInMinutes;

    public Call(Date startTime, Date finishTime, String from, String to) {
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.setFrom(from);
        this.setTo(to);
        this.setDurationInMinutes();
    }

    void setDurationInMinutes() {
        BigDecimal timeMs = new BigDecimal(this.finishTime.getTime() - this.startTime.getTime());
        BigDecimal convertMinutes = new BigDecimal(60000);
        this.durationInMinutes = timeMs.divide(convertMinutes, 4, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getDurationInMinutes() {
        return durationInMinutes;
    }

    void setStartTime(Date time) {
        this.startTime = time;
    }

    void setFinishTime(Date time) {
        this.finishTime = time;
    }

    PhoneNumber getFrom() {
        return this.from;
    }

    void setFrom(String number) {
        this.from = new PhoneNumber(number);
    }

    void setTo(String number) {
        this.to = new PhoneNumber(number);
    }

    BigDecimal getCost() {

        if (this.getDurationInMinutes().compareTo(callThreshold) > 0)
            return callThreshold.multiply(firstCost).add(
                    (this.getDurationInMinutes().subtract(callThreshold)).multiply(secondCost));
        else
            return this.getDurationInMinutes().multiply(firstCost);
    }

    public void printCall() {
        System.out.println(Config.DURATION_FORMAT.format(this.getDurationInMinutes())
                + "\t\t\t" + Config.COST_FORMAT.format(this.getCost()));
    }
}
