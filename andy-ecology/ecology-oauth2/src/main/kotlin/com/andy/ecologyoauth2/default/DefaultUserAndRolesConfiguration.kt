//package com.andy.ecologyoauth2.default
//
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.InitializingBean
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Profile
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.transaction.annotation.Transactional
//import java.util.*
//
//@Configuration
//@Profile("default-user-and-roles_route")
//class DefaultUserAndRolesConfiguration: InitializingBean {
//
//    private val log: Logger = LoggerFactory.getLogger(DefaultUserAndRolesConfiguration::class.java)
//
//
//    companion object {
//
//        const val DEFAULT_ADMIN_USERNAME = "admin"
//
//        const val DEFAULT_ADMIN_PASSWORD = "admin"
//
//        const val DEFAULT_USER_USERNAME = "user"
//
//        const val DEFAULT_USER_PASSWORD = "user"
//
//        val DEFAULT_ROLES = arrayOf("ADMIN", "USER")
//    }
//
//    @Autowired
//    private lateinit var userRepository: UserRepository
//
//    @Autowired
//    private lateinit var roleRepository: RoleRepository
//
//    @Autowired
//    private lateinit var userRoleXrefRepository: UserRoleXrefRepository
//
//    @Autowired
//    private lateinit var passwordEncoder: PasswordEncoder
//
//    @Transactional
//    override fun afterPropertiesSet() {
//
//        val defaultRoleEntity = mutableListOf<RoleEntity>()
//
//        Arrays.stream(DEFAULT_ROLES).forEach {
//            val role = it
//            defaultRoleEntity.add(roleRepository.findOneByName(it).orElseGet {
//                val roleEntity = RoleEntity()
//                roleEntity.name = role
//                roleRepository.save(roleEntity)
//            })
//        }
//
//        val defaultAdminUserEntity = userRepository.findOneByUsername(DEFAULT_ADMIN_USERNAME).orElseGet {
//            val userEntity = UserEntity()
//            userEntity.username = DEFAULT_ADMIN_USERNAME
//            userEntity.password = passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD)
//            userRepository.save(userEntity)
//        }
//
//
//        // 避免重复加入
//        defaultRoleEntity.stream().forEach {
//            val findIsPeplace = userRoleXrefRepository.findIsPeplace(it, defaultAdminUserEntity)
//            if (!findIsPeplace.isPresent){
//                val userRoleXrefEntity = UserRoleXrefEntity()
//                userRoleXrefEntity.user = defaultAdminUserEntity
//                userRoleXrefEntity.role = it
//                userRoleXrefRepository.save(userRoleXrefEntity)
//            }
//        }
//
//        val defaultUserRole = userRepository.findOneByUsername(DEFAULT_USER_USERNAME).orElseGet {
//            val userEntity = UserEntity()
//            userEntity.username = DEFAULT_USER_USERNAME
//            userEntity.password = passwordEncoder.encode(DEFAULT_USER_PASSWORD)
//            return@orElseGet userRepository.save(userEntity)
//        }
//
//        defaultRoleEntity.stream().forEach {
//            val findIsPeplace = userRoleXrefRepository.findIsPeplace(it, defaultUserRole)
//            if (!findIsPeplace.isPresent){
//                val userRoleXrefEntity = UserRoleXrefEntity()
//                userRoleXrefEntity.user = defaultUserRole
//                userRoleXrefEntity.role = it
//                userRoleXrefRepository.save(userRoleXrefEntity)
//            }
//        }
//    }
//}