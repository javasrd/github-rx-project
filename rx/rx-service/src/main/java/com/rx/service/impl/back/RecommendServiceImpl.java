package com.rx.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.DeletedType;
import com.rx.dao.RecommendMapper;
import com.rx.entity.Recommend;
import com.rx.service.back.IRecommendService;
import com.rx.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("recommendServiceBean")
public class RecommendServiceImpl extends AbstractBaseService<Recommend, Long> implements IRecommendService {

	private RecommendMapper recommendMapper;

	/**
	 * @param recommendMapper the recommendMapper to set
	 * set方式注入
	 */
	public void setRecommendMapper(RecommendMapper recommendMapper) {
		this.recommendMapper = recommendMapper;
		this.setMapper(recommendMapper);
	}

	/**
	 * @see com.rx.service.back.IRecommendService#getAll()
	 * 查询所有未删除的推荐
	 */
	@Override
	public List<Recommend> getAll() {
		Example example = new Example(Recommend.class);
		example.createCriteria().andEqualTo("deleted", DeletedType.NO);
		example.setOrderByClause("created desc");
		return recommendMapper.selectByExample(example);
	}

	/**
	 * @see com.rx.service.back.IRecommendService#deleteById(java.lang.Long)
	 * 删除推荐（逻辑删除）
	 */
	@Override
	public int deleteById(Long id) {
		Recommend recommend = new Recommend();
		recommend.setId(id);
		recommend.setDeleted(DeletedType.YES);
		return recommendMapper.updateByPrimaryKeySelective(recommend);
	}

	/**
	 * @see com.rx.service.back.IRecommendService#deleteByIds(java.util.List)
	 * 批量删除推荐（逻辑删除）
	 */
	@Override
	public int deleteByIds(List<Long> ids) {
		return recommendMapper.deleteByIds(ids);
	}

	/**
	 * Description:按showed状态读取推荐列表
	 * @see com.rx.service.back.IRecommendService#getAllByShowed(java.lang.Integer)
	 */
	@Override
	public List<Recommend> getAllByShowed(Integer showed) {
		Recommend record=new Recommend();
		record.setShowed(showed);
		return	recommendMapper.select(record);
		
	}

}
