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
package com.logaritex.hadoop.configuration.manager.service;

import java.util.List;

import com.logaritex.hadoop.configuration.manager.domain.User;

public interface UserService {

	/**
	 * Returns a list of the user names configured in the system.
	 * 
	 * @return A list of users.
	 */
	List<User> getAllUsers();

	/**
	 * Returns detailed information about a user.
	 * 
	 * @param userName
	 *            The user to read.
	 * @return The user's information.
	 */
	User get(String userName);

	/**
	 * Creates a list of users.
	 * 
	 * <br/>
	 * When creating new users, the password property of each user should be
	 * their plain text password. The returned user information will not contain
	 * any password information.
	 * 
	 * @param newUsers
	 *            Information about the users to create.
	 * @return Information about created users.
	 */
	List<User> create(User... newUsers);

	/**
	 * Deletes a user from the system.
	 * 
	 * @param userName
	 *            The name of the user to delete.
	 * @return The details of the deleted user.
	 */
	User delete(String userName);

	/**
	 * Updates the given user's information. Note that the user's name cannot be
	 * changed.
	 * 
	 * @param user
	 *            The user information.
	 * @return Returns updated user.
	 */
	User update(User user);
}