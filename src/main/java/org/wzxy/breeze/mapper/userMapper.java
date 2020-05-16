package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.User;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface userMapper {

    @SelectProvider(type = userMapperProvider.class,method = "findUserByFactor")
    @Results({
            @Result(id=true,column="uid",property="uid"),
            @Result(column="unum",property="unum"),
            @Result(column="upwd",property="upwd"),
            @Result(column = "uid",property = "roles",
                    one = @One(select = "org.wzxy.breeze.mapper.rolesMapper.findRoleByUid",fetchType = FetchType.LAZY)
            )
    })
     List<User> findUserByFactor(User user);

    @Select("SELECT * FROM user")
    @Results({
            @Result(id=true,column="uid",property="uid"),
            @Result(column="unum",property="unum"),
            @Result(column="upwd",property="upwd"),
            @Result(column = "uid",property = "roles",
                    one = @One(select = "org.wzxy.breeze.mapper.rolesMapper.findRoleByUid",fetchType = FetchType.LAZY)
            )
    })
     List<User> findAllUser();

    /*@InsertProvider(type = userMapperProvider.class,method = "addUser")
    int addUser(users user);

    @UpdateProvider(type = userMapperProvider.class,method = "updateUser")
    int updateUser(users user);

    @DeleteProvider(type = userMapperProvider.class,method = "deleteUser")
    int deleteUser(users user);*/
//////
@Insert("INSERT INTO user (uid,unum,upwd,utype) " +
        " VALUES(#{uid},#{unum},#{upwd},#{utype})")
    public void addUser(User user);

    @Delete("DELETE * FROM user WHERE uid = #{uid}")
    public void deleteUserById(User user);

    @Select("SELECT * FROM user WHERE uid = #{id}")
    public User queryUserById(int id);

    @Select("SELECT * FROM user WHERE unum = #{unum} AND utype=#{type}")
    public User queryUserByUnumAndUtype(@Param("unum") int unum, @Param("type") String type);

    @Update("UPDATE  user SET uid= #{uid} , unum = #{unum}, upwd = #{upwd}, utype = #{utype}  " +
            " WHERE uid = #{uid}")
    public void updateUser(User user);

    @Select("SELECT COUNT(*) FROM user")
    public int getTotalCount();

    @Select("SELECT * FROM user LIMIT limit #{nowPage}*#{pageSize},#{pageSize}")
    public List<User> getUsersByPage(int nowPage, int pageSize);



    class userMapperProvider{

        StringBuffer sql= new StringBuffer();

        public  String findUserByFactor(User user){
            sql.append("SELECT * FROM user");
            if(user.getUid()!=0){
                sql.append(" WHERE uid = #{uid}");
            }
            if(user.getUnum()!=0){
                if(user.getUid()!=0){
                    sql.append(" AND ");
                }else{
                    sql.append(" WHERE ");
                }
                sql.append("  unum = #{unum}");
            }
            if(user.getUpwd()!=null){
                if(user.getUnum()!=0||user.getUid()!=0){
                    sql.append(" AND ");
                }else{
                    sql.append(" WHERE ");
                }
                sql.append(" upwd = #{upwd}");
            }
            return  sql.toString();
        }



    }

}
