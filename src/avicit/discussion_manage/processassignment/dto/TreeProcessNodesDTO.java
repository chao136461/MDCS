package avicit.discussion_manage.processassignment.dto;

import java.util.List;

public class TreeProcessNodesDTO {

	private String id;

	private String pId;

	private String strId;

	private String _parentId;

	private String parentStrId;

	private String name;

	private String iconCls;

	private String open;

	private String checked;

	private String isParent;

	private String classCode;

	private String status;

	private String edition;
	
	private String  routeId;

	private String thermalUnit;

	private String manufacturingUnit;

	private String useUnit;
	

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getThermalUnit() {
		return thermalUnit;
	}

	public void setThermalUnit(String thermalUnit) {
		this.thermalUnit = thermalUnit;
	}

	public String getManufacturingUnit() {
		return manufacturingUnit;
	}

	public void setManufacturingUnit(String manufacturingUnit) {
		this.manufacturingUnit = manufacturingUnit;
	}

	public String getUseUnit() {
		return useUnit;
	}

	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getParentStrId() {
		return parentStrId;
	}

	public void setParentStrId(String parentStrId) {
		this.parentStrId = parentStrId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	private List<TreeProcessNodesDTO> children;

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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public List<TreeProcessNodesDTO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeProcessNodesDTO> children) {
		this.children = children;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

}
