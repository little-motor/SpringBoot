package cn.littlemotor.web.aspect.service.imp;

import cn.littlemotor.web.aspect.service.interf.UserNote;
import cn.littlemotor.web.model.User;

public class UserNoteImpl implements UserNote {
    @Override
    public void printNote(User user) {
        System.out.println(user.getNote());
    }
}
