package net.ausiasmarch.sambados;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Control extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Integer height = Integer.parseInt(request.getParameter("height"));
            Integer width = Integer.parseInt(request.getParameter("width"));
            Integer maxi = 30;
            Gson oGson = new Gson();
            if (width == null || width > maxi || width < 1 || height == null || height > maxi || height < 1) {
                out.print(oGson.toJson("Parameter error: height & width integers between 1 and 50."));
            } else {
                ArrayList alBlk = new ArrayList<>();
                for (int i = 1; i <= height; i++) {
                    ArrayList alRow = new ArrayList<>();
                    for (int j = 1; j <= width; j++) {
                        alRow.add(i * j);
                    }
                    alBlk.add(alRow);
                }
                out.print(oGson.toJson(alBlk));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
