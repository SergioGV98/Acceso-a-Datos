
package clases;

public class Empleado {
    
    private int numEmp;
    private String DNI;
    private String nombre;
    private double salBrutoAnual;
    private boolean tParcial;

    public Empleado(int numEmp, String DNI, String nombre, double salBrutoAnual, boolean tParcial) {
        this.numEmp = numEmp;
        this.DNI = DNI;
        this.nombre = nombre;
        this.salBrutoAnual = salBrutoAnual;
        this.tParcial = tParcial;
    }

    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    @Override
    public String toString() {
        return "Empleado{" + "numEmp=" + numEmp + ", DNI=" + DNI + ", nombre=" + nombre + ", salBrutoAnual=" + salBrutoAnual + ", tParcial=" + tParcial + '}';
    }
    
    
    
}
