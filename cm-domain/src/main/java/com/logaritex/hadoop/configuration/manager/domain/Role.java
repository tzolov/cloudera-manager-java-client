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

import java.util.List;

/**
 * A role represents a specific entity that participate in a service. Examples
 * are JobTrackers, DataNodes, HBase Masters. Each role is assigned a host where
 * it runs on.
 * 
 */
public class Role {

	public enum RoleState {
		HISTORY_NOT_AVAILABLE, UNKNOWN, STARTING, STARTED, BUSY, STOPPING, STOPPED;
	}

	/**
	 * The name of the role.
	 */
	private String name;
	/**
	 * The type of the role, e.g. NAMENODE, DATANODE, TASKTRACKER.
	 */
	private String type;
	/**
	 * 
	 */
	private boolean configStale;
	/**
	 * A reference to the host where this role runs.
	 */
	private HostRef hostRef;
	/**
	 * Readonly. A reference to the parent service.
	 */
	private ServiceRef serviceRef;
	/**
	 * Readonly. The configured run state of this role. Whether it's running,
	 * etc.
	 */
	private RoleState roleState;
	/**
	 * Readonly. The high-level health status of this role.
	 */
	private HealthSummary healthSummary;
	/**
	 * Readonly. The list of health checks of this service.
	 */
	private List<HealthCheck> healthChecks;
	/**
	 * Readonly. The HA status of this role.
	 */
	private HaStatus haStatus;
	/**
	 * Readonly. Link into the Cloudera Manager web UI for this specific role.
	 */
	private String roleUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isConfigStale() {
		return configStale;
	}

	public void setConfigStale(boolean configStale) {
		this.configStale = configStale;
	}

	public HostRef getHostRef() {
		return hostRef;
	}

	public void setHostRef(HostRef hostRef) {
		this.hostRef = hostRef;
	}

	public ServiceRef getServiceRef() {
		return serviceRef;
	}

	public void setServiceRef(ServiceRef serviceRef) {
		this.serviceRef = serviceRef;
	}

	public RoleState getRoleState() {
		return roleState;
	}

	public void setRoleState(RoleState roleState) {
		this.roleState = roleState;
	}

	public HealthSummary getHealthSummary() {
		return healthSummary;
	}

	public void setHealthSummary(HealthSummary healthSummary) {
		this.healthSummary = healthSummary;
	}

	public List<HealthCheck> getHealthChecks() {
		return healthChecks;
	}

	public void setHealthChecks(List<HealthCheck> healthChecks) {
		this.healthChecks = healthChecks;
	}

	public HaStatus getHaStatus() {
		return haStatus;
	}

	public void setHaStatus(HaStatus haStatus) {
		this.haStatus = haStatus;
	}

	public String getRoleUrl() {
		return roleUrl;
	}

	public void setRoleUrl(String roleUrl) {
		this.roleUrl = roleUrl;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", type=" + type + ", configStale"
				+ configStale + ", hostRef=" + hostRef + ", serviceRef="
				+ serviceRef + ", roleState=" + roleState + ", healthSummary="
				+ healthSummary + ", healthChecks=" + healthChecks
				+ ", haStatus=" + haStatus + ", roleUrl=" + roleUrl + "]";
	}

}
