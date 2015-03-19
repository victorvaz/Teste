package Model;

import DAL.VistaDAL;
import Entity.Recorte;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo para a entidade Recorte
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class RecorteModel implements Model<Recorte, Integer>
{
    @Override
    public void cadastrar(Recorte e)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void atualizar(Recorte e) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deletar(Recorte e)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Recorte buscar(Integer identificador)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Função para buscar os recortes
     * @return Lista de Recortes
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Override
    public List<Recorte> buscarTodos() throws SQLException, ClassNotFoundException
    {
        List<Recorte> ListaRecortes = new ArrayList<>();

        VistaDAL DAL = new VistaDAL();

        String sql = "  SELECT CLIENTE"
                   + "    FROM DIARIO_OFICIAL_CLIENTE_CONFIG"
                   + "   WHERE STATUS IN ('ATIVO', 'BLOQUEADO', 'CORTESIA')"
                   + "     AND CLIENTE = 'PROMAD'"
                   + "ORDER BY CLIENTE ASC";

        ResultSet row = DAL.executarSelectQuery(sql);

        while (row.next())
        {
            Recorte cRecorte = new Recorte();
            cRecorte.setNomeRecorte(row.getString("CLIENTE"));
            ListaRecortes.add(cRecorte);
        }

        return ListaRecortes;
    }
}