package DAL;

import Entity.Recorte;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Função para conectar ao banco de dados DB_VISTA_RECORTE do servidor VISTAAPL
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class RecorteDAL extends DAL
{
    private Recorte cRecorte;
    
    @Override
    public Connection conectar() throws ClassNotFoundException, SQLException
    {
        SERVER = "VISTAAPL";
        DATABASE =  cRecorte.getNomeRecorte();
        USER = "VISTASA";
        PASSWORD = "vistasaadm";

        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://" + SERVER + "/" + DATABASE + ";", USER, PASSWORD);
        return conexao;
    }

    public void setRecorte(Recorte cRecorte)
    {
        this.cRecorte = cRecorte;
    }

    private Recorte getRecorte()
    {
        return this.cRecorte;
    }
}
