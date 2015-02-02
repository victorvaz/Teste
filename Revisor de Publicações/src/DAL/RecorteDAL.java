package DAL;

import Core.Excecao.Excecao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Função para conectar ao banco de dados DB_VISTA_RECORTE do servidor VISTAAPL
 * <p>
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class RecorteDAL extends DAL
{
    /**
     * Nome do recorte
     */
    private String nomeRecorte;
    
    /**
     * @return o nomeRecorte O nome do recorte.
     */
    public String getNomeRecorte()
    {
        return nomeRecorte;
    }

    /**
     * @param NomeRecorte O nome do recorte que será determinado.
     */
    public void setNomeRecorte(String NomeRecorte)
    {
        this.nomeRecorte = NomeRecorte.toUpperCase();
    }
    
    @Override
    public Connection conectar()
    {
        try
        {
            SERVER = "VISTAAPL";
            DATABASE = nomeRecorte;
            USER = nomeRecorte;
            PASSWORD = nomeRecorte + "ADM";

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://" + SERVER + "/" + DATABASE + ";", USER, PASSWORD);
            return conexao;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            new Excecao("Erro de conexão", this.getClass().getName(), ex.toString());
        }

        return null;
    }
}
