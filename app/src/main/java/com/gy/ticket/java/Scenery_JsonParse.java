package com.gy.ticket.java;

import com.google.gson.reflect.TypeToken;
import com.gy.ticket.user.SceneryInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Gaoya on 2017/12/26.
 */

public class Scenery_JsonParse {
    public static List<SceneryInfo> getSceneryInfo(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<SceneryInfo>>(){}.getType();
        List<SceneryInfo> sceneryInfos = gson.fromJson(json,listType);
        return sceneryInfos;
    }
}
