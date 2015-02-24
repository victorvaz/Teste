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
import java.util.Date;
import java.util.List;

/**
 * Classe modelo para a entidade processo
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class ProcessoModel
{
    // Classes modelo
    private final TabelaEstadoModel cTabelaEstadoModel;
    private final TribunalModel     cTribunalModel;

    /**
     * Construtor da classe
     */
    public ProcessoModel()
    {
        // Inicia as classes de modelo:
        this.cTabelaEstadoModel = new TabelaEstadoModel();
        this.cTribunalModel     = new TribunalModel();
    }
    
    /**
     * Função para buscar processos de forma geral. É uma busca centralizada: todas os outros tipos de busca apontam para esta função.
     * @param cRecorte Recorte no qual buscaremos as publicações.
     * @param cEstado  Estado das publicações.
     * @param where    Condições para a busca.
     * @return Lista de Processos.
     */
    private List<Processo> buscar(Recorte cRecorte, Estado cEstado, List<String> where)
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
     * @param cRecorte
     * @param cEstado
     * @param dataBusca
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado, Date dataBusca)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        
        List<Processo> ListaProcessos = buscar(cRecorte, cEstado, where);
        return ListaProcessos;
    }
    
    /**
     * Função para buscar os processos
     * @param cRecorte
     * @param cEstado
     * @param dataBusca
     * @param cTribunal
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado, Date dataBusca, Tribunal cTribunal)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        where.add("P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'");
        
        List<Processo> ListaProcessos = buscar(cRecorte, cEstado, where);
        return ListaProcessos;
    }
    
    /**
     * Função para buscar um Processo pelo seu numero de cadastro
     * @param cRecorte
     * @param cEstado
     * @param numProcesso
     * @return Processo
     */
    public Processo buscar(Recorte cRecorte, Estado cEstado, int numProcesso)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.NUM = " + numProcesso);
        
        List<Processo> ListaProcessos = buscar(cRecorte, cEstado, where);
        return ListaProcessos.get(0);
    }
    
    /**
     * Função para buscar os processos
     * @param cRecorte
     * @param cEstado
     * @param dataBusca
     * @param trechoBusca
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado, Date dataBusca, String trechoBusca)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        where.add("P2.PUBLICACAO LIKE '%" + trechoBusca + "%'");
        
        List<Processo> ListaProcessos = buscar(cRecorte, cEstado, where);
        return ListaProcessos;
    }
    
    /**
     * Função para buscar os processos
     * @param cRecorte
     * @param cEstado
     * @param dataBusca
     * @param cTribunal
     * @param trechoBusca
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado, Date dataBusca, Tribunal cTribunal, String trechoBusca)
    {
        List<String> where = new ArrayList<>();
        where.add("P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'");
        where.add("P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'");
        where.add("P2.PUBLICACAO LIKE '%" + trechoBusca + "%'");
        
        List<Processo> ListaProcessos = buscar(cRecorte, cEstado, where);
        return ListaProcessos;
    }
    
    /**
     * Função para atualizar um processo
     * @param cRecorte
     * @param cEstado
     * @param cProcesso 
     */
    public void atualizar(Recorte cRecorte, Estado cEstado, Processo cProcesso)
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
     * @param cRecorte
     * @param cEstado
     * @param cProcesso 
     * @return Número de cadastro do processo.
     */
    public int cadastrar(Recorte cRecorte, Estado cEstado, Processo cProcesso)
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
}