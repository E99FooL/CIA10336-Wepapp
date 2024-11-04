package com.emp.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleJDBCDAO();
	}

	public ArticleVO addArticle(Integer memId, String acTitle, String acCon, 
			Timestamp postDate, Integer acStat) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setMemId(memId);
		articleVO.setAcTitle(acTitle);
		articleVO.setAcCon(acCon);
		articleVO.setPostDate(postDate);
		articleVO.setAcStat(acStat);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateArticle(Integer acId, Integer memId, String acTitle, 
			String acCon, Timestamp postDate, Integer acStat) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setAcId(acId);
		articleVO.setMemId(memId);
		articleVO.setAcTitle(acTitle);
		articleVO.setAcCon(acCon);
		articleVO.setPostDate(postDate);
		articleVO.setAcStat(acStat);
		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(Integer acId) {
		dao.delete(acId);
	}

	public ArticleVO getOneArticle(Integer acId) {
		return dao.findByPrimaryKey(acId);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
}
