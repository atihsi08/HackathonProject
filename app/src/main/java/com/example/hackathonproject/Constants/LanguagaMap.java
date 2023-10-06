package com.example.hackathonproject.Constants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class LanguagaMap {
    public  static LinkedHashMap<String,String> map = new LinkedHashMap<>();

    static {
        map.put("en-IN","English");
        map.put("hi-IN","Hindi");
        map.put("bn-IN","Bengali");
        map.put("ta-IN","Tamil");
        map.put("te-IN","Telugu");
        map.put("mr-IN","Marathi");
        map.put("gu-IN","Gujarati");
        map.put("kn-IN","Kannada");
        map.put("ml-IN","Malayalam");
        map.put("pa-IN","Punjabi");
        map.put("ur-IN","Urdu");
    }

    public static String getFromValue(String languageName){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equals(languageName)) {
                    return entry.getKey();
                }
            }
        return "en-IN";
    }

}
