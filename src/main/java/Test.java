import models.Alumno;
import reports.TareasReport;

import java.io.File;
import java.util.Arrays;
import javax.swing.JFrame;
import ui.IndexView;

public class Test {

    public static void main(String[] args) {

        /*/
        File input = new File("/home/amsi/Documentos/Asesorias_Academicas_Pantillas/Comprension_de_la_ciencia");

        TareasReport tr = new TareasReport(input);

        for (String template : tr.createReportForEachAlumnos()){
            System.out.println(template);
            System.out.println();
        }
        //*/
        
        JFrame frame = new JFrame("Generador de Reportes");
        frame.setContentPane(new IndexView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);



    }

}
