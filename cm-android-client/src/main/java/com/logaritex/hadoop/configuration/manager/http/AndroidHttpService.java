/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.logaritex.hadoop.configuration.manager.http;

import java.util.Collections;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.logaritex.hadoop.configuration.manager.service.HttpService;

public class AndroidHttpService implements HttpService {
	
	public static final int DEFAULT_CONNECTION_TIMEOUT_MS = 5000; // ~5 seconds

	private final String baseUrl;

	private final HttpHeaders httpHeaders;

	private RestTemplate restTemplate;	

	public AndroidHttpService(String hostName, int port, String username,
			String password, int connectionTimeout) {
		this(String.format("http://%s:%s", hostName.trim(), port), username,
				password, connectionTimeout);
	}

	public AndroidHttpService(String baseUrl, String username, String password, int connectionTimeout) {
		this.baseUrl = baseUrl;
		httpHeaders = createHttpHeaders(username, password);

		// Initialize a request factory, setting the request timeout
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(connectionTimeout);
		requestFactory.setConnectTimeout(0);

		restTemplate = new RestTemplate(requestFactory);

		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());
	}

	public <R> R get(String url, Class<R> responseType, Object... uriVariables) {

		R response = restTemplate
				.exchange(baseUrl + url, HttpMethod.GET,
						new HttpEntity<Object>(httpHeaders), responseType,
						uriVariables).getBody();

		return response;
	}

	public <R> R post(String url, Object request, Class<R> responseType,
			Object... uriVariables) {

		R response = restTemplate.exchange(baseUrl + url, HttpMethod.POST,
				new HttpEntity<Object>(request, httpHeaders), responseType,
				uriVariables).getBody();

		return response;
	}

	public <R> R delete(String url, Object request, Class<R> responseType,
			Object... uriVariables) {
		R response = restTemplate.exchange(baseUrl + url, HttpMethod.DELETE,
				new HttpEntity<Object>(request, httpHeaders), responseType,
				uriVariables).getBody();

		return response;
	}

	public <R> R put(String url, Object request, Class<R> responseType,
			Object... uriVariables) {

		R response = restTemplate.exchange(baseUrl + url, HttpMethod.PUT,
				new HttpEntity<Object>(request, httpHeaders), responseType,
				uriVariables).getBody();

		return response;
	}

	private HttpHeaders createHttpHeaders(String username, String password) {

		HttpBasicAuthentication authHeader = new HttpBasicAuthentication(
				username, password);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAuthorization(authHeader);
		httpHeaders.setAccept(Collections
				.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setAcceptEncoding(ContentCodingType.GZIP);

		return httpHeaders;
	}	
}
