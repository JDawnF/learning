package tools;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeFormat {
    public static void main(String[] args) throws IOException, ParseException {
        dateFormat();
    }

    private static void dateFormat(){
        String nowString="20191016091919";
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        Date date= null;
        try {
            date = sDateFormat.parse(nowString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String nowStringFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(nowStringFormat);
    }

    private static void moneyFormat(){
        BigDecimal test=new BigDecimal(10.234);
        BigDecimal bigDecimal = test.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal.toString());
    }
}
