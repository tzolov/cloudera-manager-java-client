package com.logaritex.hadoop.configuration.manager.service;

import static java.util.Arrays.*;
import java.util.List;

import static junit.framework.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import com.logaritex.hadoop.configuration.manager.domain.Cluster;
import com.logaritex.hadoop.configuration.manager.domain.Cluster.ClusterVersion;
import com.logaritex.hadoop.configuration.manager.domain.ClusterList;
import com.logaritex.hadoop.configuration.manager.service.impl.ClusterServiceImpl;

public class ClusterServiceTest {

	HttpService httpService = mock(HttpService.class);


	@Test
	public void testCreateCluster() throws Exception {
 
		ClusterServiceImpl clusterService = new ClusterServiceImpl(httpService);

		Cluster cluster = new Cluster();
		cluster.setName("default");
		cluster.setVersion(ClusterVersion.CDH3);
		Cluster[] clusters = new Cluster[]{cluster};

		List<Cluster> clusterList = asList(cluster);
		when(httpService.post(eq("/api/v1/clusters"), anyListOf(Cluster.class), eq(ClusterList.class))).thenReturn(
				new ClusterList(clusterList));

		List<Cluster> createdClusters = clusterService.createClusters(clusters);  

		assertEquals(clusters.length, createdClusters.size());
	}

}
