package com.gy.ticket.java;

import com.gy.ticket.R;

/**
 * Created by TWINKLE on 2017/12/19.
 */

public interface InitString {
    String login_url = "http://119.29.98.60:8080/Cs/LoginServlet";
    String login_register = "http://119.29.98.60:8080/Cs/RegisterServlet";
    String login_emailcheck = "http://119.29.98.60:8080/Cs/EmailCheckServlet";
    String news_url = "http://119.29.98.60:8080/Cs/FilmServlet";
    String Sing_url = "https://search.damai.cn/external/gl.html?keyword=&siteCity=%E4%B8%8A%E6%B5%B7&destCity=&ctl=%E6%BC%94%E5%94%B1%E4%BC%9A&type=1";
    String Play_url = "https://search.damai.cn/external/gl.html?keyword=&siteCity=%E4%B8%8A%E6%B5%B7&destCity=&ctl=%E5%BA%A6%E5%81%87%E4%BC%91%E9%97%B2&type=1";
    int[] pic = new int[]{R.drawable.ftravel, R.drawable.amusement, R.drawable.form};
    int[] title = new int[]{R.string.travel, R.string.amusement, R.string.form};
    String film = "https://so.ly.com/scenery/AjaxHelper/SceneryPriceFrame.aspx?action=GETNEWFRAMEFORLIST&showCount=2&ids=254&isSimple=1&priceList=1&tabself=1&tabHotel=1&isGrap=1&nobookid=&isyry=1&YpState=1&lon=0&lat=0&isforegin=0&isNewSearch=true";
    String PassServlet = "http://119.29.98.60:8080/Cs/PassServlet";
    String FindServlet = "http://119.29.98.60:8080/Cs/FindServlet";
    String[] lv_info = new String[]{"邮箱", "电话号码", "昵称", "居民身份证信息"};
    String serverurl = "https://search.damai.cn/external/gl.html?keyword=&siteCity=%E4%B8%8A%E6%B5%B7&destCity=&ctl=%E6%BC%94%E5%94%B1%E4%BC%9A&type=1";
    String play = "https://www.ly.com/scenery/AjaxHelper/SceneryPriceFrame.aspx?action=GETNEWFRAMEFORLIST&showCount=2&ids=7753&isSimple=1&priceList=1&tabself=1&tabHotel=1&isGrap=1&nobookid=&isyry=1&YpState=1&lon=0&lat=0&isforegin=0";
    String scenery = "https://www.ly.com/scenery/SceneryWeb/Index.aspx?action=ScenerySearch&cityId=338&count=10&imgHeight=500&imgWidth=800";
}



