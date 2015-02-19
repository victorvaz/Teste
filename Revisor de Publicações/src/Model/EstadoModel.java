package Model;

import Core.Excecao.Excecao;
import DAL.ClienteDAL;
import Entity.Estado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo da entidade Estado.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class EstadoModel
{
    /**
     * Função para buscar todos os estados.
     * @return Lista de Estados.
     */
    public List<Estado> buscar()
    {
        try
        {
            ClienteDAL DAL = new ClienteDAL();

            String sql = "SELECT CodEstado,"
                       + "       Sigla,"
                       + "       Nome"
                       + "  FROM db_vista_recorte.dbo.estadoextended";

            ResultSet row = DAL.executarSelectQuery(sql);

            List<Estado> ListaEstados = new ArrayList<>();

            while(row.next())
            {
                Estado EstadoAtual = new Estado();

                EstadoAtual.setCodEstado(row.getInt("CodEstado"));
                EstadoAtual.setSigla(row.getString("Sigla"));
                EstadoAtual.setNome(row.getString("Nome"));
                ListaEstados.add(EstadoAtual);
            }
            DAL.desconectar();
            return ListaEstados;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro na busca dos estados", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
    
    /**
     * Função para buscar o estado de um diário através de sua sigla.
     * @param sigla Sigla do diário.
     * @return Estado correspondente à sigla
     */
    public Estado buscar(String sigla)
    {
        try
        {
            ClienteDAL DAL = new ClienteDAL();

            String sql = "SELECT CodEstado,"
                       + "       Sigla,"
                       + "       Nome"
                       + "  FROM Estado"
                       + " WHERE Sigla = '" + sigla + "'";

            ResultSet row = DAL.executarSelectQuery(sql);
            row.next();
            
            Estado EstadoAtual = new Estado();
            EstadoAtual.setCodEstado(row.getInt("CodEstado"));
            EstadoAtual.setSigla(row.getString("Sigla"));
            EstadoAtual.setNome(row.getString("Nome"));
            
            DAL.desconectar();
            
            return EstadoAtual;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro na busca do estado do " + sigla, this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
    
    /**
     * Função para buscar o estado de um diário através de seu código
     * @param CodEstado Código do Estado
     * @return Estado correspondente ao código
     */
    public Estado buscar(int CodEstado)
    {
        try
        {
            ClienteDAL DAL = new ClienteDAL();

            String sql = "SELECT CodEstado,"
                       + "       Sigla,"
                       + "       Nome"
                       + "  FROM Estado"
                       + " WHERE CodEstado = '" + CodEstado + "'";

            ResultSet row = DAL.executarSelectQuery(sql);
            row.next();
            
            Estado EstadoAtual = new Estado();
            EstadoAtual.setCodEstado(row.getInt("CodEstado"));
            EstadoAtual.setSigla(row.getString("Sigla"));
            EstadoAtual.setNome(row.getString("Nome"));
            
            DAL.desconectar();
            
            return EstadoAtual;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro na busca do estado de código = " + CodEstado, this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
    
    /**
     * Função para retornar o nome de Estado através de seu código.
     * @param codigo Código do estado.
     * @return String Nome do estado.
     */
    public String buscarNomePorCodigo(int codigo)
    {
        try
        {
            ClienteDAL DAL = new ClienteDAL();
            
            String sql = "SELECT Nome"
                       + "  FROM Estado"
                       + " WHERE CodEstado = " + codigo;

            ResultSet row = DAL.executarSelectQuery(sql);
            
            row.next();
            String nome = row.getString("Nome");
            DAL.desconectar();
            
            return nome;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro na busca dos estados", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
}
