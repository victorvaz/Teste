package Model;

import Core.Charset.Charset;
import DAL.VistaDAL;
import Entity.Estado;
import Entity.Recorte;
import Entity.Tribunal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo da entidade Tribunal
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class TribunalModel implements Model<Tribunal, Integer>
{
    private final Recorte Recorte;

    public TribunalModel(Recorte Recorte)
    {
        this.Recorte = Recorte;
    }

    @Override
    public void cadastrar(Tribunal e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Tribunal e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(Tribunal e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tribunal buscar(Integer objetoIdentificador) throws SQLException, ClassNotFoundException 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Função para buscar tribunais
     * @return Lista de Tribunais
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Tribunal> buscarTodos() throws SQLException, ClassNotFoundException
    {
        List<Tribunal> ListaTribunais = new ArrayList<>();
        VistaDAL DAL = new VistaDAL();

        String sql = "SELECT ID,"
                   + "       SIGLA,"
                   + "       TRIBUNAL,"
                   + "       ESTADO"
                   + "  FROM DIARIO_OFICIAL_TRIBUNAIS"
                   + " WHERE CLIENTE = '" + Recorte.getNomeRecorte() + "'";

        ResultSet row = DAL.executarSelectQuery(sql);

        while (row.next())
        {
            Tribunal cTribunal = new Tribunal();
            cTribunal.setID(row.getInt("ID"));
            cTribunal.setSigla(row.getString("SIGLA"));
            cTribunal.setNomeTribunal(row.getString("TRIBUNAL"));

            Estado cEstado = new Estado();
            cEstado.setNome("ESTADO");
            cTribunal.setEstado(cEstado);

            ListaTribunais.add(cTribunal);
        }

        DAL.desconectar();

        return ListaTribunais;
    }
    
    /**
     * Função para buscar tribunais por estado
     * @param cEstado Estado
     * @return Lista de Tribunais
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public List<Tribunal> buscarPorEstado(Estado cEstado) throws SQLException, ClassNotFoundException
    {
        List<Tribunal> ListaTribunais = new ArrayList<>();
        VistaDAL DAL = new VistaDAL();

        String sql = "SELECT ID,"
                   + "       SIGLA,"
                   + "       TRIBUNAL,"
                   + "       ESTADO"
                   + "  FROM DIARIO_OFICIAL_TRIBUNAIS"
                   + " WHERE CLIENTE = '" + Recorte.getNomeRecorte() + "'"
                   + "   AND ESTADO  = '" + Charset.removeAcentos(cEstado.getNome().toUpperCase()) + "'";

        ResultSet row = DAL.executarSelectQuery(sql);

        while (row.next())
        {
            Tribunal cTribunal = new Tribunal();
            cTribunal.setID(row.getInt("ID"));
            cTribunal.setSigla(row.getString("SIGLA"));
            cTribunal.setNomeTribunal(row.getString("TRIBUNAL"));

            Estado cEstadoAtual = new Estado();
            cEstado.setNome(row.getString("ESTADO"));
            cTribunal.setEstado(cEstadoAtual);

            ListaTribunais.add(cTribunal);
        }

        DAL.desconectar();

        return ListaTribunais;
    }
    
    /**
     * Função para buscar tribunais por estado
     * @param nomeTribunal Nome do estado
     * @return Tribunais
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Tribunal buscarPorNome(String nomeTribunal) throws SQLException, ClassNotFoundException
    {
        VistaDAL DAL = new VistaDAL();

        String sql = "SELECT ID,"
                   + "       SIGLA,"
                   + "       TRIBUNAL,"
                   + "       ESTADO"
                   + "  FROM DIARIO_OFICIAL_TRIBUNAIS"
                   + " WHERE CLIENTE = '" + Recorte.getNomeRecorte() + "'"
                   + "   AND TRIBUNAL = '" + Charset.removeAcentos(nomeTribunal.toUpperCase()) + "'";

        ResultSet row = DAL.executarSelectQuery(sql);
        row.next();

        Tribunal cTribunal = new Tribunal();
        cTribunal.setID(row.getInt("ID"));
        cTribunal.setSigla(row.getString("SIGLA"));
        cTribunal.setNomeTribunal(row.getString("TRIBUNAL"));

        Estado cEstado = new Estado();
        cEstado.setNome("ESTADO");
        cTribunal.setEstado(cEstado);

        DAL.desconectar();

        return cTribunal;
    }
}