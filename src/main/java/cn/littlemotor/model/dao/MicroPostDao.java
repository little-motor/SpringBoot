package cn.littlemotor.model.dao;//package cn.littlemotor.boot.model.dao;

import cn.littlemotor.model.MicroPostModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  Mabatis映射的接口
 *  @author littlemotor
 *  @date 18.12.6
 */
@Repository
public interface MicroPostDao {
  //增加消息
  public void insertMicroPost(MicroPostModel microPostModel);

  //修改消息
  public void updateMicroPost(MicroPostModel microPostModel);

  //查询消息
  public MicroPostModel getMicroPost(long id);

  //max代表返回的id中属性的最大值
  //count表明要返回多少个对象
  public List<MicroPostModel> findMicroPost(long max, int count);

  }
