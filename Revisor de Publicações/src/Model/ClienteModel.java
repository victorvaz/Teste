package Model;

import DAL.RecorteDAL;
import Entity.Cliente;
import Entity.Escritorio;
import Entity.Recorte;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo da entidade Cliente
 * @author Victor Vaz <victor.vaz@vistaes.com.br>
 */
public class ClienteModel implements Model<Cliente, Integer>
{
    private final Recorte Recorte;
    private final Escritorio Escritorio;
    
    public ClienteModel(Recorte Recorte, Escritorio Escritorio)
    {
        this.Recorte = Recorte;
        this.Escritorio = Escritorio;
    }

    @Override
    public void cadastrar(Cliente e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Cliente e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(Cliente e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Cliente buscar(Integer objetoIdentificador) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Função para buscar os clientes de um escritório
     * @return Lista de Clientes.
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    @Override
    public List<Cliente> buscarTodos() throws SQLException, ClassNotFoundException
    {
        List<Cliente> ListaClientes = new ArrayList<>();

        RecorteDAL DAL = new RecorteDAL();
        DAL.setRecorte(Recorte);

        String sql = "SELECT NUM,"
                   + "       NOME"
                   + "  FROM CLIENTE"
                   + " WHERE CODIGO = " + Escritorio.getCodigo();

        ResultSet row = DAL.executarSelectQuery(sql);

        while (row.next())
        {
            Cliente cCliente = new Cliente();
            cCliente.setNum(row.getInt("NUM"));
            cCliente.setNome(row.getString("NOME"));

            ListaClientes.add(cCliente);
        }

        DAL.desconectar();

        return ListaClientes;
    }
}