package application;

import java.math.BigDecimal;
import java.util.Scanner;

public class ChangeNumberToString {
    private static final String[] units = {"", "nghìn", "triệu", "tỷ"};
    private static final String[] ones = {"", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"};
    private static final String[] teens = {"", "mười", "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín"};
    private static final String[] tens = {"", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};

    public static String readNumberInWords(long number) {
        if (number == 0) {
            return "không";
        }

        String result = "";
        int chunkCount = 0;

        while (number > 0) {
            long chunk = number % 1000;
           
            if (chunk != 0) {
                result = convertChunk(chunk) + " " + units[chunkCount] + " " + result;
            }
            number /= 1000;
            chunkCount++;
        }

        return result.trim();
    }

    private static String convertChunk( long chunk) {
        if (chunk == 0) {
            return "";
        } else if (chunk < 10) {
            return ones[(int) chunk];
        } else if (chunk < 20) {
            return teens[(int) (chunk - 10+1)];
        } else if (chunk < 100) {
        	  return tens[(int) (chunk / 10)] + " " + (chunk % 10 == 1 ? "mốt" : ones[(int) (chunk % 10)]);
        } else {
            return ones[(int) (chunk / 100)] + " trăm " + convertChunk(chunk % 100);
        }
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
        long number = sc.nextLong();
        String inWords = readNumberInWords(number);
        System.out.println(number + " trong chữ: " + inWords);
    }
}
