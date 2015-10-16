package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;
import be.vdab.services.ArtikelgroepService;

@WebServlet("/artikels/perartikelgroep.htm") 
public class PerArtikelGroepServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/perartikelgroep.jsp";
	private final transient ArtikelgroepService artikelgroepService = new ArtikelgroepService(); 
	
	@Override
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikelgroepen", artikelgroepService.findAll());
		String id = request.getParameter("id");
	    if (id != null) {
	      request.setAttribute("artikelgroep", artikelgroepService.read(Long.parseLong(id)));
	    }
	    request.getRequestDispatcher(VIEW).forward(request, response);
	   
	}

}