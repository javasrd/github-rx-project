package com.rx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rx.bean.FavouriteBean;
import com.rx.bean.FavouriteStatisticBean;
import com.rx.entity.UserFavorite;

import tk.mybatis.mapper.common.Mapper;

public interface UserFavoriteMapper extends Mapper<UserFavorite> {

	public List<FavouriteBean> getFavouritesByUserId(@Param("userId") long userId);

	public List<FavouriteStatisticBean> getFavouriteStatistic(@Param("userId") long userId);

}