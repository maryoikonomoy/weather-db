/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.screen;
import database.Cities;
import database.Weather;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import weatherapp.DatabaseHandler;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

;

/**
 *
 * @author oikon
 */
public class thirdScreen_tst extends javax.swing.JFrame {

    
    DefaultListModel<String> cityListModel;
    
    
    public thirdScreen_tst() {
        initComponents();
        setBackgroundImage();
        
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        
        cityListModel = new DefaultListModel<>();//μοντελο λιστας για τις πόλεις
        jList1.setModel(cityListModel);
        jList1.addListSelectionListener(new ListSelectionListener() {
        @Override //να επιλέγει ο χρήστης την πόλη
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // Παίρνουμε την επιλεγμένη πόλη από τη λίστα
                String selectedCity = jList1.getSelectedValue();
            
                // Εμφανίζουμε τις ημερομηνίες αναζήτησης για την επιλεγμένη πόλη
                displaySearchDates(selectedCity);
            }
        }
        });
    
        refreshCityList(); // Ανανεώνει τη λίστα πόλεων
        jTextArea1.addMouseListener(new MyMouseListener());//mouse listener για το κλικ στην ημερομ
    
            
    }
    
    private void setBackgroundImage() {
    String localDir = System.getProperty("user.dir");
    
    String imagePath = localDir+"\\sky.png"; //εικόνα background
    ImageIcon backgroundImage = new ImageIcon(imagePath);
    Image image = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);//fit στο παράθυρο
    backgroundImage = new ImageIcon(image);
    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, getWidth(), getHeight()); 

    // Προσθέτει την εικόνα φόντου στο contentPane του παραθύρου
    ((JPanel) getContentPane()).setOpaque(false);
    getLayeredPane().add(backgroundLabel, new Integer(Integer.MIN_VALUE));
    }

    private class MyMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            // Παίρνει το κείμενο από το jTextArea1 όπου έγινε κλικ σε καποια ημερομ
            int offset = jTextArea1.viewToModel(e.getPoint());
            try {
                int rowStart = javax.swing.text.Utilities.getRowStart(jTextArea1, offset);
                int rowEnd = javax.swing.text.Utilities.getRowEnd(jTextArea1, offset);
                String selectedText = jTextArea1.getText().substring(rowStart, rowEnd);
                
                // χρησιμοπ το selecteddate για να φορτώσουμε τα δεδομένα καιρού στα  text fields
                loadDataForSelectedDate(selectedText);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
    private void loadDataForSelectedDate(String selectedDate) {
    //  φορτώνει δεδομένα καιρού για την επιλεγμένη ημερομηνία στα αντίστοιχα text fields
    DatabaseHandler dbHandler = new DatabaseHandler();
    List<Weather> searchResults = dbHandler.getAllWeather();
    
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    for (Weather weather : searchResults) { //μπηκε αυτη η συνθηκη ωστε να ελεγχει περα απο την ημερομ και την πολη που θα φορτωθουν τα δεδομ
        if (sdf1.format(weather.getDate()).equals(selectedDate) && weather.getCityIdFk().getName().equals(jList1.getSelectedValue())) {
            // Εδώ προσθέτει δεδομ στα textflds
            jTextField1.setText(String.valueOf(weather.getTempC()));
            jTextField2.setText(String.valueOf(weather.getHumidity()));
            jTextField3.setText(String.valueOf(weather.getWindspeedkmph()));
            jTextField4.setText(String.valueOf(weather.getUvindex()));
            jTextField5.setText(weather.getWeatherdesc());
            break; 
        }
    }
}
    private void displaySearchDates(String cityName) {
     // Εδώ θα παρουμε τα dates από τν βάση ανάλογα με την επιλογή πόλης του χρήστη
    DatabaseHandler dbHandler = new DatabaseHandler();
    List<Weather> searchResults = dbHandler.getAllWeather();

    StringBuilder datesText = new StringBuilder();
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    for (Weather weather : searchResults) {
    if (weather.getCityIdFk().getName().equals(cityName)) {
        datesText.append(sdf1.format(weather.getDate())).append("\n");//τσεκ αν ανηκει στη σωστη πολη
           
        }
    }
    
    jTextArea1.setText(datesText.toString());
}
    private void refreshCityList() {
        // Δημιουργεί  αντικείμενο DatabaseHandler
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        // Ανακτά τη λίστα πόλεων 
        List<Cities> cities = dbHandler.getAllCities();
        
        // Αδειάζει τη λίστα πόλεων
        cityListModel.clear();
        
        // Προσθέτει κάθε πόλη που αναζητ στη λίστα πόλεων
        for (Cities city : cities) {
            cityListModel.addElement(city.getName());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);
        jList1.getAccessibleContext().setAccessibleParent(jList1);

        jTextArea1.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextArea1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTextArea1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                jTextArea1AncestorRemoved(evt);
            }
        });
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setText("Θερμοκρασία");

        jLabel2.setText("Υγρασία");

        jLabel3.setText("Ταχύτητα ανέμου");

        jLabel4.setText("Δείκτης UV");

        jLabel5.setText("Λεκτική πρόγνωση καιρού");

        jButton1.setText("Αρχική Οθόνη");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Εμφάνιση στατιστικών");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1AncestorAdded

    private void jTextArea1AncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTextArea1AncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1AncestorRemoved
    
    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //κουμπι αρχικής
        screen myScreen = new screen();
        myScreen.setVisible(true);//την κάνει ορατή
        dispose(); // Κλείνει την τρέχουσα οθόνη
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // εμφάνιση 4ης οθόνης με στατιστικά
        fourthScreen myfourthScreen= new fourthScreen();
        myfourthScreen.setVisible(true);//την κάνει ορατή
        dispose();//κλείνει την τρέχουσα οθόνη
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(thirdScreen_tst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(thirdScreen_tst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(thirdScreen_tst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(thirdScreen_tst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new thirdScreen_tst().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
