package dev.noah;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        long startTime = System.currentTimeMillis();

        billionRowChallenge();

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;


        System.out.println("Total time in  ms " + totalTime);


    }


    public static void billionRowChallenge() {

        HashMap<String, Data> map = new HashMap<>();


        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("measurements.txt"));

            String line = reader.readLine();

            while (line != null) {

                processLine(map, line);
                line = reader.readLine();

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        printInfo(map);

    }

    public static void processLine(Map<String, Data> map, String input) {

        char[] chars = input.toCharArray();

        int seperatorIndex = 0;
        while(chars[seperatorIndex]!=';'){
            seperatorIndex++;
        }

        String id = input.substring(0,seperatorIndex);
        chars = input.substring(seperatorIndex+1).toCharArray();

        int value = parseCharArrayToInt(chars);



        addData(map, id, value);

    }

    public static int parseCharArrayToInt(char[] chars){
        boolean hasSign = chars[0] == '-';


        int value;

        int digit1, digit2, digit3;


        if(hasSign){
            if(chars.length==5){
                digit1 = Character.digit(chars[1],10);
                digit2 = Character.digit(chars[2],10);
                digit3 = Character.digit(chars[4],10);

                value =(digit1*100 + digit2*10 + digit3) * -1;
            }else{
                digit1 = Character.digit(chars[1],10);
                digit2 = Character.digit(chars[3],10);

                value =(digit1*100 + digit2*10) * -1;

            }


        }else if (chars.length == 4) {
            digit1 = Character.digit(chars[0],10);
            digit2 = Character.digit(chars[1],10);
            digit3 = Character.digit(chars[3],10);

            value =(digit1*100 + digit2*10 + digit3) ;
        } else {
            digit1 = Character.digit(chars[0],10);
            digit2 = Character.digit(chars[2],10);

            value =(digit1*100 + digit2*10);

        }


        return  value;

    }




    private static void addData(Map<String, Data> map, String id, int value) {


        Data data = map.get(id);

        if(data==null){
            data = new Data(value);
            map.put(id, data);
            return;
        }
        data.total += value;
        data.count++;
        if (data.max < value) {
            data.max = value;
        }
        if (data.min > value) {
            data.min = value;
        }

    }

    private static void printInfo(HashMap<String, Data> map) {

        StringBuilder sb = new StringBuilder("{");
        DecimalFormat decimalFormat = new DecimalFormat("#.0");


        System.out.println("map size:" + map.keySet().size());

        int[] index = {0};
        map.forEach((id, data) -> {
            index[0]++;

            sb.append(id)
                    .append("=")
                    .append((double)data.min/100)
                    .append("/")
                    .append(decimalFormat.format(((double)data.total/100 / data.count)))
                    .append("/")
                    .append((double)data.max/100);
            if (index[0] < map.size() - 1) {
                sb.append(",");//we should skip this on the last one
            }

        });

        sb.append("}");


        System.out.println();
        System.out.println(sb);
        System.out.println();

    }


}