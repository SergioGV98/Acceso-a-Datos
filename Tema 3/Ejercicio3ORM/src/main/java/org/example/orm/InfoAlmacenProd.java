package org.example.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "info_almacen_prod", schema = "pruebasorm", catalog = "")
public class InfoAlmacenProd {
    private Integer codProd;
    private BigDecimal existencias;
    private Productos producto;
    private Productos productosByCodProd;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_prod", nullable = false)
    public Integer getCodProd() {
        return codProd;
    }

    public void setCodProd(Integer codProd) {
        this.codProd = codProd;
    }

    @Basic
    @Column(name = "existencias", nullable = false, precision = 2)
    public BigDecimal getExistencias() {
        return existencias;
    }

    public void setExistencias(BigDecimal existencias) {
        this.existencias = existencias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoAlmacenProd that = (InfoAlmacenProd) o;

        if (codProd != null ? !codProd.equals(that.codProd) : that.codProd != null) return false;
        if (existencias != null ? !existencias.equals(that.existencias) : that.existencias != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codProd != null ? codProd.hashCode() : 0;
        result = 31 * result + (existencias != null ? existencias.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
    public Productos getProductos() {
        return producto;
    }

    public void setProductos(Productos producto) {
        this.producto = producto;
    }

    @OneToOne
    @JoinColumn(name = "cod_prod", referencedColumnName = "cod_prod", nullable = false)
    public Productos getProductosByCodProd() {
        return productosByCodProd;
    }

    public void setProductosByCodProd(Productos productosByCodProd) {
        this.productosByCodProd = productosByCodProd;
    }

    @Override
    public String toString() {
        return "InfoAlmacenProd{" +
                "\ncodProd=" + codProd +
                "\nexistencias=" + existencias +
                "\nproducto=" + producto +
                '}';
    }
}
