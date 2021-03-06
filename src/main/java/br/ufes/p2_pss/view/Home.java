/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.view;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Lucas
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
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

        Desktop = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        lblState = new javax.swing.JLabel();
        btnNotification = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        mbMenu = new javax.swing.JMenuBar();
        menuImagens = new javax.swing.JMenu();
        menuViewImages = new javax.swing.JMenuItem();
        itemShare = new javax.swing.JMenuItem();
        menuAdmin = new javax.swing.JMenu();
        itemVisualizarUsuarios = new javax.swing.JMenuItem();
        itemAutorizarAcesso = new javax.swing.JMenuItem();
        menuCadastro = new javax.swing.JMenu();
        itemSair = new javax.swing.JMenuItem();
        itemEntrar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblUsername.setText("aaaaaaaaaaaaaaaaaaaa");

        lblState.setText("aaaaaaaaaaaaaaaaaaaa");

        btnNotification.setText("NN");
        btnNotification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificationActionPerformed(evt);
            }
        });

        jLabel1.setText("Prova 2 - Projeto de Sistemas de Software");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(lblUsername)
                .addGap(18, 18, 18)
                .addComponent(lblState)
                .addGap(18, 18, 18)
                .addComponent(btnNotification)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername)
                    .addComponent(lblState)
                    .addComponent(btnNotification)
                    .addComponent(jLabel1))
                .addGap(6, 6, 6))
        );

        Desktop.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesktopLayout.createSequentialGroup()
                .addGap(0, 429, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuImagens.setText("Imagens");

        menuViewImages.setText("Visualizar Imagens");
        menuImagens.add(menuViewImages);

        itemShare.setText("Compartilhar Imagem");
        menuImagens.add(itemShare);

        mbMenu.add(menuImagens);

        menuAdmin.setText("Administrador");

        itemVisualizarUsuarios.setText("Visualizar Usuários");
        itemVisualizarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizarUsuariosActionPerformed(evt);
            }
        });
        menuAdmin.add(itemVisualizarUsuarios);

        itemAutorizarAcesso.setText("Autorizar acesso");
        menuAdmin.add(itemAutorizarAcesso);

        mbMenu.add(menuAdmin);

        menuCadastro.setText("Login");

        itemSair.setText("Sair");
        menuCadastro.add(itemSair);

        itemEntrar.setText("Entrar");
        itemEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEntrarActionPerformed(evt);
            }
        });
        menuCadastro.add(itemEntrar);

        mbMenu.add(menuCadastro);

        setJMenuBar(mbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificationActionPerformed

    }//GEN-LAST:event_btnNotificationActionPerformed

    private void itemEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEntrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemEntrarActionPerformed

    private void itemVisualizarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizarUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemVisualizarUsuariosActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane Desktop;
    private javax.swing.JButton btnNotification;
    private javax.swing.JMenuItem itemAutorizarAcesso;
    private javax.swing.JMenuItem itemEntrar;
    private javax.swing.JMenuItem itemSair;
    private javax.swing.JMenuItem itemShare;
    private javax.swing.JMenuItem itemVisualizarUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblState;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenuBar mbMenu;
    private javax.swing.JMenu menuAdmin;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuImagens;
    private javax.swing.JMenuItem menuViewImages;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnNotification() {
        return btnNotification;
    }

    public JLabel getLblState() {
        return lblState;
    }

    public JLabel getLblUsername() {
        return lblUsername;
    }

    public JMenuBar getMbMenu() {
        return mbMenu;
    }

    public JMenu getMenuAdmin() {
        return menuAdmin;
    }

    public JMenu getMenuImagens() {
        return menuImagens;
    }

    public JMenuItem getMenuViewImages() {
        return menuViewImages;
    }

    public JMenuItem getItemEntrar() {
        return itemEntrar;
    }

    public JMenuItem getItemSair() {
        return itemSair;
    }

    public JMenu getMenuCadastro() {
        return menuCadastro;
    }

    public JDesktopPane getDesktop() {
        return Desktop;
    }

    public JMenuItem getItemAutorizarAcesso() {
        return itemAutorizarAcesso;
    }

    public JMenuItem getItemVisualizarUsuarios() {
        return itemVisualizarUsuarios;
    }

    public JMenuItem getItemShare() {
        return itemShare;
    }
}
