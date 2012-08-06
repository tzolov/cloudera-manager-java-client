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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.logaritex.hadoop.configuration.manager.domain.Config;
import com.logaritex.hadoop.configuration.manager.domain.ConfigList;
import com.logaritex.hadoop.configuration.manager.domain.Host;
import com.logaritex.hadoop.configuration.manager.domain.HostList;
import com.logaritex.hadoop.configuration.manager.domain.Metric;
import com.logaritex.hadoop.configuration.manager.domain.MetricList;
import com.logaritex.hadoop.configuration.manager.http.HttpService;

public class HostServiceImpl implements HostService {

	private final static String HOSTS_PATH = "/api/v1/hosts?view=full";
	private final static String HOST_PATH = "/api/v1/hosts/{hostId}";
	private final static String HOST_CONFIG_PATH = "/api/v1/hosts/{hostId}/config";
	private final static String HOST_METRICS_PATH = "/api/v1/hosts/{hostId}/metrics";

	private final HttpService http;

	public HostServiceImpl(HttpService httpService) {
		this.http = httpService;
	}

	@Override
	public List<Host> create(Host... newHosts) {

		HostList hostList = http.post(HOSTS_PATH,
				new HostList(Arrays.asList(newHosts)), HostList.class);

		if (hostList == null || hostList.getItems() == null) {
			return new ArrayList<Host>();
		}

		return hostList.getItems();
	}

	@Override
	public Host update(Host host) {
		return http.put(HOST_PATH, host, Host.class, host.getHostId());
	}

	@Override
	public List<Host> getAllHosts() {

		HostList hostList = http.get(HOSTS_PATH, HostList.class);

		if (hostList == null || hostList.getItems() == null) {
			return new ArrayList<Host>();
		}

		return hostList.getItems();
	}

	@Override
	public Host getHost(String hostId) {
		return http.get(HOST_PATH, Host.class, hostId);
	}

	@Override
	public Host delete(String hostId) {
		return http.delete(HOST_PATH, null, Host.class, hostId);
	}

	@Override
	public List<Host> deleteAllHosts() {

		HostList hostList = http.delete(HOSTS_PATH, null, HostList.class);

		if (hostList == null || hostList.getItems() == null) {
			return new ArrayList<Host>();
		}

		return hostList.getItems();

	}

	//
	// Host Configuration
	//

	@Override
	public List<Config> getHostConfig(String hostId) {

		ConfigList configList = http.get(HOST_CONFIG_PATH, ConfigList.class,
				hostId);
		if (configList != null) {
			return configList.getItems();
		}
		return null;
	}

	@Override
	public List<Config> updateHostConfig(String hostId, Config... configs) {

		ConfigList configList = http.put(HOST_CONFIG_PATH, new ConfigList(
				Arrays.asList(configs)), ConfigList.class, hostId);

		if (configList != null) {
			return configList.getItems();
		}
		return null;
	}

	//
	// Host Metrics
	//
	@Override
	public List<Metric> getHostMetrics(String hostId) {

		MetricList metricList = http.get(HOST_METRICS_PATH, MetricList.class,
				hostId);

		if (metricList == null || metricList.getItems() == null) {
			return new ArrayList<Metric>();
		}

		return metricList.getItems();
	}
}
