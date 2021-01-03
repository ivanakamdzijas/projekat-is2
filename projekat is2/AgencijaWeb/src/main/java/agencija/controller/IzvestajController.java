package agencija.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import agencija.repository.AranzmanRepository;
import agencija.repository.RezervacijaRepository;
import model.Aranzman;
import model.Rezervacija;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/izvestaji")
public class IzvestajController {
	
	@Autowired
	AranzmanRepository ar;
	
	@Autowired
	RezervacijaRepository rr;


	@RequestMapping(value = "/getIzvestajNeplacenih", method = RequestMethod.GET)
	public void getIzvestajNeplacenih(HttpServletResponse response) throws Exception {
	
		
		List<Rezervacija> rezervacije = rr.findByPlacenoOrderByAranzman(false);
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rezervacije);
		java.io.InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/NeplaceneRezervacije.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Neplacene rezervacije.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	
	}
	
	/*
	@RequestMapping(value = "/getIzvestajNeplacenih", method = RequestMethod.GET)
	public void getIzvestajNeplacenih(HttpServletResponse response) throws Exception {
	
		
		List<Rezervacija> rezervacije = rr.findByPlacenoOrderByAranzman(false);
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rezervacije);
		java.io.InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/NeplaceneRezervacije1.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Neplacene rezervacije1.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	
	}
	*/
	@RequestMapping(value = "/getIzvestajKorisnici", method = RequestMethod.GET)
	public void getIzvestajKorisnici(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Integer idAranzman = (Integer)request.getSession().getAttribute("idAranzman");
		Aranzman a = ar.findById(idAranzman).get();
		List<Rezervacija> rezervacije = rr.findByAranzman(a);
		
		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rezervacije);
		java.io.InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/Korisnici.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nazivAranzmana", a.getNazivA());
		params.put("datumP", a.getDatumP());
		params.put("datumD",a.getDatumD());
		params.put("brSlobMesta",a.getBrSlobMesta());
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		inputStream.close();
		
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=Korisnici.pdf");
		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	
	}
	
	
}
