package view;

import Core.Excecao.Excecao;
import Entity.Cliente;
import Entity.Escritorio;
import Entity.Estado;
import Entity.Processo;
import Entity.Recorte;
import Entity.Tribunal;
import Model.ClienteModel;
import Model.EscritorioModel;
import Model.EstadoModel;
import Model.ProcessoModel;
import Model.RecorteModel;
import Model.TribunalModel;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;

/**
 * View para cadastrar um processo.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class CadastroPublicacaoEmBranco extends javax.swing.JFrame
{
    // Classes de modelo:
    private final RecorteModel    cRecorteModel;
    private final EstadoModel     cEstadoModel;
    private final TribunalModel   cTribunalModel;
    private final EscritorioModel cEscritorioModel;
    private final ClienteModel    cClienteModel;
    
    /**
     * Cria o formulário CadastroPublicacao
     */
    public CadastroPublicacaoEmBranco()
    {
        initComponents();
        
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/resources/edit.png"));
        this.setIconImage(imagemTitulo);
        
        this.cRecorteModel    = new RecorteModel();
        this.cEstadoModel     = new EstadoModel();
        this.cTribunalModel   = new TribunalModel();
        this.cEscritorioModel = new EscritorioModel();
        this.cClienteModel    = new ClienteModel();
        
        preencherCampos();
        carregarRecortes();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        dataVistaTxt = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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
        codigoEscritorioPublicacaoTxt = new javax.swing.JComboBox();
        nomeBuscadotxt = new javax.swing.JComboBox();
        recorteTxt = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        estadoTxt = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tribunalTxt = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuSalvar = new javax.swing.JMenuItem();

        setTitle("Cadastrar nova publicação");

        jLabel5.setText("DATA:");

        jLabel6.setText("ESCRITÓRIO:");

        jLabel8.setText("VARIAÇÃO:");

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
        textoPublicacaoTxt.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0;\">\r\n      \r\n    </p>\r\n  </body>\r\n</html>\r\n");
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

        codigoEscritorioPublicacaoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoEscritorioPublicacaoTxtActionPerformed(evt);
            }
        });

        recorteTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recorteTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("RECORTE:");

        jLabel3.setText("ESTADO:");

        estadoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoTxtActionPerformed(evt);
            }
        });

        jLabel4.setText("DIÁRIO:");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recorteTxt, 0, 204, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadoTxt, 0, 204, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tribunalTxt, 0, 204, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numeroProcessoPublicacaoTxt))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codigoEscritorioPublicacaoTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(varaPublicacaoTxt)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nomeBuscadotxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comarcaPublicacaoTxt)
                            .addComponent(dataVistaTxt))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recorteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(estadoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(tribunalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dataVistaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroProcessoPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(codigoEscritorioPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeBuscadotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varaPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(comarcaPublicacaoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
     * Função chamada ao mudar o estado que será cadastrado
     * @param evt 
     */
    private void estadoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoTxtActionPerformed
        carregarTribunais();
    }//GEN-LAST:event_estadoTxtActionPerformed

    private void codigoEscritorioPublicacaoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoEscritorioPublicacaoTxtActionPerformed
        carregarNomes();
    }//GEN-LAST:event_codigoEscritorioPublicacaoTxtActionPerformed

    private void recorteTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recorteTxtActionPerformed
        carregarEscritorios();
        carregarEstados();
    }//GEN-LAST:event_recorteTxtActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroPublicacaoEmBranco.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroPublicacaoEmBranco().setVisible(true);
            }
        });
    }
    
    /**
     * Função para buscar todos os recortes e coloca no select dos recortes da tela inicial
     */
    private void carregarRecortes()
    {
        Thread thread;
        thread = new Thread(new Runnable()
        {
            @Override
            @SuppressWarnings("unchecked")
            public void run()
            {
                try
                {
                    // Busca os recortes
                    List<Recorte> ListaRecortes = cRecorteModel.buscar();

                    for (Recorte ListaRecorte : ListaRecortes)
                    {
                        String nomeRecorte = ListaRecorte.getNomeRecorte();                    
                        recorteTxt.addItem(nomeRecorte);
                    }

                    // Deixa selecionado o index 0
                    if (!ListaRecortes.isEmpty())
                    {
                        recorteTxt.setSelectedIndex(0);
                    }

                    // Carrega os estados
                    carregarEscritorios();
                    carregarEstados();
                }
                catch (SQLException ex)
                {
                    Excecao excecao = new Excecao("Erro de SQL", this.getClass().getName(), "Erro ao buscar os recortes. " + ex.toString());
                }
                catch (ClassNotFoundException ex)
                {
                    Excecao excecao = new Excecao("Classe não encontrada", this.getClass().getName(), "Erro ao iniciar a classe do JTDS. " + ex.toString());
                }
                
            }            
        }, "Thread para buscar os recortes.");
        
        thread.start();
    }
    
    /**
     * Função para buscar todos os estados e colocar no select dos estados da tela inicial.
     */
    private void carregarEstados()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            @SuppressWarnings("unchecked")
            public void run()
            {
                try
                {
                    // Busca os estados
                    List<Estado> ListaEstados = cEstadoModel.buscar();

                    estadoTxt.removeAllItems();

                    for (Estado ListaEstado : ListaEstados)
                    {
                        String nomeEstado = ListaEstado.getNome();
                        estadoTxt.addItem(nomeEstado);
                    }

                    // Deixa selecionado como padrão o index 0
                    if (!ListaEstados.isEmpty())
                    {
                        estadoTxt.setSelectedIndex(0);
                    }

                    // Carrega os tribunais
                    carregarTribunais();
                }
                catch (SQLException ex)
                {
                    Excecao excecao = new Excecao("Erro de SQL", this.getClass().getName(), "Erro ao buscar os estados. " + ex.toString());
                }
                catch (ClassNotFoundException ex)
                {
                    Excecao excecao = new Excecao("Classe não encontrada", this.getClass().getName(), "Erro ao iniciar a classe do JTDS. " + ex.toString());
                }
            }
        }, "Thread para buscar os estados.");
        
        thread.start();
    }
    
    /**
     * Função para buscar todos os tribunais de acordo com o estado selecionado
     */
    private void carregarTribunais()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            @SuppressWarnings("unchecked")
            public void run()
            {
                try
                {
                    Recorte cRecorte = new Recorte();
                    cRecorte.setNomeRecorte(recorteTxt.getSelectedItem().toString());

                    Estado cEstado = new Estado();
                    cEstado.setNome(estadoTxt.getSelectedItem().toString());

                    tribunalTxt.removeAllItems();

                    // Busca os tribunais
                    List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);

                    for (Tribunal ListaTribunal : ListaTribunais)
                    {
                        String siglaTribunal = ListaTribunal.getSigla();
                        String nomeTribunal  = ListaTribunal.getNomeTribunal();                    
                        tribunalTxt.addItem(siglaTribunal + " - " + nomeTribunal);
                    }

                    // Deixa selecionado como padrão o index 0
                    if (!ListaTribunais.isEmpty())
                    {
                        tribunalTxt.setSelectedIndex(0);
                    }
                }
                catch (SQLException ex)
                {
                    Excecao excecao = new Excecao("Erro de SQL", this.getClass().getName(), "Erro ao buscar os tribunais. " + ex.toString());
                }
                catch (ClassNotFoundException ex)
                {
                    Excecao excecao = new Excecao("Classe não encontrada", this.getClass().getName(), "Erro ao iniciar a classe do JTDS. " + ex.toString());
                }
            }
        }, "Thread para buscar os tribunais.");
        
        thread.start();
    }
    
    /**
     * Função para carregar nomes.
     */
    private void carregarNomes()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            @SuppressWarnings("unchecked")
            public void run()
            {
                try
                {
                    Recorte cRecorte = new Recorte();
                    cRecorte.setNomeRecorte(recorteTxt.getSelectedItem().toString());

                    nomeBuscadotxt.removeAllItems();

                    Escritorio cEscritorio = new Escritorio();
                    cEscritorio.setCodigo(Integer.parseInt(codigoEscritorioPublicacaoTxt.getSelectedItem().toString().split(" - ")[0]));

                    nomeBuscadotxt.removeAllItems();

                    List<Cliente> ListaClientes = cClienteModel.buscar(cRecorte, cEscritorio);

                    for (Cliente cCliente : ListaClientes)
                    {
                        int codigo = cCliente.getNum();
                        String nome = cCliente.getNome();
                        nomeBuscadotxt.addItem(codigo + " - " + nome);
                    }

                    if (!ListaClientes.isEmpty())
                    {
                        nomeBuscadotxt.setSelectedIndex(0);
                    }
                }
                catch (SQLException ex)
                {
                    Excecao excecao = new Excecao("Erro de SQL", this.getClass().getName(), "Erro ao buscar os nomes. " + ex.toString());
                }
                catch (ClassNotFoundException ex)
                {
                    Excecao excecao = new Excecao("Classe não encontrada", this.getClass().getName(), "Erro ao iniciar a classe do JTDS. " + ex.toString());
                }
            }
        }, "Thread para carregar os nomes.");
        
        thread.start();
    }
    
    /**
     * Função para caqrregar os escritórios.
     */
    private void carregarEscritorios()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            @SuppressWarnings("unchecked")
            public void run()
            {
                try
                {
                    Recorte cRecorte = new Recorte();
                    cRecorte.setNomeRecorte(recorteTxt.getSelectedItem().toString());

                    codigoEscritorioPublicacaoTxt.removeAllItems();

                    List<Escritorio> ListaEscritorios = cEscritorioModel.buscar(cRecorte);

                    for (Escritorio cEscritorio : ListaEscritorios)
                    {
                        int codigo = cEscritorio.getCodigo();
                        String nome = cEscritorio.getNome();
                        codigoEscritorioPublicacaoTxt.addItem(codigo + " - " + nome);
                    }

                    if (!ListaEscritorios.isEmpty())
                    {
                        codigoEscritorioPublicacaoTxt.setSelectedIndex(0);
                    }

                    carregarNomes();
                }
                catch (SQLException ex)
                {
                    Excecao excecao = new Excecao("Erro de SQL", this.getClass().getName(), "Erro ao buscar os escritórios. " + ex.toString());
                }
                catch (ClassNotFoundException ex)
                {
                    Excecao excecao = new Excecao("Classe não encontrada", this.getClass().getName(), "Erro ao iniciar a classe do JTDS. " + ex.toString());
                }
            }
        }, "Thread para buscar os escritórios.");
        
        thread.start();
    }
    
    /**
     * Função para preencher os campos do formulário.
     */
    private void preencherCampos()
    {
        dataVistaTxt.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }
    
    /**
     * Função para salvar um processo
     */
    private void salvarProcesso()
    {
        try
        {
            Recorte cRecorte = new Recorte();
            cRecorte.setNomeRecorte(recorteTxt.getSelectedItem().toString());
            
            Estado cEstado = new Estado();
            cEstado.setNome(estadoTxt.getSelectedItem().toString());
            
            DateFormat df = DateFormat.getDateInstance();
            
            Processo cProcessoAtual = new Processo();
            cProcessoAtual.setNumeroProcesso(numeroProcessoPublicacaoTxt.getText());
            cProcessoAtual.setArquivo(tribunalTxt.getSelectedItem().toString().split(" - ")[0] + "-0.txt");
            cProcessoAtual.setOrdem(0);
            cProcessoAtual.setDataVista(df.parse(dataVistaTxt.getText()));
            cProcessoAtual.setVara(varaPublicacaoTxt.getText());
            cProcessoAtual.setCorpoPublicacao(Jsoup.parse(textoPublicacaoTxt.getText()).text());
            
            Escritorio cEscritorio = new Escritorio();
            cEscritorio.setCodigo(Integer.parseInt(codigoEscritorioPublicacaoTxt.getSelectedItem().toString().split(" - ")[0]));
            Cliente cCliente = new Cliente();
            cCliente.setNome(nomeBuscadotxt.getSelectedItem().toString().split(" - ")[1]);
            cEscritorio.setCliente(cCliente);
            cProcessoAtual.setEscritorio(cEscritorio);
            
            Tribunal cTribunal = new Tribunal();
            cTribunal.setNomeTribunal(tribunalTxt.getSelectedItem().toString().split(" - ")[1]);
            cProcessoAtual.setTribunal(cTribunal);
            
            ProcessoModel cProcessoModel = new ProcessoModel(cRecorte, cEstado);
            cProcessoModel.cadastrar(cProcessoAtual);
            
            JOptionPane.showMessageDialog(null, "Publicação cadastrada com sucesso.");
        }
        catch (ParseException ex)
        {
            Excecao excecao = new Excecao("Erro ao tentar converter a data", this.getClass().getName(), ex.toString());
        }
        catch (SQLException ex)
        {
            Excecao excecao = new Excecao("Erro de SQL", this.getClass().getName(), "Erro ao atualizar o processo. " + ex.toString());
        }
        catch (ClassNotFoundException ex)
        {
            Excecao excecao = new Excecao("Classe não encontrada", this.getClass().getName(), "Erro ao iniciar a classe do JTDS. " + ex.toString());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JComboBox codigoEscritorioPublicacaoTxt;
    private javax.swing.JTextField comarcaPublicacaoTxt;
    private javax.swing.JFormattedTextField dataVistaTxt;
    private javax.swing.JComboBox estadoTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem menuSalvar;
    private javax.swing.JComboBox nomeBuscadotxt;
    private javax.swing.JTextField numeroProcessoPublicacaoTxt;
    private javax.swing.JComboBox recorteTxt;
    private javax.swing.JEditorPane textoPublicacaoTxt;
    private javax.swing.JComboBox tribunalTxt;
    private javax.swing.JTextField varaPublicacaoTxt;
    // End of variables declaration//GEN-END:variables
}
