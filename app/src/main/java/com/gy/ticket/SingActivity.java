package com.gy.ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.ticket.java.InitString;
import com.gy.ticket.java.JsonParse;
import com.gy.ticket.user.Sing;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.image.SmartImageView;

import java.util.List;

public class SingActivity extends AppCompatActivity {
    private LinearLayout sing_loading;
    private ListView singnews;
    private List<Sing> singInfos;
    private TextView sing_item_title;
    private TextView sing_item_content;
    private TextView sing_item_nature;
    private Sing singInfo;
    private SmartImageView singsiv;
    private Toolbar tl_sing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing);
        initView();
        fillData();
    }
    private void initView(){
        sing_loading = (LinearLayout) findViewById(R.id.sing_loading);
        singnews = (ListView)findViewById(R.id.sing_news);
        tl_sing = (Toolbar)findViewById(R.id.tl_sing);
        tl_sing.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void fillData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(InitString.serverurl,new AsyncHttpResponseHandler(){
            public void onSuccess(int i, org.apache.http.Header[] headers,byte[] bytes){
                try{
                    String json = new String(bytes, "utf-8");
                    json = "[ " +json + " ]";
                    singInfos = JsonParse.getSingInfo(json);
                    if(singInfos==null){
                        Toast.makeText(SingActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                    }else{
                        sing_loading.setVisibility(View.INVISIBLE);
                        singnews.setAdapter(new NewsAdapter());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            public void onFailure(int i,org.apache.http.Header[] headers,
                                  byte[]bytes,Throwable throwable){
                Toast.makeText(SingActivity.this,"请求失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class NewsAdapter extends BaseAdapter{
        public int getCount(){
            return singInfos.get(0).getSuggest().size();

        }
        public View getView(int position, View convertView, ViewGroup parent){
            View view = View.inflate(SingActivity.this, R.layout.activity_sing_item,null);
            singsiv = (SmartImageView)view.findViewById(R.id.sing_icon);
            sing_item_title = (TextView)view.findViewById(R.id.sing_item_title);
            sing_item_content = (TextView)view.findViewById(R.id.sing_item_content);
            sing_item_nature = (TextView)view.findViewById(R.id.sing_item_nature);


            Log.i("size",""+singInfos.size());

            singInfo = singInfos.get(0);

            Log.i("size",""+singInfo.getSuggest().size());

            singsiv.setImageUrl("https://pimg.damai.cn/perform/project/"+singInfo.getSuggest().get(position).getFold()
                            +"/"+singInfo.getSuggest().get(position).getProjectId()+"_n.jpg",R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher);
            sing_item_title.setText(""+singInfo.getSuggest().get(position).getProjectName());
            sing_item_content.setText(""+singInfo.getSuggest().get(position).getVenue());
            sing_item_nature.setText(""+singInfo.getSuggest().get(position).getShowTime());
            return view;
        }
        public Object getItem(int position){
            return null;
        }
        public long getItemId(int position){
            return 0;
        }
    }
}
