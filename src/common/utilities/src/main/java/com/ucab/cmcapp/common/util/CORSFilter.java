package com.ucab.cmcapp.common.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class CORSFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {
        //No se requiere inicializacion adicional
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //Se configuran los encabezados CORS
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        // Continuar con la cadena de filtros
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }

}
