package Model;

import Core.Charset;
import DAL.VistaDAL;
import Entity.Estado;
import Entity.Recorte;
import Entity.TabelaEstado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe modelo da entidade TabelaEstado
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class TabelaEstadoModel implements Model<TabelaEstado, Estado>
{
    private final Recorte Recorte;
    
    public TabelaEstadoModel(Recorte Recorte)
    {
        this.Recorte = Recorte;
    }
    
    @Override
    public void cadastrar(TabelaEstado e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(TabelaEstado e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(TabelaEstado e) throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Função para buscar a tabela referente ao estado e ao recorte
     * @param cEstado Estado
     * @return TabelaEstado
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public TabelaEstado buscar(Estado cEstado) throws SQLException, ClassNotFoundException
    {
        VistaDAL DAL = new VistaDAL();

        String sql = "SELECT TABELA"
                   + "  FROM DIARIO_OFICIAL_ESTADOS"
                   + " WHERE CLIENTE = '" + Recorte.getNomeRecorte() + "'"
                   + "   AND ESTADO = '" + Charset.removeAcentos(cEstado.getNome().toUpperCase()) + "'";

        ResultSet row = DAL.executarSelectQuery(sql);
        row.next();

        TabelaEstado cTabelaEstado = new TabelaEstado();
        cTabelaEstado.setNomeTabela(row.getString("TABELA"));

        DAL.desconectar();
        return cTabelaEstado;
    }

    @Override
    public List<TabelaEstado> buscarTodos() throws SQLException, ClassNotFoundException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}