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
 * Arguments used for the collectDiagnosticData command.
 * 
 */
public class CollectDiagnosticDataArguments {

	/**
	 * The start time (in ISO 8601 format) of the period to collection statistics for.
	 */
	public String startTime;

	/**
	 * The end time (in ISO 8601 format) of the period to collection statistics for.
	 */
	public String endTime;
	
	/**
	 * Whether to include INFO level logs. WARN, ERROR, and FATAL level logs are always included.	
	 */
	public boolean includeInfoLog;
	
	/**
	 * The support ticket number to attach to this data collection.
	 */
	public String ticketNumber;
	
	/**
	 * Comments to include with this data collection.
	 */
	public String comments;

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

	public boolean isIncludeInfoLog() {
		return includeInfoLog;
	}

	public void setIncludeInfoLog(boolean includeInfoLog) {
		this.includeInfoLog = includeInfoLog;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "CollectDiagnosticDataArguments [startTime=" + startTime
				+ ", endTime=" + endTime + ", includeInfoLog=" + includeInfoLog
				+ ", ticketNumber=" + ticketNumber + ", comments=" + comments
				+ "]";
	}
}
