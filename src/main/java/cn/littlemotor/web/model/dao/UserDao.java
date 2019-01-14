package cn.littlemotor.web.model.dao;

import cn.littlemotor.web.model.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    public User getUser(int id);
}
