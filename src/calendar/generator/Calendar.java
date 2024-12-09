/*
 * This has been written and shipped as is and author
 *  will not be held responsible for any damages. Any modifications or sales of this code
 * shall not be made without explicit permission from author
 */
package calendar.generator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This will be the central point where the animated current month will track the day in a set interval, i will first try a couple of minutes.
 * @author Doctor
 */
public class Calendar {
    private int day_of_the_week;//will be the one that trackes if it is a moday-sunday for the automated day cycle thing
    private int current_start_day;
    private int current_day_of_the_month;//will track the current day of the displayed month the automated day cycle thing
    private int current_month; // will track the month in the automated day cycle thing
    private int year;//will track the current year in the automated day cycle thing
    private int counter_for_current_day_of_the_week;//only use this for tracking the curent day of the month when printing out a whole year of a calendar
    //the arrays were made so that i can have the days of the months and the months themsleves on hand. I would make a function that accepts any array of days but i am kinda lazy
    private int[] year_month_days = {31,28,31,30,31,30,31,31,30,31,30,31};
    private int[] leap_year_month_days = {31,31,31,30,31,30,31,31,30,31,30,31};
    private String[] months = {"January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    private String dMonthHighlighted;
    private String dMonthUnhighlighted; 
    
    
    public Calendar(int start_day_of_year){
        this.current_start_day = start_day_of_year;
        this.current_day_of_the_month = 1;
        this.year = 2023;
        this.current_month = 1;
        dMonthHighlighted = generate_month(current_start_day, year_month_days[current_month], true);
        dMonthUnhighlighted = generate_month(current_start_day, year_month_days[current_month], false);
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
        String[] days = {"   M  ", "   T  ", "   W  ", "   T  ", "   F  ", "   S  ", "   S  "};
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
                        if(month[i][j]>=10)
                            st.append(" [").append(month[i][j]).append("] \t");
                        else
                            st.append(" [0").append(month[i][j]).append("] \t");
                    }
                    else{
                        if (month[i][j] >= 10)
                            st.append("  ").append(month[i][j]).append("  \t");
                        else
                            st.append("  0").append(month[i][j]).append("  \t");
                    }
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
     * this function generates the year of a calendar, you have to supply the day it starts so it the accuracy depends on you.
     * A leap year occurs when a year is divisible by 4 and the remainder of the remainder of the year/100 is an even number that is
     * year%4 = 0 and
      year/100 = remainder1
      remainder1%2 = 0
      the mapping for the start days is as follows
      1-Monday
      2-Tuesday
      3-Wednesday
      4-Thursday
      5-Friday
      6-Saturday
      7-Sunday
      it returns a string representation of the month 
     * @param start_day_of_year
     * @param year
     * @return String representation of the year
     */
    public String generate_year(int start_day_of_year, int year){
        //checking if it is a leap year
        boolean is_leap_year = false;
        if(year%4==0 && (year%100)%2==0){
            is_leap_year = true;
           
        }
        this.counter_for_current_day_of_the_week = start_day_of_year;
        StringBuilder sb = new StringBuilder();
        if(is_leap_year == false){
            for (int i = 0; i < 12; i++){
                sb.append(this.months[i]).append("-").append(year).append("\n");
                sb.append(this.generate_month(this.counter_for_current_day_of_the_week, this.year_month_days[i], false)).append("\n");
            }
        }
        else{
            for (int i = 0; i < 12; i++){
                sb.append(this.months[i]).append("-").append(year).append("\n");
                sb.append(this.generate_month(this.counter_for_current_day_of_the_week, this.leap_year_month_days[i], false)).append("\n");
            }
        }
        return sb.toString();
    }
    
    public void launch_on_console(){
        Thread draw = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized(dMonthHighlighted){
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        System.out.flush();
                        System.out.print(dMonthHighlighted);
                        
                        Thread.sleep(1000);
                        
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        System.out.flush();
                        System.out.print(dMonthUnhighlighted);
                        
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }}
            }
        });
        
        Thread update = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                try {
                    synchronized(dMonthHighlighted){
                        current_day_of_the_month++;
                        int month_length = year_month_days[current_month];
                        if(month_length >= current_day_of_the_month){
                            if(day_of_the_week <= 7)
                                day_of_the_week++;
                            dMonthHighlighted = generate_month(current_start_day, year_month_days[current_month], true);
                            dMonthUnhighlighted = generate_month(current_start_day, year_month_days[current_month], false);
                            System.out.println("bing bong");
                            
                        }
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        });
        
        draw.start();
        update.start();
    }
    
    
    
  }
