package tries;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Main {


        public static String QuestionsMarks(String str) {
            boolean found = false;
            ArrayList<Integer> nums = new ArrayList();
            char[] numbers = str.replaceAll("\\D", "").toCharArray();

            for(char a:numbers){
                nums.add(Character.getNumericValue(a));
            }

            int previousCharIndex = 0;
            int nextCharIndex = 0;

            for(int i= 1; i<nums.size(); i++){
                if((nums.get(i-1) + nums.get(i)) >= 10){
                    previousCharIndex = str.indexOf(Character.forDigit(nums.get(i-1), 10), previousCharIndex);
                    nextCharIndex = str.indexOf(Character.forDigit(nums.get(i), 10), previousCharIndex+1);
                    found = Pattern.matches("[???]" , str.substring(previousCharIndex, nextCharIndex+1));

                    if(found == false){
                        break;
                    }
                }
            }
            // code goes here
            return found == true ? "true" : "false";
        }

        public static void main (String[] args) {
            // keep this function call here
            Scanner s = new Scanner(System.in);
            System.out.print(QuestionsMarks(s.nextLine()));
        }


}
