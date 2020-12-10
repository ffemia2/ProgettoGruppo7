/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

/**
 *
 * @author Francesco Femia
 */
public abstract class JDBC implements JDBCInterface{
    private String url="jdbc:postgresql://localhost/postgres";
    private String user="postgres";
    private String pwd="polter01";
    
    

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }
    
    
}
