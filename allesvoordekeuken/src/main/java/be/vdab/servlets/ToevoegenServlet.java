package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelService;


@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";
	private final transient ArtikelService artikelservice = new ArtikelService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (!Artikel.isNaamValid(naam)) {
			fouten.put("naam", "verplicht");
		}
		BigDecimal aankoopprijs = null;
		try {
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (!Artikel.isPrijsValid(aankoopprijs)) {
				fouten.put("aankoopprijs", "tik een positief getal of 0");
			}
		} catch (NumberFormatException ex) {
			fouten.put("aankoopprijs", "tik een positief getal of 0");
		}
		BigDecimal verkoopprijs = null;
		try {
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (!Artikel.isPrijsValid(verkoopprijs)) {
				fouten.put("verkoopprijs", "tik een positief getal of 0");
			}
		} catch (NumberFormatException ex) {
			fouten.put("verkoopprijs", "tik een positief getal of 0");
		}
		if (aankoopprijs.compareTo(verkoopprijs) > 0) {
			fouten.put("verlies", "De verkoopprijs moet groter zijn dan de aankoopprijs");
		}
		if (fouten.isEmpty()) {
			Artikel artikel;
			if (request.getParameter("artikeltype").equals("Food")) {
				int houdbaarheid = Integer.parseInt(request.getParameter("houdbaarheid"));
				artikel = new FoodArtikel(naam, aankoopprijs, verkoopprijs, houdbaarheid);
			} else {
				int garantie = Integer.parseInt(request.getParameter("garantie"));
				artikel = new NonFoodArtikel(naam, aankoopprijs, verkoopprijs,garantie);
			}
			artikelservice.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath(), artikel.getId())));
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}