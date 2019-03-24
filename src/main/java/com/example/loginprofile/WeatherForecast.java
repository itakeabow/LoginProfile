package com.example.loginprofile;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherForecast extends AppCompatActivity {
    /* private static final String URL2 = "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389";
     public static final String APP_ID="7e943c97096a9784391a981c4d878b2";
     private static final String URL1 = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca";
     private static final String city = "ottawa,ca";*/
    Uri uri = Uri.parse("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric");
    String server = uri.getAuthority();
    String path = uri.getPath();
    String protocol = uri.getScheme();
    String city = uri.getQueryParameter("q");
    String appId = uri.getQueryParameter("APPID");
    String mode = uri.getQueryParameter("Mode");
    String units = uri.getQueryParameter("Units");
    String URL = server + path + protocol;
    ProgressBar progressbar;
    String urlString = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric";
    Bitmap image = null;
    Bitmap bm;
    int x=0;
    int total = 0;
    int y = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ForecastQuery forecastQuery= new ForecastQuery();
        forecastQuery.execute(urlString);
        progressbar = findViewById(R.id.progressBar1);
        progressbar.setVisibility(View.VISIBLE);

    }

    class ForecastQuery extends AsyncTask<String, Integer, String> {

        String speed, min, max, currentTemp,icon;

        XmlPullParser parser = Xml.newPullParser();

        private final String ns = null;

        @Override
        protected String doInBackground(String... args) {

            try {
                URL url = new URL(args[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(conn.getInputStream(),null);

                int eventType=-1;


                while (eventType != XmlResourceParser.END_DOCUMENT) {
                    String tagName = parser.getName();

                    switch (eventType) {
                        case XmlResourceParser.START_TAG:
                            if (tagName.equals("speed")) {
                                speed = parser.getAttributeValue(null, "value");
                                x++;
                                total=x*y;
                                publishProgress(total);
                            }
                            if(tagName.equals("temperature")){
                                min = parser.getAttributeValue(null,"min");
                                x++;
                                total=x*y;
                                publishProgress(total);
                            }
                            if(tagName.equals("temperature")){
                                max = parser.getAttributeValue(null,"max");
                                x++;
                                total=x*y;
                                publishProgress(total);
                            }
                            if(tagName.equals("weather")){
                                icon = parser.getAttributeValue(null,"icon");
                            }
                            break;

                        case XmlResourceParser.END_TAG:
                            if (tagName.equals("speed")) {
                            }
                            break;
                    }
                    eventType = parser.next();
                }
                this.bitmap();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return speed;


        }

        private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
            String result = "";
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
                Toast.makeText(WeatherForecast.this, result, Toast.LENGTH_SHORT).show();
            }
            return result;
        }

        @Override
        public void onPostExecute(String speed){

           // super.onPostExecute(speed);
            TextView textView1 = findViewById(R.id.textView1);
            textView1.setText(speed);
            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(min);
            TextView textView3 = findViewById(R.id.textView3);
            textView3.setText(max);
            ImageView imageView1 = findViewById(R.id.imageView1);
            imageView1.setImageBitmap(image);
        }

        @Override
        public void onProgressUpdate(Integer... values){
            progressbar.setProgress(values[0]);
        }

        /*public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }*/


        public Bitmap bitmap() {
            try {
                String urlString1 = "http://openweathermap.org/img/w/" + icon + ".png";
                URL imageUrl = new URL(urlString1);
                HttpURLConnection conn1 = (HttpURLConnection) imageUrl.openConnection();
                conn1.connect();
                int responseCode = conn1.getResponseCode();
                InputStream in = new java.net.URL(urlString1).openStream();
                image = BitmapFactory.decodeStream(in);
                FileOutputStream outputStream = openFileOutput( icon + ".png", Context.MODE_PRIVATE);
                //image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                outputStream.flush();
                outputStream.close();

                if(fileExistance(icon+".png")) {
                    FileInputStream fis = null;
                    fis = openFileInput(icon + ".png");
                    bm = BitmapFactory.decodeStream(fis);

                }
                x++;
                total = x * y;
                publishProgress(total);
            }catch(Exception e){
                e.printStackTrace();
            }

            return bm;

        }



        public boolean fileExistance(String fname){
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }



    }
}