/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.systemAdmin;

import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;
import users.AbstractUserFactory;
import users.User;
import users.maintainer.MaintainerFactory;
import users.planner.PlannerFactory;

/**
 *
 * @author Rosanna
 */
public class SystemUsers {
    //DESIGN PATTERN: SINGLETON
    private static SystemUsers single_instance=null; 
    
     /* Why choose HashTable?
       This type of data structure permits a low complexity on research that is the most frequent operation
       for this application. 
       It is thread-safe and can be shared with many threads. #TODO is this useless?
       Hashtable doesn’t allow any null key or value.
       BUT it is obsolete
    */
    private Map<String,User> planners;
    private Map<String,User> maintainers;
    
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
    
    public User addUser(User u) throws UsernameException{
        if (planners.containsKey(u.getUsername()) || maintainers.containsKey(u.getUsername()))
            throw new UsernameException("Username not available"); 
        
        if (u.getRole() == AbstractUserFactory.Role.PLANNER){
            User p = new PlannerFactory().createUser(u.getUsername(),u.getPassword());
            planners.put(u.getUsername(), p);
            return p;
        }
        else if (u.getRole() == AbstractUserFactory.Role.MAINTAINER){
            User m = new MaintainerFactory().createUser(u.getUsername(),u.getPassword());
            maintainers.put(u.getUsername(), m);
            return m;
        }
        else
            return null;
    }
    
    public User removeUser(User u){
        
        if (u.getRole() == AbstractUserFactory.Role.PLANNER)
            return  planners.remove(u.getUsername());
        else if (u.getRole() == AbstractUserFactory.Role.MAINTAINER)
            return maintainers.remove(u.getUsername());
        else
            return null;
    }

    public User changePassword(User u, String password){
        User found = null;
        if (u.getRole() == AbstractUserFactory.Role.MAINTAINER ){
            this.maintainers.get(u.getUsername()).setPassword(password);
            found = this.maintainers.get(u.getUsername());
        }
        if (u.getRole() == AbstractUserFactory.Role.PLANNER){
           this.planners.get(u.getUsername()).setPassword(password);
           found = this.planners.get(u.getUsername());
        }
        return found;
    }
    
    public User getUser(String username){
        User user = planners.get(username);
        if (user == null)
            user = maintainers.get(username);
        return user;
    }

    public Map<String, User> getMaintainers() {
        return maintainers;
    }

    public void setMaintainers(Map<String, User> maintainers) {
        this.maintainers = maintainers;
    }

    public Map<String, User> getPlanners() {
        return planners;
    }

    public void setPlanners(Map<String, User> planners) {
        this.planners = planners;
    }
    /*
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
    */
    @Override
    public String toString(){
        String str = "--- MAINTAINERS --" + "\n";
        
        for (User m : maintainers.values())
            str += m.toString() + "\n";
        str += "--- PLANNERS --" + "\n";
        for (User p : planners.values()){
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
        return Objects.equals(this.maintainers, other.maintainers);
    }

   
}
