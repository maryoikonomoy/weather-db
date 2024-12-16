/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Giorgos
 */
@Entity
@Table(name = "WEATHER")
@NamedQueries({
    @NamedQuery(name = "Weather.findAll", query = "SELECT w FROM Weather w"),
    @NamedQuery(name = "Weather.findById", query = "SELECT w FROM Weather w WHERE w.id = :id"),
    @NamedQuery(name = "Weather.findByDate", query = "SELECT w FROM Weather w WHERE w.date = :date"),
    @NamedQuery(name = "Weather.findByTempC", query = "SELECT w FROM Weather w WHERE w.tempC = :tempC"),
    @NamedQuery(name = "Weather.findByHumidity", query = "SELECT w FROM Weather w WHERE w.humidity = :humidity"),
    @NamedQuery(name = "Weather.findByWindspeedkmph", query = "SELECT w FROM Weather w WHERE w.windspeedkmph = :windspeedkmph"),
    @NamedQuery(name = "Weather.findByUvindex", query = "SELECT w FROM Weather w WHERE w.uvindex = :uvindex"),
    @NamedQuery(name = "Weather.findByWeatherdesc", query = "SELECT w FROM Weather w WHERE w.weatherdesc = :weatherdesc")})
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "TEMP_C")
    private String tempC;
    @Basic(optional = false)
    @Column(name = "HUMIDITY")
    private String humidity;
    @Basic(optional = false)
    @Column(name = "WINDSPEEDKMPH")
    private String windspeedkmph;
    @Basic(optional = false)
    @Column(name = "UVINDEX")
    private String uvindex;
    @Basic(optional = false)
    @Column(name = "WEATHERDESC")
    private String weatherdesc;
    @JoinColumn(name = "CITY_ID_FK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cities cityIdFk;

    public Weather() {
    }

    public Weather(Integer id) {
        this.id = id;
    }

    public Weather(Integer id, Date date, String tempC, String humidity, String windspeedkmph, String uvindex, String weatherdesc) {
        this.id = id;
        this.date = date;
        this.tempC = tempC;
        this.humidity = humidity;
        this.windspeedkmph = windspeedkmph;
        this.uvindex = uvindex;
        this.weatherdesc = weatherdesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTempC() {
        return tempC;
    }

    public void setTempC(String tempC) {
        this.tempC = tempC;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindspeedkmph() {
        return windspeedkmph;
    }

    public void setWindspeedkmph(String windspeedkmph) {
        this.windspeedkmph = windspeedkmph;
    }

    public String getUvindex() {
        return uvindex;
    }

    public void setUvindex(String uvindex) {
        this.uvindex = uvindex;
    }

    public String getWeatherdesc() {
        return weatherdesc;
    }

    public void setWeatherdesc(String weatherdesc) {
        this.weatherdesc = weatherdesc;
    }

    public Cities getCityIdFk() {
        return cityIdFk;
    }

    public void setCityIdFk(Cities cityIdFk) {
        this.cityIdFk = cityIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Weather)) {
            return false;
        }
        Weather other = (Weather) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Weather[ id=" + id + " ]";
    }
    //method for presenting objects variables
    public void showElements(){
        if(this.id==null){
            System.out.println("NO ID");
        }else{
            System.out.println("ID " + this.id.toString());
        }
        
        if(this.date==null){
            System.out.println("NO DATE");
        }else{
            System.out.println("DATE " + this.date.toString());
        }
        
        System.out.println("TEMPC " + this.tempC);
        System.out.println("HUMIDITY " + this.humidity);
        System.out.println("UVINDEX " + this.uvindex);
        System.out.println("WINDSPEED " + this.windspeedkmph);
        System.out.println("WEATHERDESC " + this.weatherdesc);
        
        if(this.cityIdFk==null){
            System.out.println("NO CITY_ID");
        }else{
            System.out.println("CITY_ID " + this.cityIdFk.toString());
        }
    }
}
