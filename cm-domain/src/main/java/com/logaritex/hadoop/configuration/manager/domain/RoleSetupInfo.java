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

/**
 * Role configuration information used when creating services.
 * 
 */
public class RoleSetupInfo extends Role {

	/**
	 * The role configuration. Optional.
	 */
	private ConfigList config;

	public ConfigList getConfig() {
		return config;
	}

	public void setConfig(ConfigList config) {
		this.config = config;
	}

	@Override
	public String toString() {
		return "RoleSetupInfo [config=" + config + ", getName()=" + getName()
				+ ", getType()=" + getType() + ", isConfigStale()="
				+ isConfigStale() + ", getHostRef()=" + getHostRef()
				+ ", getServiceRef()=" + getServiceRef() + ", getRoleState()="
				+ getRoleState() + ", getHealthSummary()=" + getHealthSummary()
				+ ", getHealthChecks()=" + getHealthChecks()
				+ ", getHaStatus()=" + getHaStatus() + ", getRoleUrl()="
				+ getRoleUrl() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
