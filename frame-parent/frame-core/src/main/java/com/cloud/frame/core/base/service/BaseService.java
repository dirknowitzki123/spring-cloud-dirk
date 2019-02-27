package com.cloud.frame.core.base.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.frame.mybatis.mapper.IBaseMapper;


/**
 * baseservice 实现类
 * @author HeJian
 *
 * @param <T>
 */
public class BaseService<T> implements IBaseService<T> {
	private static final Logger logger =  LoggerFactory.getLogger(BaseService.class);

	//同一个mode如果对应两个不同数据源的mapper请在响应主mapper上添加@Primary注解,否则会报错
	@Autowired
    protected IBaseMapper<T> mapper;
	
    public IBaseMapper<T> getMapper() {
        return mapper;
    }

    /**
	 * 保存,返回插入的条数
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public int insert(T entity) {
		return mapper.insert(entity);
	}

	/**
	 * 通过主键删除,包括联合主键,返回所删除的条数
	 * @param key
	 * @return
	 */
	@Override
	@Transactional
	public int deleteByPk(Object key) {
		return mapper.deleteByPrimaryKey(key);
	}
	
	/**
	 * 通过主键批量删除,返回所删除的条数
	 * 注意:只适合实体中只有一个id主键注解标识
	 * @param key
	 * @return
	 */
	@Override
	public int deleteByIds(List<String> ids) {
		if(ids==null || ids.size()==0){
			logger.error("传入参数id列表为空,不能进行删除操作!");
			return 0;
		}
		//String idsString = ids.toString().replaceAll("[\\[\\]\\s]", ""); // 转成如下形式: ids 如 "1,2,3,4"
		StringBuffer sbuf = new StringBuffer();
		for(String id:ids){
			sbuf.append("'").append(id).append("'").append(",");
		}
		sbuf.deleteCharAt(sbuf.length()-1);
		return mapper.deleteByIds(sbuf.toString());
	}

	/**
	 * 通过主键更新全部,为null的数据也会更新
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public int updateAllByPk(T entity) {
		 return mapper.updateByPrimaryKey(entity);
	}

	/**
	 * 通过主键更新全部,只更新不为null的数据
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional
	public int updateNotNullPk(T entity) {
		return mapper.updateByPrimaryKeySelective(entity);
	}

	/**
	 * 通过主键更新全部,为null的数据也会更新,参数为包含id的map
	 * @param params
	 * @return
	 */
	@Override
	@Transactional
	public int updtAllByPk(Map<String, Object> params) {
		return mapper.updtAllByPk(params);
	}

	/**
	 * 通过主键更新全部,只更新不为null的数据,参数为包含id的map
	 * @param params
	 * @return
	 */
	@Override
	@Transactional
	public int updtNotNullByPk(Map<String, Object> params) {
		return mapper.updtByPk(params);
	}

	/**
	 * 通过主键获取,包括联合主键
	 * @param primarKey
	 * @return
	 */
	@Override
	public T getByPk(Object primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	/**
	 * 根据实体的条件查询,返回查询结果列表
	 * @param entity
	 * @return
	 */
	@Override
	public List<T> findList(T entity) {
		return mapper.select(entity);
	}

	/**
	 * 通过实体条件查询,获取一条数据
	 * @return
	 */
	@Override
	public T findOne(T entity) {
		return mapper.selectOne(entity);
	}


}
