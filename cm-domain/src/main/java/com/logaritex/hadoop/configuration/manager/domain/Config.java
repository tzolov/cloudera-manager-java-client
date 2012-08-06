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

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Model for a configuration parameter. When an entry's value property is not
 * available, it means the entry is not configured. This means that the default
 * value for the entry, if any, will be used. Setting a value to null also can
 * be used to unset any previously set value for the parameter, reverting to the
 * default value (if any).
 * 
 * http://cloudera.github.com/cm_api/apidocs/v1/ns0_apiConfig.html
 * 
 */
public class Config {

	public enum ValidationState {
		OK, WARNING, ERROR;
	}

	/**
	 * Readonly. The canonical name that identifies this configuration
	 * parameter.
	 */
	private String name;
	/**
	 * The user-defined value. When absent, the default value (if any) will be
	 * used.
	 */
	private String value;
	/**
	 * Readonly. Requires "full" view. Whether this configuration is required
	 * for the object. If any required configuration is not set, operations on
	 * the object may not work.
	 */
	private boolean required;
	/**
	 * Readonly. Requires "full" view. The default value.
	 */
	@JsonProperty("default")
	private String defaultValue;
	/**
	 * Readonly. Requires "full" view. A user-friendly name of the parameters,
	 * as would have been shown in the web UI.
	 */
	private String displayName;
	/**
	 * Readonly. Requires "full" view. A textual description of the parameter.
	 */
	private String description;
	/**
	 * Readonly. Requires "full" view. If applicable, contains the related
	 * configuration variable used by the source project.
	 */
	private String relatedName;
	/**
	 * Readonly. Requires "full" view. State of the configuration parameter
	 * after validation.
	 */
	private ValidationState validationState;
	/**
	 * Readonly. Requires "full" view. A message explaining the parameter's
	 * validation state.
	 */
	private  String validationMessage;

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String default1) {
		this.defaultValue = default1;
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

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public ValidationState getValidationState() {
		return validationState;
	}

	public void setValidationState(ValidationState validationState) {
		this.validationState = validationState;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Config [name=" + name + ", value=" + value + ", required="
				+ required + ", default1=" + defaultValue + ", displayName="
				+ displayName + ", description=" + description
				+ ", relatedName=" + relatedName + ", validationState="
				+ validationState + ", validationMessage=" + validationMessage
				+ "]";
	}

}