package com.gy.ticket;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gy.ticket.java.InitString;
import com.gy.ticket.java.SharePerfence;
import com.gy.ticket.user.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_information_ok, iv_information_account;
    private ListView lv_info;
    private Toolbar tb_info;
    private SimpleAdapter simpleAdapter;
    private ArrayList<Map<String, String>> date = new ArrayList<>();
    private TextView logout;
    private AlertDialog dialog;
    private ImageView iv_information_fsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        init();
        apply();
    }


    private void init() {

        iv_information_ok = (ImageView) findViewById(R.id.iv_information_ok);
        lv_info = (ListView) findViewById(R.id.lv_info);
        iv_information_account = (ImageView) findViewById(R.id.iv_information_account);
        tb_info = (Toolbar) findViewById(R.id.tb_info);
        logout = (TextView) findViewById(R.id.tv_info_logout);
    }

    private void apply() {

        iv_information_ok.setOnClickListener(this);
        iv_information_account.setOnClickListener(this);
        logout.setOnClickListener(this);

        tb_info.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_data();
        lv_click();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_information_ok:
                up();
                break;
            case R.id.iv_information_account:
                break;
            case R.id.tv_info_logout:
                new SharePerfence("account", InformationActivity.this).remove_all();
                startActivity(new Intent(InformationActivity.this, MainActivity.class));
                break;
            default:
                break;
        }
    }

    private void up() {
       for(int x=0;x<date.size();x++)
       {
           Log.i("data",""+date.get(x).get("title"));
       }
       updata(date.get(0).get("title"),date.get(1).get("title"),date.get(2).get("title"),date.get(3).get("title"),
               new SharePerfence("account",InformationActivity.this).get_id());
    }

    private void add_data() {
        ArrayList<String> list = new SharePerfence("account", InformationActivity.this).get_info();
        Map<String, String> map;
        for (int x = 0; x < list.size(); x++) {
            map = new HashMap<>();
            map.put("title", list.get(x));
            map.put("info", InitString.lv_info[x]);
            date.add(map);
        }

        simpleAdapter = new SimpleAdapter(InformationActivity.this, date, android.R.layout.simple_expandable_list_item_2,
                new String[]{"info", "title"}, new int[]{android.R.id.text1, android.R.id.text2});
        lv_info.setAdapter(simpleAdapter);
    }


    private void lv_click() {

        lv_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final EditText et = new EditText(InformationActivity.this);
                et.setText(date.get(position).get("title"));
                switch (position) {
                    case 3:
                        break;
                    default:

                        dialog = new AlertDialog.Builder(InformationActivity.this).setTitle(getString(R.string.news))
                                .setView(et)
                                .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        date.get(position).put("title", et.getText().toString());
                                        simpleAdapter.notifyDataSetChanged();
                                    }
                                }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();
                        dialog.show();
                }
            }
        });

    }

    public void updata(String email, String tel, String name, String idcard,String id) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("flag", "5");
        params.put("email", email);
        params.put("tel", tel);
        params.put("idcard", idcard);
        params.put("name", name);
        params.put("admin", "");
        params.put("id",id);
        asyncHttpClient.setTimeout(10000);
        asyncHttpClient.post(InitString.FindServlet, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (i == 200) {
                    try {
                        String result = new String(bytes, "utf-8").replaceAll("\\s*", "");
                        System.out.println("输出结果：" + result);
                        if ("-4".equals(result)) {
                            new SharePerfence("account",InformationActivity.this).setInfo(new User(date.get(0).get("title"),date.get(1).get("title"),date.get(2).get("title")));
                            Toast.makeText(InformationActivity.this, getText(R.string.update_right), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(InformationActivity.this, WelActivity.class));
                        } else if ("-2".equals(result)) {
                            Toast.makeText(InformationActivity.this, getText(R.string.permission_rationale), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(InformationActivity.this, getText(R.string.permission_rationale), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(InformationActivity.this, getText(R.string.permission_rationale), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(InformationActivity.this, getText(R.string.permission_rationale), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
