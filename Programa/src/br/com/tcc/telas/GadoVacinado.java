/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.telas;

import br.com.tcc.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Mateus da Cruz
 */
public class GadoVacinado extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form Gado
     */
    
    public void Fechar(){
                this.dispose();
    }
    private String GadoID;
    
    public void Procurar1(){
        String BrincoRastreado = JOptionPane.showInputDialog("Digite o número do Brinco Rastreado do gado que você deseja procurar:");
        String sql = "SELECT id FROM gado WHERE iduser = ? AND brincorastreado = ?";
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, BrincoRastreado);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                this.setGadoID(rs.getString("id"));
                Procurar2();
                GadoSelecionado();
            }else{
                JOptionPane.showMessageDialog(null, "Houve um erro ao procurar pelo gado, tente novamente!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    
    public void Procurar2(){
        String sql = "SELECT nomevacina, datavacinado, vacinado FROM vacinas WHERE iduser = ? AND idgado = ?";
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, this.getGadoID());
            
            rs = pst.executeQuery();
            Tabela_Gado.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    
    public void GadoSelecionado(){
        String sql = "SELECT * FROM gado WHERE iduser = ? AND id = ?";
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, this.getGadoID());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
            TXT_NomeSetar.setText(rs.getString("nome"));
            TXT_BrincoSetar.setText(rs.getString("brincorastreado"));
            }else{
                JOptionPane.showMessageDialog(null, "Houve um erro ao procurar pelo gado, tente novamente!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    String IDVacina;
    public void Apagar1(){
        String sql = "SELECT id FROM vacinas WHERE iduser = ? AND nomevacina = ? AND datavacinado = ? AND vacinado = ?";
        
        int Setar = Tabela_Gado.getSelectedRow();
        String NomeVacina = Tabela_Gado.getModel().getValueAt(Setar, 0).toString();
        String DataVacina = Tabela_Gado.getModel().getValueAt(Setar, 1).toString();
        String EstadoVacina = Tabela_Gado.getModel().getValueAt(Setar, 2).toString();
        
        try {
            Login login = new Login();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, login.getId());
            pst.setString(2, NomeVacina);
            pst.setString(3, DataVacina);
            pst.setString(4, EstadoVacina);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                this.setIDVacina(rs.getString("id"));
                Apagar2();
            }else{
                JOptionPane.showMessageDialog(null, "Houve um erro ao efetuar a operação, tente novamente!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    
    public void Apagar2(){
        String sql = "DELETE FROM vacinas WHERE iduser = ? AND id = ?";
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, Login.getId());
            pst.setString(2, this.getIDVacina());
            
            int Apagado = pst.executeUpdate();
            
            if(Apagado > 0){
                JOptionPane.showMessageDialog(null, "Vacina deletada com sucesso!");
                Procurar2();
            }else{
                JOptionPane.showMessageDialog(null, "Houve um erro ao efetuar a operação, tente novamente!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    
    public GadoVacinado() {
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
        btn_apagar = new javax.swing.JButton();
        btn_procurar = new javax.swing.JButton();
        btn_fechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela_Gado = new javax.swing.JTable();
        TXT_BrincoRastreadoGado = new javax.swing.JLabel();
        TXT_NomeGado = new javax.swing.JLabel();
        TXT_NomeSetar = new javax.swing.JLabel();
        TXT_BrincoSetar = new javax.swing.JLabel();
        Título1 = new javax.swing.JLabel();
        Fundo = new javax.swing.JLabel();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        Painel.setBackground(new java.awt.Color(204, 204, 204));

        btn_apagar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_apagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/garbage (1).png"))); // NOI18N
        btn_apagar.setText("Apagar");
        btn_apagar.setToolTipText("Apagar");
        btn_apagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_apagarActionPerformed(evt);
            }
        });
        Painel.add(btn_apagar);
        btn_apagar.setBounds(60, 410, 110, 23);

        btn_procurar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_procurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/loupe (2).png"))); // NOI18N
        btn_procurar.setText("Procurar");
        btn_procurar.setToolTipText("Procurar");
        btn_procurar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_procurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_procurarActionPerformed(evt);
            }
        });
        Painel.add(btn_procurar);
        btn_procurar.setBounds(420, 410, 110, 23);

        btn_fechar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btn_fechar.setText("Fechar");
        btn_fechar.setToolTipText("Fechar");
        btn_fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        Painel.add(btn_fechar);
        btn_fechar.setBounds(700, 530, 71, 20);

        Tabela_Gado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Vacina", "Data De Vacinaçãol", "Vacinado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabela_Gado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabela_GadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabela_Gado);
        if (Tabela_Gado.getColumnModel().getColumnCount() > 0) {
            Tabela_Gado.getColumnModel().getColumn(0).setResizable(false);
            Tabela_Gado.getColumnModel().getColumn(1).setResizable(false);
            Tabela_Gado.getColumnModel().getColumn(2).setResizable(false);
        }

        Painel.add(jScrollPane1);
        jScrollPane1.setBounds(60, 170, 470, 210);

        TXT_BrincoRastreadoGado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXT_BrincoRastreadoGado.setText("Brinco Rastreado:");
        Painel.add(TXT_BrincoRastreadoGado);
        TXT_BrincoRastreadoGado.setBounds(560, 240, 120, 15);

        TXT_NomeGado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXT_NomeGado.setText("Nome:");
        Painel.add(TXT_NomeGado);
        TXT_NomeGado.setBounds(560, 190, 40, 15);

        TXT_NomeSetar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXT_NomeSetar.setText("Carregando...");
        Painel.add(TXT_NomeSetar);
        TXT_NomeSetar.setBounds(600, 190, 120, 15);

        TXT_BrincoSetar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TXT_BrincoSetar.setText("Carregando...");
        Painel.add(TXT_BrincoSetar);
        TXT_BrincoSetar.setBounds(670, 240, 80, 14);

        Título1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Título1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Título1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/syringe.png"))); // NOI18N
        Título1.setText("Gado Vacinado");
        Painel.add(Título1);
        Título1.setBounds(10, 70, 790, 60);

        Fundo.setForeground(new java.awt.Color(255, 255, 255));
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/telacinza800x600.png"))); // NOI18N
        Fundo.setToolTipText("");
        Painel.add(Fundo);
        Fundo.setBounds(4, 14, 800, 570);

        getContentPane().add(Painel);
        Painel.setBounds(-10, -20, 800, 600);

        setBounds(0, 0, 800, 586);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        // TODO add your handling code here:
        Fechar();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void Tabela_GadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela_GadoMouseClicked
        // TODO add your handling code here:s
    }//GEN-LAST:event_Tabela_GadoMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        Procurar1();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btn_procurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_procurarActionPerformed
        // TODO add your handling code here:
        Procurar1();
    }//GEN-LAST:event_btn_procurarActionPerformed

    private void btn_apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_apagarActionPerformed
        // TODO add your handling code here:
        Apagar1();
    }//GEN-LAST:event_btn_apagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    public static javax.swing.JDesktopPane Painel;
    private javax.swing.JLabel TXT_BrincoRastreadoGado;
    private javax.swing.JLabel TXT_BrincoSetar;
    private javax.swing.JLabel TXT_NomeGado;
    private javax.swing.JLabel TXT_NomeSetar;
    private javax.swing.JTable Tabela_Gado;
    private javax.swing.JLabel Título1;
    private javax.swing.JButton btn_apagar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JButton btn_procurar;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public String getGadoID() {
        return GadoID;
    }

    public void setGadoID(String GadoID) {
        this.GadoID = GadoID;
    }
    
    public String getIDVacina() {
        return IDVacina;
    }

    public void setIDVacina(String IDVacina) {
        this.IDVacina = IDVacina;
    }
}
