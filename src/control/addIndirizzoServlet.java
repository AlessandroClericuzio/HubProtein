package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IndirizzoBean;
import model.IndirizzoDAO;

@WebServlet("/addIndirizzo")
public class addIndirizzoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String via = req.getParameter("via");
		String citta = req.getParameter("citta");
		String stato_provincia = req.getParameter("stato_provincia");
		String cap = req.getParameter("cap");
		String paese = req.getParameter("paese");
		
		if (email == null || via == null || citta == null || stato_provincia == null || cap == null || paese == null) {
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
			return;
		}
		
		IndirizzoBean indirizzo = new IndirizzoBean(0, via, citta, stato_provincia, cap, paese, email);
		
		try {
			IndirizzoDAO.doSave(indirizzo);
		} catch (SQLException e) {
			req.getSession().setAttribute("errore", "Errore nell'inserimento dell'indirizzo");
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath() + "/indirizzi.jsp");
		return;
	}
}
