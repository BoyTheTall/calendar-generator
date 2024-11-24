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
    public static String generate_month(int start_day, int number_of_days){
      StringBuilder st = new StringBuilder();
       String[] days = {"M", "T", "W", "T", "F", "S", "S"};
        for (int i =0; i<7; i++){
            //System.out.print(days[i]);
            //System.out.print("\t");
            st.append(days[i]).append("\t");
        }
        System.out.println();
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
                    //System.out.print(month[i][j]);
                  //  System.out.print("\t");
                    st.append(month[i][j]).append("\t");
                }
                else{
              //      System.out.print(" ");
                //    System.out.print("\t");
                    st.append(" ").append("\t");
                }
                day_counter++;
            }
            //System.out.println();
            st.append("\n");
        }

        //System.out.print(st.toString());
        return st.toString();
    
    }
}
