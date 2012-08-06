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

import com.logaritex.hadoop.configuration.manager.domain.Cluster;
import com.logaritex.hadoop.configuration.manager.domain.Command;

public interface ClusterService {

	/**
	 * @return Lists all known clusters.
	 */
	List<Cluster> getAllClusters();

	/**
	 * Creates a collection of clusters.
	 * 
	 * @param clusters
	 *            List of clusters to created.
	 * @return List of created clusters.
	 */
	List<Cluster> createClusters(Cluster... clusters);

	/**
	 * Reads information about a cluster.
	 * 
	 * @param clusterName
	 *            Name of cluster to look up.
	 * @return Details of requested cluster.
	 */
	Cluster getCluster(String clusterName);

	/**
	 * Deletes a cluster.
	 * 
	 * @param clusterName
	 *            Name of cluster to delete.
	 * @return Details of deleted cluster.
	 */
	Cluster deleteCluster(String clusterName);

	/**
	 * List active cluster commands.
	 * 
	 * @param clusterName
	 *            The name of the cluster.
	 * @return A list of active cluster commands.
	 */
	List<Command> getActiveClusterCommands(String clusterName);

	/**
	 * Restart all services in the cluster.
	 * 
	 * <br/>
	 * Services are restarted in the appropriate order given their dependencies.
	 * 
	 * @param cluster
	 *            The name of the cluster.
	 * @return Information about the submitted command.
	 */
	Command restartCluster(Cluster cluster);

	/**
	 * Stop all services in the cluster.
	 * 
	 * <br/>
	 * Services are stopped in the appropriate order given their dependencies.
	 * 
	 * @param cluster
	 *            The name of the cluster.
	 * @return Information about the submitted command.
	 */
	Command stopCluster(Cluster cluster);

	/**
	 * Start all services in the cluster.
	 * 
	 * <br/>
	 * Services are started in the appropriate order given their dependencies.
	 * 
	 * @param cluster
	 *            The name of the cluster.
	 * @return Information about the submitted command.
	 */
	Command startCluster(Cluster cluster);

	/**
	 * Upgrades the services in the cluster to the CDH4 version.
	 * 
	 * <br/>
	 * This command requires that all services in the cluster are stopped, and
	 * that the CDH packages in the hosts used by the cluster be upgraded to
	 * CDH4 before the command is issued.
	 * 
	 * <br/>
	 * The command will upgrade the services and their configuration to the
	 * version available in the CDH4 distribution.
	 * 
	 * @param cluster
	 *            The name of the cluster.
	 * @return Information about the submitted command.
	 */
	Command upgradeServices(Cluster cluster);
}