/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.telas;
import br.com.tcc.dal.ModuloConexao;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;

import br.com.tcc.dal.ModuloConexao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 *
 * @author Mateus da Cruz
 */
public class Registro extends javax.swing.JFrame {
    Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        /*
        URL caminhoImagem = this.getClass().getResource("favicon.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        */
        conexao = ModuloConexao.conector();
        System.out.println(conexao);
        if(conexao != null){
            status_atual.setText("Online");
            //semconexao.setVisible(false);
            //status_atual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcc/icones/dbok.png")));
        }else{
            status_atual.setText("Offline");
            //semconexao.setVisible(true);
            //status_atual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tcc/icones/dberro.png")));
        }
    }
    
    public void Entrar(){
                Login login = new Login();
                login.setVisible((true));
                this.dispose();
    }
    
    public void limparcampos(){
        txt_propriedade.setText(null);
        txt_telefone.setText(null);
        txt_email.setText(null);
        txt_senha.setText(null);
    }
    
    private void Registrar(){
        String sql = "insert into usuarios(propriedade, telefone, email, senha, versaoatual) values(?, ?, ?, ?, ?)";
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_propriedade.getText());
            pst.setString(2, txt_telefone.getText());
            pst.setString(3, txt_email.getText());
            pst.setString(4, txt_senha.getText());
            pst.setString(5, "0.0.1");
            
            if((txt_propriedade.getText().isEmpty()) || (txt_telefone.getText().isEmpty()) || (txt_email.getText().isEmpty()) ||  (txt_senha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else{
            limparcampos();
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Registrado com sucesso, faça o login para ativar a conta!");
                Entrar();
            }else{
                JOptionPane.showMessageDialog(null, "Houve um problema ao efetuar o registro! Tente novamente mais tarde!");
                limparcampos();
            }
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
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

        btn_login = new javax.swing.JButton();
        btn_registrar = new javax.swing.JButton();
        status_atual = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_propriedade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        propriedade = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        senha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_senha = new javax.swing.JPasswordField();
        txt_telefone = new javax.swing.JTextField();
        Telefone = new javax.swing.JLabel();
        EMail = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        status_servidor = new javax.swing.JLabel();
        Título1 = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();
        ChaveDeAtivacao = new javax.swing.JLabel();
        txt_chavedeativacao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Software Log - Registro");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        btn_login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_login.setText("Fechar");
        btn_login.setToolTipText("Fechar");
        btn_login.setBorderPainted(false);
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login);
        btn_login.setBounds(690, 530, 80, 20);

        btn_registrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_registrar.setText("Registrar");
        btn_registrar.setToolTipText("Registrar");
        btn_registrar.setBorderPainted(false);
        btn_registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_registrar);
        btn_registrar.setBounds(330, 390, 120, 30);

        status_atual.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        status_atual.setText("Offline");
        getContentPane().add(status_atual);
        status_atual.setBounds(740, 10, 40, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/employee (4).png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(310, 230, 50, 40);

        txt_propriedade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_propriedadeActionPerformed(evt);
            }
        });
        getContentPane().add(txt_propriedade);
        txt_propriedade.setBounds(110, 230, 240, 40);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/key.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(310, 310, 40, 40);

        propriedade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        propriedade.setText("Propriedade:");
        getContentPane().add(propriedade);
        propriedade.setBounds(110, 200, 160, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/phone-call.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(640, 230, 50, 40);

        senha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        senha.setText("Senha:");
        getContentPane().add(senha);
        senha.setBounds(110, 280, 100, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/email.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(640, 310, 50, 40);
        getContentPane().add(txt_senha);
        txt_senha.setBounds(110, 310, 240, 40);

        txt_telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefoneActionPerformed(evt);
            }
        });
        getContentPane().add(txt_telefone);
        txt_telefone.setBounds(440, 230, 240, 40);

        Telefone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Telefone.setText("Telefone:");
        getContentPane().add(Telefone);
        Telefone.setBounds(440, 200, 120, 30);

        EMail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        EMail.setText("E-Mail:");
        getContentPane().add(EMail);
        EMail.setBounds(440, 280, 100, 30);

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email);
        txt_email.setBounds(440, 310, 240, 40);

        status_servidor.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        status_servidor.setText("Status do Servidor:");
        getContentPane().add(status_servidor);
        status_servidor.setBounds(640, 10, 100, 20);

        Título1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Título1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Título1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/logoboi.png"))); // NOI18N
        Título1.setText("Registro");
        getContentPane().add(Título1);
        Título1.setBounds(110, 80, 530, 90);

        fundo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/telacinza800x600.png"))); // NOI18N
        fundo.setToolTipText("");
        getContentPane().add(fundo);
        fundo.setBounds(-10, -10, 800, 580);

        ChaveDeAtivacao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ChaveDeAtivacao.setForeground(new java.awt.Color(255, 255, 255));
        ChaveDeAtivacao.setText("Chave de Ativação:");
        getContentPane().add(ChaveDeAtivacao);
        ChaveDeAtivacao.setBounds(10, 210, 210, 30);

        txt_chavedeativacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chavedeativacaoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_chavedeativacao);
        txt_chavedeativacao.setBounds(10, 240, 210, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/email.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(410, 310, 40, 40);

        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        Entrar();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txt_propriedadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_propriedadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_propriedadeActionPerformed

    private void txt_telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefoneActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        // TODO add your handling code here:
        Registrar();
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void txt_chavedeativacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chavedeativacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chavedeativacaoActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        URL caminhoImagem = this.getClass().getResource("logoboi.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChaveDeAtivacao;
    private javax.swing.JLabel EMail;
    private javax.swing.JLabel Telefone;
    private javax.swing.JLabel Título1;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel propriedade;
    private javax.swing.JLabel senha;
    private javax.swing.JLabel status_atual;
    private javax.swing.JLabel status_servidor;
    private javax.swing.JTextField txt_chavedeativacao;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_propriedade;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JTextField txt_telefone;
    // End of variables declaration//GEN-END:variables
}
