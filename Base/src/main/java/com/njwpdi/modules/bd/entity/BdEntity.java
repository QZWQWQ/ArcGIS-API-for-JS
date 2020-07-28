package com.njwpdi.modules.bd.entity;

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
 * @date 2020-07-18 16:33:08
 */
@Data
@TableName("BD")
public class BdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId
	private String bdId;
	/**
	 * $column.comments
	 */
	private String bdName;
	/**
	 * $column.comments
	 */
	private String bdPos;
	/**
	 * $column.comments
	 */
	private String bdType;
	/**
	 * $column.comments
	 */
	private String prjId;
	/**
	 * $column.comments
	 */
	private String prjName;

}
