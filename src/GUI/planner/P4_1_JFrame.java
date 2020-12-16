/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.planner;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import users.User;
import users.JDBC;
import users.maintainer.Maintainer;
import users.planner.PlannerFactory;
import Activity.Activity;
import Activity.ActivityBuilder;
import Activity.Planned.PlannedBuilder;
import Activity.competence.Competence;
import Activity.competence.Competences;
import javax.swing.JFrame;


/**
 *
 * @author Grazia D'Amore
 */
public class P4_1_JFrame extends javax.swing.JFrame {
     private DefaultListModel<String> listModel;
     private DefaultTableModel tableModel; 
     private User planner;
     private JDBC jdbc;
     private Activity activity;
     private Map<String,User> maintainers;
     private JFrame actual;
     
    /**
     * Creates new form P4_1_JFrame
     */
    public P4_1_JFrame(User planner, Activity act) {
        this.planner = planner;
        this.jdbc = new PlannerFactory().createJDBCUser(planner.getUsername(), planner.getPassword());
        this.listModel = new DefaultListModel<>();
        this.activity = act;
        this.tableModel = new DefaultTableModel(new String[] {"Maintainer", "Skills", "Avail MON", "Avail TUE", "Avail WED", "Avail THU", "Avail FRI", "Avail SAT", "Avail SUN" },0);
        this.actual=this;
        
        initComponents();
        
        this.jLabelWeekNEdit.setText("" + activity.getWeek());
        this.jLabelActivity.setText("" + activity.getActivityID() + " - "+ activity.getSite());
        
        this.jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    int row = jTable1.getSelectedRow();
                    int col = jTable1.getSelectedColumn();
                    if (col > 1 ){
                        String[] day = jTable1.getColumnName(col).split(" ");
                        User maint = maintainers.get(String.valueOf(jTable1.getModel().getValueAt(row, 0)));
                        P4_2_JFrame p4 = new P4_2_JFrame(activity, maint,planner,day[1]);
                        p4.setVisible(true);
                        actual.dispose();
                    }         
            }
        });

        Competences comps = activity.getCompetences();
        for (Competence c : comps){
            listModel.addElement(c.toString());
        }

        this.maintainers = jdbc.loadMaintainersFromDatabase();
        planner.setMaintainers(maintainers);
        for (User main : maintainers.values()) {
            Maintainer m = (Maintainer) main;
            tableModel.addRow(new Object[]{ m.getUsername(), m.isQualified(act.getCompetences())+"/"+act.getCompetences().size(), m.getDayAvailability(act.getWeek(), "MON")+"%", m.getDayAvailability(act.getWeek(), "TUE")+"%", m.getDayAvailability(act.getWeek(), "WED")+"%", m.getDayAvailability(act.getWeek(), "THU")+"%",m.getDayAvailability(act.getWeek(), "FRI")+"%",m.getDayAvailability(act.getWeek(), "SAT")+"%",m.getDayAvailability(act.getWeek(), "SUN")+"%"}); 
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

        jLabelWeekN = new javax.swing.JLabel();
        jLabelWeekNEdit = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelActivity = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable()
        {
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
                    if(Integer.parseInt(value) >= 10 && Integer.parseInt(value) < 40 ){
                        // if date  equal current date
                        componenet.setBackground(Color.ORANGE);
                        componenet.setForeground(Color.BLACK);
                    }

                    if(Integer.parseInt(value) >= 40 && Integer.parseInt(value) < 60){
                        // if date  equal current date
                        componenet.setBackground(Color.YELLOW);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(Integer.parseInt(value) >= 60 && Integer.parseInt(value) < 80){
                        // if date  equal current date
                        componenet.setBackground(Color.GREEN);
                        componenet.setForeground(Color.BLACK);
                    }
                    if(Integer.parseInt(value) >= 80 && Integer.parseInt(value) <= 100){
                        // if date  equal current date
                        componenet.setBackground(Color.BLUE);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelWeekN.setBackground(new java.awt.Color(255, 255, 255));
        jLabelWeekN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWeekN.setText("Week n°");
        jLabelWeekN.setOpaque(true);

        jLabelWeekNEdit.setBackground(new java.awt.Color(102, 102, 102));
        jLabelWeekNEdit.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWeekNEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWeekNEdit.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Activity To Assign");
        jLabel1.setOpaque(true);

        jLabelActivity.setBackground(new java.awt.Color(102, 102, 102));
        jLabelActivity.setForeground(new java.awt.Color(255, 255, 255));
        jLabelActivity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelActivity.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("  Skills needed");

        jList1.setModel(listModel);
        jScrollPane1.setViewportView(jList1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Maintainers Availability");

        jTable1.setModel(tableModel);
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelWeekN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabelWeekNEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelActivity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabelWeekN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelWeekNEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(P4_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P4_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P4_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P4_1_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
        User p = new PlannerFactory().createUser("","");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P4_1_JFrame(p,act).setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelActivity;
    private javax.swing.JLabel jLabelWeekN;
    private javax.swing.JLabel jLabelWeekNEdit;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

public void errorMessage(String str){
    JOptionPane.showMessageDialog(this, str, "Ops...", JOptionPane.ERROR_MESSAGE);  
}

public void infoMessage(String str){
    JOptionPane.showMessageDialog(this, str, "Info", JOptionPane.INFORMATION_MESSAGE);
}

public int questionMessage(String str){
    
   return JOptionPane.showConfirmDialog(this,str,"", JOptionPane.OK_CANCEL_OPTION);
}

}