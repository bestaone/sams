package cn.webestar.sams.starter.sequence;

import java.util.List;

public interface IdGenerator {

    /**
     * 生成主键
     */
    Long generate();

    /**
     * 批量生成主键
     */
    List<Long> generateBatch(int count);

}