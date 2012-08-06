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
package com.logaritex.hadoop.configuration.manager.domain;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * A service (such as HDFS, MapReduce, HBase) runs in a cluster. It has roles,
 * which are the actual entities (NameNode, DataNodes, etc.) that perform the
 * service's functions.
 * 
 * <br/>
 * <b>HDFS services and health checks</b>
 * <br/>
 * 
 * In CDH4, HDFS services may not present any health checks. This will happen if
 * the service has more than one nameservice configured. In those cases, the
 * health information will be available by fetching information about the
 * nameservices instead. The health summary is still available, and reflects a
 * service-wide summary.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

	public enum ServiceState {
		HISTORY_NOT_AVAILABLE, UNKNOWN, STARTING, STARTED, STOPPING, STOPPED;
	}
	
	public enum ServiceType {
		HDFS, MAPREDUCE, HBASE, OOZIE, ZOOKEEPER, HUE, YARN;
	}
	
	/**
	 * The name of the service.
	 */
	private String name;
	/**
	 * The type of the service, e.g. HDFS, MAPREDUCE, HBASE.
	 */
	private ServiceType type;
	/**
	 * Readonly. A reference to the enclosing cluster.
	 */
	private ClusterRef clusterRef;
	/**
	 * Readonly. The configured run state of this service. Whether it's running, etc.
	 */
	private ServiceState serviceState;
	/**
	 * Readonly. The high-level health status of this service.
	 */
	private HealthSummary healthSummary;
	/**
	 * Readonly. Expresses whether the service configuration is stale.
	 */
	private boolean configStale;
	/**
	 * Readonly. The list of health checks of this service.
	 */
	private boolean healthChecks[];
	/**
	 * Readonly. Link into the Cloudera Manager web UI for this specific service.
	 */
	private String serviceUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

	public boolean isConfigStale() {
		return configStale;
	}

	public void setConfigStale(boolean configStale) {
		this.configStale = configStale;
	}

	public ClusterRef getClusterRef() {
		return clusterRef;
	}

	public void setClusterRef(ClusterRef clusterRef) {
		this.clusterRef = clusterRef;
	}

	public ServiceState getServiceState() {
		return serviceState;
	}

	public void setServiceState(ServiceState serviceState) {
		this.serviceState = serviceState;
	}

	public HealthSummary getHealthSummary() {
		return healthSummary;
	}

	public void setHealthSummary(HealthSummary healthSummary) {
		this.healthSummary = healthSummary;
	}

	public boolean[] getHealthChecks() {
		return healthChecks;
	}

	public void setHealthChecks(boolean[] healthChecks) {
		this.healthChecks = healthChecks;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	@Override
	public String toString() {
		return "Service [name=" + name + ", type=" + type + ", configStale="
				+ configStale + ", clusterRef=" + clusterRef
				+ ", serviceState=" + serviceState + ", healthSummary="
				+ healthSummary + ", healthChecks="
				+ Arrays.toString(healthChecks) + ", serviceUrl=" + serviceUrl
				+ "]";
	}

}