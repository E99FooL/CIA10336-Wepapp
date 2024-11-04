package com.emp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ArticleVO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer acId;         // 文章編號
    private Integer memId;        // 會員編號
    private String acTitle;       // 文章標題
    private String acCon;         // 文章內容
    private Timestamp postDate;        // 發佈日期
    private Integer acStat;          // 文章發布狀態 (1: 已發布, 0: 已下架)

    public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public String getAcTitle() {
        return acTitle;
    }

    public void setAcTitle(String acTitle) {
        this.acTitle = acTitle;
    }

    public String getAcCon() {
        return acCon;
    }

    public void setAcCon(String acCon) {
        this.acCon = acCon;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate2) {
        this.postDate = postDate2;
    }

    public Integer getAcStat() {
        return acStat;
    }

    public void setAcStat(Integer acStat) {
        this.acStat = acStat;
    }
}
