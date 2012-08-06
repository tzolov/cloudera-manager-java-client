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
 * This is the model for a host in the system.
 * 
 * http://cloudera.github.com/cm_api/apidocs/v1/ns0_apiHost.html
 * 
 */
public class Host {

	/**
	 * A unique host identifier. Typically identical to the hostname, i.e. the
	 * host's FQDN.
	 */
	private String hostId;
	/**
	 * The host IP address. This field is not mutable after the initial
	 * creation.
	 */
	private String ipAddress;
	/**
	 * The hostname. This field is not mutable after the initial creation.
	 */
	private String hostname;
	/**
	 * The rack ID for this host.
	 */
	private String rackId;
	/**
	 * Readonly. Requires "full" view. When the host agent sent the last
	 * heartbeat.
	 */
	private String lastHeartbeat;
	/**
	 * Readonly. Requires "full" view. The list of roles assigned to this host.
	 */
	private List<RoleRef> roleRefs;
	/**
	 * Readonly. Requires "full" view. The high-level health status of this
	 * host.
	 */
	private HealthSummary healthSummary;
	/**
	 * Readonly. Requires "full" view. The list of health checks performed on
	 * the host, with their results.
	 */
	private List<HealthCheck> healthChecks;
	/**
	 * Readonly. A URL into the Cloudera Manager web UI for this specific host.
	 */
	private String hostUrl;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getRackId() {
		return rackId;
	}

	public void setRackId(String rackId) {
		this.rackId = rackId;
	}

	public String getLastHeartbeat() {
		return lastHeartbeat;
	}

	public void setLastHeartbeat(String lastHeartbeat) {
		this.lastHeartbeat = lastHeartbeat;
	}

	public List<RoleRef> getRoleRefs() {
		return roleRefs;
	}

	public void setRoleRefs(List<RoleRef> roleRefs) {
		this.roleRefs = roleRefs;
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

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	@Override
	public String toString() {
		return "Host [hostId=" + hostId + ", ipAddress=" + ipAddress
				+ ", hostname=" + hostname + ", rackId=" + rackId
				+ ", lastHeartbeat=" + lastHeartbeat + ", roleRefs=" + roleRefs
				+ ", healthSummary=" + healthSummary + ", healthChecks="
				+ healthChecks + ", hostUrl=" + hostUrl + "]";
	}
}
