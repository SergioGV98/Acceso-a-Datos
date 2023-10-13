package fichaccesoaleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;
import util.Par;

public class FicheroAccesoAleatorio {

    private final File f;
    private final List<Par<String, Integer>> campos;
    private long longReg;
    private long numReg = 0;

    FicheroAccesoAleatorio(
            String nomFich, List<Par<String, Integer>> campos) throws IOException {
        this.campos = campos;
        this.f = new File(nomFich);
        longReg = 0;
        for (Par<String, Integer> campo : campos) {
            this.longReg += campo.getValor();
        }
        if (f.exists()) {
            this.numReg = f.length() / this.longReg;
        }
    }

    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg++);
    }

    // Actividad 1.21
    public boolean insertar(Map<String, String> reg, long pos) throws IOException {
        if (pos < 0) {
            return false;  // Posición negativa, no se cambia nada en el archivo
        }

        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            if (pos >= numReg) {
                // La posición es mayor o igual al número de registros, añadir un nuevo registro al final
                for (Par<String, Integer> campo : this.campos) {
                    String valorCampo = reg.get(campo.getClave());
                    if (valorCampo == null) {
                        valorCampo = "";
                    }
                    String valorCampoForm = String.format("%1$-" + campo.getValor() + "s", valorCampo);
                    faa.write(valorCampoForm.getBytes("UTF-8"));
                }
                numReg++;  // Incrementar el número de registros
                return true;  // Se añadió un nuevo registro al final
            } else {
                // La posición es menor, sobrescribir un registro existente
                faa.seek(pos * this.longReg);
                for (Par<String, Integer> campo : this.campos) {
                    String valorCampo = reg.get(campo.getClave());
                    if (valorCampo == null) {
                        valorCampo = "";
                    }
                    String valorCampoForm = String.format("%1$-" + campo.getValor() + "s", valorCampo);
                    faa.write(valorCampoForm.getBytes("UTF-8"));
                }
                return true;  // Se sobrescribió un registro existente
            }
        }
    }

    // Actividad 1.17
    public void cambiarRegistro(long pos, String nomCampo, String nuevoValor) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            // En caso de que la posicion este fuera del rangon de registro lanzo un throw
            if (pos < 0 || pos >= numReg) {
                throw new IllegalArgumentException("La posición especificada está fuera del rango de registros.");
            }

            long posicionCampo = pos * longReg;

            // Encontrar la posición del campo utilizando un índice
            int campoIndex = -1;
            for (int i = 0; i < campos.size(); i++) {
                if (campos.get(i).getClave().equals(nomCampo)) {
                    campoIndex = i;
                    // En caso de que no se encuentre el campo lanzo un throw
                    if (campoIndex == -1) {
                        throw new IllegalArgumentException("El campo especificado no se encontró en la lista de campos.");
                    }
                    break;
                }
                posicionCampo = campos.get(i).getValor();
            }

            // Mover el puntero al inicio del campo
            faa.seek(posicionCampo);

            // Cojo el antiguo valor y lo sustituyo por el nuevo
            String valorCampoForm = String.format("%1$-" + campos.get(campoIndex).getValor() + "s", nuevoValor);
            // Escribir el nuevo valor en el campo
            faa.write(valorCampoForm.getBytes("UTF-8"), 0, valorCampoForm.length());

        }
    }

    // Actividad 1.18
    public String obtenerValor(long regis, String nomCampo) throws IOException {

        // Verificar que la posición proporcionada es válida
        if (regis < 0 || regis >= numReg) {
            return null;  // Posición no válida
        }

        try (RandomAccessFile faa = new RandomAccessFile(f, "r")) {
            long posicionRegistro = regis * longReg;
            int campoIndex = -1;

            for (int i = 0; i < campos.size(); i++) {
                if (campos.get(i).getClave().equals(nomCampo)) {
                    campoIndex = i;
                    break;
                }
                // Avanzar la posición de inicio al siguiente campo
                posicionRegistro = campos.get(i).getValor();
            }

            if (campoIndex != -1) {
                faa.seek(posicionRegistro);

                byte[] campoBytes = new byte[campos.get(campoIndex).getValor()];
                faa.read(campoBytes);
                return new String(campoBytes, "UTF-8").trim();
            }
        }
        return null;
    }

    //Actividad 1.19 y 1.20
    public Integer obtenerRegistro(String nomCampo, String valorBuscado, long posicionInicial) throws IOException {
        if (nomCampo == null || valorBuscado == null || nomCampo.isEmpty() || posicionInicial < 0) {
            return null;  // Parámetros incorrectos
        }

        try (RandomAccessFile faa = new RandomAccessFile(f, "r")) {
            for (long regis = posicionInicial; regis < numReg; regis++) {
                long posicionRegistro = regis * longReg;

                int campoIndex = -1;
                for (int i = 0; i < campos.size(); i++) {
                    if (campos.get(i).getClave().equals(nomCampo)) {
                        campoIndex = i;
                        break;
                    }
                    posicionRegistro += campos.get(i).getValor();
                }

                if (campoIndex != -1) {
                    faa.seek(posicionRegistro);
                    byte[] campoBytes = new byte[campos.get(campoIndex).getValor()];
                    faa.read(campoBytes);
                    String valorCampo = new String(campoBytes, "UTF-8").trim();

                    if (valorCampo.equals(valorBuscado)) {
                        return (int) regis; // Se encontró el registro, devuelve la posición
                    }
                }
            }
            return -1;  // No se encontró un registro con el valor especificado en el campo
        }
    }

    //Actividad 1.20
    public Integer buscarRegistro(String nomCampo, String valorBuscado) throws IOException {
        return obtenerRegistro(nomCampo, valorBuscado, 0);
    }
}
