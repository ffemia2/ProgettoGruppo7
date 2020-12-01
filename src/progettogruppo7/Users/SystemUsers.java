/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettogruppo7.Users;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import progettogruppo7.Exceptions.UsernameException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Rosanna
 */
public class SystemUsers implements Serializable{
    //DESIGN PATTERN: SINGLETON
    private static SystemUsers single_instance=null; 
    
     /* Why choose HashTable?
       This type of data structure permits a low complexity on research that is the most frequent operation
       for this application. 
       It is thread-safe and can be shared with many threads. #TODO is this useless?
       Hashtable doesn’t allow any null key or value.
       BUT it is obsolete
    */
    private Map<String,Planner> planners;
    private Map<String,Maintainer> maintainers;
    
    private SystemUsers(){
        this.planners = new Hashtable<>();
        this.maintainers = new Hashtable<>();
    }
    
    public static SystemUsers SystemUsers(){  
        if (single_instance == null){ 
            single_instance = new SystemUsers();    
        } 
        return single_instance; 
    } 
    
    public AbstractUser addUser(AbstractUser u) throws UsernameException{
        if (planners.containsKey(u.getUsername()) || maintainers.containsKey(u.getUsername()))
            throw new UsernameException("Username not available"); 
        
        if (u.getRole() == UserFactory.Role.PLANNER){
            Planner p = new Planner(u.getUsername(),u.getPassword());
            planners.put(u.getUsername(), p);
            return p;
        }
        else if (u.getRole() == UserFactory.Role.MAINTAINER){
            Maintainer m = new Maintainer(u.getUsername(),u.getPassword());
            maintainers.put(u.getUsername(), m);
            return m;
        }
        else
            return null;
    }
    
    public AbstractUser removeUser(AbstractUser u){
        if (u.getRole() == UserFactory.Role.PLANNER)
            return  planners.remove(u.getUsername());
        else if (u.getRole() == UserFactory.Role.MAINTAINER)
            return maintainers.remove(u.getUsername());
        else
            return null;
    }

    
    public AbstractUser getUser(String username){
        AbstractUser user = planners.get(username);
        if (user == null)
            user = maintainers.get(username);
        return user;
    }

    public Map<String, Maintainer> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(Map<String, Maintainer> maintainers) {
        this.maintainers = maintainers;
    }

    public Map<String, Planner> getPlanners() {
        return planners;
    }

    public void setPlanners(Map<String, Planner> planners) {
        this.planners = planners;
    }

    public void save(String filename){
         try(ObjectOutputStream p = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))){
            p.writeObject(this);
        } catch (FileNotFoundException ex) {
           System.out.println("FileNotFoundException");
        } catch (IOException ex) {
           //  System.out.println("IOException"+"\n");
        }
    }
    
    public SystemUsers load (String filename) throws ClassNotFoundException,IOException {
        SystemUsers ab = null;
        try(ObjectInputStream i = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){
           ab = (SystemUsers)i.readObject();
        } catch (ClassNotFoundException ex) {
           System.out.println("ClassNotFoundException");  
        } catch (FileNotFoundException ex) {
           System.out.println("FileNotFoundException");
        } catch (IOException ex) {
            // System.out.println("IOException"+"\n");
        }
        return ab;
    }

    @Override
    public String toString(){
        String str = "--- MAINTAINERS --" + "\n";
        
        for (Maintainer m : maintainers.values())
            str += m.toString() + "\n";
        str += "--- PLANNERS --" + "\n";
        for (Planner p : planners.values()){
            str += p.toString() + "\n";
        }
        return str;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.planners);
        hash = 17 * hash + Objects.hashCode(this.maintainers);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SystemUsers other = (SystemUsers) obj;
        
        if (!Objects.equals(this.planners, other.planners)) {
            return false;
        }
        if (!Objects.equals(this.maintainers, other.maintainers)) {
            return false;
        }
        return true;
    }

   
}
