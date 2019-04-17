package com.ddn.ddnsell.utils;


import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.vo.ResultVO;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
public class ResultVoUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO(0,"成功",object);
        return resultVO;
    }

    public static ResultVO success(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO(resultEnum,null);
        return resultVO;
    }

    public static ResultVO success(ResultEnum resultEnum, Object object){
        ResultVO resultVO = new ResultVO(resultEnum,object);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO(0,"成功",null);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg, Object object){
        ResultVO resultVO = new ResultVO(code,msg,false,object);
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum, Object object){
        ResultVO resultVO = new ResultVO(resultEnum,false,object);
        return resultVO;
    }
}
