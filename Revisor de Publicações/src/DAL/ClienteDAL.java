package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Função para conectar ao banco de dados DB_VISTA_RECORTE do servidor VISTAAPL
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class ClienteDAL extends DAL
{
    @Override
    public Connection conectar() throws ClassNotFoundException, SQLException
    {
        SERVER = "VISTAAPL";
        DATABASE = "DB_VISTA_CLIENTE";
        USER = "DESENVOLVIMENTO";
        PASSWORD = "DESENVOLVIMENTOADM";

        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://" + SERVER + "/" + DATABASE + ";", USER, PASSWORD);
        return conexao;
    }
}
