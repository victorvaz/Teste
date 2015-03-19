package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Camada de acesso ao banco de dados.
 * @author Víctor Vaz de Oliveira <victor.vaz@vistaes.com.br>
 */
public abstract class DAL
{
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
     * Construtor da Classe
     */
    public DAL()
    {
    }

    /**
     * Função para estabelecer uma conexão com o banco de dados.
     * @return null
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    abstract public Connection conectar() throws SQLException, ClassNotFoundException;
    
    /**
     * Função para desconectar uma conexão com o banco de dados.
     * @throws java.sql.SQLException
     */
    public void desconectar() throws SQLException
    {
        conexao.close();
    }

    /**
     * Função para executar uma query no banco de dados.
     * @param sql Comando a ser executado no banco de dados.
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public void executarQuery(String sql) throws SQLException, ClassNotFoundException
    {
        Connection conn = this.conectar();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);            
        desconectar();
    }

    /**
     * Função para executar uma query no banco de dados e retornar um ResultSet
     * @param sql Comando a ser executado no banco de dados.
     * @return ResultSet
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public ResultSet executarSelectQuery(String sql) throws SQLException, ClassNotFoundException
    {
        Connection conn = this.conectar();
        ResultSet result;
        Statement stmt = conn.createStatement();            
        result = stmt.executeQuery(sql);
        return result;
    }

    /**
     * Retorna uma string contendo as configurações do servidor.
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
