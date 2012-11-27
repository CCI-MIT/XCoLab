/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import java.util.Date;

public class Test {
	
	public static void main(String[] args) {
	    Date now = new Date();
	    System.out.println(String.format("%1$tm/%1$te/%1$ty", now));
		String val = "345";
		System.err.println(Double.parseDouble(val));
	}

}
