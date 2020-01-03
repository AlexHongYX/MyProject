/**
 * Created by xiaoaxiao on 2020/1/3
 * Description: 记录楼房信息
 */
public class BuildInfo {
    private String bno;
    private String bname;
    private String sex;
    private int age;
    private String bdept;
    public String getBno() {return bno;}
    public void setBno(String bno) {this.bno = bno;}
    public void setBname(String bname) {
        this.bname = bname;
    }
    public String getBname() {
        return bname;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setBdept(String bdept) {
        this.bdept = bdept;
    }
    public String getBdept() {
        return bdept;
    }

    public BuildInfo(String sno,String sname,String sex,int age,String sdept)
    {
        this.bno =sno;
        this.bname =sname;
        this.sex=sex;
        this.age=age;
        this.bdept =sdept;
    }
}
