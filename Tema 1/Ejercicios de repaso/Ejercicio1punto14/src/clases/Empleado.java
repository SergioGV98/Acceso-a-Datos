package clases;

public class Empleado {

    int numEmp;
    String dni;
    String nombre;
    double salBrutoAnual;
    boolean tParcial;

    public Empleado(int numEmp, String dni, String nombre, double salBrutoAnual, boolean tParcial) {
        this.numEmp = numEmp;
        this.dni = dni;
        this.nombre = nombre;
        this.salBrutoAnual = salBrutoAnual;
        this.tParcial = tParcial;
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Getter y Setter ">
    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalBrutoAnual() {
        return salBrutoAnual;
    }

    public void setSalBrutoAnual(double salBrutoAnual) {
        this.salBrutoAnual = salBrutoAnual;
    }

    public boolean istParcial() {
        return tParcial;
    }

    public void settParcial(boolean tParcial) {
        this.tParcial = tParcial;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Empleado{" + "numEmp=" + numEmp + ", dni=" + dni + ", nombre=" + nombre + ", salBrutoAnual=" + salBrutoAnual + ", tParcial=" + tParcial + '}';
    }
    
    
    
}
