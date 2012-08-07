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
 * Provides information about an HDFS nameservice.
 * 
 * Nameservices can be either a stand-alone NameNode, a NameNode paired with a SecondaryNameNode, or a high-availability
 * pair formed by an active and a stand-by NameNode.
 * 
 * <br/>
 * The following fields are only available in the object's full view:
 * 
 * <ul>
 * <li>healthSummary</li>
 * <li>healthChecks</li>
 * </ul>
 */
public class Nameservice {

	/**
	 * Name of the nameservice.
	 */
	private String name;
	/**
	 * Reference to the active NameNode.
	 */
	private RoleRef active;
	/**
	 * Reference to the active NameNode's failover controller, if configured.
	 */
	private RoleRef activeFailoverController;
	/**
	 * Reference to the stand-by NameNode.
	 */
	private RoleRef standBy;
	/**
	 * Reference to the stand-by NameNode's failover controller, if configured.
	 */
	private RoleRef standByFailoverController;
	/**
	 * Reference to the SecondaryNameNode.
	 */
	private RoleRef secondary;
	/**
	 * Mount points assigned to this nameservice in a federation.
	 */
	private List<String> mountPoints;
	/**
	 * Requires "full" view. The high-level health status of this nameservice.
	 */
	private HealthSummary healthSummary;
	/**
	 * Requires "full" view. List of health checks performed on the nameservice.
	 */
	private List<HealthCheck> healthChecks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleRef getActive() {
		return active;
	}

	public void setActive(RoleRef active) {
		this.active = active;
	}

	public RoleRef getActiveFailoverController() {
		return activeFailoverController;
	}

	public void setActiveFailoverController(RoleRef activeFailoverController) {
		this.activeFailoverController = activeFailoverController;
	}

	public RoleRef getStandBy() {
		return standBy;
	}

	public void setStandBy(RoleRef standBy) {
		this.standBy = standBy;
	}

	public RoleRef getStandByFailoverController() {
		return standByFailoverController;
	}

	public void setStandByFailoverController(RoleRef standByFailoverController) {
		this.standByFailoverController = standByFailoverController;
	}

	public RoleRef getSecondary() {
		return secondary;
	}

	public void setSecondary(RoleRef secondary) {
		this.secondary = secondary;
	}

	public List<String> getMountPoints() {
		return mountPoints;
	}

	public void setMountPoints(List<String> mountPoints) {
		this.mountPoints = mountPoints;
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

	@Override
	public String toString() {
		return "Nameservice [name=" + name + ", active=" + active + ", activeFailoverController="
				+ activeFailoverController + ", standBy=" + standBy + ", standByFailoverController="
				+ standByFailoverController + ", secondary=" + secondary + ", mountPoints=" + mountPoints
				+ ", healthSummary=" + healthSummary + ", healthChecks=" + healthChecks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nameservice other = (Nameservice) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
