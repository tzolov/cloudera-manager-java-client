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
 * Version information of Cloudera Manager itself.
 * 
 * http://cloudera.github.com/cm_api/apidocs/v1/ns0_apiVersionInfo.html
 * 
 */
public class VersionInfo {

	/**
	 * Version.
	 */
	private String version;
	/**
	 * Whether this build is a development snapshot.
	 */
	private boolean snapshot;
	/**
	 * The user performing the build.
	 */
	private String buildUser;
	/**
	 * Build timestamp.
	 */
	private String buildTimestamp;
	/**
	 * Source control management hash.
	 */
	private String gitHash;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(boolean snapshot) {
		this.snapshot = snapshot;
	}

	public String getBuildUser() {
		return buildUser;
	}

	public void setBuildUser(String buildUser) {
		this.buildUser = buildUser;
	}

	public String getBuildTimestamp() {
		return buildTimestamp;
	}

	public void setBuildTimestamp(String buildTimestamp) {
		this.buildTimestamp = buildTimestamp;
	}

	public String getGitHash() {
		return gitHash;
	}

	public void setGitHash(String gitHash) {
		this.gitHash = gitHash;
	}

	@Override
	public String toString() {
		return "VersionInfo [version=" + version + ", snapshot=" + snapshot
				+ ", buildUser=" + buildUser + ", buildTimestamp="
				+ buildTimestamp + ", gitHash=" + gitHash + "]";
	}
}
