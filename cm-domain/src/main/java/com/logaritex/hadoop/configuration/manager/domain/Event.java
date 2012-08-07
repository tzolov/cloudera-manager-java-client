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
 * Events model noteworthy incidents in Cloudera Manager or the managed Hadoop
 * cluster. An event carries its event category, severity, and a string content.
 * They also have generic attributes, which are free-form key value pairs.
 * Important events may be promoted into alerts.
 * 
 * http://cloudera.github.com/cm_api/apidocs/v1/ns0_apiEvent.html
 * 
 */
/**
 * @author chtz
 * 
 */
public class Event {

	public enum EventCategory {
		/**
		 * The category of this event is unknown
		 */
		UNKNOWN,
		/**
		 * This event was generate during a health check
		 */
		HEALTH_EVENT,
		/**
		 * This event was sent because a specific log message was detected
		 */
		LOG_EVENT,
		/**
		 * This event was caused by an audit event e.g. service restart, role reconfiguration, etc
		 */
		AUDIT_EVENT,
		/**
		 * This event was triggered by an activity event e.g. activity entered shuffle phase, stopped, preempted, etc
		 */
		ACTIVITY_EVENT;
	}

	public enum EventSeverity {
		/**
		 * The severity of this event is not known
		 */
		UNKNOWN,
		/**
		 * This event provides information about a state change.
		 */
		INFORMATIONAL,
		/**
		 * This event severity should be seen as concerning and may require attention.
		 */
		IMPORTANT,
		/**
		 * This is the most severe event and signifies an important error that requires immediate attention.
		 */
		CRITICAL;
	}

	public static class EventAttribute {

		private String name;

		private List<String> values;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<String> getValues() {
			return values;
		}

		public void setValues(List<String> values) {
			this.values = values;
		}

		@Override
		public String toString() {
			return "EventAttribute [name=" + name + ", values=" + values + "]";
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
			EventAttribute other = (EventAttribute) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	}

	/**
	 * A unique ID for this event.
	 */
	private String id;
	/**
	 * The content payload of this event.
	 */
	private String content;
	/**
	 * When the event was generated.
	 */
	private String timeOccurred;
	/**
	 * When the event was stored by Cloudera Manager. Events do not arrive in the order that they are generated. If you
	 * are writing an event poller, this is a useful field to query.
	 */
	private String timeReceived;
	/**
	 * The category of this event -- whether it is a health event, an audit event, an activity event, etc.
	 */
	private EventCategory category;
	/**
	 * The severity of the event.
	 */
	private EventSeverity severity;
	/**
	 * Whether the event is promoted to an alert according to configuration.
	 */
	private boolean alert;
	/**
	 * A list of key-value attribute pairs.
	 */
	private List<EventAttribute> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeOccurred() {
		return timeOccurred;
	}

	public void setTimeOccurred(String timeOccurred) {
		this.timeOccurred = timeOccurred;
	}

	public String getTimeReceived() {
		return timeReceived;
	}

	public void setTimeReceived(String timeReceived) {
		this.timeReceived = timeReceived;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}

	public EventSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(EventSeverity severity) {
		this.severity = severity;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	public List<EventAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<EventAttribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", content=" + content + ", timeOccurred=" + timeOccurred + ", timeReceived="
				+ timeReceived + ", category=" + category + ", severity=" + severity + ", alert=" + alert
				+ ", attributes=" + attributes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
