package Model;

import DAL.RecorteDAL;
import Entity.Escritorio;
import Entity.Recorte;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo da entidade Escritório
 * @author Víctor Vaz <victor.vaz@vistaes.com.br>
 */
public class EscritorioModel implements Model<Escritorio, Integer>
{
    private final Recorte Recorte;

    public EscritorioModel(Recorte Recorte)
    {
        this.Recorte = Recorte;
    }
    
    @Override
    public void cadastrar(Escritorio e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Escritorio e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(Escritorio e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Escritorio buscar(Integer objetoIdentificador) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Função para buscar um determinado escritório
     * @return Lista dos escritórios do recorte.
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Escritorio> buscarTodos() throws SQLException, ClassNotFoundException
    {
        List<Escritorio> ListaEscritorios = new ArrayList<>();

        RecorteDAL DAL = new RecorteDAL();
        DAL.setRecorte(Recorte);

        String sql = "SELECT CODIGO,"
                   + "       NOME"
                   + "  FROM ESCRITORIO";

        ResultSet row = DAL.executarSelectQuery(sql);

        while (row.next())
        {
            Escritorio cEscritorio = new Escritorio();
            cEscritorio.setCodigo(row.getInt("CODIGO"));
            cEscritorio.setNome(row.getString("NOME"));

            ListaEscritorios.add(cEscritorio);
        }

        DAL.desconectar();

        return ListaEscritorios;
    }
}