package Parse;


import java.io.Serializable;
import java.sql.Timestamp;

public class BOCsfSrvServiceParamBean implements Serializable {

	private static final long serialVersionUID = -5809782578272943999L;

	public static final String NAME_SPACE="com.ai.aif.csf.tool.bean";
	public static final String SELECT_PARAM="com.ai.aif.csf.tool.bean.getServiceParam";
	public static final String INSERT_PARAM="com.ai.aif.csf.tool.bean.insertServiceParam";
	public static final String INSERT_PARAM_MY="com.ai.aif.csf.tool.bean.insertServiceParamMy";
	public static final String UPDATE_PARAM="com.ai.aif.csf.tool.bean.updateServiceParam";
	public static final String DELETE_PARAM="com.ai.aif.csf.tool.bean.deleteServiceParam";

	private String defalutVal;
	private String isnull;
	private String paramInout;
	private String paramKey;
	private String paramName;
	private String paramType;
	private String parentParamKey;
	private String remarks;
	private String serviceCode;
	private String status;
	private Timestamp expireDate;
	private Timestamp validDate;
	private long paramId;
	private long paramIndex;
	private String paramMeta;
	
	public String getParamMeta() {
		return paramMeta;
	}
	public void setParamMeta(String paramMeta) {
		this.paramMeta = paramMeta;
	}
	public String getDefalutVal() {
		return defalutVal;
	}
	public void setDefalutVal(String defalutVal) {
		this.defalutVal = defalutVal;
	}
	public String getIsnull() {
		return isnull;
	}
	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}
	public String getParamInout() {
		return paramInout;
	}
	public void setParamInout(String paramInout) {
		this.paramInout = paramInout;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParentParamKey() {
		return parentParamKey;
	}
	public void setParentParamKey(String parentParamKey) {
		this.parentParamKey = parentParamKey;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}
	public Timestamp getValidDate() {
		return validDate;
	}
	public void setValidDate(Timestamp validDate) {
		this.validDate = validDate;
	}
	public long getParamId() {
		return paramId;
	}
	public void setParamId(long paramId) {
		this.paramId = paramId;
	}
	public long getParamIndex() {
		return paramIndex;
	}
	public void setParamIndex(long paramIndex) {
		this.paramIndex = paramIndex;
	}


}

