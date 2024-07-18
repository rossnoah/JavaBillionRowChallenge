package dev.noah;

public class Data{
    int count;
    float min;
    float max;
    float total;

    public Data(float value){
        count = 1;
        min = value;
        max = value;
        total = value;
    }

    public void addItem(float value){
        count++;
        total+=value;
        if(value<min){
            min = value;
        }
        if(value>max){
            max = value;
        }
    }
}
