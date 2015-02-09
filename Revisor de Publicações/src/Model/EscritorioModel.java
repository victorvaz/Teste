package Model;

import Core.Excecao.Excecao;
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
public class EscritorioModel
{
    /**
     * Função para buscar um determinado escritório
     * @param cRecorte
     * @return Lista dos escritórios do recorte.
     */
    public List<Escritorio> buscar(Recorte cRecorte)
    {
        try
        {
            List<Escritorio> ListaEscritorios = new ArrayList<>();

            RecorteDAL DAL = new RecorteDAL();
            DAL.setRecorte(cRecorte);

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
        catch (SQLException ex)
        {
            new Excecao("Erro ao buscar os processos", this.getClass().getName(), ex.toString());
        }
        
        return null;
    }
}