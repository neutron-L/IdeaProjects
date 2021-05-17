package cn.edu.bupt.OcrApp.controller;


import cn.edu.bupt.OcrApp.dao.UserDao;
import cn.edu.bupt.OcrApp.model.SysUser;
import cn.edu.bupt.OcrApp.dto.LoginUser;
import cn.edu.bupt.OcrApp.dto.Token;
import cn.edu.bupt.OcrApp.filter.TokenFilter;
import cn.edu.bupt.OcrApp.service.TokenService;
import cn.edu.bupt.common.annotation.AnonymousAccess;
import cn.edu.bupt.common.exception.BadRequestException;
import cn.edu.bupt.common.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录")
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @AnonymousAccess
    @ApiOperation("登录授权")
    @PostMapping(value = "/login")
    public CommonResult login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        SysUser sysUser = userDao.getUser(username);
        if (sysUser == null) {
            throw new BadRequestException("用户名不存在");
        }

        if (!passwordEncoder.matches(password, sysUser.getPassword())) {
            throw new BadRequestException("密码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Token token = tokenService.saveToken(loginUser);
        Map<String, Object> map = new HashMap<>();
        map.put("user", sysUser);
        map.put("token", token);
        return new CommonResult().success(map);
    }

    @AnonymousAccess
    @ApiOperation("退出登录")
    @DeleteMapping(value = "/logout")
    public CommonResult logout(HttpServletRequest request) {
        String token = TokenFilter.getToken(request);
        tokenService.deleteToken(token);
        return new CommonResult().success("退出成功");
    }
}
