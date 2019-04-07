package cn.littlemotor.web.model.dao;

import cn.littlemotor.web.model.service.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//虽然限定了Repository接口，但是好像有没有都没区别
@Repository
//注意此处需要加Mapper注释，否则无法搜索到这个接口
@Mapper
public interface UserDao {

    public User getUserbyId(int id);

    //登陆时查找用户
    //security.UserDetailsServiceImp根据name查找用户
    public User getUserbyEmail(String email);

    //注册用户时使用
    public void setUser(User user);



}
