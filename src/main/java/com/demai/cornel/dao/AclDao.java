package com.demai.cornel.dao;

import com.demai.cornel.model.AclInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AclDao {
    public void update(AclInfo aclInfo);

    public void save(AclInfo aclInfo);

    List<String> selectAclsByUserId(@Param("userId") String userId);

    List<String> selectAclsByUrl(@Param("url") String url);
}
