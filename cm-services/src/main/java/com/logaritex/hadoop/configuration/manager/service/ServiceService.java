package com.logaritex.hadoop.configuration.manager.service;

import java.util.List;

import com.logaritex.hadoop.configuration.manager.domain.BulkCommandList;
import com.logaritex.hadoop.configuration.manager.domain.Command;
import com.logaritex.hadoop.configuration.manager.domain.Role;
import com.logaritex.hadoop.configuration.manager.domain.Service;
import com.logaritex.hadoop.configuration.manager.domain.ServiceConfig;

public interface ServiceService {

	public enum View {
		summary, full
	}

	public enum ServiceCommand {
		start, stop, restart,
	
		decommission, deployClientConfig, hbaseCreateRoot,
	
		hdfsDisableAutoFailover, hdfsDisableHa, hdfsEnableAutoFailover, hdfsEnableHa, hdfsFailover,
	
		hueCreateHiveWarehouse,
	
		zooKeeperCleanup, zooKeeperInit;
	}

	public enum RoleCommand {
		start, stop, restart, refresh;
	}

	/**
	 * Lists all services registered in the cluster.
	 * 
	 * @param clusterName
	 *            The name of the cluster
	 * @return List of services
	 */
	List<Service> getAllServices(String clusterName);

	/**
	 * Creates a list of services.
	 * 
	 * There are typically two service creation strategies:
	 * 
	 * <br/>
	 * 1. The caller may choose to set up a new service piecemeal, by first creating the service itself (without any
	 * roles or configuration), and then create the roles, and then specify configuration.
	 * 
	 * <br/>
	 * 2. Alternatively, the caller can pack all the information in one call, by fully specifying the fields in the
	 * ApiServiceSetupInfo object, with
	 * <ul>
	 * <li>role to host assignment.</li>
	 * <li>service config and role type config, and</li>
	 * </ul>
	 * 
	 * @param clusterName
	 *            The name of the cluster.
	 * @param services
	 *            Details of the services to create.
	 * @return List of created services.
	 */
	List<Service> createServices(String clusterName, Service... services);

	/**
	 * Deletes a service from the system.
	 * 
	 * @param clusterName
	 *            The name of the cluster
	 * @param serviceName
	 *            The name of the service to delete
	 * @return The details of the deleted services
	 */
	Service deleteService(String clusterName, String serviceName);

	/**
	 * Retrieves details information about a service.
	 * 
	 * @param clusterName
	 *            The name of the cluster
	 * 
	 * @param serviceName
	 *            The name of the service
	 * @return The details of the service.
	 */
	Service getService(String clusterName, String serviceName);

	/**
	 * Retrieves the configuration of a specific service.
	 * 
	 * <br/>
	 * The "summary" view contains only the configured parameters, and configuration for role types that contain
	 * configured parameters.
	 * 
	 * <br/>
	 * The "full" view contains all available configuration parameters for the service and its role types. This mode
	 * performs validation on the configuration, which could take a few seconds on a large cluster (around 500 nodes or
	 * more).
	 * 
	 * @param service
	 *            The service to query.
	 * @param view
	 *            The view of the data to materialize, either "summary" or "full".
	 * @return List of service and role types configuration parameters.
	 */
	ServiceConfig getServiceConfiguration(Service service, View view);

	/**
	 * Updates the service configuration with the given values.
	 * 
	 * <br/>
	 * If a value is set in the given configuration, it will be added to the service's configuration, replacing any
	 * existing entries. If a value is unset (its value is null), the existing configuration for the attribute will be
	 * erased, if any.
	 * 
	 * <br/>
	 * Attributes that are not listed in the input will maintain their current values in the configuration.
	 * 
	 * @param service
	 *            The service to modify
	 * @param config
	 *            Configuration changes
	 * @return The new service configuration
	 */
	ServiceConfig updateServiceConfiguration(Service service, ServiceConfig config);

	/**
	 * List active service commands.
	 * 
	 * @param clusterName
	 *            The name of the cluster.
	 * @param serviceName
	 *            The name of the service.
	 * @return A list of active service commands.
	 */
	List<Command> getActiveServiceCommands(String clusterName, String serviceName);

	/**
	 * Start the service
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field of the service must be provided as well
	 * @return Information about the submitted command
	 */
	Command startService(Service service);

	/**
	 * Stop the service
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field of the service must be provided as well
	 * @return Information about the submitted command
	 */
	Command stopService(Service service);

	/**
	 * Restart the service
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field of the service must be provided as well
	 * @return Information about the submitted command
	 */
	Command restartService(Service service);

	List<String> getRoleTypes(Service service);

	List<Role> getRoles(Service service);

	BulkCommandList startRoles(List<Role> roles);

	BulkCommandList stopRoles(List<Role> roles);

	BulkCommandList restartRoles(List<Role> roles);

	BulkCommandList refreshRoles(List<Role> roles);

	/**
	 * Clean up all running server instances of a ZooKeeper service. <br/>
	 * 
	 * This command removes snapshots and transaction log files kept by ZooKeeper for backup purposes. Refer to the
	 * ZooKeeper documentation for more details
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field of the service must be provided as well
	 * @return Information about the submitted command
	 */
	Command zooKeeperInit(Service service);

	/**
	 * Initializes all the server instances of a ZooKeeper service.
	 * 
	 * <br/>
	 * This command applies to ZooKeeper services from CDH4. ZooKeeper server roles need to be initialized before they
	 * can be used.
	 * 
	 * @param service
	 *            Name of the service to start. Note that the ClusterRef field of the service must be provided as well
	 * @return Information about the submitted command
	 */
	Command zooKeeperCleanup(Service service);
}