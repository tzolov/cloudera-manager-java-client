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

import com.logaritex.hadoop.configuration.manager.domain.BulkCommandList;
import com.logaritex.hadoop.configuration.manager.domain.Command;
import com.logaritex.hadoop.configuration.manager.domain.CommandList;
import com.logaritex.hadoop.configuration.manager.domain.Role;
import com.logaritex.hadoop.configuration.manager.domain.RoleList;
import com.logaritex.hadoop.configuration.manager.domain.RoleNames;
import com.logaritex.hadoop.configuration.manager.domain.RoleTypeList;
import com.logaritex.hadoop.configuration.manager.domain.Service;
import com.logaritex.hadoop.configuration.manager.domain.ServiceConfig;
import com.logaritex.hadoop.configuration.manager.domain.ServiceList;
import com.logaritex.hadoop.configuration.manager.domain.ServiceRef;
import com.logaritex.hadoop.configuration.manager.http.HttpService;

public class ServiceService {

	private static final String SERVICES_URL = "/api/v1/clusters/{clusterName}/services";
	private static final String SERVICE_URL = "/api/v1/clusters/{clusterName}/services/{serviceName}";
	private static final String SERVICE_CONFING = "/api/v1/clusters/{clusterName}/services/{serviceName}/config?view={view}";
	private static final String SERVICE_COMMANDS_URL = "/api/v1/clusters/{clusterName}/services/{serviceName}/commands";
	private static final String SERVICE_COMMANDS_ACTION_URL = "/api/v1/clusters/{clusterName}/services/{serviceName}/commands/{command}";

	private static final String ROLES = "/api/v1/clusters/{clusterName}/services/{serviceName}/roles";
	private static final String ROLE_TYPES = "/api/v1/clusters/{clusterName}/services/{serviceName}/roleTypes";
	private static final String ROLE_COMMAND = "/api/v1/clusters/{clusterName}/services/{serviceName}/roleCommands/{command}";

	public enum View {
		summary, full
	}

	public enum ServiceCommand {
		start, stop, restart,

		decommission, deployClientConfig, hbaseCreateRoot,

		hdfsDisableAutoFailover, hdfsDisableHa, hdfsEnableAutoFailover, hdfsEnableHa, hdfsFailover,

		hueCreateHiveWarehouse,

		zooKeeperCleanup, zooKeeperInit;
	}

	public enum RoleCommand {
		start, stop, restart, refresh;
	}

	private final HttpService http;

	public ServiceService(HttpService httpService) {
		this.http = httpService;
	}

	/**
	 * Lists all services registered in the cluster.
	 * 
	 * @param clusterName
	 *            The name of the cluster
	 * @return List of services
	 */
	public List<Service> getAllServices(String clusterName) {

		ServiceList serviceItems = http.get(SERVICES_URL, ServiceList.class,
				clusterName);

		if (serviceItems == null || serviceItems.getItems() == null) {
			return new ArrayList<Service>();
		}

		return serviceItems.getItems();
	}

	/**
	 * Creates a list of services.
	 * 
	 * There are typically two service creation strategies:
	 * 
	 * <br/>
	 * 1. The caller may choose to set up a new service piecemeal, by first
	 * creating the service itself (without any roles or configuration), and
	 * then create the roles, and then specify configuration.
	 * 
	 * <br/>
	 * 2. Alternatively, the caller can pack all the information in one call, by
	 * fully specifying the fields in the ApiServiceSetupInfo object, with
	 * <ul>
	 * <li>service config and role type config, and</li>
	 * <li>role to host assignment.</li>
	 * </ul>
	 * 
	 * @param clusterName
	 *            The name of the cluster.
	 * @param services
	 *            Details of the services to create.
	 * @return List of created services.
	 */
	public List<Service> createServices(String clusterName, Service... services) {

		ServiceList serviceList = http.post(SERVICES_URL, new ServiceList(
				Arrays.asList(services)), ServiceList.class, clusterName);

		if (serviceList == null || serviceList.getItems() == null) {
			return new ArrayList<Service>();
		}

		return serviceList.getItems();
	}

	/**
	 * Deletes a service from the system.
	 * 
	 * @param clusterName
	 *            The name of the cluster
	 * @param serviceName
	 *            The name of the service to delete
	 * @return The details of the deleted services
	 */
	public Service deleteService(String clusterName, String serviceName) {
		return http.delete(SERVICE_URL, null, Service.class, clusterName,
				serviceName);
	}

	/**
	 * Retrieves details information about a service.
	 * 
	 * @param clusterName
	 *            The name of the cluster
	 * @param serviceName
	 *            The name of the service
	 * @return The details of the service.
	 */
	public Service getService(String clusterName, String serviceName) {
		return http.get(SERVICE_URL, Service.class, clusterName, serviceName);
	}

	/**
	 * Retrieves the configuration of a specific service.
	 * 
	 * <br/>
	 * The "summary" view contains only the configured parameters, and
	 * configuration for role types that contain configured parameters.
	 * 
	 * <br/>
	 * The "full" view contains all available configuration parameters for the
	 * service and its role types. This mode performs validation on the
	 * configuration, which could take a few seconds on a large cluster (around
	 * 500 nodes or more).
	 * 
	 * @param service
	 *            The service to query.
	 * @param view
	 *            The view of the data to materialize, either "summary" or
	 *            "full".
	 * @return List of service and role types configuration parameters.
	 */
	public ServiceConfig getServiceConfiguration(Service service, View view) {

		ServiceConfig serviceConfigs = http.get(SERVICE_CONFING,
				ServiceConfig.class, service.getClusterRef().getClusterName(),
				service.getName(), view.name());

		if (serviceConfigs == null || serviceConfigs.getItems() == null) {
			return null;
		}

		return serviceConfigs;
	}

	/**
	 * Updates the service configuration with the given values.
	 * 
	 * <br/>
	 * If a value is set in the given configuration, it will be added to the
	 * service's configuration, replacing any existing entries. If a value is
	 * unset (its value is null), the existing configuration for the attribute
	 * will be erased, if any.
	 * 
	 * <br/>
	 * Attributes that are not listed in the input will maintain their current
	 * values in the configuration.
	 * 
	 * @param service
	 *            The service to modify
	 * @param config
	 *            Configuration changes
	 * @return The new service configuration
	 */
	public ServiceConfig updateServiceConfiguration(Service service,
			ServiceConfig config) {

		ServiceConfig serviceConfigs = http.put(SERVICE_CONFING, config,
				ServiceConfig.class, service.getClusterRef().getClusterName(),
				service.getName(), View.full.name());

		if (serviceConfigs == null || serviceConfigs.getItems() == null) {
			return null;
		}

		return serviceConfigs;
	}

	/**
	 * List active service commands.
	 * 
	 * @param clusterName
	 *            The name of the cluster.
	 * @param serviceName
	 *            The name of the service.
	 * @return A list of active service commands.
	 */
	public List<Command> getActiveServiceCommands(String clusterName,
			String serviceName) {

		CommandList commandList = http.get(SERVICE_COMMANDS_URL,
				CommandList.class, clusterName, serviceName);

		if (commandList == null || commandList.getItems() == null) {
			return new ArrayList<Command>();
		}
		return commandList.getItems();
	}

	/**
	 * Start the service
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field
	 *            of the service must be provided as well
	 * @return Information about the submitted command
	 */
	public Command start(Service service) {
		return executeActionCommand(service, ServiceCommand.start);
	}

	/**
	 * Stop the service
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field
	 *            of the service must be provided as well
	 * @return Information about the submitted command
	 */
	public Command stop(Service service) {
		return executeActionCommand(service, ServiceCommand.stop);
	}

	/**
	 * Restart the service
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field
	 *            of the service must be provided as well
	 * @return Information about the submitted command
	 */
	public Command restart(Service service) {
		return executeActionCommand(service, ServiceCommand.restart);
	}

	/**
	 * Clean up all running server instances of a ZooKeeper service. <br/>
	 * 
	 * This command removes snapshots and transaction log files kept by
	 * ZooKeeper for backup purposes. Refer to the ZooKeeper documentation for
	 * more details
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field
	 *            of the service must be provided as well
	 * @return Information about the submitted command
	 */
	public Command zooKeeperCleanup(Service service) {
		return executeActionCommand(service, ServiceCommand.zooKeeperCleanup);
	}

	/**
	 * Initializes all the server instances of a ZooKeeper service.
	 * 
	 * <br/>
	 * This command applies to ZooKeeper services from CDH4. ZooKeeper server
	 * roles need to be initialized before they can be used.
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field
	 *            of the service must be provided as well
	 * @return Information about the submitted command
	 */
	public Command zooKeeperInit(Service service) {
		return executeActionCommand(service, ServiceCommand.zooKeeperInit);
	}

	private Command executeActionCommand(Service service, ServiceCommand command) {
		return http.post(SERVICE_COMMANDS_ACTION_URL, null, Command.class,
				service.getClusterRef().getClusterName(), service.getName(),
				command.name());
	}

	// Roles

	public List<String> getRoleTypes(Service service) {

		RoleTypeList roleList = http.get(ROLE_TYPES, RoleTypeList.class,
				service.getClusterRef().getClusterName(), service.getName());

		if (roleList == null || roleList.getItems() == null) {
			return null;
		}

		return roleList.getItems();
	}

	public List<Role> getRoles(Service service) {

		RoleList roleList = http.get(ROLES, RoleList.class, service
				.getClusterRef().getClusterName(), service.getName());

		if (roleList == null || roleList.getItems() == null) {
			return null;
		}

		return roleList.getItems();
	}

	public BulkCommandList startRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.start);
	}

	public BulkCommandList stopRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.stop);
	}

	public BulkCommandList restartRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.restart);
	}

	public BulkCommandList refreshRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.refresh);
	}

	private BulkCommandList rolesCommand(List<Role> roles, RoleCommand command) {
		if (roles != null && roles.size() > 0) {
			ServiceRef serviceRef = roles.get(0).getServiceRef();
			RoleNames roleNames = RoleNames.fromRoles(roles);

			return http.post(ROLE_COMMAND, roleNames, BulkCommandList.class,
					serviceRef.getClusterName(), serviceRef.getServiceName(),
					command.name());
		}

		return null;
	}
}
