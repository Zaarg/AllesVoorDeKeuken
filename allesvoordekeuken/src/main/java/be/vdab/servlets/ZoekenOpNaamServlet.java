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


@WebServlet("/artikels/zoekenopnaam.htm") 
public class ZoekenOpNaamServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoekenopnaam.jsp";
	private final transient ArtikelService artikelService = new ArtikelService(); 
	
	@Override
	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null)  {
			Map<String, String> fouten = new HashMap<>();
			String naam = request.getParameter("naam");
			System.out.println(naam);
			if (!Artikel.isNaamValid(naam)) {
				fouten.put("naam", "verplicht");
			}
			if (fouten.isEmpty()) {	
				request.setAttribute("namen", artikelService.findNaam(naam));
			} else {
				request.setAttribute("fouten", fouten);
			}
		}
	request.getRequestDispatcher(VIEW).forward(request, response); 
  }

}