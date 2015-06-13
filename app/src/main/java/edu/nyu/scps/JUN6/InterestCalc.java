package edu.nyu.scps.JUN6;

/**
 * Created by swaina on 6/10/15.
 */
public class InterestCalc {

    /**
     * Calculate wealth saved over a series of years ak
     *
     * @param startingAmt - the starting amount of money
     * @param interestRate - the average interest rate of your investments
     * @param contributionAmt - the amount you contribute each year
     * @param yearCnt - the number of years to run this calculation for
     * @return - a double array of the amount of money you have after each year, with the 0 index being the starting amount
     */
    public static float[] calcWealth(double startingAmt, double interestRate, double contributionAmt, int yearCnt) {
        float yearlyWealth[] = new float[yearCnt+1];

        yearlyWealth[0] = (float) startingAmt;

        for (int year = 1; year < yearCnt+1; ++year) {
            double newAmt = yearlyWealth[year-1];
            newAmt *= (1 + interestRate/100);
            newAmt += contributionAmt;
            yearlyWealth[year] = (float) newAmt;
        }

        return yearlyWealth;
    }

}
