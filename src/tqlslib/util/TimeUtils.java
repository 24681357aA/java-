package tqlslib.util;

public class TimeUtils {

   public static String formatTime(long milliseconds) {
      int seconds = (int)(milliseconds / 1000);
      int minutes = seconds / 60;
      int hours = minutes / 60;
      
      seconds %= 60;
      minutes %= 60;
      
      if (hours > 0) {
         return String.format("%02d:%02d:%02d", hours, minutes, seconds);
      } else if (minutes > 0) {
         return String.format("%02d:%02d", minutes, seconds);
      } else {
         return String.format("%02d", seconds);
      }
   }
}
