package DAL;

import Core.Excecao.Excecao;
import Entity.Recorte;
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
    private Recorte cRecorte;
    
    @Override
    public Connection conectar()
    {
        try
        {
            SERVER = "VISTAAPL";
            DATABASE =  cRecorte.getNomeRecorte();
            USER = cRecorte.getNomeRecorte();
            PASSWORD = cRecorte.getNomeRecorte() + "ADM";

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

    public void setRecorte(Recorte cRecorte)
    {
        this.cRecorte = cRecorte;
    }

    public Recorte getRecorte()
    {
        return this.cRecorte;
    }
}
