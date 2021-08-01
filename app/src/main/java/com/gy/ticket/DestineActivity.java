package com.gy.ticket;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gy.ticket.adapter.TrainAdapter;
import com.gy.ticket.java.InitString;
import com.gy.ticket.java.Play_JsonParse;
import com.gy.ticket.java.SharePerfence;
import com.gy.ticket.java.Util;
import com.gy.ticket.user.Train;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.InputStream;
import java.io.Serializable;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DestineActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_destine_begin, tv_destine_end, tv_destine_date_show, tv_destine_time;
    private Button bt_destine_ok;
    private AlertDialog alertDialog;
    private String str;
    private Toolbar tb_destine;
    private String from = "CDW", to = "XAY", time = "2018-01-02";
    public String train_url = "https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=" +
            time + "&leftTicketDTO.from_station=" +
            from + "&leftTicketDTO.to_station=" +
            to + "&purpose_codes=ADULT";

    private List<Train> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destine);
        init();
        apply();
    }


    public void init() {
        tv_destine_begin = (TextView) findViewById(R.id.tv_destine_begin);
        tv_destine_end = (TextView) findViewById(R.id.tv_destine_end);
        tv_destine_date_show = (TextView) findViewById(R.id.tv_destine_date_show);
        tv_destine_time = (TextView) findViewById(R.id.tv_destine_time);
        bt_destine_ok = (Button) findViewById(R.id.bt_destine_ok);
        tb_destine = (Toolbar) findViewById(R.id.tb_destine);
    }

    public void apply() {
        tv_destine_begin.setOnClickListener(this);
        tv_destine_end.setOnClickListener(this);
        tv_destine_date_show.setOnClickListener(this);
        tv_destine_time.setOnClickListener(this);
        bt_destine_ok.setOnClickListener(this);
        tb_destine.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_destine_begin:
                try {
                    showdialog(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_destine_end:
                try {
                    showdialog(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_destine_date_show:
                showtime();

                break;
            case R.id.bt_destine_ok:
                try {
                    fillData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void showdialog(final int flag) throws Exception {


        alertDialog = new AlertDialog.Builder(DestineActivity.this).setSingleChoiceItems(
                Util.read_txt(DestineActivity.this).toArray(new String[0]),
                0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            str = Util.read_txt(DestineActivity.this).get(which);
                            dialog.dismiss();


                            if (flag == 1) {
                                tv_destine_begin.setText(str);

                            } else {
                                tv_destine_end.setText(str);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }).create();

        if (flag == 1) {
            alertDialog.setTitle(getString(R.string.start));

        } else {
            alertDialog.setTitle(getString(R.string.destination));
        }


        alertDialog.show();

    }

    public void showtime() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH) + 1);
        int day = now.get(Calendar.DAY_OF_MONTH);
        DatePicker datePicker = new DatePicker(DestineActivity.this);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                monthOfYear = monthOfYear + 1;
                if (dayOfMonth > 0 && dayOfMonth < 10) {
                    if (monthOfYear > 0 && monthOfYear < 10) {
                        time = year + "-0" + monthOfYear + "-0" + dayOfMonth;
                        tv_destine_date_show.setText(time);

                    } else {
                        time = year + "-" + monthOfYear + "-0" + dayOfMonth;
                        tv_destine_date_show.setText(time);
                    }
                } else {
                    tv_destine_date_show.setText(time);
                    if (monthOfYear > 0 && monthOfYear < 10) {
                        time = year + "-0" + monthOfYear + "-" + dayOfMonth;
                        tv_destine_date_show.setText(time);

                    } else {
                        time = year + "-" + monthOfYear + "-" + dayOfMonth;
                        tv_destine_date_show.setText(time);
                    }
                }
                alertDialog.dismiss();
            }
        });
        alertDialog = new AlertDialog.Builder(DestineActivity.this).create();
        alertDialog.setView(datePicker);
        alertDialog.show();

    }


    private void fillData() throws Exception {
        from = Util.getid(DestineActivity.this, tv_destine_begin.getText().toString());
        to = Util.getid(DestineActivity.this, tv_destine_end.getText().toString());
        Log.i("train", from + "  " + to + "  " + time);
        Log.i("url", train_url);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(10000);
        client.post(train_url, new AsyncHttpResponseHandler() {
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {
                    String json = new String(bytes, "utf-8");
                    json = "[ " + json + " ]";
                    Log.i("json", "" + json);
                    list = JSON.parseArray(json, Train.class);
                    showticket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(int i, org.apache.http.Header[] headers,
                                  byte[] bytes, Throwable throwable) {
                Toast.makeText(DestineActivity.this, "请求失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showticket() {
        ListView lv = new ListView(DestineActivity.this);
        alertDialog = new AlertDialog.Builder(DestineActivity.this).create();
        alertDialog.setView(lv);
        lv.setAdapter(new TrainAdapter(list, DestineActivity.this));
        alertDialog.show();


    }



}
