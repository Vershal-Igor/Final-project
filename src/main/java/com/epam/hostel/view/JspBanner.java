package com.epam.hostel.view;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * {@code JspBanner} class is designed to assist on {@code JSP}.
 */
public class JspBanner extends TagSupport {
    private static Logger logger = Logger.getLogger(JspBanner.class);

    /**
     * {@code doStartTag} method assist in the
     * information display on {@code JSP}.This custom tag
     * allows to add information on JSP page like a banner .
     */
    @Override
    public int doStartTag() throws JspException {
        logger.debug("Start tag");

        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();

            out.print("<div id=\"bannerid\" class=\"banner\" style=\"text-align: center;\">");

            out.print("<img src=\"img/banner.jpg\" alt=\"Hostel_img\">");

            out.print("<h1 style=\"margin-bottom: 50px\"><spr_c:message code=\"content.banner\"/></h1>");

            out.print("</div>");

        } catch (IOException e) {
            logger.error(e.getStackTrace().toString());
        }

        return SKIP_BODY;
    }
}