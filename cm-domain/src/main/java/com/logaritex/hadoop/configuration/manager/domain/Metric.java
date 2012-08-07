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
 * A metric represents a specific metric monitored by the Cloudera Management Services, and a list of values matching a
 * user query.
 * 
 * <br/>
 * These fields are available only in the "full" view:
 * 
 * <ul>
 * <li>displayName</li>
 * <li>description</li>
 * </ul>
 * 
 */
public class Metric {

	/**
	 * Name of the metric.
	 */
	private String name;
	/**
	 * Context the metric is associated with.
	 */
	private String context;
	/**
	 * Unit of the metric values.
	 */
	private String unit;
	/**
	 * List of readings retrieved from the monitors.
	 */
	private List<MetricData> data;
	/**
	 * Requires "full" view. User-friendly display name for the metric.
	 */
	private String displayName;
	/**
	 * Requires "full" view. Description of the metric.
	 */
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<MetricData> getData() {
		return data;
	}

	public void setData(List<MetricData> data) {
		this.data = data;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Metric [name=" + name + ", context=" + context + ", unit=" + unit + ", data=" + data + ", displayName="
				+ displayName + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((context == null) ? 0 : context.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		Metric other = (Metric) obj;
		if (context == null) {
			if (other.context != null)
				return false;
		} else if (!context.equals(other.context))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

}
