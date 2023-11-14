package org.example.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos", schema = "pruebasorm", catalog = "")
public class Producto {
    private Integer codProd;
    private String nomProd;
    private BigDecimal prUnit;
    private String descr;

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
    @Column(name = "nom_prod", nullable = false, length = 40)
    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    @Basic
    @Column(name = "pr_unit", nullable = false, precision = 2)
    public BigDecimal getPrUnit() {
        return prUnit;
    }

    public void setPrUnit(BigDecimal prUnit) {
        this.prUnit = prUnit;
    }

    @Basic
    @Column(name = "descr", nullable = true, length = 120)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        if (codProd != null ? !codProd.equals(producto.codProd) : producto.codProd != null) return false;
        if (nomProd != null ? !nomProd.equals(producto.nomProd) : producto.nomProd != null) return false;
        if (prUnit != null ? !prUnit.equals(producto.prUnit) : producto.prUnit != null) return false;
        if (descr != null ? !descr.equals(producto.descr) : producto.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codProd != null ? codProd.hashCode() : 0;
        result = 31 * result + (nomProd != null ? nomProd.hashCode() : 0);
        result = 31 * result + (prUnit != null ? prUnit.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}
