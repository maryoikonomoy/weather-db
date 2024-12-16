package weatherapp;

import javax.swing.SwingUtilities;
import my.screen.screen;

public class WeatherApp {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new screen().setVisible(true);
            }
        });
    }
}
