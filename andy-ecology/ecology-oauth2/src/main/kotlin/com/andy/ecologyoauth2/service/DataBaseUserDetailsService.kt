package com.andy.ecologyoauth2.service

import com.andy.ecologyoauth2.dao.AccessTokenRepository
import com.andy.ecologyoauth2.dao.ClientDetailsRepository
import com.andy.ecologyoauth2.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.stream.Collectors

/**
 * FileName: DataBaseUserDetailsService
 * author:   候帅
 * data:     18/08/2018 07:40
 * Description: 自定义用户身份获取
 * History:
 */
@Service
class DataBaseUserDetailsService :UserDetailsService {

    companion object {
        private const val ROLE_PREFIX: String = "ROLE_"
    }

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var accessTokenRepository: AccessTokenRepository

    @Autowired
    private lateinit var clientDetailsRepository: ClientDetailsRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findOneByUsername(username!!)
                .map {
                    return@map User(it.username, it.password, it.roles.stream()
                            .map {
                                return@map SimpleGrantedAuthority(prefixRoleName(it.role.name))
                    }
                    .collect(Collectors.toList()))}
                    .orElseThrow {
                        return@orElseThrow UsernameNotFoundException("User $username was not found in the database")
                    }
    }

    /**
     * describe: 获取客户端的Token 内容
     * author 候帅
     * date 2018/8/18 下午10:21
     * @param tokenId token对应的id
     * @return   返回间隔时间
     */
    fun loadClientByToken(tokenId: String): Triple<Long, String, Long> {
        val clientId = accessTokenRepository
                .findOneByTokenId(tokenId)
                .map { return@map it.clientId }
                .orElseThrow {
                    return@orElseThrow UsernameNotFoundException("Token $tokenId was not found in the database")
                }
        val details = clientDetailsRepository.findOneByClientId(clientId).get()
        val clientLimit = details.clientLimit
        return Triple(clientLimit.intervalInMills, clientId, clientLimit.limits)
    }

    /**
     * describe: 将未有前缀的内容 添加
     * author 候帅
     * date 2018/8/18 下午10:20
     * @param roleName 角色名
     * @return 返回过滤的角色名
     */
    fun prefixRoleName(roleName: String): String {
        if (!StringUtils.isEmpty(roleName) && !roleName.startsWith(ROLE_PREFIX))
            return ROLE_PREFIX + roleName
        return roleName
    }
}
