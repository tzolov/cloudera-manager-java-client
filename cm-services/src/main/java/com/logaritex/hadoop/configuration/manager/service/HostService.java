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

import java.util.List;

import com.logaritex.hadoop.configuration.manager.domain.Config;
import com.logaritex.hadoop.configuration.manager.domain.Host;
import com.logaritex.hadoop.configuration.manager.domain.Metric;

public interface HostService {

	/**
	 * Create one or more hosts.
	 * 
	 * <br/>
	 * You must specify at least the hostname and ipAddress in the request
	 * objects. If no hostId is specified, it will be set to the hostname. It is
	 * an error to try and create host with the same hostId as another host.
	 * 
	 * @param newHosts
	 *            The list of hosts to create
	 * @return The newly created host objects
	 */
	List<Host> create(Host... newHosts);

	/**
	 * Update an existing host in the system.
	 * 
	 * <br/>
	 * Currently, only updating the rackId is supported. All other fields of the
	 * host will be ignored. Delete a host from the system.
	 * 
	 * @param hostId
	 *            The Id of the host to update
	 * @return The updated Host
	 */
	Host update(Host host);

	/**
	 * @return Returns the hostIds for all hosts in the system.
	 */
	List<Host> getAllHosts();

	/**
	 * Returns a specific Host in the system
	 * 
	 * @param hostId
	 *            The ID of the host to read
	 * @return The Host object with the specified hostId
	 */
	Host getHost(String hostId);

	/**
	 * Delete a host from the system.
	 * 
	 * @param hostId
	 *            The Id of the host to delete
	 * @return The deleted Host
	 */
	Host delete(String hostId);

	/**
	 * Delete all hosts in the system
	 * 
	 * @return The list of deleted hosts
	 */
	List<Host> deleteAllHosts();

	/**
	 * Retrieves the configuration of a specific host.
	 * 
	 * @param hostId
	 *            The ID of the host.
	 * @return List of host configuration parameters.
	 */
	List<Config> getHostConfig(String hostId);

	/**
	 * Updates the host configuration with the given values.
	 * 
	 * <br/>
	 * If a value is set in the given configuration, it will be added to the
	 * host's configuration, replacing any existing entries. If a value is unset
	 * (its value is null), the existing configuration for the attribute will be
	 * erased, if any.
	 * 
	 * <br/>
	 * Attributes that are not listed in the input will maintain their current
	 * values in the configuration.
	 * 
	 * @param hostId
	 *            The ID of the host.
	 * @param configs
	 *            Configuration changes.
	 * @return The new host configuration.
	 */
	List<Config> updateHostConfig(String hostId, Config... configs);

	/**
	 * Fetch metric readings for a host.
	 * 
	 * <br/>
	 * By default, this call will look up all metrics available for the host. If
	 * only specific metrics are desired, use the metrics parameter.
	 * 
	 * <br/>
	 * By default, the returned results correspond to a 5 minute window based on
	 * the provided end time (which defaults to the current server time). The
	 * from and to parameters can be used to control the window being queried. A
	 * maximum window of 3 hours is enforced.
	 * 
	 * <br/>
	 * When requesting a "full" view, aside from the extended properties of the
	 * returned metric data, the collection will also contain information about
	 * all metrics available for the role, even if no readings are available in
	 * the requested window.
	 * 
	 * <br/>
	 * Host metrics also include per-network interface and per-storage device
	 * metrics. Since collecting this data incurs in more overhead, query
	 * parameters can be used to choose which network interfaces and storage
	 * devices to query, or to these metrics altogether.
	 * 
	 * <br/>
	 * Storage metrics are collected at different levels; for example, per-disk
	 * and per-partition metrics are available. The "storageIds" parameter can
	 * be used to filter specific storage IDs.
	 * 
	 * <br/>
	 * In the returned data, the network interfaces and storage IDs can be
	 * identified by looking at the "context" property of the metric objects.
	 * 
	 * TODO to handle request params?
	 * 
	 * @return List of readings from the monitors.
	 */
	List<Metric> getHostMetrics(String hostId);
}