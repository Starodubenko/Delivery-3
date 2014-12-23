package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/services")
public class ShowServicesPageAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShowServicesPageAction.class);

    private ActionResult login = new ActionResult("services");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {


        return login;
    }
}

