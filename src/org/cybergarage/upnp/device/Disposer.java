/******************************************************************
 *
 *	CyberLink for Java
 *
 *	Copyright (C) Satoshi Konno 2002-2004
 *
 *	File: Disposer.java
 *
 *	Revision:
 *
 *	01/05/04
 *		- first revision.
 *	
 ******************************************************************/

package org.cybergarage.upnp.device;

import org.cybergarage.upnp.*;
import org.cybergarage.util.*;

public class Disposer extends ThreadCore {
	// //////////////////////////////////////////////
	// Constructor
	// //////////////////////////////////////////////

	private static final CommonLog log = LogFactory.createNewLog("dlna_framework");

	public Disposer(ControlPoint ctrlp) {
		setControlPoint(ctrlp);
	}

	// //////////////////////////////////////////////
	// Member
	// //////////////////////////////////////////////

	private ControlPoint ctrlPoint;

	public void setControlPoint(ControlPoint ctrlp) {
		ctrlPoint = ctrlp;
	}

	public ControlPoint getControlPoint() {
		return ctrlPoint;
	}

	// //////////////////////////////////////////////
	// Thread
	// //////////////////////////////////////////////

	public void run() {
		ControlPoint ctrlp = getControlPoint();
		long monitorInterval = ctrlp.getExpiredDeviceMonitoringInterval() * 1000;

		while (isRunnable() == true) {
			
			try {
				Thread.sleep(monitorInterval);
			} catch (InterruptedException e) {
			}
			
			try {
				
				long time1 = System.currentTimeMillis();
				ctrlp.removeExpiredDevices();
				long time2 = System.currentTimeMillis();		
		//		log.e("ctrlp.removeExpiredDevices() cost time = " + (time2 - time1));
			} catch (Exception e) {
				log.e("catch exception!!!e = " + e.getMessage());
			}
			

			// ctrlp.print();
		}
	}
}
