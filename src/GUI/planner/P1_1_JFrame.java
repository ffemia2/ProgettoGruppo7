/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.planner;


import users.User;
import users.JDBC;
import users.planner.PlannerFactory;

import Activity.Activity;
import Activity.Activities;
import Activity.ActivityBuilder;

import Activity.Unplanned.UnplannedBuilder;
import Activity.Unplanned.ExtraBuilder;

import Activity.Planned.PlannedBuilder;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/**
 *
 * @author Grazia D'Amore
 */
public class P1_1_JFrame extends javax.swing.JFrame {
    private User planner;
    private JDBC jdbc;
    private Activities added;
    /**
     * Creates new form P1_JFrame
     * @param username
     * @param password
     */
    public P1_1_JFrame(String username, String password) {
        this.added = new Activities();
        this.planner = new PlannerFactory().createUser(username,password);
        this.jdbc = new PlannerFactory().createJDBCUser(username, password);
        
        planner.setActivities(jdbc.loadActivitiesFromDatabase());
        
        Activity.setInitialCount(jdbc.loadCountFromDatabase());
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonNewAct = new javax.swing.JButton();
        jButtonAssignAct = new javax.swing.JButton();
        jButtonViewAct = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HI");

        jLabel2.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("WHAT DO YOU NEED?");

        jLabel3.setFont(new java.awt.Font("Bebas Neue", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jButtonNewAct.setText("New Activity");
        jButtonNewAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActActionPerformed(evt);
            }
        });

        jButtonAssignAct.setText("Assign Activities");
        jButtonAssignAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAssignActActionPerformed(evt);
            }
        });

        jButtonViewAct.setText("View Activities");
        jButtonViewAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewActActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonNewAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAssignAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonViewAct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButtonNewAct)
                .addGap(37, 37, 37)
                .addComponent(jButtonAssignAct)
                .addGap(39, 39, 39)
                .addComponent(jButtonViewAct)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActActionPerformed
        
        JTextField site1 = new JTextField();
        JTextField site2 = new JTextField();
        
        String[] types = {"Planned","Unplanned","Extra"};
        
        JComboBox<String> type = new JComboBox<>(types);
        String[] typologies = new String[ActivityBuilder.Typology.values().length]; 
        for(int i = 0; i < ActivityBuilder.Typology.values().length; i++ )
            typologies[i] = String.valueOf(ActivityBuilder.Typology.values()[i]);
        JComboBox<String> typology = new JComboBox<>(typologies);
        
        JTextField description = new JTextField();
        JTextField time = new JTextField();
        JCheckBox interr = new JCheckBox();
        JTextField week = new JTextField();
        
        Object[] fields = {
            "Factory Site", site1,
            "Department", site2,
            "", type,
            "Typology", typology,
            "Description", description,
            "Estimated Time (minutes)", time,
            "Interruptible", interr,
            "Week n°", week
        };
        int answer = JOptionPane.showConfirmDialog(this, fields, "Insert new Activity", JOptionPane.OK_CANCEL_OPTION);
       
        if (answer == JOptionPane.OK_OPTION){
            ActivityBuilder builder = this.selectActivity(String.valueOf(type.getSelectedItem()));           
            Activity activity = builder.setSite(site1.getText(), site2.getText())
                                       .setTypology(ActivityBuilder.Typology.valueOf(String.valueOf(typology.getSelectedItem())))
                                       .setEstimatedTime(Integer.valueOf(time.getText()))
                                       .setDescription(description.getText())
                                       .setInterruptible(interr.isSelected())
                                       .setWeek(Integer.valueOf(week.getText()))
                                       .getActivity();
                                       
            planner.getActivities().insertInActivities(activity);
            added.insertInActivities(activity);
        }
    }//GEN-LAST:event_jButtonNewActActionPerformed

    private void jButtonAssignActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAssignActActionPerformed
        onClose();
        ActivitySelection frame = new ActivitySelection(planner, planner.getActivities());
        frame.setVisible(true);
        
    }//GEN-LAST:event_jButtonAssignActActionPerformed

    private void jButtonViewActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewActActionPerformed
        P1_2_JFrame frame = new P1_2_JFrame(planner);
        frame.setVisible(true);
    }//GEN-LAST:event_jButtonViewActActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onClose();
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
            java.util.logging.Logger.getLogger(P1_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P1_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P1_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P1_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
              
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new P1_1_JFrame("Qualcuno","").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAssignAct;
    private javax.swing.JButton jButtonNewAct;
    private javax.swing.JButton jButtonViewAct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
    
private ActivityBuilder selectActivity(String type){
    if(type.equals("Planned"))
        return new PlannedBuilder();
    if(type.equals("Unplanned"))
        return new UnplannedBuilder();
    if(type.equals("Planned"))
        return new ExtraBuilder();
    
    return null;
}
   

private void onClose(){
    jdbc.saveActivitiesOnDatabase(added);
}
}