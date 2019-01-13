package cn.littlemotor.web.model.pojo.user;

public enum SexEnum {

    MANLE("男"), FEMALE("女");

    private String sex;

    SexEnum(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public static String getSex(String sex){
        for(SexEnum value : SexEnum.values()){
            if(value.getSex() == sex) return sex;
        }
        return "男";
    }
}
