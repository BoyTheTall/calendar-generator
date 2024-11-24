/*
 * This has been written and shipped as is and author
 *  will not be held responsible for any damages. Any modifications or sales of this code
 * shall not be made without explicit permission from author
 */
package calendar.generator;



/**
 *
 * @author Doctor
 */
public class CalendarGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print(generate_month(3,31));
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
   */
    public static String generate_month(int start_day, int number_of_days){
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
}
