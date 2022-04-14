package agencija.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import agencija.repository.AgencijaRoleRepository;
import agencija.repository.AgencijaUserRepository;
import agencija.repository.AranzmanRepository;
import agencija.repository.DestinacijaRepository;
import agencija.repository.RezervacijaRepository;
import agencija.repository.SmestajRepository;


import model.AgencijaRole;
import model.AgencijaUser;
import model.Aranzman;
import model.Destinacija;
import model.Rezervacija;
import model.Smestaj;

@Controller
@RequestMapping(value="/auth")
public class LoginController {

	@Autowired
	 AgencijaUserRepository aur;
	
	@Autowired
	 AgencijaRoleRepository aroleRepo;
	
	@Autowired
	AranzmanRepository ar;
	
	@Autowired
	RezervacijaRepository rr;
	
	@Autowired
	DestinacijaRepository dr;

	@Autowired
	SmestajRepository sr;
	
	@Autowired
	AgencijaRoleRepository aroler;
	

		
	
	@RequestMapping(value="loginPage", method=RequestMethod.GET) 
	public String loginPage() { 
		return "login";
	}
	
	
	
	
	@RequestMapping(value="pocetna", method=RequestMethod.GET) 
	public String pocetna() { 
		return "index";
	}
	
    @RequestMapping(value = "registerUser", method = RequestMethod.GET)
		public String newUser(Model model) {
		AgencijaUser u = new AgencijaUser();
		model.addAttribute("user", u);
		return "register";
	}

	 
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") AgencijaUser u, Model m) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	   
	    
	    u.setPassword(passwordEncoder.encode(u.getPassword()));
		
	    AgencijaRole role = aroleRepo.findById(2).get();
	    
		u.addRole(role);
		role.addUser(u);
	    
	    aur.save(u);
	    
		System.out.println("SAVED");
	    return "login";
	}
	
	

	
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/auth/loginPage";
    }


	//Zaposleni
	@RequestMapping(value="/zaposleni/getAranzmani", method = RequestMethod.GET)
	public String getAranzmani(HttpServletRequest request) {
		List<Aranzman> aranzmani = ar.findAll();
		request.getSession().setAttribute("aranzmani", aranzmani);
		return "KorisniciAranzmana";
	}
	
	
	@RequestMapping(value = "/zaposleni/korisniciAranzmana", method = RequestMethod.GET)
	public String korisniciAranzmana(Integer idAranzman, Model m, HttpServletRequest request) {
		Aranzman a = ar.findById(idAranzman).get();
		List<Rezervacija> rezervacije = rr.findByAranzman(a);
		//ovo je potrebno za izvestaj o korisnicima
		request.getSession().setAttribute("idAranzman", idAranzman);
		
		m.addAttribute("naziv", a.getNazivA());
		m.addAttribute("rezervacije", rezervacije);
		return "KorisniciAranzmana";
	}

	
	@RequestMapping(value = "/zaposleni/unosUplate", method = RequestMethod.GET)
	public String unosUplate(Integer idR, Model m) {
		Rezervacija r = rr.findById(idR).get();
		r.setPlaceno(true);
		rr.save(r);
		m.addAttribute("porukaU", "Uspesno ste uneli uplatu za rezervaciju korisnika "+ r.getAgencijaUser().getName() + ".");
		return "KorisniciAranzmana";
	}
	
	
	@RequestMapping(value = "/zaposleni/otkazivanje", method = RequestMethod.GET)
	public String otkazivanjeRezervacije(Integer idR, Model m) {
		Rezervacija r = rr.findById(idR).get();
		Aranzman a = r.getAranzman();
		rr.deleteById(idR);
		Integer zauzeto = r.getBrOsoba();
		Integer slobodnaMesta = a.getBrSlobMesta();
		a.setBrSlobMesta(slobodnaMesta + zauzeto);
		ar.save(a);
		m.addAttribute("porukaO", "Uspesno ste otkazali rezervaciju korisnika "+ r.getAgencijaUser().getName()+ ", id rezervacije: " + r.getIdRezervacija()+ ".");
		
		return "KorisniciAranzmana";
	}
	
	@RequestMapping(value = "/zaposleni/getSmestajiIDestinacije", method = RequestMethod.GET)	
	public String getSmestajiIDestinacije (HttpServletRequest request) {
		List<Smestaj> smestaji = sr.findAll();
		List<Destinacija> destinacije = dr.findAll();
		request.getSession().setAttribute("smestaji", smestaji);
		request.getSession().setAttribute("destinacije", destinacije);
		return "UnosAranzmana";
	}
	
	@RequestMapping(value = "/zaposleni/unosAranzmana", method = RequestMethod.GET)
	public String unosAranzmana(String nazivA, Date datumP, Date datumD, Integer brSlobMesta, Integer idSmestaj, Model m, HttpServletRequest request) {
		if(idSmestaj == 0) {
			request.getSession().setAttribute("nazivA", nazivA);
			request.getSession().setAttribute("datumP", datumP);
			request.getSession().setAttribute("datumD", datumD);
			request.getSession().setAttribute("brSlobMesta", brSlobMesta);
			return "UnosSmestaja";
		}else {
		Aranzman a = new Aranzman();
		a.setNazivA(nazivA);
		a.setDatumP(datumP);
		a.setDatumD(datumD);
		a.setBrSlobMesta(brSlobMesta);
		Smestaj s = sr.findById(idSmestaj).get();
		a.setSmestaj(s);
		Aranzman aranzman = ar.save(a);
		m.addAttribute("porukaAr", "Uspesno dodat aranzman sa id brojem " + aranzman.getIdAranzman() + ".");
		return "UnosAranzmana";
		}
		
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	
	@RequestMapping(value = "/zaposleni/unosSmestaja", method = RequestMethod.GET)
	public String unosSmestaja(String nazivS, String opisS, Integer cenaPoOsobi, Integer idDestinacija,Model m, HttpServletRequest request) {
		String nazivA = (String)request.getSession().getAttribute("nazivA");
		Date datumP = (Date)request.getSession().getAttribute("datumP");
		Date datumD = (Date)request.getSession().getAttribute("datumD");
		
		Integer brSlobMesta = (Integer)request.getSession().getAttribute("brSlobMesta");
		Aranzman a = new Aranzman();
		a.setNazivA(nazivA);
		a.setDatumP(datumP);
		a.setDatumD(datumD);
		a.setBrSlobMesta(brSlobMesta);
		Smestaj s = new Smestaj();
		s.setNazivS(nazivS);
		s.setOpisS(opisS);
		s.setCenaPoOsobi(cenaPoOsobi);
		Destinacija d = dr.findById(idDestinacija).get();
		s.setDestinacija(d);
		Smestaj smestaj = sr.save(s);
		 a.setSmestaj(s);
		
		Aranzman aranzman = ar.save(a);
		m.addAttribute("porukaS", "Uspesno sacuvan smestaj sa id brojem " + smestaj.getIdSmestaj() + " i aranzman id brojem  "+ aranzman.getIdAranzman() + ".");
		return "UnosAranzmana";
	}
	
	@RequestMapping(value = "/zaposleni/getSviAranzmani", method = RequestMethod.GET)
	public String getSviAranzmani(HttpServletRequest request) {
		List<Aranzman> aranzmani = ar.findAll();
		request.getSession().setAttribute("aranzmani", aranzmani);
		return "BrisanjeAranzmana";
	}
	
	@RequestMapping(value = "/zaposleni/brisanjeAranzmana", method = RequestMethod.GET)
	public String brisanjeAranzmana(Integer idA, Model m) {
		ar.deleteById(idA);
		m.addAttribute("porukaA", "Uspesno ste obrisali aranzman ciji je id = " +idA + ".");
		return "BrisanjeAranzmana";
	}
	
	
	//Korisnik
	
	@RequestMapping(value="/korisnik/getPonuda", method = RequestMethod.GET)
	public String getPonuda(HttpServletRequest request) {
		List<Aranzman> aranzmani = ar.findAll();
		request.getSession().setAttribute("aranzmani", aranzmani);
		return "PrikazPonude";
	}
	 
	@RequestMapping(value = "/korisnik/filtriranje", method = RequestMethod.GET) 
	public String filtriranje(Integer idOpcija, HttpServletRequest request) {
		List<Aranzman> aranzmani = ar.findAll();
		if(idOpcija == 1) {
			aranzmani = ar.filtrirajPoCeniRastuce();
		}else {
			aranzmani = ar.filtrirajPoCeniOpadajuce();
		}
		request.getSession().setAttribute("aranzmani", aranzmani);
		return "PrikazPonude";
	}
	@RequestMapping(value = "/korisnik/unosRezervacije", method = RequestMethod.GET)
	public String unosRezervacije(Integer idA, HttpServletRequest request, Model m) {
		Aranzman a = ar.findById(idA).get();
		request.getSession().setAttribute("aranzman", a);
		return "Rezervacija";
	}
	
	@RequestMapping(value = "/korisnik/rezervacija", method = RequestMethod.GET)
	public String unosRezervacije(Integer brOsoba,Model m, HttpServletRequest request) {
		Object ud = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (ud instanceof UserDetails) {
			username = ((UserDetails)ud).getUsername();
			} else {
			username = ud.toString();
			}
		AgencijaUser u = aur.findByUsername(username);
		Aranzman a = (Aranzman)request.getSession().getAttribute("aranzman");
		
		//Integer idA = (Integer)request.getSession().getAttribute("idA");
		System.out.println("username: " + u.getUsername());
		
		
		//Aranzman a = ar.findById(idA).get();
		
		Integer ostaloSlob = a.getBrSlobMesta();
		String porukaRez ="";
		if(ostaloSlob < brOsoba) {
			porukaRez =  "Nazalost, nema dovoljno slobodnih mesta.";
		}
		else {
		
		Rezervacija r = new Rezervacija();
		r.setBrOsoba(brOsoba);
		r.setAgencijaUser(u);
		r.setAranzman(a);
		r.setPlaceno(false);
		//probati umesto 10 int(datumD-datumP)
		Date datumP = r.getAranzman().getDatumP();
		Date datumD = r.getAranzman().getDatumD();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		String datumPolaska = format.format(datumP);
		String datumDolaska = format.format(datumD);
		String[] polazak = datumPolaska.split("-");
		String[] dolazak = datumDolaska.split("-");
		int danP = Integer.parseInt(polazak[2]);
		int danD = Integer.parseInt(dolazak[2]);
		Integer brojDana = (int)(danD) - (int)(danP);
		System.out.println("broj dana je " + brojDana);
		r.setUkupnaCena(brOsoba * a.getSmestaj().getCenaPoOsobi()*brojDana);
		
		a.setBrSlobMesta(ostaloSlob - brOsoba);
		ar.save(a);
		Rezervacija rez = rr.save(r);
		
		porukaRez = "Uspesna rezervacija! Molimo da uplatite "+ rez.getUkupnaCena() + "e najkasnije 10 dana pre polaska. Hvala na poverenju!";
		}
		m.addAttribute("porukaRez", porukaRez);
		return "Rezervacija";
	}
	

	
	@RequestMapping(value = "/korisnik/getRez", method = RequestMethod.GET)
	public String getRez( HttpServletRequest request) {
		Object ud = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (ud instanceof UserDetails) {
			username = ((UserDetails)ud).getUsername();
		}
		else {
			username = ud.toString();
		}
		AgencijaUser u = aur.findByUsername(username);
		List<Rezervacija> rezervacije = rr.findByAgencijaUser(u);
		request.getSession().setAttribute("rezervacije", rezervacije);
		return "PregledSvojihRez";
	}
	
	

}
