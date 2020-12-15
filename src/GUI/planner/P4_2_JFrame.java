/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.planner;


import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import users.User;

import users.maintainer.Maintainer;

import Activity.Activity;
import Activity.ActivityBuilder;

import Activity.Planned.PlannedBuilder;

import Activity.competence.Competence;
import Activity.competence.Competences;
import users.JDBC;
import users.maintainer.MaintainerFactory;
import users.maintainer.Maintainer_JDBC;
import users.planner.PlannerFactory;
import users.planner.Planner_JDBC;

/**
 *
 * @author Francesco Femia
 */
public class P4_2_JFrame extends javax.swing.JFrame {
    
    private DefaultTableModel dtm;
    private Activity activity;
    private Maintainer maintainer;
    private User planner;
    private JDBC jdbc_p;
    private JDBC jdbc_m;
    private int et, count=0;
    private int remainingTime;
    
    private String[] days = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
    /**
     * Creates new form P4_JFrame
     * @param ac
     * @param m
     * @param planner
     * @param day
     */
    public P4_2_JFrame(Activity ac, User m, User planner,String day) {
        this.planner = planner;
        this.activity=ac;
        this.maintainer=(Maintainer) m;
        
        
        this.jdbc_m = new MaintainerFactory().createJDBCUser(m.getUsername(), m.getPassword());
        this.jdbc_p = new PlannerFactory().createJDBCUser(planner.getUsername(), planner.getPassword());
        this.dtm =  new DefaultTableModel(new String[] { "Maintainer", "Skills", "Avilab. 8.00-9.00", "Avilab. 9.00-10.00", 
            "Avilab. 10.00-11.00", "Avilab. 11.00-12.00", "Avilab. 12.00-13.00", "Avilab. 13.00-14.00", "Avilab. 14.00-15.00" },0){
            
                //to set not editable table cells
                @Override
                public boolean isCellEditable(int row, int column){
                    return false;
                }
            
            };
        
        String user = maintainer.getUsername();
        String skill = maintainer.isQualified(activity.getCompetences())+"/"+activity.getCompetences().size();
        
        Object [] o = new Object[] {user,skill,maintainer.getSlotAvailability(activity.getWeek(), day, 0)+" min",maintainer.getSlotAvailability(activity.getWeek(), day, 1)+" min", ((Maintainer)maintainer).getSlotAvailability(activity.getWeek(),day, 2)+" min", maintainer.getSlotAvailability(activity.getWeek(),day, 3)+" min", maintainer.getSlotAvailability(activity.getWeek(), day, 4)+" min",maintainer.getSlotAvailability(activity.getWeek(),day, 5)+" min",maintainer.getSlotAvailability(activity.getWeek(),day, 6)+" min",maintainer.getSlotAvailability(activity.getWeek(),day, 7)+" min" };
        dtm.addRow(o);
        
        initComponents();
        
        this.et=ac.getEstimatedTime();
        this.percentuale.setText(String.valueOf(maintainer.getDayAvailability(activity.getWeek(),day))+"%");

        
        this.jTableMaintainerAvailability.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTableMaintainerAvailability.getSelectedRow();
                int col = jTableMaintainerAvailability.getSelectedColumn();
                
                count= ((Maintainer_JDBC)jdbc_m).selectCountActivityIDFromDatabase(ac);
                
                if(col>1 && et>0 && count==0){
                    
                    int avail = maintainer.getSlotAvailability(ac.getWeek(),day, col-2);
                    remainingTime=et-avail;  

                        int actTime=0;
                        if (remainingTime<=0)
                            actTime=avail-et;
                        else
                            actTime=avail - (et-remainingTime);
                    if(actTime>=0){
                        maintainer.addSlotAvailability(ac.getWeek(), day, col-2, actTime);
                        dtm.setValueAt( String.valueOf(actTime)+" min", row, col);
                        et-=avail;
                        percentuale.setText(String.valueOf(maintainer.getDayAvailability(activity.getWeek(), day))+"%");
                        
                        if(remainingTime<=0){
                            
                            ((Maintainer_JDBC)jdbc_m).saveActivityOnDatabase(activity);
                            activity.setAssigned(true);
                            ((Planner_JDBC)jdbc_p).updateStatusOnDatabase(activity, true);
                            maintainer.getActivities().insertInActivities(activity);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(jTableMaintainerAvailability, "Availability time too short","Ops...",JOptionPane.OK_CANCEL_OPTION);
                    }
                }
                if (count>0){
                    JOptionPane.showMessageDialog(jTableMaintainerAvailability, "Activity already assigned","Ops...",JOptionPane.OK_CANCEL_OPTION);
                    //Send.setEnabled(false);
                
                } 
            }
        });
        
        
        
        actInfo.setText(ac.toString());// manca typology
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), 1, 1).plusWeeks(activity.getWeek());
        
        DayOfWeek dow=date.getDayOfWeek();
        
        int val = 0;
        for(int i = 0; i < days.length; i++){
            if (day.equals(days[i])){
                val = i;
            }
        }
        int dayVal=val-dow.getValue();
        date=date.plusDays(dayVal);
        numDay.setText(String.valueOf(date.getDayOfMonth()));
        dayOfWeek.setText(date.getDayOfWeek().name());
        weekNum.setText(String.valueOf(activity.getWeek()));
        availabilityLabel.setText("AVAILABILITY "+ maintainer.getUsername().toUpperCase());      
        
    }
    
    private Action selection = new AbstractAction(){
       @Override
       public void actionPerformed(ActionEvent e)
       {
           JTable table = (JTable)e.getSource();
           int modelRow = Integer.valueOf( e.getActionCommand() );
           String v=((DefaultTableModel)table.getModel()).getDataVector().elementAt(modelRow).toString();
           String str=v.substring(1, v.length()-2);
           String []s= str.split(",");


       }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        weekNum = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        actInfo = new javax.swing.JLabel();
        availabilityLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMaintainerAvailability = new javax.swing.JTable(){
            @Override

            public Component prepareRenderer (TableCellRenderer renderer, int rowIndex, int columnIndex){
                Component componenet = super.prepareRenderer(renderer, rowIndex, columnIndex);

                Object obj = getModel().getValueAt(rowIndex,columnIndex);
                String str = (String) (obj);
                String value = str.replaceAll("[^0-9]", "");

                if(columnIndex > 1){

                    if(Integer.parseInt(value) < 10){
                        componenet.setBackground(Color.RED);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(Integer.parseInt(value) >= 10 && Integer.parseInt(value) < 30 ){
                        // if date  equal current date
                        componenet.setBackground(Color.ORANGE);
                        componenet.setForeground(Color.BLACK);
                    }

                    if(Integer.parseInt(value) >= 30 && Integer.parseInt(value) < 45){
                        // if date  equal current date
                        componenet.setBackground(Color.YELLOW);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(Integer.parseInt(value) >= 45 && Integer.parseInt(value) <= 60){
                        // if date  equal current date
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.WHITE);
                    }
                }
                else {

                    componenet.setBackground(Color.WHITE);
                    componenet.setForeground(Color.BLACK);
                }

                return componenet;
            }

        };
        dayOfWeek = new javax.swing.JLabel();
        numDay = new javax.swing.JLabel();
        percentuale = new javax.swing.JLabel();
        jButtonSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Week n°");
        jLabel1.setOpaque(true);

        weekNum.setBackground(new java.awt.Color(102, 102, 102));
        weekNum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        weekNum.setForeground(new java.awt.Color(255, 255, 255));
        weekNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        weekNum.setText("week");
        weekNum.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Activity to assign");
        jLabel3.setOpaque(true);

        actInfo.setBackground(new java.awt.Color(102, 102, 102));
        actInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        actInfo.setForeground(new java.awt.Color(255, 255, 255));
        actInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actInfo.setOpaque(true);

        availabilityLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        availabilityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availabilityLabel.setText("AVAILABILITY");

        jTableMaintainerAvailability.setModel(dtm);
        jScrollPane2.setViewportView(jTableMaintainerAvailability);

        dayOfWeek.setBackground(new java.awt.Color(255, 255, 255));
        dayOfWeek.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dayOfWeek.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayOfWeek.setText("Day");
        dayOfWeek.setOpaque(true);

        numDay.setBackground(new java.awt.Color(102, 102, 102));
        numDay.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        numDay.setForeground(new java.awt.Color(255, 255, 255));
        numDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numDay.setText("nDay");
        numDay.setOpaque(true);

        percentuale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percentuale.setText("Percentuale");

        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(availabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(percentuale, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dayOfWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weekNum, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numDay, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(weekNum)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numDay)
                            .addComponent(dayOfWeek)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(availabilityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(percentuale, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        
        ((Maintainer_JDBC)jdbc_m).updateAvailabiltyOnDatabase(maintainer.getAvailability());
        P4_1_JFrame p41=new P4_1_JFrame(planner, activity);
        p41.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        P4_1_JFrame p41=new P4_1_JFrame(planner, activity);
        p41.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(P4_2_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P4_2_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P4_2_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P4_2_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
            
        
        ActivityBuilder builder = new PlannedBuilder();
        Activity act = builder.setSite("Solofra", "Ditta2")
                            .setTypology(PlannedBuilder.Typology.Hydraulic)
                            .setDescription("Manutenzione generale")
                            .setWeek(1)
                            .setEstimatedTime(60)
                            .getActivity(); 
        Competences com = new Competences();
        com.insertCompetence(new Competence("Competenza 1"));
        com.insertCompetence(new Competence("Competenza 2"));
        com.insertCompetence(new Competence("Competenza 3"));
        com.insertCompetence(new Competence("Competenza 4"));
        act.setCompetences(com);
        
        Maintainer maintainer=new Maintainer("Provola Affumicata","sullaPasta");
        User planner = new PlannerFactory().createUser("Pablo", "Spanish");
        System.out.println();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P4_2_JFrame(act, maintainer, planner, "MON").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actInfo;
    private javax.swing.JLabel availabilityLabel;
    private javax.swing.JLabel dayOfWeek;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableMaintainerAvailability;
    private javax.swing.JLabel numDay;
    private javax.swing.JLabel percentuale;
    private javax.swing.JLabel weekNum;
    // End of variables declaration//GEN-END:variables
}
