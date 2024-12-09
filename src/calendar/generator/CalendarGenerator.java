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
        Calendar c = new Calendar(23);
        System.out.print(c.generate_year(1, 2024));
    }
    
    
  
}
