/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package libManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author secre
 */
public class Book extends javax.swing.JFrame {

    /** Creates new form Author */
    public Book() {
        initComponents();
        connect();
        book_load();
        load_category_item();
        load_author_item();
        load_publisher_item();
    }
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public void connect() {
        try {
            Class.forName("com.sql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","");
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // category item class, to get all category values then put into catComboBox variable
    // for this, we only need to get id and category name, status is optional
    public class CategoryItem {
        int id;
        String name;
        
        public CategoryItem(int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    public void load_category_item() {
        try {
            pst = conn.prepareStatement("select * from category");
            rs = pst.executeQuery();
            while (rs.next()) {
                   catComboBox.addItem(new CategoryItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // like Category item, we also need to load author item, then we do:
    public class AuthorItem {
        int id;
        String name;
        
        public AuthorItem(int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    public void load_author_item() {
        try {
            pst = conn.prepareStatement("select * from author");
            rs = pst.executeQuery();
            while (rs.next()) {
                   authComboBox.addItem(new AuthorItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // publisher, here we go
    public class Publisheritem {
        int id;
        String name;
        
        public Publisheritem(int id, String name) {
            this.id = id;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }
    
    public void load_publisher_item() {
        try {
            pst = conn.prepareStatement("select * from publisher");
            rs = pst.executeQuery();
            while (rs.next()) {
                   pubComboBox.addItem(new AuthorItem(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // load book to table as always
    
    public void book_load() {
        int c;
        try {
            pst = conn.prepareStatement("Select * from book");
            rs = pst.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            c = rsmd.getColumnCount();
            
            DefaultTableModel d = (DefaultTableModel)authorTable.getModel();
            d.setRowCount(0);
            
            while (rs.next()) {
                Vector vt = new Vector();
                
                for (int i = 0; i < c; i++) {
                    vt.add(rs.getString("id"));
                    vt.add(rs.getString("book_name"));
                    vt.add(rs.getString("num_of_pages"));
                    vt.add(rs.getString("category"));
                    vt.add(rs.getString("author"));
                    vt.add(rs.getString("publisher"));
                }
                
                d.addRow(vt);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        booknameTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        authorTable = new javax.swing.JTable();
        pageTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        catComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        authComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        pubComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("Book");

        jLabel2.setText("Book name");

        jLabel3.setText("Num of pages");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        authorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Book name", "Num of pages", "Category", "Author", "Publisher"
            }
        ));
        authorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(authorTable);

        pageTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageTxtActionPerformed(evt);
            }
        });

        jLabel4.setText("Category");

        catComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Author");

        jLabel6.setText("Publisher");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                            .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(pubComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(authComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(catComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(booknameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                            .addComponent(pageTxt))))
                                .addGap(34, 34, 34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(booknameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(catComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(authComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(pubComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        String bookname = booknameTxt.getText();
        int bookpages = Integer.parseInt(pageTxt.getText());
        String category = catComboBox.getSelectedItem().toString();
        String auth = authComboBox.getSelectedItem().toString();
        String publisher = pubComboBox.getSelectedItem().toString();
        
        try {
            pst = conn.prepareStatement("Insert into book(book_name,num_of_pages,"
                    + "category ,author, publisher) values(?,?,?,?,?)");
            pst.setString(1, bookname);
            pst.setInt(2, bookpages);
            pst.setString(3, category);
            pst.setString(4, auth);
            pst.setString(5, publisher);
            
            int k = pst.executeUpdate();
            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Book created successfully!");
                booknameTxt.setText("");
                pageTxt.setText("");
            }
            else {
                JOptionPane.showMessageDialog(this, "Book created failed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        book_load();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.hide();
        main m = new main();
        m.setVisible(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    // click to table item will set move this item to text fields for updating or deleting
    private void authorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_authorTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)authorTable.getModel();
        int selectedIndex = authorTable.getSelectedRow();
        
        int id = Integer.parseInt(d1.getValueAt(selectedIndex, 0).toString());
        booknameTxt.setText(d1.getValueAt(selectedIndex, 1).toString());
        pageTxt.setText(d1.getValueAt(selectedIndex,2).toString());
        catComboBox.setSelectedItem(d1.getValueAt(selectedIndex, 3).toString());
        authComboBox.setSelectedItem(d1.getValueAt(selectedIndex, 4).toString());
        pubComboBox.setSelectedItem(d1.getValueAt(selectedIndex, 5).toString());
        saveButton.setEnabled(false);
    }//GEN-LAST:event_authorTableMouseClicked

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        // get value from textfield
        String bookname = booknameTxt.getText();
        int bookpages = Integer.parseInt(pageTxt.getText());
        String category = catComboBox.getSelectedItem().toString();
        String auth = authComboBox.getSelectedItem().toString();
        String publisher = pubComboBox.getSelectedItem().toString();
        // get table model
        DefaultTableModel d1 = (DefaultTableModel)authorTable.getModel();
        int selectedIndex = authorTable.getSelectedRow();
        int id = Integer.parseInt(d1.getValueAt(selectedIndex, 0).toString());
        try {
            pst = conn.prepareStatement("Update book set book_name = ?, num_of_pages = ?"
                    + " category = ? author = ? publisher = ? where id = ?");
            pst.setString(1, bookname);
            pst.setInt(2, bookpages);
            pst.setString(3, category);
            pst.setString(4, auth);
            pst.setString(5, publisher);
            pst.setInt(6, id);
            
            int k = pst.executeUpdate();
            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Book updated successfully!");
                booknameTxt.setText("");
                pageTxt.setText("");
                saveButton.setEnabled(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "Book updated failed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        book_load();
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel d1 = (DefaultTableModel)authorTable.getModel();
        int selectedIndex = authorTable.getSelectedRow();
        int id = Integer.parseInt(d1.getValueAt(selectedIndex, 0).toString());
        try {
            pst = conn.prepareStatement("Delete from book where id = ?");
            pst.setInt(1, id);
            
            int k = pst.executeUpdate();
            if (k == 1) {
                JOptionPane.showMessageDialog(this, "Book updated!");
                booknameTxt.setText("");
                pageTxt.setText("");
            }
            else {
                JOptionPane.showMessageDialog(this, "Deleted from Book failed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        saveButton.setEnabled(true);
        book_load();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void pageTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageTxtActionPerformed

    private void catComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Book().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox authComboBox;
    private javax.swing.JTable authorTable;
    private javax.swing.JTextField booknameTxt;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox catComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pageTxt;
    private javax.swing.JComboBox pubComboBox;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

}
