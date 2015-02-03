package Model;

import Core.Charset.Charset;
import Core.Excecao.Excecao;
import DAL.VistaDAL;
import Entity.Estado;
import Entity.Recorte;
import Entity.TabelaEstado;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe modelo da entidade TabelaEstado
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class TabelaEstadoModel
{
    /**
     * Função para buscar a tabela referente ao estado e ao recorte
     * @param cRecorte Recorte
     * @param cEstado  Estado
     * @return TabelaEstado
     */
    public TabelaEstado buscarTabelaPorEstado(Recorte cRecorte, Estado cEstado)
    {
        try
        {
            VistaDAL DAL = new VistaDAL();

            String sql = "SELECT TABELA"
                       + "  FROM DIARIO_OFICIAL_ESTADOS"
                       + " WHERE CLIENTE = '" + cRecorte.getNomeRecorte() + "'"
                       + "   AND ESTADO = '" + Charset.removeAcentos(cEstado.getNome().toUpperCase()) + "'";

            ResultSet row = DAL.executarSelectQuery(sql);
            row.next();
            
            TabelaEstado cTabelaEstado = new TabelaEstado();
            cTabelaEstado.setNomeTabela(row.getString("TABELA"));
            
            DAL.desconectar();
            return cTabelaEstado;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro ao buscar a tabela do estado do recorte", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
}