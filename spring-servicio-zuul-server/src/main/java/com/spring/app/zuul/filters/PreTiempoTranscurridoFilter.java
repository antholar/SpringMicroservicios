package com.spring.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	
	public boolean shouldFilter() {
		//se ejecute o no el filtro en cada request
		return true;
	}

	public Object run() throws ZuulException {
		//aqui se resuelve la logica del metodo
		
		//se obtiene el request
		RequestContext ctx = RequestContext.getCurrentContext(); //
		HttpServletRequest request = ctx.getRequest();
		
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString() ) );
		
		
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		return null;
	}

	@Override
	public String filterType() {
		// pre, antes que se resuelva la ruta
		return "pre";
	}

	@Override
	public int filterOrder() {
		// 
		return 1;
	}

}
