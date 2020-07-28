package com.njwpdi.modules.zk.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-16 10:23:51
 */
@Data
@TableName("ZK")
public class ZkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private String zkId;
	/**
	 * $column.comments
	 */
	private String zkNum;
	/**
	 * $column.comments
	 */
	private String zkPos;
	/**
	 * $column.comments
	 */
	private String zkElv;
	/**
	 * $column.comments
	 */
	private String zkDep;
	/**
	 * $column.comments
	 */
	private String zkDsp;
	/**
	 * $column.comments
	 */
	private String prjId;
	/**
	 * $column.comments
	 */
	private String prjName;

}
