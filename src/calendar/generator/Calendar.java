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
 * @author BoyTheTall
 */
public class Calendar {
    private int day_of_the_week;//will be the one that trackes if it is a moday-sunday for the automated day cycle thing
    private int current_start_day;
    private int current_day_of_the_month;//will track the current day of the displayed month the automated day cycle thing
    private int current_month; // will track the month in the automated day cycle thing
    private int year;//will track the current year in the automated day cycle thing
    private int counter_for_current_day_of_the_week;//only use this for tracking the curent day of the month when printing out a whole year of a calendar
    //the arrays were made so that i can have the days of the months and the months themsleves on hand. I would make a function that accepts any array of days but i am kinda lazy
    private final int[] year_month_days = {31,28,31,30,31,30,31,31,30,31,30,31};
    private final int[] leap_year_month_days = {31,29,31,30,31,30,31,31,30,31,30,31};
    private final String[] months = {"January","February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private final String[] days = {"  Mo  ", "  Tu  ", "  We  ", "  Th  ", "  Fr  ", "  Sa  ", "  Su  "};
    
    //these are the frame variables
    private String dMonthHighlighted;
    private String dMonthUnhighlighted; 
    
    
    public Calendar(int start_day_of_year, int year){
        this.current_start_day = start_day_of_year;
        this.day_of_the_week = this.current_start_day;
        this.current_day_of_the_month = 1;
        this.year = year;
        this.current_month = 1;
        dMonthHighlighted = generate_drawing_month(current_start_day, year_month_days[current_month], true);
        dMonthUnhighlighted = generate_drawing_month(current_start_day, year_month_days[current_month], false);
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
    public String generate_month(int start_day, int number_of_days){
        StringBuilder st = new StringBuilder();
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
                    
                        if (month[i][j] >= 10)
                            st.append("  ").append(month[i][j]).append("  \t");
                        else
                            st.append("  0").append(month[i][j]).append("  \t");
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
        boolean is_leap_year = this.isLeapYear(year);
        this.counter_for_current_day_of_the_week = start_day_of_year;
        StringBuilder sb = new StringBuilder();
        if(is_leap_year == false){
            for (int i = 0; i < 12; i++){
                sb.append(this.months[i]).append("-").append(year).append("\n");
                sb.append(this.generate_month(this.counter_for_current_day_of_the_week, this.year_month_days[i])).append("\n");
            }
        }
        else{
            for (int i = 0; i < 12; i++){
                sb.append(this.months[i]).append("-").append(year).append("\n");
                sb.append(this.generate_month(this.counter_for_current_day_of_the_week, this.leap_year_month_days[i])).append("\n");
            }
        }
        return sb.toString();
    }
    public String generate_drawing_month(int start_day, int number_of_days, boolean track_current_day){
        StringBuilder st = new StringBuilder();
        
        st.append(months[current_month]).append("-").append(year).append("\n");
        for (int i =0; i<7; i++){
            st.append(this.days[i]).append("\t");
        }
        
        st.append("\n");
        int[][] month = new int[6][7];
        int day_counter = 0;
        int days_in_the_month = number_of_days;
        
        for (int i = start_day - 1; i < 7; i++){
            month[0][i] = day_counter + 1;
            day_counter++;
            
        }
        for (int i = 1; i < 6; i++){
            for(int j = 0; j < 7  && day_counter < days_in_the_month; j++){
                month[i][j] = day_counter + 1;
                day_counter++;
                
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
    public void launch_on_console(){
        Thread draw = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized(dMonthHighlighted){
                    try {
                        //clearing console
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                                
                        //"drawing" the current month with the highlighted day
                        System.out.print(dMonthHighlighted);
                        //putting the thread to sleep for a bit so that it doesn't immediately "draw" over what was just displayed
                        Thread.sleep(100);
                        
                        
                        //clearing console
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        //"drawing" the current month without the highlighted day
                        System.out.print(dMonthUnhighlighted);
                        //putting the thread to sleep for a bit so that it doesn't immediately "draw" over what was just displayed
                        Thread.sleep(100);
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
                        //goes through the current month
                        if(month_length >= current_day_of_the_month){
                                                      
                            generateFrames(); 
                        }
                        //generating a new month
                        else{
                            System.out.println(day_of_the_week);
                            if(!isLeapYear(year))
                                day_of_the_week = getNewMonthStartDay(day_of_the_week, year_month_days[current_month]);
                            else
                                day_of_the_week = getNewMonthStartDay(day_of_the_week, leap_year_month_days[current_month]);
                            
                            current_month++;
                            if(current_month < year_month_days.length){
                                current_start_day = day_of_the_week;
                                current_day_of_the_month = 1;//setting the day to the first
                                                               
                                generateFrames();
                            }
                            //year has ended if it goes to this point
                            else{
                                year++;
                                current_month = 0;
                                current_day_of_the_month = 1;
                                if(!isLeapYear(year))
                                    day_of_the_week = getNewMonthStartDay(day_of_the_week, year_month_days[current_month]);
                                else
                                    day_of_the_week = getNewMonthStartDay(day_of_the_week, leap_year_month_days[current_month]);
                                
                                generateFrames();
                                
                            }
                        }
                    }
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        });
        
        draw.start();
        update.start();
    }
    
    /**
     *
     * @param current_start_day
     * @param number_of_days
     * @return
     */
    public int getNewMonthStartDay(int current_start_day, int number_of_days){
    int new_start_day  = current_start_day;
    int[][] month = new int[6][7];
        int day_counter = 0;
        int days_in_the_month = number_of_days;
        
        for (int i = current_start_day - 1; i < 7; i++){
            month[0][i] = day_counter + 1;
            day_counter++;
            if (new_start_day == 7)
                    new_start_day =1;
            else
                new_start_day++;
        }
        for (int i = 1; i < 6; i++){
            for(int j = 0; j < 7  && day_counter < days_in_the_month; j++){
                month[i][j] = day_counter + 1;
                day_counter++;
                if (new_start_day == 7)
                    new_start_day =1;
                else
                    new_start_day++;
            }
        
        }
        return new_start_day;
    } 
    
    public boolean isLeapYear(int year){
        boolean is_leap_year = false;
        if((year%4==0 && year%100!=0) || (year%100 == 0 && year%400==0)){
            is_leap_year = true;
           
        }
        return is_leap_year;
    }
    
    public void setStartDateForAnimatedCalendar(int start_day_of_the_month,int day_of_the_month, int month, int year){
        this.year = year;
        this.current_day_of_the_month = day_of_the_month;
        this.current_month = month;
        this.day_of_the_week = start_day_of_the_month;
        this.current_start_day = this.day_of_the_week;
        this.generateFrames();
        
    }
    
    private void generateFrames(){
        if(!isLeapYear(year)){
            dMonthHighlighted = generate_drawing_month(current_start_day, year_month_days[current_month], true);
            dMonthUnhighlighted = generate_drawing_month(current_start_day, year_month_days[current_month], false);
        }
        else{
            dMonthHighlighted = generate_drawing_month(current_start_day, leap_year_month_days[current_month], true);
            dMonthUnhighlighted = generate_drawing_month(current_start_day, leap_year_month_days[current_month], false);
        }
    }
  }
