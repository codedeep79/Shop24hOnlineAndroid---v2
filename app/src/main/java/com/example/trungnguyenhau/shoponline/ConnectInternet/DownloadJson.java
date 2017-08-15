package com.example.trungnguyenhau.shoponline.ConnectInternet;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TRUNGNGUYENHAU on 4/21/2017.
 */

public class DownloadJson extends AsyncTask<String, Void, String>{
    private String url;

    // Hỗ trợ key và value
    private List<HashMap<String, String>> attr = new ArrayList<>();

    // Cả hai phương thưc doGet và doPost đều trả ra stringBuilder
    private StringBuilder stringBuilder = new StringBuilder();

    public DownloadJson(String url) {
        this.url = url;
    }

    public DownloadJson(String url, List<HashMap<String, String>> attr) {
        this.url = url;
        this.attr = attr;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            URL _url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) _url.openConnection();



            if (attr.size() != 0)
            {
                setMethodPost(httpURLConnection);
            }
            else
            {
                setMethodGet(httpURLConnection);
            }
        }catch(Exception e)
        {

        }
        return stringBuilder.toString();
    }


    private String setMethodGet(HttpURLConnection httpURLConnection) {
        String data = "";
        try{
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }

            data = stringBuilder.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
        catch (Exception e)
        {

        }
        return data;
    }

    private String setMethodPost (HttpURLConnection httpURLConnection) throws IOException {
        // Truyền tham số lên thì khác phương thức get nhưng lấy dữ liệu về thì giống phương thức get
        // Post và Get sẽ connect khác nhau
        String data = "";
        String key = null, val = null;
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            // Lấy dữ liệu về, dùng cho phương thức POST
            Uri.Builder builder = new Uri.Builder();

            int count = attr.size();
            for(int i = 0;i < count; ++i)
            {
                // Dùng để duyệt HashMap ở trên List
                for(Map.Entry<String, String> value: attr.get(i).entrySet())
                {
                    key = value.getKey();
                    val = value.getValue();
                }

                // Danh sách builder dưới dạng key, value
                builder.appendQueryParameter(key, val);
            }

            // Lấy dữ liệu từ HashMap
            // HashMap<String,String> hm=new HashMap<Integer,String>();
            // hm.put("maloaicha","1");
            // hm.put("maloaicha","2");

            String query = builder.build().getEncodedQuery();
            // Vì phương thức POST thì ta phải đẩy dữ liệu lên thanh URL rồi trả về dữ liệu
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);

            // thêm maloaicha = 1 lên thanh URL
            writer.write(query);
            writer.flush();

            writer.close();
            streamWriter.close();
            outputStream.close();

            data = setMethodGet(httpURLConnection);
        } catch (ProtocolException e) {
            e.printStackTrace();
        }



        return data;
    }
}
