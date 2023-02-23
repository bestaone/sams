package cn.webestar.sams.apisvc.oms.api.dto;

public class SmsLoginDTO {

    private String phoneNum;
    private Integer smsCode;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(Integer smsCode) {
        this.smsCode = smsCode;
    }

}
