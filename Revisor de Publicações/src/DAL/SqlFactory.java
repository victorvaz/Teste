package DAL;

import java.util.Arrays;
import org.jsoup.helper.StringUtil;

/**
 * Framework para criação de SQLs
 * @author Víctor Vaz de Oliveira <victor-vaz@hotmail.com.br>
 */
public class SqlFactory
{
    /**
     * Função para criar uma sql para busca
     * @param arraySelects Array para os selects. Ex.: "E.EXEMPLO AS ALGUMA_COISA", sem o SELECT
     * @param arrayFrom Array Tabela de busca. Ex.: "TABELA", sem o FROM.
     * @param arrayInnerJoin Array com os inner joins completos
     * @param arrayCondicoes Array com as condições sem o Where
     * @param arrayOrdemBy 
     * @return 
     */
    public static String createSelect(String[] arraySelects, String[] arrayFrom, String[] arrayInnerJoin, String[] arrayCondicoes, String[] arrayOrdemBy)
    {
        // Lista de selects:
        String sqlSelect = " SELECT " + StringUtil.join(Arrays.asList(arraySelects), ",\n ");
        
        // Lista de from:
        String sqlFrom = " FROM " + StringUtil.join(Arrays.asList(arrayFrom), ", ");
        
        // Lista de Inner Join:
        String sqlInnerJoin = StringUtil.join(Arrays.asList(arrayInnerJoin), " \n ");
        
        // Lista de Condições:
        String sqlCondicoes = "";
        if (arrayCondicoes.length > 0)
        {
            sqlCondicoes = " WHERE " + StringUtil.join(Arrays.asList(arrayCondicoes), " \n AND ");
        }
        
        // Lista de Ordenação:
        String sqlOrdemBy = "";
        if (arrayOrdemBy.length > 0)
        {
            sqlOrdemBy = " ORDER BY " + StringUtil.join(Arrays.asList(arrayOrdemBy), ",\n ");
        }
        
        String sqlFull = sqlSelect + " \n " + sqlFrom + " \n " + sqlInnerJoin + " \n " + sqlCondicoes + " \n " + sqlOrdemBy;
        
        return sqlFull;
    }

    private SqlFactory()
    {
    }
}
