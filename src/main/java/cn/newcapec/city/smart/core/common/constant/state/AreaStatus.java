package cn.newcapec.city.smart.core.common.constant.state;

/**
 * 地域的状态
 *
 * @author
 * @Date 2017年1月10日 下午9:54:13
 */
public enum AreaStatus {

    OK("0", "正常"), DELETED("1", "删除"), FREEZED("2", "停用");

	String code;
    String message;

    AreaStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOfs(String value) {
        if (value == null) {
            return "";
        } else {
            for (AreaStatus ms : AreaStatus.values()) {
                if (ms.getCode().equals(value)) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
