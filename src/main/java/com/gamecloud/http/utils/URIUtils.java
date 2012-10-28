package com.gamecloud.http.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * @author mateusz
 *
 */
public class URIUtils {

	public static URI build(String url, Collection<? extends BasicNameValuePair> params) throws URISyntaxException{
		URIBuilder builder = new URIBuilder(url);
		for(BasicNameValuePair nv : params)
		{
			builder.addParameter(nv.getName(), nv.getValue());
		}
		return builder.build();
	}
}
