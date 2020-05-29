package myboot.mapper;


import org.apache.ibatis.annotations.Param;

public interface TestMapper {
    int insert(@Param("name") String name, @Param("age") Integer age);
}
