/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherapp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import database.Cities;
import database.Weather;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Giorgos
 */
public class WebHandler {
    private String Json;
    private String msg;

    public WebHandler() {
        msg = null;
        Json = null;
    }
    //returns message of handles results
    public String getMsg(){
        return msg;
    }
    //retrieves the Json from wttr
    //boolean returns false if the method fails to retrieve
    private boolean getJson(String City){
        String urlToCall = "https://wttr.in/" + City + "?format=j1";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlToCall).build();
        try (Response response = client.newCall(request).execute()) {
            if(response.isSuccessful()) {
                this.Json =  response.body().string();
            }else{
                this.msg="Δε δόθηκε απάντηση από το wttr";
                return false;
            }
        }catch (IOException e) {
            this.msg="Πρόβλημα σύνδεσης με wttr";
            return false;
        }
        return true;
    }
    //updates city and weather objects from Json
    //boolean returns false if the method fails to update
    public boolean getCityAndWeather(String City_str, Cities city, Weather weather) throws ParseException{
        if (this.getJson(City_str)){
            JsonParser parser = new JsonParser();
            JsonElement jsonTree = parser.parse(this.Json);
            
            JsonObject jsonObject1 = jsonTree.getAsJsonObject();
            JsonObject nearest_area = jsonObject1.getAsJsonArray("nearest_area").get(0).getAsJsonObject();
            JsonObject areaName = nearest_area.getAsJsonArray("areaName").get(0).getAsJsonObject();
            
            String cityname = areaName.get("value").toString();
            city.setName(cityname.substring(1,cityname.length()-1));//substring to rempve quotes from strings
            
            JsonObject current_condition = jsonObject1.getAsJsonArray("current_condition").get(0).getAsJsonObject();
            
            String weathertemp = current_condition.get("temp_C").toString();
            weather.setTempC(weathertemp.substring(1,weathertemp.length()-1));
            
            String weatherhumidity = current_condition.get("humidity").toString();
            weather.setHumidity(weatherhumidity.substring(1,weatherhumidity.length()-1));
            
            String weatheruvindex = current_condition.get("uvIndex").toString();
            weather.setUvindex(weatheruvindex.substring(1,weatheruvindex.length()-1));
            
            String weatherwindspeed = current_condition.get("windspeedKmph").toString();
            weather.setWindspeedkmph(weatherwindspeed.substring(1,weatherwindspeed.length()-1));
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd h:mm a", Locale.US);
            String dateString = current_condition.get("localObsDateTime").toString().substring(1,20);
            weather.setDate(df.parse(dateString));
            
            JsonObject weatherDesc = current_condition.getAsJsonArray("weatherDesc").get(0).getAsJsonObject();    
            
            String weatherdesc = weatherDesc.get("value").toString();
            weather.setWeatherdesc(weatherdesc.substring(1,weatherdesc.length()-1));
            
            weather.setCityIdFk(city);
        }else{
            return false;
        }
        return true;
    }
}
