/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeinput;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2-this.getWidth()/2, screenSize.height/2-this.getHeight()/2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lWelcomeText = new javax.swing.JLabel();
        fPassword = new javax.swing.JPasswordField();
        bOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lWelcomeText.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lWelcomeText.setText("Введите ваш пароль:");

        fPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fPasswordFocusGained(evt);
            }
        });
        fPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fPasswordKeyReleased(evt);
            }
        });

        bOk.setText("OK");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lWelcomeText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(bOk)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lWelcomeText)
                .addGap(33, 33, 33)
                .addComponent(fPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bOk)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    Passwd passwd;
    private void fPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fPasswordKeyPressed
        if ("".equals(fPassword.getText())) passwd = new Passwd();
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            bOkActionPerformed(null);
        } else passwd.keyDown(evt.getKeyChar());
    }//GEN-LAST:event_fPasswordKeyPressed

    private void fPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fPasswordFocusGained
        fPassword.setText("");
    }//GEN-LAST:event_fPasswordFocusGained

    private void fPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fPasswordKeyReleased
        if (evt.getKeyCode()!=KeyEvent.VK_ENTER) passwd.keyUp();
    }//GEN-LAST:event_fPasswordKeyReleased

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
        try (FileController fw = new FileController(config.getFilePath(),config.isAppendMode())) {
            fw.writeRecord(passwd);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Невозможно записать данные в файл","Ошибка",JOptionPane.WARNING_MESSAGE);
        }
        if (config.isSaveOnly()){
            JOptionPane.showMessageDialog(null, "Данные записаны. Нажмите ОК, чтобы продолжить.","Информация",JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                int code = Runtime.getRuntime().exec(config.getProgramToRun()+" "+config.getFilePath()).waitFor();
                if (code==0)
                    JOptionPane.showMessageDialog(null, "Авторизация пройдена","Информация",JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(null, "Авторизация не пройдена. Код выхода: "+code,"Информация",JOptionPane.ERROR_MESSAGE);
            } catch (IOException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Невозможно запустить проверяющее приложение","Ошибка",JOptionPane.WARNING_MESSAGE);
            }
            
        }
        
    }//GEN-LAST:event_bOkActionPerformed

    private final Config config = new Config("config.properities");
    
    public static void main(String args[]) {        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow main = new MainWindow();
                main.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bOk;
    private javax.swing.JPasswordField fPassword;
    private javax.swing.JLabel lWelcomeText;
    // End of variables declaration//GEN-END:variables
}
