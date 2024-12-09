/*
 * This has been written and shipped as is and author
 *  will not be held responsible for any damages. Any modifications or sales of this code
 * shall not be made without explicit permission from author
 */
package calendar.generator;

/**
 *This will be the central point where the animated current month will track the day in a set interval, i will first try a couple of minutes.
 * @author Doctor
 */
public class Calendar {
    private int day_of_the_week;//will be the one that trackes if it is a moday-sunday
    private int current_day_of_the_month = 4;//will track the current day of the displayed month
    private int current_month;
    private int year;
    private int counter_for_current_day_of_the_week;//only use this for tracking the curent day of the month when printing out a whole year of a calendar
    private int[] month_days = {31,28,31,30,31,30,31,31,30,31,30,31};
    private String[] months = {"January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public Calendar(int start_day_of_year){
        this.day_of_the_week = start_day_of_year;
    }
    
      /**generates the calendar month with the day the month starts on and the number of days the month has
     * the mapping for the start days is as follows
     * 1-Monday
     * 2-Tuesday
     * 3-Wednesday
     * 4-Thursday
     * 5-Friday
     * 6-Saturday
     * 7-Sunday
     * it returns a string representation of the month
     * @param start_day
     * @param number_of_days
     * @param track_current_day
     * @return string month
   */
    public String generate_month(int start_day, int number_of_days, boolean track_current_day){
        StringBuilder st = new StringBuilder();
        String[] days = {"M", "T", "W", "T", "F", "S", "S"};
        for (int i =0; i<7; i++){
            st.append(days[i]).append("\t");
        }
        
        st.append("\n");
        int[][] month = new int[6][7];
        int day_counter = 0;
        int days_in_the_month = number_of_days;
        
        for (int i = start_day - 1; i < 7; i++){
            month[0][i] = day_counter + 1;
            day_counter++;
            if (this.counter_for_current_day_of_the_week == 7)
                    this.counter_for_current_day_of_the_week =1;
            else
                this.counter_for_current_day_of_the_week++;
        }
        for (int i = 1; i < 6; i++){
            for(int j = 0; j < 7  && day_counter < days_in_the_month; j++){
                month[i][j] = day_counter + 1;
                day_counter++;
                if (this.counter_for_current_day_of_the_week == 7)
                    this.counter_for_current_day_of_the_week =1;
                else
                    this.counter_for_current_day_of_the_week++;
            }
        }
        
        for (int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                if (month[i][j] > 0){
                    if(this.current_day_of_the_month == month[i][j] && track_current_day == true){
                        st.append("[").append(month[i][j]).append("]\t");
                    }
                    else
                        st.append(month[i][j]).append("\t");
                }
                else{
                    st.append(" ").append("\t");
                }
                day_counter++;
            }
            st.append("\n");
        }
        return st.toString();
    
    }
    
    /**
     *
     * @param start_day
     * @return
     */
    public String generate_year(int start_day_of_year){
        this.counter_for_current_day_of_the_week = start_day_of_year;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++){
            sb.append(this.months[i]).append("\n");
            sb.append(this.generate_month(this.counter_for_current_day_of_the_week, this.month_days[i], false)).append("\n");
        }
        return sb.toString();
    }
}
