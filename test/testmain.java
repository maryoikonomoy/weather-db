
import database.Cities;
import database.Weather;
import java.text.ParseException;
import weatherapp.DatabaseHandler;
import weatherapp.WebHandler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Giorgos
 */
public class testmain {
     public static void main(String[] args) throws ParseException{
         Cities c = new Cities();
         Weather w = new Weather();
         WebHandler wh = new WebHandler();
         DatabaseHandler dh = new DatabaseHandler();
         //wh.getCityAndWeather("kalamata", c, w);
         //c.showElements();
         //w.showElements();
         //dh.insertCity(c);
         //dh.insertWeather(w, c);
         //c.showElements();
         //dh.deleteCity(dh.getAllCities().get(0));
     }
}
