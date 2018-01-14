package com.rx.service.impl.front;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.bean.FavouriteBean;
import com.rx.bean.FavouriteStatisticBean;
import com.rx.dao.UserFavoriteMapper;
import com.rx.entity.UserFavorite;
import com.rx.service.front.IFavouriteService;
import com.rx.service.impl.AbstractBaseService;

@Service
public class FavouriteServiceImpl extends AbstractBaseService<UserFavorite, Long> implements IFavouriteService {
	
	UserFavoriteMapper userFavoriteMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setUserFavoriteMapper(UserFavoriteMapper userFavoriteMapper) { 
		this.userFavoriteMapper=userFavoriteMapper;
		this.setMapper(userFavoriteMapper);
	}	

	@Override
	public int addToFavourite(long favouriteId,  long userId) {
		int row=0;
		//favouriteMapper.
		/**
		 (1)自我的收藏查询，如不存在，则直接插入
		 (2)如果已经存在，DO NOTHING
		 */
		UserFavorite record=new UserFavorite();
		record.setFavoriteId(favouriteId);
		
		record.setIdType("1");  //类型为商品 
		record.setStatus((byte)1);  //状态为可用
		record.setUserId(userId);
				
		
		UserFavorite favourite=userFavoriteMapper.selectOne(record);
		if(favourite==null){  //如果此关注不存在，则加入
			record.setAddTime(new Date());	
			row=userFavoriteMapper.insertSelective(record);			
		}
		return row;
	}

	@Override
	public List<FavouriteBean> getFavouritesByUserId(long userId) {
		return userFavoriteMapper.getFavouritesByUserId(userId);		
	}

	@Override
	public List<FavouriteStatisticBean> getFavouriteStatistic(long userId) {
		return userFavoriteMapper.getFavouriteStatistic(userId);		
	}

	@Override
	public boolean isUserFavourite(long favouriteId,  long userId) {
		UserFavorite record=new UserFavorite();
		
		
		record.setIdType("1");
		record.setStatus((byte)1);
		record.setUserId(userId);
		record.setFavoriteId(favouriteId);		
		
		UserFavorite favourite=userFavoriteMapper.selectOne(record);
		if(favourite==null){  //如果此关注不存在
			return false;
		}
		else{
			return true;
		}
		
	}		

}
