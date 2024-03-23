package dev.noah;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        long startTime = System.currentTimeMillis();

        billionRowChallenge();

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;


        System.out.println("Total time in ms " + totalTime);

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
        String[] stings = input.split(";");
        String id = stings[0];
        double value = Double.parseDouble(stings[1]);


        addData(map, id, value);

    }


    private static void addData(Map<String, Data> map, String id, double value) {
        Data data = map.getOrDefault(id, new Data());

        data.total += value;
        data.count++;
        if (data.max < value) {
            data.max = value;
        }
        if (data.min > value) {
            data.min = value;
        }

        map.put(id, data);

    }

    private static void printInfo(HashMap<String, Data> map) {

        StringBuilder sb = new StringBuilder("{");


        System.out.println("map size:" + map.keySet().size());

        int[] index = {0};
        map.forEach((id, data) -> {
            index[0]++;

            sb.append(id)
                    .append("=")
                    .append(data.min)
                    .append("/")
                    .append(data.total / data.count)
                    .append("/")
                    .append(data.max);
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