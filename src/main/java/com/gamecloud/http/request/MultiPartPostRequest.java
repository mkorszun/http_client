package com.gamecloud.http.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;

import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.gamecloud.http.response.Response;

/**
 * 
 * @author mateusz
 *
 */
public class MultiPartPostRequest {
	
	/**
	 * Http multipart post request
	 * 
	 * @param url
	 * @param params
	 * @return {@link Response} 
	 * @throws {@link IOException}
	 * @throws {@link RequestException}
	 */
	public Response request(URL url, Collection<? extends BasicNameValuePair> params) 
			throws RequestException, IOException{
		return request(new DefaultHttpClient(), url, params, Collections.<ContentBody> emptyList());
	}
	
	/**
	 * Http multipart post request
	 * 
	 * @param url
	 * @param params
	 * @param files
	 * @return {@link Response}
	 * @throws {@link IOException} 
	 * @throws {@link RequestException}
	 */
	public Response request(URL url, Collection<? extends BasicNameValuePair> params, 
			Collection<? extends ContentBody> files) throws RequestException, IOException{
		return request(new DefaultHttpClient(), url, params, files);
	}
	
	/**
	 * Http multipart post request
	 * 
	 * @param client
	 * @param url
	 * @param params
	 * @param files
	 * @return {@link Response}
	 * @throws {@link IOException} 
	 * @throws {@link RequestException}
	 */
	public Response request(HttpClient client, URL url, Collection<? extends BasicNameValuePair> params, 
			Collection<? extends ContentBody> files) throws RequestException, IOException{
		try{
			MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			for(BasicNameValuePair p : params){
				entity.addPart(p.getName(), new StringBody(p.getValue().toString()));
			}
			for(ContentBody f : files){
				entity.addPart(f.getFilename(), f);
			}
			HttpPost httpPost = new HttpPost(url.toString());
			httpPost.setEntity(entity);
			HttpResponse response = client.execute(httpPost);
			return new Response(response);
		} catch(UnsupportedEncodingException e){
			throw new RequestException("Failed to decode property: "+e.getMessage());
		} catch (ClientProtocolException e) {
			throw new RequestException("Http connection problem: "+e.getMessage());
		}
	}
}