package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.users;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface usersMapper {

    @SelectProvider(type = userMapperProvider.class,method = "findUserByFactor")
    @Results({
            @Result(id=true,column="userId",property="userId"),
            @Result(column="name",property="name"),
            @Result(column="password",property="password"),
            @Result(column = "userId",property = "profile",
            one = @One(select = "org.wzxy.breeze.mapper.profilesMapper.findFilesById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "userId",property = "roles",
                    one = @One(select = "org.wzxy.breeze.mapper.rolesMapper.findRoleByUserId",fetchType = FetchType.LAZY)
            )
    })
     List<users> findUserByFactor(users user);

    @Select("SELECT * FROM users")
    @Results({
            @Result(id=true,column="userId",property="userId"),
            @Result(column="name",property="name"),
            @Result(column="password",property="password"),
            @Result(column = "userId",property = "profile",
                    one = @One(select = "org.wzxy.breeze.mapper.profilesMapper.findFilesById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "userId",property = "roles",
                    one = @One(select = "org.wzxy.breeze.mapper.rolesMapper.findRoleByUserId",fetchType = FetchType.LAZY)
            )
    })
     List<users> findAllUser();

    @InsertProvider(type = userMapperProvider.class,method = "addUser")
    int addUser(users user);

    @UpdateProvider(type = userMapperProvider.class,method = "updateUser")
    int updateUser(users user);

    @DeleteProvider(type = userMapperProvider.class,method = "deleteUser")
    int deleteUser(users user);



    class userMapperProvider{

        StringBuffer sql= new StringBuffer();

        public  String addUser(users user){
            sql.append("INSERT INTO users");
            if(user.getUserId()!=0){
                sql.append(" VALUES(userId,#{userId});");
            }
            if(user.getName()!=null){
                sql.append(" VALUES(name,#{name});");
            }
            if(user.getPassword()!=null){
                sql.append(" VALUES(password,#{password});");
            }

            return  sql.toString();
        }

        public  String deleteUser(users user){
            sql.append("DELETE * from users");
            if(user.getUserId()!=0){
                sql.append(" WHERE userId = #{userId}");
            }
            return  sql.toString();
        }


        public  String findUserByFactor(users user){
            sql.append("SELECT * FROM users");
            if(user.getUserId()!=0){
                sql.append(" WHERE userId = #{userId}");
            }
            if(user.getName()!=null){
                if(user.getUserId()!=0){
                    sql.append(" AND ");
                }else{
                    sql.append(" WHERE ");
                }
                sql.append("  name = #{name}");
            }
            if(user.getPassword()!=null){
                if(user.getName()!=null||user.getUserId()!=0){
                    sql.append(" AND ");
                }else{
                    sql.append(" WHERE ");
                }
                sql.append(" password = #{password}");
            }
            System.out.println("第N次  "+sql);
            return  sql.toString();
        }


        public  String updateUser(users user){
            sql.append("UPDATE  users");
            if(user.getUserId()!=0){
                sql.append(" SET userId = #{userId};");
            }
            if(user.getName()!=null){
                sql.append(" SET name = #{name};");
            }
            if(user.getPassword()!=null){
                sql.append(" SET password = #{password};");
            }
            sql.append(" WHERE userId = #{userId}");
            return  sql.toString();
        }

    }

}
