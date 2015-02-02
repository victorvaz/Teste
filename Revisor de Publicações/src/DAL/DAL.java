package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Core.Excecao.Excecao;

/**
 * Camada de acesso ao banco de dados.
 * <p>
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public class DAL
{
    private int tentativa;
    
    /**
     * Conexão com o banco de dados.
     */
    protected Connection conexao;
    /**
     * Caminho do servidor.
     */
    protected String SERVER;
    /**
     * Nome do banco de dados.
     */
    protected String DATABASE;
    /**
     * Nome de usuário do banco de dados.
     */
    protected String USER;
    /**
     * Senha do usuário do banco de dados.
     */
    protected String PASSWORD;

    /**
     * Função para estabelecer uma conexão com o banco de dados.
     * @return null
     */
    public Connection conectar()
    {
        tentativa = 0;
        return null;
    }
    
    /**
     * Função para desconectar uma conexão com o banco de dados.
     */
    public void desconectar()
    {
        try
        {
            conexao.close();
        }
        catch (SQLException ex)
        {
            new Excecao("Erro ao desconectar", this.getClass().getName(), ex.toString());
        }
    }

    /**
     * Função para executar uma query no banco de dados.
     * <p>
     * @param sql Comando a ser executado no banco de dados.
     */
    public void executarQuery(String sql)
    {
        try
        {
            Connection conn = this.conectar();            
            Statement stmt = conn.createStatement();
            stmt.execute(sql);            
            desconectar();
        }
        catch (SQLException ex) // Acontece muito ao dar SQLBUSY
        {
            tentativa++;
            
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException ex1)
            {
                Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            if (tentativa > 30)
            {            
                new Excecao("Query ultrapassou o numero de tentativas", this.getClass().getName(), sql + " " + ex.toString());
            }
            else
            {
                this.executarQuery(sql);
            }
        }
    }

    /**
     * Função para executar uma query no banco de dados e retornar um ResultSet
     * <p>
     * @param sql Comando a ser executado no banco de dados.
     * <p>
     * @return ResultSet
     */
    public ResultSet executarSelectQuery(String sql)
    {
        try
        {
            Connection conn = this.conectar();
            ResultSet result;
            Statement stmt = conn.createStatement();            
            result = stmt.executeQuery(sql);
            return result;
        }
        catch (SQLException ex) // Acontece muito ao dar SQLBUSY
        {
            tentativa++;
            
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex1)
            {
                Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            if (tentativa > 30)
            {            
                new Excecao("Query ultrapassou o numero de tentativas", this.getClass().getName(), sql + " " + ex.toString());
            }
            else
            {
                this.executarQuery(sql);
            }
        }

        return null;
    }

    /**
     * Retorna uma string contendo as configurações do servidor.
     * <p>
     * @return String
     */
    @Override
    public String toString()
    {
        String texto = "Configurações da DAL: "
            + "Servidor = " + SERVER + ", "
            + "Banco de dados = " + DATABASE + ", "
            + "Usuario = " + USER + ", "
            + "Senha = " + PASSWORD + ".";

        return texto;
    }
}
