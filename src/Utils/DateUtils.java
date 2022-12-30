package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Chanh Pham
 */
public class DateUtils {
    public static String getDateNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();  
        return String.valueOf(formatter.format(date));  
    }
    
    public static String formatDate(Date date) {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(date);
        return stringDate;
    }
    
    public  Date convertStringToDate(String dateString) {
        Date date = new Date();
        try {
            SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
            date = DateFor.parse(dateString);
        } catch (ParseException e) {
            System.err.println("malformed dateString");
        }
        return date;
    }
}
