package Model;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para implementação de classes modelo.
 * @author Victor Vaz <victor.vaz@vistaes.com.br>
 * @param <TOI> Tipo do Objeto Identificador
 * @param <E> Entidade
 */
public interface Model<E, TOI>
{
    /**
     * Função para cadastrar uma entidade
     * @param e Entidade
     * @throws java.sql.SQLException Exceção para problemas ocorridos com SQL.
     * @throws java.lang.ClassNotFoundException Exceção para problemas com classes não encontradas.
     */
    public void cadastrar(E e)throws SQLException, ClassNotFoundException;
    
    /**
     * Função para atualizar uma entidade
     * @param e Entidade
     * @throws java.sql.SQLException Exceção para problemas ocorridos com SQL.
     * @throws java.lang.ClassNotFoundException Exceção para problemas com classes não encontradas.
     */
    public void atualizar(E e) throws SQLException, ClassNotFoundException;
    
    /**
     * Função para buscar uma entidade
     * @param e 
     * @throws java.sql.SQLException Exceção para problemas ocorridos com SQL.
     * @throws java.lang.ClassNotFoundException Exceção para problemas com classes não encontradas.
     */
    public void deletar(E e) throws SQLException, ClassNotFoundException;
    
    /**
     * Função para buscar uma determinada entidade
     * @param objetoIdentificador Identificador único da entidade
     * @return A entidade buscada
     * @throws java.sql.SQLException Exceção para problemas ocorridos com SQL.
     * @throws java.lang.ClassNotFoundException Exceção para problemas com classes não encontradas.
     */
    public E buscar(TOI objetoIdentificador) throws SQLException, ClassNotFoundException;
    
    /**
     * Função para buscar todos as entidades cadastradas
     * @return Lista com as entidades
     * @throws java.sql.SQLException Exceção para problemas ocorridos com SQL.
     * @throws java.lang.ClassNotFoundException Exceção para problemas com classes não encontradas.
     */
    public List<E> buscarTodos() throws SQLException, ClassNotFoundException;
}
