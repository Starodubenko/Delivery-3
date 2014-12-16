package com.epam.star.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@MappedAction("POST/changeLocale")
public class ChangeLocaleAction implements Action {
    public static final String LOCALE = "locale";

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        String page = request.getHeader("referer");
        page = page.substring(page.indexOf("/do/")+4);

        ActionResult currentPage = new ActionResult(page,true);

        String locale = request.getParameter(LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE, new Locale(locale));
        return currentPage;
    }
}