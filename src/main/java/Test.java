import reports.TareasReport;

import java.io.File;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        File input = new File("/home/amsi/Documentos/Asesorias_Academicas_Pantillas/Comprension_de_la_ciencia");

        TareasReport tr = new TareasReport(Arrays.asList(input));

        System.out.println(tr.getAlumnoTareas().get(0).getAlumno().getNombre());

    }

}
