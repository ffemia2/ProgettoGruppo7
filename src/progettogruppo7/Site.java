package progettogruppo7;

import java.util.Objects;

/* @author marco */
public class Site {
    
    private String factorySite;
    private String department;

    public Site(String factorySite, String department) {
        //eventuale controllo sul database.
        this.factorySite = factorySite;
        this.department = department;
    }

    public String getFactorySite() {
        return factorySite;
    }

    public void setFactorySite(String factorySite) {
        this.factorySite = factorySite;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "{" + factorySite + ", " + department + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.factorySite);
        hash = 11 * hash + Objects.hashCode(this.department);
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
        final Site other = (Site) obj;
        if (!Objects.equals(this.factorySite, other.factorySite)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        return true;
    }
}
