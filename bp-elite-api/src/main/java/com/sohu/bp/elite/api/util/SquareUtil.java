package com.sohu.bp.elite.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sohu.bp.elite.api.constants.Constants;
import com.sohu.bp.elite.enums.FeedType;


public class SquareUtil {
	private static final Logger log = LoggerFactory.getLogger(SquareUtil.class);
	
	public static Long getComplexId(Long objectId, FeedType type, Boolean isUpdate){
		if(objectId <= 0) return -1L;
		if(null == isUpdate) isUpdate = false;
		return (objectId * Constants.COMPLEX_ID_SHIFT + type.getValue()) * Constants.COMPLEX_ID_SHIFT + (isUpdate == true? 1 : 0);
	}
	
	public static FeedType getFeedType(Long complexId){
		if(null == complexId || complexId < Constants.COMPLEX_ID_SHIFT){
			return FeedType.QUESTION;
		}
		int value = (int) ((complexId/Constants.COMPLEX_ID_SHIFT) % Constants.COMPLEX_ID_SHIFT);
		return FeedType.getFeedTypeByValue(value);
	}
	
	public static Long getObjectId(Long complexId){
		if(null == complexId || complexId < Constants.COMPLEX_ID_SHIFT){
			return -1l;
		}
		Long objectId = (complexId / Constants.COMPLEX_ID_SHIFT) / Constants.COMPLEX_ID_SHIFT;
		return objectId;
	}
	
	public static Boolean getIsUpdate(Long complexId){
		return (complexId % Constants.COMPLEX_ID_SHIFT == 1) ? true : false;
	}
	
	public static String getProduceText(String user, FeedType feedType, Boolean isUpdate){
		return (isUpdate == true) ? user + "更新了" + feedType.getDesc() : user + feedType.getDesc() + "了";
	}
	
}
