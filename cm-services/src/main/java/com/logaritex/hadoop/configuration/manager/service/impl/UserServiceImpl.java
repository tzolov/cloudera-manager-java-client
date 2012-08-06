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
package com.logaritex.hadoop.configuration.manager.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.logaritex.hadoop.configuration.manager.domain.User;
import com.logaritex.hadoop.configuration.manager.domain.UserList;
import com.logaritex.hadoop.configuration.manager.service.HttpService;
import com.logaritex.hadoop.configuration.manager.service.UserService;

public class UserServiceImpl implements UserService {

	private final static String USERS_PATH = "/api/v1/users";

	private final static String USER_PATH = USERS_PATH + "/{userName}";

	private final HttpService http;

	public UserServiceImpl(HttpService http) {
		super();
		this.http = http;
	}


	@Override
	public List<User> getAllUsers() {

		UserList userList = http.get(USERS_PATH, UserList.class);

		if (userList == null || userList.getItems() == null) {
			return new ArrayList<User>();
		}

		return userList.getItems();
	}

	@Override
	public User get(String userName) {
		return http.get(USER_PATH, User.class, userName);
	}

	@Override
	public List<User> create(User... newUsers) {

		UserList userList = http.post(USERS_PATH,
				new UserList(Arrays.asList(newUsers)), UserList.class);

		if (userList == null || userList.getItems() == null) {
			return new ArrayList<User>();
		}

		return userList.getItems();
	}

	@Override
	public User delete(String userName) {
		return http.delete(USER_PATH, null, User.class, userName);
	}


	@Override
	public User update(User user) {
		return http.put(USER_PATH, user, User.class, user.getName());
	}
}
