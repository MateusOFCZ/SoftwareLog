/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.telas;

import br.com.tcc.dal.ModuloConexao;
import static br.com.tcc.telas.Gado.caixa_buscar;
import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Mateus da Cruz
 */
public class Vacinas extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void Fechar(){
        Gado gado = new Gado();
        this.dispose();
    }
    
    /*public void Data() {
        Date data = new Date();
        SimpleDateFormat Formato = new SimpleDateFormat("DD MM YYYY");
        String Data = formatador.format(data);
}*/
    
    public void SetarGado(){
        String sql = "SELECT nome FROM gado WHERE iduser = ?";
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            
            rs = pst.executeQuery();

            Tabela_Gado.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
    }
    
    public void SetarVacina(){
        String sql = "SELECT nome FROM vacinalista WHERE iduser = ?";
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            
            rs = pst.executeQuery();

            Tabela_Vacina.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
    }
    
    public void AdicionarVacina(){
        String Nome = JOptionPane.showInputDialog("Insira o nome da vacina:");
        String sql = "INSERT INTO vacinalista (iduser, nome) VALUES(?, ?)";
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, Nome);
            
            int Adicionado = pst.executeUpdate();
            
            if(Adicionado > 0){
                JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");
                SetarGado();
                SetarVacina();
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao adicionar, tente novamente!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
    }
    
    private String IDGado;
    
    public void ProcurarIDGado(){
        String sql = "SELECT id FROM gado WHERE iduser = ? AND nome = ?";
        
        int Setar = Tabela_Gado.getSelectedRow();
        String NomeGado = Tabela_Gado.getModel().getValueAt(Setar, 0).toString();
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, NomeGado);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                this.setIDGado(rs.getString("id"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
    }
    
    public void Salvar(){
        ProcurarIDGado();
        String sql = "INSERT INTO vacinas (iduser, idgado, nomevacina, datavacinado, vacinado) VALUES(?, ?, ?, ?, ?)";
        String Vacinado = null;
        if(Caixa_Vacinado.isSelected() == true){
            Vacinado = "Sim";
        }
        if(Caixa_Vacinado.isSelected() == false){
            Vacinado = "Não";
        }
        
        String DataDia = (String) Caixa_Dia.getSelectedItem();
        String DataMes = (String) Caixa_Mês.getSelectedItem();
        String DataAno = (String) Caixa_Ano.getSelectedItem();
        String Data = DataDia + "/" + DataMes + "/" + DataAno;
        
        int Setar = Tabela_Vacina.getSelectedRow();
        String NomeVacina = Tabela_Vacina.getModel().getValueAt(Setar, 0).toString();
        
        try {
            Login Login = new Login();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Login.getId());
            pst.setString(2, this.getIDGado());
            pst.setString(3, NomeVacina);
            pst.setString(4, Data);
            pst.setString(5, Vacinado);
            
            int Adicionado = pst.executeUpdate();
            
            if(Adicionado > 0){
                JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");
                SetarGado();
                SetarVacina();
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao adicionar, tente novamente!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO: " + e);
        }
    }
    
    public void GadoVacinado(){
        GadoVacinado GadoVacinado = new GadoVacinado();
        Painel.add(GadoVacinado);
        GadoVacinado.setVisible(true);
    }
    
    public Vacinas() {
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
        btn_fechar = new javax.swing.JButton();
        btn_gadovacinado = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_adicionar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela_Vacina = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabela_Gado = new javax.swing.JTable();
        Caixa_Dia = new javax.swing.JComboBox<>();
        Caixa_Mês = new javax.swing.JComboBox<>();
        Caixa_Ano = new javax.swing.JComboBox<>();
        Caixa_Vacinado = new javax.swing.JCheckBox();
        Título = new javax.swing.JLabel();
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

        Painel.setBackground(new java.awt.Color(153, 153, 153));

        btn_fechar.setBackground(new java.awt.Color(255, 255, 255));
        btn_fechar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_fechar.setText("Fechar");
        btn_fechar.setToolTipText("Fechar");
        btn_fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_fecharMouseClicked(evt);
            }
        });
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        Painel.add(btn_fechar);
        btn_fechar.setBounds(700, 550, 80, 25);

        btn_gadovacinado.setBackground(new java.awt.Color(255, 255, 255));
        btn_gadovacinado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_gadovacinado.setText("Gado Vacinado");
        btn_gadovacinado.setToolTipText("Gado Vacinado");
        btn_gadovacinado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_gadovacinado.setBorderPainted(false);
        btn_gadovacinado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gadovacinadoActionPerformed(evt);
            }
        });
        Painel.add(btn_gadovacinado);
        btn_gadovacinado.setBounds(70, 410, 190, 30);

        btn_salvar.setBackground(new java.awt.Color(255, 255, 255));
        btn_salvar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_salvar.setText("Salvar");
        btn_salvar.setToolTipText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });
        Painel.add(btn_salvar);
        btn_salvar.setBounds(530, 410, 130, 30);

        btn_adicionar.setBackground(new java.awt.Color(255, 255, 255));
        btn_adicionar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/edit (2).png"))); // NOI18N
        btn_adicionar.setText("Adicionar Vacina");
        btn_adicionar.setToolTipText("Adicionar Vacina");
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        Painel.add(btn_adicionar);
        btn_adicionar.setBounds(300, 410, 190, 30);

        Tabela_Vacina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Nome Das Vacinas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabela_Vacina);
        if (Tabela_Vacina.getColumnModel().getColumnCount() > 0) {
            Tabela_Vacina.getColumnModel().getColumn(0).setResizable(false);
        }

        Painel.add(jScrollPane1);
        jScrollPane1.setBounds(300, 180, 190, 210);

        Tabela_Gado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Gado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Tabela_Gado);
        if (Tabela_Gado.getColumnModel().getColumnCount() > 0) {
            Tabela_Gado.getColumnModel().getColumn(0).setResizable(false);
        }

        Painel.add(jScrollPane2);
        jScrollPane2.setBounds(70, 180, 190, 210);

        Caixa_Dia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Caixa_Dia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione O Dia", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        Painel.add(Caixa_Dia);
        Caixa_Dia.setBounds(530, 180, 140, 23);

        Caixa_Mês.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Caixa_Mês.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione O Mês", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        Painel.add(Caixa_Mês);
        Caixa_Mês.setBounds(530, 240, 140, 23);

        Caixa_Ano.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Caixa_Ano.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione O Ano", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019" }));
        Painel.add(Caixa_Ano);
        Caixa_Ano.setBounds(530, 300, 140, 23);

        Caixa_Vacinado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Caixa_Vacinado.setSelected(true);
        Caixa_Vacinado.setText("Vacinado");
        Caixa_Vacinado.setToolTipText("Vacinado");
        Painel.add(Caixa_Vacinado);
        Caixa_Vacinado.setBounds(530, 360, 140, 25);

        Título.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        Título.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Título.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/syringe.png"))); // NOI18N
        Título.setText("Vacinas");
        Painel.add(Título);
        Título.setBounds(10, 60, 790, 90);

        Fundo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/telacinza800x600.png"))); // NOI18N
        Fundo.setToolTipText("");
        Painel.add(Fundo);
        Fundo.setBounds(10, 0, 800, 600);

        getContentPane().add(Painel);
        Painel.setBounds(-10, -30, 810, 630);

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        // TODO add your handling code here:
        Fechar();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void btn_fecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_fecharMouseClicked

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        // TODO add your handling code here:
        AdicionarVacina();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        // TODO add your handling code here:
        Salvar();
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        SetarGado();
        SetarVacina();
    }//GEN-LAST:event_formInternalFrameOpened

    private void btn_gadovacinadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gadovacinadoActionPerformed
        // TODO add your handling code here:
        GadoVacinado();
    }//GEN-LAST:event_btn_gadovacinadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Caixa_Ano;
    private javax.swing.JComboBox<String> Caixa_Dia;
    private javax.swing.JComboBox<String> Caixa_Mês;
    private javax.swing.JCheckBox Caixa_Vacinado;
    private javax.swing.JLabel Fundo;
    private javax.swing.JDesktopPane Painel;
    private javax.swing.JTable Tabela_Gado;
    private javax.swing.JTable Tabela_Vacina;
    private javax.swing.JLabel Título;
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JButton btn_gadovacinado;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the IDGado
     */
    public String getIDGado() {
        return IDGado;
    }

    /**
     * @param IDGado the IDGado to set
     */
    public void setIDGado(String IDGado) {
        this.IDGado = IDGado;
    }

}