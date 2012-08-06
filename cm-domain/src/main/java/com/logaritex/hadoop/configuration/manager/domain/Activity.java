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
 * Represents a user activity, such as a MapReduce job, a Hive query, an Oozie
 * workflow, etc.
 * 
 * http://cloudera.github.com/cm_api/apidocs/v1/ns0_apiActivity.html
 * 
 */
public class Activity {

	public enum ActivityType {
		UNKNOWN, OOZIE, PIG, HIVE, MR, STREAMING
	};

	public enum ActivityStatus {
		UNKNOWN, SUBMITTED, STARTED, SUSPENDED, FAILED, KILLED, SUCCEEDED, ASSUMED_SUCCEEDED;
	}

	/**
	 * Activity name.
	 */
	public String name;
	/**
	 * Activity type. Whether it's an MR job, a Pig job, a Hive query, etc.
	 */
	public ActivityType type;
	/**
	 * The name of the parent activity.
	 */
	public String parent;
	/**
	 * The start time of this activity.
	 */
	public String startTime;
	/**
	 * The finish time of this activity.
	 */
	public String finishTime;
	/**
	 * Activity id, which is unique within a MapReduce service.
	 */
	public String id;
	/**
	 * Activity status
	 */
	public ActivityStatus status;
	/**
	 * The user who submitted this activity.
	 */
	public String user;
	/**
	 * The user-group of this activity.
	 */
	public String group;
	/**
	 * The input data directory of the activity. An HDFS url.
	 */
	public String inputDir;
	/**
	 * The output result directory of the activity. An HDFS url.
	 */
	public String outputDir;
	/**
	 * The mapper class.
	 */
	public String mapper;
	/**
	 * The combiner class.
	 */
	public String combiner;
	/**
	 * The reducer class.
	 */
	public String reducer;
	/**
	 * The scheduler queue this activity is in.
	 */
	public String queueName;
	/**
	 * The scheduler priority of this activity.
	 */
	public String schedulerPriority;

	/**
	 * @return Returns the Activity id, which is unique within a MapReduce service.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name an Activity id, which is unique within a MapReduce service.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public ActivityType getType() {
		return type;
	}

	public void setType(ActivityType type) {
		this.type = type;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ActivityStatus getStatus() {
		return status;
	}

	public void setStatus(ActivityStatus status) {
		this.status = status;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getInputDir() {
		return inputDir;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}

	public String getMapper() {
		return mapper;
	}

	public void setMapper(String mapper) {
		this.mapper = mapper;
	}

	public String getCombiner() {
		return combiner;
	}

	public void setCombiner(String combiner) {
		this.combiner = combiner;
	}

	public String getReducer() {
		return reducer;
	}

	public void setReducer(String reducer) {
		this.reducer = reducer;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getSchedulerPriority() {
		return schedulerPriority;
	}

	public void setSchedulerPriority(String schedulerPriority) {
		this.schedulerPriority = schedulerPriority;
	}

	@Override
	public String toString() {
		return "Activity [name=" + name + ", type=" + type + ", parent="
				+ parent + ", startTime=" + startTime + ", finishTime="
				+ finishTime + ", id=" + id + ", status=" + status + ", user="
				+ user + ", group=" + group + ", inputDir=" + inputDir
				+ ", outputDir=" + outputDir + ", mapper=" + mapper
				+ ", combiner=" + combiner + ", reducer=" + reducer
				+ ", queueName=" + queueName + ", schedulerPriority="
				+ schedulerPriority + "]";
	}

}
