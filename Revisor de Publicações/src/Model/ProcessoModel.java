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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe modelo para a entidade processo
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class ProcessoModel
{
    // Classes modelo
    private TabelaEstadoModel cTabelaEstadoModel;
    private TribunalModel     cTribunalModel;

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
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado)
    {
        try
        {
            List<Processo> ListaProcessos = new ArrayList<>();
            
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
            
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);            

            String sql = "     SELECT P1.DATA     AS DATA_PUBLICACAO,"
                       + "            P1.TRIBUNAL AS TRIBUNAL,"
                       + "            P1.NOME     AS NOME_BUSCADO,"
                       + "            E.NOME      AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + " P1"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO";

            ResultSet row = DAL.executarSelectQuery(sql);

            while (row.next())
            {
                Processo cProcesso = new Processo();
                
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
     * @param cTribunal
     * @return Processo
     */
    public List<Processo> buscar(Recorte cRecorte, Estado cEstado, Tribunal cTribunal)
    {
        try
        {
            List<Processo> ListaProcessos = new ArrayList<>();
            
            String tabelaEstado = cTabelaEstadoModel.buscarTabelaPorEstado(cRecorte, cEstado).getNomeTabela();
            
            List<Tribunal> ListaTribunais = cTribunalModel.buscarPorEstado(cRecorte, cEstado);
            
            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);            

            String sql = "     SELECT P1.DATA     AS DATA_PUBLICACAO,"
                       + "            P1.TRIBUNAL AS TRIBUNAL,"
                       + "            P1.NOME     AS NOME_BUSCADO,"
                       + "            E.NOME      AS NOME_ESCRITORIO"
                       + "       FROM " + tabelaEstado + " P1"
                       + " INNER JOIN ESCRITORIO E"
                       + "         ON E.CODIGO = P1.CODIGO"
                       + "      WHERE P1.TRIBUNAL = '" + cTribunal.getNomeTribunal() + "'";

            ResultSet row = DAL.executarSelectQuery(sql);

            while (row.next())
            {
                Processo cProcesso = new Processo();
                
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
}
