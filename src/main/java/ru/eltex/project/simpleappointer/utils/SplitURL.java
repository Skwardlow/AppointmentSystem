package ru.eltex.project.simpleappointer.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

@Component
public class SplitURL {
    private static final Integer EXCLUDING_INDEX = 1;
    public ArrayList<String> split(String urlencoded) throws UnsupportedEncodingException {
        ArrayList<String> Result = new ArrayList<>();
        String urldecoded = URLDecoder.decode(urlencoded, "UTF-8");
        String[] info = urldecoded.split("&");
        for(int i = 0; i < info.length; i++){
            int index = info[i].indexOf('=');
            info[i] = info[i].substring(index + EXCLUDING_INDEX);
            Result.add(info[i]);
        }
        return Result;
    }
}
