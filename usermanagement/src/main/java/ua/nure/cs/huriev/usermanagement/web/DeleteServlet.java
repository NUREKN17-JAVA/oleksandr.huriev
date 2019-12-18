package ua.nure.cs.huriev.usermanagement.web;

import ua.nure.cs.huriev.usermanagement.User;
import ua.nure.cs.huriev.usermanagement.db.DaoFactory;
import ua.nure.cs.huriev.usermanagement.db.DatabaseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends EditServlet {

    @Override
    protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/delete.jsp").forward(req, resp);
    }

    @Override
    protected void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doCancel(req, resp);
    }

    @Override
    protected void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DaoFactory.getInstance().getUserDao().delete((User) req.getSession().getAttribute("user"));
        } catch (DatabaseException e) {
            req.setAttribute("error", "Error in the database: " + e.getMessage());
            req.getRequestDispatcher("/delete.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
    }
}