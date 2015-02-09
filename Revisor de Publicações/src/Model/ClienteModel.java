package Model;

import Core.Excecao.Excecao;
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
public class ClienteModel
{
    /**
     * Função para buscar os clientes de um escritório
     * @param cRecorte
     * @param cEscritorio
     * @return Lista de Clientes.
     */
    public List<Cliente> buscar(Recorte cRecorte, Escritorio cEscritorio)
    {
        try
        {
            List<Cliente> ListaClientes = new ArrayList<>();

            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);

            String sql = "SELECT NUM,"
                       + "       NOME"
                       + "  FROM CLIENTE"
                       + " WHERE CODIGO = " + cEscritorio.getCodigo();

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
        catch (SQLException ex)
        {
            new Excecao("Erro ao buscar os processos", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
}