package com.jieshun.vo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jieshun.util.JsonUtil;
import com.jieshun.util.StringUtil;


public class RequestParam
{
  private String systemCode;
  private String systemId;
  private String systemType;
  private String serviceId;
  private String seqId;
  private ServiceType requestType;
  private List<TXDataObject> dataItems = new ArrayList();
  private Map<String, Object> attributes = new HashMap();
  
  public String getSeqId()
  {
    return this.seqId;
  }
  
  public void setSeqId(String seqId)
  {
    this.seqId = seqId;
  }
  
  public <T> T getAttribute(String name)
  {
    return (T) this.attributes.get(name);
  }
  
  public <T> T getAttribute(String name, T defaultValue)
  {
    T temp = (T) this.attributes.get(name);
    if (temp == null) {
      temp = defaultValue;
    }
    return temp;
  }
  
  public Map<String, Object> getAttributes()
  {
    return this.attributes;
  }
  
  public void removeAttribute(String name)
  {
    this.attributes.remove(name);
  }
  
  public void setAttribute(String name, Object value)
  {
    this.attributes.put(name, value);
  }
  
  public void setAttributes(Map<String, Object> attributes)
  {
    this.attributes.putAll(attributes);
  }
  
  public ServiceType getRequestType()
  {
    return this.requestType;
  }
  
  public void setRequestType(ServiceType requestType)
  {
    this.requestType = requestType;
  }
  
  public RequestParam() {}
  
  public RequestParam(String serviceId)
  {
    this.serviceId = serviceId;
  }
  
  public RequestParam(String serviceId, String systemId, String systemCode, String systemType, List<TXDataObject> dataItems)
  {
    this.systemCode = systemCode;
    this.systemId = systemId;
    this.systemType = systemType;
    this.serviceId = serviceId;
    this.dataItems = dataItems;
  }
  
  public boolean validate()
  {
    if ((StringUtil.isEmpty(this.systemId)) || (StringUtil.isEmpty(this.systemType)) || (StringUtil.isEmpty(this.systemCode)) || (StringUtil.isEmpty(this.serviceId))) {
      return false;
    }
    return true;
  }
  
  public String getServiceId()
  {
    return this.serviceId;
  }
  
  public void setServiceId(String serviceId)
  {
    this.serviceId = serviceId;
  }
  
  public List<TXDataObject> getDataItems()
  {
    return this.dataItems;
  }
  
  public void setDataItems(List<TXDataObject> dataItems)
  {
    this.dataItems = dataItems;
  }
  
  public void addDataItem(TXDataObject item)
  {
    this.dataItems.add(item);
  }
  
  public void addDataItems(TXDataObject item)
  {
    this.dataItems.add(item);
  }
  
  public void addDataList(List<TXDataObject> items)
  {
    if (items != null) {
      this.dataItems.addAll(items);
    }
  }
  
  public void removeDataItem(TXDataObject item)
  {
    this.dataItems.remove(item);
  }
  
  public String getSystemCode()
  {
    return this.systemCode;
  }
  
  public void setSystemCode(String systemCode)
  {
    this.systemCode = systemCode;
  }
  
  public String getSystemId()
  {
    return this.systemId;
  }
  
  public void setSystemId(String systemId)
  {
    this.systemId = systemId;
  }
  
  public String getSystemType()
  {
    return this.systemType;
  }
  
  public void setSystemType(String systemType)
  {
    this.systemType = systemType;
  }
  
  @Override
public String toString()
  {
    return "RequestParam [serviceId=" + this.serviceId + "]";
  }
  
  public static void main(String[] args)
  {
    RequestParam pa = new RequestParam();
    pa.setSystemCode("dd");
    System.out.println(JsonUtil.toJson(pa));
    
    String json = "{\"systemCode\":\"dd\",\"dffff\":\"3dd\"}";
    RequestParam te = (RequestParam)JsonUtil.toObject(json, RequestParam.class);
    System.out.println(te.getSystemCode());
  }
}
