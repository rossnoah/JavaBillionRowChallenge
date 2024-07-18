package dev.noah;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");
        long startTime = System.currentTimeMillis();

        if (args.length < 1) {
            System.out.println("You must provide the file name as an arg");
            return;
        }

        billionRowChallenge(args[0]);

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Complete.");
        System.out.println("Total time in  ms " + totalTime);
    }
    public static void billionRowChallenge(String filename) {

        File file = new File(filename);

        int totalLines = 0;

        FileReader fr = null;
        BufferedReader br = null;
        HashMap<String,Data> map = new HashMap<>();

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            char[] chars = new char[100];

            char ch;
            int count = 0;

            String key = null;
            float value;
            while(br.ready()){
                ch = (char) br.read();

                if(ch==';'){
                    key = new String(chars,0,count);
                    count=0;
                    continue;
                }
                if(ch=='\n'){
                    value = Float.parseFloat(new String(chars,0,count));
                    count=0;
                    Data data = map.getOrDefault(key,new Data(value));
                    map.put(key,data);
                    data.addItem(value);
                    continue;
                }
                chars[count]=ch;
                count++;
            }
//            value = Float.parseFloat(new String(chars,0,count));
//            Data data = map.getOrDefault(key,new Data(value));
//            data.addItem(value);

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();

        }



        System.out.println("total lines: "+totalLines );
        //map size
        System.out.println("map size: "+map.size());

        for (Map.Entry<String, Data> entry : map.entrySet()) {
            String key = entry.getKey();
            Data value = entry.getValue();
            DecimalFormat df = new DecimalFormat("#.#");
            System.out.println(key + ":  min: " + df.format(value.min) + " max: " + df.format(value.max) + " avg: " + df.format(value.total/value.count));
        }






    }
}

