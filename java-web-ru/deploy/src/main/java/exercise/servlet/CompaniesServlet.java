package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        List<String> companies = getCompanies();

        if (request.getQueryString() == null || request.getParameter("search") == null) {
            companies.forEach(writer::println);
            writer.close();
        } else {
            List<String> result = companies.stream()
                    .filter(item -> item.contains(request.getParameter("search")))
                    .toList();
            if (result.isEmpty()) {
                writer.println("Companies not found");
            } else {
                result.forEach(writer::println);
            }
            writer.close();
        }

        // END
    }
}
