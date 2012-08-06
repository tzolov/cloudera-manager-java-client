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
package com.logaritex.hadoop.configuration.manager.service;

/**
 * Abstraction layer over the standard HTTP Create-Read-Update-Delete (CRUD) semantics. The HTTP request path defines
 * the entity to be acted on and the HTTP method expresses the type of action to perform.
 * 
 * This allows alternative implementations for alternative platforms (e.g. desktop or mobile) to be implementers 
 * 
 */
public interface HttpService {

	/**
	 * Read resource entries
	 * 
	 * @param url
	 *            Resource URL
	 * @param responseType
	 *            Type of the result
	 * @param uriVariables
	 *            Request parameters
	 * @return Read resource entries
	 */
	<R> R get(String url, Class<R> responseType, Object... uriVariables);

	/**
	 * Wraps a HTTP POST request to create entries from the provided responseType.
	 * 
	 * @param url
	 *            Resource URL
	 * @param request
	 *            Content being submitted.
	 * @param responseType
	 *            Type of the responded types
	 * @param uriVariables
	 *            Optional request parameters
	 * @return Returns the status of the POST operation
	 */
	<R> R post(String url, Object request, Class<R> responseType, Object... uriVariables);

	/**
	 * Delete entries
	 * 
	 * @param url Resource URL
	 * @param request
	 * @param responseType
	 * @param uriVariables
	 * @return Delete entries
	 */
	<R> R delete(String url, Object request, Class<R> responseType, Object... uriVariables);

	/**
	 * Update or edit entries
	 * 
	 * @param url ResourceURL
	 * @param request
	 * @param responseType
	 * @param uriVariables
	 * @return
	 */
	<R> R put(String url, Object request, Class<R> responseType, Object... uriVariables);
}