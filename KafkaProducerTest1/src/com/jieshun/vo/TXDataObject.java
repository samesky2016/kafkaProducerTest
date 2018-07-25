package com.jieshun.vo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TXDataObject
{
  private String objectId;
  private List<TXDataObject> subItems = new ArrayList();
  private Map<String, Object> attributes = new HashMap();
  private DataOperationType operateType = DataOperationType.READ;
  
  public String getObjectId()
  {
    return this.objectId;
  }
  
  public void setObjectId(String objectId)
  {
    this.objectId = objectId;
  }
  
  public List<TXDataObject> getSubItems()
  {
    return this.subItems;
  }
  
  public void setSubItems(List<TXDataObject> subItems)
  {
    this.subItems = subItems;
  }
  
  public void addSubItem(TXDataObject item)
  {
    this.subItems.add(item);
  }
  
  public void addSubItems(List<TXDataObject> subItems)
  {
    if (subItems != null) {
      this.subItems.addAll(subItems);
    }
  }
  
  public void removeSubItem(TXDataObject item)
  {
    this.subItems.remove(item);
  }
  
  public Map<String, Object> getAttributes()
  {
    return this.attributes;
  }
  
  public void setAttributes(Map<String, Object> attributes)
  {
    if (attributes != null) {
      this.attributes.putAll(attributes);
    }
  }
  
  public DataOperationType getOperateType()
  {
    return this.operateType;
  }
  
  public void setOperateType(DataOperationType operateType)
  {
    this.operateType = operateType;
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
  
  public void removeAttribute(String name)
  {
    this.attributes.remove(name);
  }
  
  public void setAttribute(String name, Object value)
  {
    this.attributes.put(name, value);
  }
}
