package com.gy.ticket.java;

/**
 * Created by Gaoya on 2017/12/26.
 */

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gy.ticket.user.FilmInfo;


public class Film_JsonParse {
    public static List<FilmInfo> getFilmInfo(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<FilmInfo>>(){}.getType();
        List<FilmInfo> filmInfos = gson.fromJson(json,listType);
        return filmInfos;
    }
}
