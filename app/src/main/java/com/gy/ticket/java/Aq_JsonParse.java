package com.gy.ticket.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gy.ticket.user.AqInfo;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Gaoya on 2017/12/27.
 */

public class Aq_JsonParse {
    public static List<AqInfo> getAqInfo(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<AqInfo>>(){}.getType();
        List<AqInfo> aqInfos = gson.fromJson(json,listType);
        return aqInfos;
    }
}
