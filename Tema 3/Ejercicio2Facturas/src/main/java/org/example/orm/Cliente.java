package org.example.orm;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes", schema = "pruebasorm", catalog = "")
public class Cliente {
    private Integer idCliente;
    private String dni;
    private String nomCliente;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente", nullable = false)
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Basic
    @Column(name = "dni", nullable = false, length = 9)
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "nom_cliente", nullable = false, length = 40)
    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (idCliente != null ? !idCliente.equals(cliente.idCliente) : cliente.idCliente != null) return false;
        if (dni != null ? !dni.equals(cliente.dni) : cliente.dni != null) return false;
        if (nomCliente != null ? !nomCliente.equals(cliente.nomCliente) : cliente.nomCliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente != null ? idCliente.hashCode() : 0;
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (nomCliente != null ? nomCliente.hashCode() : 0);
        return result;
    }
}
