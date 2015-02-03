package DAL;

import Entity.Recorte;
import java.sql.ResultSet;

/**
 * Interface para os bancos do recorte
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public interface DatabaseRecorteVista
{
    /**
     * Função para definir um recorte
     * @param cRecorte 
     */
    void setRecorte(Recorte cRecorte);
    
    /**
     * Função para retornar um recorte
     * @return Recorte
     */
    Recorte getRecorte();
    
    /**
     * Função para desconectar com o banco de dados.
     */
    void desconectar();
    
    /**
     * Função para executar uma query no banco de dados.
     * @param sql SQL que será executada
     */
    void executarQuery(String sql);
    
    /**
     * Função para executar uma query e retornar um resultset
     * @param sql SQL que será executada
     * @return 
     */
    ResultSet executarSelectQuery(String sql);
    
    /**
     * Função para transformar a classe em uma string
     * @return String
     */
    @Override
    String toString();
}
