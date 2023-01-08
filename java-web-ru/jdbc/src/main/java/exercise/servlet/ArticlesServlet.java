package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();

        // Запрос для получения данных о статьях. Вместо знака ? будут подставлены определенные значения
        String query = "SELECT id, title, body FROM articles ORDER BY id LIMIT 10 OFFSET ?";
        String pageNumber = request.getParameter("page") == null
                || Integer.parseInt(request.getParameter("page")) < 1
                ? "1" : request.getParameter("page");

        int offsetNumber = 10;
        int pageIndex = (Integer.parseInt(pageNumber) - 1) * offsetNumber;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pageIndex);
            // Выполняем запрос
            // Результат выполнения представлен объектом ResultSet
            ResultSet rs = statement.executeQuery();

            // При помощи метода next() можно итерировать по строкам в результате
            // Указатель перемещается на следующую строку в результатах
            while (rs.next()) {
                articles.add(Map.of(
                        "id", rs.getString("id"),
                        "title", rs.getString("title"),
                        "body", rs.getString("body")
                        )
                );
            }

        } catch (SQLException e) {
            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        String previousPage = Integer.parseInt(pageNumber) - 1 < 1 ?
                "1" : Integer.toString(Integer.parseInt(pageNumber) - 1);
        String nextPage = Integer.toString(Integer.parseInt(pageNumber) + 1);

        request.setAttribute("articles", articles);
        request.setAttribute("previousPage", previousPage);
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("pageNumber", pageNumber);
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        int id = Integer.parseInt(getId(request));
        Map<String, String> article = new HashMap<>();

        String query = "SELECT id, title, body FROM articles WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (!rs.first()) {
                // Если произошла ошибка, устанавливаем код ответа 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            article.put("id", rs.getString("id"));
            article.put("title", rs.getString("title"));
            article.put("body", rs.getString("body"));

        } catch (SQLException e) {
            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("article", article);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
