package com.gy.ticket.java;

import com.google.gson.reflect.TypeToken;
import com.gy.ticket.user.PlayInfo;
import java.lang.reflect.Type;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Gaoya on 2017/12/26.
 */

public class Play_JsonParse {
    public static List<PlayInfo> getPlayInfo(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<PlayInfo>>(){}.getType();
        List<PlayInfo> playInfos = gson.fromJson(json,listType);
        return playInfos;
    }
}
