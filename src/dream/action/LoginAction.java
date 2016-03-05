package dream.action;

/**
 * Created by lenovo on 2015/6/15.
 */
import com.opensymphony.xwork2.Action;
import dream.entity.Admin;

import java.io.*;

public class LoginAction implements Action{
    private Admin admin;


    @Override
    public String execute() throws Exception {
        if (("admin").equals(admin.getUser_id()) && ("admin").equals(admin.getPwd()))
            return SUCCESS;
        else
            return INPUT;
    }

    public Admin getAdmin() {
        return admin;
    }

        public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}