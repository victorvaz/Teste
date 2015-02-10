package Model;

import Core.Excecao.Excecao;
import DAL.RecorteDAL;
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
     * Função para buscar os processos
     * @param cRecorte
     * @param cEstado
     * @param dataBusca
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado, Date dataBusca)
    {
        try
        {
            List<Processo> ListaProcessos = new ArrayList<>();
            
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
            
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);            

            String sql = "     SELECT P1.NUM      AS NUM_PUBLICACAO,"
                       + "            P1.DATA     AS DATA_PUBLICACAO,"
                       + "            P1.TRIBUNAL AS TRIBUNAL,"
                       + "            P1.NOME     AS NOME_BUSCADO,"
                       + "            P1.CODIGO   AS CODIGO_ESCRITORIO,"
                       + "            E.NOME      AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + " P1"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO"
                       + "      WHERE P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'"
                       + "   ORDER BY P1.TRIBUNAL, P1.NUM";

            ResultSet row = DAL.executarSelectQuery(sql);

            while (row.next())
            {
                Processo cProcesso = new Processo();
                cProcesso.setNumProcesso(row.getInt("NUM_PUBLICACAO"));
                cProcesso.setDataPublicacao(row.getDate("DATA_PUBLICACAO"));
                
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
        
        return null;
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
        try
        {
            List<Processo> ListaProcessos = new ArrayList<>();
            
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
            
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);            

            String sql = "     SELECT P1.NUM      AS NUM_PUBLICACAO,"
                       + "            P1.DATA     AS DATA_PUBLICACAO,"
                       + "            P1.TRIBUNAL AS TRIBUNAL,"
                       + "            P1.NOME     AS NOME_BUSCADO,"
                       + "            P1.CODIGO   AS CODIGO_ESCRITORIO,"
                       + "            E.NOME      AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + " P1"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO"
                       + "      WHERE P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'"
                       + "        AND P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'"
                       + "   ORDER BY P1.TRIBUNAL, P1.NUM";

            ResultSet row = DAL.executarSelectQuery(sql);

            while (row.next())
            {
                Processo cProcesso = new Processo();
                cProcesso.setNumProcesso(row.getInt("NUM_PUBLICACAO"));
                cProcesso.setDataPublicacao(row.getDate("DATA_PUBLICACAO"));
                
                // Pega o tribunal correspondente:
                String nomeTribunal = row.getString("TRIBUNAL");
                for (Tribunal cTribunalLista : ListaTribunais)
                {
                    if (cTribunalLista.getNomeTribunal().equals(nomeTribunal))
                    {
                        cProcesso.setTribunal(cTribunalLista);
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
        
        return null;
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
        try
        {
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);

            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);

            String sql = "     SELECT P1.NUM        AS NUM_PUBLICACAO,"
                       + "            P1.N_PROCESSO AS NUMERO_PROCESSO,"
                       + "            P1.ARQUIVO    AS ARQUIVO,"
                       + "            P1.DATA       AS DATA_PUBLICACAO,"
                       + "            P1.TRIBUNAL   AS TRIBUNAL,"
                       + "            P1.NOME       AS NOME_BUSCADO,"
                       + "            P1.VARA       AS VARA,"
                       + "            P1.ORDEM      AS ORDEM,"
                       + "            P1.CODIGO     AS CODIGO_ESCRITORIO,"
                       + "            P2.PUBLICACAO AS CORPO_PUBLICACAO,"
                       + "            E.NOME        AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + "  P1"
                       + " INNER JOIN " + tabelaEstado + "2 P2"
                       + "         ON P1.NUM = P2.NUM2"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO"
                       + "      WHERE P1.NUM = " + numProcesso;

            ResultSet row = DAL.executarSelectQuery(sql);
            
            row.next();
            
            Processo cProcesso = new Processo();
            cProcesso.setNumProcesso(row.getInt("NUM_PUBLICACAO"));
            cProcesso.setNumeroProcesso(row.getString("NUMERO_PROCESSO"));
            cProcesso.setArquivo(row.getString("ARQUIVO"));
            cProcesso.setDataPublicacao(row.getDate("DATA_PUBLICACAO"));
            cProcesso.setVara(row.getString("VARA"));
            cProcesso.setOrdem(row.getInt("ORDEM"));
            cProcesso.setCorpoPublicacao(row.getString("CORPO_PUBLICACAO"));

            // Pega o tribunal correspondente:
            String nomeTribunal = row.getString("TRIBUNAL");
            for (Tribunal cTribunalLista : ListaTribunais)
            {
                if (cTribunalLista.getNomeTribunal().equals(nomeTribunal))
                {
                    cProcesso.setTribunal(cTribunalLista);
                }
            }

            // Escritório:
            Escritorio cEscritorio = new Escritorio();
            cEscritorio.setCodigo(Integer.parseInt(row.getString("CODIGO_ESCRITORIO")));
            cEscritorio.setNome(row.getString("NOME_ESCRITORIO"));

            Cliente cCliente = new Cliente();
            cCliente.setNome(row.getString("NOME_BUSCADO"));
            cEscritorio.setCliente(cCliente);
            cProcesso.setEscritorio(cEscritorio);
            
            DAL.desconectar();
            return cProcesso;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro ao buscar os processos", this.getClass().getName(), ex.toString());
        }
        
        return null;
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
        try
        {
            List<Processo> ListaProcessos = new ArrayList<>();
            
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
            
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);            

            String sql = "     SELECT P1.NUM      AS NUM_PUBLICACAO,"
                       + "            P1.DATA     AS DATA_PUBLICACAO,"
                       + "            P1.CODIGO   AS CODIGO_ESCRITORIO,"
                       + "            P1.TRIBUNAL AS TRIBUNAL,"
                       + "            P1.NOME     AS NOME_BUSCADO,"
                       + "            E.NOME      AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + " P1"
                       + " INNER JOIN " + tabelaEstado + "2 P2"
                       + "         ON P1.NUM = P2.NUM2"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO"
                       + "      WHERE P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'"
                       + "        AND P2.PUBLICACAO LIKE '%" + trechoBusca + "%'"
                       + "   ORDER BY P1.TRIBUNAL, P1.NUM";

            ResultSet row = DAL.executarSelectQuery(sql);

            while (row.next())
            {
                Processo cProcesso = new Processo();
                cProcesso.setNumProcesso(row.getInt("NUM_PUBLICACAO"));
                cProcesso.setDataPublicacao(row.getDate("DATA_PUBLICACAO"));
                
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
        
        return null;
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
        try
        {
            List<Processo> ListaProcessos = new ArrayList<>();
            
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
            
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);            

            String sql = "     SELECT P1.NUM      AS NUM_PUBLICACAO,"
                       + "            P1.DATA     AS DATA_PUBLICACAO,"
                       + "            P1.TRIBUNAL AS TRIBUNAL,"
                       + "            P1.NOME     AS NOME_BUSCADO,"
                       + "            P1.CODIGO   AS CODIGO_ESCRITORIO,"
                       + "            E.NOME      AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + " P1"
                       + " INNER JOIN " + tabelaEstado + "2 P2"
                       + "         ON P1.NUM = P2.NUM2"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO"
                       + "      WHERE P1.DATA = '" + (new SimpleDateFormat("yyyy-MM-dd").format(dataBusca)) + "'"
                       + "        AND P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'"
                       + "        AND P2.PUBLICACAO LIKE '%" + trechoBusca + "%'"
                       + "   ORDER BY P1.TRIBUNAL, P1.NUM";

            ResultSet row = DAL.executarSelectQuery(sql);

            while (row.next())
            {
                Processo cProcesso = new Processo();
                cProcesso.setNumProcesso(row.getInt("NUM_PUBLICACAO"));
                cProcesso.setDataPublicacao(row.getDate("DATA_PUBLICACAO"));
                
                // Pega o tribunal correspondente:
                String nomeTribunal = row.getString("TRIBUNAL");
                for (Tribunal cTribunalLista : ListaTribunais)
                {
                    if (cTribunalLista.getNomeTribunal().equals(nomeTribunal))
                    {
                        cProcesso.setTribunal(cTribunalLista);
                    }
                }
                
                // Escritório:
                Escritorio cEscritorio = new Escritorio();
                cEscritorio.setNome(row.getString("NOME_ESCRITORIO"));
                
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
        
        return null;
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
                        + "                                  '" + new SimpleDateFormat("yyyy-MM-dd").format(cProcesso.getDataPublicacao()) + "',"
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