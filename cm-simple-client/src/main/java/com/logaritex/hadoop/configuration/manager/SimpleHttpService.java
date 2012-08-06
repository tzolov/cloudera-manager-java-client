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
package com.logaritex.hadoop.configuration.manager;

import java.util.Collections;

import org.codehaus.jackson.Base64Variants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.logaritex.hadoop.configuration.manager.service.HttpService;

public class SimpleHttpService implements HttpService {

	private final String baseUrl;

	private final HttpHeaders httpHeaders;

	private final RestTemplate restTemplate;

	private int requestTimeout = 5000; // 5 [sec]

	public SimpleHttpService(String hostName, int port, String username, String password) {
		this(String.format("%s:si", hostName, port), username, password);
	}

	public SimpleHttpService(String baseUrl, String username, String password) {
		this.baseUrl = baseUrl;
		httpHeaders = createHttpHeaders(username, password);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(requestTimeout);
		requestFactory.setConnectTimeout(requestTimeout);

		restTemplate = new RestTemplate(requestFactory);

		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

	}

	@Override
	public <R> R get(String url, Class<R> clazz, Object... params) {

		R response = restTemplate.exchange(baseUrl + url, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), clazz,
				params).getBody();

		return response;
	}

	@Override
	public <R> R post(String url, Object request, Class<R> responseType, Object... uriVariables) {

		R response = restTemplate.exchange(baseUrl + url, HttpMethod.POST,
				new HttpEntity<Object>(request, httpHeaders), responseType, uriVariables).getBody();

		return response;
	}

	@Override
	public <R> R delete(String url, Object request, Class<R> responseType, Object... uriVariables) {

		R response = restTemplate.exchange(baseUrl + url, HttpMethod.DELETE,
				new HttpEntity<Object>(request, httpHeaders), responseType, uriVariables).getBody();

		return response;
	}

	@Override
	public <R> R put(String url, Object request, Class<R> responseType, Object... uriVariables) {

		R response = restTemplate.exchange(baseUrl + url, HttpMethod.PUT, new HttpEntity<Object>(request, httpHeaders),
				responseType, uriVariables).getBody();

		return response;
	}

	private static HttpHeaders createHttpHeaders(String username, String password) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept-Encoding", "gzip");
		httpHeaders.set("Authorization", getBasicAuthHeaderValue(username, password));
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		return httpHeaders;
	}

	private static String getBasicAuthHeaderValue(String username, String password) {

		byte[] bytes = String.format("%s:%s", username, password).getBytes();
		return String.format("Basic %s", Base64Variants.getDefaultVariant().encode(bytes, false));
	}
}
