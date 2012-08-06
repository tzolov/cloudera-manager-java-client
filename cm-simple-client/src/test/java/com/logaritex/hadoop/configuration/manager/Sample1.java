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

import java.util.List;

import com.logaritex.hadoop.configuration.manager.domain.Cluster;
import com.logaritex.hadoop.configuration.manager.domain.Host;
import com.logaritex.hadoop.configuration.manager.domain.Role;
import com.logaritex.hadoop.configuration.manager.domain.Service;
import com.logaritex.hadoop.configuration.manager.domain.User;
import com.logaritex.hadoop.configuration.manager.service.ConfigurationManager;
import com.logaritex.hadoop.configuration.manager.service.ServiceService;
import com.logaritex.hadoop.configuration.manager.service.impl.ServiceServiceImpl.View;
import com.logaritex.hadoop.configuration.manager.service.UserService;

public class Sample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ConfigurationManager cm = ConfigurationManagerFactory.createConfigurationManager("http://192.168.15.52:7180",
				"admin", "t0mt0mpl@$3$");

		List<Cluster> clusters = cm.getClusterService().getAllClusters();

		String clusterName = clusters.get(0).getName();

		System.out.println(clusterName);

		ServiceService serviceService = cm.getServiceService();
		List<Service> services = serviceService.getAllServices(clusterName);

		for (Service s : services) {
			System.out.println(s);
			System.out.println(serviceService.getServiceConfiguration(s, View.full));
			for (Role role : serviceService.getRoles(s)) {
				System.out.println(" >>> " + role);
			}
		}

		// System.out.println(services.get(2).getType());
		// Command command = serviceService.restart(services.get(2));
		// System.out.println(command);

		// System.out.println(serviceService.startRoles(serviceService.getRoles(services.get(2))));

		UserService userService = cm.getUserService();

		for (User user : userService.getAllUsers()) {
			System.out.println(user);
		}

		// List<User> users = userService.create(Arrays.asList(new User("chtz",
		// "g0nd0l@2012", Arrays.asList("ROLE_ADMIN"))));
		//
		// for (User user : users) {
		// System.out.println(user);
		// }

		for (Host h : cm.getHostService().getAllHosts()) {
			System.out.println(h);
		}

	}

}
