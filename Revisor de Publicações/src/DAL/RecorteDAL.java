package DAL;

import Core.Excecao.Excecao;
import Entity.Recorte;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Função para conectar ao banco de dados DB_VISTA_RECORTE do servidor VISTAAPL
 * <p>
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class RecorteDAL extends DAL implements DatabaseRecorteVista
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

    @Override
    public void setRecorte(Recorte cRecorte)
    {
        this.cRecorte = cRecorte;
    }

    @Override
    public Recorte getRecorte()
    {
        return this.cRecorte;
    }

    @Override
    public void executarQuery(String sql)
    {
        super.executarQuery(sql);
    }

    @Override
    public ResultSet executarSelectQuery(String sql)
    {
        return super.executarSelectQuery(sql);
    }
}
