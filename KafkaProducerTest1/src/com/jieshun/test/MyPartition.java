package com.jieshun.test;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class MyPartition
  implements Partitioner
{
  @Override
public void configure(Map<String, ?> configs) {}
  
  @Override
public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster)
  {
    List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
    int numPartitions = partitions.size();
    int partitionNum = 0;
    if (key == null)
    {
      Random random = new Random();
      int n = random.nextInt(numPartitions);
      System.out.println("++++++++++++++++the message sendTo topic:" + topic + " and the partitionNum:" + partitionNum + " numPartitions:" + numPartitions + " n:" + n);
      return n;
    }
    try
    {
      partitionNum = Integer.parseInt((String)key);
    }
    catch (Exception e)
    {
      partitionNum = key.hashCode();
    }
    int n = Math.abs(partitionNum % numPartitions);
    System.out.println("++++++++++++++++the message sendTo topic:" + topic + " and the partitionNum:" + partitionNum + " numPartitions:" + numPartitions + " n:" + n);
    return n;
  }
  
  public static void main(String[] args)
  {
    for (int i = 0; i < 100; i++)
    {
      Random random = new Random();
      System.out.println(random.nextInt(3));
    }
  }
  
  @Override
public void close() {}
}
