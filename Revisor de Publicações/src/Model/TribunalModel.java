package Model;

import Core.Excecao.Excecao;
import DAL.VistaDAL;
import Entity.Estado;
import Entity.Tribunal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo da entidade Tribunal
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class TribunalModel 
{
    /**
     * Função para buscar tribunais
     * @return Lista de Tribunais
     */
    public List<Tribunal> buscar()
    {
        try
        {
            List<Tribunal> ListaTribunais = new ArrayList<>();
            VistaDAL DAL = new VistaDAL();

            String sql = "SELECT ID,"
                       + "       SIGLA,"
                       + "       TRIBUNAL,"
                       + "       ESTADO"
                       + "  FROM DIARIO_OFICIAL_TRIBUNAIS"
                       + " WHERE CLIENTE = 'PROMAD'";

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
            
            return ListaTribunais;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro na busca dos tribunais", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
    
    /**
     * Função para buscar tribunais por estado
     * @param nomeEstado Nome do estado
     * @return Lista de Tribunais
     */
    public List<Tribunal> buscarPorEstado(String nomeEstado)
    {
        try
        {
            List<Tribunal> ListaTribunais = new ArrayList<>();
            VistaDAL DAL = new VistaDAL();

            String sql = "SELECT ID,"
                       + "       SIGLA,"
                       + "       TRIBUNAL,"
                       + "       ESTADO"
                       + "  FROM DIARIO_OFICIAL_TRIBUNAIS"
                       + " WHERE CLIENTE = 'PROMAD'"
                       + "   AND ESTADO  = '" + nomeEstado.toUpperCase() + "'";

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
            
            return ListaTribunais;
        }
        catch (SQLException ex)
        {
            new Excecao("Erro na busca dos tribunais", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
}