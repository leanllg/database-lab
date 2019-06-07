package com.llgzone.market.auth;

import com.llgzone.market.utils.JWTUtil;
import com.llgzone.market.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationManager implements AuthenticationManager {

    /**
     * 进行token鉴定
     * @param authentication 待鉴定的JWTAuthenticationToken
     * @return 鉴定完成的JWTAuthenticationToken，供Controller使用
     * @throws AuthenticationException 如果鉴定失败，抛出
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();
        String uid = JWTUtil.getUid(token);
        Integer type = JWTUtil.getType(token);
        if (type == null || uid == null) {
            throw new BadCredentialsException("token错误");
        }
        User userEntity = new User();
        userEntity.setType(type.byteValue());
        userEntity.setUid(uid);


        boolean isAuthenticatedSuccess = JWTUtil.verify(token, uid, type);
        if (! isAuthenticatedSuccess) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        JWTAuthenticationToken authenticatedAuth = new JWTAuthenticationToken(
                token, userEntity, AuthorityUtils.commaSeparatedStringToAuthorityList(type == 0 ? "ROLE_USER" : "ROLE_MANAGER")
        );
        return authenticatedAuth;
    }
}