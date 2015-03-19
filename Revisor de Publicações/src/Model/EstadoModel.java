package Model;

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
public class EstadoModel implements Model<Estado, Integer>
{
    @Override
    public void cadastrar(Estado e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Estado e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(Estado e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Função para buscar o estado de um diário através de seu código
     * @param CodEstado Código do Estado
     * @return Estado correspondente ao código
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public Estado buscar(Integer CodEstado) throws SQLException, ClassNotFoundException
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
    
    /**
     * Função para buscar o estado de um diário através de sua sigla.
     * @param sigla Sigla do diário.
     * @return Estado correspondente à sigla
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Estado buscar(String sigla) throws SQLException, ClassNotFoundException
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

    /**
     * Função para buscar todos os estados.
     * @return Lista de Estados.
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Estado> buscarTodos() throws SQLException, ClassNotFoundException
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
}
