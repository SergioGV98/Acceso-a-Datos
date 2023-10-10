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

    public void cambiarRegistro(Map<String, String> reg, long pos, String nomCampo, String nuevoValor) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);
            for (int i = 0; i < reg.size(); i++) {
                Map<String, String> campo = reg.get(reg)
                if(campo.getClave().contains(nomCampo)){
                    int longCamp = campo.getValor();
                    String valorCampoForm = String.format("%1$-" + longCamp + "s", nuevoValor);
                    faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCamp);
                }
            }

        }

    }
}
