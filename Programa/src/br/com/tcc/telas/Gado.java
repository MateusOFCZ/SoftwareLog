/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcc.telas;

import br.com.tcc.dal.ModuloConexao;
import static br.com.tcc.telas.Gado.caixa_buscar;
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
public class Gado extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form Gado
     */
    
    public void Fechar(){
                this.dispose();
    }
    
    public void Adicionar(){
        GadoAdicionar gadoadicionar = new GadoAdicionar();
        Painel.add(gadoadicionar);
        gadoadicionar.setVisible(true);
    }
    
    public void Consultar(){
        String Item = (String) caixa_buscar.getSelectedItem();
        if("Nome".equals(Item)){
        String sql = "SELECT nome, brincorastreado, peso, raca, nascimento FROM gado WHERE iduser = ? AND nome like ?";
        
        try {
            Login login = new Login();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, login.getId());
            pst.setString(2, campo_buscar.getText() + "%");
            
            rs = pst.executeQuery();

            Tabela_Gado.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
      }
     
    if("Brinco Rastreado".equals(Item)){
        String sql = "SELECT nome, brincorastreado, peso, raca, nascimento FROM gado WHERE iduser = ? AND brincorastreado like ?";
        
        try {
            Principal tela = new Principal();
            Login login = new Login();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, login.getId());
            pst.setString(2, campo_buscar.getText() + "%");
            
            rs = pst.executeQuery();

            Tabela_Gado.setModel(DbUtils.resultSetToTableModel(rs));
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    }
    private static String IDGado;

    public String getIDGado() {
        return IDGado;
    }

    public void setIDGado(String IDGado) {
        this.IDGado = IDGado;
    }
    
    private void Apagar1(){
        String Brinco = JOptionPane.showInputDialog("Insira o Brinco Rastreado do gado que você deseja apagar: ");
        String sql = "SELECT id FROM gado WHERE iduser = ? AND brincorastreado = ?";
        
        try {
            Login login = new Login();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, login.getId());
            pst.setString(2, Brinco);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                setIDGado(rs.getString("id"));
                Apagar2();
            }else{
                JOptionPane.showMessageDialog(null, "Houve um erro ao apagar este gado! Tente novamente!");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    
    private void Apagar2(){
        Login login = new Login();
        
        String sql = "DELETE FROM gado WHERE iduser = ? AND id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                
                pst.setString(1, login.getId());
                pst.setString(2, getIDGado());
                
                int apagado = pst.executeUpdate();
                
                if(apagado > 0){
                Apagar3();
            }else{
                    JOptionPane.showMessageDialog(null, "Houve um erro ao apagar este gado! Tente novamente!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
            }
        }
    
    private void Apagar3(){
        Login login = new Login();
        
        String sql = "DELETE FROM vacinas WHERE iduser = ? AND idgado = ?";
            try {
                pst = conexao.prepareStatement(sql);
                
                pst.setString(1, login.getId());
                pst.setString(2, getIDGado());
                
                int apagado = pst.executeUpdate();
                
                if(apagado > 0){
                JOptionPane.showMessageDialog(null, "Apagado com sucesso!");
                Consultar();
            }else{
                    JOptionPane.showMessageDialog(null, "Houve um erro ao apagar este gado! Tente novamente!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
            }
        }
    
    public void Vacinas(){
                Vacinas Vacinas = new Vacinas();
                Painel.add(Vacinas);
                Vacinas.setVisible(true);
    }
    
    public void Setar(){
        GadoEditar gadoeditar = new GadoEditar();

        int Setar = Tabela_Gado.getSelectedRow();
        if(Setar > 0){
        gadoeditar.setNome(Tabela_Gado.getModel().getValueAt(Setar, 0).toString());
        gadoeditar.setBrincorastreado(Tabela_Gado.getModel().getValueAt(Setar, 1).toString());
        gadoeditar.setPeso(Tabela_Gado.getModel().getValueAt(Setar, 2).toString());
        gadoeditar.setRaca(Tabela_Gado.getModel().getValueAt(Setar, 3).toString());
        gadoeditar.setNascimento(Tabela_Gado.getModel().getValueAt(Setar, 4).toString());
        }else{
            JOptionPane.showMessageDialog(null, "Selecione o gado na tabela e em seguida clique em Editar!");
        }
    }
    
    private void Editar(){
        String Brinco = JOptionPane.showInputDialog("Insira o Brinco Rastreado do gado que você deseja editar: ");
        
        String sql = "SELECT * FROM gado WHERE iduser = ? AND brincorastreado = ?";
        
        try {
            Login login = new Login();
            GadoEditar gadoeditar = new GadoEditar();
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, login.getId());
            pst.setString(2, Brinco);
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                gadoeditar.setNome(rs.getString("nome"));
                gadoeditar.setBrincorastreado(rs.getString("brincorastreado"));
                gadoeditar.setPeso(rs.getString("peso"));
                gadoeditar.setRaca(rs.getString("raca"));
                gadoeditar.setNascimento(rs.getString("nascimento"));
                
                Painel.add(gadoeditar);
                gadoeditar.setVisible(true);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO:\n" + e);
        }
    }
    
    public Gado() {
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
        caixa_buscar = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txt_buscaraviso = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JLabel();
        campo_buscar = new javax.swing.JTextField();
        btn_vacinas = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_adicionar = new javax.swing.JButton();
        btn_apagar = new javax.swing.JButton();
        btn_fechar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela_Gado = new javax.swing.JTable();
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

        caixa_buscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Brinco Rastreado" }));
        Painel.add(caixa_buscar);
        caixa_buscar.setBounds(550, 180, 130, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/loupe (2).png"))); // NOI18N
        Painel.add(jLabel1);
        jLabel1.setBounds(380, 170, 16, 20);

        txt_buscaraviso.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txt_buscaraviso.setText("Insira o método de busca!");
        Painel.add(txt_buscaraviso);
        txt_buscaraviso.setBounds(550, 160, 140, 13);

        txt_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_buscar.setText("Buscar:");
        Painel.add(txt_buscar);
        txt_buscar.setBounds(110, 170, 70, 17);

        campo_buscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campo_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_buscarActionPerformed(evt);
            }
        });
        campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_buscarKeyReleased(evt);
            }
        });
        Painel.add(campo_buscar);
        campo_buscar.setBounds(170, 163, 230, 30);

        btn_vacinas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_vacinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/syringe.png"))); // NOI18N
        btn_vacinas.setText("Vacinas");
        btn_vacinas.setToolTipText("Editar");
        btn_vacinas.setBorderPainted(false);
        btn_vacinas.setContentAreaFilled(false);
        btn_vacinas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_vacinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vacinasActionPerformed(evt);
            }
        });
        Painel.add(btn_vacinas);
        btn_vacinas.setBounds(650, 30, 130, 40);

        btn_editar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/edit (2).png"))); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.setToolTipText("Editar");
        btn_editar.setBorderPainted(false);
        btn_editar.setContentAreaFilled(false);
        btn_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });
        Painel.add(btn_editar);
        btn_editar.setBounds(570, 430, 110, 40);

        btn_adicionar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/file.png"))); // NOI18N
        btn_adicionar.setText("Adicionar");
        btn_adicionar.setToolTipText("Adicionar");
        btn_adicionar.setBorderPainted(false);
        btn_adicionar.setContentAreaFilled(false);
        btn_adicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adicionarActionPerformed(evt);
            }
        });
        Painel.add(btn_adicionar);
        btn_adicionar.setBounds(530, 30, 120, 40);

        btn_apagar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_apagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/garbage (1).png"))); // NOI18N
        btn_apagar.setText("Apagar");
        btn_apagar.setToolTipText("Apagar");
        btn_apagar.setBorderPainted(false);
        btn_apagar.setContentAreaFilled(false);
        btn_apagar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_apagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_apagarActionPerformed(evt);
            }
        });
        Painel.add(btn_apagar);
        btn_apagar.setBounds(440, 430, 120, 40);

        btn_fechar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_fechar.setText("Fechar");
        btn_fechar.setToolTipText("Fechar");
        btn_fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_fecharActionPerformed(evt);
            }
        });
        Painel.add(btn_fechar);
        btn_fechar.setBounds(690, 540, 90, 23);

        Tabela_Gado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nome", "Brinco Rastreado", "Peso", "Raça", "Nascimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            Tabela_Gado.getColumnModel().getColumn(3).setResizable(false);
            Tabela_Gado.getColumnModel().getColumn(4).setResizable(false);
        }

        Painel.add(jScrollPane1);
        jScrollPane1.setBounds(110, 210, 570, 210);

        Título1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        Título1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Título1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/logoboi.png"))); // NOI18N
        Título1.setText("Gado");
        Painel.add(Título1);
        Título1.setBounds(280, 50, 320, 90);

        Fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tcc/img/telacinza800x600.png"))); // NOI18N
        Fundo.setToolTipText("");
        Painel.add(Fundo);
        Fundo.setBounds(10, 20, 800, 570);

        getContentPane().add(Painel);
        Painel.setBounds(-10, -20, 800, 600);

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adicionarActionPerformed
        // TODO add your handling code here:
        Adicionar();
    }//GEN-LAST:event_btn_adicionarActionPerformed

    private void btn_apagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_apagarActionPerformed
        // TODO add your handling code here:
        Apagar1();
        Consultar();
    }//GEN-LAST:event_btn_apagarActionPerformed

    private void btn_fecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_fecharActionPerformed
        // TODO add your handling code here:
        Fechar();
    }//GEN-LAST:event_btn_fecharActionPerformed

    private void Tabela_GadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela_GadoMouseClicked
        // TODO add your handling code here:s
    }//GEN-LAST:event_Tabela_GadoMouseClicked

    private void campo_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_buscarActionPerformed
        // TODO add your handling code here:
        Consultar();
    }//GEN-LAST:event_campo_buscarActionPerformed

    private void campo_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_buscarKeyReleased
        // TODO add your handling code here:
        Consultar();
    }//GEN-LAST:event_campo_buscarKeyReleased

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        // TODO add your handling code here:
        Editar();
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_vacinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vacinasActionPerformed
        // TODO add your handling code here:
        Vacinas();
    }//GEN-LAST:event_btn_vacinasActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        Consultar();
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fundo;
    public static javax.swing.JDesktopPane Painel;
    private javax.swing.JTable Tabela_Gado;
    private javax.swing.JLabel Título1;
    private javax.swing.JButton btn_adicionar;
    private javax.swing.JButton btn_apagar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_fechar;
    private javax.swing.JButton btn_vacinas;
    public static javax.swing.JComboBox<String> caixa_buscar;
    private javax.swing.JTextField campo_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txt_buscar;
    private javax.swing.JLabel txt_buscaraviso;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the BrincoTOID
     */

    /**
     * @return the Brinco
     */
}