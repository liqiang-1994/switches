package io.luxyva.switches.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.luxyva.switches.storage.domain.FileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
