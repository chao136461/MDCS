package avicit.discussion_manage.structuralrelationship.dto;

import java.util.List;

public class ZtreeNodesDTO {
	
	  private String tabId;
	  
	  private String id;
	  
	  private String pId;
	  
	  private String name;
	  
	  private String icon;
	  
	  private String open;
	  
	  private String checked;
	  
	  private Object attributes;
	  
	  private String isParent;
	  
	  private String font;
	  
	  private List<ZtreeNodesDTO> children;
	  private String instancenumber;
	  
	

	public String getInstancenumber() {
		return instancenumber;
	}

	public void setInstancenumber(String instancenumber) {
		this.instancenumber = instancenumber;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public List<ZtreeNodesDTO> getChildren() {
		return children;
	}

	public void setChildren(List<ZtreeNodesDTO> children) {
		this.children = children;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}
	
}
