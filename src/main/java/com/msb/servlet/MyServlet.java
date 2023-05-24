package com.msb.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-23 18:01
 */
@WebServlet(name="myServlet", urlPatterns = "/srv")
public class MyServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(MyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.toString());
        log.info(resp.toString());
        super.doGet(req, resp);
    }
}
