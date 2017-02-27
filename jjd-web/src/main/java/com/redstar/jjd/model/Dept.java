package com.redstar.jjd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_DEPT")
public class Dept {

    @Id
    @GeneratedValue(generator = "deptSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "deptSequence", sequenceName = "SEQ_DEPT")
    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "DEPT_NAME", length = 64)
    private String deptName;
    /**
     * 部门描述
     */
    @Column(name = "DEPT_DESC", length = 128)
    private String deptDesc;

    /**
     * @return the deptId
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the deptName
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     *            the deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

}
