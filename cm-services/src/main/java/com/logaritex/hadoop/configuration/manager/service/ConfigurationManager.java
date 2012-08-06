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

import com.logaritex.hadoop.configuration.manager.http.HttpService;
import com.logaritex.hadoop.configuration.manager.service.impl.ClusterServiceImpl;
import com.logaritex.hadoop.configuration.manager.service.impl.HostServiceImpl;
import com.logaritex.hadoop.configuration.manager.service.impl.ServiceServiceImpl;
import com.logaritex.hadoop.configuration.manager.service.impl.UserServiceImpl;

public class ConfigurationManager {

	private final HttpService httpService;

	private ClusterService clusterService;

	private HostService hostService;

	private UserService userService;

	private ServiceService serviceService;

	public ConfigurationManager(HttpService httpService) {
		this.httpService = httpService;
	}

	public ClusterService getClusterService() {
		if (clusterService == null) {
			clusterService = new ClusterServiceImpl(httpService);
		}

		return clusterService;
	}

	public HostService getHostService() {
		if (hostService == null) {
			hostService = new HostServiceImpl(httpService);
		}

		return hostService;
	}

	public UserService getUserService() {
		if (userService == null) {
			userService = new UserServiceImpl(httpService);
		}
		return userService;
	}

	public ServiceService getServiceService() {
		if (serviceService == null) {
			serviceService = new ServiceServiceImpl(httpService);
		}
		return serviceService;
	}
}
