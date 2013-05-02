/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002
*
*	File: RenewSubscriber.java
*
*	Revision:
*
*	07/07/04
*		- first revision.
*	
******************************************************************/

package org.cybergarage.upnp.control;

import org.cybergarage.util.*;
import org.cybergarage.upnp.*;

public class RenewSubscriber extends ThreadCore
{
	public final static long INTERVAL = 120;
	private static final CommonLog log = LogFactory.createNewLog("dlna_framework");
	////////////////////////////////////////////////
	//	Constructor
	////////////////////////////////////////////////

	public RenewSubscriber(ControlPoint ctrlp)
	{
		setControlPoint(ctrlp);
	}
	
	////////////////////////////////////////////////
	//	Member
	////////////////////////////////////////////////

	private ControlPoint ctrlPoint;

	public void setControlPoint(ControlPoint ctrlp)
	{
		ctrlPoint = ctrlp;
	}
	
	public ControlPoint getControlPoint()
	{
		return ctrlPoint;
	}

	////////////////////////////////////////////////
	//	Thread
	////////////////////////////////////////////////
	
	public void run() 
	{
		ControlPoint ctrlp = getControlPoint();
		long renewInterval = INTERVAL * 1000;
		while (isRunnable() == true) {
			
			try {
				Thread.sleep(renewInterval);
			} catch (InterruptedException e1) {
			}
			
			try {
				long time1 = System.currentTimeMillis();
				ctrlp.renewSubscriberService();
				long time2 = System.currentTimeMillis();
	//			log.e("ctrlp.renewSubscriberService() cost time = " + (time2 - time1));
			} catch (Exception e) {
				log.e("catch exception!!!e = " + e.getMessage());
			}
		
		}
	}
}
