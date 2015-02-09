package view;

import Core.Excecao.Excecao;
import Entity.Cliente;
import Entity.Escritorio;
import Entity.Estado;
import Entity.Processo;
import Entity.Recorte;
import Entity.Tribunal;
import Model.ProcessoModel;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;

/**
 * View para cadastrar um processo.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class CadastroPublicacaoPorModelo extends javax.swing.JFrame
{
    // Classes de modelo:
    private final ProcessoModel cProcessoModel;
    
    // Classes de entidade:
    private final Recorte  cRecorte;
    private final Estado   cEstado;
    private final Processo cProcesso;
    
    /**
     * Cria o formulário CadastroPublicacao
     */
    public CadastroPublicacaoPorModelo()
    {
        initComponents();
        this.cRecorte  = new Recorte();
        this.cEstado   = new Estado();
        this.cProcesso = new Processo();
        this.cProcessoModel = new ProcessoModel();
    }
    
    /**
     * Cria o formulário CadastroPublicacao
     * @param recorte
     * @param estado 
     */
    public CadastroPublicacaoPorModelo(Recorte recorte, Estado estado)
    {
        initComponents();
        this.cRecorte  = recorte;
        this.cEstado   = estado;
        this.cProcesso = new Processo();
        this.cProcessoModel = new ProcessoModel();
    }
    
    /**
     * Cria o formulário CadastroPublicacao
     * @param recorte
     * @param estado
     * @param processo 
     */
    public CadastroPublicacaoPorModelo(Recorte recorte, Estado estado, Processo processo)
    {
        initComponents();
        
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/resources/edit.png"));
        this.setIconImage(imagemTitulo);
        
        this.cRecorte  = recorte;
        this.cEstado   = estado;
        this.cProcesso = processo;
        this.cProcessoModel = new ProcessoModel();
        preencherCampos();
        
        // Bloqueia campos:
        numeroProcessoPublicacaoTxt.setEnabled(false);
        arquivoPublicacaoTxt.setEnabled(false);
        ordemPublicacaoTxt.setEnabled(false);
        dataPublicacaoTxt.setEnabled(false);
        nomeBuscadotxt.setEnabled(false);
        codigoEscritorioPublicacaoTxt.setEnabled(false);
        tribunalPublicacaoTxt.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        arquivoPublicacaoTxt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ordemPublicacaoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dataPublicacaoTxt = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        codigoEscritorioPublicacaoTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nomeBuscadotxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tribunalPublicacaoTxt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        varaPublicacaoTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numeroProcessoPublicacaoTxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        comarcaPublicacaoTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoPublicacaoTxt = new javax.swing.JEditorPane();
        jLabel2 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuSalvar = new javax.swing.JMenuItem();

        setTitle("Cadastrar nova publicação");

        jLabel11.setText("ARQUIVO:");

        jLabel10.setText("ORDEM:");

        jLabel5.setText("DATA:");

        jLabel6.setText("CÓDIGO ESCRITÓRIO:");

        jLabel8.setText("VARIAÇÃO:");

        jLabel12.setText("DIÁRIO:");

        jLabel13.setText("VARA:");

        varaPublicacaoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                varaPublicacaoTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                varaPublicacaoTxtKeyReleased(evt);
            }
        });

        jLabel7.setText("NÚMERO DE PROCESSO:");

        jLabel15.setText("COMARCA:");

        textoPublicacaoTxt.setContentType("text/html"); // NOI18N
        textoPublicacaoTxt.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textoPublicacaoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoPublicacaoTxtKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(textoPublicacaoTxt);

        jLabel2.setText("TEXTO DO PROCESSO:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");

        menuSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSalvar.setText("Salvar");
        menuSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalvar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numeroProcessoPublicacaoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arquivoPublicacaoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ordemPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoEscritorioPublicacaoTxt))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(30, 30, 30)
                                .addComponent(tribunalPublicacaoTxt)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comarcaPublicacaoTxt)
                            .addComponent(nomeBuscadotxt)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(40, 40, 40)
                        .addComponent(varaPublicacaoTxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dataPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(ordemPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(arquivoPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroProcessoPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoEscritorioPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(nomeBuscadotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tribunalPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(comarcaPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(varaPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnOK))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void varaPublicacaoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_varaPublicacaoTxtKeyPressed

    }//GEN-LAST:event_varaPublicacaoTxtKeyPressed

    private void varaPublicacaoTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_varaPublicacaoTxtKeyReleased
        //confereAlteracoes();
    }//GEN-LAST:event_varaPublicacaoTxtKeyReleased

    private void textoPublicacaoTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoPublicacaoTxtKeyReleased
        //confereAlteracoes();
    }//GEN-LAST:event_textoPublicacaoTxtKeyReleased

    /**
     * Função chamada ao clicar no botão cancelar
     * @param evt 
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Função chamada ao clicar no botão salvar
     * @param evt 
     */
    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        this.salvarProcesso();
        this.setVisible(false);
    }//GEN-LAST:event_btnOKActionPerformed

    /**
     * Função chamada ao clicar no menu salvar
     * @param evt 
     */
    private void menuSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvarActionPerformed
        this.salvarProcesso();
        this.setVisible(false);
    }//GEN-LAST:event_menuSalvarActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPublicacaoPorModelo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroPublicacaoPorModelo().setVisible(true);
            }
        });
    }
    
    /**
     * Função para preencher os campos do formulário.
     */
    private void preencherCampos()
    {
        numeroProcessoPublicacaoTxt.setText(cProcesso.getNumeroProcesso());
        arquivoPublicacaoTxt.setText(cProcesso.getArquivo());
        ordemPublicacaoTxt.setText(cProcesso.getOrdem() + "");
        dataPublicacaoTxt.setText(new SimpleDateFormat("dd/MM/yyyy").format(cProcesso.getDataPublicacao()));
        codigoEscritorioPublicacaoTxt.setText(cProcesso.getEscritorio().getCodigo()+"");
        nomeBuscadotxt.setText(cProcesso.getEscritorio().getCliente().getNome());
        tribunalPublicacaoTxt.setText(cProcesso.getTribunal().getNomeTribunal());
        varaPublicacaoTxt.setText(cProcesso.getVara());
        textoPublicacaoTxt.setText(cProcesso.getCorpoPublicacao());
    }
    
    /**
     * Função para salvar um processo
     */
    private void salvarProcesso()
    {
        try
        {
            DateFormat df = DateFormat.getDateInstance();
            
            Processo cProcessoAtual = new Processo();
            cProcessoAtual.setNumeroProcesso(numeroProcessoPublicacaoTxt.getText());
            cProcessoAtual.setArquivo(arquivoPublicacaoTxt.getText());
            cProcessoAtual.setOrdem(Integer.parseInt(ordemPublicacaoTxt.getText()));
            cProcessoAtual.setDataPublicacao(df.parse(dataPublicacaoTxt.getText()));
            cProcessoAtual.setVara(varaPublicacaoTxt.getText());
            cProcessoAtual.setCorpoPublicacao(Jsoup.parse(textoPublicacaoTxt.getText()).text());
            
            Escritorio cEscritorio = new Escritorio();
            cEscritorio.setCodigo(Integer.parseInt(codigoEscritorioPublicacaoTxt.getText()));
            Cliente cCliente = new Cliente();
            cCliente.setNome(nomeBuscadotxt.getText());
            cEscritorio.setCliente(cCliente);
            cProcessoAtual.setEscritorio(cEscritorio);
            
            Tribunal cTribunal = new Tribunal();
            cTribunal.setNomeTribunal(tribunalPublicacaoTxt.getText());
            cProcessoAtual.setTribunal(cTribunal);
            
            cProcessoModel.cadastrar(cRecorte, cEstado, cProcessoAtual);
            
            JOptionPane.showMessageDialog(null, "Publicação cadastrada com sucesso.");
        }
        catch (ParseException ex)
        {
            new Excecao("Erro ao tentar converter a data", this.getClass().getName(), ex.toString());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arquivoPublicacaoTxt;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JTextField codigoEscritorioPublicacaoTxt;
    private javax.swing.JTextField comarcaPublicacaoTxt;
    private javax.swing.JFormattedTextField dataPublicacaoTxt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem menuSalvar;
    private javax.swing.JTextField nomeBuscadotxt;
    private javax.swing.JTextField numeroProcessoPublicacaoTxt;
    private javax.swing.JTextField ordemPublicacaoTxt;
    private javax.swing.JEditorPane textoPublicacaoTxt;
    private javax.swing.JTextField tribunalPublicacaoTxt;
    private javax.swing.JTextField varaPublicacaoTxt;
    // End of variables declaration//GEN-END:variables
}
