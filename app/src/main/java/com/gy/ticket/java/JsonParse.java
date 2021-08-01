package com.gy.ticket.java;

import com.google.gson.reflect.TypeToken;
import com.gy.ticket.user.Sing;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Gaoya on 2017/12/25.
 */

public class JsonParse {
    public static List<Sing> getSingInfo(String json){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Sing>>(){}.getType();
        List<Sing> singInfos = gson.fromJson(json, listType);
        return singInfos;
    }
}
