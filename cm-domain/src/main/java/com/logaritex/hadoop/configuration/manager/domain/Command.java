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
 * Provides detailed information about a submitted command.
 * 
 * http://cloudera.github.com/cm_api/apidocs/v1/ns0_apiCommand.html
 */
public class Command {

	/**
	 * The command ID.
	 */
	private String id;
	/**
	 * The command name.
	 */
	private String name;
	/**
	 * The start time.
	 */
	private String startTime;
	/**
	 * The end time, if the command is finished.
	 */
	private String endTime;
	/**
	 * Whether the command is currently active.
	 */
	private boolean active;
	/**
	 * If the command is finished, whether it was successful.
	 */
	private boolean success;
	/**
	 * If the command is finished, the result message.
	 */
	private String resultMessage;
	/**
	 * URL to the command's downloadable result data, if any exists.
	 */
	private String resultDataUrl;
	/**
	 * Reference to the service (for service commands only).
	 */
	private ServiceRef serviceRef;
	/**
	 * Reference to the role (for role commands only).
	 */
	private RoleRef roleRef;
	/**
	 * Reference to the host (for host commands only).
	 */
	private HostRef hostRef;
	/**
	 * Reference to the parent command, if any
	 */
	private Command parent;
	/**
	 * List of child commands. Only available in the full view. The list
	 * contains only the summary view of the children.
	 */
	private CommandList children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getResultDataUrl() {
		return resultDataUrl;
	}

	public void setResultDataUrl(String resultDataUrl) {
		this.resultDataUrl = resultDataUrl;
	}

	public ServiceRef getServiceRef() {
		return serviceRef;
	}

	public void setServiceRef(ServiceRef serviceRef) {
		this.serviceRef = serviceRef;
	}

	public RoleRef getRoleRef() {
		return roleRef;
	}

	public void setRoleRef(RoleRef roleRef) {
		this.roleRef = roleRef;
	}

	public HostRef getHostRef() {
		return hostRef;
	}

	public void setHostRef(HostRef hostRef) {
		this.hostRef = hostRef;
	}

	public Command getParent() {
		return parent;
	}

	public void setParent(Command parent) {
		this.parent = parent;
	}

	public CommandList getChildren() {
		return children;
	}

	public void setChildren(CommandList children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Command [id=" + id + ", name=" + name + ", startTime="
				+ startTime + ", endTime=" + endTime + ", active=" + active
				+ ", success=" + success + ", resultMessage=" + resultMessage
				+ ", resultDataUrl=" + resultDataUrl + ", serviceRef="
				+ serviceRef + ", roleRef=" + roleRef + ", hostRef=" + hostRef
				+ ", parent=" + parent + ", children=" + children + "]";
	}

}
