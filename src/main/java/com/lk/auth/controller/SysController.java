package com.lk.auth.controller;

import com.lk.auth.domain.Auth;
import com.lk.auth.domain.RolesAuths;
import com.lk.auth.domain.User;
import com.lk.auth.domain.UsersRoles;
import com.lk.auth.domain.valid.ValidAdmin;
import com.lk.auth.enums.LoginEnum;
import com.lk.auth.repository.IAuthRepo;
import com.lk.auth.repository.IRolesAuthsRepo;
import com.lk.auth.repository.IUserRepo;
import com.lk.auth.repository.IUsersRolesRepo;
import com.lk.auth.security.shiro.AdminShiroUtil;
import com.lk.auth.security.shiro.CustomerAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SysController {
    @Resource
    private IUserRepo userRepo;
    @Resource
    private IAuthRepo authRepo;
    @Resource
    private IUsersRolesRepo usersRolesRepo;
    @Resource
    private IRolesAuthsRepo rolesAuthsRepo;

    @RequestMapping(value = "/home/doSysMenu")
    @ResponseBody
    public Object[] doSysMenu(HttpServletRequest request) {
        try {
            User user = AdminShiroUtil.getUserInfo();
            HttpSession session = request.getSession();
            Set<UsersRoles> usersRoles = usersRolesRepo.findByUserId(user.getId());
            Set<Auth> authSet = new HashSet<>();
            for (UsersRoles ur : usersRoles) {
                List<RolesAuths> rolesAuths = rolesAuthsRepo.findByRoleId(ur.getRoleId());
                for (RolesAuths ra : rolesAuths) {
                    Auth auth = authRepo.findByAuthId(ra.getAuthId());
                    authSet.add(auth);
                }

            }
            Object[] objects = authSet.toArray();
            return objects;
        } catch (Exception e) {
            //log.error("获取系统主菜单失败",e);
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String loginForm(){
        return "/login";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String loginPost(@Valid ValidAdmin validAdmin, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "redirect:/login";
        }
        String username = validAdmin.getUsername();
        CustomerAuthenticationToken token = new CustomerAuthenticationToken(validAdmin.getUsername(), validAdmin.getPassword(), false , "", "");
        token.setLoginType(LoginEnum.ADMIN.toString());
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("loginType",LoginEnum.ADMIN.toString());
            return "redirect:/home";
        }else{
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        System.out.println("PublicController.logout()");
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "redirect:/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        return "403";
    }
}
