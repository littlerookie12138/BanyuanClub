public class User {
    private int id;
    private String userName;
    private String pwd;
    private String pwdConfirm;
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
        if (userType.equals("0")) {
            userTypeStr = "普通员工";
        } else {
            userTypeStr = "经理";
        }
    }

    private String userTypeStr;

    public String getUserTypeStr() {
        return userTypeStr;
    }

    public void setUserTypeStr(String userTypeStr) {
        this.userTypeStr = userTypeStr;
    }

    public User(String userName, String pwd, String pwdConfirm, String userType, int id) {
        this.userName = userName;
        this.pwd = pwd;
        this.pwdConfirm = pwdConfirm;
        this.id = id;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwdConfirm() {
        return pwdConfirm;
    }

    public void setPwdConfirm(String pwdConfirm) {
        this.pwdConfirm = pwdConfirm;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwdConfirm='" + pwdConfirm + '\'' +
                ", userTypeStr='" + userTypeStr + '\'' +
                '}';
    }
}
