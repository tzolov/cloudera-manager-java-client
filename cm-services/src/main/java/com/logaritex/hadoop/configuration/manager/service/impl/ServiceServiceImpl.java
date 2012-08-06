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
package com.logaritex.hadoop.configuration.manager.service.impl;

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
import com.logaritex.hadoop.configuration.manager.service.HttpService;
import com.logaritex.hadoop.configuration.manager.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {

	private static final String SERVICES_URL = "/api/v1/clusters/{clusterName}/services";
	private static final String SERVICE_URL = "/api/v1/clusters/{clusterName}/services/{serviceName}";
	private static final String SERVICE_CONFING = "/api/v1/clusters/{clusterName}/services/{serviceName}/config?view={view}";
	private static final String SERVICE_COMMANDS_URL = "/api/v1/clusters/{clusterName}/services/{serviceName}/commands";
	private static final String SERVICE_COMMANDS_ACTION_URL = "/api/v1/clusters/{clusterName}/services/{serviceName}/commands/{command}";

	private static final String ROLES = "/api/v1/clusters/{clusterName}/services/{serviceName}/roles";
	private static final String ROLE_TYPES = "/api/v1/clusters/{clusterName}/services/{serviceName}/roleTypes";
	private static final String ROLE_COMMAND = "/api/v1/clusters/{clusterName}/services/{serviceName}/roleCommands/{command}";

	private final HttpService http;

	public ServiceServiceImpl(HttpService httpService) {
		this.http = httpService;
	}

	@Override
	public List<Service> getAllServices(String clusterName) {

		ServiceList serviceItems = http.get(SERVICES_URL, ServiceList.class, clusterName);

		if (serviceItems == null || serviceItems.getItems() == null) {
			return new ArrayList<Service>();
		}

		return serviceItems.getItems();
	}

	@Override
	public List<Service> createServices(String clusterName, Service... services) {

		ServiceList serviceList = http.post(SERVICES_URL, new ServiceList(Arrays.asList(services)), ServiceList.class,
				clusterName);

		if (serviceList == null || serviceList.getItems() == null) {
			return new ArrayList<Service>();
		}

		return serviceList.getItems();
	}

	@Override
	public Service deleteService(String clusterName, String serviceName) {
		return http.delete(SERVICE_URL, null, Service.class, clusterName, serviceName);
	}

	@Override
	public Service getService(String clusterName, String serviceName) {
		return http.get(SERVICE_URL, Service.class, clusterName, serviceName);
	}

	@Override
	public ServiceConfig getServiceConfiguration(Service service, View view) {

		ServiceConfig serviceConfigs = http.get(SERVICE_CONFING, ServiceConfig.class, service.getClusterRef()
				.getClusterName(), service.getName(), view.name());

		if (serviceConfigs == null || serviceConfigs.getItems() == null) {
			return null;
		}

		return serviceConfigs;
	}

	@Override
	public ServiceConfig updateServiceConfiguration(Service service, ServiceConfig config) {

		ServiceConfig serviceConfigs = http.put(SERVICE_CONFING, config, ServiceConfig.class, service.getClusterRef()
				.getClusterName(), service.getName(), View.full.name());

		if (serviceConfigs == null || serviceConfigs.getItems() == null) {
			return null;
		}

		return serviceConfigs;
	}

	@Override
	public List<Command> getActiveServiceCommands(String clusterName, String serviceName) {

		CommandList commandList = http.get(SERVICE_COMMANDS_URL, CommandList.class, clusterName, serviceName);

		if (commandList == null || commandList.getItems() == null) {
			return new ArrayList<Command>();
		}
		return commandList.getItems();
	}

	@Override
	public Command startService(Service service) {
		return executeActionCommand(service, ServiceCommand.start);
	}

	@Override
	public Command stopService(Service service) {
		return executeActionCommand(service, ServiceCommand.stop);
	}

	@Override
	public Command restartService(Service service) {
		return executeActionCommand(service, ServiceCommand.restart);
	}

	@Override
	public Command zooKeeperCleanup(Service service) {
		return executeActionCommand(service, ServiceCommand.zooKeeperCleanup);
	}

	@Override
	public Command zooKeeperInit(Service service) {
		return executeActionCommand(service, ServiceCommand.zooKeeperInit);
	}

	private Command executeActionCommand(Service service, ServiceCommand command) {
		return http.post(SERVICE_COMMANDS_ACTION_URL, null, Command.class, service.getClusterRef().getClusterName(),
				service.getName(), command.name());
	}

	//
	// Roles
	//
	@Override
	public List<String> getRoleTypes(Service service) {

		RoleTypeList roleList = http.get(ROLE_TYPES, RoleTypeList.class, service.getClusterRef().getClusterName(),
				service.getName());

		if (roleList == null || roleList.getItems() == null) {
			return null;
		}

		return roleList.getItems();
	}

	@Override
	public List<Role> getRoles(Service service) {

		RoleList roleList = http
				.get(ROLES, RoleList.class, service.getClusterRef().getClusterName(), service.getName());

		if (roleList == null || roleList.getItems() == null) {
			return null;
		}

		return roleList.getItems();
	}

	@Override
	public BulkCommandList startRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.start);
	}

	@Override
	public BulkCommandList stopRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.stop);
	}

	@Override
	public BulkCommandList restartRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.restart);
	}

	@Override
	public BulkCommandList refreshRoles(List<Role> roles) {
		return rolesCommand(roles, RoleCommand.refresh);
	}

	private BulkCommandList rolesCommand(List<Role> roles, RoleCommand command) {
		if (roles != null && roles.size() > 0) {
			ServiceRef serviceRef = roles.get(0).getServiceRef();
			RoleNames roleNames = RoleNames.fromRoles(roles);

			return http.post(ROLE_COMMAND, roleNames, BulkCommandList.class, serviceRef.getClusterName(),
					serviceRef.getServiceName(), command.name());
		}

		return null;
	}
}
