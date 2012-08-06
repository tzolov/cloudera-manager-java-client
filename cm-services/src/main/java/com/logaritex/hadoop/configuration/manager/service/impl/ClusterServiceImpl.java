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

import com.logaritex.hadoop.configuration.manager.domain.Cluster;
import com.logaritex.hadoop.configuration.manager.domain.ClusterList;
import com.logaritex.hadoop.configuration.manager.domain.Command;
import com.logaritex.hadoop.configuration.manager.domain.CommandList;
import com.logaritex.hadoop.configuration.manager.http.HttpService;
import com.logaritex.hadoop.configuration.manager.service.ClusterService;

public class ClusterServiceImpl implements ClusterService {

	private static final String CLUSTERS_PATH = "/api/v1/clusters";
	private static final String CLUSTER_PATH = "/api/v1/clusters/{clusterName}";
	private static final String CLUSTER_COMMANDS_PATH = "/api/v1/clusters/{clusterName}/commands";
	private static final String CLUSTER_COMMANDS_ACTION_PATH = "/api/v1/clusters/{clusterName}/commands/{action}";

	private enum ClusterCommand {
		start, stop, restart, upgradeServices;
	};

	private HttpService http;

	public ClusterServiceImpl(HttpService httpUtil) {
		this.http = httpUtil;
	}

	@Override
	public List<Cluster> getAllClusters() {

		ClusterList clusterItems = http.get(CLUSTERS_PATH, ClusterList.class);

		if (clusterItems == null || clusterItems.getItems() == null) {
			return new ArrayList<Cluster>();
		}

		return clusterItems.getItems();
	}

	@Override
	public List<Cluster> createClusters(Cluster... clusters) {

		ClusterList clusterList = http.post(CLUSTERS_PATH, new ClusterList(
				Arrays.asList(clusters)), ClusterList.class);

		if (clusterList == null || clusterList.getItems() == null) {
			return new ArrayList<Cluster>();
		}

		return clusterList.getItems();
	}

	@Override
	public Cluster getCluster(String clusterName) {
		return http.get(CLUSTER_PATH, Cluster.class, clusterName);
	}

	@Override
	public Cluster deleteCluster(String clusterName) {
		return http.delete(CLUSTER_PATH, null, Cluster.class, clusterName);
	}

	@Override
	public List<Command> getActiveClusterCommands(String clusterName) {

		CommandList commandList = http.get(CLUSTER_COMMANDS_PATH, CommandList.class, clusterName);
		
		if (commandList == null || commandList.getItems() == null) {
			return new ArrayList<Command>();
		}
		return commandList.getItems();
	}

	@Override
	public Command restartCluster(Cluster cluster) {
		return executeCommand(cluster, ClusterCommand.restart);
	}

	@Override
	public Command stopCluster(Cluster cluster) {
		return executeCommand(cluster, ClusterCommand.stop);
	}

	@Override
	public Command startCluster(Cluster cluster) {
		return executeCommand(cluster, ClusterCommand.start);
	}

	@Override
	public Command upgradeServices(Cluster cluster) {
		return executeCommand(cluster, ClusterCommand.upgradeServices);
	}

	private Command executeCommand(Cluster cluster, ClusterCommand action) {
		return http.post(CLUSTER_COMMANDS_ACTION_PATH, null, Command.class,
				cluster.getName(), ClusterCommand.upgradeServices.name());
	}
}
