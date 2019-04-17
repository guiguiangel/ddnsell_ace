package com.ddn.ddnsell.vo;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import lombok.Data;

/**
 * @author qincx
 * @date 2019/4/14
 * @description
 */
@Data
public class RoleVo extends RoleInfo {
    private boolean isChecked = false;
}
