package com.epam.star.action.paycard;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AjaxPaymentAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        return null;
    }
}
