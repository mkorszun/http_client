package com.gamecloud.http.property;

import org.apache.http.message.BasicNameValuePair;

/**
 * Property
 * 
 * @author mateusz
 *
 */
public class Property extends BasicNameValuePair {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param n - name
	 * @param v - value
	 */
	public Property(String n, Object v){
		super(n, v.toString());
	}
}
