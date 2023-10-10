package fichaccesoaleatorio;

import java.io.File;
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

    public void insertar(Map<String, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);
            for (Par<String, Integer> campo : this.campos) {
                String nomCampo = campo.getClave();
                Integer longCampo = campo.getValor();
                String valorCampo = reg.get(nomCampo);
                if (valorCampo == null) {
                    valorCampo = "";
                }
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }
    }

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
}
