package com.emp.model;

import java.util.List;

public interface ArticleDAO_interface {
    public void insert(ArticleVO articleVO);            // 新增文章
    public void update(ArticleVO articleVO);            // 更新文章
    public void delete(Integer acId);                   // 刪除文章
    public ArticleVO findByPrimaryKey(Integer acId);    // 以文章編號查詢單一文章
    public List<ArticleVO> getAll();                    // 取得所有文章
    // 萬用複合查詢(傳入參數型態Map)(回傳 List)
    // public List<ArticleVO> getAll(Map<String, String[]> map); 
}
