package domain;

/**
 * 教师实体类
 *
 * @author daihuaiyu
 * @create: 2021-03-18 09:18
 **/
public class teacher {

    private int classNo;

    public teacher(int classNo) {
        this.classNo = classNo;
    }

    public teacher() {
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }
}

