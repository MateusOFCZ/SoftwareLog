/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.telas;

import br.com.tcc.dal.ModuloConexao;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mateus da Cruz
 */
public class Principal extends javax.swing.JFrame {
        Connection conexao = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

    /**
     * Creates new form Principal
     */
    
        private String versaorecente;
        private String versaoatual;
        private String obrigatorio;
        
    public void Verificar1(){
        String sql = "SELECT versaorecente, obrigatorio FROM configuracoes WHERE id = 1";
        
        try {
            pst = conexao.prepareStatement(sql);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                this.setVersaorecente(rs.getString("versaorecente"));
                this.setObrigatorio(rs.getString("obrigatorio"));
                Verificar2();
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao verificar a versão do programa!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Verificar2(){
        String sql = "SELECT versaoatual FROM usuarios WHERE id = ?";
        
        try {
            Login login = new Login();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, login.getId());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                this.setVersaoatual(rs.getString("versaoatual"));
                Verificar3();
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao verificar a versão do programa!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Verificar3(){
        if(this.getVersaorecente().equals(this.getVersaoatual())){
            
        }else{
            if("Não".equals(this.getObrigatorio())){
            int OPC = JOptionPane.showConfirmDialog(null, "Uma nova versão do programa está disponível, deseja baixar agora?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
            
            if(OPC == JOptionPane.YES_OPTION){
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URI("http://softwarelog.esy.es"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERRO: ");
            }
            }
//JOptionPane.showMessageDialog(null, "Uma nova versão do programa está disponível! Baixe em nosso site softwarelog.cf!");
        }else {
            int OPC = JOptionPane.showConfirmDialog(null, "Uma nova versão do programa está disponível, o download é obrigatório!", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
            
            if(OPC == JOptionPane.YES_OPTION){
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URI("http://softwarelog.esy.es"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "ERRO: ");
            }
            }
            if(OPC == JOptionPane.NO_OPTION){
                this.dispose();
            }
//JOptionPane.showMessageDialog(null, "Uma nova versão do programa está disponível! Baixe em nosso site softwarelog.cf!");
        }
            
    }
    }
    
    public void ReportarErro(){
        String ReportarErro = JOptionPane.showInputDialog("Descreva o erro com detalhes:");
        String sql = "INSERT INTO reports (iduser, erro, solucionado) VALUES(?, ?, ?)";
        
        if(" ".equals(ReportarErro) || "Descreva o erro com detalhes:".equals(ReportarErro) || ReportarErro == null){
            JOptionPane.showMessageDialog(null, "Descreva o erro para poder enviar!");
        }else{
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, ReportarErro);
            pst.setString(3, "Não");
            
            int Adicionado = pst.executeUpdate();
            
            if(Adicionado > 0){
                JOptionPane.showMessageDialog(null, "Reportado com sucesso!");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao reportar, tente novamente!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: ");
        }
        }
    }
    
    public Principal() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel = new javax.swing.JDesktopPane();
        Fundo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Menu_Gado = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        Menu_Configurações = new javax.swing.JMenuItem();
        Menu_Sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Software Log");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        Painel.setBackground(new java.awt.Color(204, 204, 204));

        Fundo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/telacinza800x600.png"))); // NOI18N

        Painel.setLayer(Fundo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PainelLayout = new javax.swing.GroupLayout(Painel);
        Painel.setLayout(PainelLayout);
        PainelLayout.setHorizontalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addComponent(Fundo, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        PainelLayout.setVerticalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Fundo, javax.swing.GroupLayout.PREFERRED_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(Painel);
        Painel.setBounds(0, -30, 800, 600);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(70, 25));
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/menu (1).png"))); // NOI18N
        jMenu2.setText("Menu");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        Menu_Gado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Menu_Gado.setText("Gado");
        Menu_Gado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_GadoActionPerformed(evt);
            }
        });
        jMenu2.add(Menu_Gado);

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem1.setText("Reportar Erro");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        Menu_Configurações.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Menu_Configurações.setText("Configurações");
        Menu_Configurações.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Menu_ConfiguraçõesMouseClicked(evt);
            }
        });
        Menu_Configurações.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_ConfiguraçõesActionPerformed(evt);
            }
        });
        jMenu2.add(Menu_Configurações);

        Menu_Sair.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Menu_Sair.setText("Sair");
        Menu_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_SairActionPerformed(evt);
            }
        });
        jMenu2.add(Menu_Sair);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Menu_GadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_GadoActionPerformed
        // TODO add your handling code here:
        Gado Gado = new Gado();
        Gado.setVisible(true);
        Painel.add(Gado);
    }//GEN-LAST:event_Menu_GadoActionPerformed

    private void Menu_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_SairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_Menu_SairActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        URL caminhoImagem = this.getClass().getResource("logoboi.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoImagem);
        setIconImage(iconeTitulo);
        Gado Gado = new Gado();
        Painel.add(Gado);
        Gado.setVisible(true);
        Verificar1();
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        ReportarErro();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void Menu_ConfiguraçõesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Menu_ConfiguraçõesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Menu_ConfiguraçõesMouseClicked

    private void Menu_ConfiguraçõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_ConfiguraçõesActionPerformed
        // TODO add your handling code here:
        Configuracoes Configuracoes = new Configuracoes();
        Painel.add(Configuracoes);
        Configuracoes.setVisible(true);
    }//GEN-LAST:event_Menu_ConfiguraçõesActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    private javax.swing.JMenuItem Menu_Configurações;
    private javax.swing.JMenuItem Menu_Gado;
    private javax.swing.JMenuItem Menu_Sair;
    public static javax.swing.JDesktopPane Painel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

    void setString(int i, JTextField iduser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the versaorecente
     */
    public String getVersaorecente() {
        return versaorecente;
    }

    /**
     * @param versaorecente the versaorecente to set
     */
    public void setVersaorecente(String versaorecente) {
        this.versaorecente = versaorecente;
    }

    /**
     * @return the versaoatual
     */
    public String getVersaoatual() {
        return versaoatual;
    }

    /**
     * @param versaoatual the versaoatual to set
     */
    public void setVersaoatual(String versaoatual) {
        this.versaoatual = versaoatual;
    }

    /**
     * @return the obrigatorio
     */
    public String getObrigatorio() {
        return obrigatorio;
    }

    /**
     * @param obrigatorio the obrigatorio to set
     */
    public void setObrigatorio(String obrigatorio) {
        this.obrigatorio = obrigatorio;
    }
}
