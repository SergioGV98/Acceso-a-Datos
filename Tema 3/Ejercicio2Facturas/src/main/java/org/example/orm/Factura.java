package org.example.orm;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "facturas", schema = "pruebasorm", catalog = "")
public class Factura {
    private Integer numFactura;
    private Date fechaFactura;
    private Cliente cliente;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num_factura", nullable = false)
    public Integer getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(Integer numFactura) {
        this.numFactura = numFactura;
    }

    @Basic
    @Column(name = "fecha_factura", nullable = false)
    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Factura factura = (Factura) o;

        if (numFactura != null ? !numFactura.equals(factura.numFactura) : factura.numFactura != null) return false;
        if (fechaFactura != null ? !fechaFactura.equals(factura.fechaFactura) : factura.fechaFactura != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numFactura != null ? numFactura.hashCode() : 0;
        result = 31 * result + (fechaFactura != null ? fechaFactura.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
