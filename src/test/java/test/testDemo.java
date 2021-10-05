package test;

import com.coshe.dao.DataMapper;
import com.coshe.pojo.Data;
import com.coshe.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class testDemo {
    @Test
    public void getDataList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        List<Data> dataList = mapper.getDataList();
        for (Data data : dataList) {
            System.out.println(data);
        }

        sqlSession.close();
    }

    @Test
    public void getDataByName(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        Data data = mapper.getDataByName("杰哥");
        System.out.println(data);

        sqlSession.close();
    }

    @Test
    public void getDataById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        DataMapper mapper = sqlSession.getMapper(DataMapper.class);
        Data data = mapper.getDataById(1);
        System.out.println(data);

        sqlSession.close();
    }


    @Test
    public void test01(){
        boolean[] flag = new boolean[4];

        for (boolean b : flag) {
            System.out.println(b);
        }
    }


    @Test
    public void test02(){
        Random random = new Random();
        List<Integer> randomList = new ArrayList<Integer>();
        boolean[] flag = new boolean[5 + 1];
        for (int i = 0; i < 4; i++) {
            int t = random.nextInt(5) + 1;
            while (flag[t]) t = (t + 1) % 5;
            flag[t] = true;
            randomList.add(t);
        }
        for (Integer integer : randomList) {
            System.out.println(integer);
        }

    }
}
