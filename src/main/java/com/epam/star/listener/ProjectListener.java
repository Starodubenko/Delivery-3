package com.epam.star.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ProjectListener implements ServletContextListener{

    // todo change class name
    public ProjectListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
     // do you know abbreviation RTFM?  no facepalm? no

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}
