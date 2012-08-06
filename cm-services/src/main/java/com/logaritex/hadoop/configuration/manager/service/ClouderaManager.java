package com.logaritex.hadoop.configuration.manager.service;

public interface ClouderaManager {

	public abstract ClusterService getClusterService();

	public abstract HostService getHostService();

	public abstract UserService getUserService();

	public abstract ServiceService getServiceService();

}