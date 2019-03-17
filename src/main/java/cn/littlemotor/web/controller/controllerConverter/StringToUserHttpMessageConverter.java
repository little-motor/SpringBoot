package cn.littlemotor.web.controller.controllerConverter;

import cn.littlemotor.web.model.service.user.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

/**
 * 用于将json的string类型转换为User类型，通过实现httpmessageconverter实现，需要注意的是
 * 需要将自定义的conver放在converts线性表中的第一个
 *
 * 备注此处还有一些需要改进的地方，后期对canwrite源码读懂后加一下canwrite对方法，这样user信息
 * 返回时不会为空
 * @author littlemotor
 * @date 19.3.15
 */
public class StringToUserHttpMessageConverter extends AbstractHttpMessageConverter<User> {

    //设置默认字体和mime类型
    public StringToUserHttpMessageConverter(){
        super(Charset.forName("UTF-8"),MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN,MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        System.out.println("support:" + User.class.isAssignableFrom(clazz));
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        System.out.println("can read: " + super.canRead(clazz, mediaType));
        return super.canRead(clazz, mediaType);
    }


    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        System.out.println("can write: " + super.canWrite(clazz, mediaType));
        return super.canWrite(clazz, mediaType);
    }

    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String name, email, password;
        System.out.println("converter");
        //此处的httpinputmessage可以通过getbody方法返回html文件的主体inputstream
        //之后将其转换为string类型
        String json = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        int[] benginIndex = new int[3];
        int[] endIndex = new int[3];
        for(int i = 0; i <= 2; i++){
            //第一次返回头index
            if(i == 0){
                benginIndex[i] = json.indexOf(":\"") + 2;
                endIndex[i] = json.indexOf("\",");
                continue;
            }
            //第二次以上一次的index为起点往后查找
            benginIndex[i] = json.indexOf(":\"", benginIndex[i - 1]) + 2;
            if(i != 2) {
                endIndex[i] = json.indexOf("\",", endIndex[i - 1] + 1);
            }else {
                //最后一次endindex是以"}结束
                endIndex[i] = json.indexOf("\"}");
            }
        }
        //存储结果
        name = json.substring(benginIndex[0], endIndex[0]);
        email = json.substring(benginIndex[1], endIndex[1]);
        password = json.substring(benginIndex[2], endIndex[2]);
        return new User(name, email, password);
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }



}
