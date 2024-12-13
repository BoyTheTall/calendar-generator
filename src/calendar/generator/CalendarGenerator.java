/*
 * This has been written and shipped as is and author
 *  will not be held responsible for any damages. Any modifications or sales of this code
 * shall not be made without explicit permission from author
 */
package calendar.generator;

import java.util.Scanner;



/**
 *
 * @author Doctor
 */
public class CalendarGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calendar c = new Calendar(1,2024);
        c.setStartDateForAnimatedCalendar(2, 12, 2, 2025);
       // System.out.print(c.generate_year(1, 2024));
        c.launch_on_console();
        
    }
    
    
  
}
