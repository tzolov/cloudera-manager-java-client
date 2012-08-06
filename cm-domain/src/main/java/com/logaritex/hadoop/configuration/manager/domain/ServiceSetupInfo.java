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

/**
 * Service configuration information.
 * 
 * <br/>
 * This object is used to configure a new service, complete with a set of roles
 * and the respective configurations.
 * 
 * <br/>
 * See the parent class ApiService for minimal specification, which is simply
 * the service name and type.
 * 
 * <br/>
 * Note that all fields here are optional. The semantics of not providing a
 * value may change depending on the call being made. Refer to the documentation
 * of the appropriate call for the behavior of optional fields.
 * 
 */
public class ServiceSetupInfo extends Service {

	/**
	 * Configuration of the service being created. Optional.
	 */
	private ServiceConfig config;

	/**
	 * The list of service roles. Optional.
	 */
	private RoleSetupInfo roles;

	public ServiceConfig getConfig() {
		return config;
	}

	public void setConfig(ServiceConfig config) {
		this.config = config;
	}

	public RoleSetupInfo getRoles() {
		return roles;
	}

	public void setRoles(RoleSetupInfo roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "ServiceSetupInfo [config=" + config + ", roles=" + roles
				+ ", getName()=" + getName() + ", getType()=" + getType()
				+ ", isConfigStale()=" + isConfigStale() + ", getClusterRef()="
				+ getClusterRef() + ", getServiceState()=" + getServiceState()
				+ ", getHealthSummary()=" + getHealthSummary()
				+ ", getHealthChecks()=" + Arrays.toString(getHealthChecks())
				+ ", getServiceUrl()=" + getServiceUrl() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
}
