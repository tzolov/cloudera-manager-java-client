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
 * Arguments used when enabling HDFS automatic failover.
 * 
 */
public class HdfsFailoverArguments {

	/**
	 * Nameservice for which to enable automatic failover.
	 */
	private String nameservice;
	/**
	 * The ZooKeeper service to use.
	 */
	private ServiceRef zooKeeperService;
	/**
	 * Name of the failover controller to create for the active NameNode.
	 */
	private String activeFCName;
	/**
	 * Name of the failover controller to create for the stand-by NameNode.
	 */
	private String standByFCName;

	public String getNameservice() {
		return nameservice;
	}

	public void setNameservice(String nameservice) {
		this.nameservice = nameservice;
	}

	public ServiceRef getZooKeeperService() {
		return zooKeeperService;
	}

	public void setZooKeeperService(ServiceRef zooKeeperService) {
		this.zooKeeperService = zooKeeperService;
	}

	public String getActiveFCName() {
		return activeFCName;
	}

	public void setActiveFCName(String activeFCName) {
		this.activeFCName = activeFCName;
	}

	public String getStandByFCName() {
		return standByFCName;
	}

	public void setStandByFCName(String standByFCName) {
		this.standByFCName = standByFCName;
	}

	@Override
	public String toString() {
		return "HdfsFailoverArguments [nameservice=" + nameservice
				+ ", zooKeeperService=" + zooKeeperService + ", activeFCName="
				+ activeFCName + ", standByFCName=" + standByFCName + "]";
	}
}
