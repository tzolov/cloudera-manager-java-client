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
 * This is the model for user information in the API.
 * 
 * <br/>
 * Note that any method that returns user information will not contain any password information. The password property
 * is only used when creating or updating users.
 * 
 */
public class User {

	/**
	 * The username, which is unique within a Cloudera Manager installation.
	 */
	private String name;

	/**
	 * Returns the user password. Passwords are not returned when querying user information, so this property will
	 * always be empty when reading information from a server.
	 */
	private String password;

	/**
	 * A list of roles this user belongs to. Current possible values are: "ROLE_ADMIN" or empty.
	 */
	private List<String> roles;

	public User() {

	}

	public User(String name, String password, List<String> roles) {
		super();
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", roles=" + roles + "]";
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
