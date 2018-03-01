package uwe.asGUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import uwe.as.Hall;
import uwe.as.Room;
import uwe.as.User;

/**
 *
 * @author Jamie Mills (16004255)
 */
public class CreateLease extends javax.swing.JFrame {

    public static uwe.as.Data_Cache data_cache;
    private List<Room> rooms;
    private List<Hall> halls;
    private List<User> students;
    MainScreen mainScreen;
    /**
     * Creates new form CreatLease
     */
    public CreateLease(MainScreen mainScreen) {
        initComponents();
        importFromCache();
        populateDateField();
        populateHallBox();
        populateRooms();
        populateStudents();
        this.mainScreen = mainScreen;
        this.setVisible(true);
    }
    
    private void importFromCache()
    {
       this.rooms = data_cache.getRooms();
       this.halls = data_cache.getHalls();
       List<User> users = data_cache.getUsers();
       this.students = users.stream().filter(u -> u.getAccountLevel() == 0).collect(Collectors.toList());
    }
    
    private void populateDateField()
    {
       DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
       Date now = Calendar.getInstance().getTime();
       String date = df.format(now);
       this.textfieldDate.setText(date);   
    }
    
    private void populateHallBox()
    {
        comboBoxHall.removeAllItems();
        for (Hall h : halls)
        {
            if (!h.getRooms().isEmpty())
            {
                comboBoxHall.addItem(h.getName());
            }
        }
    }
    
    private Hall getCurrentHall()
    {
        String currentHallName = comboBoxHall.getItemAt(comboBoxHall.getSelectedIndex());
        List<Hall> filteredHalls = halls.stream().filter(h -> h.getName().equals(currentHallName)).collect(Collectors.toList());
        if (!filteredHalls.isEmpty())
        {
            return filteredHalls.get(0);
        }
        return null;
    }
    
    private Room getCurrentRoom()
    {
        String currentRoomNumber = comboBoxRoom.getItemAt(comboBoxRoom.getSelectedIndex());
        List<Room> filteredRooms = rooms.stream().filter(r -> r.getNumber().equals(currentRoomNumber)).collect(Collectors.toList());
        if (!filteredRooms.isEmpty())
        {
          return filteredRooms.get(0);
        }
        return null; 
    }
    
    private User getCurrentStudent()
    {
        String currentStudent = comboBoxStudent.getItemAt(comboBoxStudent.getSelectedIndex());
        List<User> filteredStudents = students.stream().filter(u -> u.getRealName() == currentStudent).collect(Collectors.toList());
        if (!filteredStudents.isEmpty())
        {
          return filteredStudents.get(0);
        }
        return null;  
    }
    
    private void populateRooms()
    {
        Hall currentHall = getCurrentHall();
        comboBoxRoom.removeAllItems();
        if (currentHall != null)
        {
            List<Room> roomsInHall = rooms.stream().filter(r -> currentHall.getRooms().contains(r.getUID())).collect(Collectors.toList());
            for (Room r : roomsInHall)
            {
                comboBoxRoom.addItem(r.getNumber());
            }
        }
    }
    
    private void populateStudents()
    {
        comboBoxStudent.removeAllItems();
        for (User u : students)
        {
            comboBoxStudent.addItem(u.getRealName());
        }
    }
    
    private void getAllFieldsAndSubmit()
    {
        int currentRoomUID = -1;
        int currentStudentUID = -1;
        int leaseNumber = Integer.parseInt(textfieldLeaseNumber.getText());
        int duration = Integer.parseInt(comboBoxDuration.getItemAt(comboBoxDuration.getSelectedIndex()));
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        try
        {
            date = df.parse(textfieldDate.getText());
        }
        catch (ParseException ex)
        {
            System.out.println("DB_Controller.getLeaseFromResult() produced the following error:");
            System.out.println(ex);
        }
        
        Room currentRoom = getCurrentRoom();
        if (currentRoom != null)
        {
            currentRoomUID = currentRoom.getUID();
        }
        
        User currentStudent = getCurrentStudent();
        if (currentStudent != null)
        {
            currentStudentUID = currentStudent.getUID();
        }
        
        System.out.println("Creating a new lease with the following properties: (" +
                Integer.toString(leaseNumber) + " " +
                Integer.toString(currentStudentUID) + " " +
                Integer.toString(currentRoomUID) + " " +
                Integer.toString(duration) + " " +
                df.format(date));
        new uwe.as.Lease(leaseNumber, currentStudentUID, currentRoomUID, duration, date);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBoxHall = new javax.swing.JComboBox<>();
        labelHall = new javax.swing.JLabel();
        comboBoxRoom = new javax.swing.JComboBox<>();
        labelRoom = new javax.swing.JLabel();
        labelStudent = new javax.swing.JLabel();
        labelDuration = new javax.swing.JLabel();
        labelStartDate = new javax.swing.JLabel();
        comboBoxStudent = new javax.swing.JComboBox<>();
        textfieldDate = new javax.swing.JFormattedTextField();
        buttonCancel = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        labelTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        comboBoxDuration = new javax.swing.JComboBox<>();
        labelLeaseNumber = new javax.swing.JLabel();
        textfieldLeaseNumber = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Lease");
        setAlwaysOnTop(true);
        setResizable(false);

        comboBoxHall.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBoxHall.setPreferredSize(new java.awt.Dimension(100, 20));
        comboBoxHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxHallActionPerformed(evt);
            }
        });

        labelHall.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelHall.setText("Hall");

        comboBoxRoom.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBoxRoom.setPreferredSize(new java.awt.Dimension(100, 20));

        labelRoom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelRoom.setText("Room");

        labelStudent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelStudent.setText("Student");
        labelStudent.setToolTipText("");

        labelDuration.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelDuration.setText("Duration (Months)");

        labelStartDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelStartDate.setText("Start Date");

        comboBoxStudent.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBoxStudent.setPreferredSize(new java.awt.Dimension(100, 20));

        textfieldDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        textfieldDate.setMinimumSize(new java.awt.Dimension(100, 20));
        textfieldDate.setPreferredSize(new java.awt.Dimension(100, 20));

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        labelTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelTitle.setText("Add New Lease");

        comboBoxDuration.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "12", "18", "24" }));
        comboBoxDuration.setSelectedIndex(1);
        comboBoxDuration.setMinimumSize(new java.awt.Dimension(100, 20));
        comboBoxDuration.setPreferredSize(new java.awt.Dimension(100, 20));

        labelLeaseNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelLeaseNumber.setText("Lease Number");

        textfieldLeaseNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        textfieldLeaseNumber.setMinimumSize(new java.awt.Dimension(100, 20));
        textfieldLeaseNumber.setPreferredSize(new java.awt.Dimension(100, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonCancel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTitle)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelHall)
                            .addComponent(labelRoom)
                            .addComponent(labelStudent)
                            .addComponent(labelDuration)
                            .addComponent(labelStartDate)
                            .addComponent(labelLeaseNumber))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(31, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textfieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textfieldLeaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(labelTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHall)
                    .addComponent(comboBoxHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRoom)
                    .addComponent(comboBoxRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStudent)
                    .addComponent(comboBoxStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDuration)
                    .addComponent(comboBoxDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStartDate)
                    .addComponent(textfieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLeaseNumber)
                    .addComponent(textfieldLeaseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancel)
                    .addComponent(buttonAdd))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxHallActionPerformed
        populateRooms();
    }//GEN-LAST:event_comboBoxHallActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        mainScreen.createLeaseClosing();
        this.dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        getAllFieldsAndSubmit();
        mainScreen.createLeaseClosing();
        this.dispose();
    }//GEN-LAST:event_buttonAddActionPerformed

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
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateLease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JComboBox<String> comboBoxDuration;
    private javax.swing.JComboBox<String> comboBoxHall;
    private javax.swing.JComboBox<String> comboBoxRoom;
    private javax.swing.JComboBox<String> comboBoxStudent;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelDuration;
    private javax.swing.JLabel labelHall;
    private javax.swing.JLabel labelLeaseNumber;
    private javax.swing.JLabel labelRoom;
    private javax.swing.JLabel labelStartDate;
    private javax.swing.JLabel labelStudent;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JFormattedTextField textfieldDate;
    private javax.swing.JFormattedTextField textfieldLeaseNumber;
    // End of variables declaration//GEN-END:variables
}