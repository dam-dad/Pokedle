package teampoke.informe;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import teampoke.main.Main;
import teampoke.globalstats.GlobalApi;
import teampoke.globalstats.MarcadorPersonal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class HighscoreReport {
	public static void generarPdf() throws JRException, IOException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(Main.class.getResourceAsStream("/report/highscore.jrxml"));		

		// creamos lista de puntuaciones
		List<MarcadorPersonal> puntuaciones = null;
		try {
			puntuaciones = GlobalApi.puntuaciones();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		puntuaciones.add(new MarcadorPersonal("Martin", 10));
//		puntuaciones.add(new MarcadorPersonal("Kata", 3));
//		puntuaciones.add(new MarcadorPersonal("Dragon", 8));
//		puntuaciones.add(new MarcadorPersonal("Ruben", 0));
		
		// mapa de parámetros para el informe
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("autor", "Dragon Black"); 
		
		// generamos el informe (combinamos el informe compilado con los datos) 
        JasperPrint print  = JasperFillManager.fillReport(report, parameters, new JRBeanCollectionDataSource(puntuaciones));
        
        // exporta el informe a un fichero PDF
        JasperExportManager.exportReportToPdfFile(print, "pdf/highscore.pdf");
        
        // Abre el archivo PDF generado con el programa predeterminado del sistema
		Desktop.getDesktop().open(new File("pdf/highscore.pdf"));
	}
	
	public static void main(String[] args) throws JRException, IOException {
		generarPdf();
	}
	
}
