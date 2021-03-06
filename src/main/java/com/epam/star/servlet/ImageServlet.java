package com.epam.star.servlet;

import com.epam.star.dao.H2dao.DaoException;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.ImageDao;
import com.epam.star.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ImageServlet extends HttpServlet {
    private ImageDao imageDao;
    private static Map<String, String> contentTypes = new HashMap<>();
    public static final Logger LOGGER = LoggerFactory.getLogger(ImageServlet.class);

    static {
        contentTypes.put("jpg", "image/jpeg");
        contentTypes.put("gif", "image/gif");
        contentTypes.put("png", "image/png");
    }

    public void init() throws ServletException {
        try {
            DaoFactory factory = DaoFactory.getInstance();
            DaoManager daoManager = factory.getDaoManager();
            this.imageDao = daoManager.getImageDao();
        } catch (DaoException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getPathInfo().substring(1);

        try {
            filename = new String(filename.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (filename == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Image image;
        try {
            image = imageDao.findByFilename(filename);
        } catch (DaoException e) {
            throw new ServletException(e);
        }

        if (image == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String imageFilename = image.getFilename();
        String extension = imageFilename.substring(imageFilename.indexOf('.') + 1);
        String contentType = contentTypes.get(extension);

        if (contentType != null) response.setContentType(contentType);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image.getContent());
    }
}