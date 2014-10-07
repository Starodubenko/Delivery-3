package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AjaxSetImageToAvatarAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSaveClientDataAction.class);
    ActionResult jsonn = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        Image image;
        try {
            URL url = new URL("http://www.digitalphotoartistry.com/rose1.jpg");
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new ActionException(e);
        }


        return jsonn;
    }
}
