package com.cloud.frame.core.base.service;

import java.util.List;
import java.util.Map;


/**
 * base service的封装
 * @author HeJian
 *
 */
public interface IBaseService<T>  {
	
	/**
	 * 保存,返回插入的条数
	 * @param entity
	 * @return
	 */
	public int insert(T entity);

	/**
	 * 通过主键删除,包括联合主键,返回所删除的条数
	 * @param key
	 * @return
	 */
	public int deleteByPk(Object key);
	
	/**
	 * 通过主键批量删除,返回所删除的条数
	 * 注意:只适合实体中只有一个id主键注解标识
	 * @param key
	 * @return
	 */
	public int deleteByIds(List<String> ids);

	/**
	 * 通过主键更新全部,为null的数据也会更新
	 * @param entity
	 * @return
	 */
	public int updateAllByPk(T entity);

	/**
	 * 通过主键更新全部,只更新不为null的数据
	 * @param entity
	 * @return
	 */
	public int updateNotNullPk(T entity);
	
	/**
	 * 通过主键更新全部,为null的数据也会更新,参数为包含id的map
	 * @param params
	 * @return
	 */
	public int updtAllByPk(Map<String,Object> params);
	
	/**
	 * 通过主键更新全部,只更新不为null的数据,参数为包含id的map
	 * @param params
	 * @return
	 */
	public int updtNotNullByPk(Map<String,Object> params);
	
	/**
	 * 通过主键获取,包括联合主键
	 * @param primarKey
	 * @return
	 */
	public T getByPk(Object primaryKey);
	
	/**
	 * 根据实体的条件查询,返回查询结果列表
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	/**
	 * 通过实体条件查询,获取一条数据
	 * @return
	 */
	public T findOne(T entity);
	
}
