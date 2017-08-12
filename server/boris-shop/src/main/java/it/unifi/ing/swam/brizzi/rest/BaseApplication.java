package it.unifi.ing.swam.brizzi.rest;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//forse vanno messi tutti insiem nel package "controller" potrebbe essere ecco..

@ApplicationPath("")
public class BaseApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		return Collections.emptySet();
	}
}

	
