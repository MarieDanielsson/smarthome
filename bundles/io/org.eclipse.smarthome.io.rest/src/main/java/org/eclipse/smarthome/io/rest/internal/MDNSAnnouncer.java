/**
 * Copyright (c) 2014-2015 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.io.rest.internal;

import java.util.Hashtable;

import org.eclipse.smarthome.io.mdns.MDNSService;
import org.eclipse.smarthome.io.mdns.ServiceDescription;
import org.osgi.framework.BundleContext;

/**
 * This class announces the REST API through mDNS for clients to automatically discover it.
 * 
 * @author Kai Kreuzer - Initial contribution and API
 */
public class MDNSAnnouncer {

	public static final String REST_SERVLET_ALIAS = "/rest";
	
	private int httpSSLPort;

	private int httpPort;
	
	private String mdnsName;

	private MDNSService mdnsService;
	
	public void setMDNSService(MDNSService mdnsService) {
		this.mdnsService = mdnsService;
	}
	
	public void unsetMDNSService(MDNSService mdnsService) {
		this.mdnsService = null;
	}
	
	public void activate(BundleContext bundleContext) {			    
		if (mdnsService != null) {
			mdnsName = bundleContext.getProperty("mdnsName");
			if(mdnsName==null) { mdnsName = "smarthome"; }
        	try {
        		httpPort = Integer.parseInt(bundleContext.getProperty("jetty.port"));
 				mdnsService.registerService(getDefaultServiceDescription());
        	} catch(NumberFormatException e) {}
        	try {
        		httpSSLPort = Integer.parseInt(bundleContext.getProperty("jetty.port.ssl"));
 				mdnsService.registerService(getSSLServiceDescription());
        	} catch(NumberFormatException e) {}
		}
	}
	
	public void deactivate() {
        if (mdnsService != null) {
 			mdnsService.unregisterService(getDefaultServiceDescription());
			mdnsService.unregisterService(getSSLServiceDescription()); 			
 		}
	}
	
    private ServiceDescription getDefaultServiceDescription() {
    	Hashtable<String, String> serviceProperties = new Hashtable<String, String>();
		serviceProperties.put("uri", REST_SERVLET_ALIAS);
		return new ServiceDescription("_" + mdnsName + "-server._tcp.local.", mdnsName, httpPort, serviceProperties);
    }

    private ServiceDescription getSSLServiceDescription() {
    	ServiceDescription description = getDefaultServiceDescription();
    	description.serviceType = "_" + mdnsName + "-server-ssl._tcp.local.";
    	description.serviceName = "" + mdnsName + "-ssl";
		description.servicePort = httpSSLPort;
		return description;
    }
}
