package cn.littlemotor.web.controller;

import cn.littlemotor.web.model.dao.LikeDao;
import cn.littlemotor.web.model.service.content.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 处理用户的点赞
 * @author littlemotor
 * @date 19.5.6
 */

@Controller
public class LikeController {

    @Autowired
    LikeDao likeDao = null;

    @PostMapping(path = "/message/like")
    public void addLikeNum(@RequestBody Like like){
        System.out.println("ID dian zan ： " + like.getLikeNum());
        likeDao.insertLikeNum(like);
        System.out.println("key: " + like.getLikeId());

    }

}
