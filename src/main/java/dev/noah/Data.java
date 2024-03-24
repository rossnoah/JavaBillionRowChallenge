package dev.noah;

public class Data {
    public int count;
    public int min;
    public int max;
    public int total;

    public Data(){
        count = 0;
        min = 0;
        max = 0;
        total = 0;
    }
    public Data(int value){
        count =1;
        min=value;
        max =value;
        total=value;
    }
}