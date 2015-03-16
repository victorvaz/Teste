package Model;

import Core.Excecao.Excecao;
import DAL.RecorteDAL;
import DAL.SqlFactory;
import Entity.Cliente;
import Entity.Escritorio;
import Entity.Estado;
import Entity.Processo;
import Entity.Recorte;
import Entity.Tribunal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.jsoup.helper.StringUtil;
import view.TelaPrincipal;

/** 
 * Classe modelo para a entidade processo
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class ProcessoModel
{
    // Classes de Entidade:
    private final Recorte cRecorte;
    private final Estado  cEstado;
    
    // Classes modelo:
    private final TabelaEstadoModel cTabelaEstadoModel;
    private final TribunalModel     cTribunalModel;
    
    // Classes de view:
    private TelaPrincipal cTelaPrincipal;
    
    /**
     * Construtor da classe
     * @param cRecorte Recorte referente ao processo.
     * @param cEstado  Estado referente ao processo.
     */
    public ProcessoModel(Recorte cRecorte, Estado cEstado)
    {
        // Define as classes de entidade:
        this.cRecorte = cRecorte;
        this.cEstado  = cEstado;
        
        // Inicia as classes de modelo:
        this.cTabelaEstadoModel = new TabelaEstadoModel();
        this.cTribunalModel     = new TribunalModel();
        
        // Inicia as classes de view:
        this.cTelaPrincipal = null;
    }
    
    /**
     * Função para definir a TelaPrincipal para o processo Model;
     * @param cTelaPrincipal 
     */
    public void setTelaPrincipal(TelaPrincipal cTelaPrincipal)
    {
        this.cTelaPrincipal = cTelaPrincipal;
    }

    /**
     * Função para retornar a TelaPrincipal
     * @return cTelaPrincipal
     */
    private TelaPrincipal getTelaPrincipal()
    {
        return this.cTelaPrincipal;
    }
    
    /**
     * Função para buscar processos de forma geral. É uma busca centralizada: todas os outros tipos de busca apontam para esta função.
     * @param cRecorte Recorte no qual buscaremos as publicações.
     * @param cEstado  Estado das publicações.
     * @param where    Condições para a busca.
     * @return Lista de Processos.
     */
    private List<Processo> buscar(List<String> where)
    {
        List<Processo> ListaProcessos = new ArrayList<>();
        
        String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
        List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
        
        RecorteDAL DAL = new RecorteDAL();
        DAL.setRecorte(cRecorte);

        String[] sqlSelect =
        {
            "P1.NUM                    AS NUM_PUBLICACAO",
            "P1.N_PROCESSO             AS NUMERO_PROCESSO",
            "P1.TRIBUNAL               AS TRIBUNAL",
            "P1.VARA                   AS VARA",
            "P1.ORDEM                  AS ORDEM",
            "P1.NOME                   AS NOME_BUSCADO",
            "P1.CODIGO                 AS CODIGO_ESCRITORIO",
            "P1.ARQUIVO                AS ARQUIVO",
            "P1.DATA                   AS DATA_VISTA",
            "P1.Revisado               AS REVISADO",
            "DOM.DATA_DISPONIBILIZACAO AS DATA_DISPONIBILIZACAO",
            "DOM.DATA_PUBLICACAO       AS DATA_PUBLICACAO",
            "P2.PUBLICACAO             AS CORPO_PUBLICACAO",
            "E.NOME                    AS NOME_ESCRITORIO"
        };

        String[] sqlFrom =
        {
            tabelaEstado + " P1"
        };

        String[] sqlInnerJoin =
        {
            "INNER JOIN ESCRITORIO E"
                + "  ON E.CODIGO = P1.CODIGO",
            "INNER JOIN " + tabelaEstado + "2 P2"
                + "  ON P1.NUM = P2.NUM2",
            "INNER JOIN VISTA.dbo.DIARIO_OFICIAL_MAPA DOM"
                + "  ON DOM.DATA_VISTA = P1.DATA"
        };
        
        String[] sqlOrderBy =
        {
            "P1.TRIBUNAL",
            "P1.NUM"
        };
        
        where.add("DOM.SIGLA IN (SELECT SIGLA"
                + "                FROM VISTA.dbo.DIARIO_OFICIAL_TRIBUNAIS DOT"
                + "               WHERE CAST(DOT.TRIBUNAL AS VARCHAR(50)) COLLATE Latin1_General_CI_AI = P1.TRIBUNAL"
                + "                 AND DOT.ESTADO = '" + cEstado.getNome() + "'"
                + "                 AND DOT.CLIENTE = '" + cRecorte.getNomeRecorte() + "')");
        
        String[] sqlWhere = where.toArray(new String[where.size()]);

        // Cria o select geral:
        String sql = SqlFactory.createSelect(sqlSelect, sqlFrom, sqlInnerJoin, sqlWhere, sqlOrderBy);

        ResultSet row = DAL.executarSelectQuery(sql);
        
        try
        {
            while (row.next())
            {
                Processo cProcesso = new Processo();
                cProcesso.setNumProcesso(row.getInt("NUM_PUBLICACAO"));
                cProcesso.setNumeroProcesso(row.getString("NUMERO_PROCESSO"));
                cProcesso.setDataVista(row.getDate("DATA_VISTA"));
                cProcesso.setDataPublicacao(row.getDate("DATA_PUBLICACAO"));
                cProcesso.setDataDisponibilizacao(row.getDate("DATA_DISPONIBILIZACAO"));
                cProcesso.setArquivo(row.getString("ARQUIVO"));
                cProcesso.setVara(row.getString("VARA"));
                cProcesso.setCorpoPublicacao(row.getString("CORPO_PUBLICACAO"));
                cProcesso.setOrdem(row.getInt("ORDEM"));
                cProcesso.setRevisado(row.getInt("REVISADO") == 1);
                
                // Pega o tribunal correspondente:
                String nomeTribunal = row.getString("TRIBUNAL");
                for (Tribunal cTribunal : ListaTribunais)
                {
                    if (cTribunal.getNomeTribunal().equals(nomeTribunal))
                    {
                        cProcesso.setTribunal(cTribunal);
                    }
                }
                
                // Escritório:
                Escritorio cEscritorio = new Escritorio();
                cEscritorio.setNome(row.getString("NOME_ESCRITORIO"));
                cEscritorio.setCodigo(row.getInt("CODIGO_ESCRITORIO"));
                
                Cliente cCliente = new Cliente();
                cCliente.setNome(row.getString("NOME_BUSCADO"));
                cEscritorio.setCliente(cCliente);
                cProcesso.setEscritorio(cEscritorio);
                
                ListaProcessos.add(cProcesso);
            }

            DAL.desconectar();
            return ListaProcessos;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro ao buscar os processos", this.getClass().getName(), ex.toString());
        }
        
        return ListaProcessos;
    }
    
    /**
     * Função para buscar os processos
     * @param dataBusca
     * @return Processo
     */
    public List<Processo> buscar(Date dataBusca)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        
        List<Processo> ListaProcessos = buscar(where);
        return ListaProcessos;
    }
    
    /**
     * Função para buscar os processos
     * @param dataBusca
     * @param cTribunal
     * @return Processo
     */
    public List<Processo> buscar(Date dataBusca, Tribunal cTribunal)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        where.add("P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'");
        
        List<Processo> ListaProcessos = buscar(where);
        return ListaProcessos;
    }
    
    /**
     * Função para buscar um Processo pelo seu numero de cadastro
     * @param numProcesso
     * @return Processo
     */
    public Processo buscar(int numProcesso)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.NUM = " + numProcesso);
        
        List<Processo> ListaProcessos = buscar(where);
        return ListaProcessos.get(0);
    }
    
    /**
     * Função para buscar os processos
     * @param dataBusca
     * @param trechoBusca
     * @return Processo
     */
    public List<Processo> buscar(Date dataBusca, String trechoBusca)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        where.add("P2.PUBLICACAO LIKE '%" + trechoBusca + "%'");
        
        List<Processo> ListaProcessos = buscar(where);
        return ListaProcessos;
    }
    
    /**
     * Função para buscar os processos
     * @param dataBusca
     * @param cTribunal
     * @param trechoBusca
     * @return Processo
     */
    public List<Processo> buscar(Date dataBusca, Tribunal cTribunal, String trechoBusca)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        where.add("P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'");
        where.add("P2.PUBLICACAO LIKE '%" + trechoBusca + "%'");
        
        List<Processo> ListaProcessos = buscar(where);
        return ListaProcessos;
    }
    
    /**
     * Função para atualizar um processo
     * @param cProcesso 
     */
    public void atualizar(Processo cProcesso)
    {
        String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
        
        RecorteDAL DAL = new RecorteDAL();
        DAL.setRecorte(cRecorte);
        
        String sqlProc1 = "UPDATE " + tabelaEstado
                        + "   SET VARA = '" + cProcesso.getVara() + "'"
                        + " WHERE NUM = " + cProcesso.getNumProcesso();
        
        DAL.executarQuery(sqlProc1);
        
        String sqlProc2 = "UPDATE " + tabelaEstado + "2"
                        + "   SET PUBLICACAO = '" + cProcesso.getCorpoPublicacao() + "'"
                        + " WHERE NUM2 = " + cProcesso.getNumProcesso();
        
        DAL.executarQuery(sqlProc2);
        DAL.desconectar();
    }
    
    /**
     * Função para cadastra um processo
     * @param cProcesso 
     * @return Número de cadastro do processo.
     */
    public int cadastrar(Processo cProcesso)
    {
        try
        {
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();

            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);

            String sqlBuscaIDRegistro = "SELECT CAST(MAX(NUM) AS INT) AS PROXIMO_ID"
                                      + "  FROM PROC_ACRE";
            ResultSet rowRegistro = DAL.executarSelectQuery(sqlBuscaIDRegistro);
            rowRegistro.next();
            int id = rowRegistro.getInt("PROXIMO_ID") + 1;

            String sql1 = "INSERT INTO " + tabelaEstado + " (NUM,"
                        + "                                  DATA,"
                        + "                                  CODIGO,"
                        + "                                  NOME,"
                        + "                                  VARA,"
                        + "                                  TRIBUNAL,"
                        + "                                  ARQUIVO,"
                        + "                                  ORDEM,"
                        + "                                  N_PROCESSO)" 
                        + "                          VALUES ( " + id + ","
                        + "                                  '" + new SimpleDateFormat("yyyy-MM-dd").format(cProcesso.getDataVista()) + "',"
                        + "                                   " + cProcesso.getEscritorio().getCodigo() + ","
                        + "                                  '" + cProcesso.getEscritorio().getCliente().getNome() + "',"
                        + "                                  '" + cProcesso.getVara() + "',"
                        + "                                  '" + cProcesso.getTribunal().getNomeTribunal() + "',"
                        + "                                  '" + cProcesso.getArquivo() + "',"
                        + "                                   "  + cProcesso.getOrdem() + ","
                        + "                                  '" + cProcesso.getNumeroProcesso() + "')";

            DAL.executarQuery(sql1);

            String sql2 = "INSERT INTO " + tabelaEstado + "2 (NUM2,"
                        + "                                   PUBLICACAO)"
                        + "                           VALUES ( " + id + ","
                        + "                                   '" + cProcesso.getCorpoPublicacao() + "')";
            
            DAL.executarQuery(sql2);
            DAL.desconectar();
            
            return id;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro ao cadastrar os processos", this.getClass().getName(), ex.toString());
        }
        
        return 0;
    }
    
    /**
     * Função para mudar a bandeira de status
     * @param numProcesso
     * @param status 
     */
    public void marcarRevisao(int numProcesso, boolean status)
    {
        String dataHoraAtual = new SimpleDateFormat("yyyy/MM/dd H:m:s").format(new Date()) + ".000";
        System.out.println(dataHoraAtual);
        String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();

        RecorteDAL DAL = new RecorteDAL();
        DAL.setRecorte(cRecorte);
        
        int numStatus = 0;
        if (status)
        {
            numStatus = 1;
        }
        
        String sql = "UPDATE " + tabelaEstado
                   + "   SET Revisado = " + numStatus + ","
                   + "       DATA_PROCESSADO = '" + dataHoraAtual + "'"
                   + " WHERE NUM = " + numProcesso;
        
        DAL.executarQuery(sql);
        DAL.desconectar();
    }
    
    /**
     * Função para mudar a bandeira de status
     * @param numProcesso
     * @param status 
     */
    public void marcarRevisao(Integer[] numProcesso, boolean status)
    {
        String dataHoraAtual = new SimpleDateFormat("yyyy/MM/dd H:m:s").format(new Date()) + ".000";
        System.out.println(dataHoraAtual);
        
        if (cTelaPrincipal != null)
        {
            if (status)
            {
                cTelaPrincipal.barraProgressoOperacaoDemorada.setString("Marcando publicações como revisadas...");
            }
            else
            {
                cTelaPrincipal.barraProgressoOperacaoDemorada.setString("Marcando publicações não revisadas...");
            }
            
            cTelaPrincipal.barraProgressoOperacaoDemorada.setMaximum(numProcesso.length);
            cTelaPrincipal.barraProgressoOperacaoDemorada.setValue(0);
            cTelaPrincipal.barraProgressoOperacaoDemorada.setVisible(true);
            cTelaPrincipal.repaint();
        }
        
        String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();

        RecorteDAL DAL = new RecorteDAL();
        DAL.setRecorte(cRecorte);
        
        int numStatus = 0;
        if (status)
        {
            numStatus = 1;
        }
        
        List<String> numeros = new ArrayList<>();
        
        for (Integer num : numProcesso)
        {
            numeros.add(num.toString());
        }
        
        int buffer = 100;
        Integer[][] lotes = new Integer[(numProcesso.length / buffer) + 1][buffer];
        
        int indiceLote = 0;
        int indiceBuffer = 0;
        for (int i = 1; i <= numProcesso.length; i++)
        {
            lotes[indiceLote][indiceBuffer] = numProcesso[i - 1];
            indiceBuffer++;
            
            if (cTelaPrincipal != null)
            {
                cTelaPrincipal.barraProgressoOperacaoDemorada.setValue(i);
                cTelaPrincipal.repaint();
            }
            
            if ((i % buffer == 0) || (i == numProcesso.length))
            {
                indiceBuffer = 0;
                indiceLote++;
            }
        }
        
        // ~gambiaaa necessaria - arrumar quando ter tempo
        for (int i = 0; i < lotes.length; i++)
        {
            for (int j = 0; j < lotes[i].length; j++)
            {
                if (lotes[i][j] == null)
                {
                    lotes[i][j] = 0;
                }
            }
        }
        // fim da ~gambia necessaria
        
        if (cTelaPrincipal != null)
        {
            cTelaPrincipal.barraProgressoOperacaoDemorada.setMaximum(lotes.length);
            cTelaPrincipal.barraProgressoOperacaoDemorada.setValue(0);
            cTelaPrincipal.barraProgressoOperacaoDemorada.setVisible(true);
            cTelaPrincipal.repaint();
        }
        
        for (int i = 0; i < lotes.length; i++)
        {
            Integer[] lote = lotes[i];
            
            if (cTelaPrincipal != null)
            {
                cTelaPrincipal.barraProgressoOperacaoDemorada.setValue(i);
                cTelaPrincipal.repaint();
            }
            
            String sql = "UPDATE " + tabelaEstado
                       + "   SET Revisado = " + numStatus + ","
                       + "       DATA_PROCESSADO = '" + dataHoraAtual + "'"
                       + " WHERE NUM IN (" + StringUtil.join(Arrays.asList(lote), ", ") + ")";
            
            DAL.executarQuery(sql);
        }
        
        DAL.desconectar();
        
        if (cTelaPrincipal != null)
        {
            cTelaPrincipal.barraProgressoOperacaoDemorada.setString("Finalizado");
            cTelaPrincipal.barraProgressoOperacaoDemorada.setValue(cTelaPrincipal.barraProgressoOperacaoDemorada.getMaximum());
            cTelaPrincipal.barraProgressoOperacaoDemorada.setVisible(false);
            cTelaPrincipal.repaint();
        }
    }
}