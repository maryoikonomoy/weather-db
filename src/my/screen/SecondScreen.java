/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.screen;
import database.Cities;
import database.Weather;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import weatherapp.DatabaseHandler;
/**
 *
 * @author oikon
 */
public class SecondScreen extends javax.swing.JFrame {
    //initialization
    private Weather weather;
    private Cities city;
    private DatabaseHandler dh;
       
    private void refreshFields(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); //sets the date format
        String date1 = sdf1.format(weather.getDate());//gets date
        jTextField_tempc.setText(weather.getTempC()); //shows weather_it data to texfields
        jTextField_humidity.setText(weather.getHumidity());
        jTextField_windspeed.setText(weather.getWindspeedkmph());
        jTextField_uvindex.setText(weather.getUvindex());
        jTextField_weather_desc.setText(weather.getWeatherdesc());
        jLabel6.setText("Οι καιρικές ενδείξεις της πόλης  " + city.getName()+" για " + date1); //displays the city_it selected by the user 
    }   
    
    private void toggleTextfieldstatus(){ //changes the color according to the condition
        if(jTextField_tempc.isEditable()){
            jTextField_tempc.setEditable(false);
            jTextField_tempc.setBackground(Color.white);
            weather.setTempC(jTextField_tempc.getText());
            jTextField_humidity.setEditable(false);
            jTextField_humidity.setBackground(Color.white);
            weather.setHumidity(jTextField_humidity.getText());
            jTextField_windspeed.setEditable(false);
            jTextField_windspeed.setBackground(Color.white);
            weather.setWindspeedkmph(jTextField_windspeed.getText());
            jTextField_uvindex.setEditable(false);
            jTextField_uvindex.setBackground(Color.white);
            weather.setUvindex(jTextField_uvindex.getText());
            jTextField_weather_desc.setEditable(false);
            jTextField_weather_desc.setBackground(Color.white);
            weather.setWeatherdesc(jTextField_weather_desc.getText());
            jButton_epexergasia_dedomenwn.setText("Επεξεργασία Δεδομένων");
        }else{
            jTextField_tempc.setEditable(true);
            jTextField_tempc.setBackground(Color.red);
            jTextField_humidity.setEditable(true);
            jTextField_humidity.setBackground(Color.red);
            jTextField_windspeed.setEditable(true);
            jTextField_windspeed.setBackground(Color.red);
            jTextField_uvindex.setEditable(true);
            jTextField_uvindex.setBackground(Color.red);
            jTextField_weather_desc.setEditable(true);
            jTextField_weather_desc.setBackground(Color.red);
            jButton_epexergasia_dedomenwn.setText("Αποδοχή αλλαγών");
        }
    }
    
    private void timedMessage(String str){
        Timer timer = new Timer(2000, e -> { 
        //timer (2sec) for the wrong input message
        // reset of the message when the timer is disabled
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm"); //
        String date1 = sdf1.format(weather.getDate());
        jLabel6.setText("Οι καιρικές ενδείξεις της πόλης  " + city.getName()+" για " + date1);
        });
        timer.setRepeats(false); // executed once
        jLabel6.setText(str);
        timer.start();
    }
    
    /**
     * Creates new form SecondScreen
     */
    
    //Αυτός ο constructor είναι για τη main μέσα στο SecondScreen
    //και εμφανίζει το template της οθόνης
    public SecondScreen() {
        city = new Cities();
        weather = new Weather();
        dh = new DatabaseHandler();
        initComponents();
        setBackgroundImage();
        toggleTextfieldstatus();
    } 
    
    //constructor
    public SecondScreen(Cities city_in, Weather weather_in) {
        city = new Cities();
        weather = new Weather();
        dh = new DatabaseHandler();
        city = city_in;
        weather = weather_in;
        initComponents();
        refreshFields();
        setBackgroundImage();
        toggleTextfieldstatus();
    }        


    private void setBackgroundImage() {
    String localDir = System.getProperty("user.dir");
    
    String imagePath = localDir+"\\sky.png"; //background img
    ImageIcon backgroundImage = new ImageIcon(imagePath);
    Image image = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);//window fit
    backgroundImage = new ImageIcon(image);
    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); 

    // adds the background image to the contentPane of the window
    ((JPanel) getContentPane()).setOpaque(false);
    getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField_tempc = new javax.swing.JTextField();
        jTextField_humidity = new javax.swing.JTextField();
        jTextField_windspeed = new javax.swing.JTextField();
        jTextField_uvindex = new javax.swing.JTextField();
        jTextField_weather_desc = new javax.swing.JTextField();
        jButton_epexergasia_dedomenwn = new javax.swing.JButton();
        jButton_exodos = new javax.swing.JButton();
        jButton_arxiki_othoni = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton_apothikefsi = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Προβολή δεδομένων πόλης");

        jButton_epexergasia_dedomenwn.setText("Επεξεργασία δεδομένων ");
        jButton_epexergasia_dedomenwn.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton_epexergasia_dedomenwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_epexergasia_dedomenwnActionPerformed(evt);
            }
        });

        jButton_exodos.setText("Έξοδος");
        jButton_exodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_exodosActionPerformed(evt);
            }
        });

        jButton_arxiki_othoni.setText("Αρχική Οθόνη");
        jButton_arxiki_othoni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_arxiki_othoniActionPerformed(evt);
            }
        });

        jLabel1.setText("Θερμοκρασία");

        jLabel2.setText("Υγρασία");

        jLabel3.setText("Ταχύτητα ανέμου");

        jLabel4.setText("Δείκτης UV");

        jLabel5.setText("Λεκτική πρόγνωση καιρού");

        jButton_apothikefsi.setText("Αποθήκευση");
        jButton_apothikefsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_apothikefsiActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton_epexergasia_dedomenwn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField_humidity, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_tempc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_windspeed, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_uvindex, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField_weather_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton_arxiki_othoni, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_apothikefsi))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_exodos)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_tempc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_humidity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_windspeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_uvindex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_weather_desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_arxiki_othoni)
                    .addComponent(jButton_epexergasia_dedomenwn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_apothikefsi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_exodos)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
         
    private void jButton_epexergasia_dedomenwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_epexergasia_dedomenwnActionPerformed
        toggleTextfieldstatus();
        if(!jTextField_tempc.isEditable()){ // If the text field is not editable throws a msg
        int choice = JOptionPane.showConfirmDialog(null, "Είστε βέβαιοι ότι θέλετε να σώσετε τις αλλαγές?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            timedMessage("Changes saved");
        }
    }
    }//GEN-LAST:event_jButton_epexergasia_dedomenwnActionPerformed

    private void jButton_exodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_exodosActionPerformed
        int choice = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος ότι θέλετε να κλείσετε το πρόγραμμα;", "Έξοδος", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0); // close app
        } 
    }//GEN-LAST:event_jButton_exodosActionPerformed

    private void jButton_arxiki_othoniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_arxiki_othoniActionPerformed
        screen myScreen = new screen();
        myScreen.setVisible(true);//jumps to firstscreen
        dispose(); // close current screen
    }//GEN-LAST:event_jButton_arxiki_othoniActionPerformed

    private void jButton_apothikefsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_apothikefsiActionPerformed
        saveData();
    }//GEN-LAST:event_jButton_apothikefsiActionPerformed
    // save city_it and weather_it method
    private void saveData() {
        for(Cities city_it:dh.getAllCities()){
            if(city.getName().equals(city_it.getName())){
                for(Weather weather_it:city_it.getWeatherCollection()){
                    if(weather.getDate().equals(weather_it.getDate())){
                        timedMessage("Ο καιρός έχει ήδη αποθηκευτεί");
                        return;
                    }
                }
            }
        }
        

        dh.insertCityAndWeather(city, weather);
        timedMessage(dh.getMsg());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SecondScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SecondScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SecondScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SecondScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SecondScreen().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_apothikefsi;
    private javax.swing.JButton jButton_arxiki_othoni;
    private javax.swing.JButton jButton_epexergasia_dedomenwn;
    private javax.swing.JButton jButton_exodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField_humidity;
    private javax.swing.JTextField jTextField_tempc;
    private javax.swing.JTextField jTextField_uvindex;
    private javax.swing.JTextField jTextField_weather_desc;
    private javax.swing.JTextField jTextField_windspeed;
    // End of variables declaration//GEN-END:variables
}
