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
package com.logaritex.hadoop.configuration.manager;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.logaritex.hadoop.configuration.manager.domain.User;
import com.logaritex.hadoop.configuration.manager.service.UserService;
import com.logaritex.hadoop.configuration.manager.service.impl.UserServiceImpl;

public class UserServiceTest {

	private static final String PASSWORD = "t0mt0mpl@$3$";
	private static final String USERNAME = "admin";
	private static final String BASE_URL = "http://192.168.15.52:7180";

	private UserService userService;

	@Before
	public void setup() {

		SimpleHttpService httpService = new SimpleHttpService(BASE_URL, USERNAME, PASSWORD);

		userService = new UserServiceImpl(httpService);
	}

	@After
	public void after() {
		try {
			userService.delete("chtz");
		} catch (Exception e) {

		}
	}

	//@Test
	public void testGetAllUsers() {
		List<User> users = userService.getAllUsers();

		Assert.assertNotNull(users);

		Assert.assertEquals(1, users.size());
		Assert.assertEquals(USERNAME, users.get(0).getName());
		Assert.assertEquals(null, users.get(0).getPassword());
		Assert.assertEquals("ROLE_ADMIN", users.get(0).getRoles().get(0));
	}

	//@Test
	public void testCreateUsers() {

		Assert.assertEquals(1, userService.getAllUsers().size());

		List<User> newCreatedUsers = userService.create(new User("chtz", "pass", Arrays.asList("ROLE_ADMIN")));

		Assert.assertEquals(1, newCreatedUsers.size());

		Assert.assertEquals("chtz", newCreatedUsers.get(0).getName());
		Assert.assertEquals(null, newCreatedUsers.get(0).getPassword());
		Assert.assertEquals("ROLE_ADMIN", newCreatedUsers.get(0).getRoles().get(0));

		Assert.assertEquals(2, userService.getAllUsers().size());

	}

	//@Test
	public void testUpdateUser() {

		Assert.assertEquals(1, userService.getAllUsers().size());

		List<User> newCreatedUsers = userService.create(new User("chtz", "pass", Arrays.asList("ROLE_ADMIN")));

		Assert.assertEquals(1, newCreatedUsers.size());

		Assert.assertEquals("chtz", newCreatedUsers.get(0).getName());
		Assert.assertEquals(null, newCreatedUsers.get(0).getPassword());
		Assert.assertEquals("ROLE_ADMIN", newCreatedUsers.get(0).getRoles().get(0));

		Assert.assertEquals(2, userService.getAllUsers().size());

		User updatedUser = userService.update(new User("chtz", "pass2", Arrays.asList("ROLE_ADMIN")));

		Assert.assertEquals("chtz", updatedUser.getName());
		Assert.assertEquals(null, updatedUser.getPassword());
		Assert.assertEquals("ROLE_ADMIN", updatedUser.getRoles().get(0));

		Assert.assertEquals(2, userService.getAllUsers().size());
	}

}
