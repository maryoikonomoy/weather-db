/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weatherapp;

import database.Cities;
import database.Weather;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

/**
 *
 * @author Αλέξης
 */
public class PdfHandler {
    private String text;
    private DatabaseHandler dbHandler;

    public PdfHandler() {
       dbHandler = new DatabaseHandler();
       text = "";
    }
    //check if database is populated
    private boolean checkData(){     
        List<Cities> cities = dbHandler.getAllCities();
        return !cities.isEmpty();
    }
    //creates the pdf text
    private void setText(){
        text = "Στατιστικά Δεδομένα Eπισκεψιμότητας \n----------------------------\n"; // To εκτυπώσιμο text
        int counter =0; // Ο μετρητής για τις πόλεις στα array
        // Φόρτωση των δεδομένων από λίστα
        List<Cities> cities = dbHandler.getAllCities();
        List<Weather> searchResults = dbHandler.getAllWeather();  
        // Τα δύο arrays που περιέχουν αυτά που θέλιυμε να εκτυπώσουμε
        String[] cityList = new String[cities.size()];
        int[] timesCounted =new int[cities.size()];
        //Γεμίζω τα arrays με τα ζητούμενα
        for (Cities c: cities) {
            int numOfSearches = 0;
            cityList[counter]=c.getName();
            for (Weather w: searchResults){
                if (c.getName().equals(w.getCityIdFk().getName())){
                   numOfSearches++;
                } 
            }
           timesCounted[counter]=numOfSearches;
           counter++;
        }
        // Ταξινομώ τα δύο arrays με βάση την επισκεψιμότητα 
        // Το length είναι το counter
         int temp = 0;
         String var ="";
             for(int i=0; i < counter; i++){  
                     for(int j=1; j < (counter-i); j++){  
                              if(timesCounted[j-1] < timesCounted[j]){  
                                     //swap elements  
                                     temp = timesCounted[j-1];
                                     var = cityList[j-1];
                                     timesCounted[j-1] = timesCounted[j];
                                     cityList[j-1] = cityList[j];
                                     timesCounted[j] = temp;
                                     cityList[j] = var;
                             }  

                     }  
             }  
        // Προσθήκη με την σωστή σειρά στο text
        for(int i=0; i < counter; i++){
           String times = String.valueOf(timesCounted[i]);
        text = text+cityList[i]+ ": \t "+ times + "\n"; 
        }
    }
    //creates and retuns the pdf text
    public String getText(){
        setText();
        return text;
    }
    //saves the pdf
    public void printPdf(){
        try {
        // Set font for utf-8, 1253     
        BaseFont bf=BaseFont.createFont("c:/windows/fonts/arial.ttf","CP1253",BaseFont.NOT_EMBEDDED);
        bf.setSubset(true);
        Font arial = new Font(bf, 12);
        //Create Document instance.
        Document document = new Document();
        String localDir = System.getProperty("user.dir");
        //Create OutputStream instance.
        OutputStream outputStream = new FileOutputStream(new File(localDir + "\\statistics.pdf"));
        //Create PDFWriter instance.
        PdfWriter.getInstance(document, outputStream);

        //Open the document.
        document.open();

        //Add content to the document
        Paragraph textToAdd = new Paragraph(getText(),arial);
        document.add(textToAdd);

        //Close document and outputStream.
            document.close();
            outputStream.close();
            System.out.println("Pdf created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}