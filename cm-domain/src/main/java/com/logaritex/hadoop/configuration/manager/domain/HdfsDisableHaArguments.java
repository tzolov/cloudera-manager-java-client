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
 * Arguments used for the HDFS disable HA command.
 * 
 */
public class HdfsDisableHaArguments {

	/**
	 * Name of the the NameNode to be kept.
	 */
	private String activeName;
	/**
	 * Name of the SecondaryNamenode to associate with the active NameNode.
	 */
	private String secondaryName;
	/**
	 * Whether to re-start dependent services. Defaults to true.
	 */
	private boolean startDependentServices;
	/**
	 * Whether to re-deploy client configurations. Defaults to true.
	 */
	private boolean deployClientConfigs;

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getSecondaryName() {
		return secondaryName;
	}

	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}

	public boolean isStartDependentServices() {
		return startDependentServices;
	}

	public void setStartDependentServices(boolean startDependentServices) {
		this.startDependentServices = startDependentServices;
	}

	public boolean isDeployClientConfigs() {
		return deployClientConfigs;
	}

	public void setDeployClientConfigs(boolean deployClientConfigs) {
		this.deployClientConfigs = deployClientConfigs;
	}

	@Override
	public String toString() {
		return "HdfsDisableHaArguments [activeName=" + activeName
				+ ", secondaryName=" + secondaryName
				+ ", startDependentServices=" + startDependentServices
				+ ", deployClientConfigs=" + deployClientConfigs + "]";
	}
}
