/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherapp;

import database.Cities;
import database.Weather;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Giorgos
 */
public class DatabaseHandler {
    private String msg;
    //decided not to create a single entity manager in the constructor to avoid
    //creating a method that does em.close that can be easily forgotten by the user
    //each method has its own emfactory wich is slower but sefer
    public DatabaseHandler() {
        msg = null;
    }
    //returns message of handles results
    public String getMsg(){
        return msg;
    }
    //saves a city in the database
    //boolean returns false if method fails to save city in the database
    public boolean insertCity(Cities cityin){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherAppPU");
        EntityManager em = emf.createEntityManager();
        //check if city exists in the database and if yes exits the method
        //returns true and not false because this is intended
        List<Cities> list = getAllCities();
        for(Cities city : list){
            if (city.getName().equals(cityin.getName())){
                msg = "Η πόλη είναι ήδη αποθηκευμένη στη database";
                return true;
            }
        }
        //sets city id
        cityin.setId(getNextCityId(list));
        //save city in the database
        try{
            em.getTransaction().begin();
            em.persist(cityin);
            em.getTransaction().commit();
        }catch(Exception e){
            msg = "Σφάλμα κατα την αποθήκευση της πόλης";
            return false;
        }finally{
            em.close();
            emf.close();
            return true;
        }
    }
    //saves a weather in the database
    //needs the corresponding city for the foreign key
    //boolean returns false if method fails to save returns in the database
    //we allow duplicate wether inputs to be saved
    //no duplicate wethers is an application requrement and not a database reqirement
    //we check for duplicate weathers in the application and prevent method execution
    public boolean insertWeather(Weather weatherin, Cities cityin){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherAppPU");
        EntityManager em = emf.createEntityManager();
        //sets weather id
        List<Weather> list = getAllWeather();
        weatherin.setId(getNextWeatherId(list));
        //sets weather foreign key
        List<Cities> list1 = getAllCities();
        for(Cities city : list1){
            if (city.getName().equals(cityin.getName())){
                weatherin.setCityIdFk(city);
                break;
            }
        }
        //save weather in the database
        try{
            em.getTransaction().begin();
            em.persist(weatherin);
            em.getTransaction().commit();
        }catch(Exception e){
            msg = "Σφάλμα κατά την αποθήκευση του καιρού";
            return false;
        }finally{
            em.close();
            emf.close();
            msg = "Ο καιρός αποθηκεύτηκε επιτυχώς";
            return true;
        }
    }
    //does both previous methods combined
    //we choose this method for simplicity
    public boolean insertCityAndWeather(Cities cityin,Weather weatherin){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherAppPU");
        EntityManager em = emf.createEntityManager();
        
        List<Cities> list_c = getAllCities();
        List<Weather> list_w = getAllWeather();
        //check if city already exists
        for(Cities city : list_c){
            if (city.getName().equals(cityin.getName())){
                cityin.setId(city.getId());
                weatherin.setId(getNextWeatherId(list_w));
                weatherin.setCityIdFk(city);
                try{
                    em.getTransaction().begin();
                    em.persist(weatherin);
                    em.getTransaction().commit();
                }catch(Exception e){
                    msg = "Σφάλμα κατά την αποθήκευση του καιρού";
                }finally{
                    em.close();
                    emf.close();
                    msg = "Ο καιρός αποθηκεύτηκε επιτυχώς";
                    return true;
                }
            }
        }
        
        cityin.setId(getNextCityId(list_c));

        weatherin.setId(getNextWeatherId(list_w));
        weatherin.setCityIdFk(cityin);
        
        try{
            em.getTransaction().begin();
            em.persist(cityin);
            em.persist(weatherin);
            em.getTransaction().commit();
        }catch(Exception e){
            msg = "Σφάλμα κατά την αποθήκευση είτε τησ πόλης είτε του καιρού";
            return false;
        }finally{
            em.close();
            emf.close();
            msg = "Η πόλη και ο καιρός αποθηκεύτηκαν επιτυχώς";
            return true;
        }
    }
    //deletes a city from the database and all its corresponding weather
    public boolean deleteCity(Cities city){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherAppPU");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            //merge the city object with the actual database object
            Cities managedCity = em.merge(city);
            //remove the merged city
            em.remove(managedCity);
            em.getTransaction().commit();
        }catch(Exception e){
            msg = "Σφάλμα κατά τη διαγραφή τησ πόλης";
            return false;
        }finally{
            em.close();
            emf.close();
        }
        return true;
    }
    //returns a list of all the cities in database
    public List<Cities> getAllCities(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherAppPU");
        EntityManager em = emf.createEntityManager();
        List<Cities> list = null;
        try{
            em.getTransaction().begin();
            list = em.createQuery("SELECT a FROM Cities a",Cities.class).getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            System.err.println("Error loading Cities from Database in getAllCities method");
        }finally{
            em.close();
            emf.close();
            return list;
        }
    }
    //returns a list of all the weathers in database
    //to find a specific weather when we know the city it belongs we prefer
    //to look through the city's weathercollection because it is smaller 
    //and thus faster
    public List<Weather> getAllWeather(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WeatherAppPU");
        EntityManager em = emf.createEntityManager();

        List<Weather> list = null;
        try{
            em.getTransaction().begin();
            list = em.createQuery("SELECT w FROM Weather w",Weather.class).getResultList();
            em.getTransaction().commit();
        }catch(Exception e){
            System.err.println("Error loading Weather from Database in getAllCities method");
        }finally{
            em.close();
            emf.close();
            return list;
        }
    }
    //returns the next available id for a city input
    private Integer getNextCityId(List<Cities> list){
        first: for (int i=1; i<=list.size(); i++){
            second: for(Cities city : list){
                if (city.getId()==i){
                    continue first;
                }
            }
            return i;
        }
        return list.size()+1;
    }
    //returns the next available id for a weather input
    private Integer getNextWeatherId(List<Weather> list){
        first: for (int i=1; i<=list.size(); i++){
            second: for(Weather weather : list){
                if (weather.getId()==i){
                    continue first;
                }
            }
            return i;
        }
        return list.size()+1;
    }
}
