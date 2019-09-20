package ru.eltex.project.simpleappointer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Splitting url util, created for working with URL post requests
 *
 * @author Aaaaa988
 * @version 1.0
 * @see Component
 */
@Slf4j
@Component
public class SplitURL {
    private static final Integer EXCLUDING_INDEX = 1;

    /**
     * Splitting url method
     *
     * @param urlencoded encoded url
     * @return Arraylist what url contains
     * @throws UnsupportedEncodingException
     */
    public static ArrayList<String> split(String urlencoded) throws UnsupportedEncodingException {
        log.info("Got request " + urlencoded);
        ArrayList<String> Result = new ArrayList<>();
        String urldecoded = URLDecoder.decode(urlencoded, "UTF-8");
        String[] info = urldecoded.split("&");
        for (int i = 0; i < info.length; i++) {
            int index = info[i].indexOf('=');
            info[i] = info[i].substring(index + EXCLUDING_INDEX);
            Result.add(info[i]);
        }
        return Result;
    }
}
