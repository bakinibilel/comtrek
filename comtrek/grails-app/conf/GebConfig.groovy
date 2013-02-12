/*******************************************************************************
 * Copyright (c) 2013 ComTrek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ComTrek - initial API and implementation
 ******************************************************************************/
//sauce {
//	baseUrl = 'http://localhost/comtrek/'
//	String username = System.getenv("SAUCE_ONDEMAND_USERNAME");
//	String apiKey = System.getenv("SAUCE_ONDEMAND_ACCESS_KEY");
//	if(username == null || apiKey == null){
//		System.err.println("Sauce OnDemand credentials not set.");
//	}
//	DesiredCapabilities capabillities = DesiredCapabilities.chrome();
//	capabillities.setCapability("name", "MyApp");
//	capabillities.setCapability("platform", Platform.LINUX);
//	capabillities.setCapability("selenium-version", "2.21.0");
//	driver = {
//		def driver = new RemoteWebDriver(new URL("http://${username}:${apiKey}@ondemand.saucelabs.com:80/wd/hub"), capabillities)
//		driver.setFileDetector(new LocalFileDetector());
//		driver
//	}
//}
