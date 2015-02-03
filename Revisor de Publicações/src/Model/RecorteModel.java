package Model;

import Core.Excecao.Excecao;
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
public class RecorteModel
{
    /**
     * Função para buscar os recortes
     * @return Lista de Recortes
     */
    public List<Recorte> buscar()
    {
        try
        {
            List<Recorte> ListaRecortes = new ArrayList<>();
            
            VistaDAL DAL = new VistaDAL();

            String sql = "  SELECT CLIENTE"
                       + "    FROM DIARIO_OFICIAL_CLIENTE_CONFIG"
                       + "   WHERE STATUS IN ('ATIVO', 'BLOQUEADO', 'CORTESIA')"
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
        catch (SQLException ex)
        {
            new Excecao("Erro na busca dos recorte", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
}